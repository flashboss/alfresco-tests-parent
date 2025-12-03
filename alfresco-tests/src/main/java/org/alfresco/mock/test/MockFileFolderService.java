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
 * Mock implementation of the {@link FileFolderService} interface for testing purposes.
 * This class provides file and folder operations such as listing, creating, copying,
 * moving, renaming, and deleting nodes in a mock Alfresco repository backed by the file system.
 *
 * @author lucastancapiano
 */
public class MockFileFolderService implements FileFolderService, Serializable {

	/**
	 * The node service for node operations.
	 */
	@Autowired
	private NodeService nodeService;

	/**
	 * The namespace service for QName resolution.
	 */
	@Autowired
	private NamespaceService namespaceService;

	/**
	 * {@inheritDoc}
	 * Lists all file and folder children of the specified node.
	 *
	 * @param contextNodeRef The parent node reference.
	 * @return A list of {@link FileInfo} objects representing the children.
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
	 * {@inheritDoc}
	 * Lists file and folder children with paging support.
	 *
	 * @param contextNodeRef The parent node reference.
	 * @param files Whether to include files.
	 * @param folders Whether to include folders.
	 * @param ignoreTypeQNames Set of type QNames to ignore.
	 * @param sortProps Sort properties.
	 * @param pagingRequest Paging parameters.
	 * @return Paging results containing the list of children.
	 */
	@Override
	public PagingResults<FileInfo> list(NodeRef contextNodeRef, boolean files, boolean folders,
			Set<QName> ignoreTypeQNames, List<Pair<QName, Boolean>> sortProps, PagingRequest pagingRequest) {
		List<FileInfo> result = list(contextNodeRef);
		PagingResults<FileInfo> pagingResults = new MockPagingResults<FileInfo>(result);
		return pagingResults;
	}

	/**
	 * {@inheritDoc}
	 * Lists file and folder children matching a pattern with paging support.
	 *
	 * @param contextNodeRef The parent node reference.
	 * @param files Whether to include files.
	 * @param folders Whether to include folders.
	 * @param pattern The name pattern to match.
	 * @param ignoreTypeQNames Set of type QNames to ignore.
	 * @param sortProps Sort properties.
	 * @param pagingRequest Paging parameters.
	 * @return Paging results containing the list of matching children.
	 */
	@Override
	public PagingResults<FileInfo> list(NodeRef contextNodeRef, boolean files, boolean folders, String pattern,
			Set<QName> ignoreTypeQNames, List<Pair<QName, Boolean>> sortProps, PagingRequest pagingRequest) {
		return list(contextNodeRef, files, folders, ignoreTypeQNames, sortProps, pagingRequest);
	}

	/**
	 * {@inheritDoc}
	 * Lists only file children (not folders) of the specified node.
	 *
	 * @param contextNodeRef The parent node reference.
	 * @return A list of {@link FileInfo} objects representing the files.
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
	 * {@inheritDoc}
	 * Lists only folder children (not files) of the specified node.
	 *
	 * @param contextNodeRef The parent node reference.
	 * @return A list of {@link FileInfo} objects representing the folders.
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
	 * {@inheritDoc}
	 * Lists all folders recursively within the specified node.
	 *
	 * @param contextNodeRef The parent node reference.
	 * @param filter Optional filter for subfolder inclusion.
	 * @return A list of {@link FileInfo} objects representing all nested folders.
	 */
	@Override
	public List<FileInfo> listDeepFolders(NodeRef contextNodeRef, SubFolderFilter filter) {
		return recursiveDeep(contextNodeRef, filter, new ArrayList<FileInfo>(), false);
	}

	/**
	 * {@inheritDoc}
	 * Gets the localized sibling of the specified node.
	 *
	 * @param nodeRef The node reference.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public NodeRef getLocalizedSibling(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Searches for a child node by name within the specified context.
	 *
	 * @param contextNodeRef The parent node reference.
	 * @param name The name to search for.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public NodeRef searchSimple(NodeRef contextNodeRef, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Searches for files matching a name pattern.
	 *
	 * @param contextNodeRef The context node reference.
	 * @param namePattern The name pattern to match.
	 * @param includeSubFolders Whether to search in subfolders.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public List<FileInfo> search(NodeRef contextNodeRef, String namePattern, boolean includeSubFolders) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Searches for files or folders matching a name pattern.
	 *
	 * @param contextNodeRef The context node reference.
	 * @param namePattern The name pattern to match.
	 * @param fileSearch Whether to search files.
	 * @param folderSearch Whether to search folders.
	 * @param includeSubFolders Whether to search in subfolders.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public List<FileInfo> search(NodeRef contextNodeRef, String namePattern, boolean fileSearch, boolean folderSearch,
			boolean includeSubFolders) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Renames a file or folder.
	 *
	 * @param fileFolderRef The node reference of the file or folder to rename.
	 * @param newName The new name.
	 * @return The {@link FileInfo} of the renamed node.
	 * @throws FileExistsException If a file with the new name already exists.
	 * @throws FileNotFoundException If the file is not found.
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
	 * {@inheritDoc}
	 * Moves a file or folder to a new parent location.
	 *
	 * @param sourceNodeRef The source node reference.
	 * @param targetParentRef The target parent node reference.
	 * @param newName The new name for the moved node (optional).
	 * @return The {@link FileInfo} of the moved node.
	 * @throws FileExistsException If a file with the name already exists at the target.
	 * @throws FileNotFoundException If the source file is not found.
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
	 * {@inheritDoc}
	 * Moves a file or folder from a specific parent to a new parent location.
	 *
	 * @param sourceNodeRef The source node reference.
	 * @param sourceParentRef The source parent node reference.
	 * @param targetParentRef The target parent node reference.
	 * @param newName The new name for the moved node (optional).
	 * @return The {@link FileInfo} of the moved node.
	 * @throws FileExistsException If a file with the name already exists at the target.
	 * @throws FileNotFoundException If the source file is not found.
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
	 * {@inheritDoc}
	 * Moves a file or folder with explicit source and target parents.
	 *
	 * @param sourceNodeRef The source node reference.
	 * @param sourceParentRef The source parent node reference.
	 * @param targetParentRef The target parent node reference.
	 * @param newName The new name for the moved node (optional).
	 * @return {@code null} as this is a mock implementation.
	 * @throws FileExistsException If a file with the name already exists at the target.
	 * @throws FileNotFoundException If the source file is not found.
	 */
	@Override
	public FileInfo move(NodeRef sourceNodeRef, NodeRef sourceParentRef, NodeRef targetParentRef, String newName)
			throws FileExistsException, FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Copies a file or folder to a new location.
	 *
	 * @param sourceNodeRef The source node reference.
	 * @param targetParentRef The target parent node reference.
	 * @param newName The new name for the copied node (optional).
	 * @return The {@link FileInfo} of the copied node.
	 * @throws FileExistsException If a file with the name already exists at the target.
	 * @throws FileNotFoundException If the source file is not found.
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
	 * {@inheritDoc}
	 * Creates a new file or folder.
	 *
	 * @param parentNodeRef The parent node reference.
	 * @param name The name of the new file or folder.
	 * @param typeQName The type QName (folder or content).
	 * @return The {@link FileInfo} of the created node.
	 * @throws FileExistsException If a file with the name already exists.
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
	 * {@inheritDoc}
	 * Creates a new file or folder with a specific association QName.
	 *
	 * @param parentNodeRef The parent node reference.
	 * @param name The name of the new file or folder.
	 * @param typeQName The type QName (folder or content).
	 * @param assocQName The association QName.
	 * @return {@code null} as this is a mock implementation.
	 * @throws FileExistsException If a file with the name already exists.
	 */
	@Override
	public FileInfo create(NodeRef parentNodeRef, String name, QName typeQName, QName assocQName)
			throws FileExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Deletes a file or folder and all its children.
	 *
	 * @param nodeRef The node reference to delete.
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
	 * {@inheritDoc}
	 * Gets the name path from a root node to a target node.
	 *
	 * @param rootNodeRef The root node reference.
	 * @param nodeRef The target node reference.
	 * @return {@code null} as this is a mock implementation.
	 * @throws FileNotFoundException If the file is not found.
	 */
	@Override
	public List<FileInfo> getNamePath(NodeRef rootNodeRef, NodeRef nodeRef) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Resolves a name path to a file info.
	 *
	 * @param rootNodeRef The root node reference.
	 * @param pathElements The path elements.
	 * @return The resolved {@link FileInfo}.
	 * @throws FileNotFoundException If the file is not found.
	 */
	@Override
	public FileInfo resolveNamePath(NodeRef rootNodeRef, List<String> pathElements) throws FileNotFoundException {
		return resolveNamePath(rootNodeRef, pathElements, false);
	}

	/**
	 * {@inheritDoc}
	 * Resolves a name path to a file info with optional existence check.
	 *
	 * @param rootNodeRef The root node reference.
	 * @param pathElements The path elements.
	 * @param mustExist Whether the file must exist.
	 * @return The resolved {@link FileInfo} or {@code null} if not found.
	 * @throws FileNotFoundException If the file is not found and mustExist is true.
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
	 * {@inheritDoc}
	 * Gets the file info for a node.
	 *
	 * @param nodeRef The node reference.
	 * @return The {@link FileInfo} for the node.
	 * @throws InvalidNodeRefException If the node reference is invalid.
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
	 * {@inheritDoc}
	 * Gets a content reader for a node.
	 *
	 * @param nodeRef The node reference.
	 * @return A {@link ContentReader} for the node's content.
	 */
	@Override
	public ContentReader getReader(NodeRef nodeRef) {
		File file = getNodeService().getNodeRefs().get(nodeRef);
		File content = new File(file.getAbsolutePath() + "/" + file.getName());
		return new FileContentReader(content);
	}

	/**
	 * {@inheritDoc}
	 * Gets a content writer for a node.
	 *
	 * @param nodeRef The node reference.
	 * @return A {@link ContentWriter} for writing to the node's content.
	 */
	@Override
	public ContentWriter getWriter(NodeRef nodeRef) {
		File file = getNodeService().getNodeRefs().get(nodeRef);
		return new MockContentWriter(file, nodeRef, nodeService);
	}

	/**
	 * {@inheritDoc}
	 * Checks if a node exists.
	 *
	 * @param nodeRef The node reference.
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean exists(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Gets the file folder service type for a type QName.
	 *
	 * @param typeQName The type QName.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public FileFolderServiceType getType(QName typeQName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Mock implementation of the {@link FileInfo} interface for testing purposes.
	 * Represents basic file or folder information in the mock repository.
	 */
	public class MockFileInfo implements FileInfo {

		/**
		 * The node reference.
		 */
		private NodeRef nodeRef;

		/**
		 * The file or folder name.
		 */
		private String name;

		/**
		 * The type QName (folder or content).
		 */
		private QName typeQName;

		/**
		 * Constructs a new MockFileInfo with the specified parameters.
		 *
		 * @param nodeRef The node reference.
		 * @param name The file or folder name.
		 * @param typeQName The type QName.
		 */
		public MockFileInfo(NodeRef nodeRef, String name, QName typeQName) {
			this.nodeRef = nodeRef;
			this.name = name;
			this.typeQName = typeQName;
		}

		/**
		 * {@inheritDoc}
		 * @return The node reference.
		 */
		@Override
		public NodeRef getNodeRef() {
			return nodeRef;
		}

		/**
		 * {@inheritDoc}
		 * @return {@code true} if this is a folder, {@code false} otherwise.
		 */
		@Override
		public boolean isFolder() {
			return typeQName.equals(ContentModel.TYPE_FOLDER);
		}

		/**
		 * {@inheritDoc}
		 * @return {@code false} as links are not implemented.
		 */
		@Override
		public boolean isLink() {
			// TODO Auto-generated method stub
			return false;
		}

		/**
		 * {@inheritDoc}
		 * @return {@code false} as hidden files are not tracked.
		 */
		@Override
		public boolean isHidden() {
			// TODO Auto-generated method stub
			return false;
		}

		/**
		 * {@inheritDoc}
		 * @return {@code null} as links are not implemented.
		 */
		@Override
		public NodeRef getLinkNodeRef() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * {@inheritDoc}
		 * @return The file or folder name.
		 */
		@Override
		public String getName() {
			return name;
		}

		/**
		 * {@inheritDoc}
		 * @return {@code null} as creation date is not tracked.
		 */
		@Override
		public Date getCreatedDate() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * {@inheritDoc}
		 * @return {@code null} as modification date is not tracked.
		 */
		@Override
		public Date getModifiedDate() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * {@inheritDoc}
		 * @return {@code null} as content data is not tracked.
		 */
		@Override
		public ContentData getContentData() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * {@inheritDoc}
		 * @return The properties map for this node.
		 */
		@Override
		public Map<QName, Serializable> getProperties() {
			return nodeService.getProperties(nodeRef);
		}

		/**
		 * {@inheritDoc}
		 * @return The type QName.
		 */
		@Override
		public QName getType() {
			return typeQName;
		}

	};

	/**
	 * Gets the mock node service.
	 *
	 * @return The {@link MockNodeService} instance.
	 */
	public MockNodeService getNodeService() {
		return (MockNodeService) nodeService;
	}

	/**
	 * Sets the mock node service.
	 *
	 * @param nodeService The {@link MockNodeService} instance to set.
	 */
	public void setNodeService(MockNodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * Gets the namespace service.
	 *
	 * @return The {@link NamespaceService} instance.
	 */
	public NamespaceService getNamespaceService() {
		return namespaceService;
	}

	/**
	 * Sets the namespace service.
	 *
	 * @param namespaceService The {@link NamespaceService} instance to set.
	 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	/**
	 * Recursively traverses the folder structure to collect all nested items.
	 *
	 * @param contextNodeRef The starting node reference.
	 * @param filter Optional filter for subfolder inclusion.
	 * @param result The list to accumulate results.
	 * @param withFiles Whether to include files in addition to folders.
	 * @return The accumulated list of file info objects.
	 */
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

	/**
	 * Recursively copies the children of a source node to a target parent.
	 *
	 * @param sourceNodeRef The source node reference.
	 * @param targetParentRef The target parent node reference.
	 */
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
