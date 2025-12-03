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
 * Mock implementation of the MockPermissionService class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockPermissionService implements PermissionService, Serializable {

	@Autowired
	private NodeService nodeService;

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getOwnerAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getAllAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getAllPermission() {
		return ((MockNodeService) nodeService).getPermissions();
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Set<AccessPermission> getPermissions(NodeRef nodeRef) {
		return ((MockNodeService) nodeService).getPermissions(nodeRef);
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Set<AccessPermission> getAllSetPermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Set<String> getSettablePermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Set<String> getSettablePermissions(QName type) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public AccessStatus hasPermission(NodeRef nodeRef, String permission) {
		return hasReadPermission(nodeRef);
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public AccessStatus hasReadPermission(NodeRef nodeRef) {
		return AccessStatus.ALLOWED;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Set<String> getReaders(Long aclId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public AccessStatus hasPermission(Long aclID, PermissionContext context, String permission) {
		return AccessStatus.ALLOWED;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void deletePermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub

	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void clearPermission(NodeRef nodeRef, String authority) {
		// TODO Auto-generated method stub

	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void deletePermission(NodeRef nodeRef, String authority, String permission) {
		// TODO Auto-generated method stub

	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void setPermission(NodeRef nodeRef, String authority, String permission, boolean allow) {
		AccessPermission accessPermission = new MockAccessPermission(permission, authority);
		((MockNodeService) nodeService).setPermission(nodeRef, accessPermission);
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void setInheritParentPermissions(NodeRef nodeRef, boolean inheritParentPermissions) {
		// TODO Auto-generated method stub

	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public boolean getInheritParentPermissions(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void setPermission(StoreRef storeRef, String authority, String permission, boolean allow) {
		// TODO Auto-generated method stub

	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void deletePermission(StoreRef storeRef, String authority, String permission) {
		// TODO Auto-generated method stub

	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void clearPermission(StoreRef storeRef, String authority) {
		// TODO Auto-generated method stub

	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void deletePermissions(StoreRef storeRef) {
		// TODO Auto-generated method stub

	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Set<AccessPermission> getAllSetPermissions(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Set<String> getAuthorisations() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Set<String> getReadersDenied(Long aclId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void setInheritParentPermissions(NodeRef nodeRef, boolean inheritParentPermissions, boolean asyncCall) {
		// TODO Auto-generated method stub
		
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
