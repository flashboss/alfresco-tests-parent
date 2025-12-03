package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionHistory;

/**
 * Mock implementation of VersionHistory for testing purposes.
 * 
 * @author vige
 */
public class MockVersionHistory implements VersionHistory, Serializable {

	/** The versions. */
	private Collection<Version> versions = new ArrayList<Version>();

	@Override
	/**
	 * Get root version.
	 *
	 * @return the version
	 */
	public Version getRootVersion() {
		return versions.toArray(new Version[0])[0];
	}

	@Override
	/**
	 * Get head version.
	 *
	 * @return the version
	 */
	public Version getHeadVersion() {
		return versions.toArray(new Version[0])[versions.size() - 1];
	}

	@Override
	/**
	 * Get all versions.
	 *
	 * @return the collection
	 */
	public Collection<Version> getAllVersions() {
		return versions;
	}

	@Override
	/**
	 * Get predecessor.
	 *
	 * @param version the version
	 * @return the version
	 */
	public Version getPredecessor(Version version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get successors.
	 *
	 * @param version the version
	 * @return the collection
	 */
	public Collection<Version> getSuccessors(Version version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get version.
	 *
	 * @param versionLabel the version label
	 * @return the version
	 */
	public Version getVersion(String versionLabel) {
		// TODO Auto-generated method stub
		return null;
	}

}
