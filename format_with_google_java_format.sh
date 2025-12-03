#!/bin/bash

# Script to format all Java code using google-java-format
# and apply to all numeric branches (without testing)

set -e

BASE_BRANCH="7.4.2.1"

echo "Formatting all Java code with google-java-format..."
echo "===================================================="
echo ""

# Check if google-java-format is available
if ! command -v google-java-format &> /dev/null; then
    echo "ERROR: google-java-format not found!"
    echo "Please install it with: brew install google-java-format"
    exit 1
fi

# Set Java 17 for google-java-format (requires Java 11+)
export JAVA_HOME=$(/usr/libexec/java_home -v 17 2>/dev/null || /usr/libexec/java_home -v 11 2>/dev/null || /usr/libexec/java_home)
export PATH="${JAVA_HOME}/bin:${PATH}"

echo "Using Java: ${JAVA_HOME}"
java -version 2>&1 | head -1
echo "Using google-java-format: $(google-java-format --version 2>&1 | head -1)"
echo ""

# Format on base branch
echo "Formatting on base branch: ${BASE_BRANCH}"
git checkout "${BASE_BRANCH}"

# Get all Java files
JAVA_FILES=$(find . -name "*.java" -type f | grep -v target | grep -v ".git" | sort)

echo "Found $(echo "${JAVA_FILES}" | wc -l | tr -d ' ') Java files"
echo "Formatting..."

# Format all Java files
COUNT=0
SUCCESS=0
FAILED=0
for FILE in ${JAVA_FILES}; do
    if [ -f "${FILE}" ]; then
        if google-java-format -i "${FILE}" 2>/dev/null; then
            SUCCESS=$((SUCCESS + 1))
        else
            FAILED=$((FAILED + 1))
            echo "  Warning: Failed to format ${FILE}"
        fi
        COUNT=$((COUNT + 1))
        if [ $((COUNT % 50)) -eq 0 ]; then
            echo "  Processed ${COUNT} files... (${SUCCESS} formatted, ${FAILED} failed)"
        fi
    fi
done

echo "  Processed ${COUNT} files total (${SUCCESS} formatted, ${FAILED} failed)"
echo ""

# Commit formatting changes
if ! git diff --quiet; then
    git add -A
    git commit -m "Format all Java code using google-java-format" || true
    git push origin "${BASE_BRANCH}" || true
    echo "✓ Formatted and pushed ${BASE_BRANCH}"
else
    echo "No formatting changes needed on ${BASE_BRANCH}"
fi

# Get all numeric branches
BRANCHES=$(git branch | grep -E "^\s*[0-9]" | sed 's/^[* ]*//' | grep -v "^${BASE_BRANCH}$" | sort)

echo ""
echo "Applying formatting to other branches (no testing)..."
echo "====================================================="
echo ""

for BRANCH in ${BRANCHES}; do
    echo "Processing branch: ${BRANCH}"
    
    # Checkout branch
    git checkout "${BRANCH}" || {
        echo "ERROR: Failed to checkout ${BRANCH}"
        continue
    }
    
    # Try to merge formatting changes
    if git merge --no-commit --no-ff "${BASE_BRANCH}" 2>&1 | tee /tmp/merge_output_${BRANCH}.txt; then
        # Check for conflicts
        CONFLICT_FILES=$(git diff --name-only --diff-filter=U 2>/dev/null || true)
        if [ -n "${CONFLICT_FILES}" ]; then
            echo "  Conflicts detected, resolving (keeping theirs for Java files)..."
            
            # Resolve conflicts: keep theirs for Java files, ours for pom.xml and README
            for FILE in ${CONFLICT_FILES}; do
                if [[ "${FILE}" == *.java ]]; then
                    git checkout --theirs "${FILE}" 2>/dev/null || true
                    git add "${FILE}" 2>/dev/null || true
                elif [[ "${FILE}" == pom.xml ]] || [[ "${FILE}" == */pom.xml ]] || [[ "${FILE}" == README* ]] || [[ "${FILE}" == */README* ]]; then
                    git checkout --ours "${FILE}" 2>/dev/null || true
                    git add "${FILE}" 2>/dev/null || true
                else
                    git checkout --theirs "${FILE}" 2>/dev/null || true
                    git add "${FILE}" 2>/dev/null || true
                fi
            done
        fi
        
        # Complete merge
        git commit -m "Merge ${BASE_BRANCH}: Format all Java code using google-java-format" || {
            echo "ERROR: Failed to commit merge on ${BRANCH}"
            git merge --abort 2>/dev/null || true
            continue
        }
    else
        echo "  Merge failed, skipping..."
        git merge --abort 2>/dev/null || true
        continue
    fi
    
    # Push branch
    git push origin "${BRANCH}" || {
        echo "ERROR: Failed to push ${BRANCH}"
        continue
    }
    
    echo "✓ Branch ${BRANCH} updated"
    echo ""
done

# Return to base branch
git checkout "${BASE_BRANCH}"

echo "=========================================="
echo "All branches processed (no testing performed)"
echo "=========================================="
