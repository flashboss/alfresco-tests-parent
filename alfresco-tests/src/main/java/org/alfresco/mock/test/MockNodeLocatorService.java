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
 * Mock implementation of the MockNodeLocatorService class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockNodeLocatorService implements NodeLocatorService, Serializable {

	@Autowired
	private SearchService searchService;

	/**


	 * {@inheritDoc}


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


	 */


	@Override
	public void register(String locatorName, NodeLocator locator) {
		// TODO Auto-generated method stub

	}

}
