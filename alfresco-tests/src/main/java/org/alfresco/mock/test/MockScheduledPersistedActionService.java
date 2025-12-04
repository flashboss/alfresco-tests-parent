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
  /**
   * Create schedule.
   *
   * @param persistedAction the persisted action
   * @return the scheduled persisted action
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
   * @return the scheduled persisted action
   */
  @Override
  public ScheduledPersistedAction getSchedule(Action persistedAction) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get schedule.
   *
   * @param persistedActionNodeRef the persisted action node ref
   * @return the scheduled persisted action
   */
  @Override
  public ScheduledPersistedAction getSchedule(NodeRef persistedActionNodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * List schedules.
   *
   * @return the list
   */
  @Override
  public List<ScheduledPersistedAction> listSchedules() {
    // TODO Auto-generated method stub
    return null;
  }
}
