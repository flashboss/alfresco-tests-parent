package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import org.alfresco.service.namespace.NamespaceException;
import org.alfresco.service.namespace.NamespaceService;

public class MockNamespaceService implements NamespaceService, Serializable {

	@Override
	public String getNamespaceURI(String prefix) throws NamespaceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getPrefixes(String namespaceURI) throws NamespaceException {
		// TODO Auto-generated method stub
		return Arrays.asList(NamespaceService.DEFAULT_PREFIX);
	}

	@Override
	public Collection<String> getPrefixes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getURIs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerNamespace(String prefix, String uri) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregisterNamespace(String prefix) {
		// TODO Auto-generated method stub

	}

}
