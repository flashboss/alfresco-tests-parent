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

	private Map<String, Serializable> versionProperties;
	private NodeRef frozenStateNodeRef;
	private NodeRef versionedNodeRef;

	public MockVersion() {
		versionProperties = new HashMap<String, Serializable>();
	}

	public MockVersion(NodeRef frozenStateNodeRef, NodeRef versionedNodeRef,
			Map<String, Serializable> versionProperties) {
		this.frozenStateNodeRef = frozenStateNodeRef;
		this.versionedNodeRef = versionedNodeRef;
		this.versionProperties = versionProperties;
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
	public String getCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Date getFrozenModifiedDate() {
		return new Date();
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getFrozenModifier() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getVersionLabel() {
		return (String) versionProperties.get(Version2Model.PROP_QNAME_VERSION_LABEL.getLocalName());
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public VersionType getVersionType() {
		// TODO Auto-generated method stub
		return null;
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
	public Map<String, Serializable> getVersionProperties() {
		return versionProperties;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Serializable getVersionProperty(String name) {
		return versionProperties.get(name);
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public NodeRef getVersionedNodeRef() {
		return versionedNodeRef;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public NodeRef getFrozenStateNodeRef() {
		return frozenStateNodeRef;
	}

}
