package org.alfresco.mock.test.ws;

import java.util.HashMap;
import java.util.Map;

import org.alfresco.repo.content.MimetypeMap;
import org.springframework.extensions.config.ConfigService;
import org.springframework.extensions.webscripts.Container;
import org.springframework.extensions.webscripts.FormatRegistry;
import org.springframework.extensions.webscripts.Registry;
import org.springframework.extensions.webscripts.ScriptParameterFactoryRegistry;
import org.springframework.extensions.webscripts.ScriptProcessorRegistry;
import org.springframework.extensions.webscripts.SearchPath;
import org.springframework.extensions.webscripts.ServerModel;
import org.springframework.extensions.webscripts.TemplateProcessorRegistry;

/**
 * Mock implementation of Container for testing purposes.
 * 
 * @author vige
 */
public class MockContainer implements Container {

	/** The template parameters. */
	private Map<String, Object> templateParameters = new HashMap<String, Object>();
	/** The search path. */
	private SearchPath searchPath = new SearchPath();
	/** The script processor registry. */
	private ScriptProcessorRegistry scriptProcessorRegistry = new ScriptProcessorRegistry();
	/** The template processor registry. */
	private TemplateProcessorRegistry templateProcessorRegistry = new MockTemplateProcessorRegistry();
	/** The format registry. */
	private FormatRegistry formatRegistry = new FormatRegistry();
	{
		Map<String, String> formats = new HashMap<String, String>();
		formats.put("json", MimetypeMap.MIMETYPE_JSON);
		formats.put("xls", MimetypeMap.MIMETYPE_EXCEL);
		formatRegistry.addFormats(null, formats);
	}

	@Override
	/**
	 * Get name.
	 *
	 * @return the string
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get description.
	 *
	 * @return the server model
	 */
	public ServerModel getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get script processor registry.
	 *
	 * @return the script processor registry
	 */
	public ScriptProcessorRegistry getScriptProcessorRegistry() {
		return scriptProcessorRegistry;
	}

	@Override
	/**
	 * Get script parameter factory registry.
	 *
	 * @return the script parameter factory registry
	 */
	public ScriptParameterFactoryRegistry getScriptParameterFactoryRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get script parameters.
	 *
	 */
	public Map<String, Object> getScriptParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get template processor registry.
	 *
	 * @return the template processor registry
	 */
	public TemplateProcessorRegistry getTemplateProcessorRegistry() {
		return templateProcessorRegistry;
	}

	@Override
	/**
	 * Get template parameters.
	 *
	 */
	public Map<String, Object> getTemplateParameters() {
		// TODO Auto-generated method stub
		return templateParameters;
	}

	@Override
	/**
	 * Get format registry.
	 *
	 * @return the format registry
	 */
	public FormatRegistry getFormatRegistry() {
		return formatRegistry;
	}

	@Override
	/**
	 * Get registry.
	 *
	 * @return the registry
	 */
	public Registry getRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get search path.
	 *
	 * @return the search path
	 */
	public SearchPath getSearchPath() {
		return searchPath;
	}

	@Override
	/**
	 * Get config service.
	 *
	 * @return the config service
	 */
	public ConfigService getConfigService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Allow callbacks.
	 *
	 * @return the boolean
	 */
	public boolean allowCallbacks() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Reset.
	 *
	 */
	public void reset() {
		// TODO Auto-generated method stub

	}

}
