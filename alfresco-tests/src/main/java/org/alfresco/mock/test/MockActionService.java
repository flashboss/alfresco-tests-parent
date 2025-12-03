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
 * Mock implementation of ActionService and RuntimeActionService for testing purposes.
 * Provides basic action creation and execution capabilities using Spring's ApplicationContext
 * to locate and invoke action beans.
 * 
 * @author vige
 */
public class MockActionService implements ActionService, RuntimeActionService, Serializable {

	/** Spring application context for bean lookup. */
	@Autowired
	private ApplicationContext appContext;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionDefinition getActionDefinition(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ActionDefinition> getActionDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ActionDefinition> getActionDefinitions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionConditionDefinition getActionConditionDefinition(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ActionConditionDefinition> getActionConditionDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParameterConstraint getParameterConstraint(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ParameterConstraint> getParameterConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Action createAction(String name) {
		return new MockAction(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Action createAction(String name, Map<String, Serializable> params) {
		return new MockAction(name, params);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CompositeAction createCompositeAction() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionCondition createActionCondition(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionCondition createActionCondition(String name, Map<String, Serializable> params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CompositeActionCondition createCompositeActionCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public void executeAction(Action action, NodeRef actionedUponNodeRef, boolean checkConditions) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeAction(Action action, NodeRef actionedUponNodeRef, boolean checkConditions,
			boolean executeAsynchronously) {
		executeAction(action, actionedUponNodeRef);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean evaluateAction(Action action, NodeRef actionedUponNodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean evaluateActionCondition(ActionCondition condition, NodeRef actionedUponNodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveAction(NodeRef nodeRef, Action action) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Action> getActions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Action getAction(NodeRef nodeRef, String actionId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeAction(NodeRef nodeRef, Action action) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeAllActions(NodeRef nodeRef) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void postCommit() {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerActionConditionEvaluator(ActionConditionEvaluator actionConditionEvaluator) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerActionExecuter(ActionExecuter actionExecuter) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerParameterConstraint(ParameterConstraint parameterConstraint) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Action createAction(NodeRef actionNodeRef) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NodeRef createActionNodeRef(Action action, NodeRef parentNodeRef, QName assocTypeName, QName assocName) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveActionImpl(NodeRef actionNodeRef, Action action) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeActionImpl(Action action, NodeRef actionedUponNodeRef, boolean checkConditions,
			boolean executedAsynchronously, Set<String> actionChain) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void directActionExecution(Action action, NodeRef actionedUponNodeRef) {
		// TODO Auto-generated method stub
	}

}