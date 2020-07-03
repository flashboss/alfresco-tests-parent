package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;

import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.service.cmr.repository.CopyService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

public class MockCopyService implements CopyService, Serializable {

	@Override
	public NodeRef copy(NodeRef sourceNodeRef, NodeRef targetParentNodeRef, QName assocTypeQName, QName assocQName,
			boolean copyChildren) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef copyAndRename(NodeRef sourceNodeRef, NodeRef targetParentNodeRef, QName assocTypeQName,
			QName assocQName, boolean copyChildren) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef copy(NodeRef sourceNodeRef, NodeRef targetParentNodeRef, QName assocTypeQName, QName assocQName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void copy(NodeRef sourceNodeRef, NodeRef destinationNodeRef) {
		// TODO Auto-generated method stub
		
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
