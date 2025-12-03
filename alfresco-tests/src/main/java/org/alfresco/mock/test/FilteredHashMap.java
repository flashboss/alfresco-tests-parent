package org.alfresco.mock.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;

/**
 * A filtered HashMap implementation that filters out specific paths.
 * Used to exclude test folder paths and store reference paths from the map.
 *
 * @author lucastancapiano
 */
public class FilteredHashMap extends HashMap<NodeRef, File> {

	/**
	 * Constructs an empty FilteredHashMap with the specified initial capacity and load factor.
	 *
	 * @param initialCapacity the initial capacity
	 * @param loadFactor the load factor
	 */
	public FilteredHashMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	/**
	 * Constructs an empty FilteredHashMap with the specified initial capacity.
	 *
	 * @param initialCapacity the initial capacity
	 */
	public FilteredHashMap(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Constructs an empty FilteredHashMap with default initial capacity and load factor.
	 */
	public FilteredHashMap() {
		super();
	}

	/**
	 * Constructs a FilteredHashMap with the same mappings as the specified map,
	 * excluding filtered values.
	 *
	 * @param m the map whose mappings are to be placed in this map
	 */
	public FilteredHashMap(Map<NodeRef, File> m) {
		super(m);
		removeFilteredValues();
	}

	/**
	 * Associates the specified file with the specified node reference,
	 * only if the file path passes the filter.
	 *
	 * @param key the node reference key
	 * @param value the file value
	 * @return the previous value associated with key, or null if filtered out
	 */
	@Override
	public File put(NodeRef key, File value) {
		if (haveToAdd(value.getAbsolutePath()))
			return super.put(key, value);
		else
			return null;
	}

	/**
	 * Copies all mappings from the specified map to this map,
	 * filtering out entries that don't pass the filter.
	 *
	 * @param m the map to copy from
	 */
	@Override
	public void putAll(Map<? extends NodeRef, ? extends File> m) {
		for (NodeRef key : m.keySet()) {
			if (haveToAdd(m.get(key).getAbsolutePath()))
				super.put(key, m.get(key));
		}
	}

	/**
	 * Removes all entries that don't pass the filter.
	 */
	private void removeFilteredValues() {
		for (NodeRef key : keySet()) {
			if (!haveToAdd(get(key).getAbsolutePath()))
				remove(key);
		}
	}

	/**
	 * Checks if a path should be added to the map.
	 *
	 * @param absolutePath the absolute path to check
	 * @return true if the path should be added, false if it should be filtered out
	 */
	private boolean haveToAdd(String absolutePath) {
		return !absolutePath.endsWith(MockContentService.FOLDER_TEST.substring(0, 21))
				&& !absolutePath.endsWith(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_WORKSPACE)
				&& !absolutePath.endsWith(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_ARCHIVE);
	}
}
