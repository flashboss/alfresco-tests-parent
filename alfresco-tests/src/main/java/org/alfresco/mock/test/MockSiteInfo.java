package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.site.SiteInfo;
import org.alfresco.service.cmr.site.SiteVisibility;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of SiteInfo for testing purposes.
 * Provides basic site information with configurable node reference and short name.
 *
 * @author lucastancapiano
 */
public class MockSiteInfo implements SiteInfo, Serializable {

	/** The node reference. */
	private NodeRef nodeRef;

	/** The site short name. */
	private String shortName;

	/**
	 * Constructs a new MockSiteInfo with the specified node reference and short name.
	 *
	 * @param nodeRef the node reference
	 * @param shortName the site short name
	 */
	public MockSiteInfo(NodeRef nodeRef, String shortName) {
		this.nodeRef = nodeRef;
		this.shortName = shortName;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the node reference
	 */
	@Override
	public NodeRef getNodeRef() {
		return nodeRef;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the site preset
	 */
	@Override
	public String getSitePreset() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the site short name
	 */
	@Override
	public String getShortName() {
		return shortName;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the title
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param title the title to set
	 */
	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the description
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param description the description to set
	 */
	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param isPublic whether the site is public
	 */
	@Override
	public void setIsPublic(boolean isPublic) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if public, false otherwise
	 */
	@Override
	public boolean getIsPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the site visibility
	 */
	@Override
	public SiteVisibility getVisibility() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param visibility the visibility to set
	 */
	@Override
	public void setVisibility(SiteVisibility visibility) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the custom properties map
	 */
	@Override
	public Map<QName, Serializable> getCustomProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the property QName
	 * @return the custom property value
	 */
	@Override
	public Serializable getCustomProperty(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

}
