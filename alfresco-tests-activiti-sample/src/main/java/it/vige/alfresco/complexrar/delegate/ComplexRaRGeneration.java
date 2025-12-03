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
* Mock implementation of the ComplexRaRGeneration class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class ComplexRaRGeneration extends BaseJavaDelegate {

	private static Log logger = LogFactory.getLog(ComplexRaRGeneration.class);

/**
* The complex hash util.
 */
	private ComplexHashUtil complexHashUtil;

/**
* The rar folder.
 */
	protected String rarFolder;
/**
* The node service.
 */
	protected NodeService nodeService;
/**
* The search service.
 */
	protected SearchService searchService;
/**
* The namespace service.
 */
	protected NamespaceService namespaceService;

/**
* Sets the rar folder.
* @param rarFolder the rarFolder
 */
	public void setRarFolder(String rarFolder) {
		this.rarFolder = rarFolder;
	}

/**
* Sets the node service.
* @param nodeService the nodeService
 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

/**
* Sets the search service.
* @param searchService the searchService
 */
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

/**
* Sets the namespace service.
* @param namespaceService the namespaceService
 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

/**
* Performs execute.
* @param execution the execution
* @throws Exception if an error occurs
 */
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

/**
* Gets the complex hash util.
* @return the result
 */
	public ComplexHashUtil getComplexHashUtil() {
		return complexHashUtil;
	}

/**
* Sets the complex hash util.
* @param complexHashUtil the complexHashUtil
 */
	public void setComplexHashUtil(ComplexHashUtil complexHashUtil) {
		this.complexHashUtil = complexHashUtil;
	}

}