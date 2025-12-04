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
	private static Map<String, String> prefixes = new HashMap<String, String>();	/**
	 * Get namespace u r i.
	 *
	 * @param prefix the prefix
	 * @return the string
	 */


	@Override
	public String getNamespaceURI(String prefix) throws NamespaceException {
		return prefix != null && prefix.equals(NamespaceService.CONTENT_MODEL_PREFIX)
				? NamespaceService.CONTENT_MODEL_1_0_URI
				: prefixes.get(prefix);
	}	/**
	 * Get prefixes.
	 *
	 * @param namespaceURI the namespace u r i
	 * @return the collection
	 */


	@Override
	public Collection<String> getPrefixes(String namespaceURI) throws NamespaceException {
		List<String> results = new ArrayList<String>();
		for (String prefix : prefixes.keySet())
			if (prefixes.get(prefix).equals(namespaceURI))
				results.add(prefix);
		return results;
	}	/**
	 * Get prefixes.
	 *
	 * @return the collection
	 */


	@Override
	public Collection<String> getPrefixes() {
		return prefixes.keySet();
	}	/**
	 * Get u r is.
	 *
	 * @return the collection
	 */


	@Override
	public Collection<String> getURIs() {
		return prefixes.values();
	}	/**
	 * Register namespace.
	 *
	 * @param prefix the prefix
	 * @param uri the uri
	 */


	@Override
	public void registerNamespace(String prefix, String uri) {
		prefixes.put(prefix, uri);
	}	/**
	 * Unregister namespace.
	 *
	 * @param prefix the prefix
	 */


	@Override
	public void unregisterNamespace(String prefix) {
		// TODO Auto-generated method stub

	}

}
