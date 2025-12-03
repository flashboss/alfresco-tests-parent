package org.alfresco.mock.test.activiti;

import org.alfresco.mock.test.script.MockScope;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * @author vige
 */
public class MockActivitiScriptNode extends ActivitiScriptNode {

	public MockActivitiScriptNode(NodeRef nodeRef, ServiceRegistry services) {
		super(nodeRef, services);
	}

	@Override
	public void save() {
	}

	@Override
	public void setScope(Scriptable scope) {
		Context.enter();
		this.scope = new MockScope();
	}

}