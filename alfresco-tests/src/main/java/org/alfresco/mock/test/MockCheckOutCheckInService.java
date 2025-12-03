package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Map;

import org.alfresco.service.cmr.coci.CheckOutCheckInService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of MockCheckOutCheckInService for testing purposes.
 *
 * @author lucastancapiano
 */
public class MockCheckOutCheckInService implements CheckOutCheckInService, Serializable {

	private boolean checkedOut;

	@Override
	public NodeRef checkout(NodeRef nodeRef, NodeRef destinationParentNodeRef, QName destinationAssocTypeQName,
			QName destinationAssocQName) {
		return checkout(nodeRef);
	}

	@Override
	public NodeRef checkout(NodeRef nodeRef) {
		checkedOut = true;
		return nodeRef;
	}

	@Override
	public NodeRef checkin(NodeRef workingCopyNodeRef, Map<String, Serializable> versionProperties, String contentUrl,
			boolean keepCheckedOut) {
		return checkin(workingCopyNodeRef, versionProperties);
	}

	@Override
	public NodeRef checkin(NodeRef workingCopyNodeRef, Map<String, Serializable> versionProperties, String contentUrl) {
		return checkin(workingCopyNodeRef, versionProperties);
	}

	@Override
	public NodeRef checkin(NodeRef workingCopyNodeRef, Map<String, Serializable> versionProperties) {
		checkedOut = false;
		return workingCopyNodeRef;
	}

	@Override
	public NodeRef cancelCheckout(NodeRef workingCopyNodeRef) {
		checkedOut = false;
		return workingCopyNodeRef;
	}

	@Override
	public NodeRef getWorkingCopy(NodeRef nodeRef) {
		return nodeRef;
	}

	@Override
	public NodeRef getCheckedOut(NodeRef nodeRef) {
		return nodeRef;
	}

	@Override
	public boolean isWorkingCopy(NodeRef nodeRef) {
		return checkedOut;
	}

	@Override
	public boolean isCheckedOut(NodeRef nodeRef) {
		return checkedOut;
	}

}
