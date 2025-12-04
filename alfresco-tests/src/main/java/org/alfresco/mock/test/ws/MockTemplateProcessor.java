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

	@Override
 /**
 * Has template.
 *
 * @param template the template
 * @return the boolean
 */
	public boolean hasTemplate(String template) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
 /**
 * Process.
 *
 * @param template the template
 * @param model the model
 * @param out the out
 */
	public void process(String template, Object model, Writer out) {
		if (model instanceof Map)
			((MockWriter) out).setModel((Map<String, Object>) model);
	}

	@Override
 /**
 * Process string.
 *
 * @param template the template
 * @param model the model
 * @param out the out
 */
	public void processString(String template, Object model, Writer out) {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Get default encoding.
 *
 * @return the string
 */
	public String getDefaultEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /** Reset. */
	public void reset() {
		// TODO Auto-generated method stub

	}

}
