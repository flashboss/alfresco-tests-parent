package org.alfresco.mock.test;

import java.util.List;

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.scheduled.ScheduledPersistedAction;
import org.alfresco.service.cmr.action.scheduled.ScheduledPersistedActionService;
import org.alfresco.service.cmr.repository.NodeRef;

/**
 * Mock implementation of the MockScheduledPersistedActionService class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockScheduledPersistedActionService implements ScheduledPersistedActionService {

	/**


	 * {@inheritDoc}


	 */


	@Override
	public ScheduledPersistedAction createSchedule(Action persistedAction) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void saveSchedule(ScheduledPersistedAction schedule) {
		// TODO Auto-generated method stub
		
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void deleteSchedule(ScheduledPersistedAction schedule) {
		// TODO Auto-generated method stub
		
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public ScheduledPersistedAction getSchedule(Action persistedAction) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public ScheduledPersistedAction getSchedule(NodeRef persistedActionNodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public List<ScheduledPersistedAction> listSchedules() {
		// TODO Auto-generated method stub
		return null;
	}

}
