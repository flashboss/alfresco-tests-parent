package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;

import org.alfresco.repo.search.impl.solr.facet.FacetQueryProvider;
import org.alfresco.repo.search.impl.solr.facet.SolrFacetHelper;

/**
 * Mock implementation of SolrFacetHelper for testing purposes.
 * 
 * @author vige
 */
public class MockSolrFacetHelper extends SolrFacetHelper implements Serializable {

 /**
 * Constructs a new mock solr facet helper.
 *
 * @param facetQueryProviders the facet query providers
 * @return the result
 */
	public MockSolrFacetHelper(List<FacetQueryProvider> facetQueryProviders) {
		super(facetQueryProviders);
	}
}
