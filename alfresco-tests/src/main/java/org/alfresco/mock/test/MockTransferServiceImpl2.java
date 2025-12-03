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
 * Mock implementation of the Alfresco TransferServiceImpl2 for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockTransferServiceImpl2 implements TransferService2 {

	@Override
	public TransferEndEvent transfer(String targetName, TransferDefinition definition,
			Collection<TransferCallback> callback) throws TransferFailureException {
		TransferEventSuccess transferEndEvent = new TransferEventSuccess();
		transferEndEvent.setTransferState(TransferEvent.TransferState.SUCCESS);
		transferEndEvent.setMessage("success");
		transferEndEvent.setLast(true);
		return transferEndEvent;
	}

	@Override
	public TransferEndEvent transfer(String targetName, TransferDefinition definition, TransferCallback... callbacks)
			throws TransferFailureException {
		return transfer(targetName, definition, Arrays.asList(callbacks));
	}

	@Override
	public void transferAsync(String targetName, TransferDefinition definition, Collection<TransferCallback> callback)
			throws TransferException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transferAsync(String targetName, TransferDefinition definition, TransferCallback... callbacks)
			throws TransferException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void verify(TransferTarget target) throws TransferException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TransferTarget createAndSaveTransferTarget(String name, String title, String description,
			String endpointProtocol, String endpointHost, int endpointPort, String endpointPath, String username,
			char[] password) throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransferTarget createTransferTarget(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TransferTarget> getTransferTargets() throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TransferTarget> getTransferTargets(String groupName) throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransferTarget getTransferTarget(String name) throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean targetExists(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteTransferTarget(String name) throws TransferException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TransferTarget saveTransferTarget(TransferTarget update) throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enableTransferTarget(String name, boolean enable) throws TransferException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelAsync(String transferId) {
		// TODO Auto-generated method stub
		
	}

}
