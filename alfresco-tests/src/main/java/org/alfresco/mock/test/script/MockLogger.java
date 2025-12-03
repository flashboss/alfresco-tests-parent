package org.alfresco.mock.test.script;

import java.io.Serializable;

import org.alfresco.repo.jscript.ScriptLogger;
import org.alfresco.repo.processor.BaseProcessorExtension;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Mock implementation of ScriptLogger for testing purposes.
 * Provides logging functionality for JavaScript scripts.
 *
 * @author lucastancapiano
 */
public class MockLogger extends BaseProcessorExtension implements Serializable {

	/** The logger instance. */
	private static final Log logger = LogFactory.getLog(ScriptLogger.class);

	/** The system output wrapper. */
	private static final SystemOut systemOut = new SystemOut();

	/**
	 * Checks if logging is enabled.
	 *
	 * @return true if debug logging is enabled
	 */
	public boolean isLoggingEnabled() {
		return isDebugLoggingEnabled();
	}

	/**
	 * Logs a message at debug level.
	 *
	 * @param str the message to log
	 */
	public void log(String str) {
		debug(str);
	}

	/**
	 * Checks if debug logging is enabled.
	 *
	 * @return true if debug logging is enabled
	 */
	public boolean isDebugLoggingEnabled() {
		return logger.isDebugEnabled();
	}

	/**
	 * Logs a message at debug level.
	 *
	 * @param str the message to log
	 */
	public void debug(String str) {
		logger.debug(str);
	}

	/**
	 * Checks if info logging is enabled.
	 *
	 * @return true if info logging is enabled
	 */
	public boolean isInfoLoggingEnabled() {
		return logger.isInfoEnabled();
	}

	/**
	 * Logs a message at info level.
	 *
	 * @param str the message to log
	 */
	public void info(String str) {
		logger.info(str);
	}

	/**
	 * Checks if warn logging is enabled.
	 *
	 * @return true if warn logging is enabled
	 */
	public boolean isWarnLoggingEnabled() {
		return logger.isWarnEnabled();
	}

	/**
	 * Logs a message at warn level.
	 *
	 * @param str the message to log
	 */
	public void warn(String str) {
		logger.warn(str);
	}

	/**
	 * Checks if error logging is enabled.
	 *
	 * @return true if error logging is enabled
	 */
	public boolean isErrorLoggingEnabled() {
		return logger.isErrorEnabled();
	}

	/**
	 * Logs a message at error level.
	 *
	 * @param str the message to log
	 */
	public void error(String str) {
		logger.error(str);
	}

	/**
	 * Gets the system output wrapper.
	 *
	 * @return the system output wrapper
	 */
	public SystemOut getSystem() {
		return systemOut;
	}

	/**
	 * Inner class for system output operations.
	 */
	public static class SystemOut {
		/**
		 * Outputs an object to standard output.
		 *
		 * @param str the object to output
		 */
		public void out(Object str) {
			System.out.println(str);
		}
	}
}
