package org.alfresco.mock.test.ws;

import java.util.HashMap;
import java.util.Map;

import org.springframework.extensions.webscripts.Container;
import org.springframework.extensions.webscripts.Runtime;
import org.springframework.extensions.webscripts.WebScriptSession;

/**
 * Mock implementation of Runtime for testing purposes.
 * 
 * @author vige
 */
public class MockRuntime implements Runtime {
	
	/** The template parameters. */
	private Map<String, Object> templateParameters = new HashMap<String, Object>();

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
	 * Get session.
	 *
	 * @return the web script session
	 */
	public WebScriptSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get container.
	 *
	 * @return the container
	 */
	public Container getContainer() {
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
	 * Get template parameters.
	 *
	 */
	public Map<String, Object> getTemplateParameters() {
		return templateParameters;
	}

}
