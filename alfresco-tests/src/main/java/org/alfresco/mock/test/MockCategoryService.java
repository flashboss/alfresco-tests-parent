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
	/**
	 * Get children.
	 *
	 * @param categoryRef the category ref
	 * @param mode the mode
	 * @param depth the depth
	 * @return the result
	 */
	public Collection<ChildAssociationRef> getChildren(NodeRef categoryRef, Mode mode, Depth depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get categories.
	 *
	 * @param storeRef the store ref
	 * @param aspectQName the aspect q name
	 * @param depth the depth
	 * @return the result
	 */
	public Collection<ChildAssociationRef> getCategories(StoreRef storeRef, QName aspectQName, Depth depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get classifications.
	 *
	 * @param storeRef the store ref
	 * @return the result
	 */
	public Collection<ChildAssociationRef> getClassifications(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get root categories.
	 *
	 * @param storeRef the store ref
	 * @param aspectName the aspect name
	 * @return the result
	 */
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
	/**
	 * Get root categories.
	 *
	 * @param storeRef the store ref
	 * @param aspectName the aspect name
	 * @param filter the filter
	 * @return the result
	 */
	public Collection<ChildAssociationRef> getRootCategories(StoreRef storeRef, QName aspectName, String filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get category.
	 *
	 * @param parent the parent
	 * @param aspectName the aspect name
	 * @param name the name
	 * @return the result
	 */
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
	/**
	 * Get classification aspects.
	 *
	 * @return the result
	 */
	public Collection<QName> getClassificationAspects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Create classification.
	 *
	 * @param storeRef the store ref
	 * @param aspectName the aspect name
	 * @param attributeName the attribute name
	 * @return the result
	 */
	public NodeRef createClassification(StoreRef storeRef, QName aspectName, String attributeName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Create root category.
	 *
	 * @param storeRef the store ref
	 * @param aspectName the aspect name
	 * @param name the name
	 * @return the result
	 */
	public NodeRef createRootCategory(StoreRef storeRef, QName aspectName, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Create category.
	 *
	 * @param parent the parent
	 * @param name the name
	 * @return the result
	 */
	public NodeRef createCategory(NodeRef parent, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Delete classification.
	 *
	 * @param storeRef the store ref
	 * @param aspectName the aspect name
	 */
	public void deleteClassification(StoreRef storeRef, QName aspectName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Delete category.
	 *
	 * @param nodeRef the node ref
	 */
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
