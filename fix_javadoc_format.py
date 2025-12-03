#!/usr/bin/env python3
"""
Script to fix malformed Javadoc comments by removing extra blank lines.
"""

import os
import re
from pathlib import Path

def fix_javadoc_in_file(file_path):
    """Fix malformed Javadoc in a single file."""
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
        
        original_content = content
        
        # Pattern to match Javadoc with extra blank lines
        # Matches /** followed by optional blank lines, then content, then */
        pattern = r'(\s+)/\*\*(\s+\n\s*\n\s*)\*(\s+\{@inheritDoc\}\s+)\*(\s+\n\s*\n\s*)\*/'
        
        def fix_javadoc(match):
            indent = match.group(1)
            return f'{indent}/**\n{indent} * {{@inheritDoc}}\n{indent} */'
        
        # Replace malformed Javadoc
        content = re.sub(pattern, fix_javadoc, content, flags=re.MULTILINE)
        
        # Also fix cases with multiple blank lines in Javadoc
        # Pattern: /** followed by one or more blank lines, then * {@inheritDoc}, then blank lines, then */
        pattern2 = r'(\s+)/\*\*(\s*\n\s*\n\s*)\*(\s*\{@inheritDoc\}\s*)\*(\s*\n\s*\n\s*)\*/'
        content = re.sub(pattern2, r'\1/**\n\1 * {@inheritDoc}\n\1 */', content, flags=re.MULTILINE)
        
        # More aggressive pattern for any Javadoc with blank lines
        # Match /** ... blank lines ... * content ... blank lines ... */
        lines = content.split('\n')
        fixed_lines = []
        i = 0
        while i < len(lines):
            line = lines[i]
            # Check if this is a Javadoc start
            if re.match(r'\s*/\*\*', line):
                fixed_lines.append(line)
                i += 1
                # Skip blank lines after /**
                while i < len(lines) and re.match(r'\s*$', lines[i]):
                    i += 1
                # Add content lines (starting with *)
                while i < len(lines) and (re.match(r'\s*\*', lines[i]) or re.match(r'\s*$', lines[i])):
                    content_line = lines[i]
                    # Skip blank lines inside Javadoc
                    if not re.match(r'\s*$', content_line):
                        fixed_lines.append(content_line)
                    i += 1
                # Skip blank lines before */
                while i < len(lines) and re.match(r'\s*$', lines[i]):
                    i += 1
                # Add closing */
                if i < len(lines) and re.match(r'\s*\*/', lines[i]):
                    fixed_lines.append(lines[i])
                    i += 1
            else:
                fixed_lines.append(line)
                i += 1
        
        content = '\n'.join(fixed_lines)
        
        # Final cleanup: remove blank lines between /** and * {@inheritDoc}
        content = re.sub(r'(\s+/\*\*)\s+\n\s+\n(\s+\* \{@inheritDoc\})', r'\1\n\2', content)
        # Remove blank lines between * {@inheritDoc} and */
        content = re.sub(r'(\s+\* \{@inheritDoc\})\s+\n\s+\n(\s+\*/)', r'\1\n\2', content)
        
        # Only write if content changed
        if content != original_content:
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(content)
            return True
        return False
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
        if fix_javadoc_in_file(java_file):
            processed += 1
            print(f"Fixed: {java_file}")
    
    print(f"\nFixed {processed} files")

if __name__ == '__main__':
    main()

