package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.site.SiteInfo;
import org.alfresco.service.cmr.site.SiteVisibility;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of MockSiteInfo for testing purposes.
 *
 * @author vige
 */
public class MockSiteInfo implements SiteInfo, Serializable {

	/** The node ref. */
	private NodeRef nodeRef;
	/** The short name. */
	private String shortName;

	/**
	 * Constructs a new MockSiteInfo.
	 *
	 * @param nodeRef the node ref
	 * @param shortName the short name
	 */
	public MockSiteInfo(NodeRef nodeRef, String shortName) {
		this.nodeRef = nodeRef;
		this.shortName = shortName;
	}

	@Override
	/**
	 * Get node ref.
	 *
	 * @return the result
	 */
	public NodeRef getNodeRef() {
		return nodeRef;
	}

	@Override
	/**
	 * Get site preset.
	 *
	 * @return the result
	 */
	public String getSitePreset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get short name.
	 *
	 * @return the result
	 */
	public String getShortName() {
		return shortName;
	}

	@Override
	/**
	 * Get title.
	 *
	 * @return the result
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
	 * @return the result
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
	 * @return the result
	 */
	public boolean getIsPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get visibility.
	 *
	 * @return the result
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
	/**
	 * Get custom properties.
	 *
	 */
	public Map<QName, Serializable> getCustomProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get custom property.
	 *
	 * @param name the name
	 * @return the result
	 */
	public Serializable getCustomProperty(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

}
