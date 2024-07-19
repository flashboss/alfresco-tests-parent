package org.alfresco.mock.test;

import java.io.Serializable;

import jakarta.transaction.UserTransaction;

import org.alfresco.repo.transaction.RetryingTransactionHelper;
import org.alfresco.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

public class MockTransactionService implements TransactionService, Serializable {

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
		return new MockUserTransaction();
	}

	@Override
	public UserTransaction getUserTransaction(boolean readOnly) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTransaction getUserTransaction(boolean readOnly, boolean ignoreSystemReadOnly) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTransaction getNonPropagatingUserTransaction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTransaction getNonPropagatingUserTransaction(boolean readOnly) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTransaction getNonPropagatingUserTransaction(boolean readOnly, boolean ignoreSystemReadOnly) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RetryingTransactionHelper getRetryingTransactionHelper() {
		return retryingTransactionHelper;
	}

	public void setRetryingTransactionHelper(RetryingTransactionHelper retryingTransactionHelper) {
		this.retryingTransactionHelper = retryingTransactionHelper;
	}

}