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
 * Mock implementation of MockNodeLocatorService for testing purposes.
 *
 * @author vige
 */
public class MockNodeLocatorService implements NodeLocatorService, Serializable {

  /** The search service. */
  @Autowired private SearchService searchService;

  /**
   * Get node.
   *
   * @param locatorName the locator name
   * @param source the source
   * @param params the params
   * @return the result
   */
  @Override
  public NodeRef getNode(String locatorName, NodeRef source, Map<String, Serializable> params) {
    if (params != null && params.get("query") != null)
      return searchService
          .query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, locatorName, params.get("query") + "")
          .getNodeRef(0);
    else return null;
  }

  /**
   * Register.
   *
   * @param locatorName the locator name
   * @param locator the locator
   */
  @Override
  public void register(String locatorName, NodeLocator locator) {
    // TODO Auto-generated method stub

  }
}
