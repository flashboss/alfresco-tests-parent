package org.alfresco.mock.test;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

public class MockTransactionManager implements PlatformTransactionManager {
	
	private TransactionStatus transactionStatus = new MockTransactionStatus();

	@Override
	public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
		return transactionStatus;
	}

	@Override
	public void commit(TransactionStatus status) throws TransactionException {
		// TODO Auto-generated method stub

	}

	@Override
	public void rollback(TransactionStatus status) throws TransactionException {
		// TODO Auto-generated method stub

	}
	
	public class MockTransactionStatus implements TransactionStatus {

		@Override
		public Object createSavepoint() throws TransactionException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void rollbackToSavepoint(Object savepoint) throws TransactionException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void releaseSavepoint(Object savepoint) throws TransactionException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isNewTransaction() {
			return true;
		}

		@Override
		public boolean hasSavepoint() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void setRollbackOnly() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isRollbackOnly() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void flush() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isCompleted() {
			return true;
		}
		
	}

}
