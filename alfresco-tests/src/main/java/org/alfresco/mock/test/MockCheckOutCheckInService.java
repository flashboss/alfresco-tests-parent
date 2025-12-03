package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Map;

import org.alfresco.service.cmr.coci.CheckOutCheckInService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of CheckOutCheckInService for testing purposes.
 * Provides simple checkout/checkin functionality with state tracking.
 *
 * @author lucastancapiano
 */
public class MockCheckOutCheckInService implements CheckOutCheckInService, Serializable {

	/** Flag indicating if a node is checked out. */
	private boolean checkedOut;

	/**
	 * {@inheritDoc}
	 *
	 * @param nodeRef the node to check out
	 * @param destinationParentNodeRef the destination parent node
	 * @param destinationAssocTypeQName the destination association type
	 * @param destinationAssocQName the destination association name
	 * @return the working copy node reference
	 */
	@Override
	public NodeRef checkout(NodeRef nodeRef, NodeRef destinationParentNodeRef, QName destinationAssocTypeQName,
			QName destinationAssocQName) {
		return checkout(nodeRef);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param nodeRef the node to check out
	 * @return the working copy node reference
	 */
	@Override
	public NodeRef checkout(NodeRef nodeRef) {
		checkedOut = true;
		return nodeRef;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param workingCopyNodeRef the working copy node reference
	 * @param versionProperties the version properties
	 * @param contentUrl the content URL
	 * @param keepCheckedOut whether to keep checked out
	 * @return the checked in node reference
	 */
	@Override
	public NodeRef checkin(NodeRef workingCopyNodeRef, Map<String, Serializable> versionProperties, String contentUrl,
			boolean keepCheckedOut) {
		return checkin(workingCopyNodeRef, versionProperties);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param workingCopyNodeRef the working copy node reference
	 * @param versionProperties the version properties
	 * @param contentUrl the content URL
	 * @return the checked in node reference
	 */
	@Override
	public NodeRef checkin(NodeRef workingCopyNodeRef, Map<String, Serializable> versionProperties, String contentUrl) {
		return checkin(workingCopyNodeRef, versionProperties);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param workingCopyNodeRef the working copy node reference
	 * @param versionProperties the version properties
	 * @return the checked in node reference
	 */
	@Override
	public NodeRef checkin(NodeRef workingCopyNodeRef, Map<String, Serializable> versionProperties) {
		checkedOut = false;
		return workingCopyNodeRef;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param workingCopyNodeRef the working copy node reference
	 * @return the original node reference
	 */
	@Override
	public NodeRef cancelCheckout(NodeRef workingCopyNodeRef) {
		checkedOut = false;
		return workingCopyNodeRef;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param nodeRef the node reference
	 * @return the working copy node reference
	 */
	@Override
	public NodeRef getWorkingCopy(NodeRef nodeRef) {
		return nodeRef;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param nodeRef the node reference
	 * @return the checked out node reference
	 */
	@Override
	public NodeRef getCheckedOut(NodeRef nodeRef) {
		return nodeRef;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param nodeRef the node reference
	 * @return true if it's a working copy, false otherwise
	 */
	@Override
	public boolean isWorkingCopy(NodeRef nodeRef) {
		return checkedOut;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param nodeRef the node reference
	 * @return true if checked out, false otherwise
	 */
	@Override
	public boolean isCheckedOut(NodeRef nodeRef) {
		return checkedOut;
	}

}
