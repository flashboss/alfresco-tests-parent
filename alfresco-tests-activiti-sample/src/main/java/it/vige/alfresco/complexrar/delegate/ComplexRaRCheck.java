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
 * Activiti delegate for checking RaR documents.
 * Validates documents for duplicates and unsigned files.
 * 
 * @author vige
 */
public class ComplexRaRCheck extends BaseJavaDelegate {

	/** Service for node operations. */
	private NodeService nodeService;

	/**
	 * Executes the RaR check delegate.
	 * Checks for duplicate and unsigned documents in the workflow package.
	 * 
	 * {@inheritDoc}
	 * 
	 * @param execution the Activiti delegate execution context
	 * @throws Exception if check fails
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
	 * 
	 * @param nodeService the node service to use
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}