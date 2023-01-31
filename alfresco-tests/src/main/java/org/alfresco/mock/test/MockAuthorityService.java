package org.alfresco.mock.test;

import static org.alfresco.model.ContentModel.ASSOC_CONTAINS;
import static org.alfresco.model.ContentModel.TYPE_CONTENT;
import static org.alfresco.model.ContentModel.TYPE_FOLDER;
import static org.alfresco.service.cmr.repository.StoreRef.PROTOCOL_WORKSPACE;
import static org.alfresco.service.namespace.NamespaceService.CONTENT_MODEL_1_0_URI;
import static org.alfresco.service.namespace.QName.createQName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.security.AuthorityService;
import org.alfresco.service.cmr.security.AuthorityType;
import org.alfresco.service.namespace.QName;
import org.springframework.beans.factory.annotation.Autowired;

public class MockAuthorityService implements AuthorityService, Serializable {

	private final static String GROUP_PREFIX = "GROUP_";

	@Autowired
	private NodeService nodeService;

	private Map<String, NodeRef> authorityNodes = new HashMap<String, NodeRef>();

	@Override
	public boolean hasAdminAuthority() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAdminAuthority(String authorityName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasGuestAuthority() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGuestAuthority(String authorityName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<String> getAuthorities() {
		return authorityNodes.keySet();
	}

	@Override
	public Set<String> getAuthoritiesForUser(String userName) {
		return getAuthorities();
	}

	@Override
	public Set<String> getAllAuthorities(AuthorityType type) {
		return getAuthorities();
	}

	@Override
	public PagingResults<String> getAuthorities(AuthorityType type, String zoneName, String displayNameFilter,
			boolean sortByDisplayName, boolean sortAscending, PagingRequest pagingRequest) {
		List<String> mainList = new ArrayList<String>();
		mainList.addAll(authorityNodes.keySet());
		PagingResults<String> authorities = new MockPagingResults<String>(mainList);
		return authorities;
	}

	@Override
	public Set<String> getAllRootAuthorities(AuthorityType type) {
		return getAuthorities();
	}

	@Override
	public String createAuthority(AuthorityType type, String shortName) {
		return createAuthority(type, shortName, null, null);
	}

	@Override
	public String createAuthority(AuthorityType type, String shortName, String authorityDisplayName,
			Set<String> authorityZones) {
		String name = getName(null, shortName);
		NodeRef root = nodeService.getRootNode(new StoreRef(PROTOCOL_WORKSPACE, "SpacesStore"));
		NodeRef system = nodeService.getChildByName(root, TYPE_FOLDER, "system");
		NodeRef authorities = nodeService.getChildByName(system, TYPE_FOLDER, "authorities");
		QName assocQName = createQName(CONTENT_MODEL_1_0_URI, name);
		NodeRef node = nodeService.createNode(authorities, ASSOC_CONTAINS, assocQName, TYPE_CONTENT).getChildRef();
		authorityNodes.put(name, node);
		return name;
	}

	@Override
	public void addAuthority(String parentName, String childName) {
		createAuthority(null, childName);
	}

	@Override
	public void addAuthority(Collection<String> parentNames, String childName) {
		if (parentNames != null)
			for (String parentName : parentNames)
				addAuthority(parentName, childName);
	}

	@Override
	public void removeAuthority(String parentName, String childName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAuthority(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAuthority(String name, boolean cascade) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<String> getContainedAuthorities(AuthorityType type, String name, boolean immediate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getContainingAuthorities(AuthorityType type, String name, boolean immediate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getContainingAuthoritiesInZone(AuthorityType type, String name, String zoneName,
			AuthorityFilter filter, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getShortName(String name) {
		return name.replaceAll(GROUP_PREFIX, "");
	}

	@Override
	public String getName(AuthorityType type, String shortName) {
		return GROUP_PREFIX + shortName;
	}

	@Override
	public boolean authorityExists(String name) {
		return authorityNodes.get(name) != null;
	}

	@Override
	public String getAuthorityDisplayName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAuthorityDisplayName(String authorityName, String authorityDisplayName) {
		// TODO Auto-generated method stub

	}

	@Override
	public NodeRef getAuthorityNodeRef(String name) {
		return authorityNodes.get(name);
	}

	@Override
	public NodeRef getOrCreateZone(String zoneName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getZone(String zoneName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getAuthorityZones(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getAllAuthoritiesInZone(String zoneName, AuthorityType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getAllRootAuthoritiesInZone(String zoneName, AuthorityType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAuthorityToZones(String authorityName, Set<String> zones) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeAuthorityFromZones(String authorityName, Set<String> zones) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<String> getDefaultZones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> findAuthorities(AuthorityType type, String parentAuthority, boolean immediate,
			String displayNamePattern, String zoneName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
