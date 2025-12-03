package org.alfresco.mock.test;

import java.util.List;

import org.alfresco.repo.replication.ReplicationDefinitionPersisterImpl;
import org.alfresco.service.cmr.replication.ReplicationDefinition;

/**
 * Mock implementation of ReplicationDefinitionPersisterImpl for testing purposes.
 * 
 * @author vige
 */
public class MockReplicationDefinitionPersisterImpl extends ReplicationDefinitionPersisterImpl {

	@Override
	/**
	 * Save replication definition.
	 *
	 * @param replicationDefinition the replication definition
	 */
	public void saveReplicationDefinition(ReplicationDefinition replicationDefinition) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Delete replication definition.
	 *
	 * @param replicationDefinition the replication definition
	 */
	public void deleteReplicationDefinition(ReplicationDefinition replicationDefinition) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Rename replication definition.
	 *
	 * @param oldReplicationName the old replication name
	 * @param newReplicationName the new replication name
	 */
	public void renameReplicationDefinition(String oldReplicationName, String newReplicationName) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Load replication definition.
	 *
	 * @param replicationName the replication name
	 * @return the replication definition
	 */
	public ReplicationDefinition loadReplicationDefinition(String replicationName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Load replication definitions.
	 *
	 * @return the list
	 */
	public List<ReplicationDefinition> loadReplicationDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Load replication definitions.
	 *
	 * @param targetName the target name
	 * @return the list
	 */
	public List<ReplicationDefinition> loadReplicationDefinitions(String targetName) {
		// TODO Auto-generated method stub
		return null;
	}

}
