package org.alfresco.mock.test;

import org.alfresco.repo.lock.JobLockService;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of MockJobLockServiceImpl for testing purposes.
 *
 * @author vige
 */
public class MockJobLockServiceImpl implements JobLockService {

  /**
   * Get transactional lock.
   *
   * @param lockQName the lock q name
   * @param timeToLive the time to live
   */
  @Override
  public void getTransactionalLock(QName lockQName, long timeToLive) {
    // TODO Auto-generated method stub

  }

  /**
   * Get transactional lock.
   *
   * @param lockQName the lock q name
   * @param timeToLive the time to live
   * @param retryWait the retry wait
   * @param retryCount the retry count
   */
  @Override
  public void getTransactionalLock(
      QName lockQName, long timeToLive, long retryWait, int retryCount) {
    // TODO Auto-generated method stub

  }

  /**
   * Get lock.
   *
   * @param lockQName the lock q name
   * @param timeToLive the time to live
   * @return the result
   */
  @Override
  public String getLock(QName lockQName, long timeToLive) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get lock.
   *
   * @param lockQName the lock q name
   * @param timeToLive the time to live
   * @param retryWait the retry wait
   * @param retryCount the retry count
   * @return the result
   */
  @Override
  public String getLock(QName lockQName, long timeToLive, long retryWait, int retryCount) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Refresh lock.
   *
   * @param lockToken the lock token
   * @param lockQName the lock q name
   * @param timeToLive the time to live
   */
  @Override
  public void refreshLock(String lockToken, QName lockQName, long timeToLive) {
    // TODO Auto-generated method stub

  }

  /**
   * Refresh lock.
   *
   * @param lockToken the lock token
   * @param lockQName the lock q name
   * @param timeToLive the time to live
   * @param callback the callback
   */
  @Override
  public void refreshLock(
      String lockToken, QName lockQName, long timeToLive, JobLockRefreshCallback callback) {
    // TODO Auto-generated method stub

  }

  /**
   * Release lock.
   *
   * @param lockToken the lock token
   * @param lockQName the lock q name
   */
  @Override
  public void releaseLock(String lockToken, QName lockQName) {
    // TODO Auto-generated method stub

  }

  /**
   * Release lock verify.
   *
   * @param lockToken the lock token
   * @param lockQName the lock q name
   * @return the result
   */
  @Override
  public boolean releaseLockVerify(String lockToken, QName lockQName) {
    // TODO Auto-generated method stub
    return false;
  }
}
