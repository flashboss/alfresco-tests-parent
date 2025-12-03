package org.alfresco.mock.test.ws;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
* Mock implementation of the MockWriter class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockWriter extends Writer {

	private Map<String, Object> model;

/**
* {@inheritDoc}
* @param off the off
* @param len the len
* @throws IOException if an error occurs
 */
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
	}

/**
* {@inheritDoc}
* @throws IOException if an error occurs
 */
	@Override
	public void flush() throws IOException {
	}

/**
* {@inheritDoc}
* @throws IOException if an error occurs
 */
	@Override
	public void close() throws IOException {
	}

/**
* Gets the model map.
*
* @return the model map
 */
	public Map<String, Object> getModel() {
		return model;
	}

/**
* Sets the model.
*
* @param model the model map
 */
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
