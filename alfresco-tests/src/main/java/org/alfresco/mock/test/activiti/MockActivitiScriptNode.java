package org.alfresco.mock.test.activiti;

import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.mozilla.javascript.Scriptable;

public class MockActivitiScriptNode extends ActivitiScriptNode {

	public MockActivitiScriptNode(NodeRef nodeRef, ServiceRegistry services) {
		super(nodeRef, services);
	}

	@Override
	public void save() {

	}
	
	@Override
    public void setScope(Scriptable scope)
    {
        this.scope = null;
    }

}