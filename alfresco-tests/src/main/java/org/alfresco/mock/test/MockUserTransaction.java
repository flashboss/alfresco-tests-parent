package org.alfresco.mock.test;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Mock implementation of UserTransaction for testing purposes.
 * Provides stub transaction methods without actual transaction handling.
 *
 * @author lucastancapiano
 */
public class MockUserTransaction implements UserTransaction {

	/**
	 * {@inheritDoc}
	 *
	 * @throws NotSupportedException if not supported
	 * @throws SystemException if system error occurs
	 */
	@Override
	public void begin() throws NotSupportedException, SystemException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws RollbackException if rollback occurs
	 * @throws HeuristicMixedException if heuristic mixed
	 * @throws HeuristicRollbackException if heuristic rollback
	 * @throws SecurityException if security error
	 * @throws IllegalStateException if illegal state
	 * @throws SystemException if system error
	 */
	@Override
	public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException,
			SecurityException, IllegalStateException, SystemException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the transaction status
	 * @throws SystemException if system error occurs
	 */
	@Override
	public int getStatus() throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalStateException if illegal state
	 * @throws SecurityException if security error
	 * @throws SystemException if system error
	 */
	@Override
	public void rollback() throws IllegalStateException, SecurityException, SystemException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalStateException if illegal state
	 * @throws SystemException if system error
	 */
	@Override
	public void setRollbackOnly() throws IllegalStateException, SystemException {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param arg0 the timeout in seconds
	 * @throws SystemException if system error occurs
	 */
	@Override
	public void setTransactionTimeout(int arg0) throws SystemException {
		// TODO Auto-generated method stub
	}

}
