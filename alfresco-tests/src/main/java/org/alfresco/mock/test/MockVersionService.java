package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import org.alfresco.repo.version.VersionServicePolicies.CalculateVersionLabelPolicy;
import org.alfresco.service.cmr.repository.AspectMissingException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.version.ReservedVersionNameException;
import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionHistory;
import org.alfresco.service.cmr.version.VersionService;
import org.alfresco.service.namespace.QName;

public class MockVersionService implements VersionService, Serializable {

	@Override
	public StoreRef getVersionStoreReference() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAVersion(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVersioned(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Version createVersion(NodeRef nodeRef, Map<String, Serializable> versionProperties)
			throws ReservedVersionNameException, AspectMissingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Version> createVersion(NodeRef nodeRef, Map<String, Serializable> versionProperties,
			boolean versionChildren) throws ReservedVersionNameException, AspectMissingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Version> createVersion(Collection<NodeRef> nodeRefs, Map<String, Serializable> versionProperties)
			throws ReservedVersionNameException, AspectMissingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VersionHistory getVersionHistory(NodeRef nodeRef) throws AspectMissingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Version getCurrentVersion(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void revert(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revert(NodeRef nodeRef, boolean deep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revert(NodeRef nodeRef, Version version) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revert(NodeRef nodeRef, Version version, boolean deep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NodeRef restore(NodeRef nodeRef, NodeRef parentNodeRef, QName assocTypeQName, QName assocQName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef restore(NodeRef nodeRef, NodeRef parentNodeRef, QName assocTypeQName, QName assocQName,
			boolean deep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVersionHistory(NodeRef nodeRef) throws AspectMissingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVersion(NodeRef nodeRef, Version version) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ensureVersioningEnabled(NodeRef nodeRef, Map<QName, Serializable> versionProperties) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerVersionLabelPolicy(QName typeQName, CalculateVersionLabelPolicy policy) {
		// TODO Auto-generated method stub
		
	}

}
