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

  /**
   * Constructs a new MockWebScriptRequest.
   *
   * @param format the format
   * @param templateVars the template variables
   * @param webScript the web script
   * @param requestFields the request fields
   * @param serviceRegistry the service registry
   */
  public MockWebScriptRequest(
      String format,
      Map<String, String> templateVars,
      WebScript webScript,
      Map<String, Serializable> requestFields,
      ServiceRegistry serviceRegistry) {
    this.format = format;
    this.runtime = new MockRuntime();
    httpServletRequest = new MockHttpServletRequest(requestFields, serviceRegistry);
    formData = new FormData(httpServletRequest);
    if (templateVars == null) templateVars = new HashMap<String, String>();
    serviceMatch = new Match(null, templateVars, null, webScript);
    parameterNames = new String[0];
    headerNames = new String[0];
  }

  /**
   * Get service match.
   *
   * @return the result
   */
  @Override
  public Match getServiceMatch() {
    return serviceMatch;
  }

  /**
   * Get server path.
   *
   * @return the result
   */
  @Override
  public String getServerPath() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get context path.
   *
   * @return the result
   */
  @Override
  public String getContextPath() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get service context path.
   *
   * @return the result
   */
  @Override
  public String getServiceContextPath() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get service path.
   *
   * @return the result
   */
  @Override
  public String getServicePath() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get u r l.
   *
   * @return the result
   */
  @Override
  public String getURL() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get path info.
   *
   * @return the result
   */
  @Override
  public String getPathInfo() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get query string.
   *
   * @return the result
   */
  @Override
  public String getQueryString() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String[] getParameterNames() {
    return parameterNames;
  }

  /**
   * Get parameter.
   *
   * @param name the name
   * @return the result
   */
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

  /**
   * Get header.
   *
   * @param name the name
   * @return the result
   */
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

  /**
   * Get extension path.
   *
   * @return the result
   */
  @Override
  public String getExtensionPath() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get content type.
   *
   * @return the result
   */
  @Override
  public String getContentType() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get content.
   *
   * @return the result
   */
  @Override
  public Content getContent() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Parse content.
   *
   * @return the result
   */
  @Override
  public Object parseContent() {
    return formData;
  }

  /**
   * Is guest.
   *
   * @return the result
   */
  @Override
  public boolean isGuest() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Get format.
   *
   * @return the result
   */
  @Override
  public String getFormat() {
    return format;
  }

  /**
   * Get format style.
   *
   * @return the result
   */
  @Override
  public FormatStyle getFormatStyle() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get agent.
   *
   * @return the result
   */
  @Override
  public String getAgent() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get j s o n callback.
   *
   * @return the result
   */
  @Override
  public String getJSONCallback() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Force success status.
   *
   * @return the result
   */
  @Override
  public boolean forceSuccessStatus() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Get runtime.
   *
   * @return the result
   */
  @Override
  public Runtime getRuntime() {
    return runtime;
  }
}
