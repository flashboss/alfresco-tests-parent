package org.alfresco.mock.test.script;

import java.io.Serializable;

import org.alfresco.repo.jscript.ScriptLogger;
import org.alfresco.repo.processor.BaseProcessorExtension;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Mock implementation of the MockLogger class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockLogger extends BaseProcessorExtension implements Serializable {
	
	private static final Log logger = LogFactory.getLog(ScriptLogger.class);
	private static final SystemOut systemOut = new SystemOut();

	public boolean isLoggingEnabled() {
		return isDebugLoggingEnabled();
	}

	public void log(String str) {
		debug(str);
	}

	public boolean isDebugLoggingEnabled() {
		return logger.isDebugEnabled();
	}

	public void debug(String str) {
		logger.debug(str);
	}

	public boolean isInfoLoggingEnabled() {
		return logger.isInfoEnabled();
	}

	public void info(String str) {
		logger.info(str);
	}

	public boolean isWarnLoggingEnabled() {
		return logger.isWarnEnabled();
	}

	public void warn(String str) {
		logger.warn(str);
	}

	public boolean isErrorLoggingEnabled() {
		return logger.isErrorEnabled();
	}

	public void error(String str) {
		logger.error(str);
	}

	public SystemOut getSystem() {
		return systemOut;
	}

	public static class SystemOut {
		public void out(Object str) {
			System.out.println(str);
		}
	}
}