package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.repo.transaction.RetryingTransactionHelper;

/**
* Mock implementation of the MockRetryingTransactionHelper class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockRetryingTransactionHelper extends RetryingTransactionHelper implements Serializable {

/**
* {@inheritDoc}
* @param cb the cb
* @param readOnly the readOnly
* @param requiresNew the requiresNew
* @return the result
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
