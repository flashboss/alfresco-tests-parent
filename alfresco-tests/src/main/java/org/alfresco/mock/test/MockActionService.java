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
 * Mock implementation of the MockActionService class for testing purposes. This class provides a
 * mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockActionService implements ActionService, RuntimeActionService, Serializable {

  /** The app context. */
  @Autowired private ApplicationContext appContext;

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public List<ActionDefinition> getActionDefinitions() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public List<ActionDefinition> getActionDefinitions(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public List<ActionConditionDefinition> getActionConditionDefinitions() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public List<ParameterConstraint> getParameterConstraints() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param name the name
   * @return the result
   */
  @Override
  public Action createAction(String name) {
    return new MockAction(name);
  }

  /**
   * {@inheritDoc}
   *
   * @param name the name
   * @return the result
   */
  @Override
  public Action createAction(String name, Map<String, Serializable> params) {
    return new MockAction(name, params);
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public CompositeAction createCompositeAction() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
   *
   * @param name the name
   * @return the result
   */
  @Override
  public ActionCondition createActionCondition(String name, Map<String, Serializable> params) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public CompositeActionCondition createCompositeActionCondition() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param action the action
   * @param actionedUponNodeRef the actionedUponNodeRef
   */
  @Override
  public void executeAction(Action action, NodeRef actionedUponNodeRef) {
    Object bean = appContext.getBean(action.getId());
    try {
      Method method = bean.getClass().getMethod("coreExecute", Action.class, NodeRef.class);
      method.invoke(bean, action, actionedUponNodeRef);
    } catch (NoSuchMethodException
        | SecurityException
        | IllegalAccessException
        | IllegalArgumentException
        | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param action the action
   * @param actionedUponNodeRef the actionedUponNodeRef
   * @param checkConditions the checkConditions
   */
  @Override
  public void executeAction(Action action, NodeRef actionedUponNodeRef, boolean checkConditions) {
    // TODO Auto-generated method stub

  }

  /** {@inheritDoc} */
  @Override
  public void executeAction(
      Action action,
      NodeRef actionedUponNodeRef,
      boolean checkConditions,
      boolean executeAsynchronously) {
    executeAction(action, actionedUponNodeRef);
  }

  /**
   * {@inheritDoc}
   *
   * @param action the action
   * @param actionedUponNodeRef the actionedUponNodeRef
   * @return the result
   */
  @Override
  public boolean evaluateAction(Action action, NodeRef actionedUponNodeRef) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @param condition the condition
   * @param actionedUponNodeRef the actionedUponNodeRef
   * @return the result
   */
  @Override
  public boolean evaluateActionCondition(ActionCondition condition, NodeRef actionedUponNodeRef) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param action the action
   */
  @Override
  public void saveAction(NodeRef nodeRef, Action action) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public List<Action> getActions(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param actionId the actionId
   * @return the result
   */
  @Override
  public Action getAction(NodeRef nodeRef, String actionId) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param action the action
   */
  @Override
  public void removeAction(NodeRef nodeRef, Action action) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   */
  @Override
  public void removeAllActions(NodeRef nodeRef) {
    // TODO Auto-generated method stub

  }

  /** {@inheritDoc} */
  @Override
  public void postCommit() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'postCommit'");
  }

  /**
   * {@inheritDoc}
   *
   * @param actionConditionEvaluator the actionConditionEvaluator
   */
  @Override
  public void registerActionConditionEvaluator(ActionConditionEvaluator actionConditionEvaluator) {
    // TODO Auto-generated method stub
  }

  /**
   * {@inheritDoc}
   *
   * @param actionExecuter the actionExecuter
   */
  @Override
  public void registerActionExecuter(ActionExecuter actionExecuter) {
    // TODO Auto-generated method stub
  }

  /**
   * {@inheritDoc}
   *
   * @param parameterConstraint the parameterConstraint
   */
  @Override
  public void registerParameterConstraint(ParameterConstraint parameterConstraint) {
    // TODO Auto-generated method stub
  }

  /**
   * {@inheritDoc}
   *
   * @param actionNodeRef the actionNodeRef
   * @return the result
   */
  @Override
  public Action createAction(NodeRef actionNodeRef) {
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param action the action
   * @param parentNodeRef the parentNodeRef
   * @param assocTypeName the assocTypeName
   * @param assocName the assocName
   * @return the result
   */
  @Override
  public NodeRef createActionNodeRef(
      Action action, NodeRef parentNodeRef, QName assocTypeName, QName assocName) {
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param actionNodeRef the actionNodeRef
   * @param action the action
   */
  @Override
  public void saveActionImpl(NodeRef actionNodeRef, Action action) {
    // TODO Auto-generated method stub
  }

  /** {@inheritDoc} */
  @Override
  public void executeActionImpl(
      Action action,
      NodeRef actionedUponNodeRef,
      boolean checkConditions,
      boolean executedAsynchronously,
      Set<String> actionChain) {
    // TODO Auto-generated method stub
  }

  /**
   * {@inheritDoc}
   *
   * @param action the action
   * @param actionedUponNodeRef the actionedUponNodeRef
   */
  @Override
  public void directActionExecution(Action action, NodeRef actionedUponNodeRef) {
    // TODO Auto-generated method stub
  }

  /**
   * {@inheritDoc}
   *
   * @param action the action
   * @param logger the logger
   * @param t the t
   * @param message the message
   * @return the result
   */
  @Override
  public boolean onLogException(Action action, Log logger, Throwable t, String message) {
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @param action the action
   */
  @Override
  public void verifyActionAccessRestrictions(Action action) {
    // TODO Auto-generated method stub
  }
}
