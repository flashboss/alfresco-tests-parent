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
 * Mock implementation of MockAuthorityService for testing purposes.
 *
 * @author vige
 */
public class MockAuthorityService implements AuthorityService, Serializable {

	/** The group prefix. */
	private final static String GROUP_PREFIX = "GROUP_";

	/** The node service. */
	@Autowired
	private NodeService nodeService;

	/** The authority nodes. */
	private Map<String, NodeRef> authorityNodes = new HashMap<String, NodeRef>();

	/**
	 * Has admin authority.
	 *
	 * @return the result
	 */
	@Override
	public boolean hasAdminAuthority() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Is admin authority.
	 *
	 * @param authorityName the authority name
	 * @return the result
	 */
	@Override
	public boolean isAdminAuthority(String authorityName) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Has guest authority.
	 *
	 * @return the result
	 */
	@Override
	public boolean hasGuestAuthority() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Is guest authority.
	 *
	 * @param authorityName the authority name
	 * @return the result
	 */
	@Override
	public boolean isGuestAuthority(String authorityName) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Count users.
	 *
	 * @return the result
	 */
	@Override
	public long countUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Count groups.
	 *
	 * @return the result
	 */
	@Override
	public long countGroups() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Get authorities.
	 *
	 * @return the result
	 */
	@Override
	public Set<String> getAuthorities() {
		return authorityNodes.keySet();
	}

	/**
	 * Get authorities for user.
	 *
	 * @param userName the user name
	 * @return the result
	 */
	@Override
	public Set<String> getAuthoritiesForUser(String userName) {
		return getAuthorities();
	}

	/**
	 * Get all authorities.
	 *
	 * @param type the type
	 * @return the result
	 */
	@Override
	public Set<String> getAllAuthorities(AuthorityType type) {
		return getAuthorities();
	}

	/**
	 * Get authorities info.
	 *
	 * @param type the type
	 * @param zoneName the zone name
	 * @param displayNameFilter the display name filter
	 * @param sortBy the sort by
	 * @param sortAscending the sort ascending
	 * @param pagingRequest the paging request
	 * @return the result
	 */
	@Override
	public PagingResults<AuthorityInfo> getAuthoritiesInfo(AuthorityType type, String zoneName,
			String displayNameFilter, String sortBy, boolean sortAscending, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get authorities.
	 *
	 * @param type the type
	 * @param zoneName the zone name
	 * @param displayNameFilter the display name filter
	 * @param sortByDisplayName the sort by display name
	 * @param sortAscending the sort ascending
	 * @param pagingRequest the paging request
	 * @return the result
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
	 * Get all root authorities.
	 *
	 * @param type the type
	 * @return the result
	 */
	@Override
	public Set<String> getAllRootAuthorities(AuthorityType type) {
		return getAuthorities();
	}

	/**
	 * Create authority.
	 *
	 * @param type the type
	 * @param shortName the short name
	 * @return the result
	 */
	@Override
	public String createAuthority(AuthorityType type, String shortName) {
		return createAuthority(type, shortName, null, null);
	}

	/**
	 * Create authority.
	 *
	 * @param type the type
	 * @param shortName the short name
	 * @param authorityDisplayName the authority display name
	 * @param authorityZones the authority zones
	 * @return the result
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
	 * Add authority.
	 *
	 * @param parentName the parent name
	 * @param childName the child name
	 */
	@Override
	public void addAuthority(String parentName, String childName) {
		createAuthority(null, childName);
	}

	/**
	 * Add authority.
	 *
	 * @param parentNames the parent names
	 * @param childName the child name
	 */
	@Override
	public void addAuthority(Collection<String> parentNames, String childName) {
		if (parentNames != null)
			for (String parentName : parentNames)
				addAuthority(parentName, childName);
	}

	/**
	 * Remove authority.
	 *
	 * @param parentName the parent name
	 * @param childName the child name
	 */
	@Override
	public void removeAuthority(String parentName, String childName) {
		// TODO Auto-generated method stub

	}

	/**
	 * Delete authority.
	 *
	 * @param name the name
	 */
	@Override
	public void deleteAuthority(String name) {
		// TODO Auto-generated method stub

	}

	/**
	 * Delete authority.
	 *
	 * @param name the name
	 * @param cascade the cascade
	 */
	@Override
	public void deleteAuthority(String name, boolean cascade) {
		// TODO Auto-generated method stub

	}

	/**
	 * Get contained authorities.
	 *
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
	 * Get containing authorities.
	 *
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
	 * Get containing authorities in zone.
	 *
	 * @param type the type
	 * @param name the name
	 * @param zoneName the zone name
	 * @param filter the filter
	 * @param size the size
	 * @return the result
	 */
	@Override
	public Set<String> getContainingAuthoritiesInZone(AuthorityType type, String name, String zoneName,
			AuthorityFilter filter, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get short name.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public String getShortName(String name) {
		return name.replaceAll(GROUP_PREFIX, "");
	}

	/**
	 * Get name.
	 *
	 * @param type the type
	 * @param shortName the short name
	 * @return the result
	 */
	@Override
	public String getName(AuthorityType type, String shortName) {
		return GROUP_PREFIX + shortName;
	}

	/**
	 * Authority exists.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public boolean authorityExists(String name) {
		return authorityNodes.get(name) != null;
	}

	/**
	 * Get authority display name.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public String getAuthorityDisplayName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Set authority display name.
	 *
	 * @param authorityName the authority name
	 * @param authorityDisplayName the authority display name
	 */
	@Override
	public void setAuthorityDisplayName(String authorityName, String authorityDisplayName) {
		// TODO Auto-generated method stub

	}

	/**
	 * Get authority node ref.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public NodeRef getAuthorityNodeRef(String name) {
		return authorityNodes.get(name);
	}

	/**
	 * Get or create zone.
	 *
	 * @param zoneName the zone name
	 * @return the result
	 */
	@Override
	public NodeRef getOrCreateZone(String zoneName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get zone.
	 *
	 * @param zoneName the zone name
	 * @return the result
	 */
	@Override
	public NodeRef getZone(String zoneName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get authority zones.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public Set<String> getAuthorityZones(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get all authorities in zone.
	 *
	 * @param zoneName the zone name
	 * @param type the type
	 * @return the result
	 */
	@Override
	public Set<String> getAllAuthoritiesInZone(String zoneName, AuthorityType type) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get all root authorities in zone.
	 *
	 * @param zoneName the zone name
	 * @param type the type
	 * @return the result
	 */
	@Override
	public Set<String> getAllRootAuthoritiesInZone(String zoneName, AuthorityType type) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Add authority to zones.
	 *
	 * @param authorityName the authority name
	 * @param zones the zones
	 */
	@Override
	public void addAuthorityToZones(String authorityName, Set<String> zones) {
		// TODO Auto-generated method stub

	}

	/**
	 * Remove authority from zones.
	 *
	 * @param authorityName the authority name
	 * @param zones the zones
	 */
	@Override
	public void removeAuthorityFromZones(String authorityName, Set<String> zones) {
		// TODO Auto-generated method stub

	}

	/**
	 * Get default zones.
	 *
	 * @return the result
	 */
	@Override
	public Set<String> getDefaultZones() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Find authorities.
	 *
	 * @param type the type
	 * @param parentAuthority the parent authority
	 * @param immediate the immediate
	 * @param displayNamePattern the display name pattern
	 * @param zoneName the zone name
	 * @return the result
	 */
	@Override
	public Set<String> findAuthorities(AuthorityType type, String parentAuthority, boolean immediate,
			String displayNamePattern, String zoneName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Set node service.
	 *
	 * @param nodeService the node service
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
