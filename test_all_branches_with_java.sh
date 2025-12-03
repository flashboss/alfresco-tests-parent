#!/bin/bash

# Script to test all numeric branches with the correct Java version from README

set -e

BASE_BRANCH="7.4.2.1"
RESULTS_FILE="/tmp/branch_test_results.txt"

# Get all numeric branches
BRANCHES=$(git branch | grep -E "^\s*[0-9]" | sed 's/^[* ]*//' | sort)

echo "Testing all branches with correct Java versions from README"
echo "=========================================================="
echo ""

# Clear results file
> "${RESULTS_FILE}"

# Function to get Java version from README
get_java_version() {
    local BRANCH=$1
    local README_CONTENT=$(git show "${BRANCH}:README.md" 2>/dev/null || echo "")
    
    if [ -z "${README_CONTENT}" ]; then
        echo "unknown"
        return
    fi
    
    # Try to extract Java version (look for "Java 7", "Java 8", "Java 11", "Java 17", etc.)
    local JAVA_VERSION=$(echo "${README_CONTENT}" | grep -iE "Java [0-9]+|JDK [0-9]+|JRE [0-9]+" | head -1 | sed -E 's/.*[Jj]ava[[:space:]]+([0-9]+).*/\1/' | head -1)
    
    if [ -z "${JAVA_VERSION}" ]; then
        echo "unknown"
    else
        echo "${JAVA_VERSION}"
    fi
}

# Function to set JAVA_HOME for a specific Java version
set_java_home() {
    local JAVA_VERSION=$1
    
    case "${JAVA_VERSION}" in
        7|1.7)
            export JAVA_HOME=$(/usr/libexec/java_home -v 1.7 2>/dev/null || echo "")
            ;;
        8|1.8)
            export JAVA_HOME=$(/usr/libexec/java_home -v 1.8 2>/dev/null || echo "")
            ;;
        11)
            export JAVA_HOME=$(/usr/libexec/java_home -v 11 2>/dev/null || echo "")
            ;;
        12|13|14|15|16)
            export JAVA_HOME=$(/usr/libexec/java_home -v "${JAVA_VERSION}" 2>/dev/null || echo "")
            ;;
        17)
            export JAVA_HOME=$(/usr/libexec/java_home -v 17 2>/dev/null || echo "")
            ;;
        18|19|20|21)
            export JAVA_HOME=$(/usr/libexec/java_home -v "${JAVA_VERSION}" 2>/dev/null || echo "")
            ;;
        *)
            # Try to find any matching version
            export JAVA_HOME=$(/usr/libexec/java_home -v "${JAVA_VERSION}" 2>/dev/null || echo "")
            ;;
    esac
    
    if [ -z "${JAVA_HOME}" ]; then
        echo "WARNING: Java ${JAVA_VERSION} not found, using default"
        export JAVA_HOME=$(/usr/libexec/java_home)
    fi
    
    export PATH="${JAVA_HOME}/bin:${PATH}"
}

# Function to test a branch
test_branch() {
    local BRANCH=$1
    local JAVA_VERSION=$(get_java_version "${BRANCH}")
    
    echo "=========================================="
    echo "Testing branch: ${BRANCH}"
    echo "Required Java version: ${JAVA_VERSION}"
    echo "=========================================="
    
    # Checkout branch
    git checkout "${BRANCH}" || {
        echo "ERROR: Failed to checkout ${BRANCH}" | tee -a "${RESULTS_FILE}"
        return 1
    }
    
    # Set Java version
    if [ "${JAVA_VERSION}" != "unknown" ]; then
        set_java_home "${JAVA_VERSION}"
        if [ -n "${JAVA_HOME}" ] && [ -d "${JAVA_HOME}" ]; then
            export PATH="${JAVA_HOME}/bin:${PATH}"
            echo "Using Java: ${JAVA_HOME}"
            java -version 2>&1 | head -1
        else
            echo "WARNING: Java ${JAVA_VERSION} not found, using default"
            export JAVA_HOME=$(/usr/libexec/java_home)
            export PATH="${JAVA_HOME}/bin:${PATH}"
            java -version 2>&1 | head -1
        fi
    else
        echo "WARNING: Could not determine Java version from README, using default"
        export JAVA_HOME=$(/usr/libexec/java_home)
        export PATH="${JAVA_HOME}/bin:${PATH}"
        java -version 2>&1 | head -1
    fi
    
    # Verify warnings
    echo "Checking warnings..."
    WARNING_COUNT=$(mvn validate 2>&1 | grep -E "WARNING.*version|WARNING.*prerequisites" | wc -l | tr -d ' ')
    if [ "${WARNING_COUNT}" -eq "0" ]; then
        echo "✓ No warnings"
        WARNINGS_OK="YES"
    else
        echo "WARNING: ${WARNING_COUNT} warnings found"
        mvn validate 2>&1 | grep -E "WARNING.*version|WARNING.*prerequisites" | head -3
        WARNINGS_OK="NO"
    fi
    
    # Try compilation
    echo "Compiling..."
    if mvn clean compile -q 2>&1 | tee /tmp/compile_output_${BRANCH}.txt; then
        echo "✓ Compilation successful"
        COMPILE_OK="YES"
    else
        # Check error type
        if grep -q "javax.xml.bind\|javax.transaction" /tmp/compile_output_${BRANCH}.txt; then
            echo "⚠ Compilation failed due to Java version differences (expected for some branches)"
            COMPILE_OK="PARTIAL"
        elif grep -q "Source option.*no longer supported\|Target option.*no longer supported" /tmp/compile_output_${BRANCH}.txt; then
            echo "⚠ Compilation failed due to Java version mismatch"
            COMPILE_OK="NO"
        else
            echo "✗ Compilation failed"
            cat /tmp/compile_output_${BRANCH}.txt | grep -E "ERROR|error" | tail -5
            COMPILE_OK="NO"
        fi
    fi
    
    # Record results
    echo "${BRANCH}|${JAVA_VERSION}|${WARNINGS_OK}|${COMPILE_OK}" >> "${RESULTS_FILE}"
    
    echo ""
}

# Test all branches
for BRANCH in ${BRANCHES}; do
    test_branch "${BRANCH}"
done

# Return to base branch
git checkout "${BASE_BRANCH}"

# Print summary
echo "=========================================="
echo "TEST SUMMARY"
echo "=========================================="
echo ""
echo "Branch | Java | Warnings OK | Compile OK"
echo "----------------------------------------"
cat "${RESULTS_FILE}" | while IFS='|' read BRANCH JAVA WARN COMPILE; do
    printf "%-15s | %-6s | %-10s | %s\n" "${BRANCH}" "${JAVA}" "${WARN}" "${COMPILE}"
done

echo ""
echo "Full results saved to: ${RESULTS_FILE}"

