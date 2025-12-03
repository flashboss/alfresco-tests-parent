package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Map;

import org.alfresco.repo.nodelocator.NodeLocator;
import org.alfresco.repo.nodelocator.NodeLocatorService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mock implementation of NodeLocatorService for testing purposes.
 * Provides node location functionality using search queries.
 *
 * @author lucastancapiano
 */
public class MockNodeLocatorService implements NodeLocatorService, Serializable {

	/** The search service. */
	@Autowired
	private SearchService searchService;

	/**
	 * {@inheritDoc}
	 *
	 * @param locatorName the name of the locator
	 * @param source the source node reference
	 * @param params the parameters including the query
	 * @return the located node reference
	 */
	@Override
	public NodeRef getNode(String locatorName, NodeRef source, Map<String, Serializable> params) {
		if (params != null && params.get("query") != null)
			return searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, locatorName, params.get("query") + "")
					.getNodeRef(0);
		else
			return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param locatorName the locator name to register
	 * @param locator the node locator implementation
	 */
	@Override
	public void register(String locatorName, NodeLocator locator) {
		// TODO Auto-generated method stub
	}

}
