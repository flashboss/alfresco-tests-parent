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
 * Provides a simple action implementation with configurable name and parameters.
 *
 * @author lucastancapiano
 */
public class MockAction implements Action {

	/** The action name. */
	private String name;

	/** The action parameters. */
	private Map<String, Serializable> params;

	/**
	 * Constructs a new MockAction with the specified name.
	 *
	 * @param name the action name
	 */
	public MockAction(String name) {
		this.name = name;
	}

	/**
	 * Constructs a new MockAction with the specified name and parameters.
	 *
	 * @param name the action name
	 * @param params the action parameters
	 */
	public MockAction(String name, Map<String, Serializable> params) {
		this(name);
		this.params = params;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the action ID (same as name)
	 */
	@Override
	public String getId() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the parameter values map
	 */
	@Override
	public Map<String, Serializable> getParameterValues() {
		return params;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the parameter name
	 * @return the parameter value
	 */
	@Override
	public Serializable getParameterValue(String name) {
		return params.get(name);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param parameterValues the parameter values to set
	 */
	@Override
	public void setParameterValues(Map<String, Serializable> parameterValues) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the parameter name
	 * @param value the parameter value
	 */
	@Override
	public void setParameterValue(String name, Serializable value) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the node reference
	 */
	@Override
	public NodeRef getNodeRef() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the action definition name
	 */
	@Override
	public String getActionDefinitionName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the title
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param title the title to set
	 */
	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the description
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param description the description to set
	 */
	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the track status flag
	 */
	@Override
	public Boolean getTrackStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param trackStatus the track status flag to set
	 */
	@Override
	public void setTrackStatus(Boolean trackStatus) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if asynchronous execution, false otherwise
	 */
	@Override
	public boolean getExecuteAsychronously() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param executeAsynchronously the asynchronous flag to set
	 */
	@Override
	public void setExecuteAsynchronously(boolean executeAsynchronously) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the compensating action
	 */
	@Override
	public Action getCompensatingAction() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param action the compensating action to set
	 */
	@Override
	public void setCompensatingAction(Action action) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the created date
	 */
	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the creator
	 */
	@Override
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the modified date
	 */
	@Override
	public Date getModifiedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the modifier
	 */
	@Override
	public String getModifier() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if has action conditions, false otherwise
	 */
	@Override
	public boolean hasActionConditions() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param actionCondition the action condition to find
	 * @return the index of the action condition
	 */
	@Override
	public int indexOfActionCondition(ActionCondition actionCondition) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the list of action conditions
	 */
	@Override
	public List<ActionCondition> getActionConditions() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param index the index of the action condition
	 * @return the action condition at the specified index
	 */
	@Override
	public ActionCondition getActionCondition(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param actionCondition the action condition to add
	 */
	@Override
	public void addActionCondition(ActionCondition actionCondition) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param index the index at which to add
	 * @param actionCondition the action condition to add
	 */
	@Override
	public void addActionCondition(int index, ActionCondition actionCondition) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param index the index at which to set
	 * @param actionCondition the action condition to set
	 */
	@Override
	public void setActionCondition(int index, ActionCondition actionCondition) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param actionCondition the action condition to remove
	 */
	@Override
	public void removeActionCondition(ActionCondition actionCondition) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeAllActionConditions() {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param values the parameter values to add
	 */
	@Override
	public void addParameterValues(Map<String, Serializable> values) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the execution start date
	 */
	@Override
	public Date getExecutionStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the execution end date
	 */
	@Override
	public Date getExecutionEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the execution status
	 */
	@Override
	public ActionStatus getExecutionStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the execution failure message
	 */
	@Override
	public String getExecutionFailureMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
