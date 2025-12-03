package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.site.SiteInfo;
import org.alfresco.service.cmr.site.SiteVisibility;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of the MockSiteInfo class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockSiteInfo implements SiteInfo, Serializable {

	private NodeRef nodeRef;
	private String shortName;

	public MockSiteInfo(NodeRef nodeRef, String shortName) {
		this.nodeRef = nodeRef;
		this.shortName = shortName;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public NodeRef getNodeRef() {
		return nodeRef;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getSitePreset() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getShortName() {
		return shortName;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub

	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void setIsPublic(boolean isPublic) {
		// TODO Auto-generated method stub

	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public boolean getIsPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public SiteVisibility getVisibility() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void setVisibility(SiteVisibility visibility) {
		// TODO Auto-generated method stub

	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Map<QName, Serializable> getCustomProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Serializable getCustomProperty(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void setCreatedDate(Date createdDate) {
		// TODO Auto-generated method stub

	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Date getLastModifiedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		// TODO Auto-generated method stub

	}

}
