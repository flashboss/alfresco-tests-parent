package org.alfresco.mock.test;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Mock implementation of UserTransaction for testing purposes.
 *
 * @author vige
 */
public class MockUserTransaction implements UserTransaction {
  /** Begin. */
  @Override
  public void begin() throws NotSupportedException, SystemException {
    // TODO Auto-generated method stub

  }

  /** Commit. */
  @Override
  public void commit()
      throws RollbackException,
          HeuristicMixedException,
          HeuristicRollbackException,
          SecurityException,
          IllegalStateException,
          SystemException {
    // TODO Auto-generated method stub

  }

  /**
   * Get status.
   *
   * @return the int
   */
  @Override
  public int getStatus() throws SystemException {
    // TODO Auto-generated method stub
    return 0;
  }

  /** Rollback. */
  @Override
  public void rollback() throws IllegalStateException, SecurityException, SystemException {
    // TODO Auto-generated method stub

  }

  /** Set rollback only. */
  @Override
  public void setRollbackOnly() throws IllegalStateException, SystemException {
    // TODO Auto-generated method stub

  }

  /**
   * Set transaction timeout.
   *
   * @param arg0 the arg0
   */
  @Override
  public void setTransactionTimeout(int arg0) throws SystemException {
    // TODO Auto-generated method stub

  }
}
