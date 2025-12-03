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
 * Mock implementation of MockNamespaceService for testing purposes.
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
	 * @return the result
	 */
	public String getNamespaceURI(String prefix) throws NamespaceException {
		return prefix != null && prefix.equals(NamespaceService.CONTENT_MODEL_PREFIX)
				? NamespaceService.CONTENT_MODEL_1_0_URI
				: prefixes.get(prefix);
	}

	@Override
	/**
	 * Get prefixes.
	 *
	 * @param namespaceURI the namespace u r i
	 * @return the result
	 */
	public Collection<String> getPrefixes(String namespaceURI) throws NamespaceException {
		List<String> results = new ArrayList<String>();
		for (String prefix : prefixes.keySet())
			if (prefixes.get(prefix).equals(namespaceURI))
				results.add(prefix);
		return results;
	}

	@Override
	/**
	 * Get prefixes.
	 *
	 * @return the result
	 */
	public Collection<String> getPrefixes() {
		return prefixes.keySet();
	}

	@Override
	/**
	 * Get u r is.
	 *
	 * @return the result
	 */
	public Collection<String> getURIs() {
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
