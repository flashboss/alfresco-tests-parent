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
import org.alfresco.repo.node.getchildren.FilterProp;
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
 * Mock implementation of the MockFileFolderService class for testing purposes. This class provides
 * a mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockFileFolderService implements FileFolderService, Serializable {

  /** The node service. */
  @Autowired private NodeService nodeService;

  /** The namespace service. */
  @Autowired private NamespaceService namespaceService;

  /**
   * {@inheritDoc}
   *
   * @param contextNodeRef the contextNodeRef
   * @return the result
   */
  @Override
  public List<FileInfo> list(NodeRef contextNodeRef) {
    List<FileInfo> result = new ArrayList<FileInfo>();
    List<ChildAssociationRef> associationRefs = nodeService.getChildAssocs(contextNodeRef);
    for (ChildAssociationRef associationRef : associationRefs) {
      FileInfo fileInfo =
          new MockFileInfo(
              associationRef.getChildRef(),
              associationRef.getQName().getLocalName(),
              associationRef.getQName());
      result.add(fileInfo);
    }
    return result;
  }

  /** {@inheritDoc} */
  @Override
  public PagingResults<FileInfo> list(
      NodeRef contextNodeRef,
      boolean files,
      boolean folders,
      Set<QName> ignoreTypeQNames,
      List<Pair<QName, Boolean>> sortProps,
      PagingRequest pagingRequest) {
    List<FileInfo> result = list(contextNodeRef);
    PagingResults<FileInfo> pagingResults = new MockPagingResults<FileInfo>(result);
    return pagingResults;
  }

  /** {@inheritDoc} */
  @Override
  public PagingResults<FileInfo> list(
      NodeRef contextNodeRef,
      boolean files,
      boolean folders,
      String pattern,
      Set<QName> ignoreTypeQNames,
      List<Pair<QName, Boolean>> sortProps,
      PagingRequest pagingRequest) {
    return list(contextNodeRef, files, folders, ignoreTypeQNames, sortProps, pagingRequest);
  }

  /**
   * {@inheritDoc}
   *
   * @param contextNodeRef the contextNodeRef
   * @return the result
   */
  @Override
  public List<FileInfo> listFiles(NodeRef contextNodeRef) {
    List<FileInfo> allFiles = list(contextNodeRef);
    List<FileInfo> onlyFiles = new ArrayList<FileInfo>();
    for (FileInfo fileInfo : allFiles) {
      if (!fileInfo.isFolder()) onlyFiles.add(fileInfo);
    }
    return onlyFiles;
  }

  /**
   * {@inheritDoc}
   *
   * @param contextNodeRef the contextNodeRef
   * @return the result
   */
  @Override
  public List<FileInfo> listFolders(NodeRef contextNodeRef) {
    List<FileInfo> allFiles = list(contextNodeRef);
    List<FileInfo> onlyFolders = new ArrayList<FileInfo>();
    for (FileInfo fileInfo : allFiles) {
      if (fileInfo.isFolder()) onlyFolders.add(fileInfo);
    }
    return onlyFolders;
  }

  /**
   * {@inheritDoc}
   *
   * @param contextNodeRef the contextNodeRef
   * @param filter the filter
   * @return the result
   */
  @Override
  public List<FileInfo> listDeepFolders(NodeRef contextNodeRef, SubFolderFilter filter) {
    return recursiveDeep(contextNodeRef, filter, new ArrayList<FileInfo>(), false);
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public NodeRef getLocalizedSibling(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param contextNodeRef the contextNodeRef
   * @param name the name
   * @return the result
   */
  @Override
  public NodeRef searchSimple(NodeRef contextNodeRef, String name) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param contextNodeRef the contextNodeRef
   * @param namePattern the namePattern
   * @param includeSubFolders the includeSubFolders
   * @return the result
   */
  @Override
  public List<FileInfo> search(
      NodeRef contextNodeRef, String namePattern, boolean includeSubFolders) {
    // TODO Auto-generated method stub
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public List<FileInfo> search(
      NodeRef contextNodeRef,
      String namePattern,
      boolean fileSearch,
      boolean folderSearch,
      boolean includeSubFolders) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param fileFolderRef the fileFolderRef
   * @param newName the newName
   * @return the result
   * @throws FileExistsException if an error occurs
   * @throws FileNotFoundException if an error occurs
   */
  @Override
  public FileInfo rename(NodeRef fileFolderRef, String newName)
      throws FileExistsException, FileNotFoundException {
    try {
      Map<NodeRef, File> nodeRefs = ((MockNodeService) nodeService).getNodeRefs();
      List<FileInfo> children = recursiveDeep(fileFolderRef, null, new ArrayList<FileInfo>(), true);
      File file = nodeRefs.get(fileFolderRef);
      String oldName = "/" + file.getName() + "/";
      Files.move(file.toPath(), file.toPath().resolveSibling(newName));
      nodeService.setProperty(fileFolderRef, ContentModel.PROP_NAME, newName);
      for (FileInfo fileInfo : children) {
        File fileChild = nodeRefs.get(fileInfo.getNodeRef());
        nodeRefs.put(
            fileInfo.getNodeRef(),
            new File(fileChild.getAbsolutePath().replaceAll(oldName, "/" + newName + "/")));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    FileInfo fileInfo =
        new MockFileInfo(fileFolderRef, newName, nodeService.getType(fileFolderRef));
    return fileInfo;
  }

  /**
   * {@inheritDoc}
   *
   * @param sourceNodeRef the sourceNodeRef
   * @param targetParentRef the targetParentRef
   * @param newName the newName
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
   * {@inheritDoc}
   *
   * @param sourceNodeRef the sourceNodeRef
   * @param sourceParentRef the sourceParentRef
   * @param targetParentRef the targetParentRef
   * @param newName the newName
   * @return the result
   */
  @Override
  public FileInfo moveFrom(
      NodeRef sourceNodeRef, NodeRef sourceParentRef, NodeRef targetParentRef, String newName)
      throws FileExistsException, FileNotFoundException {
    File file = getNodeService().getNodeRefs().get(targetParentRef);
    if (file.isFile()) {
      file.delete();
      file.mkdir();
    }
    String name = getNodeService().getNodeRefs().get(sourceNodeRef).getName();
    NodeRef originalNode =
        nodeService.getChildByName(sourceParentRef, ContentModel.ASSOC_CONTAINS, name);
    return move(originalNode, targetParentRef, newName);
  }

  /**
   * {@inheritDoc}
   *
   * @param sourceNodeRef the sourceNodeRef
   * @param sourceParentRef the sourceParentRef
   * @param targetParentRef the targetParentRef
   * @param newName the newName
   * @return the result
   */
  @Override
  public FileInfo move(
      NodeRef sourceNodeRef, NodeRef sourceParentRef, NodeRef targetParentRef, String newName)
      throws FileExistsException, FileNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param sourceNodeRef the sourceNodeRef
   * @param targetParentRef the targetParentRef
   * @param newName the newName
   * @return the result
   */
  @Override
  public FileInfo copy(NodeRef sourceNodeRef, NodeRef targetParentRef, String newName)
      throws FileExistsException, FileNotFoundException {
    if (newName == null) newName = nodeService.getPath(sourceNodeRef).last().getElementString();
    QName assocQName = QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, newName);
    ChildAssociationRef association =
        nodeService.createNode(
            targetParentRef,
            ContentModel.ASSOC_CONTAINS,
            assocQName,
            ContentModel.TYPE_CONTENT,
            nodeService.getProperties(sourceNodeRef));
    recursiveCopy(sourceNodeRef, association.getChildRef());
    File source = getNodeService().getNodeRefs().get(sourceNodeRef);
    File target = getNodeService().getNodeRefs().get(targetParentRef);
    try {
      File newDir = new File(target + "/" + newName);
      FileUtils.copyDirectory(source, newDir);
      File oldFile = new File(newDir + "/" + source.getName());
      File newFile = new File(newDir + "/" + newName);
      if (oldFile.exists() && !newFile.exists()) FileUtils.moveFile(oldFile, newFile);
    } catch (IOException | IllegalArgumentException e) {
      e.printStackTrace();
    }
    return new MockFileInfo(association.getChildRef(), newName, ContentModel.TYPE_CONTENT);
  }

  /**
   * {@inheritDoc}
   *
   * @param parentNodeRef the parentNodeRef
   * @param name the name
   * @param typeQName the typeQName
   * @return the result
   * @throws FileExistsException if an error occurs
   */
  @Override
  public FileInfo create(NodeRef parentNodeRef, String name, QName typeQName)
      throws FileExistsException {
    if (!parentNodeRef.getId().isEmpty()
        && nodeService.getPrimaryParent(parentNodeRef) != null
        && !name.contains(":")) {
      String prefix = NamespaceService.CONTENT_MODEL_PREFIX;
      name = prefix + ":" + name;
    }
    QName assocQName = QName.createQName(name);
    if (name.contains(":"))
      assocQName = QName.createQName(name.split(":")[0], name.split(":")[1], namespaceService);
    ChildAssociationRef association =
        nodeService.createNode(parentNodeRef, ContentModel.ASSOC_CONTAINS, assocQName, typeQName);
    return new MockFileInfo(association.getChildRef(), name, typeQName);
  }

  /**
   * {@inheritDoc}
   *
   * @param parentNodeRef the parentNodeRef
   * @param name the name
   * @param typeQName the typeQName
   * @param assocQName the assocQName
   * @return the result
   */
  @Override
  public FileInfo create(NodeRef parentNodeRef, String name, QName typeQName, QName assocQName)
      throws FileExistsException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   */
  @Override
  public void delete(NodeRef nodeRef) {
    List<NodeRef> toRemove = new ArrayList<NodeRef>();
    Map<NodeRef, File> nodeRefs = getNodeService().getNodeRefs();
    String pathParent = nodeService.getPath(nodeRef).toString() + "/";
    for (NodeRef node : nodeRefs.keySet()) {
      String pathChild = nodeService.getPath(node).toString();
      if (pathChild.contains(pathParent)) toRemove.add(node);
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
   *
   * @param rootNodeRef the rootNodeRef
   * @param nodeRef the nodeRef
   * @return the result
   * @throws FileNotFoundException if an error occurs
   */
  @Override
  public List<FileInfo> getNamePath(NodeRef rootNodeRef, NodeRef nodeRef)
      throws FileNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param rootNodeRef the rootNodeRef
   * @param nodeRef the nodeRef
   * @return the result
   * @throws FileNotFoundException if an error occurs
   */
  @Override
  public List<String> getNameOnlyPath(NodeRef rootNodeRef, NodeRef nodeRef)
      throws FileNotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param rootNodeRef the rootNodeRef
   * @param pathElements the pathElements
   * @return the result
   * @throws FileNotFoundException if an error occurs
   */
  @Override
  public FileInfo resolveNamePath(NodeRef rootNodeRef, List<String> pathElements)
      throws FileNotFoundException {
    return resolveNamePath(rootNodeRef, pathElements, false);
  }

  /**
   * {@inheritDoc}
   *
   * @param rootNodeRef the rootNodeRef
   * @param pathElements the pathElements
   * @param mustExist the mustExist
   * @return the result
   */
  @Override
  public FileInfo resolveNamePath(NodeRef rootNodeRef, List<String> pathElements, boolean mustExist)
      throws FileNotFoundException {
    NodeRef nodeRef = null;
    NodeRef parent = rootNodeRef;
    for (String path : pathElements) {
      nodeRef = nodeService.getChildByName(parent, ContentModel.ASSOC_CONTAINS, path);
      if (nodeRef == null) return null;
      parent = nodeRef;
    }
    return getFileInfo(nodeRef);
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public FileInfo getFileInfo(NodeRef nodeRef) {
    QName qname = ContentModel.TYPE_CONTENT;
    File file = getNodeService().getNodeRefs().get(nodeRef);
    if (file == null) throw new InvalidNodeRefException(nodeRef);
    if (!new File(file + "/" + file.getName()).exists()) qname = ContentModel.TYPE_FOLDER;
    return new MockFileInfo(nodeRef, file.getName(), qname);
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public ContentReader getReader(NodeRef nodeRef) {
    File file = getNodeService().getNodeRefs().get(nodeRef);
    File content = new File(file.getAbsolutePath() + "/" + file.getName());
    return new FileContentReader(content);
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public ContentWriter getWriter(NodeRef nodeRef) {
    File file = getNodeService().getNodeRefs().get(nodeRef);
    return new MockContentWriter(file, nodeRef, nodeService);
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public boolean exists(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @param typeQName the typeQName
   * @return the result
   */
  @Override
  public FileFolderServiceType getType(QName typeQName) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @return the result
   */
  @Override
  public boolean isHidden(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRef the nodeRef
   * @param isHidden the isHidden
   */
  @Override
  public void setHidden(NodeRef nodeRef, boolean isHidden) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param nodeRefs the nodeRefs
   * @return the result
   */
  @Override
  public List<FileInfo> toFileInfoList(List<NodeRef> nodeRefs) {
    // TODO Auto-generated method stub
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public PagingResults<FileInfo> list(
      NodeRef arg0,
      Set<QName> arg1,
      Set<QName> arg2,
      Set<QName> arg3,
      List<Pair<QName, Boolean>> arg4,
      List<FilterProp> arg5,
      PagingRequest arg6) {
    // TODO Auto-generated method stub
    return null;
  }

  /** Mock file info implementation. */
  public class MockFileInfo implements FileInfo {

    /** The node ref. */
    private NodeRef nodeRef;

    /** The name. */
    private String name;

    /** The type q name. */
    private QName typeQName;

    /**
     * Constructs a new MockFileInfo with the specified node reference, name, and type.
     *
     * @param nodeRef the node reference
     * @param name the file name
     * @param typeQName the type QName
     */
    public MockFileInfo(NodeRef nodeRef, String name, QName typeQName) {
      this.nodeRef = nodeRef;
      this.name = name;
      this.typeQName = typeQName;
    }

    /**
     * {@inheritDoc}
     *
     * @return the result
     */
    @Override
    public NodeRef getNodeRef() {
      return nodeRef;
    }

    /**
     * {@inheritDoc}
     *
     * @return the result
     */
    @Override
    public boolean isFolder() {
      return typeQName.equals(ContentModel.TYPE_FOLDER);
    }

    /**
     * {@inheritDoc}
     *
     * @return the result
     */
    @Override
    public boolean isLink() {
      // TODO Auto-generated method stub
      return false;
    }

    /**
     * {@inheritDoc}
     *
     * @return the result
     */
    @Override
    public boolean isHidden() {
      // TODO Auto-generated method stub
      return false;
    }

    /**
     * {@inheritDoc}
     *
     * @return the result
     */
    @Override
    public NodeRef getLinkNodeRef() {
      // TODO Auto-generated method stub
      return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return the result
     */
    @Override
    public String getName() {
      return name;
    }

    /**
     * {@inheritDoc}
     *
     * @return the result
     */
    @Override
    public Date getCreatedDate() {
      // TODO Auto-generated method stub
      return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return the result
     */
    @Override
    public Date getModifiedDate() {
      // TODO Auto-generated method stub
      return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return the result
     */
    @Override
    public ContentData getContentData() {
      // TODO Auto-generated method stub
      return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return the result
     */
    @Override
    public Map<QName, Serializable> getProperties() {
      return nodeService.getProperties(nodeRef);
    }

    /**
     * {@inheritDoc}
     *
     * @return the result
     */
    @Override
    public QName getType() {
      return typeQName;
    }
  }
  ;

  /**
   * Gets the node service.
   *
   * @return the node service
   */
  public MockNodeService getNodeService() {
    return (MockNodeService) nodeService;
  }

  /**
   * Sets the node service.
   *
   * @param nodeService the node service
   */
  public void setNodeService(MockNodeService nodeService) {
    this.nodeService = nodeService;
  }

  /**
   * Gets the namespace service.
   *
   * @return the namespace service
   */
  public NamespaceService getNamespaceService() {
    return namespaceService;
  }

  /**
   * Sets the namespace service.
   *
   * @param namespaceService the namespace service
   */
  public void setNamespaceService(NamespaceService namespaceService) {
    this.namespaceService = namespaceService;
  }

  /** {@inheritDoc} */
  @Override
  public PagingResults<FileInfo> list(
      NodeRef rootNodeRef,
      Set<QName> searchTypeQNames,
      Set<QName> ignoreAspectQNames,
      List<Pair<QName, Boolean>> sortProps,
      PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  private List<FileInfo> recursiveDeep(
      NodeRef contextNodeRef, SubFolderFilter filter, List<FileInfo> result, boolean withFiles) {
    List<FileInfo> nodes = null;
    if (withFiles) nodes = list(contextNodeRef);
    else nodes = listFolders(contextNodeRef);
    result.addAll(nodes);
    for (FileInfo fileInfo : nodes) {
      return recursiveDeep(fileInfo.getNodeRef(), filter, result, withFiles);
    }
    return result;
  }

  /**
   * Performs recursive copy.
   *
   * @param sourceNodeRef the sourceNodeRef
   * @param targetParentRef the targetParentRef
   */
  private void recursiveCopy(NodeRef sourceNodeRef, NodeRef targetParentRef) {
    List<ChildAssociationRef> children = nodeService.getChildAssocs(sourceNodeRef);
    for (ChildAssociationRef child : children) {
      File file = ((MockNodeService) nodeService).getNodeRefs().get(child.getChildRef());
      if (!file.isFile()) {
        ChildAssociationRef result =
            nodeService.createNode(
                targetParentRef,
                ContentModel.ASSOC_CONTAINS,
                QName.createQName(
                    "" + nodeService.getProperty(child.getChildRef(), ContentModel.PROP_NAME)),
                ContentModel.TYPE_CONTENT,
                nodeService.getProperties(sourceNodeRef));
        recursiveCopy(child.getChildRef(), result.getChildRef());
      }
    }
  }
}
