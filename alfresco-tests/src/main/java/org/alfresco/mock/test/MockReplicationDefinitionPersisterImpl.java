package org.alfresco.mock.test;

import java.util.List;
import org.alfresco.repo.replication.ReplicationDefinitionPersisterImpl;
import org.alfresco.service.cmr.replication.ReplicationDefinition;

/**
 * Mock implementation of MockReplicationDefinitionPersisterImpl for testing purposes.
 *
 * @author vige
 */
public class MockReplicationDefinitionPersisterImpl extends ReplicationDefinitionPersisterImpl {

  /**
   * Save replication definition.
   *
   * @param replicationDefinition the replication definition
   */
  @Override
  public void saveReplicationDefinition(ReplicationDefinition replicationDefinition) {
    // TODO Auto-generated method stub

  }

  /**
   * Delete replication definition.
   *
   * @param replicationDefinition the replication definition
   */
  @Override
  public void deleteReplicationDefinition(ReplicationDefinition replicationDefinition) {
    // TODO Auto-generated method stub

  }

  /**
   * Rename replication definition.
   *
   * @param oldReplicationName the old replication name
   * @param newReplicationName the new replication name
   */
  @Override
  public void renameReplicationDefinition(String oldReplicationName, String newReplicationName) {
    // TODO Auto-generated method stub

  }

  /**
   * Load replication definition.
   *
   * @param replicationName the replication name
   * @return the result
   */
  @Override
  public ReplicationDefinition loadReplicationDefinition(String replicationName) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Load replication definitions.
   *
   * @return the result
   */
  @Override
  public List<ReplicationDefinition> loadReplicationDefinitions() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Load replication definitions.
   *
   * @param targetName the target name
   * @return the result
   */
  @Override
  public List<ReplicationDefinition> loadReplicationDefinitions(String targetName) {
    // TODO Auto-generated method stub
    return null;
  }
}
