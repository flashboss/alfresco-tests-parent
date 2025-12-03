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
 * Provides a simple action with name and parameter values support.
 * 
 * @author vige
 */
public class MockAction implements Action {

	/** The action name. */
	private String name;

	/** The action parameter values. */
	private Map<String, Serializable> params;

	/**
	 * Creates a new MockAction with the specified name.
	 * 
	 * @param name the action name
	 */
	public MockAction(String name) {
		this.name = name;
	}

	/**
	 * Creates a new MockAction with the specified name and parameters.
	 * 
	 * @param name the action name
	 * @param params the action parameter values
	 */
	public MockAction(String name, Map<String, Serializable> params) {
		this(name);
		this.params = params;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Serializable> getParameterValues() {
		return params;
	}

	/**
	 * {@inheritDoc}
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
	 */
	@Override
	public void setParameterValue(String name, Serializable value) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NodeRef getNodeRef() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getActionDefinitionName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getTrackStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTrackStatus(Boolean trackStatus) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean getExecuteAsychronously() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setExecuteAsynchronously(boolean executeAsynchronously) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Action getCompensatingAction() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCompensatingAction(Action action) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getModifiedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getModifier() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasActionConditions() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int indexOfActionCondition(ActionCondition actionCondition) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ActionCondition> getActionConditions() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionCondition getActionCondition(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addActionCondition(ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addActionCondition(int index, ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setActionCondition(int index, ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
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
	 */
	@Override
	public Date getExecutionStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getExecutionEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ActionStatus getExecutionStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getExecutionFailureMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
