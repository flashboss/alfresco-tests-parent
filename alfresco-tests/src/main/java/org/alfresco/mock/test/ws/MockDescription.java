package org.alfresco.mock.test.ws;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import org.springframework.extensions.webscripts.ArgumentTypeDescription;
import org.springframework.extensions.webscripts.Description;
import org.springframework.extensions.webscripts.NegotiatedFormat;
import org.springframework.extensions.webscripts.Path;
import org.springframework.extensions.webscripts.TypeDescription;

/**
 * Mock implementation of MockDescription for testing purposes.
 *
 * @author vige
 */
public class MockDescription implements Description {

  /** The required cache. */
  private RequiredCache requiredCache = new MockRequiredCache();

  /**
   * Get id.
   *
   * @return the result
   */
  @Override
  public String getId() {
    return "test";
  }

  /**
   * Get short name.
   *
   * @return the result
   */
  @Override
  public String getShortName() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get description.
   *
   * @return the result
   */
  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get store path.
   *
   * @return the result
   */
  @Override
  public String getStorePath() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get script path.
   *
   * @return the result
   */
  @Override
  public String getScriptPath() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get package.
   *
   * @return the result
   */
  @Override
  public Path getPackage() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get desc path.
   *
   * @return the result
   */
  @Override
  public String getDescPath() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get desc document.
   *
   * @return the result
   */
  @Override
  public InputStream getDescDocument() throws IOException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get kind.
   *
   * @return the result
   */
  @Override
  public String getKind() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get familys.
   *
   * @return the result
   */
  @Override
  public Set<String> getFamilys() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get required authentication.
   *
   * @return the result
   */
  @Override
  public RequiredAuthentication getRequiredAuthentication() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get run as.
   *
   * @return the result
   */
  @Override
  public String getRunAs() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get required transaction.
   *
   * @return the result
   */
  @Override
  public RequiredTransaction getRequiredTransaction() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get required transaction parameters.
   *
   * @return the result
   */
  @Override
  public RequiredTransactionParameters getRequiredTransactionParameters() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get required cache.
   *
   * @return the result
   */
  @Override
  public RequiredCache getRequiredCache() {
    return requiredCache;
  }

  /**
   * Get method.
   *
   * @return the result
   */
  @Override
  public String getMethod() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String[] getURIs() {
    // TODO Auto-generated method stub
    return null;
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
   * Get default format.
   *
   * @return the result
   */
  @Override
  public String getDefaultFormat() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NegotiatedFormat[] getNegotiatedFormats() {
    // TODO Auto-generated method stub
    return null;
  }

  /** Get extensions. */
  @Override
  public Map<String, Serializable> getExtensions() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get lifecycle.
   *
   * @return the result
   */
  @Override
  public Lifecycle getLifecycle() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get multipart processing.
   *
   * @return the result
   */
  @Override
  public boolean getMultipartProcessing() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Set multipart processing.
   *
   * @param multipartProcessing the multipart processing
   */
  @Override
  public void setMultipartProcessing(boolean multipartProcessing) {
    // TODO Auto-generated method stub

  }

  @Override
  public ArgumentTypeDescription[] getArguments() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TypeDescription[] getRequestTypes() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TypeDescription[] getResponseTypes() {
    // TODO Auto-generated method stub
    return null;
  }
}
