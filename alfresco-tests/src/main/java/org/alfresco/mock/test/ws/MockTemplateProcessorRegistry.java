package org.alfresco.mock.test.ws;

import org.springframework.extensions.webscripts.TemplateProcessor;
import org.springframework.extensions.webscripts.TemplateProcessorRegistry;

/**
* Mock implementation of the MockTemplateProcessorRegistry class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockTemplateProcessorRegistry extends TemplateProcessorRegistry {

/**
* {@inheritDoc}
* @param path the path
* @return the result
 */
	@Override
	public String findValidTemplatePath(final String path) {
		return "";
	}

/**
* {@inheritDoc}
* @param path the path
* @return the result
 */
	@Override
	public TemplateProcessor getTemplateProcessor(String path) {
		return new MockTemplateProcessor();
	}
}
