package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.service.cmr.model.FileExistsException;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.model.FileNotFoundException;
import org.alfresco.service.cmr.repository.CopyService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mock implementation of MockCopyService for testing purposes.
 *
 * @author vige
 */
public class MockCopyService implements CopyService, Serializable {

	/** The file folder service. */
	@Autowired
	private FileFolderService fileFolderService;

	/** The node service. */
	@Autowired
	private NodeService nodeService;

	/**
	 * Copy.
	 *
	 * @param sourceNodeRef the source node ref
	 * @param targetParentNodeRef the target parent node ref
	 * @param assocTypeQName the assoc type q name
	 * @param assocQName the assoc q name
	 * @param copyChildren the copy children
	 * @return the result
	 */
	@Override
	public NodeRef copy(NodeRef sourceNodeRef, NodeRef targetParentNodeRef, QName assocTypeQName, QName assocQName,
			boolean copyChildren) {
		String name = (String) nodeService.getProperty(sourceNodeRef, ContentModel.PROP_NAME);
		NodeRef result = null;
		try {
			FileInfo fileInfo = fileFolderService.copy(sourceNodeRef, targetParentNodeRef, name);
			result = fileInfo.getNodeRef();
			nodeService.setType(result, assocQName);
		} catch (FileExistsException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Copy and rename.
	 *
	 * @param sourceNodeRef the source node ref
	 * @param targetParentNodeRef the target parent node ref
	 * @param assocTypeQName the assoc type q name
	 * @param assocQName the assoc q name
	 * @param copyChildren the copy children
	 * @return the result
	 */
	@Override
	public NodeRef copyAndRename(NodeRef sourceNodeRef, NodeRef targetParentNodeRef, QName assocTypeQName,
			QName assocQName, boolean copyChildren) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Copy.
	 *
	 * @param sourceNodeRef the source node ref
	 * @param targetParentNodeRef the target parent node ref
	 * @param assocTypeQName the assoc type q name
	 * @param assocQName the assoc q name
	 * @return the result
	 */
	@Override
	public NodeRef copy(NodeRef sourceNodeRef, NodeRef targetParentNodeRef, QName assocTypeQName, QName assocQName) {
		return copy(sourceNodeRef, targetParentNodeRef, assocTypeQName, assocQName, false);
	}

	/**
	 * Copy.
	 *
	 * @param sourceNodeRef the source node ref
	 * @param destinationNodeRef the destination node ref
	 */
	@Override
	public void copy(NodeRef sourceNodeRef, NodeRef destinationNodeRef) {
		copy(sourceNodeRef, destinationNodeRef, ContentModel.ASSOC_CONTAINS, ContentModel.ASSOC_CHILDREN);
	}

	/**
	 * Get original.
	 *
	 * @param copiedNodeRef the copied node ref
	 * @return the result
	 */
	@Override
	public NodeRef getOriginal(NodeRef copiedNodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get copies.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	@Override
	public List<NodeRef> getCopies(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get copies.
	 *
	 * @param originalNodeRef the original node ref
	 * @param pagingRequest the paging request
	 * @return the result
	 */
	@Override
	public PagingResults<CopyInfo> getCopies(NodeRef originalNodeRef, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get copies.
	 *
	 * @param originalNodeRef the original node ref
	 * @param copyParentNodeRef the copy parent node ref
	 * @param pagingRequest the paging request
	 * @return the result
	 */
	@Override
	public PagingResults<CopyInfo> getCopies(NodeRef originalNodeRef, NodeRef copyParentNodeRef,
			PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get top level node new name.
	 *
	 * @param sourceNodeRef the source node ref
	 * @param targetParentRef the target parent ref
	 * @param assocTypeQName the assoc type q name
	 * @param assocQName the assoc q name
	 * @return the result
	 */
	@Override
	public String getTopLevelNodeNewName(NodeRef sourceNodeRef, NodeRef targetParentRef, QName assocTypeQName,
			QName assocQName) {
		// TODO Auto-generated method stub
		return null;
	}

}
