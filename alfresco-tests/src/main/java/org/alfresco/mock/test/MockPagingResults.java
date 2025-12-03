package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;

import org.alfresco.query.PagingResults;
import org.alfresco.util.Pair;

/**
 * Mock implementation of PagingResults for testing purposes.
 * Wraps a list of results in a paging structure.
 *
 * @param <R> the type of results
 * @author lucastancapiano
 */
public class MockPagingResults<R> implements PagingResults<R>, Serializable {

	/** The list of files/results. */
	private List<R> files;

	/**
	 * Constructs a new MockPagingResults with the specified list.
	 *
	 * @param files the list of results
	 */
	public MockPagingResults(List<R> files) {
		this.files = files;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the page of results
	 */
	@Override
	public List<R> getPage() {
		return files;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if there are more items, false otherwise
	 */
	@Override
	public boolean hasMoreItems() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the total result count as a pair
	 */
	@Override
	public Pair<Integer, Integer> getTotalResultCount() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the query execution ID
	 */
	@Override
	public String getQueryExecutionId() {
		// TODO Auto-generated method stub
		return null;
	}

}
