package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;
import org.alfresco.query.PagingResults;
import org.alfresco.util.Pair;

/**
 * Mock implementation of PagingResults for testing purposes.
 *
 * @author vige
 */
public class MockPagingResults<R> implements PagingResults<R>, Serializable {
  /** The files. */
  private List<R> files;

  /**
   * Constructs a new mock paging results.
   *
   * @param files the files
   * @return the result
   */
  public MockPagingResults(List<R> files) {
    this.files = files;
  }

  /**
   * Get page.
   *
   * @return the list
   */
  @Override
  public List<R> getPage() {
    return files;
  }

  /**
   * Has more items.
   *
   * @return the boolean
   */
  @Override
  public boolean hasMoreItems() {
    // TODO Auto-generated method stub
    return false;
  }

  /** Get total result count. */
  @Override
  public Pair<Integer, Integer> getTotalResultCount() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get query execution id.
   *
   * @return the string
   */
  @Override
  public String getQueryExecutionId() {
    // TODO Auto-generated method stub
    return null;
  }
}
