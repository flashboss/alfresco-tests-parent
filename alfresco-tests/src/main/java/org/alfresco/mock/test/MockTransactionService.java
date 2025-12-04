package org.alfresco.mock.test;

import java.io.Serializable;

import javax.transaction.UserTransaction;

import org.alfresco.repo.transaction.RetryingTransactionHelper;
import org.alfresco.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mock implementation of the Alfresco TransactionService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockTransactionService implements TransactionService, Serializable {

	/** The retrying transaction helper. */
	@Autowired
	private RetryingTransactionHelper retryingTransactionHelper;	/**
	 * Get allow write.
	 *
	 * @return the boolean
	 */


	@Override
	public boolean getAllowWrite() {
		// TODO Auto-generated method stub
		return false;
	}	/**
	 * Is read only.
	 *
	 * @return the boolean
	 */


	@Override
	public boolean isReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}	/**
	 * Get user transaction.
	 *
	 * @return the user transaction
	 */


	@Override
	public UserTransaction getUserTransaction() {
		return new MockUserTransaction();
	}	/**
	 * Get user transaction.
	 *
	 * @param readOnly the read only
	 * @return the user transaction
	 */


	@Override
	public UserTransaction getUserTransaction(boolean readOnly) {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get user transaction.
	 *
	 * @param readOnly the read only
	 * @param ignoreSystemReadOnly the ignore system read only
	 * @return the user transaction
	 */


	@Override
	public UserTransaction getUserTransaction(boolean readOnly, boolean ignoreSystemReadOnly) {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get non propagating user transaction.
	 *
	 * @return the user transaction
	 */


	@Override
	public UserTransaction getNonPropagatingUserTransaction() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get non propagating user transaction.
	 *
	 * @param readOnly the read only
	 * @return the user transaction
	 */


	@Override
	public UserTransaction getNonPropagatingUserTransaction(boolean readOnly) {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get non propagating user transaction.
	 *
	 * @param readOnly the read only
	 * @param ignoreSystemReadOnly the ignore system read only
	 * @return the user transaction
	 */


	@Override
	public UserTransaction getNonPropagatingUserTransaction(boolean readOnly, boolean ignoreSystemReadOnly) {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get retrying transaction helper.
	 *
	 * @return the retrying transaction helper
	 */


	@Override
	public RetryingTransactionHelper getRetryingTransactionHelper() {
		return retryingTransactionHelper;
	}

	/**
	 * Set retrying transaction helper.
	 *
	 * @param retryingTransactionHelper the retrying transaction helper
	 */
	public void setRetryingTransactionHelper(RetryingTransactionHelper retryingTransactionHelper) {
		this.retryingTransactionHelper = retryingTransactionHelper;
	}

}