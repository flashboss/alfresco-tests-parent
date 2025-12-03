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

	@Override
	/**
	 * Get id.
	 *
	 * @return the result
	 */
	public String getId() {
		return name;
	}

	@Override
	/**
	 * Get parameter values.
	 *
	 */
	public Map<String, Serializable> getParameterValues() {
		return params;
	}

	@Override
	/**
	 * Get parameter value.
	 *
	 * @param name the name
	 * @return the result
	 */
	public Serializable getParameterValue(String name) {
		return params.get(name);
	}

	@Override
	/**
	 * Set parameter values.
	 *
	 * @param parameterValues the parameter values
	 */
	public void setParameterValues(Map<String, Serializable> parameterValues) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Set parameter value.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public void setParameterValue(String name, Serializable value) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get node ref.
	 *
	 * @return the result
	 */
	public NodeRef getNodeRef() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get action definition name.
	 *
	 * @return the result
	 */
	public String getActionDefinitionName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get title.
	 *
	 * @return the result
	 */
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Set title.
	 *
	 * @param title the title
	 */
	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get description.
	 *
	 * @return the result
	 */
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Set description.
	 *
	 * @param description the description
	 */
	public void setDescription(String description) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get track status.
	 *
	 * @return the result
	 */
	public Boolean getTrackStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Set track status.
	 *
	 * @param trackStatus the track status
	 */
	public void setTrackStatus(Boolean trackStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get execute asychronously.
	 *
	 * @return the result
	 */
	public boolean getExecuteAsychronously() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Set execute asynchronously.
	 *
	 * @param executeAsynchronously the execute asynchronously
	 */
	public void setExecuteAsynchronously(boolean executeAsynchronously) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get compensating action.
	 *
	 * @return the result
	 */
	public Action getCompensatingAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Set compensating action.
	 *
	 * @param action the action
	 */
	public void setCompensatingAction(Action action) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get created date.
	 *
	 * @return the result
	 */
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get creator.
	 *
	 * @return the result
	 */
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get modified date.
	 *
	 * @return the result
	 */
	public Date getModifiedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get modifier.
	 *
	 * @return the result
	 */
	public String getModifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Has action conditions.
	 *
	 * @return the result
	 */
	public boolean hasActionConditions() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Index of action condition.
	 *
	 * @param actionCondition the action condition
	 * @return the result
	 */
	public int indexOfActionCondition(ActionCondition actionCondition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	/**
	 * Get action conditions.
	 *
	 * @return the result
	 */
	public List<ActionCondition> getActionConditions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get action condition.
	 *
	 * @param index the index
	 * @return the result
	 */
	public ActionCondition getActionCondition(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Add action condition.
	 *
	 * @param actionCondition the action condition
	 */
	public void addActionCondition(ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Add action condition.
	 *
	 * @param index the index
	 * @param actionCondition the action condition
	 */
	public void addActionCondition(int index, ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Set action condition.
	 *
	 * @param index the index
	 * @param actionCondition the action condition
	 */
	public void setActionCondition(int index, ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Remove action condition.
	 *
	 * @param actionCondition the action condition
	 */
	public void removeActionCondition(ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Remove all action conditions.
	 *
	 */
	public void removeAllActionConditions() {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Add parameter values.
	 *
	 * @param values the values
	 */
	public void addParameterValues(Map<String, Serializable> values) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get execution start date.
	 *
	 * @return the result
	 */
	public Date getExecutionStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get execution end date.
	 *
	 * @return the result
	 */
	public Date getExecutionEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get execution status.
	 *
	 * @return the result
	 */
	public ActionStatus getExecutionStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get execution failure message.
	 *
	 * @return the result
	 */
	public String getExecutionFailureMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
