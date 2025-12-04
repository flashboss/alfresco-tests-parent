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
 * Mock implementation of the Alfresco PersonService for testing purposes. Provides stub
 * implementations for testing without a running Alfresco server.
 *
 * @author vige
 */
public class MockPersonService implements PersonService, Serializable {
  /**
   * Get person.
   *
   * @param userName the user name
   * @return the node ref
   */
  @Override
  public NodeRef getPerson(String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get person or null.
   *
   * @param userName the user name
   * @return the node ref
   */
  @Override
  public NodeRef getPersonOrNull(String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get person.
   *
   * @param userName the user name
   * @param autoCreateHomeFolderAndMissingPersonIfAllowed the auto create home folder and missing
   *     person if allowed
   * @return the node ref
   */
  @Override
  public NodeRef getPerson(String userName, boolean autoCreateHomeFolderAndMissingPersonIfAllowed) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get person.
   *
   * @param personRef the person ref
   * @return the person info
   */
  @Override
  public PersonInfo getPerson(NodeRef personRef) throws NoSuchPersonException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Person exists.
   *
   * @param userName the user name
   * @return the boolean
   */
  @Override
  public boolean personExists(String userName) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Create missing people.
   *
   * @return the boolean
   */
  @Override
  public boolean createMissingPeople() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Set create missing people.
   *
   * @param createMissing the create missing
   */
  @Override
  public void setCreateMissingPeople(boolean createMissing) {
    // TODO Auto-generated method stub

  }

  /**
   * Get mutable properties.
   *
   * @return the set
   */
  @Override
  public Set<QName> getMutableProperties() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Set person properties.
   *
   * @param userName the user name
   * @param properties the properties
   */
  @Override
  public void setPersonProperties(String userName, Map<QName, Serializable> properties) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setPersonProperties(
      String userName, Map<QName, Serializable> properties, boolean autoCreateHomeFolder) {
    // TODO Auto-generated method stub

  }

  /**
   * Is mutable.
   *
   * @return the boolean
   */
  @Override
  public boolean isMutable() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Create person.
   *
   * @param properties the properties
   * @return the node ref
   */
  @Override
  public NodeRef createPerson(Map<QName, Serializable> properties) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Create person.
   *
   * @param properties the properties
   * @param zones the zones
   * @return the node ref
   */
  @Override
  public NodeRef createPerson(Map<QName, Serializable> properties, Set<String> zones) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Notify person.
   *
   * @param userName the user name
   * @param password the password
   */
  @Override
  public void notifyPerson(String userName, String password) {
    // TODO Auto-generated method stub

  }

  /**
   * Delete person.
   *
   * @param userName the user name
   */
  @Override
  public void deletePerson(String userName) {
    // TODO Auto-generated method stub

  }

  /**
   * Delete person.
   *
   * @param personRef the person ref
   */
  @Override
  public void deletePerson(NodeRef personRef) {
    // TODO Auto-generated method stub

  }

  /**
   * Delete person.
   *
   * @param personRef the person ref
   * @param deleteAuthentication the delete authentication
   */
  @Override
  public void deletePerson(NodeRef personRef, boolean deleteAuthentication) {
    // TODO Auto-generated method stub

  }

  /**
   * Get all people.
   *
   * @return the set
   */
  @Override
  public Set<NodeRef> getAllPeople() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PagingResults<PersonInfo> getPeople(
      String pattern,
      List<QName> filterProps,
      List<Pair<QName, Boolean>> sortProps,
      PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PagingResults<PersonInfo> getPeople(
      List<Pair<QName, String>> stringPropFilters,
      boolean filterIgnoreCase,
      List<Pair<QName, Boolean>> sortProps,
      PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PagingResults<PersonInfo> getPeople(
      String pattern,
      List<QName> filterStringProps,
      Set<QName> inclusiveAspects,
      Set<QName> exclusiveAspects,
      boolean includeAdministraotrs,
      List<Pair<QName, Boolean>> sortProps,
      PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get people filtered by property.
   *
   * @param propertyKey the property key
   * @param propertyValue the property value
   * @param count the count
   * @return the set
   */
  @Override
  public Set<NodeRef> getPeopleFilteredByProperty(
      QName propertyKey, Serializable propertyValue, int count) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get people container.
   *
   * @return the node ref
   */
  @Override
  public NodeRef getPeopleContainer() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get user names are case sensitive.
   *
   * @return the boolean
   */
  @Override
  public boolean getUserNamesAreCaseSensitive() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Get user identifier.
   *
   * @param caseSensitiveUserName the case sensitive user name
   * @return the string
   */
  @Override
  public String getUserIdentifier(String caseSensitiveUserName) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Count people.
   *
   * @return the int
   */
  @Override
  public int countPeople() {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * Is enabled.
   *
   * @param userName the user name
   * @return the boolean
   */
  @Override
  public boolean isEnabled(String userName) {
    // TODO Auto-generated method stub
    return false;
  }
}
