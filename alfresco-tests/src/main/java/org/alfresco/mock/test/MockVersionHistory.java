package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionHistory;

/**
 * Mock implementation of MockVersionHistory for testing purposes.
 *
 * @author lucastancapiano
 */
public class MockVersionHistory implements VersionHistory, Serializable {

	private Collection<Version> versions = new ArrayList<Version>();

	@Override
	public Version getRootVersion() {
		return versions.toArray(new Version[0])[0];
	}

	@Override
	public Version getHeadVersion() {
		return versions.toArray(new Version[0])[versions.size() - 1];
	}

	@Override
	public Collection<Version> getAllVersions() {
		return versions;
	}

	@Override
	public Version getPredecessor(Version version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Version> getSuccessors(Version version) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Version getVersion(String versionLabel) {
		// TODO Auto-generated method stub
		return null;
	}

}
