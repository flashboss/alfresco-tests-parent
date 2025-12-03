package org.alfresco.mock.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;

/**
 * Mock implementation of the FilteredHashMap class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
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

	/**


	 * {@inheritDoc}


	 */


	@Override
	public File putIfAbsent(NodeRef key, File value) {
		if (haveToAdd(value.getAbsolutePath()))
			return super.putIfAbsent(key, value);
		else
			return null;
	}

	/**


	 * {@inheritDoc}


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


	 */


	@Override
	public void putAll(Map<? extends NodeRef, ? extends File> m) {
		super.putAll(filterMap(m));
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public File merge(NodeRef key, File value,
			BiFunction<? super File, ? super File, ? extends File> remappingFunction) {
		if (haveToAdd(value.getAbsolutePath()))
			return super.merge(key, value, remappingFunction);
		else
			return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void replaceAll(BiFunction<? super NodeRef, ? super File, ? extends File> function) {
		super.replaceAll(function);
		removeFilteredValues();
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public boolean replace(NodeRef key, File oldValue, File newValue) {
		if (haveToAdd(newValue.getAbsolutePath()))
			return super.replace(key, oldValue, newValue);
		else
			return false;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public File replace(NodeRef key, File value) {
		if (haveToAdd(value.getAbsolutePath()))
			return super.replace(key, value);
		else
			return null;
	}

	private void removeFilteredValues() {
		this.entrySet().forEach(x -> {
			if (!haveToAdd(x.getValue().getAbsolutePath()))
				remove(x.getKey());
		});
	}

	private Map<NodeRef, File> filterMap(Map<? extends NodeRef, ? extends File> map) {
		return map.entrySet().stream().filter(x -> haveToAdd(x.getValue().getAbsolutePath()))
				.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
	}

	private boolean haveToAdd(String absolutePath) {
		return !absolutePath.endsWith(MockContentService.FOLDER_TEST.substring(0, 21))
				&& !absolutePath.endsWith(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_WORKSPACE)
				&& !absolutePath.endsWith(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_ARCHIVE);
	}
}
