package org.alfresco.mock.test;

import java.util.List;

import org.alfresco.repo.replication.ReplicationDefinitionPersisterImpl;
import org.alfresco.service.cmr.replication.ReplicationDefinition;

/**
 * Mock implementation of ReplicationDefinitionPersisterImpl for testing purposes.
 * Provides replication definition persistence stub.
 * 
 * @author vige
 */
public class MockReplicationDefinitionPersisterImpl extends ReplicationDefinitionPersisterImpl {

	@Override
	public void saveReplicationDefinition(ReplicationDefinition replicationDefinition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReplicationDefinition(ReplicationDefinition replicationDefinition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void renameReplicationDefinition(String oldReplicationName, String newReplicationName) {
		// TODO Auto-generated method stub

	}

	@Override
	public ReplicationDefinition loadReplicationDefinition(String replicationName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReplicationDefinition> loadReplicationDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReplicationDefinition> loadReplicationDefinitions(String targetName) {
		// TODO Auto-generated method stub
		return null;
	}

}
