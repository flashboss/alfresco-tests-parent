package org.alfresco.mock.test.ws;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.Runtime;
import org.springframework.extensions.webscripts.WebScriptResponse;

/**
 * Mock implementation of WebScriptResponse for testing purposes.
 *
 * @author vige
 */
public class MockWebScriptResponse implements WebScriptResponse {
  /** The mock writer. */
  private MockWriter mockWriter = new MockWriter();

  /**
   * Set status.
   *
   * @param status the status
   */
  @Override
  public void setStatus(int status) {
    // TODO Auto-generated method stub

  }

  /**
   * Set header.
   *
   * @param name the name
   * @param value the value
   */
  @Override
  public void setHeader(String name, String value) {
    // TODO Auto-generated method stub

  }

  /**
   * Add header.
   *
   * @param name the name
   * @param value the value
   */
  @Override
  public void addHeader(String name, String value) {
    // TODO Auto-generated method stub

  }

  /**
   * Set content type.
   *
   * @param contentType the content type
   */
  @Override
  public void setContentType(String contentType) {
    // TODO Auto-generated method stub

  }

  /**
   * Set content encoding.
   *
   * @param contentEncoding the content encoding
   */
  @Override
  public void setContentEncoding(String contentEncoding) {
    // TODO Auto-generated method stub

  }

  /**
   * Set cache.
   *
   * @param cache the cache
   */
  @Override
  public void setCache(Cache cache) {
    // TODO Auto-generated method stub

  }

  /**
   * Get writer.
   *
   * @return the writer
   */
  @Override
  public Writer getWriter() throws IOException {
    return mockWriter;
  }

  /**
   * Get output stream.
   *
   * @return the output stream
   */
  @Override
  public OutputStream getOutputStream() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  /** Reset. */
  @Override
  public void reset() {
    // TODO Auto-generated method stub

  }

  /**
   * Reset.
   *
   * @param arg0 the arg0
   */
  @Override
  public void reset(String arg0) {
    // TODO Auto-generated method stub

  }

  /**
   * Encode script url.
   *
   * @param url the url
   * @return the string
   */
  @Override
  public String encodeScriptUrl(String url) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Encode resource url.
   *
   * @param url the url
   * @return the string
   */
  @Override
  public String encodeResourceUrl(String url) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get encode script url function.
   *
   * @param name the name
   * @return the string
   */
  @Override
  public String getEncodeScriptUrlFunction(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get encode resource url function.
   *
   * @param name the name
   * @return the string
   */
  @Override
  public String getEncodeResourceUrlFunction(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get runtime.
   *
   * @return the runtime
   */
  @Override
  public Runtime getRuntime() {
    // TODO Auto-generated method stub
    return null;
  }

  /** Get model. */
  public Map<String, Object> getModel() {
    return mockWriter.getModel();
  }
}
