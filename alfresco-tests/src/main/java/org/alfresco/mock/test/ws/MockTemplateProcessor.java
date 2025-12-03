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

	@Override
	public void processString(String template, Object model, Writer out) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDefaultEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
