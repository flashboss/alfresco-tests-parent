package org.alfresco.mock.test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.repo.content.filestore.FileContentReader;
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
import org.alfresco.service.cmr.repository.InvalidNodeRefException;
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
					associationRef.getQName());
			result.add(fileInfo);
		}
		return result;
	}

	@Override
	public PagingResults<FileInfo> list(NodeRef contextNodeRef, boolean files, boolean folders,
			Set<QName> ignoreTypeQNames, List<Pair<QName, Boolean>> sortProps, PagingRequest pagingRequest) {
		List<FileInfo> result = list(contextNodeRef);
		PagingResults<FileInfo> pagingResults = new MockPagingResults<FileInfo>(result);
		return pagingResults;
	}

	@Override
	public PagingResults<FileInfo> list(NodeRef contextNodeRef, boolean files, boolean folders, String pattern,
			Set<QName> ignoreTypeQNames, List<Pair<QName, Boolean>> sortProps, PagingRequest pagingRequest) {
		return list(contextNodeRef, files, folders, ignoreTypeQNames, sortProps, pagingRequest);
	}

	@Override
	public List<FileInfo> listFiles(NodeRef contextNodeRef) {
		List<FileInfo> allFiles = list(contextNodeRef);
		List<FileInfo> onlyFiles = new ArrayList<FileInfo>();
		for (FileInfo fileInfo : allFiles) {
			if (!fileInfo.isFolder())
				onlyFiles.add(fileInfo);
		}
		return onlyFiles;
	}

	@Override
	public List<FileInfo> listFolders(NodeRef contextNodeRef) {
		List<FileInfo> allFiles = list(contextNodeRef);
		List<FileInfo> onlyFolders = new ArrayList<FileInfo>();
		for (FileInfo fileInfo : allFiles) {
			if (fileInfo.isFolder())
				onlyFolders.add(fileInfo);
		}
		return onlyFolders;
	}

	@Override
	public List<FileInfo> listDeepFolders(NodeRef contextNodeRef, SubFolderFilter filter) {
		return recursiveDeep(contextNodeRef, filter, new ArrayList<FileInfo>(), false);
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
		try {
			Map<NodeRef, File> nodeRefs = ((MockNodeService) nodeService).getNodeRefs();
			List<FileInfo> children = recursiveDeep(fileFolderRef, null, new ArrayList<FileInfo>(), true);
			File file = nodeRefs.get(fileFolderRef);
			String oldName = "/" + file.getName() + "/";
			Files.move(file.toPath(), file.toPath().resolveSibling(newName));
			nodeService.setProperty(fileFolderRef, ContentModel.PROP_NAME, newName);
			for (FileInfo fileInfo : children) {
				File fileChild = nodeRefs.get(fileInfo.getNodeRef());
				nodeRefs.put(fileInfo.getNodeRef(),
						new File(fileChild.getAbsolutePath().replaceAll(oldName, "/" + newName + "/")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileInfo fileInfo = new MockFileInfo(fileFolderRef, newName, nodeService.getType(fileFolderRef));
		return fileInfo;
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
		recursiveCopy(sourceNodeRef, association.getChildRef());
		File source = getNodeService().getNodeRefs().get(sourceNodeRef);
		File target = getNodeService().getNodeRefs().get(targetParentRef);
		try {
			File newDir = new File(target + File.separator + newName);
			FileUtils.copyDirectory(source, newDir);
			File oldFile = new File(newDir + File.separator + source.getName());
			File newFile = new File(newDir + File.separator + newName);
			if (oldFile.exists() && !newFile.exists())
				FileUtils.moveFile(oldFile, newFile);
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
					FileUtils.deleteDirectory(file);
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
		return resolveNamePath(rootNodeRef, pathElements, false);
	}

	@Override
	public FileInfo resolveNamePath(NodeRef rootNodeRef, List<String> pathElements, boolean mustExist)
			throws FileNotFoundException {
		NodeRef nodeRef = null;
		NodeRef parent = rootNodeRef;
		for (String path : pathElements) {
			nodeRef = nodeService.getChildByName(parent, ContentModel.ASSOC_CONTAINS, path);
			if (nodeRef == null)
				return null;
			parent = nodeRef;
		}
		return getFileInfo(nodeRef);
	}

	@Override
	public FileInfo getFileInfo(NodeRef nodeRef) {
		QName qname = ContentModel.TYPE_CONTENT;
		File file = getNodeService().getNodeRefs().get(nodeRef);
		if (file == null)
			throw new InvalidNodeRefException(nodeRef);
		if (!new File(file + File.separator + file.getName()).exists())
			qname = ContentModel.TYPE_FOLDER;
		return new MockFileInfo(nodeRef, file.getName(), qname);
	}

	@Override
	public ContentReader getReader(NodeRef nodeRef) {
		File file = getNodeService().getNodeRefs().get(nodeRef);
		File content = new File(file.getAbsolutePath() + File.separator + file.getName());
		return new FileContentReader(content);
	}

	@Override
	public ContentWriter getWriter(NodeRef nodeRef) {
		File file = getNodeService().getNodeRefs().get(nodeRef);
		return new MockContentWriter(file, nodeRef, nodeService);
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

	@Override
	public PagingResults<FileInfo> list(NodeRef rootNodeRef, Set<QName> searchTypeQNames, Set<QName> ignoreAspectQNames,
			List<Pair<QName, Boolean>> sortProps, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<FileInfo> recursiveDeep(NodeRef contextNodeRef, SubFolderFilter filter, List<FileInfo> result,
			boolean withFiles) {
		List<FileInfo> nodes = null;
		if (withFiles)
			nodes = list(contextNodeRef);
		else
			nodes = listFolders(contextNodeRef);
		result.addAll(nodes);
		for (FileInfo fileInfo : nodes) {
			return recursiveDeep(fileInfo.getNodeRef(), filter, result, withFiles);
		}
		return result;
	}

	private void recursiveCopy(NodeRef sourceNodeRef, NodeRef targetParentRef) {
		List<ChildAssociationRef> children = nodeService.getChildAssocs(sourceNodeRef);
		for (ChildAssociationRef child : children) {
			File file = ((MockNodeService) nodeService).getNodeRefs().get(child.getChildRef());
			if (!file.isFile()) {
				ChildAssociationRef result = nodeService.createNode(targetParentRef, ContentModel.ASSOC_CONTAINS,
						QName.createQName("" + nodeService.getProperty(child.getChildRef(), ContentModel.PROP_NAME)),
						ContentModel.TYPE_CONTENT, nodeService.getProperties(sourceNodeRef));
				recursiveCopy(child.getChildRef(), result.getChildRef());
			}
		}
	}

}
