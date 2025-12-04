package org.alfresco.mock.test.activiti;

import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.alfresco.mock.test.script.MockLogger;
import org.alfresco.repo.jscript.ScriptUtils;
import org.alfresco.repo.jscript.Search;
import org.alfresco.service.ServiceRegistry;

/**
 * Mock implementation of ActivitiProcessEngineConfiguration for testing purposes.
 *
 * @author vige
 */
public class ActivitiProcessEngineConfiguration extends StandaloneProcessEngineConfiguration {

	/** The service registry. */
	private ServiceRegistry serviceRegistry;
	/** The search script. */
	private Search searchScript;
	/** The logger script. */
	private MockLogger loggerScript;
	/** The utils script. */
	private ScriptUtils utilsScript;

	/**
	 * Get service registry.
	 *
	 * @return the result
	 */
	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	/**
	 * Set service registry.
	 *
	 * @param serviceRegistry the service registry
	 */
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	/**
	 * Get search script.
	 *
	 * @return the result
	 */
	public Search getSearchScript() {
		return searchScript;
	}

	/**
	 * Set search script.
	 *
	 * @param searchScript the search script
	 */
	public void setSearchScript(Search searchScript) {
		this.searchScript = searchScript;
	}

	/**
	 * Get logger script.
	 *
	 * @return the result
	 */
	public MockLogger getLoggerScript() {
		return loggerScript;
	}

	/**
	 * Set logger script.
	 *
	 * @param loggerScript the logger script
	 */
	public void setLoggerScript(MockLogger loggerScript) {
		this.loggerScript = loggerScript;
	}

	/**
	 * Get utils script.
	 *
	 * @return the result
	 */
	public ScriptUtils getUtilsScript() {
		return utilsScript;
	}

	/**
	 * Set utils script.
	 *
	 * @param utilsScript the utils script
	 */
	public void setUtilsScript(ScriptUtils utilsScript) {
		this.utilsScript = utilsScript;
	}
}
