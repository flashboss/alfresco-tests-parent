package org.alfresco.mock.test.ws;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Mock implementation of Writer for testing purposes.
 * 
 * @author vige
 */
public class MockWriter extends Writer {

	private Map<String, Object> model;

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
	}

	@Override
	public void flush() throws IOException {
	}

	@Override
	public void close() throws IOException {
	}

 /** Get model. */
	public Map<String, Object> getModel() {
		return model;
	}

 /**
 * Set model.
 *
 * @param model the model
 */
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
