package org.alfresco.mock.test.ws;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alfresco.service.ServiceRegistry;
import org.springframework.extensions.surf.util.Content;
import org.springframework.extensions.webscripts.Description.FormatStyle;
import org.springframework.extensions.webscripts.Match;
import org.springframework.extensions.webscripts.Runtime;
import org.springframework.extensions.webscripts.WebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.servlet.FormData;

/**
 * Mock implementation of MockWebScriptRequest for testing purposes.
 *
 * @author vige
 */
public class MockWebScriptRequest implements WebScriptRequest {

	/** The format. */
	private String format;
	/** The form data. */
	private FormData formData;
	/** The runtime. */
	private Runtime runtime;
	/** The service match. */
	private Match serviceMatch;
	private String[] parameterNames;
	private String[] headerNames;
	/** The http servlet request. */
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

	@Override
	/**
	 * Get service match.
	 *
	 * @return the result
	 */
	public Match getServiceMatch() {
		return serviceMatch;
	}

	@Override
	/**
	 * Get server path.
	 *
	 * @return the result
	 */
	public String getServerPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get context path.
	 *
	 * @return the result
	 */
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get service context path.
	 *
	 * @return the result
	 */
	public String getServiceContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get service path.
	 *
	 * @return the result
	 */
	public String getServicePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get u r l.
	 *
	 * @return the result
	 */
	public String getURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get path info.
	 *
	 * @return the result
	 */
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get query string.
	 *
	 * @return the result
	 */
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParameterNames() {
		return parameterNames;
	}

	@Override
	/**
	 * Get parameter.
	 *
	 * @param name the name
	 * @return the result
	 */
	public String getParameter(String name) {
		return httpServletRequest.getParameter(name);
	}

	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getHeaderNames() {
		return headerNames;
	}

	@Override
	/**
	 * Get header.
	 *
	 * @param name the name
	 * @return the result
	 */
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getHeaderValues(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get extension path.
	 *
	 * @return the result
	 */
	public String getExtensionPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get content type.
	 *
	 * @return the result
	 */
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get content.
	 *
	 * @return the result
	 */
	public Content getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Parse content.
	 *
	 * @return the result
	 */
	public Object parseContent() {
		return formData;
	}

	@Override
	/**
	 * Is guest.
	 *
	 * @return the result
	 */
	public boolean isGuest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get format.
	 *
	 * @return the result
	 */
	public String getFormat() {
		return format;
	}

	@Override
	/**
	 * Get format style.
	 *
	 * @return the result
	 */
	public FormatStyle getFormatStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get agent.
	 *
	 * @return the result
	 */
	public String getAgent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get j s o n callback.
	 *
	 * @return the result
	 */
	public String getJSONCallback() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Force success status.
	 *
	 * @return the result
	 */
	public boolean forceSuccessStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get runtime.
	 *
	 * @return the result
	 */
	public Runtime getRuntime() {
		return runtime;
	}

}
