package org.alfresco.mock.test.ws;

import java.io.Writer;
import java.util.Map;

import org.springframework.extensions.webscripts.TemplateProcessor;

/**
 * Mock implementation of the {@link TemplateProcessor} interface for testing purposes.
 * This class provides a basic template processing capability that captures the model
 * data written to WebScript responses during tests.
 *
 * @author lucastancapiano
 */
public class MockTemplateProcessor implements TemplateProcessor {

	/**
	 * {@inheritDoc}
	 * Checks if a template exists at the given path.
	 *
	 * @param template The template path to check.
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean hasTemplate(String template) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Processes a template with the given model and writes the output.
	 * In this mock implementation, if the model is a Map, it is captured
	 * in the MockWriter for later retrieval during testing.
	 *
	 * @param template The template path.
	 * @param model The model object containing data for the template.
	 * @param out The writer to output the processed template.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void process(String template, Object model, Writer out) {
		if (model instanceof Map)
			((MockWriter) out).setModel((Map<String, Object>) model);
	}

	/**
	 * {@inheritDoc}
	 * Processes a template string with the given model and writes the output.
	 * This is a stub implementation.
	 *
	 * @param template The template string.
	 * @param model The model object containing data for the template.
	 * @param out The writer to output the processed template.
	 */
	@Override
	public void processString(String template, Object model, Writer out) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 * Returns the default encoding for templates.
	 *
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public String getDefaultEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Resets the template processor state. This is a stub implementation.
	 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
