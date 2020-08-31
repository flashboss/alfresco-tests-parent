package org.alfresco.mock.test;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ActionCondition;
import org.alfresco.service.cmr.action.ActionConditionDefinition;
import org.alfresco.service.cmr.action.ActionDefinition;
import org.alfresco.service.cmr.action.ActionService;
import org.alfresco.service.cmr.action.CompositeAction;
import org.alfresco.service.cmr.action.CompositeActionCondition;
import org.alfresco.service.cmr.action.ParameterConstraint;
import org.alfresco.service.cmr.repository.NodeRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class MockActionService implements ActionService, Serializable {

	@Autowired
	private ApplicationContext appContext;

	@Override
	public ActionDefinition getActionDefinition(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActionDefinition> getActionDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActionDefinition> getActionDefinitions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionConditionDefinition getActionConditionDefinition(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActionConditionDefinition> getActionConditionDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParameterConstraint getParameterConstraint(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParameterConstraint> getParameterConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action createAction(String name) {
		return new MockAction(name);
	}

	@Override
	public Action createAction(String name, Map<String, Serializable> params) {
		return new MockAction(name, params);
	}

	@Override
	public CompositeAction createCompositeAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionCondition createActionCondition(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionCondition createActionCondition(String name, Map<String, Serializable> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompositeActionCondition createCompositeActionCondition() {
		// TODO Auto-generated method stub
		return null;
	}

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

	@Override
	public void executeAction(Action action, NodeRef actionedUponNodeRef, boolean checkConditions) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeAction(Action action, NodeRef actionedUponNodeRef, boolean checkConditions,
			boolean executeAsynchronously) {
		executeAction(action, actionedUponNodeRef);
	}

	@Override
	public boolean evaluateAction(Action action, NodeRef actionedUponNodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean evaluateActionCondition(ActionCondition condition, NodeRef actionedUponNodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveAction(NodeRef nodeRef, Action action) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Action> getActions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action getAction(NodeRef nodeRef, String actionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAction(NodeRef nodeRef, Action action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAllActions(NodeRef nodeRef) {
		// TODO Auto-generated method stub

	}

}
