package it.vige.alfresco.complexrar.delegate;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.error.AlfrescoRuntimeException;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.repo.workflow.activiti.BaseJavaDelegate;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.vige.alfresco.complexrar.util.ComplexHashUtil;
import it.vige.common.ConservationModel;

/**
 * ComplexRaRGeneration implementation for testing purposes.
 *
 * @author vige
 */
public class ComplexRaRGeneration extends BaseJavaDelegate {

	private static Log logger = LogFactory.getLog(ComplexRaRGeneration.class);

	private ComplexHashUtil complexHashUtil;

	protected String rarFolder;
	protected NodeService nodeService;
	protected SearchService searchService;
	protected NamespaceService namespaceService;

	public void setRarFolder(String rarFolder) {
		this.rarFolder = rarFolder;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	public void execute(DelegateExecution execution) throws Exception {
		logger.debug("RaRGeneration start");
		NodeRef rootNodeRef = nodeService.getRootNode(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		List<NodeRef> results = searchService.selectNodes(rootNodeRef, rarFolder, null, namespaceService, false);
		if (results.size() == 0) {
			throw new AlfrescoRuntimeException(rarFolder);
		}
		NodeRef irarFolderNodeRef = results.get(0);
		logger.debug("RaRGeneration nodo trovato:" + irarFolderNodeRef.getId());
		int rarCounter = (int) nodeService.getProperty(irarFolderNodeRef, ConservationModel.PROP_RAR_ID_COUNTER);
		nodeService.setProperty(irarFolderNodeRef, ConservationModel.PROP_RAR_ID_COUNTER, rarCounter + 1);
		ActivitiScriptNode bpmPackage = (ActivitiScriptNode) execution.getVariable("bpm_package");
		List<ChildAssociationRef> relatedSaSsChild = nodeService.getChildAssocs(bpmPackage.getNodeRef());
		for (ChildAssociationRef relatedSaSChild : relatedSaSsChild) {
			complexHashUtil.setHash(relatedSaSChild.getChildRef());
		}
		execution.setVariable("vigewf_rarId", rarCounter);
	}

	public ComplexHashUtil getComplexHashUtil() {
		return complexHashUtil;
	}

	public void setComplexHashUtil(ComplexHashUtil complexHashUtil) {
		this.complexHashUtil = complexHashUtil;
	}

}