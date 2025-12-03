package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.site.SiteInfo;
import org.alfresco.service.cmr.site.SiteVisibility;
import org.alfresco.service.namespace.QName;

/**
 * @author vige
 */
public class MockSiteInfo implements SiteInfo, Serializable {

	private NodeRef nodeRef;
	private String shortName;

	public MockSiteInfo(NodeRef nodeRef, String shortName) {
		this.nodeRef = nodeRef;
		this.shortName = shortName;
	}

	@Override
	public NodeRef getNodeRef() {
		return nodeRef;
	}

	@Override
	public String getSitePreset() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getShortName() {
		return shortName;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIsPublic(boolean isPublic) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getIsPublic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SiteVisibility getVisibility() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVisibility(SiteVisibility visibility) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<QName, Serializable> getCustomProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable getCustomProperty(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		// TODO Auto-generated method stub

	}

	@Override
	public Date getLastModifiedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		// TODO Auto-generated method stub

	}

}
