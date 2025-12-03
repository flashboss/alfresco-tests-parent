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
 * Mock implementation of Version for testing purposes.
 * Provides basic version information with configurable properties.
 *
 * @author lucastancapiano
 */
public class MockVersion implements Version {

	/** The version properties map. */
	private Map<String, Serializable> versionProperties;

	/** The frozen state node reference. */
	private NodeRef frozenStateNodeRef;

	/** The versioned node reference. */
	private NodeRef versionedNodeRef;

	/**
	 * Constructs a new MockVersion with empty properties.
	 */
	public MockVersion() {
		versionProperties = new HashMap<String, Serializable>();
	}

	/**
	 * Constructs a new MockVersion with the specified parameters.
	 *
	 * @param frozenStateNodeRef the frozen state node reference
	 * @param versionedNodeRef the versioned node reference
	 * @param versionProperties the version properties
	 */
	public MockVersion(NodeRef frozenStateNodeRef, NodeRef versionedNodeRef,
			Map<String, Serializable> versionProperties) {
		this.frozenStateNodeRef = frozenStateNodeRef;
		this.versionedNodeRef = versionedNodeRef;
		this.versionProperties = versionProperties;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the created date
	 */
	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the creator
	 */
	@Override
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the frozen modified date
	 */
	@Override
	public Date getFrozenModifiedDate() {
		return new Date();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the frozen modifier
	 */
	@Override
	public String getFrozenModifier() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the version label
	 */
	@Override
	public String getVersionLabel() {
		return (String) versionProperties.get(Version2Model.PROP_QNAME_VERSION_LABEL.getLocalName());
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the version type
	 */
	@Override
	public VersionType getVersionType() {
		// TODO Auto-generated method stub
		return null;
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
	 * @return the version properties map
	 */
	@Override
	public Map<String, Serializable> getVersionProperties() {
		return versionProperties;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the property name
	 * @return the version property value
	 */
	@Override
	public Serializable getVersionProperty(String name) {
		return versionProperties.get(name);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the versioned node reference
	 */
	@Override
	public NodeRef getVersionedNodeRef() {
		return versionedNodeRef;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the frozen state node reference
	 */
	@Override
	public NodeRef getFrozenStateNodeRef() {
		return frozenStateNodeRef;
	}

}
