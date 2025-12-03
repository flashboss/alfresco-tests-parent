package it.vige.alfresco.complexrar.delegate;

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

import it.vige.common.ConservationModel;

/**
 * Activiti delegate for generating IRaR (Index of RaR) documents.
 * Creates an XML document containing metadata about the RaR archive.
 * 
 * @author vige
 */
public class ComplexIRaRGeneration extends BaseJavaDelegate {

	/** Logger for this class. */
	private static Log logger = LogFactory.getLog(ComplexIRaRGeneration.class);

	/** Prefix for IRaR node names. */
	private static String IRAR_NODE_NAME_PREFIX = "IRaR_";

	/** Suffix for IRaR node names. */
	private static String IRAR_NODE_NAME_SUFFIX = ".xml";

	/** Service for template processing. */
	private TemplateService templateService;

	/** Service for node operations. */
	private NodeService nodeService;

	/** Service for content operations. */
	private ContentService contentService;

	/** Service for namespace resolution. */
	private NamespaceService namespaceService;

	/** Service for search operations. */
	private SearchService searchService;

	/** XPath to the IRaR template. */
	private String irarTemplate;

	/** XPath to the IRaR folder. */
	private String irarFolder;

	/**
	 * Generates an IRaR document for the given RaR ID.
	 * 
	 * @param rarId the RaR identifier
	 * @param execution the Activiti delegate execution context
	 * @return the node reference of the created IRaR document
	 */
	public NodeRef generateIRaR(int rarId, DelegateExecution execution) {
		logger.debug("generateIRaR start");
		NodeRef rootNodeRef = nodeService.getRootNode(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		List<NodeRef> results = searchService.selectNodes(rootNodeRef, irarFolder, null, namespaceService, false);
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
		ChildAssociationRef childAssRef = nodeService.createNode(irarFolderNodeRef, ContentModel.ASSOC_CONTAINS,
				QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, irarNodeName), ConservationModel.TYPE_IRAR,
				irarProps);
		NodeRef irarNodeRef = childAssRef.getChildRef();
		ContentWriter irarContentWriter = contentService.getWriter(irarNodeRef, ContentModel.PROP_CONTENT, true);
		irarContentWriter.setMimetype(MimetypeMap.MIMETYPE_XML);
		irarContentWriter.setEncoding(StandardCharsets.UTF_8.toString());
		irarContentWriter.putContent(irarContentString);
		logger.debug("nodeService is " + nodeService == null ? "null" : "not null");
		logger.debug("generateIRaR end");
		return irarNodeRef;
	}

	/**
	 * Generates the model for the IRaR template.
	 * 
	 * @param rarId the RaR identifier
	 * @param execution the Activiti delegate execution context
	 * @return the model map for template processing
	 */
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
	 * Sets the IRaR template path.
	 * 
	 * @param irarTemplate the XPath to the IRaR template
	 */
	public void setIrarTemplate(String irarTemplate) {
		this.irarTemplate = irarTemplate;
	}

	/**
	 * Sets the IRaR folder path.
	 * 
	 * @param irarFolder the XPath to the IRaR folder
	 */
	public void setIrarFolder(String irarFolder) {
		this.irarFolder = irarFolder;
	}

	/**
	 * Sets the template service.
	 * 
	 * @param templateService the template service to use
	 */
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	/**
	 * Sets the node service.
	 * 
	 * @param nodeService the node service to use
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * Sets the content service.
	 * 
	 * @param contentService the content service to use
	 */
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	/**
	 * Sets the namespace service.
	 * 
	 * @param namespaceService the namespace service to use
	 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	/**
	 * Sets the search service.
	 * 
	 * @param searchService the search service to use
	 */
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	/**
	 * Executes the IRaR generation delegate.
	 * Generates an IRaR document and stores the reference in the workflow.
	 * 
	 * {@inheritDoc}
	 * 
	 * @param execution the Activiti delegate execution context
	 * @throws Exception if IRaR generation fails
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