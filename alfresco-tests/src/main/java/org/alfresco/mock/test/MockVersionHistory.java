package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionHistory;

/**
 * Mock implementation of the MockVersionHistory class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockVersionHistory implements VersionHistory, Serializable {

	private Collection<Version> versions = new ArrayList<Version>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Version getRootVersion() {
		return versions.toArray(new Version[0])[0];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Version getHeadVersion() {
		return versions.toArray(new Version[0])[versions.size() - 1];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Version> getAllVersions() {
		return versions;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Version getPredecessor(Version version) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<Version> getSuccessors(Version version) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Version getVersion(String versionLabel) {
		// TODO Auto-generated method stub
		return null;
	}

}
