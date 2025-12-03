package org.alfresco.mock.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;

/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public class FilteredHashMap extends HashMap<NodeRef, File> {

	/**
	 * Constructs a new filtered hash map.
	 *
	 * @param initialCapacity the initial capacity
	 * @param loadFactor the load factor
	 * @return the result
	 */
	public FilteredHashMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	/**
	 * Constructs a new filtered hash map.
	 *
	 * @param initialCapacity the initial capacity
	 * @return the result
	 */
	public FilteredHashMap(int initialCapacity) {
		super(initialCapacity);
	}

	/**
	 * Constructs a new filtered hash map.
	 *
	 * @return the result
	 */
	public FilteredHashMap() {
		super();
	}

	/**
	 * Constructs a new filtered hash map.
	 *
	 * @param m the m
	 * @return the result
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
	 * @return the file
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

	/**
	 * Remove filtered values.
	 *
	 */
	private void removeFilteredValues() {
		for (NodeRef key : keySet()) {
			if (!haveToAdd(get(key).getAbsolutePath()))
				remove(key);
		}
	}

	/**
	 * Have to add.
	 *
	 * @param absolutePath the absolute path
	 * @return the boolean
	 */
	private boolean haveToAdd(String absolutePath) {
		return !absolutePath.endsWith(MockContentService.FOLDER_TEST.substring(0, 21))
				&& !absolutePath.endsWith(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_WORKSPACE)
				&& !absolutePath.endsWith(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_ARCHIVE);
	}
}
