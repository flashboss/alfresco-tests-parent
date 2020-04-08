package it.vige.activiti;

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

public class Generation extends BaseJavaDelegate {

	private static Log logger = LogFactory.getLog(Generation.class);

	private static String IPDA_NODE_NAME_PREFIX = "IPdA_";
	private static String IPDA_NODE_NAME_SUFFIX = ".xml";

	private TemplateService templateService;
	private NodeService nodeService;
	private ContentService contentService;
	private NamespaceService namespaceService;
	private SearchService searchService;

	private String ipdaTemplate;
	private String ipdaFolder;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		int pdaId = (int) execution.getVariable("mccwf_pdaId");
		logger.info("CONTRATTI banca IPdAGeneration start for pda " + pdaId);
		NodeRef ipda = generateIPdA(pdaId, execution);
		execution.setVariable("mccwf_relatedIPdA", ipda.toString());
		logger.info("CONTRATTI Banca IPdAGeneration end for pda " + pdaId);
	}

	public NodeRef generateIPdA(int pdaId, DelegateExecution execution) {
		logger.debug("generateIPdA start");
		NodeRef rootNodeRef = nodeService.getRootNode(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		List<NodeRef> results = searchService.selectNodes(rootNodeRef, ipdaFolder, null, namespaceService, false);
		if (results.size() == 0) {
			throw new AlfrescoRuntimeException(ipdaFolder);
		}
		NodeRef ipdaFolderNodeRef = results.get(0);
		Map<String, Serializable> model = generateIPdAModel(pdaId, execution);
		// Process the template
		String ipdaContentString = templateService.processTemplate("freemarker", ipdaTemplate, model);
		String paddedPdaId = String.format("%05d", pdaId);
		String ipdaNodeName = IPDA_NODE_NAME_PREFIX + paddedPdaId + IPDA_NODE_NAME_SUFFIX;
		Map<QName, Serializable> ipdaProps = new HashMap<QName, Serializable>();
		ipdaProps.put(ContentModel.PROP_NAME, ipdaNodeName);
		ChildAssociationRef childAssRef = nodeService.createNode(ipdaFolderNodeRef, ContentModel.ASSOC_CONTAINS,
				QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, ipdaNodeName), ConservazioneModel.TYPE_IPDA,
				ipdaProps);
		NodeRef ipdaNodeRef = childAssRef.getChildRef();
		ContentWriter ipdaContentWriter = contentService.getWriter(ipdaNodeRef, ContentModel.PROP_CONTENT, true);
		ipdaContentWriter.setMimetype(MimetypeMap.MIMETYPE_XML);
		ipdaContentWriter.setEncoding(StandardCharsets.UTF_8.toString());
		ipdaContentWriter.putContent(ipdaContentString);
		logger.debug("nodeService is " + nodeService == null ? "null" : "not null");
		logger.debug("generateIPdA end");
		return ipdaNodeRef;
	}

	private Map<String, Serializable> generateIPdAModel(int pdaId, DelegateExecution execution) {
		Map<String, Serializable> model = new HashMap<String, Serializable>();
		execution.getVariable("");
		model.put("pdaID", pdaId);
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

	public void setIpdaTemplate(String ipdaTemplate) {
		this.ipdaTemplate = ipdaTemplate;
	}

	public void setIpdaFolder(String ipdaFolder) {
		this.ipdaFolder = ipdaFolder;
	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
}