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

	@Override
 /**
 * Get node ref.
 *
 * @return the node ref
 */
	public NodeRef getNodeRef() {
		return nodeRef;
	}

	@Override
 /**
 * Get site preset.
 *
 * @return the string
 */
	public String getSitePreset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get short name.
 *
 * @return the string
 */
	public String getShortName() {
		return shortName;
	}

	@Override
 /**
 * Get title.
 *
 * @return the string
 */
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Set title.
 *
 * @param title the title
 */
	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Get description.
 *
 * @return the string
 */
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Set description.
 *
 * @param description the description
 */
	public void setDescription(String description) {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Set is public.
 *
 * @param isPublic the is public
 */
	public void setIsPublic(boolean isPublic) {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Get is public.
 *
 * @return the boolean
 */
	public boolean getIsPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Get visibility.
 *
 * @return the site visibility
 */
	public SiteVisibility getVisibility() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Set visibility.
 *
 * @param visibility the visibility
 */
	public void setVisibility(SiteVisibility visibility) {
		// TODO Auto-generated method stub

	}

	@Override
 /** Get custom properties. */
	public Map<QName, Serializable> getCustomProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get custom property.
 *
 * @param name the name
 * @return the serializable
 */
	public Serializable getCustomProperty(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get created date.
 *
 * @return the date
 */
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Set created date.
 *
 * @param createdDate the created date
 */
	public void setCreatedDate(Date createdDate) {
		// TODO Auto-generated method stub

	}

	@Override
 /**
 * Get last modified date.
 *
 * @return the date
 */
	public Date getLastModifiedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Set last modified date.
 *
 * @param lastModifiedDate the last modified date
 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		// TODO Auto-generated method stub

	}

}
