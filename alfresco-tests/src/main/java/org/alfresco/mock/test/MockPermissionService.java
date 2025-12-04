package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Set;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.security.AccessPermission;
import org.alfresco.service.cmr.security.AccessStatus;
import org.alfresco.service.cmr.security.PermissionContext;
import org.alfresco.service.cmr.security.PermissionService;
import org.alfresco.service.namespace.QName;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mock implementation of the MockPermissionService class for testing purposes. This class provides
 * a mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockPermissionService implements PermissionService, Serializable {

  /** The node service. */
  @Autowired private NodeService nodeService;

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getOwnerAuthority() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getAllAuthorities() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getAllPermission() {
    return ((MockNodeService) nodeService).getPermissions();
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public Set<AccessPermission> getPermissions(NodeRef nodeRef) {
    return ((MockNodeService) nodeService).getPermissions(nodeRef);
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public Set<AccessPermission> getAllSetPermissions(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public Set<String> getSettablePermissions(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param type the type
   * @return the result
   */
  @Override
  public Set<String> getSettablePermissions(QName type) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param permission the permission
   * @return the result
   */
  @Override
  public AccessStatus hasPermission(NodeRef nodeRef, String permission) {
    return hasReadPermission(nodeRef);
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public AccessStatus hasReadPermission(NodeRef nodeRef) {
    return AccessStatus.ALLOWED;
  }

  /**
   * {@inheritDoc}
   *
   * @param aclId the aclId
   * @return the result
   */
  @Override
  public Set<String> getReaders(Long aclId) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param aclID the aclID
   * @param context the context
   * @param permission the permission
   * @return the result
   */
  @Override
  public AccessStatus hasPermission(Long aclID, PermissionContext context, String permission) {
    return AccessStatus.ALLOWED;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   */
  @Override
  public void deletePermissions(NodeRef nodeRef) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param authority the authority
   */
  @Override
  public void clearPermission(NodeRef nodeRef, String authority) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param authority the authority
   * @param permission the permission
   */
  @Override
  public void deletePermission(NodeRef nodeRef, String authority, String permission) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param authority the authority
   * @param permission the permission
   * @param allow the allow
   */
  @Override
  public void setPermission(NodeRef nodeRef, String authority, String permission, boolean allow) {
    AccessPermission accessPermission = new MockAccessPermission(permission, authority);
    ((MockNodeService) nodeService).setPermission(nodeRef, accessPermission);
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param inheritParentPermissions the inheritParentPermissions
   */
  @Override
  public void setInheritParentPermissions(NodeRef nodeRef, boolean inheritParentPermissions) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public boolean getInheritParentPermissions(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param authority the authority
   * @param permission the permission
   * @param allow the allow
   */
  @Override
  public void setPermission(StoreRef storeRef, String authority, String permission, boolean allow) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param authority the authority
   * @param permission the permission
   */
  @Override
  public void deletePermission(StoreRef storeRef, String authority, String permission) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param authority the authority
   */
  @Override
  public void clearPermission(StoreRef storeRef, String authority) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   */
  @Override
  public void deletePermissions(StoreRef storeRef) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @return the result
   */
  @Override
  public Set<AccessPermission> getAllSetPermissions(StoreRef storeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Set<String> getAuthorisations() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param aclId the aclId
   * @return the result
   */
  @Override
  public Set<String> getReadersDenied(Long aclId) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param inheritParentPermissions the inheritParentPermissions
   * @param asyncCall the asyncCall
   */
  @Override
  public void setInheritParentPermissions(
      NodeRef nodeRef, boolean inheritParentPermissions, boolean asyncCall) {
    // TODO Auto-generated method stub

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
