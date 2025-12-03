package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.site.SiteInfo;
import org.alfresco.service.cmr.site.SiteVisibility;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of the MockSiteInfo class for testing purposes. This class provides a mock
 * implementation that allows unit and integration tests to run without requiring a full Alfresco
 * server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockSiteInfo implements SiteInfo, Serializable {

  /** The node ref. */
  private NodeRef nodeRef;

  /** The short name. */
  private String shortName;

  /**
   * Constructs a new MockSiteInfo instance.
   *
   * @param nodeRef the nodeRef
   * @param shortName the shortName
   */
  public MockSiteInfo(NodeRef nodeRef, String shortName) {
    this.nodeRef = nodeRef;
    this.shortName = shortName;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public NodeRef getNodeRef() {
    return nodeRef;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getSitePreset() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getShortName() {
    return shortName;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getTitle() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param title the title
   */
  @Override
  public void setTitle(String title) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param description the description
   */
  @Override
  public void setDescription(String description) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param isPublic the isPublic
   */
  @Override
  public void setIsPublic(boolean isPublic) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean getIsPublic() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public SiteVisibility getVisibility() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param visibility the visibility
   */
  @Override
  public void setVisibility(SiteVisibility visibility) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Map<QName, Serializable> getCustomProperties() {
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
  public Serializable getCustomProperty(QName name) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Date getCreatedDate() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param createdDate the createdDate
   */
  @Override
  public void setCreatedDate(Date createdDate) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Date getLastModifiedDate() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param lastModifiedDate the lastModifiedDate
   */
  @Override
  public void setLastModifiedDate(Date lastModifiedDate) {
    // TODO Auto-generated method stub

  }
}
