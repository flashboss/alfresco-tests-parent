package org.alfresco.mock.test;

import java.util.List;

import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.scheduled.ScheduledPersistedAction;
import org.alfresco.service.cmr.action.scheduled.ScheduledPersistedActionService;

/**
 * @author vige
 */
public class MockScheduledPersistedActionService implements ScheduledPersistedActionService {

	@Override
	public ScheduledPersistedAction createSchedule(Action persistedAction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSchedule(ScheduledPersistedAction schedule) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSchedule(ScheduledPersistedAction schedule) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ScheduledPersistedAction getSchedule(Action persistedAction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduledPersistedAction> listSchedules() {
		// TODO Auto-generated method stub
		return null;
	}

}
