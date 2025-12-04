package org.alfresco.mock.test;

import java.util.List;
import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.tagging.TagScope;
import org.alfresco.service.cmr.tagging.TaggingService;
import org.alfresco.util.Pair;

/**
 * Mock implementation of the Alfresco TaggingService for testing purposes. Provides stub
 * implementations for testing without a running Alfresco server.
 *
 * @author vige
 */
public class MockTaggingService implements TaggingService {
  /**
   * Is tag.
   *
   * @param storeRef the store ref
   * @param tag the tag
   * @return the boolean
   */
  @Override
  public boolean isTag(StoreRef storeRef, String tag) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Get tag name.
   *
   * @param nodeRef the node ref
   * @return the string
   */
  @Override
  public String getTagName(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get tags.
   *
   * @param storeRef the store ref
   * @return the list
   */
  @Override
  public List<String> getTags(StoreRef storeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get tags.
   *
   * @param storeRef the store ref
   * @param pagingRequest the paging request
   */
  @Override
  public PagingResults<Pair<NodeRef, String>> getTags(
      StoreRef storeRef, PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get tags.
   *
   * @param storeRef the store ref
   * @param filter the filter
   * @return the list
   */
  @Override
  public List<String> getTags(StoreRef storeRef, String filter) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Create tag.
   *
   * @param storeRef the store ref
   * @param tag the tag
   * @return the node ref
   */
  @Override
  public NodeRef createTag(StoreRef storeRef, String tag) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Delete tag.
   *
   * @param storeRef the store ref
   * @param tag the tag
   */
  @Override
  public void deleteTag(StoreRef storeRef, String tag) {
    // TODO Auto-generated method stub

  }

  /**
   * Change tag.
   *
   * @param storeRef the store ref
   * @param existingTag the existing tag
   * @param newTag the new tag
   * @return the node ref
   */
  @Override
  public NodeRef changeTag(StoreRef storeRef, String existingTag, String newTag) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Has tag.
   *
   * @param nodeRef the node ref
   * @param tag the tag
   * @return the boolean
   */
  @Override
  public boolean hasTag(NodeRef nodeRef, String tag) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Add tag.
   *
   * @param nodeRef the node ref
   * @param tag the tag
   * @return the node ref
   */
  @Override
  public NodeRef addTag(NodeRef nodeRef, String tag) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get tag node ref.
   *
   * @param storeRef the store ref
   * @param tag the tag
   * @return the node ref
   */
  @Override
  public NodeRef getTagNodeRef(StoreRef storeRef, String tag) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Add tags.
   *
   * @param nodeRef the node ref
   * @param tags the tags
   */
  @Override
  public List<Pair<String, NodeRef>> addTags(NodeRef nodeRef, List<String> tags) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Remove tag.
   *
   * @param nodeRef the node ref
   * @param tag the tag
   */
  @Override
  public void removeTag(NodeRef nodeRef, String tag) {
    // TODO Auto-generated method stub

  }

  /**
   * Remove tags.
   *
   * @param nodeRef the node ref
   * @param tags the tags
   */
  @Override
  public void removeTags(NodeRef nodeRef, List<String> tags) {
    // TODO Auto-generated method stub

  }

  /**
   * Get tags.
   *
   * @param nodeRef the node ref
   * @return the list
   */
  @Override
  public List<String> getTags(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get tags.
   *
   * @param nodeRef the node ref
   * @param pagingRequest the paging request
   */
  @Override
  public PagingResults<Pair<NodeRef, String>> getTags(
      NodeRef nodeRef, PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Set tags.
   *
   * @param nodeRef the node ref
   * @param tags the tags
   */
  @Override
  public void setTags(NodeRef nodeRef, List<String> tags) {
    // TODO Auto-generated method stub

  }

  /**
   * Clear tags.
   *
   * @param nodeRef the node ref
   */
  @Override
  public void clearTags(NodeRef nodeRef) {
    // TODO Auto-generated method stub

  }

  /**
   * Is tag scope.
   *
   * @param nodeRef the node ref
   * @return the boolean
   */
  @Override
  public boolean isTagScope(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Add tag scope.
   *
   * @param nodeRef the node ref
   */
  @Override
  public void addTagScope(NodeRef nodeRef) {
    // TODO Auto-generated method stub

  }

  /**
   * Refresh tag scope.
   *
   * @param nodeRef the node ref
   * @param async the async
   */
  @Override
  public void refreshTagScope(NodeRef nodeRef, boolean async) {
    // TODO Auto-generated method stub

  }

  /**
   * Remove tag scope.
   *
   * @param nodeRef the node ref
   */
  @Override
  public void removeTagScope(NodeRef nodeRef) {
    // TODO Auto-generated method stub

  }

  /**
   * Find tag scope.
   *
   * @param nodeRef the node ref
   * @return the tag scope
   */
  @Override
  public TagScope findTagScope(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Find all tag scopes.
   *
   * @param nodeRef the node ref
   * @return the list
   */
  @Override
  public List<TagScope> findAllTagScopes(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Find tagged nodes.
   *
   * @param storeRef the store ref
   * @param tag the tag
   * @return the list
   */
  @Override
  public List<NodeRef> findTaggedNodes(StoreRef storeRef, String tag) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Find tagged nodes.
   *
   * @param storeRef the store ref
   * @param tag the tag
   * @param nodeRef the node ref
   * @return the list
   */
  @Override
  public List<NodeRef> findTaggedNodes(StoreRef storeRef, String tag, NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get paged tags.
   *
   * @param storeRef the store ref
   * @param fromTag the from tag
   * @param pageSize the page size
   */
  @Override
  public Pair<List<String>, Integer> getPagedTags(StoreRef storeRef, int fromTag, int pageSize) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get paged tags.
   *
   * @param storeRef the store ref
   * @param filter the filter
   * @param fromTag the from tag
   * @param pageSize the page size
   */
  @Override
  public Pair<List<String>, Integer> getPagedTags(
      StoreRef storeRef, String filter, int fromTag, int pageSize) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Find tagged nodes and count by tag name.
   *
   * @param storeRef the store ref
   * @return the list
   */
  @Override
  public List<Pair<String, Integer>> findTaggedNodesAndCountByTagName(StoreRef storeRef) {
    // TODO Auto-generated method stub
    return null;
  }
}
