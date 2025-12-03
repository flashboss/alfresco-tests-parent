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
 * @author vige
 */
public class MockNamespaceService implements NamespaceService, Serializable {

	private static Map<String, String> prefixes = new HashMap<String, String>();

	@Override
	public String getNamespaceURI(String prefix) throws NamespaceException {
		return prefix != null && prefix.equals(NamespaceService.CONTENT_MODEL_PREFIX)
				? NamespaceService.CONTENT_MODEL_1_0_URI
				: prefixes.get(prefix);
	}

	@Override
	public Collection<String> getPrefixes(String namespaceURI) throws NamespaceException {
		List<String> results = new ArrayList<String>();
		for (String prefix : prefixes.keySet())
			if (prefixes.get(prefix).equals(namespaceURI))
				results.add(prefix);
		return results;
	}

	@Override
	public Collection<String> getPrefixes() {
		return prefixes.keySet();
	}

	@Override
	public Collection<String> getURIs() {
		return prefixes.values();
	}

	@Override
	public void registerNamespace(String prefix, String uri) {
		prefixes.put(prefix, uri);
	}

	@Override
	public void unregisterNamespace(String prefix) {
		// TODO Auto-generated method stub

	}

}
