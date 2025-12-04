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
 * 
 * @author vige
 */
public class MockVersion implements Version {

	private Map<String, Serializable> versionProperties;
 /** The frozen state node ref. */
	private NodeRef frozenStateNodeRef;
 /** The versioned node ref. */
	private NodeRef versionedNodeRef;

 /**
 * Constructs a new mock version.
 *
 * @return the result
 */
	public MockVersion() {
  /**
  * Constructs a new mock version.
  *
  * @return the result
  */
		versionProperties = new HashMap<String, Serializable>();
	}

	public MockVersion(NodeRef frozenStateNodeRef, NodeRef versionedNodeRef,
			Map<String, Serializable> versionProperties) {
		this.frozenStateNodeRef = frozenStateNodeRef;
		this.versionedNodeRef = versionedNodeRef;
		this.versionProperties = versionProperties;
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
 * Get creator.
 *
 * @return the string
 */
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get frozen modified date.
 *
 * @return the date
 */
	public Date getFrozenModifiedDate() {
  /**
  * Get frozen modified date.
  *
  * @return the date
  */
		return new Date();
	}

	@Override
 /**
 * Get frozen modifier.
 *
 * @return the string
 */
	public String getFrozenModifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get version label.
 *
 * @return the string
 */
	public String getVersionLabel() {
  /**
  * Get version label.
  *
  * @return the string
  */
		return (String) versionProperties.get(Version2Model.PROP_QNAME_VERSION_LABEL.getLocalName());
	}

	@Override
 /**
 * Get version type.
 *
 * @return the version type
 */
	public VersionType getVersionType() {
		// TODO Auto-generated method stub
		return null;
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
 /** Get version properties. */
	public Map<String, Serializable> getVersionProperties() {
		return versionProperties;
	}

	@Override
 /**
 * Get version property.
 *
 * @param name the name
 * @return the serializable
 */
	public Serializable getVersionProperty(String name) {
  /**
  * Get version property.
  *
  * @param name the name
  * @return the serializable
  */
		return versionProperties.get(name);
	}

	@Override
 /**
 * Get versioned node ref.
 *
 * @return the node ref
 */
	public NodeRef getVersionedNodeRef() {
		return versionedNodeRef;
	}

	@Override
 /**
 * Get frozen state node ref.
 *
 * @return the node ref
 */
	public NodeRef getFrozenStateNodeRef() {
		return frozenStateNodeRef;
	}

}
