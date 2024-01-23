package org.alfresco.mock.test;

import java.util.List;
import java.util.Set;

import org.alfresco.repo.node.NodeServicePolicies;
import org.alfresco.repo.rule.RuleServiceImpl.ExecutedRuleData;
import org.alfresco.repo.rule.RuntimeRuleService;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.rule.Rule;
import org.alfresco.service.cmr.rule.RuleService;
import org.alfresco.service.cmr.rule.RuleType;
import org.alfresco.service.namespace.QName;

public class MockRuleService implements RuleService, RuntimeRuleService,
		NodeServicePolicies.OnCreateChildAssociationPolicy, NodeServicePolicies.OnCreateNodePolicy,
		NodeServicePolicies.OnUpdateNodePolicy, NodeServicePolicies.OnAddAspectPolicy {

	@Override
	public void onAddAspect(NodeRef nodeRef, QName aspectTypeQName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdateNode(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCreateNode(ChildAssociationRef childAssocRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCreateChildAssociation(ChildAssociationRef childAssocRef, boolean isNewNode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeRule(Rule rule, NodeRef actionedUponNodeRef, Set<ExecutedRuleData> executedRules) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRulePendingExecution(NodeRef actionableNodeRef, NodeRef actionedUponNodeRef, Rule rule) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRulePendingExecution(NodeRef actionableNodeRef, NodeRef actionedUponNodeRef, Rule rule,
			boolean executeAtEnd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRulePendingExecution(NodeRef actionedUponNodeRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executePendingRules() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerRuleType(RuleType ruleType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ChildAssociationRef getSavedRuleFolderAssoc(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RuleType> getRuleTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuleType getRuleType(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enableRules() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableRules() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rulesEnabled(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void disableRules(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableRules(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableRule(Rule rule) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableRule(Rule rule) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableRuleType(String ruleType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableRuleType(String ruleType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRuleTypeEnabled(String ruleType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRules(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int countRules(NodeRef arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<NodeRef> getLinkedFromRuleNodes(NodeRef arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getLinkedToRuleNode(NodeRef arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getOwningNodeRef(Rule arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getOwningNodeRef(Action arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rule getRule(NodeRef arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rule> getRules(NodeRef arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rule> getRules(NodeRef arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rule> getRules(NodeRef arg0, boolean arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLinkedToRuleNode(NodeRef arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeAllRules(NodeRef arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRule(NodeRef arg0, Rule arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveRule(NodeRef arg0, Rule arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveRule(NodeRef arg0, Rule arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRulePosition(NodeRef arg0, NodeRef arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRulePosition(NodeRef arg0, Rule arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
