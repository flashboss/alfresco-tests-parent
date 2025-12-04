package org.alfresco.mock.test.ws;

import java.io.Writer;
import java.util.Map;

import org.springframework.extensions.webscripts.TemplateProcessor;

/**
 * Mock implementation of TemplateProcessor for testing purposes.
 * 
 * @author vige
 */
public class MockTemplateProcessor implements TemplateProcessor {
	/**
	 * Has template.
	 *
	 * @param template the template
	 * @return the boolean
	 */
	@Override
	public boolean hasTemplate(String template) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Process.
	 *
	 * @param template the template
	 * @param model    the model
	 * @param out      the out
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void process(String template, Object model, Writer out) {
		if (model instanceof Map)
			((MockWriter) out).setModel((Map<String, Object>) model);
	}

	/**
	 * Process string.
	 *
	 * @param template the template
	 * @param model    the model
	 * @param out      the out
	 */
	@Override
	public void processString(String template, Object model, Writer out) {
		// TODO Auto-generated method stub

	}

	/**
	 * Get default encoding.
	 *
	 * @return the string
	 */
	@Override
	public String getDefaultEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Reset.
	 *
	 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
