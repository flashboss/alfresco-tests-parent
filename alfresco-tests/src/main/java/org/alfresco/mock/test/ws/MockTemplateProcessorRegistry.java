package org.alfresco.mock.test.ws;

import org.springframework.extensions.webscripts.TemplateProcessor;
import org.springframework.extensions.webscripts.TemplateProcessorRegistry;

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
