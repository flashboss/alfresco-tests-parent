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
 * Activiti workflow delegate for document generation.
 * Copies documents from the workflow package to the RaR folder
 * and increments the RaR ID counter.
 * 
 * @author vige
 */
public class Generation extends BaseJavaDelegate {

	/** Logger for this class. */
	private static Log logger = LogFactory.getLog(Generation.class);

	/** XPath to the RaR folder configured via Spring. */
	private String rarFolder;

	/** Service for node operations. */
	private NodeService nodeService;

	/** Service for search operations. */
	private SearchService searchService;

	/** Service for namespace resolution. */
	private NamespaceService namespaceService;

	/** Service for file and folder operations. */
	private FileFolderService fileFolderService;

	/**
	 * Executes the generation delegate.
	 * Finds the RaR folder, increments the counter, and copies
	 * documents from the workflow package to the RaR folder.
	 * 
	 * @param execution the Activiti delegate execution context
	 * @throws Exception if the RaR folder is not found or copy fails
	 */
	public void execute(DelegateExecution execution) throws Exception {
		logger.debug("Generation start");
		NodeRef rootNodeRef = nodeService.getRootNode(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
		List<NodeRef> results = searchService.selectNodes(rootNodeRef, rarFolder, null, namespaceService, false);
		if (results.size() == 0) {
			throw new AlfrescoRuntimeException(rarFolder);
		}
		NodeRef irarFolderNodeRef = results.get(0);
		logger.debug("Node found:" + irarFolderNodeRef.getId());
		int rarCounter = (int) nodeService.getProperty(irarFolderNodeRef, SimpleModel.PROP_RAR_ID_COUNTER);
		nodeService.setProperty(irarFolderNodeRef, SimpleModel.PROP_RAR_ID_COUNTER, rarCounter + 1);
		ActivitiScriptNode bpmPackage = (ActivitiScriptNode) execution.getVariable("bpm_package");
		List<ChildAssociationRef> relatedSaSsChild = nodeService.getChildAssocs(bpmPackage.getNodeRef());
		for (ChildAssociationRef relatedSaSChild : relatedSaSsChild) {
			FileInfo fileInfo = fileFolderService.copy(relatedSaSChild.getChildRef(), irarFolderNodeRef, null);
			logger.info(fileInfo);
		}
		execution.setVariable("mywf_rarId", rarCounter);
	}

	/**
	 * Sets the XPath to the RaR folder.
	 * 
	 * @param rarFolder the XPath expression to locate the RaR folder
	 */
	public void setRarFolder(String rarFolder) {
		this.rarFolder = rarFolder;
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
	 * Sets the search service.
	 * 
	 * @param searchService the search service to use
	 */
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
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
	 * Sets the file folder service.
	 * 
	 * @param fileFolderService the file folder service to use
	 */
	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}
}