package org.alfresco.mock.test;

import java.util.List;

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.scheduled.ScheduledPersistedAction;
import org.alfresco.service.cmr.action.scheduled.ScheduledPersistedActionService;

/**
 * Mock implementation of ScheduledPersistedActionService for testing purposes.
 * Provides stub methods for scheduled action management.
 *
 * @author lucastancapiano
 */
public class MockScheduledPersistedActionService implements ScheduledPersistedActionService {

	/**
	 * {@inheritDoc}
	 *
	 * @param persistedAction the action to create schedule for
	 * @return the created schedule
	 */
	@Override
	public ScheduledPersistedAction createSchedule(Action persistedAction) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param schedule the schedule to save
	 */
	@Override
	public void saveSchedule(ScheduledPersistedAction schedule) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param schedule the schedule to delete
	 */
	@Override
	public void deleteSchedule(ScheduledPersistedAction schedule) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param persistedAction the action to get schedule for
	 * @return the schedule
	 */
	@Override
	public ScheduledPersistedAction getSchedule(Action persistedAction) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the list of schedules
	 */
	@Override
	public List<ScheduledPersistedAction> listSchedules() {
		// TODO Auto-generated method stub
		return null;
	}

}
