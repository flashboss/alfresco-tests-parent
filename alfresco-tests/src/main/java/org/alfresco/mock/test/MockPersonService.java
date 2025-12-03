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
 * Mock implementation of the Alfresco PersonService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockPersonService implements PersonService, Serializable {

	@Override
	/**
	 * Get person.
	 *
	 * @param userName the user name
	 * @return the node ref
	 */
	public NodeRef getPerson(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get person or null.
	 *
	 * @param userName the user name
	 * @return the node ref
	 */
	public NodeRef getPersonOrNull(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get person.
	 *
	 * @param userName the user name
	 * @param autoCreateHomeFolderAndMissingPersonIfAllowed the auto create home folder and missing person if allowed
	 * @return the node ref
	 */
	public NodeRef getPerson(String userName, boolean autoCreateHomeFolderAndMissingPersonIfAllowed) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get person.
	 *
	 * @param personRef the person ref
	 * @return the person info
	 */
	public PersonInfo getPerson(NodeRef personRef) throws NoSuchPersonException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Person exists.
	 *
	 * @param userName the user name
	 * @return the boolean
	 */
	public boolean personExists(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Create missing people.
	 *
	 * @return the boolean
	 */
	public boolean createMissingPeople() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Set create missing people.
	 *
	 * @param createMissing the create missing
	 */
	public void setCreateMissingPeople(boolean createMissing) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Get mutable properties.
	 *
	 * @return the set
	 */
	public Set<QName> getMutableProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Set person properties.
	 *
	 * @param userName the user name
	 * @param properties the properties
	 */
	public void setPersonProperties(String userName, Map<QName, Serializable> properties) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPersonProperties(String userName, Map<QName, Serializable> properties,
			boolean autoCreateHomeFolder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Is mutable.
	 *
	 * @return the boolean
	 */
	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Create person.
	 *
	 * @param properties the properties
	 * @return the node ref
	 */
	public NodeRef createPerson(Map<QName, Serializable> properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Create person.
	 *
	 * @param properties the properties
	 * @param zones the zones
	 * @return the node ref
	 */
	public NodeRef createPerson(Map<QName, Serializable> properties, Set<String> zones) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Notify person.
	 *
	 * @param userName the user name
	 * @param password the password
	 */
	public void notifyPerson(String userName, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Delete person.
	 *
	 * @param userName the user name
	 */
	public void deletePerson(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Delete person.
	 *
	 * @param personRef the person ref
	 */
	public void deletePerson(NodeRef personRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Delete person.
	 *
	 * @param personRef the person ref
	 * @param deleteAuthentication the delete authentication
	 */
	public void deletePerson(NodeRef personRef, boolean deleteAuthentication) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Get all people.
	 *
	 * @return the set
	 */
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
	/**
	 * Get people filtered by property.
	 *
	 * @param propertyKey the property key
	 * @param propertyValue the property value
	 * @param count the count
	 * @return the set
	 */
	public Set<NodeRef> getPeopleFilteredByProperty(QName propertyKey, Serializable propertyValue, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get people container.
	 *
	 * @return the node ref
	 */
	public NodeRef getPeopleContainer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get user names are case sensitive.
	 *
	 * @return the boolean
	 */
	public boolean getUserNamesAreCaseSensitive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get user identifier.
	 *
	 * @param caseSensitiveUserName the case sensitive user name
	 * @return the string
	 */
	public String getUserIdentifier(String caseSensitiveUserName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Count people.
	 *
	 * @return the int
	 */
	public int countPeople() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	/**
	 * Is enabled.
	 *
	 * @param userName the user name
	 * @return the boolean
	 */
	public boolean isEnabled(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

}
