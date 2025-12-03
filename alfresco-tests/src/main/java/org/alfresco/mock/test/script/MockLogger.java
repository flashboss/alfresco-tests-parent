package org.alfresco.mock.test.script;

import java.io.Serializable;
import org.alfresco.repo.jscript.ScriptLogger;
import org.alfresco.repo.processor.BaseProcessorExtension;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Mock implementation of the MockLogger class for testing purposes. This class provides a mock
 * implementation that allows unit and integration tests to run without requiring a full Alfresco
 * server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockLogger extends BaseProcessorExtension implements Serializable {

  private static final Log logger = LogFactory.getLog(ScriptLogger.class);
  private static final SystemOut systemOut = new SystemOut();

  /**
   * Checks if logging is enabled.
   *
   * @return true if logging is enabled, false otherwise
   */
  public boolean isLoggingEnabled() {
    return isDebugLoggingEnabled();
  }

  /**
   * Logs a message.
   *
   * @param str the message to log
   */
  public void log(String str) {
    debug(str);
  }

  /**
   * Checks if debug logging is enabled.
   *
   * @return true if debug logging is enabled, false otherwise
   */
  public boolean isDebugLoggingEnabled() {
    return logger.isDebugEnabled();
  }

  /**
   * Logs a debug message.
   *
   * @param str the debug message
   */
  public void debug(String str) {
    logger.debug(str);
  }

  /**
   * Checks if info logging is enabled.
   *
   * @return true if info logging is enabled, false otherwise
   */
  public boolean isInfoLoggingEnabled() {
    return logger.isInfoEnabled();
  }

  /**
   * Logs an info message.
   *
   * @param str the info message
   */
  public void info(String str) {
    logger.info(str);
  }

  /**
   * Checks if warn logging is enabled.
   *
   * @return true if warn logging is enabled, false otherwise
   */
  public boolean isWarnLoggingEnabled() {
    return logger.isWarnEnabled();
  }

  /**
   * Logs a warning message.
   *
   * @param str the warning message
   */
  public void warn(String str) {
    logger.warn(str);
  }

  /**
   * Checks if error logging is enabled.
   *
   * @return true if error logging is enabled, false otherwise
   */
  public boolean isErrorLoggingEnabled() {
    return logger.isErrorEnabled();
  }

  /**
   * Logs an error message.
   *
   * @param str the error message
   */
  public void error(String str) {
    logger.error(str);
  }

  /**
   * Gets the system output handler.
   *
   * @return the system output handler
   */
  public SystemOut getSystem() {
    return systemOut;
  }

  /** System output handler class. */
  public static class SystemOut {
    /**
     * Outputs an object to system out.
     *
     * @param str the object to output
     */
    public void out(Object str) {
      System.out.println(str);
    }
  }
}
