package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.repo.transaction.RetryingTransactionHelper;

/**
 * Mock implementation of RetryingTransactionHelper for testing purposes.
 * 
 * @author vige
 */
public class MockRetryingTransactionHelper extends RetryingTransactionHelper implements Serializable {

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
