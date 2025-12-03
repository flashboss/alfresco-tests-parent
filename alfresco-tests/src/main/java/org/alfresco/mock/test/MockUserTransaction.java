package org.alfresco.mock.test;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
* Mock implementation of the MockUserTransaction class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockUserTransaction implements UserTransaction {

/**
* {@inheritDoc}
* @throws NotSupportedException if an error occurs
* @throws SystemException if an error occurs
 */
	@Override
	public void begin() throws NotSupportedException, SystemException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws RollbackException if an error occurs
	 * @throws HeuristicMixedException if an error occurs
	 * @throws HeuristicRollbackException if an error occurs
	 * @throws SecurityException if an error occurs
	 * @throws IllegalStateException if an error occurs
	 * @throws SystemException if an error occurs
	 */
	@Override
	public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException,
			SecurityException, IllegalStateException, SystemException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @return the result
* @throws SystemException if an error occurs
 */
	@Override
	public int getStatus() throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

/**
* {@inheritDoc}
* @throws IllegalStateException if an error occurs
* @throws SecurityException if an error occurs
* @throws SystemException if an error occurs
 */
	@Override
	public void rollback() throws IllegalStateException, SecurityException, SystemException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @throws IllegalStateException if an error occurs
* @throws SystemException if an error occurs
 */
	@Override
	public void setRollbackOnly() throws IllegalStateException, SystemException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param arg0 the arg0
* @throws SystemException if an error occurs
 */
	@Override
	public void setTransactionTimeout(int arg0) throws SystemException {
		// TODO Auto-generated method stub

	}

}
