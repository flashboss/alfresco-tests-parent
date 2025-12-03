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
        # Merge successful, check for conflicts
        if grep -q "CONFLICT" /tmp/merge_output.txt; then
            echo "Conflicts detected, resolving..."
            # Resolve conflicts: keep theirs for Java files, ours for pom.xml and README
            git checkout --theirs -- "**/*.java" 2>/dev/null || true
            git checkout --ours -- "**/pom.xml" 2>/dev/null || true
            git checkout --ours -- "**/README*" 2>/dev/null || true
            
            # Add resolved files
            git add -A
            
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
        echo "Merge failed, trying cherry-pick..."
        git reset --hard HEAD
        
        # Try cherry-pick
        if git cherry-pick --no-commit "${COMMIT_HASH}" 2>&1 | tee /tmp/cherrypick_output.txt; then
            # Cherry-pick successful, check for conflicts
            if grep -q "CONFLICT" /tmp/cherrypick_output.txt; then
                echo "Conflicts detected, resolving..."
                # Resolve conflicts: keep theirs for Java files, ours for pom.xml and README
                git checkout --theirs -- "**/*.java" 2>/dev/null || true
                git checkout --ours -- "**/pom.xml" 2>/dev/null || true
                git checkout --ours -- "**/README*" 2>/dev/null || true
                
                # Add resolved files
                git add -A
                
                # Complete cherry-pick
                git cherry-pick --continue || {
                    echo "ERROR: Failed to continue cherry-pick on ${BRANCH}"
                    git cherry-pick --abort 2>/dev/null || true
                    continue
                }
            else
                # No conflicts, complete cherry-pick
                git commit --no-edit || {
                    echo "ERROR: Failed to commit cherry-pick on ${BRANCH}"
                    git cherry-pick --abort 2>/dev/null || true
                    continue
                }
            fi
        else
            echo "ERROR: Cherry-pick failed on ${BRANCH}"
            git cherry-pick --abort 2>/dev/null || true
            continue
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

