package org.alfresco.mock.test;

import java.util.List;
import org.alfresco.repo.replication.ReplicationDefinitionPersisterImpl;
import org.alfresco.service.cmr.replication.ReplicationDefinition;

/**
 * Mock implementation of the MockReplicationDefinitionPersisterImpl class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests to run without
 * requiring a full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockReplicationDefinitionPersisterImpl extends ReplicationDefinitionPersisterImpl {

  /**
   * {@inheritDoc}
   *
   * @param replicationDefinition the replicationDefinition
   */
  @Override
  public void saveReplicationDefinition(ReplicationDefinition replicationDefinition) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param replicationDefinition the replicationDefinition
   */
  @Override
  public void deleteReplicationDefinition(ReplicationDefinition replicationDefinition) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param oldReplicationName the oldReplicationName
   * @param newReplicationName the newReplicationName
   */
  @Override
  public void renameReplicationDefinition(String oldReplicationName, String newReplicationName) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param replicationName the replicationName
   * @return the result
   */
  @Override
  public ReplicationDefinition loadReplicationDefinition(String replicationName) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public List<ReplicationDefinition> loadReplicationDefinitions() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param targetName the targetName
   * @return the result
   */
  @Override
  public List<ReplicationDefinition> loadReplicationDefinitions(String targetName) {
    // TODO Auto-generated method stub
    return null;
  }
}
