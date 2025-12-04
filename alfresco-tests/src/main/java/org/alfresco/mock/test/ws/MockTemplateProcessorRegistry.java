package org.alfresco.mock.test.ws;

import org.springframework.extensions.webscripts.TemplateProcessor;
import org.springframework.extensions.webscripts.TemplateProcessorRegistry;

/**
 * Mock implementation of TemplateProcessorRegistry for testing purposes.
 * 
 * @author vige
 */
public class MockTemplateProcessorRegistry extends TemplateProcessorRegistry {
	/**
	 * Find valid template path.
	 *
	 * @param path the path
	 * @return the string
	 */
	@Override
	public String findValidTemplatePath(final String path) {
		return "";
	}
	/**
	 * Get template processor.
	 *
	 * @param path the path
	 * @return the template processor
	 */
	@Override
	public TemplateProcessor getTemplateProcessor(String path) {
		return new MockTemplateProcessor();
	}
}
