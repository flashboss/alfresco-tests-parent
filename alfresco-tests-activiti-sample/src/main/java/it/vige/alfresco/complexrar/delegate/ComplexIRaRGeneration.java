package it.vige.alfresco.complexrar.delegate;

import it.vige.common.ConservationModel;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.repo.workflow.activiti.BaseJavaDelegate;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.repository.TemplateService;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.javascript.NativeArray;

/**
 * Mock implementation of the ComplexIRaRGeneration class for testing purposes. This class provides
 * a mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class ComplexIRaRGeneration extends BaseJavaDelegate {

  private static Log logger = LogFactory.getLog(ComplexIRaRGeneration.class);

  private static String IRAR_NODE_NAME_PREFIX = "IRaR_";
  private static String IRAR_NODE_NAME_SUFFIX = ".xml";

  /** The template service. */
  private TemplateService templateService;

  /** The node service. */
  private NodeService nodeService;

  /** The content service. */
  private ContentService contentService;

  /** The namespace service. */
  private NamespaceService namespaceService;

  /** The search service. */
  private SearchService searchService;

  /** The irar template. */
  private String irarTemplate;

  /** The irar folder. */
  private String irarFolder;

  /**
   * Performs generate i ra r.
   *
   * @param rarId the rarId
   * @param execution the execution
   * @return the result
   */
  public NodeRef generateIRaR(int rarId, DelegateExecution execution) {
    logger.debug("generateIRaR start");
    NodeRef rootNodeRef = nodeService.getRootNode(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
    List<NodeRef> results =
        searchService.selectNodes(rootNodeRef, irarFolder, null, namespaceService, false);
    if (results.size() == 0) {
      throw new AlfrescoRuntimeException(irarFolder);
    }
    NodeRef irarFolderNodeRef = results.get(0);
    Map<String, Serializable> model = generateIRaRModel(rarId, execution);
    // Process the template
    String irarContentString = templateService.processTemplate("freemarker", irarTemplate, model);
    String paddedRarId = String.format("%05d", rarId);
    String irarNodeName = IRAR_NODE_NAME_PREFIX + paddedRarId + IRAR_NODE_NAME_SUFFIX;
    Map<QName, Serializable> irarProps = new HashMap<QName, Serializable>();
    irarProps.put(ContentModel.PROP_NAME, irarNodeName);
    ChildAssociationRef childAssRef =
        nodeService.createNode(
            irarFolderNodeRef,
            ContentModel.ASSOC_CONTAINS,
            QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, irarNodeName),
            ConservationModel.TYPE_IRAR,
            irarProps);
    NodeRef irarNodeRef = childAssRef.getChildRef();
    ContentWriter irarContentWriter =
        contentService.getWriter(irarNodeRef, ContentModel.PROP_CONTENT, true);
    irarContentWriter.setMimetype(MimetypeMap.MIMETYPE_XML);
    irarContentWriter.setEncoding(StandardCharsets.UTF_8.toString());
    irarContentWriter.putContent(irarContentString);
    logger.debug("nodeService is " + nodeService == null ? "null" : "not null");
    logger.debug("generateIRaR end");
    return irarNodeRef;
  }

  private Map<String, Serializable> generateIRaRModel(int rarId, DelegateExecution execution) {
    Map<String, Serializable> model = new HashMap<String, Serializable>();
    execution.getVariable("");
    model.put("rarID", rarId);
    List<ScriptNode> files = new ArrayList<ScriptNode>();
    ActivitiScriptNode bpmPackage = (ActivitiScriptNode) execution.getVariable("bpm_package");
    logger.debug("bpm_package content:");
    NativeArray children = (NativeArray) bpmPackage.getChildren();
    for (int i = 0; i < (int) (children.getLength()); i++) {
      files.add((ScriptNode) children.get(i, null));
      logger.debug((ScriptNode) children.get(i, null));
    }
    model.put("files", (Serializable) files);
    return model;
  }

  /**
   * Sets the irar template.
   *
   * @param irarTemplate the irarTemplate
   */
  public void setIrarTemplate(String irarTemplate) {
    this.irarTemplate = irarTemplate;
  }

  /**
   * Sets the irar folder.
   *
   * @param irarFolder the irarFolder
   */
  public void setIrarFolder(String irarFolder) {
    this.irarFolder = irarFolder;
  }

  /**
   * Sets the template service.
   *
   * @param templateService the templateService
   */
  public void setTemplateService(TemplateService templateService) {
    this.templateService = templateService;
  }

  /**
   * Sets the node service.
   *
   * @param nodeService the nodeService
   */
  public void setNodeService(NodeService nodeService) {
    this.nodeService = nodeService;
  }

  /**
   * Sets the content service.
   *
   * @param contentService the contentService
   */
  public void setContentService(ContentService contentService) {
    this.contentService = contentService;
  }

  /**
   * Sets the namespace service.
   *
   * @param namespaceService the namespaceService
   */
  public void setNamespaceService(NamespaceService namespaceService) {
    this.namespaceService = namespaceService;
  }

  /**
   * Sets the search service.
   *
   * @param searchService the searchService
   */
  public void setSearchService(SearchService searchService) {
    this.searchService = searchService;
  }

  /**
   * {@inheritDoc}
   *
   * @param execution the execution
   * @throws Exception if an error occurs
   */
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    int rarId = (int) execution.getVariable("vigewf_rarId");
    logger.info("COMPLEX bank IRaRGeneration start for rar " + rarId);
    NodeRef irar = generateIRaR(rarId, execution);
    execution.setVariable("vigewf_relatedIRaR", irar.toString());
    logger.info("COMPLEX Bank IRaRGeneration end for rar " + rarId);
  }
}
