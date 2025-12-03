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
 * @author vige
 */
public class MockPermissionService implements PermissionService, Serializable {

	@Autowired
	private NodeService nodeService;

	@Override
	public String getOwnerAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllPermission() {
		return ((MockNodeService) nodeService).getPermissions();
	}

	@Override
	public Set<AccessPermission> getPermissions(NodeRef nodeRef) {
		return ((MockNodeService) nodeService).getPermissions(nodeRef);
	}

	@Override
	public Set<AccessPermission> getAllSetPermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getSettablePermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getSettablePermissions(QName type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccessStatus hasPermission(NodeRef nodeRef, String permission) {
		return hasReadPermission(nodeRef);
	}

	@Override
	public AccessStatus hasReadPermission(NodeRef nodeRef) {
		return AccessStatus.ALLOWED;
	}

	@Override
	public Set<String> getReaders(Long aclId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccessStatus hasPermission(Long aclID, PermissionContext context, String permission) {
		return AccessStatus.ALLOWED;
	}

	@Override
	public void deletePermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearPermission(NodeRef nodeRef, String authority) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePermission(NodeRef nodeRef, String authority, String permission) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPermission(NodeRef nodeRef, String authority, String permission, boolean allow) {
		AccessPermission accessPermission = new MockAccessPermission(permission, authority);
		((MockNodeService) nodeService).setPermission(nodeRef, accessPermission);
	}

	@Override
	public void setInheritParentPermissions(NodeRef nodeRef, boolean inheritParentPermissions) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getInheritParentPermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPermission(StoreRef storeRef, String authority, String permission, boolean allow) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePermission(StoreRef storeRef, String authority, String permission) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearPermission(StoreRef storeRef, String authority) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePermissions(StoreRef storeRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<AccessPermission> getAllSetPermissions(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getAuthorisations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getReadersDenied(Long aclId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
