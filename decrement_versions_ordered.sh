#!/bin/bash

# Script per decrementare le versioni nei pom.xml per tutti i branch di versione
# Processa dalla versione più vecchia alla più recente

# Verifica che Maven sia disponibile
if ! command -v mvn &> /dev/null; then
    echo "ERROR: Maven (mvn) is not available in PATH"
    echo "Please ensure Maven is installed and configured in your .zshrc"
    exit 1
fi

# Mappa delle versioni Java disponibili
declare -A JAVA_HOMES
JAVA_HOMES["7"]="/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home"
JAVA_HOMES["8"]="/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home"
JAVA_HOMES["11"]="/Library/Java/JavaVirtualMachines/jdk-11.0.9.jdk/Contents/Home"
JAVA_HOMES["17"]="/Library/Java/JavaVirtualMachines/jdk-17.0.1.jdk/Contents/Home"
JAVA_HOMES["18"]="/Library/Java/JavaVirtualMachines/jdk-18.0.1.jdk/Contents/Home"
JAVA_HOMES["19"]="/Library/Java/JavaVirtualMachines/jdk-19.jdk/Contents/Home"
JAVA_HOMES["20"]="/Library/Java/JavaVirtualMachines/jdk-20.0.1.jdk/Contents/Home"
JAVA_HOMES["21"]="/Library/Java/JavaVirtualMachines/jdk-21.0.2.jdk/Contents/Home"

# Funzione per leggere la versione Java dal README
get_java_version_from_readme() {
    local readme_file="$1"
    if [ ! -f "$readme_file" ]; then
        echo ""
        return
    fi
    
    # Cerca "Java X" nel README (es. "Java 17", "Java 8", "Java 11")
    local java_version=$(grep -iE "Java [0-9]+" "$readme_file" | head -1 | sed -E 's/.*[Jj]ava ([0-9]+).*/\1/')
    echo "$java_version"
}

# Funzione per impostare JAVA_HOME
set_java_home() {
    local java_version=$1
    local java_home="${JAVA_HOMES[$java_version]}"
    
    if [ -z "$java_home" ]; then
        echo "ERROR: Java version $java_version not found in available versions"
        return 1
    fi
    
    if [ ! -d "$java_home" ]; then
        echo "ERROR: Java home directory does not exist: $java_home"
        return 1
    fi
    
    export JAVA_HOME="$java_home"
    export PATH="$JAVA_HOME/bin:$PATH"
    echo "Set JAVA_HOME to: $JAVA_HOME"
    java -version
    return 0
}

decrement_version() {
    local version=$1
    # Estrae l'ultimo numero della versione (es. 23.2.1.11 -> 11)
    local last_num=$(echo "$version" | sed -E 's/.*\.([0-9]+)$/\1/')
    local prefix=$(echo "$version" | sed -E 's/(.*)\.[0-9]+$/\1/')
    
    # Decrementa l'ultimo numero
    local new_last=$((last_num - 1))
    
    # Se il numero diventa negativo, non decrementare
    if [ $new_last -lt 0 ]; then
        echo "$version"
    else
        echo "${prefix}.${new_last}"
    fi
}

process_branch() {
    local branch=$1
    echo "=========================================="
    echo "Processing branch: $branch"
    echo "=========================================="
    
    # Checkout del branch
    git checkout "$branch" || {
        echo "ERROR: Failed to checkout branch $branch"
        return 1
    }
    
    # Legge la versione Java richiesta dal README
    local java_version=$(get_java_version_from_readme "README.md")
    if [ -z "$java_version" ]; then
        echo "WARNING: Could not determine Java version from README.md, will use current Java for compilation"
    else
        echo "Required Java version: $java_version"
        set_java_home "$java_version" || {
            echo "ERROR: Failed to set Java version $java_version for branch $branch"
            return 1
        }
    fi
    
    # Trova tutti i pom.xml e legge la versione attuale (dal primo pom.xml principale)
    local current_version=$(grep -m 1 "<version>" pom.xml | sed -E 's/.*<version>([^<]+)<\/version>.*/\1/' | tr -d '[:space:]')
    
    if [ -z "$current_version" ]; then
        echo "ERROR: Could not find version in pom.xml for branch $branch"
        return 1
    fi
    
    echo "Current version: $current_version"
    
    # Decrementa la versione
    local new_version=$(decrement_version "$current_version")
    echo "New version: $new_version"
    
    # Aggiorna tutti i pom.xml
    while IFS= read -r pomfile; do
        # Salta i file in target
        if [[ "$pomfile" == *"/target/"* ]]; then
            continue
        fi
        
        # Aggiorna la versione nel pom.xml
        sed -i '' "s/<version>${current_version}<\/version>/<version>${new_version}<\/version>/g" "$pomfile"
        echo "Updated: $pomfile"
    done < <(find . -name "pom.xml" -type f)
    
    # Verifica che ci siano modifiche
    if git diff --quiet; then
        echo "WARNING: No changes detected for branch $branch"
        return 0
    fi
    
    # Compila di nuovo con la nuova versione per verificare che funzioni PRIMA di committare
    echo "Compiling with new version to verify it works..."
    if ! mvn clean compile -DskipTests; then
        echo "=========================================="
        echo "ERROR: Compilation failed with new version for branch $branch"
        echo "Reverting version changes. Please fix compilation errors manually first."
        echo "=========================================="
        git checkout -- .
        echo "You are now on branch $branch - fix errors and re-run the script."
        return 1
    fi
    echo "Compilation with new version successful!"
    
    # Commit solo se la compilazione è riuscita
    git add -A
    git commit -m "Decrement version from $current_version to $new_version" || {
        echo "ERROR: Failed to commit for branch $branch"
        return 1
    }
    
    # Push
    git push origin "$branch" || {
        echo "ERROR: Failed to push branch $branch"
        return 1
    }
    
    # Crea tag
    git tag "$new_version" || {
        echo "ERROR: Failed to create tag $new_version for branch $branch"
        return 1
    }
    
    # Push tag
    git push origin "$new_version" || {
        echo "ERROR: Failed to push tag $new_version for branch $branch"
        return 1
    }
    
    echo "SUCCESS: Branch $branch processed successfully"
    echo ""
}

# Lista dei branch di versione da processare (solo quelli non ancora processati)
# In ordine dalla più vecchia alla più recente
branches=(
    "4.2.c"
    "4.2.6.6"
    "5.0.d"
    "5.1.g"
    "5.2.7"
    "5.2.f"
    "5.2.g"
    "6.0.0-ea"
    "6.0.2-ea"
    "6.1.0-ea"
    "6.2.0-ea"
    "6.2.2"
    "7.0.0-A20"
    "7.1.0"
    "7.2.0"
    "7.2.1"
    "7.3.0"
)

# Salva il branch corrente
current_branch=$(git branch --show-current)

# Processa ogni branch in ordine
for branch in "${branches[@]}"; do
    process_branch "$branch"
done

# Torna al branch originale
git checkout "$current_branch"

echo "=========================================="
echo "All branches processed!"
echo "=========================================="

