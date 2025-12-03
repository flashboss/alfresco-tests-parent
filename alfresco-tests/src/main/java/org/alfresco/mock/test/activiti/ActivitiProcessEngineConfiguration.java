package org.alfresco.mock.test.activiti;

import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.alfresco.mock.test.script.MockLogger;
import org.alfresco.repo.jscript.ScriptUtils;
import org.alfresco.repo.jscript.Search;
import org.alfresco.service.ServiceRegistry;

/**
* Mock implementation of the ActivitiProcessEngineConfiguration class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class ActivitiProcessEngineConfiguration extends StandaloneProcessEngineConfiguration {

/**
* The service registry.
 */
	private ServiceRegistry serviceRegistry;
/**
* The search script.
 */
	private Search searchScript;
/**
* The logger script.
 */
	private MockLogger loggerScript;
/**
* The utils script.
 */
	private ScriptUtils utilsScript;

/**
* Gets the service registry.
*
* @return the service registry
 */
	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

/**
* Sets the service registry.
*
* @param serviceRegistry the service registry
 */
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

/**
* Gets the search script.
*
* @return the search script
 */
	public Search getSearchScript() {
		return searchScript;
	}

/**
* Sets the search script.
*
* @param searchScript the search script
 */
	public void setSearchScript(Search searchScript) {
		this.searchScript = searchScript;
	}

/**
* Gets the logger script.
*
* @return the logger script
 */
	public MockLogger getLoggerScript() {
		return loggerScript;
	}

/**
* Sets the logger script.
*
* @param loggerScript the logger script
 */
	public void setLoggerScript(MockLogger loggerScript) {
		this.loggerScript = loggerScript;
	}

/**
* Gets the utils script.
*
* @return the utils script
 */
	public ScriptUtils getUtilsScript() {
		return utilsScript;
	}

/**
* Sets the utils script.
*
* @param utilsScript the utils script
 */
	public void setUtilsScript(ScriptUtils utilsScript) {
		this.utilsScript = utilsScript;
	}
}
