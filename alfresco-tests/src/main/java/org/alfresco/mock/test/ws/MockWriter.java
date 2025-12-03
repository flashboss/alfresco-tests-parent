package org.alfresco.mock.test.ws;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Mock implementation of {@link Writer} for testing purposes.
 * This class captures model data from WebScript processing, allowing tests
 * to verify the data that would normally be rendered to the response.
 *
 * @author lucastancapiano
 */
public class MockWriter extends Writer {

	/**
	 * The model data captured from template processing.
	 */
	private Map<String, Object> model;

	/**
	 * {@inheritDoc}
	 * Writes a portion of an array of characters to the output.
	 * This is a no-op in the mock implementation.
	 *
	 * @param cbuf The character array to write.
	 * @param off The offset from which to start writing.
	 * @param len The number of characters to write.
	 * @throws IOException If an I/O error occurs.
	 */
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
	}

	/**
	 * {@inheritDoc}
	 * Flushes the writer. This is a no-op in the mock implementation.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	@Override
	public void flush() throws IOException {
	}

	/**
	 * {@inheritDoc}
	 * Closes the writer. This is a no-op in the mock implementation.
	 *
	 * @throws IOException If an I/O error occurs.
	 */
	@Override
	public void close() throws IOException {
	}

	/**
	 * Returns the model data captured during template processing.
	 *
	 * @return The map containing the model data.
	 */
	public Map<String, Object> getModel() {
		return model;
	}

	/**
	 * Sets the model data. This is typically called by the mock template processor
	 * when processing a template with model data.
	 *
	 * @param model The map containing the model data to set.
	 */
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
