package org.alfresco.mock.test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.service.cmr.model.FileExistsException;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.model.FileFolderServiceType;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.model.FileNotFoundException;
import org.alfresco.service.cmr.model.SubFolderFilter;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.ContentData;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.Pair;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class MockFileFolderService implements FileFolderService, Serializable {

	@Autowired
	private NodeService nodeService;

	@Autowired
	private NamespaceService namespaceService;

	@Override
	public List<FileInfo> list(NodeRef contextNodeRef) {
		List<FileInfo> result = new ArrayList<FileInfo>();
		List<ChildAssociationRef> associationRefs = nodeService.getChildAssocs(contextNodeRef);
		for (ChildAssociationRef associationRef : associationRefs) {
			FileInfo fileInfo = new MockFileInfo(associationRef.getChildRef(), associationRef.getQName().getLocalName(),
					associationRef.getTypeQName());
			result.add(fileInfo);
		}
		return result;
	}

	@Override
	public PagingResults<FileInfo> list(NodeRef contextNodeRef, boolean files, boolean folders,
			Set<QName> ignoreTypeQNames, List<Pair<QName, Boolean>> sortProps, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<FileInfo> list(NodeRef contextNodeRef, boolean files, boolean folders, String pattern,
			Set<QName> ignoreTypeQNames, List<Pair<QName, Boolean>> sortProps, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileInfo> listFiles(NodeRef contextNodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileInfo> listFolders(NodeRef contextNodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileInfo> listDeepFolders(NodeRef contextNodeRef, SubFolderFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getLocalizedSibling(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef searchSimple(NodeRef contextNodeRef, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileInfo> search(NodeRef contextNodeRef, String namePattern, boolean includeSubFolders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileInfo> search(NodeRef contextNodeRef, String namePattern, boolean fileSearch, boolean folderSearch,
			boolean includeSubFolders) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileInfo rename(NodeRef fileFolderRef, String newName) throws FileExistsException, FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileInfo move(NodeRef sourceNodeRef, NodeRef targetParentRef, String newName)
			throws FileExistsException, FileNotFoundException {
		FileInfo fileInfo = copy(sourceNodeRef, targetParentRef, newName);
		nodeService.setProperty(fileInfo.getNodeRef(), MockNodeService.PRIMARY_PARENT, targetParentRef);
		delete(sourceNodeRef);
		return fileInfo;
	}

	@Override
	public FileInfo moveFrom(NodeRef sourceNodeRef, NodeRef sourceParentRef, NodeRef targetParentRef, String newName)
			throws FileExistsException, FileNotFoundException {
		File file = getNodeService().getNodeRefs().get(targetParentRef);
		if (file.isFile()) {
			file.delete();
			file.mkdir();
		}
		String name = getNodeService().getNodeRefs().get(sourceNodeRef).getName();
		NodeRef originalNode = nodeService.getChildByName(sourceParentRef, ContentModel.ASSOC_CONTAINS, name);
		return move(originalNode, targetParentRef, newName);
	}

	@Override
	public FileInfo move(NodeRef sourceNodeRef, NodeRef sourceParentRef, NodeRef targetParentRef, String newName)
			throws FileExistsException, FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileInfo copy(NodeRef sourceNodeRef, NodeRef targetParentRef, String newName)
			throws FileExistsException, FileNotFoundException {
		if (newName == null)
			newName = nodeService.getPath(sourceNodeRef).last().getElementString();
		QName assocQName = QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, newName);
		ChildAssociationRef association = nodeService.createNode(targetParentRef, ContentModel.ASSOC_CONTAINS,
				assocQName, ContentModel.TYPE_CONTENT, nodeService.getProperties(sourceNodeRef));
		File source = getNodeService().getNodeRefs().get(sourceNodeRef);
		File target = getNodeService().getNodeRefs().get(targetParentRef);
		try {
			FileUtils.copyFile(source, new File(target + File.separator + newName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new MockFileInfo(association.getChildRef(), newName, ContentModel.TYPE_CONTENT);
	}

	@Override
	public FileInfo create(NodeRef parentNodeRef, String name, QName typeQName) throws FileExistsException {
		if (!parentNodeRef.getId().isEmpty() && nodeService.getPrimaryParent(parentNodeRef) != null
				&& !name.contains(":")) {
			String prefix = NamespaceService.CONTENT_MODEL_PREFIX;
			name = prefix + ":" + name;
		}
		QName assocQName = QName.createQName(name);
		if (name.contains(":"))
			assocQName = QName.createQName(name.split(":")[0], name.split(":")[1], namespaceService);
		ChildAssociationRef association = nodeService.createNode(parentNodeRef, ContentModel.ASSOC_CONTAINS, assocQName,
				typeQName);
		return new MockFileInfo(association.getChildRef(), name, typeQName);
	}

	@Override
	public FileInfo create(NodeRef parentNodeRef, String name, QName typeQName, QName assocQName)
			throws FileExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(NodeRef nodeRef) {
		List<NodeRef> toRemove = new ArrayList<NodeRef>();
		Map<NodeRef, File> nodeRefs = getNodeService().getNodeRefs();
		String pathParent = nodeService.getPath(nodeRef).toString() + File.separator;
		for (NodeRef node : nodeRefs.keySet()) {
			String pathChild = nodeService.getPath(node).toString();
			if (pathChild.contains(pathParent))
				toRemove.add(node);
		}
		nodeRefs.keySet().removeAll(toRemove);
		try {
			File file = nodeRefs.get(nodeRef);
			if (file != null)
				if (!(file.getPath() + "/").equals(MockContentService.FOLDER_TEST))
					if (file.isDirectory())
						FileUtils.deleteDirectory(file);
					else
						file.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		nodeRefs.remove(nodeRef);
	}

	@Override
	public List<FileInfo> getNamePath(NodeRef rootNodeRef, NodeRef nodeRef) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getNameOnlyPath(NodeRef rootNodeRef, NodeRef nodeRef) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileInfo resolveNamePath(NodeRef rootNodeRef, List<String> pathElements) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileInfo resolveNamePath(NodeRef rootNodeRef, List<String> pathElements, boolean mustExist)
			throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileInfo getFileInfo(NodeRef nodeRef) {
		QName qname = ContentModel.TYPE_CONTENT;
		File file = getNodeService().getNodeRefs().get(nodeRef);
		if (file.isDirectory())
			qname = ContentModel.TYPE_FOLDER;
		return new MockFileInfo(nodeRef, file.getName(), qname);
	}

	@Override
	public ContentReader getReader(NodeRef nodeRef) {
		File file = getNodeService().getNodeRefs().get(nodeRef);
		return new MockContentReader(file);
	}

	@Override
	public ContentWriter getWriter(NodeRef nodeRef) {
		File file = getNodeService().getNodeRefs().get(nodeRef);
		return new MockContentWriter(file);
	}

	@Override
	public boolean exists(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FileFolderServiceType getType(QName typeQName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isHidden(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHidden(NodeRef nodeRef, boolean isHidden) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FileInfo> toFileInfoList(List<NodeRef> nodeRefs) {
		// TODO Auto-generated method stub
		return null;
	}

	public class MockFileInfo implements FileInfo {

		private NodeRef nodeRef;
		private String name;
		private QName typeQName;

		public MockFileInfo(NodeRef nodeRef, String name, QName typeQName) {
			this.nodeRef = nodeRef;
			this.name = name;
			this.typeQName = typeQName;
		}

		@Override
		public NodeRef getNodeRef() {
			return nodeRef;
		}

		@Override
		public boolean isFolder() {
			return typeQName.equals(ContentModel.TYPE_FOLDER);
		}

		@Override
		public boolean isLink() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isHidden() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public NodeRef getLinkNodeRef() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public Date getCreatedDate() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Date getModifiedDate() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ContentData getContentData() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<QName, Serializable> getProperties() {
			return nodeService.getProperties(nodeRef);
		}

		@Override
		public QName getType() {
			return typeQName;
		}

	};

	public MockNodeService getNodeService() {
		return (MockNodeService) nodeService;
	}

	public void setNodeService(MockNodeService nodeService) {
		this.nodeService = nodeService;
	}

	public NamespaceService getNamespaceService() {
		return namespaceService;
	}

	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

}
