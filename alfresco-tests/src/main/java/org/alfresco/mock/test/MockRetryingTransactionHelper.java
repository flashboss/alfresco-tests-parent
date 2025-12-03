package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.repo.transaction.RetryingTransactionHelper;

/**
 * Mock implementation of RetryingTransactionHelper for testing purposes.
 * Executes callbacks directly without actual transaction handling.
 *
 * @author lucastancapiano
 */
public class MockRetryingTransactionHelper extends RetryingTransactionHelper implements Serializable {

	/**
	 * {@inheritDoc}
	 * Executes the callback directly without actual transaction handling.
	 *
	 * @param cb the callback to execute
	 * @param readOnly whether the transaction is read-only
	 * @param requiresNew whether a new transaction is required
	 * @param <R> the return type
	 * @return the result of the callback execution
	 */
	@Override
	public <R> R doInTransaction(RetryingTransactionCallback<R> cb, boolean readOnly, boolean requiresNew) {
		try {
			return cb.execute();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
}
