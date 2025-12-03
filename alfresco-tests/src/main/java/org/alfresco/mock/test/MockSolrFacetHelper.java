package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;

import org.alfresco.repo.search.impl.solr.facet.FacetQueryProvider;
import org.alfresco.repo.search.impl.solr.facet.SolrFacetHelper;

/**
 * Mock implementation of the MockSolrFacetHelper class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockSolrFacetHelper extends SolrFacetHelper implements Serializable {

	public MockSolrFacetHelper(List<FacetQueryProvider> facetQueryProviders) {
		super(facetQueryProviders);
	}
}
