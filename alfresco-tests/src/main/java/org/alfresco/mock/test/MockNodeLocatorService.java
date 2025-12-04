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
 * Mock implementation of the Alfresco NodeLocatorService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockNodeLocatorService implements NodeLocatorService, Serializable {

	@Autowired
	private SearchService searchService;

	@Override
 /**
 * Get node.
 *
 * @param locatorName the locator name
 * @param source the source
 * @param params the params
 * @return the node ref
 */
	public NodeRef getNode(String locatorName, NodeRef source, Map<String, Serializable> params) {
  /**
  * Get node.
  *
  * @param locatorName the locator name
  * @param source the source
  * @param params the params
  * @return the node ref
  */
		if (params != null && params.get("query") != null)
			return searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, locatorName, params.get("query") + "")
					.getNodeRef(0);
		else
			return null;
	}

	@Override
 /**
 * Register.
 *
 * @param locatorName the locator name
 * @param locator the locator
 */
	public void register(String locatorName, NodeLocator locator) {
		// TODO Auto-generated method stub

	}

}
