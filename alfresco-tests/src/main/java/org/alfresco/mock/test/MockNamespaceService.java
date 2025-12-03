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
 * Mock implementation of the {@link NamespaceService} interface for testing purposes.
 * Provides namespace prefix and URI resolution functionality with support for
 * registering custom namespaces.
 *
 * @author lucastancapiano
 */
public class MockNamespaceService implements NamespaceService, Serializable {

	/**
	 * Map storing namespace prefixes to URIs.
	 */
	private static Map<String, String> prefixes = new HashMap<String, String>();

	/**
	 * {@inheritDoc}
	 * Gets the namespace URI for a given prefix.
	 *
	 * @param prefix The namespace prefix.
	 * @return The namespace URI, or the content model URI if the prefix is "cm".
	 * @throws NamespaceException If the namespace is not found.
	 */
	@Override
	public String getNamespaceURI(String prefix) throws NamespaceException {
		return prefix != null && prefix.equals(NamespaceService.CONTENT_MODEL_PREFIX)
				? NamespaceService.CONTENT_MODEL_1_0_URI
				: prefixes.get(prefix);
	}

	/**
	 * {@inheritDoc}
	 * Gets all prefixes registered for a given namespace URI.
	 *
	 * @param namespaceURI The namespace URI.
	 * @return A collection of prefixes for the namespace.
	 * @throws NamespaceException If the namespace is not found.
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
	 * Gets all registered prefixes.
	 *
	 * @return A collection of all namespace prefixes.
	 */
	@Override
	public Collection<String> getPrefixes() {
		return prefixes.keySet();
	}

	/**
	 * {@inheritDoc}
	 * Gets all registered namespace URIs.
	 *
	 * @return A collection of all namespace URIs.
	 */
	@Override
	public Collection<String> getURIs() {
		return prefixes.values();
	}

	/**
	 * {@inheritDoc}
	 * Registers a new namespace prefix to URI mapping.
	 *
	 * @param prefix The namespace prefix.
	 * @param uri The namespace URI.
	 */
	@Override
	public void registerNamespace(String prefix, String uri) {
		prefixes.put(prefix, uri);
	}

	/**
	 * {@inheritDoc}
	 * Unregisters a namespace prefix. This is a stub implementation.
	 *
	 * @param prefix The namespace prefix to unregister.
	 */
	@Override
	public void unregisterNamespace(String prefix) {
		// TODO Auto-generated method stub

	}

}
