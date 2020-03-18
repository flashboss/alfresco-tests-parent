package org.alfresco.mock.test;

import org.alfresco.repo.transaction.RetryingTransactionHelper;

public class MockRetryingTransactionHelper extends RetryingTransactionHelper {

	@Override
	public <R> R doInTransaction(RetryingTransactionCallback<R> cb, boolean readOnly, boolean requiresNew) {
		return null;
	}
}
