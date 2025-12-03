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
 * Mock implementation of the {@link CopyService} interface for testing purposes.
 * This class provides node copy functionality in a mock Alfresco environment,
 * delegating to the file folder service for actual copy operations.
 *
 * @author lucastancapiano
 */
public class MockCopyService implements CopyService, Serializable {

	/**
	 * The file folder service for copy operations.
	 */
	@Autowired
	private FileFolderService fileFolderService;

	/**
	 * The node service for property access.
	 */
	@Autowired
	private NodeService nodeService;

	/**
	 * {@inheritDoc}
	 * Copies a node to a new parent with optional child copying.
	 *
	 * @param sourceNodeRef The source node reference.
	 * @param targetParentNodeRef The target parent node reference.
	 * @param assocTypeQName The association type QName.
	 * @param assocQName The association QName.
	 * @param copyChildren Whether to copy children.
	 * @return The node reference of the copied node.
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
	 * {@inheritDoc}
	 * Copies and renames a node.
	 *
	 * @param sourceNodeRef The source node reference.
	 * @param targetParentNodeRef The target parent node reference.
	 * @param assocTypeQName The association type QName.
	 * @param assocQName The association QName.
	 * @param copyChildren Whether to copy children.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public NodeRef copyAndRename(NodeRef sourceNodeRef, NodeRef targetParentNodeRef, QName assocTypeQName,
			QName assocQName, boolean copyChildren) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Copies a node without children.
	 *
	 * @param sourceNodeRef The source node reference.
	 * @param targetParentNodeRef The target parent node reference.
	 * @param assocTypeQName The association type QName.
	 * @param assocQName The association QName.
	 * @return The node reference of the copied node.
	 */
	@Override
	public NodeRef copy(NodeRef sourceNodeRef, NodeRef targetParentNodeRef, QName assocTypeQName, QName assocQName) {
		return copy(sourceNodeRef, targetParentNodeRef, assocTypeQName, assocQName, false);
	}

	/**
	 * {@inheritDoc}
	 * Copies content from source to destination node.
	 *
	 * @param sourceNodeRef The source node reference.
	 * @param destinationNodeRef The destination node reference.
	 */
	@Override
	public void copy(NodeRef sourceNodeRef, NodeRef destinationNodeRef) {
		copy(sourceNodeRef, destinationNodeRef, ContentModel.ASSOC_CONTAINS, ContentModel.ASSOC_CHILDREN);
	}

	/**
	 * {@inheritDoc}
	 * Gets the original node reference for a copy.
	 *
	 * @param copiedNodeRef The copied node reference.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public NodeRef getOriginal(NodeRef copiedNodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Gets all copies of a node.
	 *
	 * @param nodeRef The original node reference.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public List<NodeRef> getCopies(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Gets copies of a node with paging.
	 *
	 * @param originalNodeRef The original node reference.
	 * @param pagingRequest The paging request.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public PagingResults<CopyInfo> getCopies(NodeRef originalNodeRef, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Gets copies of a node within a parent with paging.
	 *
	 * @param originalNodeRef The original node reference.
	 * @param copyParentNodeRef The parent node to search within.
	 * @param pagingRequest The paging request.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public PagingResults<CopyInfo> getCopies(NodeRef originalNodeRef, NodeRef copyParentNodeRef,
			PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
