package org.alfresco.mock.test.ws;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.alfresco.service.ServiceRegistry;
import org.springframework.extensions.surf.util.Content;
import org.springframework.extensions.webscripts.Description.FormatStyle;
import org.springframework.extensions.webscripts.Match;
import org.springframework.extensions.webscripts.Runtime;
import org.springframework.extensions.webscripts.WebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.servlet.FormData;

/**
 * Mock implementation of the MockWebScriptRequest class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockWebScriptRequest implements WebScriptRequest {

	private String format;
	private FormData formData;
	private Runtime runtime;
	private Match serviceMatch;
	private String[] parameterNames;
	private String[] headerNames;
	private HttpServletRequest httpServletRequest;

	public MockWebScriptRequest(String format, Map<String, String> templateVars, WebScript webScript,
			Map<String, Serializable> requestFields, ServiceRegistry serviceRegistry) {

		this.format = format;
		this.runtime = new MockRuntime();
		httpServletRequest = new MockHttpServletRequest(requestFields, serviceRegistry);
		formData = new FormData(httpServletRequest);
		if (templateVars == null)
			templateVars = new HashMap<String, String>();
		serviceMatch = new Match(null, templateVars, null, webScript);
		parameterNames = new String[0];
		headerNames = new String[0];
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Match getServiceMatch() {
		return serviceMatch;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getServerPath() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getServiceContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getServicePath() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getURL() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String[] getParameterNames() {
		return parameterNames;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getParameter(String name) {
		return httpServletRequest.getParameter(name);
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String[] getHeaderNames() {
		return headerNames;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String[] getHeaderValues(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getExtensionPath() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Content getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Object parseContent() {
		return formData;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public boolean isGuest() {
		// TODO Auto-generated method stub
		return false;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getFormat() {
		return format;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public FormatStyle getFormatStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getAgent() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getJSONCallback() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public boolean forceSuccessStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Runtime getRuntime() {
		return runtime;
	}

}
