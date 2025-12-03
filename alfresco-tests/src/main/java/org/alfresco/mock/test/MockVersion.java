package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.repo.version.Version2Model;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionType;

/**
* Mock implementation of the MockVersion class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockVersion implements Version {

/**
* The version properties map.
 */
	private Map<String, Serializable> versionProperties;
/**
* The frozen state node ref.
 */
	private NodeRef frozenStateNodeRef;
/**
* The versioned node ref.
 */
	private NodeRef versionedNodeRef;

/**
* Constructs a new MockVersion instance.
 */
	public MockVersion() {
		versionProperties = new HashMap<String, Serializable>();
	}

/**
* Constructs a new MockVersion instance.
*
* @param frozenStateNodeRef the frozen state node reference
* @param versionedNodeRef the versioned node reference
* @param versionProperties the version properties map
 */
	public MockVersion(NodeRef frozenStateNodeRef, NodeRef versionedNodeRef,
			Map<String, Serializable> versionProperties) {
		this.frozenStateNodeRef = frozenStateNodeRef;
		this.versionedNodeRef = versionedNodeRef;
		this.versionProperties = versionProperties;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Date getFrozenModifiedDate() {
		return new Date();
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getFrozenModifier() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getVersionLabel() {
		return (String) versionProperties.get(Version2Model.PROP_QNAME_VERSION_LABEL.getLocalName());
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public VersionType getVersionType() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Map<String, Serializable> getVersionProperties() {
		return versionProperties;
	}

/**
* {@inheritDoc}
* @param name the name
* @return the result
 */
	@Override
	public Serializable getVersionProperty(String name) {
		return versionProperties.get(name);
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public NodeRef getVersionedNodeRef() {
		return versionedNodeRef;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public NodeRef getFrozenStateNodeRef() {
		return frozenStateNodeRef;
	}

}
