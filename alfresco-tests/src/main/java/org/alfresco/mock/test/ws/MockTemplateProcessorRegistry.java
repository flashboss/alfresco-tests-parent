package org.alfresco.mock.test.ws;

import org.springframework.extensions.webscripts.TemplateProcessor;
import org.springframework.extensions.webscripts.TemplateProcessorRegistry;

/**
 * Mock implementation of the {@link TemplateProcessorRegistry} for testing purposes.
 * This class provides mock template processor lookup functionality for WebScripts in tests,
 * returning empty paths and mock template processors.
 *
 * @author lucastancapiano
 */
public class MockTemplateProcessorRegistry extends TemplateProcessorRegistry {

	/**
	 * {@inheritDoc}
	 * Finds a valid template path for the given path.
	 *
	 * @param path The template path to search for.
	 * @return An empty string as this is a mock implementation.
	 */
	@Override
	public String findValidTemplatePath(final String path) {
		return "";
	}

	/**
	 * {@inheritDoc}
	 * Returns a template processor for the given path.
	 *
	 * @param path The template path.
	 * @return A new {@link MockTemplateProcessor} instance.
	 */
	@Override
	public TemplateProcessor getTemplateProcessor(String path) {
		return new MockTemplateProcessor();
	}
}
