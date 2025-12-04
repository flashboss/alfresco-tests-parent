package org.alfresco.mock.test.ws;

import java.util.HashMap;
import java.util.Map;
import org.springframework.extensions.webscripts.Container;
import org.springframework.extensions.webscripts.Runtime;
import org.springframework.extensions.webscripts.WebScriptSession;

/**
 * Mock implementation of MockRuntime for testing purposes.
 *
 * @author vige
 */
public class MockRuntime implements Runtime {

  /** The template parameters. */
  private Map<String, Object> templateParameters = new HashMap<String, Object>();

  /**
   * Get name.
   *
   * @return the result
   */
  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get session.
   *
   * @return the result
   */
  @Override
  public WebScriptSession getSession() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get container.
   *
   * @return the result
   */
  @Override
  public Container getContainer() {
    // TODO Auto-generated method stub
    return null;
  }

  /** Get script parameters. */
  @Override
  public Map<String, Object> getScriptParameters() {
    // TODO Auto-generated method stub
    return null;
  }

  /** Get template parameters. */
  @Override
  public Map<String, Object> getTemplateParameters() {
    return templateParameters;
  }
}
