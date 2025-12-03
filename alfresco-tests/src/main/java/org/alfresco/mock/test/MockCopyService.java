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
 * Mock implementation of the Alfresco CopyService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockCopyService implements CopyService, Serializable {

	@Autowired
	private FileFolderService fileFolderService;

	@Autowired
	private NodeService nodeService;

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

	@Override
	public NodeRef copyAndRename(NodeRef sourceNodeRef, NodeRef targetParentNodeRef, QName assocTypeQName,
			QName assocQName, boolean copyChildren) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef copy(NodeRef sourceNodeRef, NodeRef targetParentNodeRef, QName assocTypeQName, QName assocQName) {
		return copy(sourceNodeRef, targetParentNodeRef, assocTypeQName, assocQName, false);
	}

	@Override
	public void copy(NodeRef sourceNodeRef, NodeRef destinationNodeRef) {
		copy(sourceNodeRef, destinationNodeRef, ContentModel.ASSOC_CONTAINS, ContentModel.ASSOC_CHILDREN);
	}

	@Override
	public NodeRef getOriginal(NodeRef copiedNodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NodeRef> getCopies(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<CopyInfo> getCopies(NodeRef originalNodeRef, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<CopyInfo> getCopies(NodeRef originalNodeRef, NodeRef copyParentNodeRef,
			PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTopLevelNodeNewName(NodeRef sourceNodeRef, NodeRef targetParentRef, QName assocTypeQName,
			QName assocQName) {
		// TODO Auto-generated method stub
		return null;
	}

}
