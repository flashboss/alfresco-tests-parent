package it.vige.alfresco.complexrar.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.repo.workflow.activiti.BaseJavaDelegate;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;

import it.vige.common.HashModel;

/**
* Mock implementation of the ComplexRaRCheck class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class ComplexRaRCheck extends BaseJavaDelegate {

/**
* The node service.
 */
	private NodeService nodeService;

/**
* Performs execute.
* @param execution the execution
* @throws Exception if an error occurs
 */
	public void execute(DelegateExecution execution) throws Exception {
		// Check duplicates
		List<String> duplicatedPdvs = new ArrayList<String>();
		List<String> unsignedPdvs = new ArrayList<String>();
		Map<String, String> sass = new HashMap<String, String>();
		ActivitiScriptNode bpmPackage = (ActivitiScriptNode) execution.getVariable("bpm_package");
		List<ChildAssociationRef> relatedSaSsChild = nodeService.getChildAssocs(bpmPackage.getNodeRef());
		for (ChildAssociationRef relatedSaSChild : relatedSaSsChild) {
			NodeRef relatedSaSNodeRef = relatedSaSChild.getChildRef();
			if (sass.containsKey(relatedSaSNodeRef.toString())) {
				duplicatedPdvs.add(relatedSaSNodeRef.toString());
			} else {
				String sasHash = (String) nodeService.getProperty(relatedSaSNodeRef, HashModel.PROP_HASH_VALUE);
				if (sass.containsValue(sasHash)) {
					duplicatedPdvs.add(relatedSaSNodeRef.toString());
				} else {
					sass.put(relatedSaSNodeRef.toString(), sasHash);
				}
			}

		}
		List<String> relatedSaSsCleaned = new ArrayList<String>();
		relatedSaSsCleaned.addAll(sass.keySet());
		execution.setVariable("vigewf_relatedSaSsCleaned", relatedSaSsCleaned);
		execution.setVariable("vigewf_reviewDuplicatedSaSError", duplicatedPdvs);
		execution.setVariable("vigewf_reviewUnsignedSaSError", unsignedPdvs);
	}

/**
* Sets the node service.
* @param nodeService the nodeService
 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}