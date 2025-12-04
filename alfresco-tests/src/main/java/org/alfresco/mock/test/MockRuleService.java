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
 * Mock implementation of the Alfresco RuleService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockRuleService implements RuleService, RuntimeRuleService,
		NodeServicePolicies.OnCreateChildAssociationPolicy, NodeServicePolicies.OnCreateNodePolicy,
		NodeServicePolicies.OnUpdateNodePolicy, NodeServicePolicies.OnAddAspectPolicy, Serializable {

	@Autowired
	private ActionService actionService;

	// Storage for rules by folder nodeRef
 /** The action service. */
	private Map<NodeRef, List<Rule>> rulesByFolder = new HashMap<>();

	// Storage for rule nodeRefs to rules
 /** The action service. */
	private Map<NodeRef, Rule> rulesByNodeRef = new HashMap<>();

	// Storage for rule types
 /** The action service. */
	private Map<String, RuleType> ruleTypes = new HashMap<>();

	// Global enabled state
	private boolean globallyEnabled = true;

	// Per-folder enabled state
	private Set<NodeRef> disabledFolders = new HashSet<>();

	// Per-rule enabled state
	private Set<NodeRef> disabledRules = new HashSet<>();

	// Per-rule-type enabled state
	private Set<String> disabledRuleTypes = new HashSet<>();

	// Pending rules for execution
	private Map<NodeRef, List<PendingRule>> pendingRules = new HashMap<>();

	// Rule ownership mapping
	private Map<Rule, NodeRef> ruleOwnership = new HashMap<>();
 /** The action ownership. */
	private Map<Action, NodeRef> actionOwnership = new HashMap<>();
 /** The rule set ownership. */
	private Map<NodeRef, NodeRef> ruleSetOwnership = new HashMap<>();

	// Rule set associations
 /** The rule set ownership. */
	private Map<NodeRef, NodeRef> folderToRuleSet = new HashMap<>();
 /** The rule set ownership. */
	private Map<NodeRef, List<NodeRef>> ruleSetToFolders = new HashMap<>();

	// Rule to rule set mapping
 /** The rule set ownership. */
	private Map<NodeRef, NodeRef> ruleToRuleSet = new HashMap<>();

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
	public void executeRule(Rule rule, NodeRef actionedUponNodeRef, Set<ExecutedRuleData> executedRules) {
  /**
  * Execute rule.
  *
  * @param rule the rule
  * @param actionedUponNodeRef the actioned upon node ref
  * @param executedRules the executed rules
  */
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
 * Is rule enabled.
 *
 * @param rule the rule
 * @return the boolean
 */
	private boolean isRuleEnabled(Rule rule) {
  /**
  * Is rule enabled.
  *
  * @param rule the rule
  * @return the boolean
  */
		if (rule.getNodeRef() != null) {
   /**
   * Is rule enabled.
   *
   * @param rule the rule
   * @return the boolean
   */
			return !disabledRules.contains(rule.getNodeRef());
		}
		return true;
	}

	@Override
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
 /** Execute pending rules. */
	public void executePendingRules() {
  /** Execute pending rules. */
		for (Map.Entry<NodeRef, List<PendingRule>> entry : pendingRules.entrySet()) {
   /** Execute pending rules. */
			for (PendingRule pendingRule : entry.getValue()) {
    /** Execute pending rules. */
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
  /**
  * Register rule type.
  *
  * @param ruleType the rule type
  */
		if (ruleType != null && ruleType.getName() != null) {
			ruleTypes.put(ruleType.getName(), ruleType);
		}
	}

	@Override
 /**
 * Get saved rule folder assoc.
 *
 * @param nodeRef the node ref
 * @return the child association ref
 */
	public ChildAssociationRef getSavedRuleFolderAssoc(NodeRef nodeRef) {
		// Mock implementation - return null as we don't track associations
		return null;
	}

	@Override
 /**
 * Get rule types.
 *
 * @return the list
 */
	public List<RuleType> getRuleTypes() {
  /**
  * Get rule types.
  *
  * @return the list
  */
		return new ArrayList<>(ruleTypes.values());
	}

	@Override
 /**
 * Get rule type.
 *
 * @param name the name
 * @return the rule type
 */
	public RuleType getRuleType(String name) {
  /**
  * Get rule type.
  *
  * @param name the name
  * @return the rule type
  */
		return ruleTypes.get(name);
	}

	@Override
 /** Enable rules. */
	public void enableRules() {
		globallyEnabled = true;
	}

	@Override
 /** Disable rules. */
	public void disableRules() {
		globallyEnabled = false;
	}

	@Override
 /**
 * Is enabled.
 *
 * @return the boolean
 */
	public boolean isEnabled() {
		return globallyEnabled;
	}

	@Override
 /**
 * Rules enabled.
 *
 * @param nodeRef the node ref
 * @return the boolean
 */
	public boolean rulesEnabled(NodeRef nodeRef) {
  /**
  * Rules enabled.
  *
  * @param nodeRef the node ref
  * @return the boolean
  */
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
  /**
  * Disable rules.
  *
  * @param nodeRef the node ref
  */
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
  /**
  * Disable rules.
  *
  * @param nodeRef the node ref
  */
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
  /**
  * Disable rule.
  *
  * @param rule the rule
  */
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
  /**
  * Disable rule.
  *
  * @param rule the rule
  */
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
  /**
  * Disable rule type.
  *
  * @param ruleType the rule type
  */
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
  /**
  * Disable rule type.
  *
  * @param ruleType the rule type
  */
		if (ruleType != null) {
			disabledRuleTypes.remove(ruleType);
		}
	}

	@Override
 /**
 * Is rule type enabled.
 *
 * @param ruleType the rule type
 * @return the boolean
 */
	public boolean isRuleTypeEnabled(String ruleType) {
  /**
  * Is rule type enabled.
  *
  * @param ruleType the rule type
  * @return the boolean
  */
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
 * @return the boolean
 */
	public boolean hasRules(NodeRef nodeRef) {
  /**
  * Has rules.
  *
  * @param nodeRef the node ref
  * @return the boolean
  */
		List<Rule> rules = rulesByFolder.get(nodeRef);
  /**
  * Has rules.
  *
  * @param nodeRef the node ref
  * @return the boolean
  */
		return rules != null && !rules.isEmpty();
	}

 /**
 * Has non inherited rules.
 *
 * @param nodeRef the node ref
 * @return the boolean
 */
	public boolean hasNonInheritedRules(NodeRef nodeRef) {
		// In a simplified mock, we consider all rules as non-inherited
  /**
  * Has non inherited rules.
  *
  * @param nodeRef the node ref
  * @return the boolean
  */
		return hasRules(nodeRef);
	}

	@Override
 /**
 * Get rules.
 *
 * @param nodeRef the node ref
 * @return the list
 */
	public List<Rule> getRules(NodeRef nodeRef) {
  /**
  * Get rules.
  *
  * @param nodeRef the node ref
  * @return the list
  */
		return getRules(nodeRef, false);
	}

	@Override
 /**
 * Get rules.
 *
 * @param nodeRef the node ref
 * @param includeInhertied the include inhertied
 * @return the list
 */
	public List<Rule> getRules(NodeRef nodeRef, boolean includeInhertied) {
  /**
  * Has rules.
  *
  * @param nodeRef the node ref
  * @return the boolean
  */
		List<Rule> rules = rulesByFolder.get(nodeRef);
  /**
  * Get rules.
  *
  * @param nodeRef the node ref
  * @param includeInhertied the include inhertied
  * @return the list
  */
		if (rules == null) {
   /**
   * Get nodes supplying rule sets.
   *
   * @param nodeRef the node ref
   * @return the list
   */
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
 * @return the int
 */
	public int countRules(NodeRef nodeRef) {
  /**
  * Count rules.
  *
  * @param nodeRef the node ref
  * @return the int
  */
		return getRules(nodeRef).size();
	}

 /**
 * Get nodes supplying rule sets.
 *
 * @param nodeRef the node ref
 * @return the list
 */
	public List<NodeRef> getNodesSupplyingRuleSets(NodeRef nodeRef) {
		// Mock implementation - return empty list
  /**
  * Get nodes supplying rule sets.
  *
  * @param nodeRef the node ref
  * @return the list
  */
		return new ArrayList<>();
	}

 /**
 * Get folders inheriting rule set.
 *
 * @param ruleSet the rule set
 * @param maxFoldersToReturn the max folders to return
 * @return the list
 */
	public List<NodeRef> getFoldersInheritingRuleSet(NodeRef ruleSet, int maxFoldersToReturn) {
  /**
  * Get folders inheriting rule set.
  *
  * @param ruleSet the rule set
  * @param maxFoldersToReturn the max folders to return
  * @return the list
  */
		List<NodeRef> folders = ruleSetToFolders.get(ruleSet);
  /**
  * Get folders inheriting rule set.
  *
  * @param ruleSet the rule set
  * @param maxFoldersToReturn the max folders to return
  * @return the list
  */
		if (folders == null) {
   /**
   * Get nodes supplying rule sets.
   *
   * @param nodeRef the node ref
   * @return the list
   */
			return new ArrayList<>();
		}
		if (maxFoldersToReturn > 0 && folders.size() > maxFoldersToReturn) {
			return folders.subList(0, maxFoldersToReturn);
		}
		return new ArrayList<>(folders);
	}

 /**
 * Get folders linking to rule set.
 *
 * @param ruleSet the rule set
 * @param maxFoldersToReturn the max folders to return
 * @return the list
 */
	public List<NodeRef> getFoldersLinkingToRuleSet(NodeRef ruleSet, int maxFoldersToReturn) {
		// Similar to getFoldersInheritingRuleSet for mock
  /**
  * Get folders linking to rule set.
  *
  * @param ruleSet the rule set
  * @param maxFoldersToReturn the max folders to return
  * @return the list
  */
		return getFoldersInheritingRuleSet(ruleSet, maxFoldersToReturn);
	}

	@Override
 /**
 * Get rule.
 *
 * @param nodeRef the node ref
 * @return the rule
 */
	public Rule getRule(NodeRef nodeRef) {
  /**
  * Get rule.
  *
  * @param nodeRef the node ref
  * @return the rule
  */
		return rulesByNodeRef.get(nodeRef);
	}

	@Override
 /**
 * Save rule.
 *
 * @param nodeRef the node ref
 * @param rule the rule
 */
	public void saveRule(NodeRef nodeRef, Rule rule) {
  /**
  * Save rule.
  *
  * @param nodeRef the node ref
  * @param rule the rule
  */
		if (nodeRef == null || rule == null) {
			return;
		}

  /**
  * Has rules.
  *
  * @param nodeRef the node ref
  * @return the boolean
  */
		List<Rule> rules = rulesByFolder.get(nodeRef);
  /**
  * Get rules.
  *
  * @param nodeRef the node ref
  * @param includeInhertied the include inhertied
  * @return the list
  */
		if (rules == null) {
			rules = new ArrayList<>();
			rulesByFolder.put(nodeRef, rules);
		}

		// If rule has a nodeRef, check if it already exists
  /**
  * Is rule enabled.
  *
  * @param rule the rule
  * @return the boolean
  */
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
  /**
  * Save rule.
  *
  * @param nodeRef the node ref
  * @param rule the rule
  */
		if (nodeRef == null || rule == null) {
			return;
		}

  /**
  * Has rules.
  *
  * @param nodeRef the node ref
  * @return the boolean
  */
		List<Rule> rules = rulesByFolder.get(nodeRef);
  /**
  * Get rules.
  *
  * @param nodeRef the node ref
  * @param includeInhertied the include inhertied
  * @return the list
  */
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
  /**
  * Is rule enabled.
  *
  * @param rule the rule
  * @return the boolean
  */
		if (rule.getNodeRef() != null) {
			Rule existingRule = rulesByNodeRef.get(rule.getNodeRef());
			if (existingRule != null) {
				rules.remove(existingRule);
			}
		}

		// Insert at specified index
		rules.add(index, rule);

		// Store in nodeRef map
  /**
  * Is rule enabled.
  *
  * @param rule the rule
  * @return the boolean
  */
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
  /**
  * Set rule position.
  *
  * @param nodeRef the node ref
  * @param ruleNodeRef the rule node ref
  * @param index the index
  */
		Rule rule = getRule(ruleNodeRef);
  /**
  * Set rule position.
  *
  * @param nodeRef the node ref
  * @param ruleNodeRef the rule node ref
  * @param index the index
  */
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
  /**
  * Has rules.
  *
  * @param nodeRef the node ref
  * @return the boolean
  */
		List<Rule> rules = rulesByFolder.get(nodeRef);
  /**
  * Set rule position.
  *
  * @param nodeRef the node ref
  * @param rule the rule
  * @param index the index
  */
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
  /**
  * Save rule.
  *
  * @param nodeRef the node ref
  * @param rule the rule
  */
		if (nodeRef == null || rule == null) {
			return;
		}

  /**
  * Has rules.
  *
  * @param nodeRef the node ref
  * @return the boolean
  */
		List<Rule> rules = rulesByFolder.get(nodeRef);
		if (rules != null) {
			rules.remove(rule);
		}

  /**
  * Is rule enabled.
  *
  * @param rule the rule
  * @return the boolean
  */
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
  /**
  * Remove all rules.
  *
  * @param nodeRef the node ref
  */
		if (nodeRef == null) {
			return;
		}

		List<Rule> rules = rulesByFolder.remove(nodeRef);
		if (rules != null) {
			for (Rule rule : rules) {
    /**
    * Is rule enabled.
    *
    * @param rule the rule
    * @return the boolean
    */
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
 * @return the node ref
 */
	public NodeRef getOwningNodeRef(Rule rule) {
  /**
  * Get owning node ref.
  *
  * @param rule the rule
  * @return the node ref
  */
		return ruleOwnership.get(rule);
	}

	@Override
 /**
 * Get owning node ref.
 *
 * @param action the action
 * @return the node ref
 */
	public NodeRef getOwningNodeRef(Action action) {
  /**
  * Get owning node ref.
  *
  * @param action the action
  * @return the node ref
  */
		return actionOwnership.get(action);
	}

 /**
 * Get owning node ref.
 *
 * @param ruleSet the rule set
 * @return the node ref
 */
	public NodeRef getOwningNodeRef(NodeRef ruleSet) {
  /**
  * Get owning node ref.
  *
  * @param ruleSet the rule set
  * @return the node ref
  */
		return ruleSetOwnership.get(ruleSet);
	}

 /**
 * Is linked to rule node.
 *
 * @param nodeRef the node ref
 * @return the boolean
 */
	public boolean isLinkedToRuleNode(NodeRef nodeRef) {
  /**
  * Is linked to rule node.
  *
  * @param nodeRef the node ref
  * @return the boolean
  */
		return folderToRuleSet.containsKey(nodeRef);
	}

 /**
 * Get linked to rule node.
 *
 * @param nodeRef the node ref
 * @return the node ref
 */
	public NodeRef getLinkedToRuleNode(NodeRef nodeRef) {
  /**
  * Get linked to rule node.
  *
  * @param nodeRef the node ref
  * @return the node ref
  */
		return folderToRuleSet.get(nodeRef);
	}

 /**
 * Get linked from rule nodes.
 *
 * @param nodeRef the node ref
 * @return the list
 */
	public List<NodeRef> getLinkedFromRuleNodes(NodeRef nodeRef) {
  /**
  * Get linked from rule nodes.
  *
  * @param nodeRef the node ref
  * @return the list
  */
		List<NodeRef> folders = ruleSetToFolders.get(nodeRef);
		return folders != null ? new ArrayList<>(folders) : new ArrayList<>();
	}

 /**
 * Get rule set node.
 *
 * @param folderNodeRef the folder node ref
 * @return the node ref
 */
	public NodeRef getRuleSetNode(NodeRef folderNodeRef) {
  /**
  * Get rule set node.
  *
  * @param folderNodeRef the folder node ref
  * @return the node ref
  */
		return folderToRuleSet.get(folderNodeRef);
	}

 /**
 * Is rule set associated with folder.
 *
 * @param ruleSetNodeRef the rule set node ref
 * @param folderNodeRef the folder node ref
 * @return the boolean
 */
	public boolean isRuleSetAssociatedWithFolder(NodeRef ruleSetNodeRef, NodeRef folderNodeRef) {
  /**
  * Is rule set associated with folder.
  *
  * @param ruleSetNodeRef the rule set node ref
  * @param folderNodeRef the folder node ref
  * @return the boolean
  */
		NodeRef associatedRuleSet = folderToRuleSet.get(folderNodeRef);
  /**
  * Is rule set associated with folder.
  *
  * @param ruleSetNodeRef the rule set node ref
  * @param folderNodeRef the folder node ref
  * @return the boolean
  */
		return ruleSetNodeRef != null && ruleSetNodeRef.equals(associatedRuleSet);
	}

 /**
 * Is rule associated with rule set.
 *
 * @param ruleNodeRef the rule node ref
 * @param ruleSetNodeRef the rule set node ref
 * @return the boolean
 */
	public boolean isRuleAssociatedWithRuleSet(NodeRef ruleNodeRef, NodeRef ruleSetNodeRef) {
  /**
  * Is rule associated with rule set.
  *
  * @param ruleNodeRef the rule node ref
  * @param ruleSetNodeRef the rule set node ref
  * @return the boolean
  */
		NodeRef associatedRuleSet = ruleToRuleSet.get(ruleNodeRef);
  /**
  * Is rule set associated with folder.
  *
  * @param ruleSetNodeRef the rule set node ref
  * @param folderNodeRef the folder node ref
  * @return the boolean
  */
		return ruleSetNodeRef != null && ruleSetNodeRef.equals(associatedRuleSet);
	}

 /**
 * Is rule set shared.
 *
 * @param ruleSetNodeRef the rule set node ref
 * @return the boolean
 */
	public boolean isRuleSetShared(NodeRef ruleSetNodeRef) {
  /**
  * Is rule set shared.
  *
  * @param ruleSetNodeRef the rule set node ref
  * @return the boolean
  */
		List<NodeRef> folders = ruleSetToFolders.get(ruleSetNodeRef);
  /**
  * Is rule set shared.
  *
  * @param ruleSetNodeRef the rule set node ref
  * @return the boolean
  */
		return folders != null && folders.size() > 1;
	}

}
