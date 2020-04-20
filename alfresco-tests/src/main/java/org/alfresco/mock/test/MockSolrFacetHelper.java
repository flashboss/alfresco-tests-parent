package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;

import org.alfresco.repo.search.impl.solr.facet.FacetQueryProvider;
import org.alfresco.repo.search.impl.solr.facet.SolrFacetHelper;

public class MockSolrFacetHelper extends SolrFacetHelper implements Serializable {

	public MockSolrFacetHelper(List<FacetQueryProvider> facetQueryProviders) {
		super(facetQueryProviders);
	}

}