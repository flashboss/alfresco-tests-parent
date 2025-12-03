package org.alfresco.mock.test.ws;

import java.io.Writer;
import java.util.Map;

import org.springframework.extensions.webscripts.TemplateProcessor;

/**
 * Mock implementation of the MockTemplateProcessor class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockTemplateProcessor implements TemplateProcessor {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasTemplate(String template) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void process(String template, Object model, Writer out) {
		if (model instanceof Map)
			((MockWriter) out).setModel((Map<String, Object>) model);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processString(String template, Object model, Writer out) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDefaultEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
