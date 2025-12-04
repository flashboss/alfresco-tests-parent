package org.alfresco.mock.test.activiti;

import org.alfresco.mock.test.script.MockScope;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

/**
 * Mock implementation of the MockActivitiScriptNode class for testing purposes. This class provides
 * a mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockActivitiScriptNode extends ActivitiScriptNode {

  /**
   * Constructs a new MockActivitiScriptNode with the specified node reference and service registry.
   *
   * @param nodeRef the node reference
   * @param services the service registry
   */
  public MockActivitiScriptNode(NodeRef nodeRef, ServiceRegistry services) {
    super(nodeRef, services);
  }

  /** {@inheritDoc} */
  @Override
  public void save() {}

  /**
   * {@inheritDoc}
   *
   * @param scope the scope
   */
  @Override
  public void setScope(Scriptable scope) {
    Context.enter();
    this.scope = new MockScope();
  }
}
