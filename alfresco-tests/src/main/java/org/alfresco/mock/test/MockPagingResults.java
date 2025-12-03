package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;

import org.alfresco.query.PagingResults;
import org.alfresco.util.Pair;

/**
 * Mock implementation of MockPagingResults for testing purposes.
 *
 * @author vige
 */
public class MockPagingResults<R> implements PagingResults<R>, Serializable {

	/** The files. */
	private List<R> files;

	/**
	 * Constructs a new MockPagingResults.
	 *
	 * @param files the files
	 */
	public MockPagingResults(List<R> files) {
		this.files = files;
	}

	@Override
	/**
	 * Get page.
	 *
	 * @return the result
	 */
	public List<R> getPage() {
		return files;
	}

	@Override
	/**
	 * Has more items.
	 *
	 * @return the result
	 */
	public boolean hasMoreItems() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get total result count.
	 *
	 */
	public Pair<Integer, Integer> getTotalResultCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get query execution id.
	 *
	 * @return the result
	 */
	public String getQueryExecutionId() {
		// TODO Auto-generated method stub
		return null;
	}

}
