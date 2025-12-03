package org.alfresco.mock.test.ws;

import java.util.HashMap;
import java.util.Map;

import org.springframework.extensions.webscripts.Container;
import org.springframework.extensions.webscripts.Runtime;
import org.springframework.extensions.webscripts.WebScriptSession;

/**
 * Mock implementation of the WebScript {@link Runtime} interface for testing purposes.
 * This class provides a basic runtime environment for WebScripts in tests,
 * including template parameters access.
 *
 * @author lucastancapiano
 */
public class MockRuntime implements Runtime {

	/**
	 * The map of template parameters available to WebScripts.
	 */
	private Map<String, Object> templateParameters = new HashMap<String, Object>();

	/**
	 * {@inheritDoc}
	 * Returns the name of this runtime.
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
	 * Returns the WebScript session.
	 *
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public WebScriptSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Returns the container for this runtime.
	 *
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Container getContainer() {
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
	 * Returns the template parameters map.
	 *
	 * @return The map of template parameters.
	 */
	@Override
	public Map<String, Object> getTemplateParameters() {
		return templateParameters;
	}

}
