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
* Mock implementation of the MockAction class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockAction implements Action {

/**
* The name.
 */
	private String name;

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
* @return the result
 */
	@Override
	public String getId() {
		return name;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Map<String, Serializable> getParameterValues() {
		return params;
	}

/**
* {@inheritDoc}
* @param name the name
* @return the result
 */
	@Override
	public Serializable getParameterValue(String name) {
		return params.get(name);
	}

/**
* {@inheritDoc}
 */
	@Override
	public void setParameterValues(Map<String, Serializable> parameterValues) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param name the name
* @param value the value
 */
	@Override
	public void setParameterValue(String name, Serializable value) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public NodeRef getNodeRef() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getActionDefinitionName() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param title the title
 */
	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param description the description
 */
	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Boolean getTrackStatus() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param trackStatus the trackStatus
 */
	@Override
	public void setTrackStatus(Boolean trackStatus) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public boolean getExecuteAsychronously() {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param executeAsynchronously the executeAsynchronously
 */
	@Override
	public void setExecuteAsynchronously(boolean executeAsynchronously) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Action getCompensatingAction() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param action the action
 */
	@Override
	public void setCompensatingAction(Action action) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Date getModifiedDate() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getModifier() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public boolean hasActionConditions() {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param actionCondition the actionCondition
* @return the result
 */
	@Override
	public int indexOfActionCondition(ActionCondition actionCondition) {
		// TODO Auto-generated method stub
		return 0;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public List<ActionCondition> getActionConditions() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param index the index
* @return the result
 */
	@Override
	public ActionCondition getActionCondition(int index) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param actionCondition the actionCondition
 */
	@Override
	public void addActionCondition(ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param index the index
* @param actionCondition the actionCondition
 */
	@Override
	public void addActionCondition(int index, ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param index the index
* @param actionCondition the actionCondition
 */
	@Override
	public void setActionCondition(int index, ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param actionCondition the actionCondition
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
 */
	@Override
	public void addParameterValues(Map<String, Serializable> values) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Date getExecutionStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Date getExecutionEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ActionStatus getExecutionStatus() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getExecutionFailureMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
