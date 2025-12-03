package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.security.NoSuchPersonException;
import org.alfresco.service.cmr.security.PersonService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.Pair;

/**
 * Mock implementation of MockPersonService for testing purposes.
 *
 * @author vige
 */
public class MockPersonService implements PersonService, Serializable {

	@Override
	public NodeRef getPerson(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getPersonOrNull(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getPerson(String userName, boolean autoCreateHomeFolderAndMissingPersonIfAllowed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonInfo getPerson(NodeRef personRef) throws NoSuchPersonException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean personExists(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createMissingPeople() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCreateMissingPeople(boolean createMissing) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<QName> getMutableProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPersonProperties(String userName, Map<QName, Serializable> properties) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPersonProperties(String userName, Map<QName, Serializable> properties,
			boolean autoCreateHomeFolder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public NodeRef createPerson(Map<QName, Serializable> properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef createPerson(Map<QName, Serializable> properties, Set<String> zones) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifyPerson(String userName, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(NodeRef personRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(NodeRef personRef, boolean deleteAuthentication) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<NodeRef> getAllPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<PersonInfo> getPeople(String pattern, List<QName> filterProps,
			List<Pair<QName, Boolean>> sortProps, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<PersonInfo> getPeople(List<Pair<QName, String>> stringPropFilters, boolean filterIgnoreCase,
			List<Pair<QName, Boolean>> sortProps, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<PersonInfo> getPeople(String pattern, List<QName> filterStringProps,
			Set<QName> inclusiveAspects, Set<QName> exclusiveAspects, boolean includeAdministraotrs,
			List<Pair<QName, Boolean>> sortProps, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<NodeRef> getPeopleFilteredByProperty(QName propertyKey, Serializable propertyValue, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getPeopleContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getUserNamesAreCaseSensitive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUserIdentifier(String caseSensitiveUserName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countPeople() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEnabled(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

}
