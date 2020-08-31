package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.repo.security.authority.AuthorityInfo;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.security.AuthorityService;
import org.alfresco.service.cmr.security.AuthorityType;

public class MockAuthorityService implements AuthorityService, Serializable {

	private List<String> authorities = new ArrayList<String>();

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
	public long countUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long countGroups() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<String> getAuthorities() {
		return convertListToSet(authorities);
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
	public PagingResults<AuthorityInfo> getAuthoritiesInfo(AuthorityType type, String zoneName,
			String displayNameFilter, String sortBy, boolean sortAscending, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<String> getAuthorities(AuthorityType type, String zoneName, String displayNameFilter,
			boolean sortByDisplayName, boolean sortAscending, PagingRequest pagingRequest) {
		PagingResults<String> authorities = new MockPagingResults<String>(this.authorities);
		return authorities;
	}

	@Override
	public Set<String> getAllRootAuthorities(AuthorityType type) {
		return getAuthorities();
	}

	@Override
	public String createAuthority(AuthorityType type, String shortName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createAuthority(AuthorityType type, String shortName, String authorityDisplayName,
			Set<String> authorityZones) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAuthority(String parentName, String childName) {
		authorities.add(childName);
	}

	@Override
	public void addAuthority(Collection<String> parentNames, String childName) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName(AuthorityType type, String shortName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authorityExists(String name) {
		// TODO Auto-generated method stub
		return false;
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

	private static <T> Set<T> convertListToSet(List<T> list) {
		// create an empty set
		Set<T> set = new HashSet<>();

		// Add each element of list into the set
		for (T t : list)
			set.add(t);

		// return the set
		return set;
	}

}
