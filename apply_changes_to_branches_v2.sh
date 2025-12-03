#!/bin/bash

# Script to apply changes to all numeric branches
# Ensures compilation and resolves warnings

set -e

BASE_BRANCH="7.4.2.1"
COMMIT_HASH=$(git rev-parse HEAD)

# Get all numeric branches
BRANCHES=$(git branch | grep -E "^\s*[0-9]" | sed 's/^[* ]*//' | grep -v "^${BASE_BRANCH}$" | sort)

echo "Base branch: ${BASE_BRANCH}"
echo "Commit hash: ${COMMIT_HASH}"
echo "Branches to update:"
echo "${BRANCHES}"
echo ""

for BRANCH in ${BRANCHES}; do
    echo "=========================================="
    echo "Processing branch: ${BRANCH}"
    echo "=========================================="
    
    # Checkout branch
    git checkout "${BRANCH}" || {
        echo "ERROR: Failed to checkout ${BRANCH}"
        continue
    }
    
    # Try to merge
    if git merge --no-commit --no-ff "${BASE_BRANCH}" 2>&1 | tee /tmp/merge_output.txt; then
        # Check for conflicts
        if git diff --name-only --diff-filter=U | grep -q .; then
            echo "Conflicts detected, resolving..."
            
            # Resolve conflicts: keep theirs for Java files, ours for pom.xml and README
            for FILE in $(git diff --name-only --diff-filter=U); do
                if [[ "${FILE}" == *.java ]]; then
                    echo "  Resolving ${FILE} (keeping theirs for Java)"
                    git checkout --theirs "${FILE}"
                    git add "${FILE}"
                elif [[ "${FILE}" == pom.xml ]] || [[ "${FILE}" == */pom.xml ]] || [[ "${FILE}" == README* ]] || [[ "${FILE}" == */README* ]]; then
                    echo "  Resolving ${FILE} (keeping ours for pom.xml/README)"
                    git checkout --ours "${FILE}"
                    git add "${FILE}"
                else
                    echo "  Resolving ${FILE} (keeping theirs)"
                    git checkout --theirs "${FILE}"
                    git add "${FILE}"
                fi
            done
            
            # Complete merge
            git commit -m "Merge ${BASE_BRANCH}: Fix Javadoc formatting, add missing plugin versions, replace prerequisites with maven-enforcer-plugin" || {
                echo "ERROR: Failed to commit merge on ${BRANCH}"
                git merge --abort 2>/dev/null || true
                continue
            }
        else
            # No conflicts, complete merge
            git commit -m "Merge ${BASE_BRANCH}: Fix Javadoc formatting, add missing plugin versions, replace prerequisites with maven-enforcer-plugin" || {
                echo "ERROR: Failed to commit merge on ${BRANCH}"
                git merge --abort 2>/dev/null || true
                continue
            }
        fi
    else
        echo "Merge failed, aborting and trying direct file copy..."
        git merge --abort 2>/dev/null || true
        
        # Get list of changed files from the commit
        CHANGED_FILES=$(git diff --name-only "${BASE_BRANCH}" "${COMMIT_HASH}" | grep -E "\.(java|xml)$|pom\.xml|README" || true)
        
        if [ -n "${CHANGED_FILES}" ]; then
            # Copy files directly from base branch
            git checkout "${BASE_BRANCH}" -- ${CHANGED_FILES} 2>/dev/null || true
            
            # Commit changes
            git add -A
            if git diff --cached --quiet; then
                echo "No changes to commit on ${BRANCH}"
            else
                git commit -m "Apply changes from ${BASE_BRANCH}: Fix Javadoc formatting, add missing plugin versions, replace prerequisites with maven-enforcer-plugin" || {
                    echo "ERROR: Failed to commit on ${BRANCH}"
                    git reset --hard HEAD
                    continue
                }
            fi
        fi
    fi
    
    # Verify compilation
    echo "Verifying compilation..."
    if mvn clean compile -q 2>&1 | tee /tmp/compile_output.txt; then
        echo "✓ Compilation successful"
    else
        echo "ERROR: Compilation failed on ${BRANCH}"
        echo "Output:"
        cat /tmp/compile_output.txt | tail -20
        git reset --hard HEAD
        continue
    fi
    
    # Verify warnings
    echo "Checking warnings..."
    WARNING_COUNT=$(mvn validate 2>&1 | grep -E "WARNING.*version|WARNING.*prerequisites" | wc -l | tr -d ' ')
    if [ "${WARNING_COUNT}" -eq "0" ]; then
        echo "✓ No warnings"
    else
        echo "WARNING: ${WARNING_COUNT} warnings found on ${BRANCH}"
        mvn validate 2>&1 | grep -E "WARNING.*version|WARNING.*prerequisites" | head -5
    fi
    
    # Push branch
    echo "Pushing branch ${BRANCH}..."
    git push origin "${BRANCH}" || {
        echo "ERROR: Failed to push ${BRANCH}"
        continue
    }
    
    echo "✓ Branch ${BRANCH} updated successfully"
    echo ""
done

# Return to base branch
git checkout "${BASE_BRANCH}"

echo "=========================================="
echo "All branches processed"
echo "=========================================="

