package org.alfresco.mock.test.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MockHttpServletRequest implements HttpServletRequest {

	private ServletInputStream servletInputStream = new ServletInputStream() {

		int counter;
		byte[] buffer = { 45, 45, 45, 45, 45, 45, 87, 101, 98, 75, 105, 116, 70, 111, 114, 109, 66, 111, 117, 110, 100,
				97, 114, 121, 70, 85, 119, 119, 80, 81, 103, 118, 56, 65, 68, 50, 75, 90, 118, 82, 13, 10, 67, 111, 110,
				116, 101, 110, 116, 45, 68, 105, 115, 112, 111, 115, 105, 116, 105, 111, 110, 58, 32, 102, 111, 114,
				109, 45, 100, 97, 116, 97, 59, 32, 110, 97, 109, 101, 61, 34, 100, 97, 116, 101, 95, 109, 111, 100, 105,
				102, 105, 101, 100, 34, 13, 10, 13, 10, 50, 48, 50, 48, 45, 48, 54, 45, 49, 57, 13, 10, 45, 45, 45, 45,
				45, 45, 87, 101, 98, 75, 105, 116, 70, 111, 114, 109, 66, 111, 117, 110, 100, 97, 114, 121, 70, 85, 119,
				119, 80, 81, 103, 118, 56, 65, 68, 50, 75, 90, 118, 82, 13, 10, 67, 111, 110, 116, 101, 110, 116, 45,
				68, 105, 115, 112, 111, 115, 105, 116, 105, 111, 110, 58, 32, 102, 111, 114, 109, 45, 100, 97, 116, 97,
				59, 32, 110, 97, 109, 101, 61, 34, 100, 97, 116, 101, 95, 110, 100, 103, 95, 115, 116, 97, 114, 116, 34,
				13, 10, 13, 10, 49, 57, 55, 48, 45, 48, 49, 45, 48, 49, 13, 10, 45, 45, 45, 45, 45, 45, 87, 101, 98, 75,
				105, 116, 70, 111, 114, 109, 66, 111, 117, 110, 100, 97, 114, 121, 70, 85, 119, 119, 80, 81, 103, 118,
				56, 65, 68, 50, 75, 90, 118, 82, 13, 10, 67, 111, 110, 116, 101, 110, 116, 45, 68, 105, 115, 112, 111,
				115, 105, 116, 105, 111, 110, 58, 32, 102, 111, 114, 109, 45, 100, 97, 116, 97, 59, 32, 110, 97, 109,
				101, 61, 34, 100, 97, 116, 101, 95, 110, 100, 103, 95, 101, 110, 100, 34, 13, 10, 13, 10, 50, 48, 50,
				48, 45, 48, 54, 45, 49, 57, 13, 10, 45, 45, 45, 45, 45, 45, 87, 101, 98, 75, 105, 116, 70, 111, 114,
				109, 66, 111, 117, 110, 100, 97, 114, 121, 70, 85, 119, 119, 80, 81, 103, 118, 56, 65, 68, 50, 75, 90,
				118, 82, 13, 10, 67, 111, 110, 116, 101, 110, 116, 45, 68, 105, 115, 112, 111, 115, 105, 116, 105, 111,
				110, 58, 32, 102, 111, 114, 109, 45, 100, 97, 116, 97, 59, 32, 110, 97, 109, 101, 61, 34, 99, 111, 100,
				105, 99, 101, 110, 100, 103, 34, 13, 10, 13, 10, 78, 68, 71, 45, 50, 48, 49, 53, 55, 55, 50, 54, 13, 10,
				45, 45, 45, 45, 45, 45, 87, 101, 98, 75, 105, 116, 70, 111, 114, 109, 66, 111, 117, 110, 100, 97, 114,
				121, 70, 85, 119, 119, 80, 81, 103, 118, 56, 65, 68, 50, 75, 90, 118, 82, 45, 45, 13, 10 };

		@Override
		public int read() throws IOException {
			int result;
			if (counter >= buffer.length && counter < 483) {
				result = 0;
			} else if (counter >= 483) {
				result = -1;
			} else
				result = buffer[counter];
			counter++;
			return result;
		}
	};

	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<?> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getContentType() {
		return "multipart/form-data=null, boundary=----WebKitFormBoundaryFUwwPQgv8AD2KZvR";
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return servletInputStream;
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<?> getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<?, ?> getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(String name, Object o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<?> getLocales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRealPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getDateHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<?> getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<?> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIntHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpSession getSession(boolean create) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRequestedSessionIdFromUrl() {
		// TODO Auto-generated method stub
		return false;
	}

}
