package org.alfresco.mock.test;

import java.util.Collection;
import java.util.List;

import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.CategoryService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.Pair;

/**
 * Mock implementation of MockCategoryService for testing purposes.
 *
 * @author vige
 */
public class MockCategoryService implements CategoryService {

	@Override
	public Collection<ChildAssociationRef> getChildren(NodeRef categoryRef, Mode mode, Depth depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ChildAssociationRef> getCategories(StoreRef storeRef, QName aspectQName, Depth depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ChildAssociationRef> getClassifications(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ChildAssociationRef> getRootCategories(StoreRef storeRef, QName aspectName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<ChildAssociationRef> getRootCategories(StoreRef storeRef, QName aspectName,
			PagingRequest pagingRequest, boolean sortByName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ChildAssociationRef> getRootCategories(StoreRef storeRef, QName aspectName, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildAssociationRef getCategory(NodeRef parent, QName aspectName, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ChildAssociationRef> getRootCategories(StoreRef storeRef, QName aspectName, String name,
			boolean create) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getClassificationAspects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef createClassification(StoreRef storeRef, QName aspectName, String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef createRootCategory(StoreRef storeRef, QName aspectName, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef createCategory(NodeRef parent, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteClassification(StoreRef storeRef, QName aspectName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCategory(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pair<NodeRef, Integer>> getTopCategories(StoreRef storeRef, QName aspectName, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<ChildAssociationRef> getRootCategories(StoreRef storeRef, QName aspectName,
			PagingRequest pagingRequest, boolean sortByName, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

}
