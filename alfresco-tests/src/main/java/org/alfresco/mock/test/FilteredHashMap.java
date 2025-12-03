package org.alfresco.mock.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;

/**
 * A HashMap extension that filters out certain node references based on their file path.
 * Used to exclude root store paths from the node reference map.
 *
 * @author vige
 */
public class FilteredHashMap extends HashMap<NodeRef, File> {

	/**
	 * Constructs a new FilteredHashMap with initial capacity and load factor.
	 *
	 * @param initialCapacity the initial capacity
	 * @param loadFactor the load factor
	 */
	public FilteredHashMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	/**
	 * Constructs a new FilteredHashMap with initial capacity.
	 *
	 * @param initialCapacity the initial capacity
	 */
	public FilteredHashMap(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Constructs a new empty FilteredHashMap.
	 */
	public FilteredHashMap() {
		super();
	}

	/**
	 * Constructs a new FilteredHashMap from an existing map, filtering values.
	 *
	 * @param m the map to copy
	 */
	public FilteredHashMap(Map<NodeRef, File> m) {
		super(m);
		removeFilteredValues();
	}

	@Override
	/**
	 * Put.
	 *
	 * @param key the key
	 * @param value the value
	 * @return the result
	 */
	public File put(NodeRef key, File value) {
		if (haveToAdd(value.getAbsolutePath()))
			return super.put(key, value);
		else
			return null;
	}

	@Override
	/**
	 * Put all.
	 *
	 * @param m the m
	 */
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
