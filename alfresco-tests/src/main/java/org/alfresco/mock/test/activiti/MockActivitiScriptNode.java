package org.alfresco.mock.test.activiti;

import org.alfresco.mock.test.script.MockScope;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * Mock implementation of MockActivitiScriptNode for testing purposes.
 *
 * @author vige
 */
public class MockActivitiScriptNode extends ActivitiScriptNode {

	/**
	 * Constructs a new MockActivitiScriptNode.
	 *
	 * @param nodeRef the node ref
	 * @param services the services
	 */
	public MockActivitiScriptNode(NodeRef nodeRef, ServiceRegistry services) {
		super(nodeRef, services);
	}

	@Override
	/**
	 * Save.
	 *
	 */
	public void save() {
	}

	@Override
	/**
	 * Set scope.
	 *
	 * @param scope the scope
	 */
	public void setScope(Scriptable scope) {
		Context.enter();
		this.scope = new MockScope();
	}

}