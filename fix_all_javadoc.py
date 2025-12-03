#!/usr/bin/env python3
"""
Script to fix all malformed Javadoc comments by removing extra blank lines.
"""

import os
import re
from pathlib import Path

def fix_javadoc_in_file(file_path):
    """Fix malformed Javadoc in a single file."""
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            lines = f.readlines()
        
        fixed_lines = []
        i = 0
        
        while i < len(lines):
            line = lines[i]
            
            # Check if this is a Javadoc start
            if re.match(r'^\s*/\*\*\s*$', line):
                indent = len(line) - len(line.lstrip())
                fixed_lines.append(line.rstrip() + '\n')
                i += 1
                
                # Skip blank lines after /**
                while i < len(lines) and re.match(r'^\s*$', lines[i]):
                    i += 1
                
                # Collect content lines (starting with *)
                content_lines = []
                while i < len(lines):
                    if re.match(r'^\s*\*/\s*$', lines[i]):
                        # Found closing */
                        break
                    elif re.match(r'^\s*\*', lines[i]):
                        # Content line
                        content_lines.append(lines[i])
                        i += 1
                        # Skip blank lines after content
                        while i < len(lines) and re.match(r'^\s*$', lines[i]):
                            i += 1
                    elif re.match(r'^\s*$', lines[i]):
                        # Blank line - skip it
                        i += 1
                    else:
                        # Not part of Javadoc
                        break
                
                # Add content lines
                for content_line in content_lines:
                    fixed_lines.append(content_line.rstrip() + '\n')
                
                # Add closing */
                if i < len(lines) and re.match(r'^\s*\*/\s*$', lines[i]):
                    fixed_lines.append(lines[i].rstrip() + '\n')
                    i += 1
            else:
                fixed_lines.append(line)
                i += 1
        
        # Write back
        with open(file_path, 'w', encoding='utf-8') as f:
            f.writelines(fixed_lines)
        
        return True
    except Exception as e:
        print(f"Error processing {file_path}: {e}")
        return False

def main():
    """Main function to process all Java files."""
    project_root = Path(__file__).parent
    java_files = list(project_root.rglob('*.java'))
    
    # Filter out generated and target files
    java_files = [f for f in java_files if 'generated' not in str(f) and 'target' not in str(f)]
    
    print(f"Found {len(java_files)} Java files")
    
    processed = 0
    for java_file in java_files:
        try:
            with open(java_file, 'r', encoding='utf-8') as f:
                original = f.read()
            
            if fix_javadoc_in_file(java_file):
                with open(java_file, 'r', encoding='utf-8') as f:
                    new_content = f.read()
                if original != new_content:
                    processed += 1
                    print(f"Fixed: {java_file}")
        except:
            pass
    
    print(f"\nFixed {processed} files")

if __name__ == '__main__':
    main()

