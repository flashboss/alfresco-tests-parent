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

  /**
   * Create schedule.
   *
   * @param persistedAction the persisted action
   * @return the result
   */
  @Override
  public ScheduledPersistedAction createSchedule(Action persistedAction) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Save schedule.
   *
   * @param schedule the schedule
   */
  @Override
  public void saveSchedule(ScheduledPersistedAction schedule) {
    // TODO Auto-generated method stub

  }

  /**
   * Delete schedule.
   *
   * @param schedule the schedule
   */
  @Override
  public void deleteSchedule(ScheduledPersistedAction schedule) {
    // TODO Auto-generated method stub

  }

  /**
   * Get schedule.
   *
   * @param persistedAction the persisted action
   * @return the result
   */
  @Override
  public ScheduledPersistedAction getSchedule(Action persistedAction) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * List schedules.
   *
   * @return the result
   */
  @Override
  public List<ScheduledPersistedAction> listSchedules() {
    // TODO Auto-generated method stub
    return null;
  }
}
