package org.alfresco.mock.test;

import org.alfresco.repo.lock.JobLockService;
import org.alfresco.service.namespace.QName;

/**
* Mock implementation of the MockJobLockServiceImpl class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockJobLockServiceImpl implements JobLockService {

/**
* {@inheritDoc}
* @param lockQName the lockQName
* @param timeToLive the timeToLive
 */
	@Override
	public void getTransactionalLock(QName lockQName, long timeToLive) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param lockQName the lockQName
* @param timeToLive the timeToLive
* @param retryWait the retryWait
* @param retryCount the retryCount
 */
	@Override
	public void getTransactionalLock(QName lockQName, long timeToLive, long retryWait, int retryCount) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param lockQName the lockQName
* @param timeToLive the timeToLive
* @return the result
 */
	@Override
	public String getLock(QName lockQName, long timeToLive) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param lockQName the lockQName
* @param timeToLive the timeToLive
* @param retryWait the retryWait
* @param retryCount the retryCount
* @return the result
 */
	@Override
	public String getLock(QName lockQName, long timeToLive, long retryWait, int retryCount) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param lockQName the lockQName
* @param timeToLive the timeToLive
* @param callback the callback
* @return the result
 */
	@Override
	public String getLock(QName lockQName, long timeToLive, JobLockRefreshCallback callback) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param lockToken the lockToken
* @param lockQName the lockQName
* @param timeToLive the timeToLive
 */
	@Override
	public void refreshLock(String lockToken, QName lockQName, long timeToLive) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param lockToken the lockToken
* @param lockQName the lockQName
* @param timeToLive the timeToLive
* @param callback the callback
 */
	@Override
	public void refreshLock(String lockToken, QName lockQName, long timeToLive, JobLockRefreshCallback callback) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param lockToken the lockToken
* @param lockQName the lockQName
 */
	@Override
	public void releaseLock(String lockToken, QName lockQName) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param lockToken the lockToken
* @param lockQName the lockQName
* @return the result
 */
	@Override
	public boolean releaseLockVerify(String lockToken, QName lockQName) {
		// TODO Auto-generated method stub
		return false;
	}

}
