package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.rule.RuleServiceImpl.ExecutedRuleData;
import org.alfresco.repo.rule.RuntimeRuleService;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ActionService;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.rule.Rule;
import org.alfresco.service.cmr.rule.RuleService;
import org.alfresco.service.cmr.rule.RuleType;
import org.alfresco.service.namespace.QName;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mock implementation of RuleService for testing purposes.
 * Provides in-memory storage for rules, rule types, and rule execution.
 *
 * @author vige
 */
public class MockRuleService implements RuleService, RuntimeRuleService,
		NodeServicePolicies.OnCreateChildAssociationPolicy, NodeServicePolicies.OnCreateNodePolicy,
		NodeServicePolicies.OnUpdateNodePolicy, NodeServicePolicies.OnAddAspectPolicy, Serializable {

	/** The action service for executing rule actions. */
	@Autowired
	/** The action service. */
	private ActionService actionService;

	/** Storage for rules indexed by folder nodeRef. */
	private Map<NodeRef, List<Rule>> rulesByFolder = new HashMap<>();

	/** Storage for rules indexed by rule nodeRef. */
	private Map<NodeRef, Rule> rulesByNodeRef = new HashMap<>();

	/** Storage for registered rule types. */
	private Map<String, RuleType> ruleTypes = new HashMap<>();

	/** Global enabled state for all rules. */
	private boolean globallyEnabled = true;

	/** Set of folders with disabled rules. */
	private Set<NodeRef> disabledFolders = new HashSet<>();

	/** Set of disabled rule nodeRefs. */
	private Set<NodeRef> disabledRules = new HashSet<>();

	/** Set of disabled rule type names. */
	private Set<String> disabledRuleTypes = new HashSet<>();

	/** Pending rules waiting for execution. */
	private Map<NodeRef, List<PendingRule>> pendingRules = new HashMap<>();

	/** Maps rules to their owning folder nodeRef. */
	private Map<Rule, NodeRef> ruleOwnership = new HashMap<>();

	/** Maps actions to their owning nodeRef. */
	private Map<Action, NodeRef> actionOwnership = new HashMap<>();

	/** Maps rule sets to their owning nodeRef. */
	private Map<NodeRef, NodeRef> ruleSetOwnership = new HashMap<>();

	/** Maps folders to their associated rule set. */
	private Map<NodeRef, NodeRef> folderToRuleSet = new HashMap<>();

	/** Maps rule sets to the folders that inherit them. */
	private Map<NodeRef, List<NodeRef>> ruleSetToFolders = new HashMap<>();

	/** Maps rules to their containing rule set. */
	private Map<NodeRef, NodeRef> ruleToRuleSet = new HashMap<>();

	/**
	 * Internal class representing a pending rule awaiting execution.
	 */
	private static class PendingRule {
		/** The actionable node reference. */
		@SuppressWarnings("unused")
		NodeRef actionableNodeRef;

		/** The node being acted upon. */
		NodeRef actionedUponNodeRef;

		/** The rule to execute. */
		Rule rule;

		/** Whether to execute at end of transaction. */
		boolean executeAtEnd;

		/**
		 * Constructs a new PendingRule.
		 *
		 * @param actionableNodeRef the actionable node reference
		 * @param actionedUponNodeRef the node being acted upon
		 * @param rule the rule to execute
		 * @param executeAtEnd whether to execute at end of transaction
		 */
		PendingRule(NodeRef actionableNodeRef, NodeRef actionedUponNodeRef, Rule rule, boolean executeAtEnd) {
			this.actionableNodeRef = actionableNodeRef;
			this.actionedUponNodeRef = actionedUponNodeRef;
			this.rule = rule;
			this.executeAtEnd = executeAtEnd;
		}
	}

	@Override
	/**
	 * On add aspect.
	 *
	 * @param nodeRef the node ref
	 * @param aspectTypeQName the aspect type q name
	 */
	public void onAddAspect(NodeRef nodeRef, QName aspectTypeQName) {
		// In a real implementation, this would trigger rules for aspect addition
		// For mock, we can leave it empty or trigger rules if needed
	}

	@Override
	/**
	 * On update node.
	 *
	 * @param nodeRef the node ref
	 */
	public void onUpdateNode(NodeRef nodeRef) {
		// In a real implementation, this would trigger rules for node updates
		// For mock, we can leave it empty or trigger rules if needed
	}

	@Override
	/**
	 * On create node.
	 *
	 * @param childAssocRef the child assoc ref
	 */
	public void onCreateNode(ChildAssociationRef childAssocRef) {
		// In a real implementation, this would trigger rules for node creation
		// For mock, we can leave it empty or trigger rules if needed
	}

	@Override
	/**
	 * On create child association.
	 *
	 * @param childAssocRef the child assoc ref
	 * @param isNewNode the is new node
	 */
	public void onCreateChildAssociation(ChildAssociationRef childAssocRef, boolean isNewNode) {
		// In a real implementation, this would trigger rules for child association creation
		// For mock, we can leave it empty or trigger rules if needed
	}

	@Override
	/**
	 * Execute rule.
	 *
	 * @param rule the rule
	 * @param actionedUponNodeRef the actioned upon node ref
	 * @param executedRules the executed rules
	 */
	public void executeRule(Rule rule, NodeRef actionedUponNodeRef, Set<ExecutedRuleData> executedRules) {
		if (rule == null || actionedUponNodeRef == null) {
			return;
		}

		// Check if rule is enabled
		if (!isRuleEnabled(rule)) {
			return;
		}

		// Check if rule type is enabled (simplified - rule type checking would require Rule interface details)
		// For mock, we skip this check to keep it simple

		// Execute actions in the rule
		if (actionService != null && rule.getAction() != null) {
			actionService.executeAction(rule.getAction(), actionedUponNodeRef);
		}
	}

	/**
	 * Checks if a rule is enabled.
	 *
	 * @param rule the rule to check
	 * @return true if the rule is enabled, false otherwise
	 */
	private boolean isRuleEnabled(Rule rule) {
		if (rule.getNodeRef() != null) {
			return !disabledRules.contains(rule.getNodeRef());
		}
		return true;
	}

	@Override
	/**
	 * Add rule pending execution.
	 *
	 * @param actionableNodeRef the actionable node ref
	 * @param actionedUponNodeRef the actioned upon node ref
	 * @param rule the rule
	 */
	public void addRulePendingExecution(NodeRef actionableNodeRef, NodeRef actionedUponNodeRef, Rule rule) {
		addRulePendingExecution(actionableNodeRef, actionedUponNodeRef, rule, false);
	}

	@Override
	public void addRulePendingExecution(NodeRef actionableNodeRef, NodeRef actionedUponNodeRef, Rule rule,
			boolean executeAtEnd) {
		if (actionedUponNodeRef == null || rule == null) {
			return;
		}

		List<PendingRule> pending = pendingRules.get(actionedUponNodeRef);
		if (pending == null) {
			pending = new ArrayList<>();
			pendingRules.put(actionedUponNodeRef, pending);
		}
		pending.add(new PendingRule(actionableNodeRef, actionedUponNodeRef, rule, executeAtEnd));
	}

	@Override
	/**
	 * Remove rule pending execution.
	 *
	 * @param actionedUponNodeRef the actioned upon node ref
	 */
	public void removeRulePendingExecution(NodeRef actionedUponNodeRef) {
		pendingRules.remove(actionedUponNodeRef);
	}

	@Override
	/**
	 * Execute pending rules.
	 *
	 */
	public void executePendingRules() {
		for (Map.Entry<NodeRef, List<PendingRule>> entry : pendingRules.entrySet()) {
			for (PendingRule pendingRule : entry.getValue()) {
				if (!pendingRule.executeAtEnd) {
					executeRule(pendingRule.rule, pendingRule.actionedUponNodeRef, null);
				}
			}
		}
		// Clear executed rules
		pendingRules.clear();
	}

	@Override
	/**
	 * Register rule type.
	 *
	 * @param ruleType the rule type
	 */
	public void registerRuleType(RuleType ruleType) {
		if (ruleType != null && ruleType.getName() != null) {
			ruleTypes.put(ruleType.getName(), ruleType);
		}
	}

	@Override
	/**
	 * Get saved rule folder assoc.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	public ChildAssociationRef getSavedRuleFolderAssoc(NodeRef nodeRef) {
		// Mock implementation - return null as we don't track associations
		return null;
	}

	@Override
	/**
	 * Get rule types.
	 *
	 * @return the result
	 */
	public List<RuleType> getRuleTypes() {
		return new ArrayList<>(ruleTypes.values());
	}

	@Override
	/**
	 * Get rule type.
	 *
	 * @param name the name
	 * @return the result
	 */
	public RuleType getRuleType(String name) {
		return ruleTypes.get(name);
	}

	@Override
	/**
	 * Enable rules.
	 *
	 */
	public void enableRules() {
		globallyEnabled = true;
	}

	@Override
	/**
	 * Disable rules.
	 *
	 */
	public void disableRules() {
		globallyEnabled = false;
	}

	@Override
	/**
	 * Is enabled.
	 *
	 * @return the result
	 */
	public boolean isEnabled() {
		return globallyEnabled;
	}

	@Override
	/**
	 * Rules enabled.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	public boolean rulesEnabled(NodeRef nodeRef) {
		if (!globallyEnabled) {
			return false;
		}
		return !disabledFolders.contains(nodeRef);
	}

	@Override
	/**
	 * Disable rules.
	 *
	 * @param nodeRef the node ref
	 */
	public void disableRules(NodeRef nodeRef) {
		if (nodeRef != null) {
			disabledFolders.add(nodeRef);
		}
	}

	@Override
	/**
	 * Enable rules.
	 *
	 * @param nodeRef the node ref
	 */
	public void enableRules(NodeRef nodeRef) {
		if (nodeRef != null) {
			disabledFolders.remove(nodeRef);
		}
	}

	@Override
	/**
	 * Disable rule.
	 *
	 * @param rule the rule
	 */
	public void disableRule(Rule rule) {
		if (rule != null && rule.getNodeRef() != null) {
			disabledRules.add(rule.getNodeRef());
		}
	}

	@Override
	/**
	 * Enable rule.
	 *
	 * @param rule the rule
	 */
	public void enableRule(Rule rule) {
		if (rule != null && rule.getNodeRef() != null) {
			disabledRules.remove(rule.getNodeRef());
		}
	}

	@Override
	/**
	 * Disable rule type.
	 *
	 * @param ruleType the rule type
	 */
	public void disableRuleType(String ruleType) {
		if (ruleType != null) {
			disabledRuleTypes.add(ruleType);
		}
	}

	@Override
	/**
	 * Enable rule type.
	 *
	 * @param ruleType the rule type
	 */
	public void enableRuleType(String ruleType) {
		if (ruleType != null) {
			disabledRuleTypes.remove(ruleType);
		}
	}

	@Override
	/**
	 * Is rule type enabled.
	 *
	 * @param ruleType the rule type
	 * @return the result
	 */
	public boolean isRuleTypeEnabled(String ruleType) {
		if (ruleType == null) {
			return true;
		}
		return !disabledRuleTypes.contains(ruleType);
	}

	@Override
	/**
	 * Has rules.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	public boolean hasRules(NodeRef nodeRef) {
		List<Rule> rules = rulesByFolder.get(nodeRef);
		return rules != null && !rules.isEmpty();
	}

	/**
	 * Checks if a folder has non-inherited rules.
	 *
	 * @param nodeRef the folder node reference
	 * @return true if the folder has non-inherited rules
	 */
	public boolean hasNonInheritedRules(NodeRef nodeRef) {
		// In a simplified mock, we consider all rules as non-inherited
		return hasRules(nodeRef);
	}

	@Override
	/**
	 * Get rules.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	public List<Rule> getRules(NodeRef nodeRef) {
		return getRules(nodeRef, false);
	}

	@Override
	/**
	 * Get rules.
	 *
	 * @param nodeRef the node ref
	 * @param includeInhertied the include inhertied
	 * @return the result
	 */
	public List<Rule> getRules(NodeRef nodeRef, boolean includeInhertied) {
		List<Rule> rules = rulesByFolder.get(nodeRef);
		if (rules == null) {
			return new ArrayList<>();
		}
		// Filter out disabled rules
		List<Rule> enabledRules = new ArrayList<>();
		for (Rule rule : rules) {
			if (isRuleEnabled(rule)) {
				enabledRules.add(rule);
			}
		}
		return enabledRules;
	}

	@Override
	/**
	 * Get rules.
	 *
	 * @param nodeRef the node ref
	 * @param includeInhertiedRuleType the include inhertied rule type
	 * @param ruleTypeName the rule type name
	 * @return the result
	 */
	public List<Rule> getRules(NodeRef nodeRef, boolean includeInhertiedRuleType, String ruleTypeName) {
		List<Rule> allRules = getRules(nodeRef, includeInhertiedRuleType);
		if (ruleTypeName == null) {
			return allRules;
		}
		// Simplified filtering - in real implementation would check rule.getRuleType()
		// For mock, return all rules as filtering by rule type would require Rule interface details
		// Note: Actual filtering by rule type would require access to Rule.getRuleType() method
		// For simplicity, return all rules - can be enhanced if Rule interface provides rule type access
		return allRules;
	}

	@Override
	/**
	 * Count rules.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	public int countRules(NodeRef nodeRef) {
		return getRules(nodeRef).size();
	}

	/**
	 * Gets the nodes supplying rule sets to a folder.
	 *
	 * @param nodeRef the folder node reference
	 * @return list of nodes supplying rule sets
	 */
	public List<NodeRef> getNodesSupplyingRuleSets(NodeRef nodeRef) {
		// Mock implementation - return empty list
		return new ArrayList<>();
	}

	/**
	 * Gets the folders inheriting a rule set.
	 *
	 * @param ruleSet the rule set node reference
	 * @param maxFoldersToReturn maximum number of folders to return
	 * @return list of folders inheriting the rule set
	 */
	public List<NodeRef> getFoldersInheritingRuleSet(NodeRef ruleSet, int maxFoldersToReturn) {
		List<NodeRef> folders = ruleSetToFolders.get(ruleSet);
		if (folders == null) {
			return new ArrayList<>();
		}
		if (maxFoldersToReturn > 0 && folders.size() > maxFoldersToReturn) {
			return new ArrayList<>(folders.subList(0, maxFoldersToReturn));
		}
		return new ArrayList<>(folders);
	}

	/**
	 * Gets the folders linking to a rule set.
	 *
	 * @param ruleSet the rule set node reference
	 * @param maxFoldersToReturn maximum number of folders to return
	 * @return list of folders linking to the rule set
	 */
	public List<NodeRef> getFoldersLinkingToRuleSet(NodeRef ruleSet, int maxFoldersToReturn) {
		// Similar to getFoldersInheritingRuleSet for mock
		return getFoldersInheritingRuleSet(ruleSet, maxFoldersToReturn);
	}

	@Override
	/**
	 * Get rule.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	public Rule getRule(NodeRef nodeRef) {
		return rulesByNodeRef.get(nodeRef);
	}

	/**
	 * Saves a rule to a folder.
	 *
	 * @param nodeRef the folder node reference
	 * @param rule the rule to save
	 */
	public void saveRule(NodeRef nodeRef, Rule rule) {
		if (nodeRef == null || rule == null) {
			return;
		}

		List<Rule> rules = rulesByFolder.get(nodeRef);
		if (rules == null) {
			rules = new ArrayList<>();
			rulesByFolder.put(nodeRef, rules);
		}

		// If rule has a nodeRef, check if it already exists
		if (rule.getNodeRef() != null) {
			Rule existingRule = rulesByNodeRef.get(rule.getNodeRef());
			if (existingRule != null) {
				// Update existing rule
				int index = rules.indexOf(existingRule);
				if (index >= 0) {
					rules.set(index, rule);
				}
			} else {
				// Add new rule
				rules.add(rule);
			}
			rulesByNodeRef.put(rule.getNodeRef(), rule);
		} else {
			// Add new rule without nodeRef
			rules.add(rule);
		}

		// Store ownership
		ruleOwnership.put(rule, nodeRef);
	}

	@Override
	/**
	 * Save rule.
	 *
	 * @param nodeRef the node ref
	 * @param rule the rule
	 * @param index the index
	 */
	public void saveRule(NodeRef nodeRef, Rule rule, int index) {
		if (nodeRef == null || rule == null) {
			return;
		}

		List<Rule> rules = rulesByFolder.get(nodeRef);
		if (rules == null) {
			rules = new ArrayList<>();
			rulesByFolder.put(nodeRef, rules);
		}

		// Ensure index is valid
		if (index < 0) {
			index = 0;
		}
		if (index > rules.size()) {
			index = rules.size();
		}

		// Remove existing rule if present
		if (rule.getNodeRef() != null) {
			Rule existingRule = rulesByNodeRef.get(rule.getNodeRef());
			if (existingRule != null) {
				rules.remove(existingRule);
			}
		}

		// Insert at specified index
		rules.add(index, rule);

		// Store in nodeRef map
		if (rule.getNodeRef() != null) {
			rulesByNodeRef.put(rule.getNodeRef(), rule);
		}

		// Store ownership
		ruleOwnership.put(rule, nodeRef);
	}

	@Override
	/**
	 * Set rule position.
	 *
	 * @param nodeRef the node ref
	 * @param ruleNodeRef the rule node ref
	 * @param index the index
	 */
	public void setRulePosition(NodeRef nodeRef, NodeRef ruleNodeRef, int index) {
		Rule rule = getRule(ruleNodeRef);
		if (rule != null) {
			setRulePosition(nodeRef, rule, index);
		}
	}

	@Override
	/**
	 * Set rule position.
	 *
	 * @param nodeRef the node ref
	 * @param rule the rule
	 * @param index the index
	 */
	public void setRulePosition(NodeRef nodeRef, Rule rule, int index) {
		List<Rule> rules = rulesByFolder.get(nodeRef);
		if (rules == null || !rules.contains(rule)) {
			return;
		}

		rules.remove(rule);
		if (index < 0) {
			index = 0;
		}
		if (index > rules.size()) {
			index = rules.size();
		}
		rules.add(index, rule);
	}

	@Override
	/**
	 * Remove rule.
	 *
	 * @param nodeRef the node ref
	 * @param rule the rule
	 */
	public void removeRule(NodeRef nodeRef, Rule rule) {
		if (nodeRef == null || rule == null) {
			return;
		}

		List<Rule> rules = rulesByFolder.get(nodeRef);
		if (rules != null) {
			rules.remove(rule);
		}

		if (rule.getNodeRef() != null) {
			rulesByNodeRef.remove(rule.getNodeRef());
			disabledRules.remove(rule.getNodeRef());
		}

		ruleOwnership.remove(rule);
	}

	@Override
	/**
	 * Remove all rules.
	 *
	 * @param nodeRef the node ref
	 */
	public void removeAllRules(NodeRef nodeRef) {
		if (nodeRef == null) {
			return;
		}

		List<Rule> rules = rulesByFolder.remove(nodeRef);
		if (rules != null) {
			for (Rule rule : rules) {
				if (rule.getNodeRef() != null) {
					rulesByNodeRef.remove(rule.getNodeRef());
					disabledRules.remove(rule.getNodeRef());
				}
				ruleOwnership.remove(rule);
			}
		}
	}

	@Override
	/**
	 * Get owning node ref.
	 *
	 * @param rule the rule
	 * @return the result
	 */
	public NodeRef getOwningNodeRef(Rule rule) {
		return ruleOwnership.get(rule);
	}

	@Override
	/**
	 * Get owning node ref.
	 *
	 * @param action the action
	 * @return the result
	 */
	public NodeRef getOwningNodeRef(Action action) {
		return actionOwnership.get(action);
	}

	/**
	 * Gets the owning node for a rule set.
	 *
	 * @param ruleSet the rule set node reference
	 * @return the owning node reference
	 */
	public NodeRef getOwningNodeRef(NodeRef ruleSet) {
		return ruleSetOwnership.get(ruleSet);
	}

	@Override
	/**
	 * Is linked to rule node.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	public boolean isLinkedToRuleNode(NodeRef nodeRef) {
		return folderToRuleSet.containsKey(nodeRef);
	}

	@Override
	/**
	 * Get linked to rule node.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	public NodeRef getLinkedToRuleNode(NodeRef nodeRef) {
		return folderToRuleSet.get(nodeRef);
	}

	/**
	 * Gets the folders linked from a rule node.
	 *
	 * @param nodeRef the rule node reference
	 * @return list of linked folder node references
	 */
	public List<NodeRef> getLinkedFromRuleNodes(NodeRef nodeRef) {
		List<NodeRef> folders = ruleSetToFolders.get(nodeRef);
		if (folders == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(folders);
	}

	/**
	 * Gets the rule set node for a folder.
	 *
	 * @param folderNodeRef the folder node reference
	 * @return the rule set node reference
	 */
	public NodeRef getRuleSetNode(NodeRef folderNodeRef) {
		return folderToRuleSet.get(folderNodeRef);
	}

	/**
	 * Checks if a rule set is associated with a folder.
	 *
	 * @param ruleSetNodeRef the rule set node reference
	 * @param folderNodeRef the folder node reference
	 * @return true if the rule set is associated with the folder
	 */
	public boolean isRuleSetAssociatedWithFolder(NodeRef ruleSetNodeRef, NodeRef folderNodeRef) {
		NodeRef associatedRuleSet = folderToRuleSet.get(folderNodeRef);
		return ruleSetNodeRef != null && ruleSetNodeRef.equals(associatedRuleSet);
	}

	/**
	 * Checks if a rule is associated with a rule set.
	 *
	 * @param ruleNodeRef the rule node reference
	 * @param ruleSetNodeRef the rule set node reference
	 * @return true if the rule is associated with the rule set
	 */
	public boolean isRuleAssociatedWithRuleSet(NodeRef ruleNodeRef, NodeRef ruleSetNodeRef) {
		NodeRef associatedRuleSet = ruleToRuleSet.get(ruleNodeRef);
		return ruleSetNodeRef != null && ruleSetNodeRef.equals(associatedRuleSet);
	}

	/**
	 * Checks if a rule set is shared by multiple folders.
	 *
	 * @param ruleSetNodeRef the rule set node reference
	 * @return true if the rule set is shared
	 */
	public boolean isRuleSetShared(NodeRef ruleSetNodeRef) {
		List<NodeRef> folders = ruleSetToFolders.get(ruleSetNodeRef);
		return folders != null && folders.size() > 1;
	}

}
