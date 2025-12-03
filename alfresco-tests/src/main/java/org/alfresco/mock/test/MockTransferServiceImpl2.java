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
 * Mock implementation of the MockTransferServiceImpl2 class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockTransferServiceImpl2 implements TransferService2 {

	/**


	 * {@inheritDoc}


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


	 */


	@Override
	public TransferEndEvent transfer(String targetName, TransferDefinition definition, TransferCallback... callbacks)
			throws TransferFailureException {
		return transfer(targetName, definition, Arrays.asList(callbacks));
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void transferAsync(String targetName, TransferDefinition definition, Collection<TransferCallback> callback)
			throws TransferException {
		// TODO Auto-generated method stub
		
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void transferAsync(String targetName, TransferDefinition definition, TransferCallback... callbacks)
			throws TransferException {
		// TODO Auto-generated method stub
		
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void verify(TransferTarget target) throws TransferException {
		// TODO Auto-generated method stub
		
	}

	/**


	 * {@inheritDoc}


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


	 */


	@Override
	public TransferTarget createTransferTarget(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Set<TransferTarget> getTransferTargets() throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public Set<TransferTarget> getTransferTargets(String groupName) throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public TransferTarget getTransferTarget(String name) throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public boolean targetExists(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void deleteTransferTarget(String name) throws TransferException {
		// TODO Auto-generated method stub
		
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public TransferTarget saveTransferTarget(TransferTarget update) throws TransferException {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void enableTransferTarget(String name, boolean enable) throws TransferException {
		// TODO Auto-generated method stub
		
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void cancelAsync(String transferId) {
		// TODO Auto-generated method stub
		
	}

}
