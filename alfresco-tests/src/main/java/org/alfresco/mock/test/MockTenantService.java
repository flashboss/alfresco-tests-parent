package org.alfresco.mock.test;

import org.alfresco.repo.tenant.Tenant;
import org.alfresco.repo.tenant.TenantService;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

public class MockTenantService implements TenantService {

	@Override
	public String getDomainUser(String baseUsername, String tenantDomain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBaseNameUser(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentUserDomain() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public NodeRef getName(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getName(NodeRef inNodeRef, NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreRef getName(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildAssociationRef getName(ChildAssociationRef childAssocRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssociationRef getName(AssociationRef assocRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreRef getName(String username, StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QName getName(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QName getName(NodeRef inNodeRef, QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QName getBaseName(QName name, boolean forceIfNonTenant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getBaseName(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getBaseName(NodeRef nodeRef, boolean forceForNonTenant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreRef getBaseName(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildAssociationRef getBaseName(ChildAssociationRef childAssocRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildAssociationRef getBaseName(ChildAssociationRef childAssocRef, boolean forceIfNonTenant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssociationRef getBaseName(AssociationRef assocRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBaseName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBaseName(String name, boolean forceIfNonTenant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkDomainUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkDomain(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NodeRef getRootNode(NodeService nodeService, SearchService searchService, NamespaceService namespaceService,
			String rootPath, NodeRef rootNodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTenantUser() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTenantUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTenantName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUserDomain(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tenant getTenant(String tenantDomain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDomain(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDomain(String name, boolean checkCurrentDomain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPrimaryDomain(String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
