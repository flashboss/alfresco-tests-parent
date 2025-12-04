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

/**
 * Mock implementation of MockFileFolderService for testing purposes.
 *
 * @author vige
 */
public class MockFileFolderService implements FileFolderService, Serializable {

	/** The node service. */
	@Autowired
	private NodeService nodeService;

	/** The namespace service. */
	@Autowired
	private NamespaceService namespaceService;

	/**
	 * List.
	 *
	 * @param contextNodeRef the context node ref
	 * @return the result
	 */
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

	/**
	 * List.
	 *
	 * @param contextNodeRef the context node ref
	 * @param files the files
	 * @param folders the folders
	 * @param ignoreTypeQNames the ignore type q names
	 * @param sortProps the sort props
	 * @param pagingRequest the paging request
	 * @return the result
	 */
	@Override
	public PagingResults<FileInfo> list(NodeRef contextNodeRef, boolean files, boolean folders,
			Set<QName> ignoreTypeQNames, List<Pair<QName, Boolean>> sortProps, PagingRequest pagingRequest) {
		List<FileInfo> result = list(contextNodeRef);
		PagingResults<FileInfo> pagingResults = new MockPagingResults<FileInfo>(result);
		return pagingResults;
	}

	/**
	 * List.
	 *
	 * @param contextNodeRef the context node ref
	 * @param files the files
	 * @param folders the folders
	 * @param pattern the pattern
	 * @param ignoreTypeQNames the ignore type q names
	 * @param sortProps the sort props
	 * @param pagingRequest the paging request
	 * @return the result
	 */
	@Override
	public PagingResults<FileInfo> list(NodeRef contextNodeRef, boolean files, boolean folders, String pattern,
			Set<QName> ignoreTypeQNames, List<Pair<QName, Boolean>> sortProps, PagingRequest pagingRequest) {
		return list(contextNodeRef, files, folders, ignoreTypeQNames, sortProps, pagingRequest);
	}

	/**
	 * List files.
	 *
	 * @param contextNodeRef the context node ref
	 * @return the result
	 */
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

	/**
	 * List folders.
	 *
	 * @param contextNodeRef the context node ref
	 * @return the result
	 */
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

	/**
	 * List deep folders.
	 *
	 * @param contextNodeRef the context node ref
	 * @param filter the filter
	 * @return the result
	 */
	@Override
	public List<FileInfo> listDeepFolders(NodeRef contextNodeRef, SubFolderFilter filter) {
		return recursiveDeep(contextNodeRef, filter, new ArrayList<FileInfo>(), false);
	}

	/**
	 * Get localized sibling.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	@Override
	public NodeRef getLocalizedSibling(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Search simple.
	 *
	 * @param contextNodeRef the context node ref
	 * @param name the name
	 * @return the result
	 */
	@Override
	public NodeRef searchSimple(NodeRef contextNodeRef, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Search.
	 *
	 * @param contextNodeRef the context node ref
	 * @param namePattern the name pattern
	 * @param includeSubFolders the include sub folders
	 * @return the result
	 */
	@Override
	public List<FileInfo> search(NodeRef contextNodeRef, String namePattern, boolean includeSubFolders) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Search.
	 *
	 * @param contextNodeRef the context node ref
	 * @param namePattern the name pattern
	 * @param fileSearch the file search
	 * @param folderSearch the folder search
	 * @param includeSubFolders the include sub folders
	 * @return the result
	 */
	@Override
	public List<FileInfo> search(NodeRef contextNodeRef, String namePattern, boolean fileSearch, boolean folderSearch,
			boolean includeSubFolders) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Rename.
	 *
	 * @param fileFolderRef the file folder ref
	 * @param newName the new name
	 * @return the result
	 */
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

	/**
	 * Move.
	 *
	 * @param sourceNodeRef the source node ref
	 * @param targetParentRef the target parent ref
	 * @param newName the new name
	 * @return the result
	 */
	@Override
	public FileInfo move(NodeRef sourceNodeRef, NodeRef targetParentRef, String newName)
			throws FileExistsException, FileNotFoundException {
		FileInfo fileInfo = copy(sourceNodeRef, targetParentRef, newName);
		nodeService.setProperty(fileInfo.getNodeRef(), MockNodeService.PRIMARY_PARENT, targetParentRef);
		delete(sourceNodeRef);
		return fileInfo;
	}

	/**
	 * Move from.
	 *
	 * @param sourceNodeRef the source node ref
	 * @param sourceParentRef the source parent ref
	 * @param targetParentRef the target parent ref
	 * @param newName the new name
	 * @return the result
	 */
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

	/**
	 * Move.
	 *
	 * @param sourceNodeRef the source node ref
	 * @param sourceParentRef the source parent ref
	 * @param targetParentRef the target parent ref
	 * @param newName the new name
	 * @return the result
	 */
	@Override
	public FileInfo move(NodeRef sourceNodeRef, NodeRef sourceParentRef, NodeRef targetParentRef, String newName)
			throws FileExistsException, FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Copy.
	 *
	 * @param sourceNodeRef the source node ref
	 * @param targetParentRef the target parent ref
	 * @param newName the new name
	 * @return the result
	 */
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
			File newDir = new File(target + "/" + newName);
			FileUtils.copyDirectory(source, newDir);
			File oldFile = new File(newDir + "/" + source.getName());
			File newFile = new File(newDir + "/" + newName);
			if (oldFile.exists() && !newFile.exists())
				FileUtils.moveFile(oldFile, newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new MockFileInfo(association.getChildRef(), newName, ContentModel.TYPE_CONTENT);
	}

	/**
	 * Create.
	 *
	 * @param parentNodeRef the parent node ref
	 * @param name the name
	 * @param typeQName the type q name
	 * @return the result
	 */
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

	/**
	 * Create.
	 *
	 * @param parentNodeRef the parent node ref
	 * @param name the name
	 * @param typeQName the type q name
	 * @param assocQName the assoc q name
	 * @return the result
	 */
	@Override
	public FileInfo create(NodeRef parentNodeRef, String name, QName typeQName, QName assocQName)
			throws FileExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Delete.
	 *
	 * @param nodeRef the node ref
	 */
	@Override
	public void delete(NodeRef nodeRef) {
		List<NodeRef> toRemove = new ArrayList<NodeRef>();
		Map<NodeRef, File> nodeRefs = getNodeService().getNodeRefs();
		String pathParent = nodeService.getPath(nodeRef).toString() + "/";
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

	/**
	 * Get name path.
	 *
	 * @param rootNodeRef the root node ref
	 * @param nodeRef the node ref
	 * @return the result
	 */
	@Override
	public List<FileInfo> getNamePath(NodeRef rootNodeRef, NodeRef nodeRef) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get name only path.
	 *
	 * @param rootNodeRef the root node ref
	 * @param nodeRef the node ref
	 * @return the result
	 */
	@Override
	public List<String> getNameOnlyPath(NodeRef rootNodeRef, NodeRef nodeRef) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Resolve name path.
	 *
	 * @param rootNodeRef the root node ref
	 * @param pathElements the path elements
	 * @return the result
	 */
	@Override
	public FileInfo resolveNamePath(NodeRef rootNodeRef, List<String> pathElements) throws FileNotFoundException {
		return resolveNamePath(rootNodeRef, pathElements, false);
	}

	/**
	 * Resolve name path.
	 *
	 * @param rootNodeRef the root node ref
	 * @param pathElements the path elements
	 * @param mustExist the must exist
	 * @return the result
	 */
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

	/**
	 * Get file info.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	@Override
	public FileInfo getFileInfo(NodeRef nodeRef) {
		QName qname = ContentModel.TYPE_CONTENT;
		File file = getNodeService().getNodeRefs().get(nodeRef);
		if (file == null)
			throw new InvalidNodeRefException(nodeRef);
		if (!new File(file + "/" + file.getName()).exists())
			qname = ContentModel.TYPE_FOLDER;
		return new MockFileInfo(nodeRef, file.getName(), qname);
	}

	/**
	 * Get reader.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	@Override
	public ContentReader getReader(NodeRef nodeRef) {
		File file = getNodeService().getNodeRefs().get(nodeRef);
		File content = new File(file.getAbsolutePath() + "/" + file.getName());
		return new FileContentReader(content);
	}

	/**
	 * Get writer.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	@Override
	public ContentWriter getWriter(NodeRef nodeRef) {
		File file = getNodeService().getNodeRefs().get(nodeRef);
		return new MockContentWriter(file, nodeRef, nodeService);
	}

	/**
	 * Exists.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	@Override
	public boolean exists(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Get type.
	 *
	 * @param typeQName the type q name
	 * @return the result
	 */
	@Override
	public FileFolderServiceType getType(QName typeQName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Is hidden.
	 *
	 * @param nodeRef the node ref
	 * @return the result
	 */
	@Override
	public boolean isHidden(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Set hidden.
	 *
	 * @param nodeRef the node ref
	 * @param isHidden the is hidden
	 */
	@Override
	public void setHidden(NodeRef nodeRef, boolean isHidden) {
		// TODO Auto-generated method stub

	}

	/**
	 * To file info list.
	 *
	 * @param nodeRefs the node refs
	 * @return the result
	 */
	@Override
	public List<FileInfo> toFileInfoList(List<NodeRef> nodeRefs) {
		// TODO Auto-generated method stub
		return null;
	}

	public class MockFileInfo implements FileInfo {

	/** The node ref. */
		private NodeRef nodeRef;
	/** The name. */
		private String name;
	/** The type q name. */
		private QName typeQName;

	/**
	 * Constructs a new MockFileInfo.
	 *
	 * @param nodeRef the node ref
	 * @param name the name
	 * @param typeQName the type q name
	 */
		public MockFileInfo(NodeRef nodeRef, String name, QName typeQName) {
			this.nodeRef = nodeRef;
			this.name = name;
			this.typeQName = typeQName;
		}

		@Override
	/**
	 * Get node ref.
	 *
	 * @return the result
	 */
		public NodeRef getNodeRef() {
			return nodeRef;
		}

		@Override
	/**
	 * Is folder.
	 *
	 * @return the result
	 */
		public boolean isFolder() {
			return typeQName.equals(ContentModel.TYPE_FOLDER);
		}

		@Override
	/**
	 * Is link.
	 *
	 * @return the result
	 */
		public boolean isLink() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
	/**
	 * Is hidden.
	 *
	 * @return the result
	 */
		public boolean isHidden() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
	/**
	 * Get link node ref.
	 *
	 * @return the result
	 */
		public NodeRef getLinkNodeRef() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
	/**
	 * Get name.
	 *
	 * @return the result
	 */
		public String getName() {
			return name;
		}

		@Override
	/**
	 * Get created date.
	 *
	 * @return the result
	 */
		public Date getCreatedDate() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
	/**
	 * Get modified date.
	 *
	 * @return the result
	 */
		public Date getModifiedDate() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
	/**
	 * Get content data.
	 *
	 * @return the result
	 */
		public ContentData getContentData() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
	/**
	 * Get properties.
	 *
	 */
		public Map<QName, Serializable> getProperties() {
			return nodeService.getProperties(nodeRef);
		}

		@Override
	/**
	 * Get type.
	 *
	 * @return the result
	 */
		public QName getType() {
			return typeQName;
		}

	};

	/**
	 * Get node service.
	 *
	 * @return the result
	 */
	public MockNodeService getNodeService() {
		return (MockNodeService) nodeService;
	}

	/**
	 * Set node service.
	 *
	 * @param nodeService the node service
	 */
	public void setNodeService(MockNodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * Get namespace service.
	 *
	 * @return the result
	 */
	public NamespaceService getNamespaceService() {
		return namespaceService;
	}

	/**
	 * Set namespace service.
	 *
	 * @param namespaceService the namespace service
	 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
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
