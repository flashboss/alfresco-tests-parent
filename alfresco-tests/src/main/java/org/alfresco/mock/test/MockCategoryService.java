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
 * Mock implementation of the Alfresco CategoryService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockCategoryService implements CategoryService {

	/**
	 * Get children.
	 *
	 * @param categoryRef the category ref
	 * @param mode the mode
	 * @param depth the depth
	 * @return the collection
	 */
@Override
	public Collection<ChildAssociationRef> getChildren(NodeRef categoryRef, Mode mode, Depth depth) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get categories.
	 *
	 * @param storeRef the store ref
	 * @param aspectQName the aspect q name
	 * @param depth the depth
	 * @return the collection
	 */
@Override
	public Collection<ChildAssociationRef> getCategories(StoreRef storeRef, QName aspectQName, Depth depth) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get classifications.
	 *
	 * @param storeRef the store ref
	 * @return the collection
	 */
@Override
	public Collection<ChildAssociationRef> getClassifications(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get root categories.
	 *
	 * @param storeRef the store ref
	 * @param aspectName the aspect name
	 * @return the collection
	 */
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
	public PagingResults<ChildAssociationRef> getRootCategories(StoreRef storeRef, QName aspectName,
			PagingRequest pagingRequest, boolean sortByName, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get root categories.
	 *
	 * @param storeRef the store ref
	 * @param aspectName the aspect name
	 * @param filter the filter
	 * @return the collection
	 */
@Override
	public Collection<ChildAssociationRef> getRootCategories(StoreRef storeRef, QName aspectName, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get category.
	 *
	 * @param parent the parent
	 * @param aspectName the aspect name
	 * @param name the name
	 * @return the child association ref
	 */
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

	/**
	 * Get classification aspects.
	 *
	 * @return the collection
	 */
@Override
	public Collection<QName> getClassificationAspects() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create classification.
	 *
	 * @param storeRef the store ref
	 * @param aspectName the aspect name
	 * @param attributeName the attribute name
	 * @return the node ref
	 */
@Override
	public NodeRef createClassification(StoreRef storeRef, QName aspectName, String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create root category.
	 *
	 * @param storeRef the store ref
	 * @param aspectName the aspect name
	 * @param name the name
	 * @return the node ref
	 */
@Override
	public NodeRef createRootCategory(StoreRef storeRef, QName aspectName, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create category.
	 *
	 * @param parent the parent
	 * @param name the name
	 * @return the node ref
	 */
@Override
	public NodeRef createCategory(NodeRef parent, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Delete classification.
	 *
	 * @param storeRef the store ref
	 * @param aspectName the aspect name
	 */
@Override
	public void deleteClassification(StoreRef storeRef, QName aspectName) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Delete category.
	 *
	 * @param nodeRef the node ref
	 */
@Override
	public void deleteCategory(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Get top categories.
	 *
	 * @param storeRef the store ref
	 * @param aspectName the aspect name
	 * @param count the count
	 */
@Override
	public List<Pair<NodeRef, Integer>> getTopCategories(StoreRef storeRef, QName aspectName, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
