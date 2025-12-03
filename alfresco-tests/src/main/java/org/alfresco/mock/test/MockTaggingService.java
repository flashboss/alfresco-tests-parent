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
 * Mock implementation of the Alfresco TaggingService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockTaggingService implements TaggingService{

	@Override
	/**
	 * Is tag.
	 *
	 * @param storeRef the store ref
	 * @param tag the tag
	 * @return the boolean
	 */
	public boolean isTag(StoreRef storeRef, String tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get tag name.
	 *
	 * @param nodeRef the node ref
	 * @return the string
	 */
	public String getTagName(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get tags.
	 *
	 * @param storeRef the store ref
	 * @return the list
	 */
	public List<String> getTags(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get tags.
	 *
	 * @param storeRef the store ref
	 * @param pagingRequest the paging request
	 */
	public PagingResults<Pair<NodeRef, String>> getTags(StoreRef storeRef, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get tags.
	 *
	 * @param storeRef the store ref
	 * @param filter the filter
	 * @return the list
	 */
	public List<String> getTags(StoreRef storeRef, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Create tag.
	 *
	 * @param storeRef the store ref
	 * @param tag the tag
	 * @return the node ref
	 */
	public NodeRef createTag(StoreRef storeRef, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Delete tag.
	 *
	 * @param storeRef the store ref
	 * @param tag the tag
	 */
	public void deleteTag(StoreRef storeRef, String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Change tag.
	 *
	 * @param storeRef the store ref
	 * @param existingTag the existing tag
	 * @param newTag the new tag
	 * @return the node ref
	 */
	public NodeRef changeTag(StoreRef storeRef, String existingTag, String newTag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Has tag.
	 *
	 * @param nodeRef the node ref
	 * @param tag the tag
	 * @return the boolean
	 */
	public boolean hasTag(NodeRef nodeRef, String tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Add tag.
	 *
	 * @param nodeRef the node ref
	 * @param tag the tag
	 * @return the node ref
	 */
	public NodeRef addTag(NodeRef nodeRef, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get tag node ref.
	 *
	 * @param storeRef the store ref
	 * @param tag the tag
	 * @return the node ref
	 */
	public NodeRef getTagNodeRef(StoreRef storeRef, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Add tags.
	 *
	 * @param nodeRef the node ref
	 * @param tags the tags
	 */
	public List<Pair<String, NodeRef>> addTags(NodeRef nodeRef, List<String> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Remove tag.
	 *
	 * @param nodeRef the node ref
	 * @param tag the tag
	 */
	public void removeTag(NodeRef nodeRef, String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Remove tags.
	 *
	 * @param nodeRef the node ref
	 * @param tags the tags
	 */
	public void removeTags(NodeRef nodeRef, List<String> tags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Get tags.
	 *
	 * @param nodeRef the node ref
	 * @return the list
	 */
	public List<String> getTags(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get tags.
	 *
	 * @param nodeRef the node ref
	 * @param pagingRequest the paging request
	 */
	public PagingResults<Pair<NodeRef, String>> getTags(NodeRef nodeRef, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Set tags.
	 *
	 * @param nodeRef the node ref
	 * @param tags the tags
	 */
	public void setTags(NodeRef nodeRef, List<String> tags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Clear tags.
	 *
	 * @param nodeRef the node ref
	 */
	public void clearTags(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Is tag scope.
	 *
	 * @param nodeRef the node ref
	 * @return the boolean
	 */
	public boolean isTagScope(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Add tag scope.
	 *
	 * @param nodeRef the node ref
	 */
	public void addTagScope(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Refresh tag scope.
	 *
	 * @param nodeRef the node ref
	 * @param async the async
	 */
	public void refreshTagScope(NodeRef nodeRef, boolean async) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Remove tag scope.
	 *
	 * @param nodeRef the node ref
	 */
	public void removeTagScope(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Find tag scope.
	 *
	 * @param nodeRef the node ref
	 * @return the tag scope
	 */
	public TagScope findTagScope(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Find all tag scopes.
	 *
	 * @param nodeRef the node ref
	 * @return the list
	 */
	public List<TagScope> findAllTagScopes(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Find tagged nodes.
	 *
	 * @param storeRef the store ref
	 * @param tag the tag
	 * @return the list
	 */
	public List<NodeRef> findTaggedNodes(StoreRef storeRef, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Find tagged nodes.
	 *
	 * @param storeRef the store ref
	 * @param tag the tag
	 * @param nodeRef the node ref
	 * @return the list
	 */
	public List<NodeRef> findTaggedNodes(StoreRef storeRef, String tag, NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get paged tags.
	 *
	 * @param storeRef the store ref
	 * @param fromTag the from tag
	 * @param pageSize the page size
	 */
	public Pair<List<String>, Integer> getPagedTags(StoreRef storeRef, int fromTag, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get paged tags.
	 *
	 * @param storeRef the store ref
	 * @param filter the filter
	 * @param fromTag the from tag
	 * @param pageSize the page size
	 */
	public Pair<List<String>, Integer> getPagedTags(StoreRef storeRef, String filter, int fromTag, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
