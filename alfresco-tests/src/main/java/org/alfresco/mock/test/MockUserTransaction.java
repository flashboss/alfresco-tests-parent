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

	@Override
 /** Begin. */
	public void begin() throws NotSupportedException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException,
			SecurityException, IllegalStateException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
 /**
 * Get status.
 *
 * @return the int
 */
	public int getStatus() throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
 /** Rollback. */
	public void rollback() throws IllegalStateException, SecurityException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
 /** Set rollback only. */
	public void setRollbackOnly() throws IllegalStateException, SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
 /**
 * Set transaction timeout.
 *
 * @param arg0 the arg0
 */
	public void setTransactionTimeout(int arg0) throws SystemException {
		// TODO Auto-generated method stub
		
	}

}
