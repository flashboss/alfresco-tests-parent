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

	private ServiceRegistry serviceRegistry;
	private Search searchScript;
	private MockLogger loggerScript;
	private ScriptUtils utilsScript;

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public Search getSearchScript() {
		return searchScript;
	}

	public void setSearchScript(Search searchScript) {
		this.searchScript = searchScript;
	}

	public MockLogger getLoggerScript() {
		return loggerScript;
	}

	public void setLoggerScript(MockLogger loggerScript) {
		this.loggerScript = loggerScript;
	}

	public ScriptUtils getUtilsScript() {
		return utilsScript;
	}

	public void setUtilsScript(ScriptUtils utilsScript) {
		this.utilsScript = utilsScript;
	}
}
