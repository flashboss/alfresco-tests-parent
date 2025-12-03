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
	public boolean isTag(StoreRef storeRef, String tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTagName(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getTags(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<Pair<NodeRef, String>> getTags(StoreRef storeRef, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getTags(StoreRef storeRef, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef createTag(StoreRef storeRef, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTag(StoreRef storeRef, String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NodeRef changeTag(StoreRef storeRef, String existingTag, String newTag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasTag(NodeRef nodeRef, String tag) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public NodeRef addTag(NodeRef nodeRef, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getTagNodeRef(StoreRef storeRef, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pair<String, NodeRef>> addTags(NodeRef nodeRef, List<String> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeTag(NodeRef nodeRef, String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTags(NodeRef nodeRef, List<String> tags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getTags(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<Pair<NodeRef, String>> getTags(NodeRef nodeRef, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTags(NodeRef nodeRef, List<String> tags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearTags(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isTagScope(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addTagScope(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshTagScope(NodeRef nodeRef, boolean async) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTagScope(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TagScope findTagScope(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TagScope> findAllTagScopes(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NodeRef> findTaggedNodes(StoreRef storeRef, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NodeRef> findTaggedNodes(StoreRef storeRef, String tag, NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pair<List<String>, Integer> getPagedTags(StoreRef storeRef, int fromTag, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pair<List<String>, Integer> getPagedTags(StoreRef storeRef, String filter, int fromTag, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pair<String, Integer>> findTaggedNodesAndCountByTagName(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

}
