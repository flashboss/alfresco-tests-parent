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
 * Mock implementation of the WebScript {@link Container} interface for testing purposes.
 * This class provides a basic container environment for executing WebScripts in tests,
 * including registries for format, script processor, and template processor.
 *
 * @author lucastancapiano
 */
public class MockContainer implements Container {

	/**
	 * The map of template parameters available to WebScripts.
	 */
	private Map<String, Object> templateParameters = new HashMap<String, Object>();

	/**
	 * The search path for locating WebScript resources.
	 */
	private SearchPath searchPath = new SearchPath();

	/**
	 * The registry for script processors.
	 */
	private ScriptProcessorRegistry scriptProcessorRegistry = new ScriptProcessorRegistry();

	/**
	 * The registry for template processors.
	 */
	private TemplateProcessorRegistry templateProcessorRegistry = new MockTemplateProcessorRegistry();

	/**
	 * The registry for supported formats (e.g., JSON, XLS).
	 */
	private FormatRegistry formatRegistry = new FormatRegistry();
	{
		Map<String, String> formats = new HashMap<String, String>();
		formats.put("json", MimetypeMap.MIMETYPE_JSON);
		formats.put("xls", MimetypeMap.MIMETYPE_EXCEL);
		formatRegistry.addFormats(null, formats);
	}

	/**
	 * {@inheritDoc}
	 * Returns the name of this container.
	 *
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Returns the server model description.
	 *
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public ServerModel getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Returns the script processor registry.
	 *
	 * @return The {@link ScriptProcessorRegistry} instance.
	 */
	@Override
	public ScriptProcessorRegistry getScriptProcessorRegistry() {
		return scriptProcessorRegistry;
	}

	/**
	 * {@inheritDoc}
	 * Returns the script parameter factory registry.
	 *
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public ScriptParameterFactoryRegistry getScriptParameterFactoryRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Returns the script parameters map.
	 *
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Map<String, Object> getScriptParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Returns the template processor registry.
	 *
	 * @return The {@link TemplateProcessorRegistry} instance.
	 */
	@Override
	public TemplateProcessorRegistry getTemplateProcessorRegistry() {
		return templateProcessorRegistry;
	}

	/**
	 * {@inheritDoc}
	 * Returns the template parameters map.
	 *
	 * @return The map of template parameters.
	 */
	@Override
	public Map<String, Object> getTemplateParameters() {
		// TODO Auto-generated method stub
		return templateParameters;
	}

	/**
	 * {@inheritDoc}
	 * Returns the format registry containing supported formats.
	 *
	 * @return The {@link FormatRegistry} instance.
	 */
	@Override
	public FormatRegistry getFormatRegistry() {
		return formatRegistry;
	}

	/**
	 * {@inheritDoc}
	 * Returns the WebScript registry.
	 *
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Registry getRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Returns the search path for WebScript resources.
	 *
	 * @return The {@link SearchPath} instance.
	 */
	@Override
	public SearchPath getSearchPath() {
		return searchPath;
	}

	/**
	 * {@inheritDoc}
	 * Returns the configuration service.
	 *
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public ConfigService getConfigService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Indicates whether callbacks are allowed.
	 *
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean allowCallbacks() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Resets the container state. This is a stub implementation.
	 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
