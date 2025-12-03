package org.alfresco.mock.test.activiti;

import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.alfresco.mock.test.script.MockLogger;
import org.alfresco.repo.jscript.ScriptUtils;
import org.alfresco.repo.jscript.Search;
import org.alfresco.service.ServiceRegistry;

/**
 * Configuration for Activiti process engine in mock environment.
 * Extends StandaloneProcessEngineConfiguration with Alfresco service
 * registry and script components.
 * 
 * @author vige
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
