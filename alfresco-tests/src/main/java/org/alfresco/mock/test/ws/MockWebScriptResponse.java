package org.alfresco.mock.test.ws;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.Runtime;
import org.springframework.extensions.webscripts.WebScriptResponse;

public class MockWebScriptResponse implements WebScriptResponse {

	private MockWriter mockWriter = new MockWriter();

	@Override
	public void setStatus(int status) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHeader(String name, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addHeader(String name, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContentType(String contentType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContentEncoding(String contentEncoding) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCache(Cache cache) {
		// TODO Auto-generated method stub

	}

	@Override
	public Writer getWriter() throws IOException {
		return mockWriter;
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public String encodeScriptUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeResourceUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEncodeScriptUrlFunction(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEncodeResourceUrlFunction(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Runtime getRuntime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset(String arg0) {
		// TODO Auto-generated method stub

	}

	public Map<String, Object> getModel() {
		return mockWriter.getModel();
	}

}
