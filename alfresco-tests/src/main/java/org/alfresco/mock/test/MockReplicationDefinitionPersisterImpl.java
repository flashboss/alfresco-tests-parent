package org.alfresco.mock.test;

import java.util.List;

import org.alfresco.repo.replication.ReplicationDefinitionPersisterImpl;
import org.alfresco.service.cmr.replication.ReplicationDefinition;

/**
 * Mock implementation of ReplicationDefinitionPersisterImpl for testing purposes.
 * Provides stub methods for replication definition persistence.
 *
 * @author lucastancapiano
 */
public class MockReplicationDefinitionPersisterImpl extends ReplicationDefinitionPersisterImpl {

	/**
	 * {@inheritDoc}
	 *
	 * @param replicationDefinition the replication definition to save
	 */
	@Override
	public void saveReplicationDefinition(ReplicationDefinition replicationDefinition) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param replicationDefinition the replication definition to delete
	 */
	@Override
	public void deleteReplicationDefinition(ReplicationDefinition replicationDefinition) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param oldReplicationName the old replication name
	 * @param newReplicationName the new replication name
	 */
	@Override
	public void renameReplicationDefinition(String oldReplicationName, String newReplicationName) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param replicationName the replication name to load
	 * @return the replication definition
	 */
	@Override
	public ReplicationDefinition loadReplicationDefinition(String replicationName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return all replication definitions
	 */
	@Override
	public List<ReplicationDefinition> loadReplicationDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param targetName the target name to filter by
	 * @return the replication definitions for the target
	 */
	@Override
	public List<ReplicationDefinition> loadReplicationDefinitions(String targetName) {
		// TODO Auto-generated method stub
		return null;
	}

}
