package org.alfresco.mock.test.script;

import java.io.Serializable;

import org.alfresco.repo.jscript.ScriptLogger;
import org.alfresco.repo.processor.BaseProcessorExtension;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Mock implementation of MockLogger for testing purposes.
 *
 * @author vige
 */
public class MockLogger extends BaseProcessorExtension implements Serializable {
	
	/** The logger. */
	private static final Log logger = LogFactory.getLog(ScriptLogger.class);
	/** The system out. */
	private static final SystemOut systemOut = new SystemOut();

	/**
	 * Is logging enabled.
	 *
	 * @return the result
	 */
	public boolean isLoggingEnabled() {
		return isDebugLoggingEnabled();
	}

	/**
	 * Log.
	 *
	 * @param str the str
	 */
	public void log(String str) {
		debug(str);
	}

	/**
	 * Is debug logging enabled.
	 *
	 * @return the result
	 */
	public boolean isDebugLoggingEnabled() {
		return logger.isDebugEnabled();
	}

	/**
	 * Debug.
	 *
	 * @param str the str
	 */
	public void debug(String str) {
		logger.debug(str);
	}

	/**
	 * Is info logging enabled.
	 *
	 * @return the result
	 */
	public boolean isInfoLoggingEnabled() {
		return logger.isInfoEnabled();
	}

	/**
	 * Info.
	 *
	 * @param str the str
	 */
	public void info(String str) {
		logger.info(str);
	}

	/**
	 * Is warn logging enabled.
	 *
	 * @return the result
	 */
	public boolean isWarnLoggingEnabled() {
		return logger.isWarnEnabled();
	}

	/**
	 * Warn.
	 *
	 * @param str the str
	 */
	public void warn(String str) {
		logger.warn(str);
	}

	/**
	 * Is error logging enabled.
	 *
	 * @return the result
	 */
	public boolean isErrorLoggingEnabled() {
		return logger.isErrorEnabled();
	}

	/**
	 * Error.
	 *
	 * @param str the str
	 */
	public void error(String str) {
		logger.error(str);
	}

	/**
	 * Get system.
	 *
	 * @return the result
	 */
	public SystemOut getSystem() {
		return systemOut;
	}

	public static class SystemOut {
	/**
	 * Out.
	 *
	 * @param str the str
	 */
		public void out(Object str) {
			System.out.println(str);
		}
	}
}