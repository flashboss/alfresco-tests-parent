package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionType;

public class MockVersion implements Version {

	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getFrozenModifiedDate() {
		return new Date();
	}

	@Override
	public String getFrozenModifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVersionLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VersionType getVersionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Serializable> getVersionProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable getVersionProperty(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getVersionedNodeRef() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getFrozenStateNodeRef() {
		// TODO Auto-generated method stub
		return null;
	}

}
