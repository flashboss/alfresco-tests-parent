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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.io.FileUtils;

/**
 * Mock implementation of HttpServletRequest for testing purposes.
 * 
 * @author vige
 */
public class MockHttpServletRequest implements HttpServletRequest {

	private byte[] buffer = new byte[0];
	/** The fields. */
	private Map<String, Serializable> fields;
	/** The service registry. */
	private ServiceRegistry serviceRegistry;

	/** The servlet input stream. */
	private ServletInputStream servletInputStream = new ServletInputStream() {

		int counter;	/**
		 * Read.
		 *
		 * @return the int
		 */


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
	};

	/**
	 * Constructs a new mock http servlet request.
	 *
	 * @param fields the fields
	 * @param serviceRegistry the service registry
	 * @return the result
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
					/** The name. */
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
					/** The name. */
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
	}	/**
	 * Get attribute.
	 *
	 * @param name the name
	 * @return the object
	 */


	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get attribute names.
	 *
	 * @return the enumeration
	 */


	@Override
	public Enumeration<?> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get character encoding.
	 *
	 * @return the string
	 */


	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Set character encoding.
	 *
	 * @param env the env
	 */


	@Override
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

	}	/**
	 * Get content length.
	 *
	 * @return the int
	 */


	@Override
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}	/**
	 * Get content type.
	 *
	 * @return the string
	 */


	@Override
	public String getContentType() {
		return "multipart/form-data=null, boundary=----WebKitFormBoundaryFUwwPQgv8AD2KZvR";
	}	/**
	 * Get input stream.
	 *
	 * @return the servlet input stream
	 */


	@Override
	public ServletInputStream getInputStream() throws IOException {
		return servletInputStream;
	}	/**
	 * Get parameter.
	 *
	 * @param name the name
	 * @return the string
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
	}	/**
	 * Get parameter names.
	 *
	 * @return the enumeration
	 */


	@Override
	public Enumeration<?> getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get parameter values.
	 *
	 * @param name the name
	 * @return the string[]
	 */


	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get parameter map.
	 *
	 */


	@Override
	public Map<?, ?> getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get protocol.
	 *
	 * @return the string
	 */


	@Override
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get scheme.
	 *
	 * @return the string
	 */


	@Override
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get server name.
	 *
	 * @return the string
	 */


	@Override
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get server port.
	 *
	 * @return the int
	 */


	@Override
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}	/**
	 * Get reader.
	 *
	 * @return the buffered reader
	 */


	@Override
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get remote addr.
	 *
	 * @return the string
	 */


	@Override
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get remote host.
	 *
	 * @return the string
	 */


	@Override
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Set attribute.
	 *
	 * @param name the name
	 * @param o the o
	 */


	@Override
	public void setAttribute(String name, Object o) {
		// TODO Auto-generated method stub

	}	/**
	 * Remove attribute.
	 *
	 * @param name the name
	 */


	@Override
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub

	}	/**
	 * Get locale.
	 *
	 * @return the locale
	 */


	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get locales.
	 *
	 * @return the enumeration
	 */


	@Override
	public Enumeration<?> getLocales() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Is secure.
	 *
	 * @return the boolean
	 */


	@Override
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}	/**
	 * Get request dispatcher.
	 *
	 * @param path the path
	 * @return the request dispatcher
	 */


	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get real path.
	 *
	 * @param path the path
	 * @return the string
	 */


	@Override
	public String getRealPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get remote port.
	 *
	 * @return the int
	 */


	@Override
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}	/**
	 * Get local name.
	 *
	 * @return the string
	 */


	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get local addr.
	 *
	 * @return the string
	 */


	@Override
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get local port.
	 *
	 * @return the int
	 */


	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}	/**
	 * Get auth type.
	 *
	 * @return the string
	 */


	@Override
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get cookies.
	 *
	 * @return the cookie[]
	 */


	@Override
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get date header.
	 *
	 * @param name the name
	 * @return the long
	 */


	@Override
	public long getDateHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}	/**
	 * Get header.
	 *
	 * @param name the name
	 * @return the string
	 */


	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get headers.
	 *
	 * @param name the name
	 * @return the enumeration
	 */


	@Override
	public Enumeration<?> getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get header names.
	 *
	 * @return the enumeration
	 */


	@Override
	public Enumeration<?> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get int header.
	 *
	 * @param name the name
	 * @return the int
	 */


	@Override
	public int getIntHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}	/**
	 * Get method.
	 *
	 * @return the string
	 */


	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get path info.
	 *
	 * @return the string
	 */


	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get path translated.
	 *
	 * @return the string
	 */


	@Override
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get context path.
	 *
	 * @return the string
	 */


	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get query string.
	 *
	 * @return the string
	 */


	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get remote user.
	 *
	 * @return the string
	 */


	@Override
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Is user in role.
	 *
	 * @param role the role
	 * @return the boolean
	 */


	@Override
	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}	/**
	 * Get user principal.
	 *
	 * @return the principal
	 */


	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get requested session id.
	 *
	 * @return the string
	 */


	@Override
	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get request u r i.
	 *
	 * @return the string
	 */


	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get request u r l.
	 *
	 * @return the string buffer
	 */


	@Override
	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get servlet path.
	 *
	 * @return the string
	 */


	@Override
	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get session.
	 *
	 * @param create the create
	 * @return the http session
	 */


	@Override
	public HttpSession getSession(boolean create) {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get session.
	 *
	 * @return the http session
	 */


	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Is requested session id valid.
	 *
	 * @return the boolean
	 */


	@Override
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}	/**
	 * Is requested session id from cookie.
	 *
	 * @return the boolean
	 */


	@Override
	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}	/**
	 * Is requested session id from u r l.
	 *
	 * @return the boolean
	 */


	@Override
	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}	/**
	 * Is requested session id from url.
	 *
	 * @return the boolean
	 */


	@Override
	public boolean isRequestedSessionIdFromUrl() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Get field.
	 *
	 * @param field the field
	 * @param parameters the parameters
	 * @return the byte[]
	 */
	private byte[] getField(Serializable field, Map<String, String> parameters) {

	/** The content disposition. */
		String contentDisposition = "Content-Disposition: form-data; name=\"" + field + "\"";
		if (parameters != null)
			for (String parameter : parameters.keySet())
				contentDisposition += "; " + parameter + "=\"" + parameters.get(parameter) + "\"";
		return contentDisposition.getBytes();
	}

	/**
	 * Concat all.
	 *
	 * @param first the first
	 * @param rest the rest
	 * @return the byte[]
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

}
