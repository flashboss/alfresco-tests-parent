package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ActionCondition;
import org.alfresco.service.cmr.action.ActionStatus;
import org.alfresco.service.cmr.repository.NodeRef;

/**
 * Mock implementation of Action for testing purposes.
 *
 * @author vige
 */
public class MockAction implements Action {

  /** The name. */
  private String name;

  /** The params. */
  private Map<String, Serializable> params;

  /**
   * Constructs a new MockAction with a name.
   *
   * @param name the action name
   */
  public MockAction(String name) {
    this.name = name;
  }

  /**
   * Constructs a new MockAction with a name and parameters.
   *
   * @param name the action name
   * @param params the action parameters
   */
  public MockAction(String name, Map<String, Serializable> params) {
    this(name);
    this.params = params;
  }

  /**
   * Get id.
   *
   * @return the result
   */
  @Override
  public String getId() {
    return name;
  }

  /** Get parameter values. */
  @Override
  public Map<String, Serializable> getParameterValues() {
    return params;
  }

  /**
   * Get parameter value.
   *
   * @param name the name
   * @return the result
   */
  @Override
  public Serializable getParameterValue(String name) {
    return params.get(name);
  }

  /**
   * Set parameter values.
   *
   * @param parameterValues the parameter values
   */
  @Override
  public void setParameterValues(Map<String, Serializable> parameterValues) {
    // TODO Auto-generated method stub

  }

  /**
   * Set parameter value.
   *
   * @param name the name
   * @param value the value
   */
  @Override
  public void setParameterValue(String name, Serializable value) {
    // TODO Auto-generated method stub

  }

  /**
   * Get node ref.
   *
   * @return the result
   */
  @Override
  public NodeRef getNodeRef() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get action definition name.
   *
   * @return the result
   */
  @Override
  public String getActionDefinitionName() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get title.
   *
   * @return the result
   */
  @Override
  public String getTitle() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Set title.
   *
   * @param title the title
   */
  @Override
  public void setTitle(String title) {
    // TODO Auto-generated method stub

  }

  /**
   * Get description.
   *
   * @return the result
   */
  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Set description.
   *
   * @param description the description
   */
  @Override
  public void setDescription(String description) {
    // TODO Auto-generated method stub

  }

  /**
   * Get track status.
   *
   * @return the result
   */
  @Override
  public Boolean getTrackStatus() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Set track status.
   *
   * @param trackStatus the track status
   */
  @Override
  public void setTrackStatus(Boolean trackStatus) {
    // TODO Auto-generated method stub

  }

  /**
   * Get execute asychronously.
   *
   * @return the result
   */
  @Override
  public boolean getExecuteAsychronously() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Set execute asynchronously.
   *
   * @param executeAsynchronously the execute asynchronously
   */
  @Override
  public void setExecuteAsynchronously(boolean executeAsynchronously) {
    // TODO Auto-generated method stub

  }

  /**
   * Get compensating action.
   *
   * @return the result
   */
  @Override
  public Action getCompensatingAction() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Set compensating action.
   *
   * @param action the action
   */
  @Override
  public void setCompensatingAction(Action action) {
    // TODO Auto-generated method stub

  }

  /**
   * Get created date.
   *
   * @return the result
   */
  @Override
  public Date getCreatedDate() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get creator.
   *
   * @return the result
   */
  @Override
  public String getCreator() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get modified date.
   *
   * @return the result
   */
  @Override
  public Date getModifiedDate() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get modifier.
   *
   * @return the result
   */
  @Override
  public String getModifier() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Has action conditions.
   *
   * @return the result
   */
  @Override
  public boolean hasActionConditions() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Index of action condition.
   *
   * @param actionCondition the action condition
   * @return the result
   */
  @Override
  public int indexOfActionCondition(ActionCondition actionCondition) {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * Get action conditions.
   *
   * @return the result
   */
  @Override
  public List<ActionCondition> getActionConditions() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get action condition.
   *
   * @param index the index
   * @return the result
   */
  @Override
  public ActionCondition getActionCondition(int index) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Add action condition.
   *
   * @param actionCondition the action condition
   */
  @Override
  public void addActionCondition(ActionCondition actionCondition) {
    // TODO Auto-generated method stub

  }

  /**
   * Add action condition.
   *
   * @param index the index
   * @param actionCondition the action condition
   */
  @Override
  public void addActionCondition(int index, ActionCondition actionCondition) {
    // TODO Auto-generated method stub

  }

  /**
   * Set action condition.
   *
   * @param index the index
   * @param actionCondition the action condition
   */
  @Override
  public void setActionCondition(int index, ActionCondition actionCondition) {
    // TODO Auto-generated method stub

  }

  /**
   * Remove action condition.
   *
   * @param actionCondition the action condition
   */
  @Override
  public void removeActionCondition(ActionCondition actionCondition) {
    // TODO Auto-generated method stub

  }

  /** Remove all action conditions. */
  @Override
  public void removeAllActionConditions() {
    // TODO Auto-generated method stub

  }

  /**
   * Add parameter values.
   *
   * @param values the values
   */
  @Override
  public void addParameterValues(Map<String, Serializable> values) {
    // TODO Auto-generated method stub

  }

  /**
   * Get execution start date.
   *
   * @return the result
   */
  @Override
  public Date getExecutionStartDate() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get execution end date.
   *
   * @return the result
   */
  @Override
  public Date getExecutionEndDate() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get execution status.
   *
   * @return the result
   */
  @Override
  public ActionStatus getExecutionStatus() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get execution failure message.
   *
   * @return the result
   */
  @Override
  public String getExecutionFailureMessage() {
    // TODO Auto-generated method stub
    return null;
  }
}
