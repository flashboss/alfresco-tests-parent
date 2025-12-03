package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionHistory;

/**
 * Mock implementation of VersionHistory for testing purposes.
 * Provides a simple collection-based version history.
 *
 * @author lucastancapiano
 */
public class MockVersionHistory implements VersionHistory, Serializable {

	/** The collection of versions. */
	private Collection<Version> versions = new ArrayList<Version>();

	/**
	 * {@inheritDoc}
	 *
	 * @return the root version (first in collection)
	 */
	@Override
	public Version getRootVersion() {
		return versions.toArray(new Version[0])[0];
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the head version (last in collection)
	 */
	@Override
	public Version getHeadVersion() {
		return versions.toArray(new Version[0])[versions.size() - 1];
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return all versions in the history
	 */
	@Override
	public Collection<Version> getAllVersions() {
		return versions;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param version the version to find predecessor for
	 * @return the predecessor version
	 */
	@Override
	public Version getPredecessor(Version version) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param version the version to find successors for
	 * @return the successor versions
	 */
	@Override
	public Collection<Version> getSuccessors(Version version) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param versionLabel the version label to find
	 * @return the version with the specified label
	 */
	@Override
	public Version getVersion(String versionLabel) {
		// TODO Auto-generated method stub
		return null;
	}

}
