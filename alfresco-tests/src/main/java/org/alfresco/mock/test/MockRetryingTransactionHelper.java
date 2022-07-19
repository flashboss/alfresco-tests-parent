package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.repo.transaction.RetryingTransactionHelper;

public class MockRetryingTransactionHelper extends RetryingTransactionHelper implements Serializable {

	@Override
    public <R> R doInTransaction(RetryingTransactionCallback<R> cb, boolean readOnly, boolean requiresNew)
    {
    	return null;
    }
}
