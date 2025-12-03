package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;

import org.alfresco.query.PagingResults;
import org.alfresco.util.Pair;

/**
* Mock implementation of the MockPagingResults class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockPagingResults<R> implements PagingResults<R>, Serializable {

/**
* The files list.
 */
	private List<R> files;

/**
* Constructs a new MockPagingResults instance.
*
* @param files the list of files
 */
	public MockPagingResults(List<R> files) {
		this.files = files;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public List<R> getPage() {
		return files;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public boolean hasMoreItems() {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Pair<Integer, Integer> getTotalResultCount() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getQueryExecutionId() {
		// TODO Auto-generated method stub
		return null;
	}

}
