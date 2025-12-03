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

	@Autowired
	/** The node service. */
	private NodeService nodeService;

	@Override
	/**
	 * Get owner authority.
	 *
	 * @return the string
	 */
	public String getOwnerAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get all authorities.
	 *
	 * @return the string
	 */
	public String getAllAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get all permission.
	 *
	 * @return the string
	 */
	public String getAllPermission() {
		return ((MockNodeService) nodeService).getPermissions();
	}

	@Override
	/**
	 * Get permissions.
	 *
	 * @param nodeRef the node ref
	 * @return the set
	 */
	public Set<AccessPermission> getPermissions(NodeRef nodeRef) {
		return ((MockNodeService) nodeService).getPermissions(nodeRef);
	}

	@Override
	/**
	 * Get all set permissions.
	 *
	 * @param nodeRef the node ref
	 * @return the set
	 */
	public Set<AccessPermission> getAllSetPermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get settable permissions.
	 *
	 * @param nodeRef the node ref
	 * @return the set
	 */
	public Set<String> getSettablePermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get settable permissions.
	 *
	 * @param type the type
	 * @return the set
	 */
	public Set<String> getSettablePermissions(QName type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Has permission.
	 *
	 * @param nodeRef the node ref
	 * @param permission the permission
	 * @return the access status
	 */
	public AccessStatus hasPermission(NodeRef nodeRef, String permission) {
		return hasReadPermission(nodeRef);
	}

	@Override
	/**
	 * Has read permission.
	 *
	 * @param nodeRef the node ref
	 * @return the access status
	 */
	public AccessStatus hasReadPermission(NodeRef nodeRef) {
		return AccessStatus.ALLOWED;
	}

	@Override
	/**
	 * Get readers.
	 *
	 * @param aclId the acl id
	 * @return the set
	 */
	public Set<String> getReaders(Long aclId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Has permission.
	 *
	 * @param aclID the acl i d
	 * @param context the context
	 * @param permission the permission
	 * @return the access status
	 */
	public AccessStatus hasPermission(Long aclID, PermissionContext context, String permission) {
		return AccessStatus.ALLOWED;
	}

	@Override
	/**
	 * Delete permissions.
	 *
	 * @param nodeRef the node ref
	 */
	public void deletePermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Clear permission.
	 *
	 * @param nodeRef the node ref
	 * @param authority the authority
	 */
	public void clearPermission(NodeRef nodeRef, String authority) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Delete permission.
	 *
	 * @param nodeRef the node ref
	 * @param authority the authority
	 * @param permission the permission
	 */
	public void deletePermission(NodeRef nodeRef, String authority, String permission) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Set permission.
	 *
	 * @param nodeRef the node ref
	 * @param authority the authority
	 * @param permission the permission
	 * @param allow the allow
	 */
	public void setPermission(NodeRef nodeRef, String authority, String permission, boolean allow) {
		AccessPermission accessPermission = new MockAccessPermission(permission, authority);
		((MockNodeService) nodeService).setPermission(nodeRef, accessPermission);
	}

	@Override
	/**
	 * Set inherit parent permissions.
	 *
	 * @param nodeRef the node ref
	 * @param inheritParentPermissions the inherit parent permissions
	 */
	public void setInheritParentPermissions(NodeRef nodeRef, boolean inheritParentPermissions) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get inherit parent permissions.
	 *
	 * @param nodeRef the node ref
	 * @return the boolean
	 */
	public boolean getInheritParentPermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Set permission.
	 *
	 * @param storeRef the store ref
	 * @param authority the authority
	 * @param permission the permission
	 * @param allow the allow
	 */
	public void setPermission(StoreRef storeRef, String authority, String permission, boolean allow) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Delete permission.
	 *
	 * @param storeRef the store ref
	 * @param authority the authority
	 * @param permission the permission
	 */
	public void deletePermission(StoreRef storeRef, String authority, String permission) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Clear permission.
	 *
	 * @param storeRef the store ref
	 * @param authority the authority
	 */
	public void clearPermission(StoreRef storeRef, String authority) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Delete permissions.
	 *
	 * @param storeRef the store ref
	 */
	public void deletePermissions(StoreRef storeRef) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get all set permissions.
	 *
	 * @param storeRef the store ref
	 * @return the set
	 */
	public Set<AccessPermission> getAllSetPermissions(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get authorisations.
	 *
	 * @return the set
	 */
	public Set<String> getAuthorisations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get readers denied.
	 *
	 * @param aclId the acl id
	 * @return the set
	 */
	public Set<String> getReadersDenied(Long aclId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Set inherit parent permissions.
	 *
	 * @param nodeRef the node ref
	 * @param inheritParentPermissions the inherit parent permissions
	 * @param asyncCall the async call
	 */
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
