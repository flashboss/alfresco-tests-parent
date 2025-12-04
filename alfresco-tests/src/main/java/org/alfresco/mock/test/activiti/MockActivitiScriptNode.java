package org.alfresco.mock.test.activiti;

import org.alfresco.mock.test.script.MockScope;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * Mock implementation of ActivitiScriptNode for testing purposes.
 *
 * @author vige
 */
public class MockActivitiScriptNode extends ActivitiScriptNode {

  /**
   * Constructs a new mock activiti script node.
   *
   * @param nodeRef the node ref
   * @param services the services
   * @return the result
   */
  public MockActivitiScriptNode(NodeRef nodeRef, ServiceRegistry services) {
    super(nodeRef, services);
  }

  /** Save. */
  @Override
  public void save() {}

  /**
   * Set scope.
   *
   * @param scope the scope
   */
  @Override
  public void setScope(Scriptable scope) {
    Context.enter();
    this.scope = new MockScope();
  }
}
