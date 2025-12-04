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
	private Map<String, Serializable> fields;
 /** The service registry. */
	private ServiceRegistry serviceRegistry;

	private ServletInputStream servletInputStream = new ServletInputStream() {

		int counter;

		@Override
  /**
  * Read.
  *
  * @return the int
  */
		public int read() throws IOException {
			int result;
   /**
   * Read.
   *
   * @return the int
   */
			if (counter >= buffer.length && counter < buffer.length + 1) {
				result = 0;
   /**
   * Read.
   *
   * @return the int
   */
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
  /**
  * Constructs a new mock http servlet request.
  *
  * @param fields the fields
  * @param serviceRegistry the service registry
  * @return the result
  */
		byte[] head = "------WebKitFormBoundaryFUwwPQgv8AD2KZvR".getBytes();
  /**
  * Constructs a new mock http servlet request.
  *
  * @param fields the fields
  * @param serviceRegistry the service registry
  * @return the result
  */
		for (String key : fields.keySet()) {
			byte[] vbytes = new byte[0];
			byte[] kbytes = getField(key, null);
			Serializable value = fields.get(key);
			if (value instanceof NodeRef) {
    /**
    * Get parameter.
    *
    * @param name the name
    * @return the string
    */
				NodeService nodeService = serviceRegistry.getNodeService();
				try {
					NodeRef fvalue = (NodeRef) value;
					ContentService contentService = serviceRegistry.getContentService();
					InputStream stream = contentService.getReader(fvalue, ContentModel.PROP_CONTENT)
							.getContentInputStream();
     /** The name. */
					vbytes = new byte[stream.available()];
					stream.read(vbytes);
					Map<String, String> parameters = new HashMap<String, String>();
     /** The name. */
					String name = (String) nodeService.getProperty(fvalue, ContentModel.PROP_NAME);
					parameters.put("filename", name);
     /** The name. */
					kbytes = getField(key, parameters);
    /** The name. */
				} catch (Exception e) {
					e.printStackTrace();
				}
   /** The name. */
			} else if (value instanceof File) {
    /** The name. */
				File file = (File) value;
    /** The name. */
				try (InputStream stream = new FileInputStream(file)) {
     /** The name. */
					vbytes = new byte[stream.available()];
					stream.read(vbytes);
					Map<String, String> parameters = new HashMap<String, String>();
     /** The name. */
					String name = file.getName();
					parameters.put("filename", name);
     /** The name. */
					kbytes = getField(key, parameters);
     /** The name. */
					vbytes = FileUtils.readFileToByteArray(file);
    /** The name. */
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

	@Override
 /**
 * Get attribute.
 *
 * @param name the name
 * @return the object
 */
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get attribute names.
 *
 * @return the enumeration
 */
	public Enumeration<?> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get character encoding.
 *
 * @return the string
 */
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Set character encoding.
 *
 * @param env the env
 */
	public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Get content length.
 *
 * @return the int
 */
	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
 /**
 * Get content type.
 *
 * @return the string
 */
	public String getContentType() {
		return "multipart/form-data=null, boundary=----WebKitFormBoundaryFUwwPQgv8AD2KZvR";
	}

	@Override
 /**
 * Get input stream.
 *
 * @return the servlet input stream
 */
	public ServletInputStream getInputStream() throws IOException {
		return servletInputStream;
	}

	@Override
 /**
 * Get parameter.
 *
 * @param name the name
 * @return the string
 */
	public String getParameter(String name) {
  /**
  * Get parameter.
  *
  * @param name the name
  * @return the string
  */
		NodeService nodeService = serviceRegistry.getNodeService();
  /**
  * Get parameter.
  *
  * @param name the name
  * @return the string
  */
		Serializable value = fields.get(name);
		if (value == null)
			return null;
		else if (value instanceof NodeRef)
			return (String) nodeService.getProperty((NodeRef) value, ContentModel.PROP_NAME);
		else
			return value + "";
	}

	@Override
 /**
 * Get parameter names.
 *
 * @return the enumeration
 */
	public Enumeration<?> getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get parameter values.
 *
 * @param name the name
 * @return the string[]
 */
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /** Get parameter map. */
	public Map<?, ?> getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get protocol.
 *
 * @return the string
 */
	public String getProtocol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get scheme.
 *
 * @return the string
 */
	public String getScheme() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get server name.
 *
 * @return the string
 */
	public String getServerName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get server port.
 *
 * @return the int
 */
	public int getServerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
 /**
 * Get reader.
 *
 * @return the buffered reader
 */
	public BufferedReader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get remote addr.
 *
 * @return the string
 */
	public String getRemoteAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get remote host.
 *
 * @return the string
 */
	public String getRemoteHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Set attribute.
 *
 * @param name the name
 * @param o the o
 */
	public void setAttribute(String name, Object o) {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Remove attribute.
 *
 * @param name the name
 */
	public void removeAttribute(String name) {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Get locale.
 *
 * @return the locale
 */
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get locales.
 *
 * @return the enumeration
 */
	public Enumeration<?> getLocales() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Is secure.
 *
 * @return the boolean
 */
	public boolean isSecure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Get request dispatcher.
 *
 * @param path the path
 * @return the request dispatcher
 */
	public RequestDispatcher getRequestDispatcher(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get real path.
 *
 * @param path the path
 * @return the string
 */
	public String getRealPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get remote port.
 *
 * @return the int
 */
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
 /**
 * Get local name.
 *
 * @return the string
 */
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get local addr.
 *
 * @return the string
 */
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get local port.
 *
 * @return the int
 */
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
 /**
 * Get auth type.
 *
 * @return the string
 */
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get cookies.
 *
 * @return the cookie[]
 */
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get date header.
 *
 * @param name the name
 * @return the long
 */
	public long getDateHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
 /**
 * Get header.
 *
 * @param name the name
 * @return the string
 */
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get headers.
 *
 * @param name the name
 * @return the enumeration
 */
	public Enumeration<?> getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get header names.
 *
 * @return the enumeration
 */
	public Enumeration<?> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get int header.
 *
 * @param name the name
 * @return the int
 */
	public int getIntHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
 /**
 * Get method.
 *
 * @return the string
 */
	public String getMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get path info.
 *
 * @return the string
 */
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get path translated.
 *
 * @return the string
 */
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get context path.
 *
 * @return the string
 */
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get query string.
 *
 * @return the string
 */
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get remote user.
 *
 * @return the string
 */
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Is user in role.
 *
 * @param role the role
 * @return the boolean
 */
	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Get user principal.
 *
 * @return the principal
 */
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get requested session id.
 *
 * @return the string
 */
	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get request u r i.
 *
 * @return the string
 */
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get request u r l.
 *
 * @return the string buffer
 */
	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get servlet path.
 *
 * @return the string
 */
	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get session.
 *
 * @param create the create
 * @return the http session
 */
	public HttpSession getSession(boolean create) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get session.
 *
 * @return the http session
 */
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Is requested session id valid.
 *
 * @return the boolean
 */
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Is requested session id from cookie.
 *
 * @return the boolean
 */
	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Is requested session id from u r l.
 *
 * @return the boolean
 */
	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Is requested session id from url.
 *
 * @return the boolean
 */
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
		String contentDisposition = "Content-Disposition: form-data; name=\"" + field + "\"";
  /** The content disposition. */
		if (parameters != null)
   /** The content disposition. */
			for (String parameter : parameters.keySet())
    /** The content disposition. */
				contentDisposition += "; " + parameter + "=\"" + parameters.get(parameter) + "\"";
  /** The content disposition. */
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
  /**
  * Concat all.
  *
  * @param first the first
  * @param rest the rest
  * @return the byte[]
  */
		for (byte[] array : rest) {
			totalLength += array.length;
		}
		byte[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
  /**
  * Concat all.
  *
  * @param first the first
  * @param rest the rest
  * @return the byte[]
  */
		for (byte[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

}
