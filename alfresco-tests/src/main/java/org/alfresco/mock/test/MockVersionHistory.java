package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionHistory;

public class MockVersionHistory implements VersionHistory, Serializable {

	private Collection<Version> versions = new ArrayList<Version>();
	{
		versions.add(new MockVersion());
	}

	@Override
	public Version getRootVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Version getHeadVersion() {
		// TODO Auto-generated method stub
		return null;
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
