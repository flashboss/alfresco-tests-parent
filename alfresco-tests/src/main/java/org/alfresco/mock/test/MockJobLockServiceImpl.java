package org.alfresco.mock.test;

import org.alfresco.repo.lock.JobLockService;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of MockJobLockServiceImpl for testing purposes.
 *
 * @author vige
 */
public class MockJobLockServiceImpl implements JobLockService {

	@Override
	/**
	 * Get transactional lock.
	 *
	 * @param lockQName the lock q name
	 * @param timeToLive the time to live
	 */
	public void getTransactionalLock(QName lockQName, long timeToLive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Get transactional lock.
	 *
	 * @param lockQName the lock q name
	 * @param timeToLive the time to live
	 * @param retryWait the retry wait
	 * @param retryCount the retry count
	 */
	public void getTransactionalLock(QName lockQName, long timeToLive, long retryWait, int retryCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Get lock.
	 *
	 * @param lockQName the lock q name
	 * @param timeToLive the time to live
	 * @return the result
	 */
	public String getLock(QName lockQName, long timeToLive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get lock.
	 *
	 * @param lockQName the lock q name
	 * @param timeToLive the time to live
	 * @param retryWait the retry wait
	 * @param retryCount the retry count
	 * @return the result
	 */
	public String getLock(QName lockQName, long timeToLive, long retryWait, int retryCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Refresh lock.
	 *
	 * @param lockToken the lock token
	 * @param lockQName the lock q name
	 * @param timeToLive the time to live
	 */
	public void refreshLock(String lockToken, QName lockQName, long timeToLive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Refresh lock.
	 *
	 * @param lockToken the lock token
	 * @param lockQName the lock q name
	 * @param timeToLive the time to live
	 * @param callback the callback
	 */
	public void refreshLock(String lockToken, QName lockQName, long timeToLive, JobLockRefreshCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Release lock.
	 *
	 * @param lockToken the lock token
	 * @param lockQName the lock q name
	 */
	public void releaseLock(String lockToken, QName lockQName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Release lock verify.
	 *
	 * @param lockToken the lock token
	 * @param lockQName the lock q name
	 * @return the result
	 */
	public boolean releaseLockVerify(String lockToken, QName lockQName) {
		// TODO Auto-generated method stub
		return false;
	}

}
