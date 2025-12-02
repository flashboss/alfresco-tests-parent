#!/bin/bash

# Script helper per correggere gli errori comuni in MockRuleService.java

FILE="alfresco-tests/src/main/java/org/alfresco/mock/test/MockRuleService.java"

if [ ! -f "$FILE" ]; then
    echo "ERROR: File $FILE not found"
    exit 1
fi

# 1. Cambia saveRule da Rule a void
sed -i '' 's/public Rule saveRule(NodeRef nodeRef, Rule rule)/public void saveRule(NodeRef nodeRef, Rule rule)/g' "$FILE"
sed -i '' 's/return null;/return;/g' "$FILE"
sed -i '' '/return rule;$/d' "$FILE"

# 2. Rimuove @Override dai metodi che non sovrascrivono
sed -i '' '/^[[:space:]]*@Override$/{N; /^[[:space:]]*@Override[[:space:]]*\n[[:space:]]*public boolean hasNonInheritedRules/d; }' "$FILE"
sed -i '' '/^[[:space:]]*@Override$/{N; /^[[:space:]]*@Override[[:space:]]*\n[[:space:]]*public List<NodeRef> getNodesSupplyingRuleSets/d; }' "$FILE"
sed -i '' '/^[[:space:]]*@Override$/{N; /^[[:space:]]*@Override[[:space:]]*\n[[:space:]]*public List<NodeRef> getFoldersInheritingRuleSet/d; }' "$FILE"
sed -i '' '/^[[:space:]]*@Override$/{N; /^[[:space:]]*@Override[[:space:]]*\n[[:space:]]*public List<NodeRef> getFoldersLinkingToRuleSet/d; }' "$FILE"
sed -i '' '/^[[:space:]]*@Override$/{N; /^[[:space:]]*@Override[[:space:]]*\n[[:space:]]*public NodeRef getOwningNodeRef(NodeRef ruleSet)/d; }' "$FILE"
sed -i '' '/^[[:space:]]*@Override$/{N; /^[[:space:]]*@Override[[:space:]]*\n[[:space:]]*public List<NodeRef> getLinkedFromRuleNodes/d; }' "$FILE"
sed -i '' '/^[[:space:]]*@Override$/{N; /^[[:space:]]*@Override[[:space:]]*\n[[:space:]]*public NodeRef getRuleSetNode/d; }' "$FILE"
sed -i '' '/^[[:space:]]*@Override$/{N; /^[[:space:]]*@Override[[:space:]]*\n[[:space:]]*public boolean isRuleSetAssociatedWithFolder/d; }' "$FILE"
sed -i '' '/^[[:space:]]*@Override$/{N; /^[[:space:]]*@Override[[:space:]]*\n[[:space:]]*public boolean isRuleAssociatedWithRuleSet/d; }' "$FILE"
sed -i '' '/^[[:space:]]*@Override$/{N; /^[[:space:]]*@Override[[:space:]]*\n[[:space:]]*public boolean isRuleSetShared/d; }' "$FILE"

# 3. Correggi getFoldersInheritingRuleSet per gestire subList
sed -i '' 's/return folders\.subList(0, maxFoldersToReturn);/return new ArrayList<>(folders.subList(0, maxFoldersToReturn));/g' "$FILE"

# 4. Correggi getLinkedFromRuleNodes
sed -i '' 's/return folders != null ? new ArrayList<>(folders) : new ArrayList<>();/if (folders == null) { return new ArrayList<>(); } return new ArrayList<>(folders);/g' "$FILE"

echo "Fixed MockRuleService.java"

