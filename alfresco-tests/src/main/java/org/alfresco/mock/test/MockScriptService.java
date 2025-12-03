package org.alfresco.mock.test;

import java.io.Serializable;
import org.alfresco.repo.processor.ScriptServiceImpl;
import org.alfresco.service.cmr.repository.ScriptProcessor;

/**
 * Mock implementation of the MockScriptService class for testing purposes. This class provides a
 * mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockScriptService extends ScriptServiceImpl implements Serializable {

  /** The script processor. */
  private ScriptProcessor scriptProcessor;

  /**
   * Performs lookup script processor.
   *
   * @param name the name
   * @return the result
   */
  @Override
  protected ScriptProcessor lookupScriptProcessor(String name) {
    return scriptProcessor;
  }

  /**
   * Gets the script processor.
   *
   * @return the script processor
   */
  public ScriptProcessor getScriptProcessor() {
    return scriptProcessor;
  }

  /**
   * Sets the script processor.
   *
   * @param scriptProcessor the script processor
   */
  public void setScriptProcessor(ScriptProcessor scriptProcessor) {
    this.scriptProcessor = scriptProcessor;
  }
}
