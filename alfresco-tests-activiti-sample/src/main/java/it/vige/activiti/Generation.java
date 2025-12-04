package it.vige.activiti;

import java.util.List;
import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.repo.workflow.activiti.BaseJavaDelegate;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class providing functionality for Alfresco testing.
 *
 * @author vige
 */
public class Generation extends BaseJavaDelegate {
  /** The logger. */
  private static Log logger = LogFactory.getLog(Generation.class);

  /** The rar folder. */
  private String rarFolder;

  /** The node service. */
  private NodeService nodeService;

  /** The search service. */
  private SearchService searchService;

  /** The namespace service. */
  private NamespaceService namespaceService;

  /** The file folder service. */
  private FileFolderService fileFolderService;

  /**
   * Execute.
   *
   * @param execution the execution
   */
  public void execute(DelegateExecution execution) throws Exception {
    logger.debug("Generation start");
    NodeRef rootNodeRef = nodeService.getRootNode(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
    List<NodeRef> results =
        searchService.selectNodes(rootNodeRef, rarFolder, null, namespaceService, false);
    if (results.size() == 0) {
      throw new AlfrescoRuntimeException(rarFolder);
    }
    NodeRef irarFolderNodeRef = results.get(0);
    logger.debug("Node found:" + irarFolderNodeRef.getId());
    int rarCounter =
        (int) nodeService.getProperty(irarFolderNodeRef, SimpleModel.PROP_RAR_ID_COUNTER);
    nodeService.setProperty(irarFolderNodeRef, SimpleModel.PROP_RAR_ID_COUNTER, rarCounter + 1);
    ActivitiScriptNode bpmPackage = (ActivitiScriptNode) execution.getVariable("bpm_package");
    List<ChildAssociationRef> relatedSaSsChild =
        nodeService.getChildAssocs(bpmPackage.getNodeRef());
    for (ChildAssociationRef relatedSaSChild : relatedSaSsChild) {
      FileInfo fileInfo =
          fileFolderService.copy(relatedSaSChild.getChildRef(), irarFolderNodeRef, null);
      logger.info(fileInfo);
    }
    execution.setVariable("mywf_rarId", rarCounter);
  }

  /**
   * Set rar folder.
   *
   * @param rarFolder the rar folder
   */
  public void setRarFolder(String rarFolder) {
    this.rarFolder = rarFolder;
  }

  /**
   * Set node service.
   *
   * @param nodeService the node service
   */
  public void setNodeService(NodeService nodeService) {
    this.nodeService = nodeService;
  }

  /**
   * Set search service.
   *
   * @param searchService the search service
   */
  public void setSearchService(SearchService searchService) {
    this.searchService = searchService;
  }

  /**
   * Set namespace service.
   *
   * @param namespaceService the namespace service
   */
  public void setNamespaceService(NamespaceService namespaceService) {
    this.namespaceService = namespaceService;
  }

  /**
   * Set file folder service.
   *
   * @param fileFolderService the file folder service
   */
  public void setFileFolderService(FileFolderService fileFolderService) {
    this.fileFolderService = fileFolderService;
  }
}
