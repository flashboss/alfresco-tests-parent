package org.alfresco.mock.test.ws;

import java.io.Writer;

import org.springframework.extensions.webscripts.TemplateProcessor;

public class MockTemplateProcessor implements TemplateProcessor {

	@Override
	public boolean hasTemplate(String template) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void process(String template, Object model, Writer out) {
		// TODO Auto-generated method stub
		
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
