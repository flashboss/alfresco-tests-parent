package org.alfresco.mock.test.ws;

import java.util.HashMap;
import java.util.Map;

import org.springframework.extensions.webscripts.Container;
import org.springframework.extensions.webscripts.Runtime;
import org.springframework.extensions.webscripts.WebScriptSession;

/**
 * Mock implementation of MockRuntime for testing purposes.
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
	 * @return the result
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get session.
	 *
	 * @return the result
	 */
	public WebScriptSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get container.
	 *
	 * @return the result
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
