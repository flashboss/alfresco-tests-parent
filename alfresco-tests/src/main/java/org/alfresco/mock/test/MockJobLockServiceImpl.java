package org.alfresco.mock.test;

import org.alfresco.repo.lock.JobLockService;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of the Alfresco JobLockServiceImpl for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockJobLockServiceImpl implements JobLockService {

	@Override
	public void getTransactionalLock(QName lockQName, long timeToLive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getTransactionalLock(QName lockQName, long timeToLive, long retryWait, int retryCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLock(QName lockQName, long timeToLive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLock(QName lockQName, long timeToLive, long retryWait, int retryCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLock(QName lockQName, long timeToLive, JobLockRefreshCallback callback) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshLock(String lockToken, QName lockQName, long timeToLive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshLock(String lockToken, QName lockQName, long timeToLive, JobLockRefreshCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void releaseLock(String lockToken, QName lockQName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean releaseLockVerify(String lockToken, QName lockQName) {
		// TODO Auto-generated method stub
		return false;
	}

}
