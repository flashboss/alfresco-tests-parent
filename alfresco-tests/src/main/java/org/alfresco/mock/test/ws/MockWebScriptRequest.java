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
 * @author vige
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

	@Override
	public Match getServiceMatch() {
		return serviceMatch;
	}

	@Override
	public String getServerPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServiceContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServicePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParameterNames() {
		return parameterNames;
	}

	@Override
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
	public String getExtensionPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Content getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object parseContent() {
		return formData;
	}

	@Override
	public boolean isGuest() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getFormat() {
		return format;
	}

	@Override
	public FormatStyle getFormatStyle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAgent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJSONCallback() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean forceSuccessStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Runtime getRuntime() {
		return runtime;
	}

}
