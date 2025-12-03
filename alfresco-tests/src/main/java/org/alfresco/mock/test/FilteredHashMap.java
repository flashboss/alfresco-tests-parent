package org.alfresco.mock.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;

/**
 * Mock implementation of FilteredHashMap for testing purposes.
 *
 * @author lucastancapiano
 */
public class FilteredHashMap extends HashMap<NodeRef, File> {

	public FilteredHashMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public FilteredHashMap(int initialCapacity) {
		super(initialCapacity);
	}

	public FilteredHashMap() {
		super();
	}

	public FilteredHashMap(Map<NodeRef, File> m) {
		super(m);
		removeFilteredValues();
	}

	@Override
	public File put(NodeRef key, File value) {
		if (haveToAdd(value.getAbsolutePath()))
			return super.put(key, value);
		else
			return null;
	}

	@Override
	public void putAll(Map<? extends NodeRef, ? extends File> m) {
		for (NodeRef key : m.keySet()) {
			if (haveToAdd(m.get(key).getAbsolutePath()))
				super.put(key, m.get(key));
		}
	}

	private void removeFilteredValues() {
		for (NodeRef key : keySet()) {
			if (!haveToAdd(get(key).getAbsolutePath()))
				remove(key);
		}
	}

	private boolean haveToAdd(String absolutePath) {
		return !absolutePath.endsWith(MockContentService.FOLDER_TEST.substring(0, 21))
				&& !absolutePath.endsWith(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_WORKSPACE)
				&& !absolutePath.endsWith(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_ARCHIVE);
	}
}
