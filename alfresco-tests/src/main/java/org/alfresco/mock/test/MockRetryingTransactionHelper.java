package org.alfresco.mock.test;

import java.io.Serializable;
import org.alfresco.repo.transaction.RetryingTransactionHelper;

/**
 * Mock implementation of RetryingTransactionHelper for testing purposes.
 *
 * @author vige
 */
public class MockRetryingTransactionHelper extends RetryingTransactionHelper
    implements Serializable {
  /**
   * Do in transaction.
   *
   * @param cb the cb
   * @param readOnly the read only
   * @param requiresNew the requires new
   */
  @Override
  public <R> R doInTransaction(
      RetryingTransactionCallback<R> cb, boolean readOnly, boolean requiresNew) {
    try {
      return cb.execute();
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return null;
  }
}
