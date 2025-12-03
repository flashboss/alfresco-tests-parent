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

	@Override
	/**
	 * Get page.
	 *
	 * @return the list
	 */
	public List<R> getPage() {
		return files;
	}

	@Override
	/**
	 * Has more items.
	 *
	 * @return the boolean
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
	 * @return the string
	 */
	public String getQueryExecutionId() {
		// TODO Auto-generated method stub
		return null;
	}

}
