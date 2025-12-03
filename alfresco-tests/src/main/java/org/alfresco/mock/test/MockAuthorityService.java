package org.alfresco.mock.test;

import static org.alfresco.model.ContentModel.ASSOC_CONTAINS;
import static org.alfresco.model.ContentModel.TYPE_CONTENT;
import static org.alfresco.model.ContentModel.TYPE_FOLDER;
import static org.alfresco.service.cmr.repository.StoreRef.PROTOCOL_WORKSPACE;
import static org.alfresco.service.cmr.repository.StoreRef.STORE_REF_WORKSPACE_SPACESSTORE;
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
import org.alfresco.repo.security.authority.AuthorityInfo;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.security.AuthorityService;
import org.alfresco.service.cmr.security.AuthorityType;
import org.alfresco.service.namespace.QName;
import org.springframework.beans.factory.annotation.Autowired;

/**
* Mock implementation of the MockAuthorityService class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockAuthorityService implements AuthorityService, Serializable {

	private final static String GROUP_PREFIX = "GROUP_";

/**
* The node service.
 */
	@Autowired
	private NodeService nodeService;

/**
* The authority nodes map.
 */
	private Map<String, NodeRef> authorityNodes = new HashMap<String, NodeRef>();

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public boolean hasAdminAuthority() {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param authorityName the authorityName
* @return the result
 */
	@Override
	public boolean isAdminAuthority(String authorityName) {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public boolean hasGuestAuthority() {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param authorityName the authorityName
* @return the result
 */
	@Override
	public boolean isGuestAuthority(String authorityName) {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public long countUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public long countGroups() {
		// TODO Auto-generated method stub
		return 0;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Set<String> getAuthorities() {
		return authorityNodes.keySet();
	}

/**
* {@inheritDoc}
* @param userName the userName
* @return the result
 */
	@Override
	public Set<String> getAuthoritiesForUser(String userName) {
		return getAuthorities();
	}

/**
* {@inheritDoc}
* @param type the type
* @return the result
 */
	@Override
	public Set<String> getAllAuthorities(AuthorityType type) {
		return getAuthorities();
	}

/**
* {@inheritDoc}
 */
	@Override
	public PagingResults<AuthorityInfo> getAuthoritiesInfo(AuthorityType type, String zoneName,
			String displayNameFilter, String sortBy, boolean sortAscending, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public PagingResults<String> getAuthorities(AuthorityType type, String zoneName, String displayNameFilter,
			boolean sortByDisplayName, boolean sortAscending, PagingRequest pagingRequest) {
		List<String> mainList = new ArrayList<String>();
		mainList.addAll(authorityNodes.keySet());
		PagingResults<String> authorities = new MockPagingResults<String>(mainList);
		return authorities;
	}

/**
* {@inheritDoc}
* @param type the type
* @return the result
 */
	@Override
	public Set<String> getAllRootAuthorities(AuthorityType type) {
		return getAuthorities();
	}

/**
* {@inheritDoc}
* @param type the type
* @param shortName the shortName
* @return the result
 */
	@Override
	public String createAuthority(AuthorityType type, String shortName) {
		return createAuthority(type, shortName, null, null);
	}

/**
* {@inheritDoc}
 */
	@Override
	public String createAuthority(AuthorityType type, String shortName, String authorityDisplayName,
			Set<String> authorityZones) {
		String name = getName(null, shortName);
		NodeRef root = nodeService.getRootNode(new StoreRef(PROTOCOL_WORKSPACE, STORE_REF_WORKSPACE_SPACESSTORE.getIdentifier()));
		NodeRef system = nodeService.getChildByName(root, TYPE_FOLDER, "system");
		NodeRef authorities = nodeService.getChildByName(system, TYPE_FOLDER, "authorities");
		QName assocQName = createQName(CONTENT_MODEL_1_0_URI, name);
		NodeRef node = nodeService.createNode(authorities, ASSOC_CONTAINS, assocQName, TYPE_CONTENT).getChildRef();
		authorityNodes.put(name, node);
		return name;
	}

/**
* {@inheritDoc}
* @param parentName the parentName
* @param childName the childName
 */
	@Override
	public void addAuthority(String parentName, String childName) {
		createAuthority(null, childName);
	}

/**
* {@inheritDoc}
* @param parentNames the parentNames
* @param childName the childName
 */
	@Override
	public void addAuthority(Collection<String> parentNames, String childName) {
		if (parentNames != null)
			for (String parentName : parentNames)
				addAuthority(parentName, childName);
	}

/**
* {@inheritDoc}
* @param parentName the parentName
* @param childName the childName
 */
	@Override
	public void removeAuthority(String parentName, String childName) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param name the name
 */
	@Override
	public void deleteAuthority(String name) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param name the name
* @param cascade the cascade
 */
	@Override
	public void deleteAuthority(String name, boolean cascade) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param type the type
* @param name the name
* @param immediate the immediate
* @return the result
 */
	@Override
	public Set<String> getContainedAuthorities(AuthorityType type, String name, boolean immediate) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param type the type
* @param name the name
* @param immediate the immediate
* @return the result
 */
	@Override
	public Set<String> getContainingAuthorities(AuthorityType type, String name, boolean immediate) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public Set<String> getContainingAuthoritiesInZone(AuthorityType type, String name, String zoneName,
			AuthorityFilter filter, int size) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param name the name
* @return the result
 */
	@Override
	public String getShortName(String name) {
		return name.replaceAll(GROUP_PREFIX, "");
	}

/**
* {@inheritDoc}
* @param type the type
* @param shortName the shortName
* @return the result
 */
	@Override
	public String getName(AuthorityType type, String shortName) {
		return GROUP_PREFIX + shortName;
	}

/**
* {@inheritDoc}
* @param name the name
* @return the result
 */
	@Override
	public boolean authorityExists(String name) {
		return authorityNodes.get(name) != null;
	}

/**
* {@inheritDoc}
* @param name the name
* @return the result
 */
	@Override
	public String getAuthorityDisplayName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param authorityName the authorityName
* @param authorityDisplayName the authorityDisplayName
 */
	@Override
	public void setAuthorityDisplayName(String authorityName, String authorityDisplayName) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param name the name
* @return the result
 */
	@Override
	public NodeRef getAuthorityNodeRef(String name) {
		return authorityNodes.get(name);
	}

/**
* {@inheritDoc}
* @param zoneName the zoneName
* @return the result
 */
	@Override
	public NodeRef getOrCreateZone(String zoneName) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param zoneName the zoneName
* @return the result
 */
	@Override
	public NodeRef getZone(String zoneName) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param name the name
* @return the result
 */
	@Override
	public Set<String> getAuthorityZones(String name) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param zoneName the zoneName
* @param type the type
* @return the result
 */
	@Override
	public Set<String> getAllAuthoritiesInZone(String zoneName, AuthorityType type) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param zoneName the zoneName
* @param type the type
* @return the result
 */
	@Override
	public Set<String> getAllRootAuthoritiesInZone(String zoneName, AuthorityType type) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param authorityName the authorityName
* @param zones the zones
 */
	@Override
	public void addAuthorityToZones(String authorityName, Set<String> zones) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param authorityName the authorityName
* @param zones the zones
 */
	@Override
	public void removeAuthorityFromZones(String authorityName, Set<String> zones) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Set<String> getDefaultZones() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public Set<String> findAuthorities(AuthorityType type, String parentAuthority, boolean immediate,
			String displayNamePattern, String zoneName) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* Sets the node service.
*
* @param nodeService the node service
 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
