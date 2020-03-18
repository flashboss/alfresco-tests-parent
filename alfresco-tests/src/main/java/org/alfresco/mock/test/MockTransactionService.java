package org.alfresco.mock.test;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.alfresco.repo.transaction.RetryingTransactionHelper;
import org.alfresco.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

public class MockTransactionService implements TransactionService {
	
	private MockUserTransaction userTransaction = new MockUserTransaction();

	@Autowired
	private RetryingTransactionHelper retryingTransactionHelper;

	@Override
	public boolean getAllowWrite() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserTransaction getUserTransaction() {
		return userTransaction;
	}

	@Override
	public UserTransaction getUserTransaction(boolean readOnly) {
		return getUserTransaction();
	}

	@Override
	public UserTransaction getUserTransaction(boolean readOnly, boolean ignoreSystemReadOnly) {
		return getUserTransaction();
	}

	@Override
	public UserTransaction getNonPropagatingUserTransaction() {
		return getUserTransaction();
	}

	@Override
	public UserTransaction getNonPropagatingUserTransaction(boolean readOnly) {
		return getUserTransaction();
	}

	@Override
	public UserTransaction getNonPropagatingUserTransaction(boolean readOnly, boolean ignoreSystemReadOnly) {
		return getUserTransaction();
	}

	@Override
	public RetryingTransactionHelper getRetryingTransactionHelper() {
		return retryingTransactionHelper;
	}

	public class MockUserTransaction implements UserTransaction {

		@Override
		public void begin() throws NotSupportedException, SystemException {
			// TODO Auto-generated method stub

		}

		@Override
		public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException,
				SecurityException, IllegalStateException, SystemException {
			// TODO Auto-generated method stub

		}

		@Override
		public int getStatus() throws SystemException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void rollback() throws IllegalStateException, SecurityException, SystemException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setRollbackOnly() throws IllegalStateException, SystemException {
			// TODO Auto-generated method stub

		}

		@Override
		public void setTransactionTimeout(int arg0) throws SystemException {
			// TODO Auto-generated method stub

		}

	}

}
