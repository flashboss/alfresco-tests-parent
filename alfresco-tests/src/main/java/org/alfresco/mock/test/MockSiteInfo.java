package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.site.SiteInfo;
import org.alfresco.service.cmr.site.SiteVisibility;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of SiteInfo for testing purposes.
 *
 * @author vige
 */
public class MockSiteInfo implements SiteInfo, Serializable {

  /** The node ref. */
  private NodeRef nodeRef;

  /** The short name. */
  private String shortName;

  /**
   * Constructs a new mock site info.
   *
   * @param nodeRef the node ref
   * @param shortName the short name
   * @return the result
   */
  public MockSiteInfo(NodeRef nodeRef, String shortName) {
    this.nodeRef = nodeRef;
    this.shortName = shortName;
  }

  /**
   * Get node ref.
   *
   * @return the node ref
   */
  @Override
  public NodeRef getNodeRef() {
    return nodeRef;
  }

  /**
   * Get site preset.
   *
   * @return the string
   */
  @Override
  public String getSitePreset() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get short name.
   *
   * @return the string
   */
  @Override
  public String getShortName() {
    return shortName;
  }

  /**
   * Get title.
   *
   * @return the string
   */
  @Override
  public String getTitle() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Set title.
   *
   * @param title the title
   */
  @Override
  public void setTitle(String title) {
    // TODO Auto-generated method stub

  }

  /**
   * Get description.
   *
   * @return the string
   */
  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Set description.
   *
   * @param description the description
   */
  @Override
  public void setDescription(String description) {
    // TODO Auto-generated method stub

  }

  /**
   * Set is public.
   *
   * @param isPublic the is public
   */
  @Override
  public void setIsPublic(boolean isPublic) {
    // TODO Auto-generated method stub

  }

  /**
   * Get is public.
   *
   * @return the boolean
   */
  @Override
  public boolean getIsPublic() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Get visibility.
   *
   * @return the site visibility
   */
  @Override
  public SiteVisibility getVisibility() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Set visibility.
   *
   * @param visibility the visibility
   */
  @Override
  public void setVisibility(SiteVisibility visibility) {
    // TODO Auto-generated method stub

  }

  /** Get custom properties. */
  @Override
  public Map<QName, Serializable> getCustomProperties() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get custom property.
   *
   * @param name the name
   * @return the serializable
   */
  @Override
  public Serializable getCustomProperty(QName name) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get created date.
   *
   * @return the date
   */
  @Override
  public Date getCreatedDate() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Set created date.
   *
   * @param createdDate the created date
   */
  @Override
  public void setCreatedDate(Date createdDate) {
    // TODO Auto-generated method stub

  }

  /**
   * Get last modified date.
   *
   * @return the date
   */
  @Override
  public Date getLastModifiedDate() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Set last modified date.
   *
   * @param lastModifiedDate the last modified date
   */
  @Override
  public void setLastModifiedDate(Date lastModifiedDate) {
    // TODO Auto-generated method stub

  }
}
