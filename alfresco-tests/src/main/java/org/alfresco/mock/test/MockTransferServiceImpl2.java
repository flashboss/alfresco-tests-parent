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
 * Mock implementation of TransferService2 for testing purposes.
 * Provides stub methods for transfer operations with success events.
 *
 * @author lucastancapiano
 */
public class MockTransferServiceImpl2 implements TransferService2 {

	/**
	 * {@inheritDoc}
	 *
	 * @param targetName the target name
	 * @param definition the transfer definition
	 * @param callback the callbacks
	 * @return the transfer end event (always success)
	 * @throws TransferFailureException if transfer fails
	 */
	@Override
	public TransferEndEvent transfer(String targetName, TransferDefinition definition,
			Collection<TransferCallback> callback) throws TransferFailureException {
		TransferEventSuccess transferEndEvent = new TransferEventSuccess();
		transferEndEvent.setTransferState(TransferEvent.TransferState.SUCCESS);
		transferEndEvent.setMessage("success");
		transferEndEvent.setLast(true);
		return transferEndEvent;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param targetName the target name
	 * @param definition the transfer definition
	 * @param callbacks the callbacks
	 * @return the transfer end event
	 * @throws TransferFailureException if transfer fails
	 */
	@Override
	public TransferEndEvent transfer(String targetName, TransferDefinition definition, TransferCallback... callbacks)
			throws TransferFailureException {
		return transfer(targetName, definition, Arrays.asList(callbacks));
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param targetName the target name
	 * @param definition the transfer definition
	 * @param callback the callbacks
	 * @throws TransferException if transfer fails
	 */
	@Override
	public void transferAsync(String targetName, TransferDefinition definition, Collection<TransferCallback> callback)
			throws TransferException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param targetName the target name
	 * @param definition the transfer definition
	 * @param callbacks the callbacks
	 * @throws TransferException if transfer fails
	 */
	@Override
	public void transferAsync(String targetName, TransferDefinition definition, TransferCallback... callbacks)
			throws TransferException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param target the transfer target to verify
	 * @throws TransferException if verification fails
	 */
	@Override
	public void verify(TransferTarget target) throws TransferException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the target name
	 * @param title the title
	 * @param description the description
	 * @param endpointProtocol the endpoint protocol
	 * @param endpointHost the endpoint host
	 * @param endpointPort the endpoint port
	 * @param endpointPath the endpoint path
	 * @param username the username
	 * @param password the password
	 * @return the created transfer target
	 * @throws TransferException if creation fails
	 */
	@Override
	public TransferTarget createAndSaveTransferTarget(String name, String title, String description,
			String endpointProtocol, String endpointHost, int endpointPort, String endpointPath, String username,
			char[] password) throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the target name
	 * @return the created transfer target
	 */
	@Override
	public TransferTarget createTransferTarget(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return all transfer targets
	 * @throws TransferException if retrieval fails
	 */
	@Override
	public Set<TransferTarget> getTransferTargets() throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param groupName the group name
	 * @return the transfer targets for the group
	 * @throws TransferException if retrieval fails
	 */
	@Override
	public Set<TransferTarget> getTransferTargets(String groupName) throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the target name
	 * @return the transfer target
	 * @throws TransferException if retrieval fails
	 */
	@Override
	public TransferTarget getTransferTarget(String name) throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the target name
	 * @return true if exists, false otherwise
	 */
	@Override
	public boolean targetExists(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the target name to delete
	 * @throws TransferException if deletion fails
	 */
	@Override
	public void deleteTransferTarget(String name) throws TransferException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param update the transfer target to save
	 * @return the saved transfer target
	 * @throws TransferException if save fails
	 */
	@Override
	public TransferTarget saveTransferTarget(TransferTarget update) throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the target name
	 * @param enable whether to enable
	 * @throws TransferException if enable/disable fails
	 */
	@Override
	public void enableTransferTarget(String name, boolean enable) throws TransferException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param transferId the transfer ID to cancel
	 */
	@Override
	public void cancelAsync(String transferId) {
		// TODO Auto-generated method stub
	}

}
