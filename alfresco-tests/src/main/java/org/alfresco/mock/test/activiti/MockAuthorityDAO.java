package org.alfresco.mock.test.activiti;

import java.util.Collection;
import java.util.Set;

import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.repo.security.authority.AuthorityDAO;
import org.alfresco.repo.security.authority.AuthorityInfo;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.security.AuthorityService.AuthorityFilter;
import org.alfresco.service.cmr.security.AuthorityType;

public class MockAuthorityDAO implements AuthorityDAO {

	@Override
	public long getPersonCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getGroupCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addAuthority(Collection<String> parentNames, String childName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAuthority(String name, String authorityDisplayName, Set<String> authorityZones) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAuthority(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> getContainedAuthorities(AuthorityType type, String parentName, boolean immediate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAuthorityContained(String authority, String authorityToFind, Set<String> positiveHits,
			Set<String> negativeHits) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeAuthority(String parentName, String childName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAuthority(String parentName, String childName, boolean cacheRefresh) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> getContainingAuthorities(AuthorityType type, String name, boolean immediate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getContainingAuthoritiesInZone(AuthorityType type, String authority, String zoneName,
			AuthorityFilter filter, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<AuthorityInfo> getAuthoritiesInfo(AuthorityType type, String zoneName,
			String displayNameFilter, String sortBy, boolean sortAscending, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<String> getAuthorities(AuthorityType type, String zoneName, String displayNameFilter,
			boolean sortByDisplayName, boolean sortAscending, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authorityExists(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public NodeRef getAuthorityNodeRefOrNull(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthorityName(NodeRef authorityRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthorityDisplayName(String authorityName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAuthorityDisplayName(String authorityName, String authorityDisplayName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<String> getRootAuthorities(AuthorityType type, String zoneName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> findAuthorities(AuthorityType type, String parentAuthority, boolean immediate,
			String displayNamePattern, String zoneName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getShortName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName(AuthorityType type, String shortName) {
		// TODO Auto-generated method stub
		return null;
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
	public void addAuthorityToZones(String authorityName, Set<String> zones) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAuthorityFromZones(String authorityName, Set<String> zones) {
		// TODO Auto-generated method stub
		
	}

}
