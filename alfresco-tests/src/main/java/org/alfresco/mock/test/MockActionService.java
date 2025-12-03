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
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * Mock implementation of the Alfresco ActionService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockActionService implements ActionService, RuntimeActionService, Serializable {

	@Autowired
	/** The app context. */
	private ApplicationContext appContext;

	@Override
	/**
	 * Get action definition.
	 *
	 * @param name the name
	 * @return the action definition
	 */
	public ActionDefinition getActionDefinition(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get action definitions.
	 *
	 * @return the list
	 */
	public List<ActionDefinition> getActionDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get action definitions.
	 *
	 * @param nodeRef the node ref
	 * @return the list
	 */
	public List<ActionDefinition> getActionDefinitions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get action condition definition.
	 *
	 * @param name the name
	 * @return the action condition definition
	 */
	public ActionConditionDefinition getActionConditionDefinition(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get action condition definitions.
	 *
	 * @return the list
	 */
	public List<ActionConditionDefinition> getActionConditionDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get parameter constraint.
	 *
	 * @param name the name
	 * @return the parameter constraint
	 */
	public ParameterConstraint getParameterConstraint(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get parameter constraints.
	 *
	 * @return the list
	 */
	public List<ParameterConstraint> getParameterConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Create action.
	 *
	 * @param name the name
	 * @return the action
	 */
	public Action createAction(String name) {
		return new MockAction(name);
	}

	@Override
	/**
	 * Create action.
	 *
	 * @param name the name
	 * @param params the params
	 * @return the action
	 */
	public Action createAction(String name, Map<String, Serializable> params) {
		return new MockAction(name, params);
	}

	@Override
	/**
	 * Create composite action.
	 *
	 * @return the composite action
	 */
	public CompositeAction createCompositeAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Create action condition.
	 *
	 * @param name the name
	 * @return the action condition
	 */
	public ActionCondition createActionCondition(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Create action condition.
	 *
	 * @param name the name
	 * @param params the params
	 * @return the action condition
	 */
	public ActionCondition createActionCondition(String name, Map<String, Serializable> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Create composite action condition.
	 *
	 * @return the composite action condition
	 */
	public CompositeActionCondition createCompositeActionCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Execute action.
	 *
	 * @param action the action
	 * @param actionedUponNodeRef the actioned upon node ref
	 */
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

	@Override
	/**
	 * Execute action.
	 *
	 * @param action the action
	 * @param actionedUponNodeRef the actioned upon node ref
	 * @param checkConditions the check conditions
	 */
	public void executeAction(Action action, NodeRef actionedUponNodeRef, boolean checkConditions) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeAction(Action action, NodeRef actionedUponNodeRef, boolean checkConditions,
			boolean executeAsynchronously) {
		executeAction(action, actionedUponNodeRef);
	}

	@Override
	/**
	 * Evaluate action.
	 *
	 * @param action the action
	 * @param actionedUponNodeRef the actioned upon node ref
	 * @return the boolean
	 */
	public boolean evaluateAction(Action action, NodeRef actionedUponNodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Evaluate action condition.
	 *
	 * @param condition the condition
	 * @param actionedUponNodeRef the actioned upon node ref
	 * @return the boolean
	 */
	public boolean evaluateActionCondition(ActionCondition condition, NodeRef actionedUponNodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Save action.
	 *
	 * @param nodeRef the node ref
	 * @param action the action
	 */
	public void saveAction(NodeRef nodeRef, Action action) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get actions.
	 *
	 * @param nodeRef the node ref
	 * @return the list
	 */
	public List<Action> getActions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get action.
	 *
	 * @param nodeRef the node ref
	 * @param actionId the action id
	 * @return the action
	 */
	public Action getAction(NodeRef nodeRef, String actionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Remove action.
	 *
	 * @param nodeRef the node ref
	 * @param action the action
	 */
	public void removeAction(NodeRef nodeRef, Action action) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Remove all actions.
	 *
	 * @param nodeRef the node ref
	 */
	public void removeAllActions(NodeRef nodeRef) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Post commit.
	 *
	 */
	public void postCommit() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'postCommit'");
	}

	@Override
	/**
	 * Register action condition evaluator.
	 *
	 * @param actionConditionEvaluator the action condition evaluator
	 */
	public void registerActionConditionEvaluator(ActionConditionEvaluator actionConditionEvaluator) {
		// TODO Auto-generated method stub
	}

	@Override
	/**
	 * Register action executer.
	 *
	 * @param actionExecuter the action executer
	 */
	public void registerActionExecuter(ActionExecuter actionExecuter) {
		// TODO Auto-generated method stub
	}

	@Override
	/**
	 * Register parameter constraint.
	 *
	 * @param parameterConstraint the parameter constraint
	 */
	public void registerParameterConstraint(ParameterConstraint parameterConstraint) {
		// TODO Auto-generated method stub
	}

	@Override
	/**
	 * Create action.
	 *
	 * @param actionNodeRef the action node ref
	 * @return the action
	 */
	public Action createAction(NodeRef actionNodeRef) {
		return null;
	}

	@Override
	/**
	 * Create action node ref.
	 *
	 * @param action the action
	 * @param parentNodeRef the parent node ref
	 * @param assocTypeName the assoc type name
	 * @param assocName the assoc name
	 * @return the node ref
	 */
	public NodeRef createActionNodeRef(Action action, NodeRef parentNodeRef, QName assocTypeName, QName assocName) {
		return null;
	}

	@Override
	/**
	 * Save action impl.
	 *
	 * @param actionNodeRef the action node ref
	 * @param action the action
	 */
	public void saveActionImpl(NodeRef actionNodeRef, Action action) {
		// TODO Auto-generated method stub
	}

	@Override
	public void executeActionImpl(Action action, NodeRef actionedUponNodeRef, boolean checkConditions,
			boolean executedAsynchronously, Set<String> actionChain) {
		// TODO Auto-generated method stub
	}

	@Override
	/**
	 * Direct action execution.
	 *
	 * @param action the action
	 * @param actionedUponNodeRef the actioned upon node ref
	 */
	public void directActionExecution(Action action, NodeRef actionedUponNodeRef) {
		// TODO Auto-generated method stub
	}

	@Override
	/**
	 * On log exception.
	 *
	 * @param action the action
	 * @param logger the logger
	 * @param t the t
	 * @param message the message
	 * @return the boolean
	 */
	public boolean onLogException(Action action, Log logger, Throwable t, String message) {
		return false;
	}

}
