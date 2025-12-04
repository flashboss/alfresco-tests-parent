package org.alfresco.mock.test;

import java.util.List;

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.scheduled.ScheduledPersistedAction;
import org.alfresco.service.cmr.action.scheduled.ScheduledPersistedActionService;
import org.alfresco.service.cmr.repository.NodeRef;

/**
 * Mock implementation of the Alfresco ScheduledPersistedActionService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockScheduledPersistedActionService implements ScheduledPersistedActionService {

	@Override
 /**
 * Create schedule.
 *
 * @param persistedAction the persisted action
 * @return the scheduled persisted action
 */
	public ScheduledPersistedAction createSchedule(Action persistedAction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Save schedule.
 *
 * @param schedule the schedule
 */
	public void saveSchedule(ScheduledPersistedAction schedule) {
		// TODO Auto-generated method stub
		
	}

	@Override
 /**
 * Delete schedule.
 *
 * @param schedule the schedule
 */
	public void deleteSchedule(ScheduledPersistedAction schedule) {
		// TODO Auto-generated method stub
		
	}

	@Override
 /**
 * Get schedule.
 *
 * @param persistedAction the persisted action
 * @return the scheduled persisted action
 */
	public ScheduledPersistedAction getSchedule(Action persistedAction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get schedule.
 *
 * @param persistedActionNodeRef the persisted action node ref
 * @return the scheduled persisted action
 */
	public ScheduledPersistedAction getSchedule(NodeRef persistedActionNodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * List schedules.
 *
 * @return the list
 */
	public List<ScheduledPersistedAction> listSchedules() {
		// TODO Auto-generated method stub
		return null;
	}

}
