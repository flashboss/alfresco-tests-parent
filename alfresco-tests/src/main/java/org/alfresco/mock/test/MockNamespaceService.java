package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.service.namespace.NamespaceException;
import org.alfresco.service.namespace.NamespaceService;

/**
 * Mock implementation of the Alfresco NamespaceService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockNamespaceService implements NamespaceService, Serializable {

 /** The prefixes. */
	private static Map<String, String> prefixes = new HashMap<String, String>();

	@Override
 /**
 * Get namespace u r i.
 *
 * @param prefix the prefix
 * @return the string
 */
	public String getNamespaceURI(String prefix) throws NamespaceException {
  /**
  * Get namespace u r i.
  *
  * @param prefix the prefix
  * @return the string
  */
		return prefix != null && prefix.equals(NamespaceService.CONTENT_MODEL_PREFIX)
				? NamespaceService.CONTENT_MODEL_1_0_URI
				: prefixes.get(prefix);
	}

	@Override
 /**
 * Get prefixes.
 *
 * @param namespaceURI the namespace u r i
 * @return the collection
 */
	public Collection<String> getPrefixes(String namespaceURI) throws NamespaceException {
  /**
  * Get prefixes.
  *
  * @param namespaceURI the namespace u r i
  * @return the collection
  */
		List<String> results = new ArrayList<String>();
  /**
  * Get prefixes.
  *
  * @param namespaceURI the namespace u r i
  * @return the collection
  */
		for (String prefix : prefixes.keySet())
			if (prefixes.get(prefix).equals(namespaceURI))
				results.add(prefix);
		return results;
	}

	@Override
 /**
 * Get prefixes.
 *
 * @return the collection
 */
	public Collection<String> getPrefixes() {
  /**
  * Get prefixes.
  *
  * @return the collection
  */
		return prefixes.keySet();
	}

	@Override
 /**
 * Get u r is.
 *
 * @return the collection
 */
	public Collection<String> getURIs() {
  /**
  * Get u r is.
  *
  * @return the collection
  */
		return prefixes.values();
	}

	@Override
 /**
 * Register namespace.
 *
 * @param prefix the prefix
 * @param uri the uri
 */
	public void registerNamespace(String prefix, String uri) {
		prefixes.put(prefix, uri);
	}

	@Override
 /**
 * Unregister namespace.
 *
 * @param prefix the prefix
 */
	public void unregisterNamespace(String prefix) {
		// TODO Auto-generated method stub

	}

}
