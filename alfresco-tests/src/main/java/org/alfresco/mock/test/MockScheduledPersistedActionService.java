package org.alfresco.mock.test;

import java.util.List;

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.scheduled.ScheduledPersistedAction;
import org.alfresco.service.cmr.action.scheduled.ScheduledPersistedActionService;

/**
 * Mock implementation of MockScheduledPersistedActionService for testing purposes.
 *
 * @author vige
 */
public class MockScheduledPersistedActionService implements ScheduledPersistedActionService {

	@Override
	/**
	 * Create schedule.
	 *
	 * @param persistedAction the persisted action
	 * @return the result
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
	 * @return the result
	 */
	public ScheduledPersistedAction getSchedule(Action persistedAction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * List schedules.
	 *
	 * @return the result
	 */
	public List<ScheduledPersistedAction> listSchedules() {
		// TODO Auto-generated method stub
		return null;
	}

}
