package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Map;
import org.alfresco.service.cmr.coci.CheckOutCheckInService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of the MockCheckOutCheckInService class for testing purposes. This class
 * provides a mock implementation that allows unit and integration tests to run without requiring a
 * full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockCheckOutCheckInService implements CheckOutCheckInService, Serializable {

  /** The checked out. */
  private boolean checkedOut;

  /** {@inheritDoc} */
  @Override
  public NodeRef checkout(
      NodeRef nodeRef,
      NodeRef destinationParentNodeRef,
      QName destinationAssocTypeQName,
      QName destinationAssocQName) {
    return checkout(nodeRef);
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public NodeRef checkout(NodeRef nodeRef) {
    checkedOut = true;
    return nodeRef;
  }

  /** {@inheritDoc} */
  @Override
  public NodeRef checkin(
      NodeRef workingCopyNodeRef,
      Map<String, Serializable> versionProperties,
      String contentUrl,
      boolean keepCheckedOut) {
    return checkin(workingCopyNodeRef, versionProperties);
  }

  /**
   * {@inheritDoc}
   *
   * @param workingCopyNodeRef the workingCopyNodeRef
   * @param contentUrl the contentUrl
   * @return the result
   */
  @Override
  public NodeRef checkin(
      NodeRef workingCopyNodeRef, Map<String, Serializable> versionProperties, String contentUrl) {
    return checkin(workingCopyNodeRef, versionProperties);
  }

  /**
   * {@inheritDoc}
   *
   * @param workingCopyNodeRef the workingCopyNodeRef
   * @return the result
   */
  @Override
  public NodeRef checkin(NodeRef workingCopyNodeRef, Map<String, Serializable> versionProperties) {
    checkedOut = false;
    return workingCopyNodeRef;
  }

  /**
   * {@inheritDoc}
   *
   * @param workingCopyNodeRef the workingCopyNodeRef
   * @return the result
   */
  @Override
  public NodeRef cancelCheckout(NodeRef workingCopyNodeRef) {
    checkedOut = false;
    return workingCopyNodeRef;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public NodeRef getWorkingCopy(NodeRef nodeRef) {
    return nodeRef;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public NodeRef getCheckedOut(NodeRef nodeRef) {
    return nodeRef;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public boolean isWorkingCopy(NodeRef nodeRef) {
    return checkedOut;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public boolean isCheckedOut(NodeRef nodeRef) {
    return checkedOut;
  }
}
