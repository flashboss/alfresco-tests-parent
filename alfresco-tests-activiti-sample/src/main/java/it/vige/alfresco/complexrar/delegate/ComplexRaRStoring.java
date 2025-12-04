package it.vige.alfresco.complexrar.delegate;

import it.vige.common.ConservationModel;
import it.vige.common.SignConstants;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ComplexRaRStoring implementation for testing purposes.
 *
 * @author vige
 */
public class ComplexRaRStoring extends ComplexRaRGeneration {

  /** The logger. */
  private static Log logger = LogFactory.getLog(ComplexRaRStoring.class);

  /** The file folder service. */
  protected FileFolderService fileFolderService;

  /**
   * Set file folder service.
   *
   * @param fileFolderService the file folder service
   */
  public void setFileFolderService(FileFolderService fileFolderService) {
    this.fileFolderService = fileFolderService;
  }

  /**
   * Execute.
   *
   * @param execution the execution
   */
  public void execute(DelegateExecution execution) throws Exception {
    logger.debug("Execute start");
    int rarId = (int) execution.getVariable("vigewf_rarId");
    NodeRef rootNodeRef = nodeService.getRootNode(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
    List<NodeRef> results =
        searchService.selectNodes(rootNodeRef, rarFolder, null, namespaceService, false);
    if (results.size() == 0) {
      throw new AlfrescoRuntimeException(rarFolder);
    }
    NodeRef rarFolderNodeRef = results.get(0);
    String paddedRarId = String.format("%05d", rarId);
    String rarNodeName = "RAR_" + paddedRarId;
    Map<QName, Serializable> rarProps = new HashMap<QName, Serializable>();
    rarProps.put(ContentModel.PROP_NAME, rarNodeName);
    ChildAssociationRef childAssRef =
        nodeService.createNode(
            rarFolderNodeRef,
            ContentModel.ASSOC_CONTAINS,
            QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, rarNodeName),
            ConservationModel.TYPE_RAR,
            rarProps);
    logger.debug("Created node with IRAR files");
    NodeRef rarNodeRef = childAssRef.getChildRef();
    ActivitiScriptNode bpmPackage = (ActivitiScriptNode) execution.getVariable("bpm_package");
    List<ChildAssociationRef> relatedSaSsChild =
        nodeService.getChildAssocs(bpmPackage.getNodeRef());
    for (ChildAssociationRef relatedSaSChild : relatedSaSsChild) {
      NodeRef relatedSaSNodeRef = relatedSaSChild.getChildRef();
      String relatedSASName =
          (String) nodeService.getProperty(relatedSaSNodeRef, ContentModel.PROP_NAME);
      NodeRef relateSaSParentNodeRef =
          nodeService.getPrimaryParent(relatedSaSNodeRef).getParentRef();
      fileFolderService.moveFrom(
          relatedSaSNodeRef, relateSaSParentNodeRef, rarNodeRef, relatedSASName);

      String relatedISaSName;
      if (relatedSASName.endsWith(SignConstants.P7M_EXTENSION)) {
        relatedISaSName =
            "i" + relatedSASName.replaceFirst("zip." + SignConstants.P7M_EXTENSION, "xml");
      } else {
        relatedISaSName = "i" + relatedSASName.replaceFirst("zip", "xml");
      }

      NodeRef relateISaSNodeRef =
          nodeService.getChildByName(
              relateSaSParentNodeRef, ContentModel.ASSOC_CONTAINS, relatedISaSName);
      fileFolderService.moveFrom(
          relateISaSNodeRef, relateSaSParentNodeRef, rarNodeRef, relatedISaSName);
    }
    logger.debug("I took all the sas files to be sent for conservation ");
    String irarNodeRefString = (String) execution.getVariable("vigewf_relatedIRaR");
    NodeRef irarNodeRef = new NodeRef(irarNodeRefString);
    NodeRef irarParentNodeRef = nodeService.getPrimaryParent(irarNodeRef).getParentRef();
    String irarName = (String) nodeService.getProperty(irarNodeRef, ContentModel.PROP_NAME);
    logger.debug("Taken name IRAR with the counter value from the Workflow");
    // adds the aspect to move the content to the new content store
    Map<QName, Serializable> metadataStoreSel = new HashMap<>();
    metadataStoreSel.put(ContentModel.PROP_STORE_NAME, "storeComplexBankRar");
    nodeService.addAspect(irarNodeRef, ContentModel.ASPECT_STORE_SELECTOR, metadataStoreSel);
    logger.debug("Added aspect for changing repository");
    fileFolderService.moveFrom(irarNodeRef, irarParentNodeRef, rarNodeRef, irarName);
    logger.debug("Execute stop");
  }
}
;
