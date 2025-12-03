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
* Mock implementation of the MockContainer class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockContainer implements Container {

	private Map<String, Object> templateParameters = new HashMap<String, Object>();
	private SearchPath searchPath = new SearchPath();
	private ScriptProcessorRegistry scriptProcessorRegistry = new ScriptProcessorRegistry();
	private TemplateProcessorRegistry templateProcessorRegistry = new MockTemplateProcessorRegistry();
	private FormatRegistry formatRegistry = new FormatRegistry();
	{
		Map<String, String> formats = new HashMap<String, String>();
		formats.put("json", MimetypeMap.MIMETYPE_JSON);
		formats.put("xls", MimetypeMap.MIMETYPE_EXCEL);
		formatRegistry.addFormats(null, formats);
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ServerModel getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ScriptProcessorRegistry getScriptProcessorRegistry() {
		return scriptProcessorRegistry;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ScriptParameterFactoryRegistry getScriptParameterFactoryRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Map<String, Object> getScriptParameters() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public TemplateProcessorRegistry getTemplateProcessorRegistry() {
		return templateProcessorRegistry;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Map<String, Object> getTemplateParameters() {
		// TODO Auto-generated method stub
		return templateParameters;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public FormatRegistry getFormatRegistry() {
		return formatRegistry;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Registry getRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public SearchPath getSearchPath() {
		return searchPath;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ConfigService getConfigService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public boolean allowCallbacks() {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
