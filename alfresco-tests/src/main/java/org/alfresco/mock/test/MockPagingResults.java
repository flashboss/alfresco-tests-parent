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

	private List<R> files;

	public MockPagingResults(List<R> files) {
		this.files = files;
	}

	@Override
	public List<R> getPage() {
		return files;
	}

	@Override
	public boolean hasMoreItems() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pair<Integer, Integer> getTotalResultCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryExecutionId() {
		// TODO Auto-generated method stub
		return null;
	}

}
