package org.alfresco.mock.test.ws;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ReadListener;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConnection;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpUpgradeHandler;
import jakarta.servlet.http.Part;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.io.FileUtils;

/**
 * Mock implementation of the MockHttpServletRequest class for testing purposes. This class provides
 * a mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author lucastancapiano
 */
public class MockHttpServletRequest implements HttpServletRequest {

	/** The buffer. */
	private byte[] buffer = new byte[0];

	/** The fields. */
	private Map<String, Serializable> fields;

	/** The service registry. */
	private ServiceRegistry serviceRegistry;

	/** The servlet input stream. */
	private ServletInputStream servletInputStream = new ServletInputStream() {

		int counter;

		/** {@inheritDoc} */
		@Override
		public int read() throws IOException {
			int result;
			if (counter >= buffer.length && counter < buffer.length + 1) {
				result = 0;
			} else if (counter >= buffer.length + 1) {
				result = -1;
			} else
				result = buffer[counter];
			counter++;
			return result;
		}

		/** {@inheritDoc} */
		@Override
		public boolean isFinished() {
			// TODO Auto-generated method stub
			return false;
		}

		/** {@inheritDoc} */
		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}

		/** {@inheritDoc} */
		@Override
		public void setReadListener(ReadListener readListener) {
			// TODO Auto-generated method stub
			
		}
	};

	/**
	 * Constructs a new MockHttpServletRequest with the specified fields and service registry.
	 *
	 * @param fields          the fields map
	 * @param serviceRegistry the service registry
	 */
	public MockHttpServletRequest(Map<String, Serializable> fields, ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
		byte[] head = "------WebKitFormBoundaryFUwwPQgv8AD2KZvR".getBytes();
		for (String key : fields.keySet()) {
			byte[] vbytes = new byte[0];
			byte[] kbytes = getField(key, null);
			Serializable value = fields.get(key);
			if (value instanceof NodeRef) {
				NodeService nodeService = serviceRegistry.getNodeService();
				try {
					NodeRef fvalue = (NodeRef) value;
					ContentService contentService = serviceRegistry.getContentService();
					InputStream stream = contentService.getReader(fvalue, ContentModel.PROP_CONTENT)
							.getContentInputStream();
					vbytes = new byte[stream.available()];
					stream.read(vbytes);
					Map<String, String> parameters = new HashMap<String, String>();
					String name = (String) nodeService.getProperty(fvalue, ContentModel.PROP_NAME);
					parameters.put("filename", name);
					kbytes = getField(key, parameters);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (value instanceof File) {
				File file = (File) value;
				try (InputStream stream = new FileInputStream(file)) {
					vbytes = new byte[stream.available()];
					stream.read(vbytes);
					Map<String, String> parameters = new HashMap<String, String>();
					String name = file.getName();
					parameters.put("filename", name);
					kbytes = getField(key, parameters);
					vbytes = FileUtils.readFileToByteArray(file);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else
				vbytes = ((String) value).getBytes();
			buffer = concatAll(buffer, head, new byte[] { 13, 10 }, kbytes, new byte[] { 13, 10 },
					new byte[] { 13, 10 }, vbytes, new byte[] { 13, 10 });
		}
		buffer = concatAll(buffer, head, new byte[] { 45, 45, 13, 10 });
		this.fields = fields;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param env the env
	 * @throws UnsupportedEncodingException if an error occurs
	 */
	@Override
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getContentType() {
		return "multipart/form-data=null, boundary=----WebKitFormBoundaryFUwwPQgv8AD2KZvR";
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 * @throws IOException if an error occurs
	 */
	@Override
	public ServletInputStream getInputStream() throws IOException {
		return servletInputStream;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public String getParameter(String name) {
		NodeService nodeService = serviceRegistry.getNodeService();
		Serializable value = fields.get(name);
		if (value == null)
			return null;
		else if (value instanceof NodeRef)
			return (String) nodeService.getProperty((NodeRef) value, ContentModel.PROP_NAME);
		else
			return value + "";
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 * @throws IOException if an error occurs
	 */
	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the name
	 * @param o    the o
	 */
	@Override
	public void setAttribute(String name, Object o) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the name
	 */
	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param path the path
	 * @return the result
	 */
	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public long getDateHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public int getIntHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param role the role
	 * @return the result
	 */
	@Override
	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param create the create
	 * @return the result
	 */
	@Override
	public HttpSession getSession(boolean create) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Gets the field content disposition.
	 *
	 * @param field      the field
	 * @param parameters the parameters
	 * @return the field bytes
	 */
	private byte[] getField(Serializable field, Map<String, String> parameters) {
		String contentDisposition = "Content-Disposition: form-data; name=\"" + field + "\"";
		if (parameters != null)
			for (String parameter : parameters.keySet())
				contentDisposition += "; " + parameter + "=\"" + parameters.get(parameter) + "\"";
		return contentDisposition.getBytes();
	}

	/**
	 * Concatenates all byte arrays.
	 *
	 * @param first the first array
	 * @param rest  the rest of arrays
	 * @return the concatenated array
	 */
	private byte[] concatAll(byte[] first, byte[]... rest) {
		int totalLength = first.length;
		for (byte[] array : rest) {
			totalLength += array.length;
		}
		byte[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (byte[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public long getContentLengthLong() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 * @throws IllegalStateException if an error occurs
	 */
	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param servletRequest  the servlet request
	 * @param servletResponse the servlet response
	 * @return the result
	 * @throws IllegalStateException if an error occurs
	 */
	@Override
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
			throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public boolean isAsyncStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public AsyncContext getAsyncContext() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public DispatcherType getDispatcherType() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getRequestId() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String getProtocolRequestId() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public ServletConnection getServletConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public String changeSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param response the response
	 * @return the result
	 * @throws IOException      if an error occurs
	 * @throws ServletException if an error occurs
	 */
	@Override
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param username the username
	 * @param password the password
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void login(String username, String password) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void logout() throws ServletException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 * @throws IOException      if an error occurs
	 * @throws ServletException if an error occurs
	 */
	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the name
	 * @return the result
	 * @throws IOException      if an error occurs
	 * @throws ServletException if an error occurs
	 */
	@Override
	public Part getPart(String name) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param <T>          the type
	 * @param handlerClass the handler class
	 * @return the result
	 * @throws IOException      if an error occurs
	 * @throws ServletException if an error occurs
	 */
	@Override
	public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public Enumeration<String> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public Enumeration<String> getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public Enumeration<Locale> getLocales() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public Enumeration<String> getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public Enumeration<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
