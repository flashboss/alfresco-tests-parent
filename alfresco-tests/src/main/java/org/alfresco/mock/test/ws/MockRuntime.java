package org.alfresco.mock.test.ws;

import java.util.HashMap;
import java.util.Map;

import org.springframework.extensions.webscripts.Container;
import org.springframework.extensions.webscripts.Runtime;
import org.springframework.extensions.webscripts.WebScriptSession;

/**
 * Mock implementation of Runtime for testing purposes.
 * Provides WebScript runtime stub.
 * 
 * @author vige
 */
public class MockRuntime implements Runtime {
	
	private Map<String, Object> templateParameters = new HashMap<String, Object>();

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebScriptSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Container getContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getScriptParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getTemplateParameters() {
		return templateParameters;
	}

}
