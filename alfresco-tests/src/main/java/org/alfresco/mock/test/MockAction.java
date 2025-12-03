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

	private String name;

	private Map<String, Serializable> params;

	public MockAction(String name) {
		this.name = name;
	}

	public MockAction(String name, Map<String, Serializable> params) {
		this(name);
		this.params = params;
	}

	@Override
	public String getId() {
		return name;
	}

	@Override
	public Map<String, Serializable> getParameterValues() {
		return params;
	}

	@Override
	public Serializable getParameterValue(String name) {
		return params.get(name);
	}

	@Override
	public void setParameterValues(Map<String, Serializable> parameterValues) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setParameterValue(String name, Serializable value) {
		// TODO Auto-generated method stub

	}

	@Override
	public NodeRef getNodeRef() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getActionDefinitionName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub

	}

	@Override
	public Boolean getTrackStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTrackStatus(Boolean trackStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getExecuteAsychronously() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setExecuteAsynchronously(boolean executeAsynchronously) {
		// TODO Auto-generated method stub

	}

	@Override
	public Action getCompensatingAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCompensatingAction(Action action) {
		// TODO Auto-generated method stub

	}

	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getModifiedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getModifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasActionConditions() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int indexOfActionCondition(ActionCondition actionCondition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ActionCondition> getActionConditions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionCondition getActionCondition(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addActionCondition(ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addActionCondition(int index, ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setActionCondition(int index, ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeActionCondition(ActionCondition actionCondition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAllActionConditions() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addParameterValues(Map<String, Serializable> values) {
		// TODO Auto-generated method stub

	}

	@Override
	public Date getExecutionStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getExecutionEndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionStatus getExecutionStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getExecutionFailureMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
