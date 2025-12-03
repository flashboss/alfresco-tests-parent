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
 * Mock implementation of the RuleService for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 *
 * @author lucastancapiano
 */
public class MockRuleService implements RuleService, RuntimeRuleService,
		NodeServicePolicies.OnCreateChildAssociationPolicy, NodeServicePolicies.OnCreateNodePolicy,
		NodeServicePolicies.OnUpdateNodePolicy, NodeServicePolicies.OnAddAspectPolicy, Serializable {

	/** The action service. */
	@Autowired
	private ActionService actionService;

	/** Storage for rules by folder nodeRef. */
	private Map<NodeRef, List<Rule>> rulesByFolder = new HashMap<>();

	/** Storage for rule nodeRefs to rules. */
	private Map<NodeRef, Rule> rulesByNodeRef = new HashMap<>();

	/** Storage for rule types. */
	private Map<String, RuleType> ruleTypes = new HashMap<>();

	/** Global enabled state. */
	private boolean globallyEnabled = true;

	/** Per-folder enabled state. */
	private Set<NodeRef> disabledFolders = new HashSet<>();

	/** Per-rule enabled state. */
	private Set<NodeRef> disabledRules = new HashSet<>();

	/** Per-rule-type enabled state. */
	private Set<String> disabledRuleTypes = new HashSet<>();

	/** Pending rules for execution. */
	private Map<NodeRef, List<PendingRule>> pendingRules = new HashMap<>();

	/** Rule ownership mapping. */
	private Map<Rule, NodeRef> ruleOwnership = new HashMap<>();

	/** Action ownership mapping. */
	private Map<Action, NodeRef> actionOwnership = new HashMap<>();

	/** Rule set ownership mapping. */
	private Map<NodeRef, NodeRef> ruleSetOwnership = new HashMap<>();

	/** Folder to rule set mapping. */
	private Map<NodeRef, NodeRef> folderToRuleSet = new HashMap<>();

	/** Rule set to folders mapping. */
	private Map<NodeRef, List<NodeRef>> ruleSetToFolders = new HashMap<>();

	/** Rule to rule set mapping. */
	private Map<NodeRef, NodeRef> ruleToRuleSet = new HashMap<>();

	/**
	 * Internal class representing a pending rule execution.
	 */
	private static class PendingRule {
		@SuppressWarnings("unused")
		NodeRef actionableNodeRef;
		NodeRef actionedUponNodeRef;
		Rule rule;
		boolean executeAtEnd;

		PendingRule(NodeRef actionableNodeRef, NodeRef actionedUponNodeRef, Rule rule, boolean executeAtEnd) {
			this.actionableNodeRef = actionableNodeRef;
			this.actionedUponNodeRef = actionedUponNodeRef;
			this.rule = rule;
			this.executeAtEnd = executeAtEnd;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onAddAspect(NodeRef nodeRef, QName aspectTypeQName) {
		// In a real implementation, this would trigger rules for aspect addition
		// For mock, we can leave it empty or trigger rules if needed
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUpdateNode(NodeRef nodeRef) {
		// In a real implementation, this would trigger rules for node updates
		// For mock, we can leave it empty or trigger rules if needed
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCreateNode(ChildAssociationRef childAssocRef) {
		// In a real implementation, this would trigger rules for node creation
		// For mock, we can leave it empty or trigger rules if needed
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCreateChildAssociation(ChildAssociationRef childAssocRef, boolean isNewNode) {
		// In a real implementation, this would trigger rules for child association creation
		// For mock, we can leave it empty or trigger rules if needed
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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
	 * Checks if the specified rule is enabled.
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addRulePendingExecution(NodeRef actionableNodeRef, NodeRef actionedUponNodeRef, Rule rule) {
		addRulePendingExecution(actionableNodeRef, actionedUponNodeRef, rule, false);
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeRulePendingExecution(NodeRef actionedUponNodeRef) {
		pendingRules.remove(actionedUponNodeRef);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerRuleType(RuleType ruleType) {
		if (ruleType != null && ruleType.getName() != null) {
			ruleTypes.put(ruleType.getName(), ruleType);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ChildAssociationRef getSavedRuleFolderAssoc(NodeRef nodeRef) {
		// Mock implementation - return null as we don't track associations
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RuleType> getRuleTypes() {
		return new ArrayList<>(ruleTypes.values());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RuleType getRuleType(String name) {
		return ruleTypes.get(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enableRules() {
		globallyEnabled = true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disableRules() {
		globallyEnabled = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		return globallyEnabled;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean rulesEnabled(NodeRef nodeRef) {
		if (!globallyEnabled) {
			return false;
		}
		return !disabledFolders.contains(nodeRef);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disableRules(NodeRef nodeRef) {
		if (nodeRef != null) {
			disabledFolders.add(nodeRef);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enableRules(NodeRef nodeRef) {
		if (nodeRef != null) {
			disabledFolders.remove(nodeRef);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disableRule(Rule rule) {
		if (rule != null && rule.getNodeRef() != null) {
			disabledRules.add(rule.getNodeRef());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enableRule(Rule rule) {
		if (rule != null && rule.getNodeRef() != null) {
			disabledRules.remove(rule.getNodeRef());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disableRuleType(String ruleType) {
		if (ruleType != null) {
			disabledRuleTypes.add(ruleType);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void enableRuleType(String ruleType) {
		if (ruleType != null) {
			disabledRuleTypes.remove(ruleType);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRuleTypeEnabled(String ruleType) {
		if (ruleType == null) {
			return true;
		}
		return !disabledRuleTypes.contains(ruleType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasRules(NodeRef nodeRef) {
		List<Rule> rules = rulesByFolder.get(nodeRef);
		return rules != null && !rules.isEmpty();
	}

	/**
	 * Checks if the node has non-inherited rules.
	 *
	 * @param nodeRef the node reference
	 * @return true if the node has non-inherited rules
	 */
	public boolean hasNonInheritedRules(NodeRef nodeRef) {
		// In a simplified mock, we consider all rules as non-inherited
		return hasRules(nodeRef);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Rule> getRules(NodeRef nodeRef) {
		return getRules(nodeRef, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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

	/**
	 * {@inheritDoc}
	 */
	@Override
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int countRules(NodeRef nodeRef) {
		return getRules(nodeRef).size();
	}

	/**
	 * Gets the nodes supplying rule sets for the specified node.
	 *
	 * @param nodeRef the node reference
	 * @return the list of nodes supplying rule sets
	 */
	public List<NodeRef> getNodesSupplyingRuleSets(NodeRef nodeRef) {
		// Mock implementation - return empty list
		return new ArrayList<>();
	}

	/**
	 * Gets the folders inheriting the specified rule set.
	 *
	 * @param ruleSet the rule set node reference
	 * @param maxFoldersToReturn the maximum number of folders to return
	 * @return the list of folders inheriting the rule set
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
	 * Gets the folders linking to the specified rule set.
	 *
	 * @param ruleSet the rule set node reference
	 * @param maxFoldersToReturn the maximum number of folders to return
	 * @return the list of folders linking to the rule set
	 */
	public List<NodeRef> getFoldersLinkingToRuleSet(NodeRef ruleSet, int maxFoldersToReturn) {
		// Similar to getFoldersInheritingRuleSet for mock
		return getFoldersInheritingRuleSet(ruleSet, maxFoldersToReturn);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rule getRule(NodeRef nodeRef) {
		return rulesByNodeRef.get(nodeRef);
	}

	/**
	 * Saves a rule to the specified folder.
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

	/**
	 * {@inheritDoc}
	 */
	@Override
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRulePosition(NodeRef nodeRef, NodeRef ruleNodeRef, int index) {
		Rule rule = getRule(ruleNodeRef);
		if (rule != null) {
			setRulePosition(nodeRef, rule, index);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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

	/**
	 * {@inheritDoc}
	 */
	@Override
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

	/**
	 * {@inheritDoc}
	 */
	@Override
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NodeRef getOwningNodeRef(Rule rule) {
		return ruleOwnership.get(rule);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NodeRef getOwningNodeRef(Action action) {
		return actionOwnership.get(action);
	}

	/**
	 * Gets the owning node reference for the specified rule set.
	 *
	 * @param ruleSet the rule set node reference
	 * @return the owning node reference
	 */
	public NodeRef getOwningNodeRef(NodeRef ruleSet) {
		return ruleSetOwnership.get(ruleSet);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isLinkedToRuleNode(NodeRef nodeRef) {
		return folderToRuleSet.containsKey(nodeRef);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NodeRef getLinkedToRuleNode(NodeRef nodeRef) {
		return folderToRuleSet.get(nodeRef);
	}

	/**
	 * Gets the folders linked from the specified rule node.
	 *
	 * @param nodeRef the node reference
	 * @return the list of linked folders
	 */
	public List<NodeRef> getLinkedFromRuleNodes(NodeRef nodeRef) {
		List<NodeRef> folders = ruleSetToFolders.get(nodeRef);
		if (folders == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(folders);
	}

	/**
	 * Gets the rule set node for the specified folder.
	 *
	 * @param folderNodeRef the folder node reference
	 * @return the rule set node reference
	 */
	public NodeRef getRuleSetNode(NodeRef folderNodeRef) {
		return folderToRuleSet.get(folderNodeRef);
	}

	/**
	 * Checks if the rule set is associated with the folder.
	 *
	 * @param ruleSetNodeRef the rule set node reference
	 * @param folderNodeRef the folder node reference
	 * @return true if associated, false otherwise
	 */
	public boolean isRuleSetAssociatedWithFolder(NodeRef ruleSetNodeRef, NodeRef folderNodeRef) {
		NodeRef associatedRuleSet = folderToRuleSet.get(folderNodeRef);
		return ruleSetNodeRef != null && ruleSetNodeRef.equals(associatedRuleSet);
	}

	/**
	 * Checks if the rule is associated with the rule set.
	 *
	 * @param ruleNodeRef the rule node reference
	 * @param ruleSetNodeRef the rule set node reference
	 * @return true if associated, false otherwise
	 */
	public boolean isRuleAssociatedWithRuleSet(NodeRef ruleNodeRef, NodeRef ruleSetNodeRef) {
		NodeRef associatedRuleSet = ruleToRuleSet.get(ruleNodeRef);
		return ruleSetNodeRef != null && ruleSetNodeRef.equals(associatedRuleSet);
	}

	/**
	 * Checks if the rule set is shared among multiple folders.
	 *
	 * @param ruleSetNodeRef the rule set node reference
	 * @return true if the rule set is shared, false otherwise
	 */
	public boolean isRuleSetShared(NodeRef ruleSetNodeRef) {
		List<NodeRef> folders = ruleSetToFolders.get(ruleSetNodeRef);
		return folders != null && folders.size() > 1;
	}

}
