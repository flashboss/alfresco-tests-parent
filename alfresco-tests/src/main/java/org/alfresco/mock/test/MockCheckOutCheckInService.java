package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Map;

import org.alfresco.service.cmr.coci.CheckOutCheckInService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of the Alfresco CheckOutCheckInService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockCheckOutCheckInService implements CheckOutCheckInService, Serializable {

	/** The checked out. */
	private boolean checkedOut;

	@Override
	public NodeRef checkout(NodeRef nodeRef, NodeRef destinationParentNodeRef, QName destinationAssocTypeQName,
			QName destinationAssocQName) {
		return checkout(nodeRef);
	}	/**
	 * Checkout.
	 *
	 * @param nodeRef the node ref
	 * @return the node ref
	 */


	@Override
	public NodeRef checkout(NodeRef nodeRef) {
		checkedOut = true;
		return nodeRef;
	}

	@Override
	public NodeRef checkin(NodeRef workingCopyNodeRef, Map<String, Serializable> versionProperties, String contentUrl,
			boolean keepCheckedOut) {
		return checkin(workingCopyNodeRef, versionProperties);
	}	/**
	 * Checkin.
	 *
	 * @param workingCopyNodeRef the working copy node ref
	 * @param versionProperties the version properties
	 * @param contentUrl the content url
	 * @return the node ref
	 */


	@Override
	public NodeRef checkin(NodeRef workingCopyNodeRef, Map<String, Serializable> versionProperties, String contentUrl) {
		return checkin(workingCopyNodeRef, versionProperties);
	}	/**
	 * Checkin.
	 *
	 * @param workingCopyNodeRef the working copy node ref
	 * @param versionProperties the version properties
	 * @return the node ref
	 */


	@Override
	public NodeRef checkin(NodeRef workingCopyNodeRef, Map<String, Serializable> versionProperties) {
		checkedOut = false;
		return workingCopyNodeRef;
	}	/**
	 * Cancel checkout.
	 *
	 * @param workingCopyNodeRef the working copy node ref
	 * @return the node ref
	 */


	@Override
	public NodeRef cancelCheckout(NodeRef workingCopyNodeRef) {
		checkedOut = false;
		return workingCopyNodeRef;
	}	/**
	 * Get working copy.
	 *
	 * @param nodeRef the node ref
	 * @return the node ref
	 */


	@Override
	public NodeRef getWorkingCopy(NodeRef nodeRef) {
		return nodeRef;
	}	/**
	 * Get checked out.
	 *
	 * @param nodeRef the node ref
	 * @return the node ref
	 */


	@Override
	public NodeRef getCheckedOut(NodeRef nodeRef) {
		return nodeRef;
	}	/**
	 * Is working copy.
	 *
	 * @param nodeRef the node ref
	 * @return the boolean
	 */


	@Override
	public boolean isWorkingCopy(NodeRef nodeRef) {
		return checkedOut;
	}	/**
	 * Is checked out.
	 *
	 * @param nodeRef the node ref
	 * @return the boolean
	 */


	@Override
	public boolean isCheckedOut(NodeRef nodeRef) {
		return checkedOut;
	}

}
