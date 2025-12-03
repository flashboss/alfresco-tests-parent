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
 * @author vige
 */
public class Generation extends BaseJavaDelegate {

	private static Log logger = LogFactory.getLog(Generation.class);

	private String rarFolder;
	private NodeService nodeService;
	private SearchService searchService;
	private NamespaceService namespaceService;
	private FileFolderService fileFolderService;

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

	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}
}