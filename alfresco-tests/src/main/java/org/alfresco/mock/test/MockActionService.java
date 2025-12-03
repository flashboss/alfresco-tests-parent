package org.alfresco.mock.test;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.repo.action.RuntimeActionService;
import org.alfresco.repo.action.evaluator.ActionConditionEvaluator;
import org.alfresco.repo.action.executer.ActionExecuter;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ActionCondition;
import org.alfresco.service.cmr.action.ActionConditionDefinition;
import org.alfresco.service.cmr.action.ActionDefinition;
import org.alfresco.service.cmr.action.ActionService;
import org.alfresco.service.cmr.action.CompositeAction;
import org.alfresco.service.cmr.action.CompositeActionCondition;
import org.alfresco.service.cmr.action.ParameterConstraint;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Mock implementation of ActionService for testing purposes.
 *
 * @author vige
 */
public class MockActionService implements ActionService, RuntimeActionService, Serializable {

	/** The app context. */
	@Autowired
	private ApplicationContext appContext;

	/**
	 * Get action definition.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public ActionDefinition getActionDefinition(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get action definitions.
	 *
	 * @return the result
	 */
	@Override
	public List<ActionDefinition> getActionDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get action definitions.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	@Override
	public List<ActionDefinition> getActionDefinitions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get action condition definition.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public ActionConditionDefinition getActionConditionDefinition(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get action condition definitions.
	 *
	 * @return the result
	 */
	@Override
	public List<ActionConditionDefinition> getActionConditionDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get parameter constraint.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public ParameterConstraint getParameterConstraint(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get parameter constraints.
	 *
	 * @return the result
	 */
	@Override
	public List<ParameterConstraint> getParameterConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create action.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public Action createAction(String name) {
		return new MockAction(name);
	}

	/**
	 * Create action.
	 *
	 * @param name the name
	 * @param params the params
	 * @return the result
	 */
	@Override
	public Action createAction(String name, Map<String, Serializable> params) {
		return new MockAction(name, params);
	}

	/**
	 * Create composite action.
	 *
	 * @return the result
	 */
	@Override
	public CompositeAction createCompositeAction() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create action condition.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public ActionCondition createActionCondition(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create action condition.
	 *
	 * @param name the name
	 * @param params the params
	 * @return the result
	 */
	@Override
	public ActionCondition createActionCondition(String name, Map<String, Serializable> params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create composite action condition.
	 *
	 * @return the result
	 */
	@Override
	public CompositeActionCondition createCompositeActionCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Execute action.
	 *
	 * @param action the action
	 * @param actionedUponNodeRef the actioned upon node ref
	 */
	@Override
	public void executeAction(Action action, NodeRef actionedUponNodeRef) {
		Object bean = appContext.getBean(action.getId());
		try {
			Method method = bean.getClass().getMethod("coreExecute", Action.class, NodeRef.class);
			method.invoke(bean, action, actionedUponNodeRef);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Execute action.
	 *
	 * @param action the action
	 * @param actionedUponNodeRef the actioned upon node ref
	 * @param checkConditions the check conditions
	 */
	@Override
	public void executeAction(Action action, NodeRef actionedUponNodeRef, boolean checkConditions) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeAction(Action action, NodeRef actionedUponNodeRef, boolean checkConditions,
			boolean executeAsynchronously) {
		executeAction(action, actionedUponNodeRef);
	}

	/**
	 * Evaluate action.
	 *
	 * @param action the action
	 * @param actionedUponNodeRef the actioned upon node ref
	 * @return the result
	 */
	@Override
	public boolean evaluateAction(Action action, NodeRef actionedUponNodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Evaluate action condition.
	 *
	 * @param condition the condition
	 * @param actionedUponNodeRef the actioned upon node ref
	 * @return the result
	 */
	@Override
	public boolean evaluateActionCondition(ActionCondition condition, NodeRef actionedUponNodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Save action.
	 *
	 * @param nodeRef the node ref
	 * @param action the action
	 */
	@Override
	public void saveAction(NodeRef nodeRef, Action action) {
		// TODO Auto-generated method stub

	}

	/**
	 * Get actions.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	@Override
	public List<Action> getActions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get action.
	 *
	 * @param nodeRef the node ref
	 * @param actionId the action id
	 * @return the result
	 */
	@Override
	public Action getAction(NodeRef nodeRef, String actionId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Remove action.
	 *
	 * @param nodeRef the node ref
	 * @param action the action
	 */
	@Override
	public void removeAction(NodeRef nodeRef, Action action) {
		// TODO Auto-generated method stub

	}

	/**
	 * Remove all actions.
	 *
	 * @param nodeRef the node ref
	 */
	@Override
	public void removeAllActions(NodeRef nodeRef) {
		// TODO Auto-generated method stub

	}

	/**
	 * Post commit.
	 *
	 */
	@Override
	public void postCommit() {
		// TODO Auto-generated method stub
	}

	/**
	 * Register action condition evaluator.
	 *
	 * @param actionConditionEvaluator the action condition evaluator
	 */
	@Override
	public void registerActionConditionEvaluator(ActionConditionEvaluator actionConditionEvaluator) {
		// TODO Auto-generated method stub
	}

	/**
	 * Register action executer.
	 *
	 * @param actionExecuter the action executer
	 */
	@Override
	public void registerActionExecuter(ActionExecuter actionExecuter) {
		// TODO Auto-generated method stub
	}

	/**
	 * Register parameter constraint.
	 *
	 * @param parameterConstraint the parameter constraint
	 */
	@Override
	public void registerParameterConstraint(ParameterConstraint parameterConstraint) {
		// TODO Auto-generated method stub
	}

	/**
	 * Create action.
	 *
	 * @param actionNodeRef the action node ref
	 * @return the result
	 */
	@Override
	public Action createAction(NodeRef actionNodeRef) {
		return null;
	}

	/**
	 * Create action node ref.
	 *
	 * @param action the action
	 * @param parentNodeRef the parent node ref
	 * @param assocTypeName the assoc type name
	 * @param assocName the assoc name
	 * @return the result
	 */
	@Override
	public NodeRef createActionNodeRef(Action action, NodeRef parentNodeRef, QName assocTypeName, QName assocName) {
		return null;
	}

	/**
	 * Save action impl.
	 *
	 * @param actionNodeRef the action node ref
	 * @param action the action
	 */
	@Override
	public void saveActionImpl(NodeRef actionNodeRef, Action action) {
		// TODO Auto-generated method stub
	}

	@Override
	public void executeActionImpl(Action action, NodeRef actionedUponNodeRef, boolean checkConditions,
			boolean executedAsynchronously, Set<String> actionChain) {
		// TODO Auto-generated method stub
	}

	/**
	 * Direct action execution.
	 *
	 * @param action the action
	 * @param actionedUponNodeRef the actioned upon node ref
	 */
	@Override
	public void directActionExecution(Action action, NodeRef actionedUponNodeRef) {
		// TODO Auto-generated method stub
	}

}
