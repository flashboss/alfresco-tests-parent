package org.alfresco.mock.test.ws;

import org.springframework.extensions.webscripts.TemplateProcessor;
import org.springframework.extensions.webscripts.TemplateProcessorRegistry;

/**
 * Mock implementation of MockTemplateProcessorRegistry for testing purposes.
 *
 * @author vige
 */
public class MockTemplateProcessorRegistry extends TemplateProcessorRegistry {

	@Override
	public String findValidTemplatePath(final String path) {
		return "";
	}
	
	@Override
	public TemplateProcessor getTemplateProcessor(String path) {
		return new MockTemplateProcessor();
	}
}
