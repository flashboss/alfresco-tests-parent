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
* Mock implementation of the MockCategoryService class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockCategoryService implements CategoryService {

/**
* {@inheritDoc}
* @param categoryRef the categoryRef
* @param mode the mode
* @param depth the depth
* @return the result
 */
	@Override
	public Collection<ChildAssociationRef> getChildren(NodeRef categoryRef, Mode mode, Depth depth) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param storeRef the storeRef
* @param aspectQName the aspectQName
* @param depth the depth
* @return the result
 */
	@Override
	public Collection<ChildAssociationRef> getCategories(StoreRef storeRef, QName aspectQName, Depth depth) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param storeRef the storeRef
* @return the result
 */
	@Override
	public Collection<ChildAssociationRef> getClassifications(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param storeRef the storeRef
* @param aspectName the aspectName
* @return the result
 */
	@Override
	public Collection<ChildAssociationRef> getRootCategories(StoreRef storeRef, QName aspectName) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public PagingResults<ChildAssociationRef> getRootCategories(StoreRef storeRef, QName aspectName,
			PagingRequest pagingRequest, boolean sortByName) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public PagingResults<ChildAssociationRef> getRootCategories(StoreRef storeRef, QName aspectName,
			PagingRequest pagingRequest, boolean sortByName, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param storeRef the storeRef
* @param aspectName the aspectName
* @param filter the filter
* @return the result
 */
	@Override
	public Collection<ChildAssociationRef> getRootCategories(StoreRef storeRef, QName aspectName, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param parent the parent
* @param aspectName the aspectName
* @param name the name
* @return the result
 */
	@Override
	public ChildAssociationRef getCategory(NodeRef parent, QName aspectName, String name) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public Collection<ChildAssociationRef> getRootCategories(StoreRef storeRef, QName aspectName, String name,
			boolean create) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Collection<QName> getClassificationAspects() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param storeRef the storeRef
* @param aspectName the aspectName
* @param attributeName the attributeName
* @return the result
 */
	@Override
	public NodeRef createClassification(StoreRef storeRef, QName aspectName, String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param storeRef the storeRef
* @param aspectName the aspectName
* @param name the name
* @return the result
 */
	@Override
	public NodeRef createRootCategory(StoreRef storeRef, QName aspectName, String name) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param parent the parent
* @param name the name
* @return the result
 */
	@Override
	public NodeRef createCategory(NodeRef parent, String name) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param storeRef the storeRef
* @param aspectName the aspectName
 */
	@Override
	public void deleteClassification(StoreRef storeRef, QName aspectName) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
 */
	@Override
	public void deleteCategory(NodeRef nodeRef) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param storeRef the storeRef
* @param aspectName the aspectName
* @param count the count
 */
	@Override
	public List<Pair<NodeRef, Integer>> getTopCategories(StoreRef storeRef, QName aspectName, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
