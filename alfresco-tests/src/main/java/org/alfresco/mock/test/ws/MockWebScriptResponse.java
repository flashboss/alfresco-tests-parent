package org.alfresco.mock.test.ws;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.Runtime;
import org.springframework.extensions.webscripts.WebScriptResponse;

/**
 * Mock implementation of MockWebScriptResponse for testing purposes.
 *
 * @author vige
 */
public class MockWebScriptResponse implements WebScriptResponse {

	/** The mock writer. */
	private MockWriter mockWriter = new MockWriter();

	@Override
	/**
	 * Set status.
	 *
	 * @param status the status
	 */
	public void setStatus(int status) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Set header.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public void setHeader(String name, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Add header.
	 *
	 * @param name the name
	 * @param value the value
	 */
	public void addHeader(String name, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Set content type.
	 *
	 * @param contentType the content type
	 */
	public void setContentType(String contentType) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Set content encoding.
	 *
	 * @param contentEncoding the content encoding
	 */
	public void setContentEncoding(String contentEncoding) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Set cache.
	 *
	 * @param cache the cache
	 */
	public void setCache(Cache cache) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get writer.
	 *
	 * @return the result
	 */
	public Writer getWriter() throws IOException {
		return mockWriter;
	}

	@Override
	/**
	 * Get output stream.
	 *
	 * @return the result
	 */
	public OutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Reset.
	 *
	 */
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Encode script url.
	 *
	 * @param url the url
	 * @return the result
	 */
	public String encodeScriptUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Encode resource url.
	 *
	 * @param url the url
	 * @return the result
	 */
	public String encodeResourceUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get encode script url function.
	 *
	 * @param name the name
	 * @return the result
	 */
	public String getEncodeScriptUrlFunction(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get encode resource url function.
	 *
	 * @param name the name
	 * @return the result
	 */
	public String getEncodeResourceUrlFunction(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get runtime.
	 *
	 * @return the result
	 */
	public Runtime getRuntime() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get model.
	 *
	 */
	public Map<String, Object> getModel() {
		return mockWriter.getModel();
	}

}
