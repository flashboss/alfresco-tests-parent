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
 * Mock implementation of the Alfresco PermissionService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockPermissionService implements PermissionService, Serializable {
	/** The node service. */
	@Autowired
	private NodeService nodeService;	/**
	 * Get owner authority.
	 *
	 * @return the string
	 */
	@Override
	public String getOwnerAuthority() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get all authorities.
	 *
	 * @return the string
	 */
	@Override
	public String getAllAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get all permission.
	 *
	 * @return the string
	 */
	@Override
	public String getAllPermission() {
		return ((MockNodeService) nodeService).getPermissions();
	}
	/**
	 * Get permissions.
	 *
	 * @param nodeRef the node ref
	 * @return the set
	 */
	@Override
	public Set<AccessPermission> getPermissions(NodeRef nodeRef) {
		return ((MockNodeService) nodeService).getPermissions(nodeRef);
	}
	/**
	 * Get all set permissions.
	 *
	 * @param nodeRef the node ref
	 * @return the set
	 */
	@Override
	public Set<AccessPermission> getAllSetPermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get settable permissions.
	 *
	 * @param nodeRef the node ref
	 * @return the set
	 */
	@Override
	public Set<String> getSettablePermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get settable permissions.
	 *
	 * @param type the type
	 * @return the set
	 */
	@Override
	public Set<String> getSettablePermissions(QName type) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Has permission.
	 *
	 * @param nodeRef the node ref
	 * @param permission the permission
	 * @return the access status
	 */
	@Override
	public AccessStatus hasPermission(NodeRef nodeRef, String permission) {
		return hasReadPermission(nodeRef);
	}
	/**
	 * Has read permission.
	 *
	 * @param nodeRef the node ref
	 * @return the access status
	 */
	@Override
	public AccessStatus hasReadPermission(NodeRef nodeRef) {
		return AccessStatus.ALLOWED;
	}
	/**
	 * Get readers.
	 *
	 * @param aclId the acl id
	 * @return the set
	 */
	@Override
	public Set<String> getReaders(Long aclId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Has permission.
	 *
	 * @param aclID the acl i d
	 * @param context the context
	 * @param permission the permission
	 * @return the access status
	 */
	@Override
	public AccessStatus hasPermission(Long aclID, PermissionContext context, String permission) {
		return AccessStatus.ALLOWED;
	}
	/**
	 * Delete permissions.
	 *
	 * @param nodeRef the node ref
	 */
	@Override
	public void deletePermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub

	}
	/**
	 * Clear permission.
	 *
	 * @param nodeRef the node ref
	 * @param authority the authority
	 */
	@Override
	public void clearPermission(NodeRef nodeRef, String authority) {
		// TODO Auto-generated method stub

	}
	/**
	 * Delete permission.
	 *
	 * @param nodeRef the node ref
	 * @param authority the authority
	 * @param permission the permission
	 */
	@Override
	public void deletePermission(NodeRef nodeRef, String authority, String permission) {
		// TODO Auto-generated method stub

	}
	/**
	 * Set permission.
	 *
	 * @param nodeRef the node ref
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
	 * Set inherit parent permissions.
	 *
	 * @param nodeRef the node ref
	 * @param inheritParentPermissions the inherit parent permissions
	 */
	@Override
	public void setInheritParentPermissions(NodeRef nodeRef, boolean inheritParentPermissions) {
		// TODO Auto-generated method stub

	}
	/**
	 * Get inherit parent permissions.
	 *
	 * @param nodeRef the node ref
	 * @return the boolean
	 */
	@Override
	public boolean getInheritParentPermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Set permission.
	 *
	 * @param storeRef the store ref
	 * @param authority the authority
	 * @param permission the permission
	 * @param allow the allow
	 */
	@Override
	public void setPermission(StoreRef storeRef, String authority, String permission, boolean allow) {
		// TODO Auto-generated method stub

	}
	/**
	 * Delete permission.
	 *
	 * @param storeRef the store ref
	 * @param authority the authority
	 * @param permission the permission
	 */
	@Override
	public void deletePermission(StoreRef storeRef, String authority, String permission) {
		// TODO Auto-generated method stub

	}
	/**
	 * Clear permission.
	 *
	 * @param storeRef the store ref
	 * @param authority the authority
	 */
	@Override
	public void clearPermission(StoreRef storeRef, String authority) {
		// TODO Auto-generated method stub

	}
	/**
	 * Delete permissions.
	 *
	 * @param storeRef the store ref
	 */
	@Override
	public void deletePermissions(StoreRef storeRef) {
		// TODO Auto-generated method stub

	}
	/**
	 * Get all set permissions.
	 *
	 * @param storeRef the store ref
	 * @return the set
	 */
	@Override
	public Set<AccessPermission> getAllSetPermissions(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get authorisations.
	 *
	 * @return the set
	 */
	@Override
	public Set<String> getAuthorisations() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get readers denied.
	 *
	 * @param aclId the acl id
	 * @return the set
	 */
	@Override
	public Set<String> getReadersDenied(Long aclId) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Set inherit parent permissions.
	 *
	 * @param nodeRef the node ref
	 * @param inheritParentPermissions the inherit parent permissions
	 * @param asyncCall the async call
	 */
	@Override
	public void setInheritParentPermissions(NodeRef nodeRef, boolean inheritParentPermissions, boolean asyncCall) {
		// TODO Auto-generated method stub
		
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
