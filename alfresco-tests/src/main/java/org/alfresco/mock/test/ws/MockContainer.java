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

public class MockContainer implements Container {

	private SearchPath searchPath = new SearchPath();
	private FormatRegistry formatRegistry = new FormatRegistry();
	{
		Map<String, String> formats = new HashMap<String, String>();
		formats.put("json", MimetypeMap.MIMETYPE_JSON);
		formatRegistry.addFormats(null, formats);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerModel getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScriptProcessorRegistry getScriptProcessorRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScriptParameterFactoryRegistry getScriptParameterFactoryRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getScriptParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemplateProcessorRegistry getTemplateProcessorRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getTemplateParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormatRegistry getFormatRegistry() {
		return formatRegistry;
	}

	@Override
	public Registry getRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchPath getSearchPath() {
		return searchPath;
	}

	@Override
	public ConfigService getConfigService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean allowCallbacks() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
