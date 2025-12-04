package org.alfresco.mock.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
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

  /**
   * Put if absent.
   *
   * @param key the key
   * @param value the value
   * @return the file
   */
  @Override
  public File putIfAbsent(NodeRef key, File value) {
    if (haveToAdd(value.getAbsolutePath())) return super.putIfAbsent(key, value);
    else return null;
  }

  /**
   * Put.
   *
   * @param key the key
   * @param value the value
   * @return the file
   */
  @Override
  public File put(NodeRef key, File value) {
    if (haveToAdd(value.getAbsolutePath())) return super.put(key, value);
    else return null;
  }

  /**
   * Put all.
   *
   * @param m the m
   */
  @Override
  public void putAll(Map<? extends NodeRef, ? extends File> m) {
    super.putAll(filterMap(m));
  }

  @Override
  public File merge(
      NodeRef key,
      File value,
      BiFunction<? super File, ? super File, ? extends File> remappingFunction) {
    if (haveToAdd(value.getAbsolutePath())) return super.merge(key, value, remappingFunction);
    else return null;
  }

  /**
   * Replace all.
   *
   * @param function the function
   */
  @Override
  public void replaceAll(BiFunction<? super NodeRef, ? super File, ? extends File> function) {
    super.replaceAll(function);
    removeFilteredValues();
  }

  /**
   * Replace.
   *
   * @param key the key
   * @param oldValue the old value
   * @param newValue the new value
   * @return the boolean
   */
  @Override
  public boolean replace(NodeRef key, File oldValue, File newValue) {
    if (haveToAdd(newValue.getAbsolutePath())) return super.replace(key, oldValue, newValue);
    else return false;
  }

  /**
   * Replace.
   *
   * @param key the key
   * @param value the value
   * @return the file
   */
  @Override
  public File replace(NodeRef key, File value) {
    if (haveToAdd(value.getAbsolutePath())) return super.replace(key, value);
    else return null;
  }

  /** Remove filtered values. */
  private void removeFilteredValues() {
    this.entrySet()
        .forEach(
            x -> {
              if (!haveToAdd(x.getValue().getAbsolutePath())) remove(x.getKey());
            });
  }

  /**
   * Filter map.
   *
   * @param map the map
   */
  private Map<NodeRef, File> filterMap(Map<? extends NodeRef, ? extends File> map) {
    return map.entrySet().stream()
        .filter(x -> haveToAdd(x.getValue().getAbsolutePath()))
        .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
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
