package org.alfresco.mock.test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.tagging.TagScope;
import org.alfresco.service.cmr.tagging.TaggingService;
import org.alfresco.util.Pair;

/**
 * Mock implementation of the MockTaggingService class for testing purposes. This class provides a
 * mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockTaggingService implements TaggingService {

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param tag the tag
   * @return the result
   */
  @Override
  public boolean isTag(StoreRef storeRef, String tag) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public String getTagName(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @return the result
   */
  @Override
  public List<String> getTags(StoreRef storeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param pagingRequest the pagingRequest
   */
  @Override
  public PagingResults<Pair<NodeRef, String>> getTags(
      StoreRef storeRef, PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param filter the filter
   * @return the result
   */
  @Override
  public List<String> getTags(StoreRef storeRef, String filter) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param tag the tag
   * @return the result
   */
  @Override
  public NodeRef createTag(StoreRef storeRef, String tag) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param tag the tag
   */
  @Override
  public void deleteTag(StoreRef storeRef, String tag) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param existingTag the existingTag
   * @param newTag the newTag
   * @return the result
   */
  @Override
  public NodeRef changeTag(StoreRef storeRef, String existingTag, String newTag) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param tag the tag
   * @return the result
   */
  @Override
  public boolean hasTag(NodeRef nodeRef, String tag) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param tag the tag
   * @return the result
   */
  @Override
  public NodeRef addTag(NodeRef nodeRef, String tag) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param tag the tag
   * @return the result
   */
  @Override
  public NodeRef getTagNodeRef(StoreRef storeRef, String tag) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param tags the tags
   */
  @Override
  public List<Pair<String, NodeRef>> addTags(NodeRef nodeRef, List<String> tags) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param tag the tag
   */
  @Override
  public void removeTag(NodeRef nodeRef, String tag) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param tags the tags
   */
  @Override
  public void removeTags(NodeRef nodeRef, List<String> tags) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public List<String> getTags(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param pagingRequest the pagingRequest
   */
  @Override
  public PagingResults<Pair<NodeRef, String>> getTags(
      NodeRef nodeRef, PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param tags the tags
   */
  @Override
  public void setTags(NodeRef nodeRef, List<String> tags) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   */
  @Override
  public void clearTags(NodeRef nodeRef) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public boolean isTagScope(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   */
  @Override
  public void addTagScope(NodeRef nodeRef) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param async the async
   */
  @Override
  public void refreshTagScope(NodeRef nodeRef, boolean async) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   */
  @Override
  public void removeTagScope(NodeRef nodeRef) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public TagScope findTagScope(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public List<TagScope> findAllTagScopes(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param tag the tag
   * @return the result
   */
  @Override
  public List<NodeRef> findTaggedNodes(StoreRef storeRef, String tag) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param tag the tag
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public List<NodeRef> findTaggedNodes(StoreRef storeRef, String tag, NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param fromTag the fromTag
   * @param pageSize the pageSize
   */
  @Override
  public Pair<List<String>, Integer> getPagedTags(StoreRef storeRef, int fromTag, int pageSize) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param filter the filter
   * @param fromTag the fromTag
   * @param pageSize the pageSize
   */
  @Override
  public Pair<List<String>, Integer> getPagedTags(
      StoreRef storeRef, String filter, int fromTag, int pageSize) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   */
  @Override
  public List<Pair<String, Integer>> findTaggedNodesAndCountByTagName(StoreRef storeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public Map<NodeRef, Long> getTags(
      StoreRef storeRef,
      List<String> parameterIncludes,
      Pair<String, Boolean> sorting,
      Collection<String> exactNamesFilter,
      Collection<String> alikeNamesFilter) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @param name the name
   * @return the result
   */
  @Override
  public long findCountByTagName(StoreRef storeRef, String name) {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * {@inheritDoc}
   *
   * @param storeRef the storeRef
   * @return the result
   */
  @Override
  public Map<String, Long> calculateCount(StoreRef storeRef) {
    // TODO Auto-generated method stub
    return null;
  }
}
