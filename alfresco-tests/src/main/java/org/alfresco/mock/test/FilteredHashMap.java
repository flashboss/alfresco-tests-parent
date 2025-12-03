package org.alfresco.mock.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;

/**
 * A HashMap extension that filters out certain paths from being stored.
 * This class excludes entries for workspace and archive root paths
 * used by the mock content service.
 * 
 * @author vige
 */
public class FilteredHashMap extends HashMap<NodeRef, File> {

	/**
	 * Creates a FilteredHashMap with specified initial capacity and load factor.
	 * 
	 * @param initialCapacity the initial capacity
	 * @param loadFactor the load factor
	 */
	public FilteredHashMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	/**
	 * Creates a FilteredHashMap with specified initial capacity.
	 * 
	 * @param initialCapacity the initial capacity
	 */
	public FilteredHashMap(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Creates an empty FilteredHashMap with default initial capacity.
	 */
	public FilteredHashMap() {
		super();
	}

	/**
	 * Creates a FilteredHashMap with the same mappings as the specified Map.
	 * Filtered values are removed during construction.
	 * 
	 * @param m the map whose mappings are to be placed in this map
	 */
	public FilteredHashMap(Map<NodeRef, File> m) {
		super(m);
		removeFilteredValues();
	}

	/**
	 * {@inheritDoc}
	 * Only adds the entry if it passes the filter criteria.
	 */
	@Override
	public File put(NodeRef key, File value) {
		if (haveToAdd(value.getAbsolutePath()))
			return super.put(key, value);
		else
			return null;
	}

	/**
	 * {@inheritDoc}
	 * Only adds entries that pass the filter criteria.
	 */
	@Override
	public void putAll(Map<? extends NodeRef, ? extends File> m) {
		for (NodeRef key : m.keySet()) {
			if (haveToAdd(m.get(key).getAbsolutePath()))
				super.put(key, m.get(key));
		}
	}

	/**
	 * Removes all values that don't pass the filter criteria.
	 */
	private void removeFilteredValues() {
		for (NodeRef key : keySet()) {
			if (!haveToAdd(get(key).getAbsolutePath()))
				remove(key);
		}
	}

	/**
	 * Determines if a path should be added to the map.
	 * Excludes workspace and archive root paths.
	 * 
	 * @param absolutePath the absolute path to check
	 * @return true if the path should be added, false otherwise
	 */
	private boolean haveToAdd(String absolutePath) {
		return !absolutePath.endsWith(MockContentService.FOLDER_TEST.substring(0, 21))
				&& !absolutePath.endsWith(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_WORKSPACE)
				&& !absolutePath.endsWith(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_ARCHIVE);
	}
}
