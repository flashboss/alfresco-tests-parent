package org.alfresco.mock.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import org.alfresco.service.cmr.transfer.TransferCallback;
import org.alfresco.service.cmr.transfer.TransferDefinition;
import org.alfresco.service.cmr.transfer.TransferEndEvent;
import org.alfresco.service.cmr.transfer.TransferEvent;
import org.alfresco.service.cmr.transfer.TransferEventSuccess;
import org.alfresco.service.cmr.transfer.TransferException;
import org.alfresco.service.cmr.transfer.TransferFailureException;
import org.alfresco.service.cmr.transfer.TransferService2;
import org.alfresco.service.cmr.transfer.TransferTarget;

/**
 * Mock implementation of the MockTransferServiceImpl2 class for testing purposes. This class
 * provides a mock implementation that allows unit and integration tests to run without requiring a
 * full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockTransferServiceImpl2 implements TransferService2 {

  /** {@inheritDoc} */
  @Override
  public TransferEndEvent transfer(
      String targetName, TransferDefinition definition, Collection<TransferCallback> callback)
      throws TransferFailureException {
    TransferEventSuccess transferEndEvent = new TransferEventSuccess();
    transferEndEvent.setTransferState(TransferEvent.TransferState.SUCCESS);
    transferEndEvent.setMessage("success");
    transferEndEvent.setLast(true);
    return transferEndEvent;
  }

  /**
   * {@inheritDoc}
   *
   * @param targetName the targetName
   * @param definition the definition
   * @return the result
   */
  @Override
  public TransferEndEvent transfer(
      String targetName, TransferDefinition definition, TransferCallback... callbacks)
      throws TransferFailureException {
    return transfer(targetName, definition, Arrays.asList(callbacks));
  }

  /**
   * {@inheritDoc}
   *
   * @param targetName the targetName
   * @param definition the definition
   * @param callback the callback
   */
  @Override
  public void transferAsync(
      String targetName, TransferDefinition definition, Collection<TransferCallback> callback)
      throws TransferException {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param targetName the targetName
   * @param definition the definition
   */
  @Override
  public void transferAsync(
      String targetName, TransferDefinition definition, TransferCallback... callbacks)
      throws TransferException {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param target the target
   * @throws TransferException if an error occurs
   */
  @Override
  public void verify(TransferTarget target) throws TransferException {
    // TODO Auto-generated method stub

  }

  /** {@inheritDoc} */
  @Override
  public TransferTarget createAndSaveTransferTarget(
      String name,
      String title,
      String description,
      String endpointProtocol,
      String endpointHost,
      int endpointPort,
      String endpointPath,
      String username,
      char[] password)
      throws TransferException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param name the name
   * @return the result
   */
  @Override
  public TransferTarget createTransferTarget(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   * @throws TransferException if an error occurs
   */
  @Override
  public Set<TransferTarget> getTransferTargets() throws TransferException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param groupName the groupName
   * @return the result
   * @throws TransferException if an error occurs
   */
  @Override
  public Set<TransferTarget> getTransferTargets(String groupName) throws TransferException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param name the name
   * @return the result
   * @throws TransferException if an error occurs
   */
  @Override
  public TransferTarget getTransferTarget(String name) throws TransferException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param name the name
   * @return the result
   */
  @Override
  public boolean targetExists(String name) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @param name the name
   * @throws TransferException if an error occurs
   */
  @Override
  public void deleteTransferTarget(String name) throws TransferException {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param update the update
   * @return the result
   * @throws TransferException if an error occurs
   */
  @Override
  public TransferTarget saveTransferTarget(TransferTarget update) throws TransferException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param name the name
   * @param enable the enable
   * @throws TransferException if an error occurs
   */
  @Override
  public void enableTransferTarget(String name, boolean enable) throws TransferException {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param transferId the transferId
   */
  @Override
  public void cancelAsync(String transferId) {
    // TODO Auto-generated method stub

  }
}
