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
 * Mock implementation of the MockNamespaceService class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockNamespaceService implements NamespaceService, Serializable {

	private static Map<String, String> prefixes = new HashMap<String, String>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNamespaceURI(String prefix) throws NamespaceException {
		return prefix != null && prefix.equals(NamespaceService.CONTENT_MODEL_PREFIX)
				? NamespaceService.CONTENT_MODEL_1_0_URI
				: prefixes.get(prefix);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<String> getPrefixes(String namespaceURI) throws NamespaceException {
		List<String> results = new ArrayList<String>();
		for (String prefix : prefixes.keySet())
			if (prefixes.get(prefix).equals(namespaceURI))
				results.add(prefix);
		return results;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<String> getPrefixes() {
		return prefixes.keySet();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<String> getURIs() {
		return prefixes.values();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerNamespace(String prefix, String uri) {
		prefixes.put(prefix, uri);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unregisterNamespace(String prefix) {
		// TODO Auto-generated method stub

	}

}
