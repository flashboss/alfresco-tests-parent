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
 * Mock implementation of MockVersion for testing purposes.
 *
 * @author vige
 */
public class MockVersion implements Version {

	/** The version properties. */
	private Map<String, Serializable> versionProperties;
	/** The frozen state node ref. */
	private NodeRef frozenStateNodeRef;
	/** The versioned node ref. */
	private NodeRef versionedNodeRef;

	/**
	 * Constructs a new MockVersion.
	 *
	 */
	public MockVersion() {
		versionProperties = new HashMap<String, Serializable>();
	}

	/**
	 * Constructs a new MockVersion.
	 *
	 * @param frozenStateNodeRef the frozen state node ref
	 * @param versionedNodeRef the versioned node ref
	 * @param versionProperties the version properties
	 */
	public MockVersion(NodeRef frozenStateNodeRef, NodeRef versionedNodeRef,
			Map<String, Serializable> versionProperties) {
		this.frozenStateNodeRef = frozenStateNodeRef;
		this.versionedNodeRef = versionedNodeRef;
		this.versionProperties = versionProperties;
	}

	/**
	 * Get created date.
	 *
	 * @return the result
	 */
	@Override
	public Date getCreatedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get creator.
	 *
	 * @return the result
	 */
	@Override
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get frozen modified date.
	 *
	 * @return the result
	 */
	@Override
	public Date getFrozenModifiedDate() {
		return new Date();
	}

	/**
	 * Get frozen modifier.
	 *
	 * @return the result
	 */
	@Override
	public String getFrozenModifier() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get version label.
	 *
	 * @return the result
	 */
	@Override
	public String getVersionLabel() {
		return (String) versionProperties.get(Version2Model.PROP_QNAME_VERSION_LABEL.getLocalName());
	}

	/**
	 * Get version type.
	 *
	 * @return the result
	 */
	@Override
	public VersionType getVersionType() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get description.
	 *
	 * @return the result
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get version properties.
	 *
	 */
	@Override
	public Map<String, Serializable> getVersionProperties() {
		return versionProperties;
	}

	/**
	 * Get version property.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public Serializable getVersionProperty(String name) {
		return versionProperties.get(name);
	}

	/**
	 * Get versioned node ref.
	 *
	 * @return the result
	 */
	@Override
	public NodeRef getVersionedNodeRef() {
		return versionedNodeRef;
	}

	/**
	 * Get frozen state node ref.
	 *
	 * @return the result
	 */
	@Override
	public NodeRef getFrozenStateNodeRef() {
		return frozenStateNodeRef;
	}

}
