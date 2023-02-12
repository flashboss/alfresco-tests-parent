package it.vige.activiti.test;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.alfresco.mock.test.activiti.ActivitiProcessEngineConfiguration;
import org.alfresco.mock.test.activiti.MockActivitiScriptNode;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNodeList;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.junit.Assert;

import it.vige.activiti.ComplexAbstractForm;
import it.vige.common.SignConstants;

public class WorkflowResubmitTest extends ComplexAbstractForm {

	@Deployment(resources = { "alfresco/module/alfresco-tests-activiti-sample/workflow/ComplexRaRProcess.bpmn" })
	public void testHuman() throws ParseException {
		Map<String, Object> variables = new HashMap<String, Object>();
		init(variables);
		variables.put("vigewf_starterRaR", "Human");
		ActivitiProcessEngineConfiguration activitiProcessEngineConfiguration = (ActivitiProcessEngineConfiguration) processEngineConfiguration;
		ServiceRegistry serviceRegistry = activitiProcessEngineConfiguration.getServiceRegistry();

		// Start process
		variables.put("vigewf_endDateSaS", dateFormat.parse("Mar 16 00:00:00 CET 2020"));
		MockActivitiScriptNode activitiScriptNode = new MockActivitiScriptNode(generationFolder, serviceRegistry);
		ActivitiScriptNodeList activitiScriptNodeList = new ActivitiScriptNodeList();
		activitiScriptNodeList.add(activitiScriptNode);
		variables.put("vigewf_relatedSaSFolder", activitiScriptNodeList);
		variables.put("vigewf_startDateSaS", dateFormat.parse("Mar 14 00:00:00 CET 2018"));
		variables.put("bpm_workflowDescription", "mkkmkmkmk");
		ProcessInstance instance = runtimeService.startProcessInstanceByKey(ACTIVITY_KEY, variables);

		// I click on the completed task
		List<Task> selectedSaS = taskService.createTaskQuery().taskDefinitionKey("selectedSaS")
				.includeProcessVariables().list();
		assertEquals(1, selectedSaS.size());
		Task firstTask = selectedSaS.get(0);
		taskService.complete(firstTask.getId());

		// Execute restart
		List<Task> rarReview = taskService.createTaskQuery().taskDefinitionKey("rarReview").includeProcessVariables()
				.list();
		assertEquals(1, rarReview.size());
		Task secondTask = rarReview.get(0);
		Map<String, String> secondTaskLocalVariables = new HashMap<String, String>();
		secondTaskLocalVariables.put("vigewf_reviewRaR", "Riavvia");
		taskService.setVariablesLocal(secondTask.getId(), secondTaskLocalVariables);
		taskService.complete(secondTask.getId());

		// Execut continue
		rarReview = taskService.createTaskQuery().taskDefinitionKey("rarReview").includeProcessVariables().list();
		assertEquals(1, rarReview.size());
		secondTask = rarReview.get(0);
		secondTaskLocalVariables = new HashMap<String, String>();
		secondTaskLocalVariables.put("vigewf_reviewRaR", "Continua");
		taskService.setVariablesLocal(secondTask.getId(), secondTaskLocalVariables);
		taskService.complete(secondTask.getId());

		// Execute subscription
		List<Task> irarSubscription = taskService.createTaskQuery().taskDefinitionKey("irarSubscription")
				.includeProcessVariables().list();
		assertEquals(1, irarSubscription.size());
		Task thirdTask = irarSubscription.get(0);
		Map<String, String> thirdTaskLocalVariables = new HashMap<String, String>();
		thirdTaskLocalVariables.put("vigewf_namUsername", "prova");
		thirdTaskLocalVariables.put("vigewf_namPassword", "prova");
		taskService.setVariablesLocal(thirdTask.getId(), thirdTaskLocalVariables);
		taskService.complete(thirdTask.getId());

		// Process ended successfully
		instance = runtimeService.createProcessInstanceQuery().active().processInstanceId(instance.getId())
				.singleResult();
		Assert.assertNull(instance);

		SearchService searchService = serviceRegistry.getSearchService();
		NodeService nodeService = serviceRegistry.getNodeService();

		// check that the two files in the new rar folder have been created with the
		// counter updated
		String generationFolderName = (String) nodeService.getProperty(generationFolder, ContentModel.PROP_NAME);
		ResultSet resultQ = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
				SearchService.LANGUAGE_FTS_ALFRESCO, "PATH:\"RAR_00001/sas_complex_" + generationFolderName + ".zip\"");
		NodeRef createdNodeRef = resultQ.getNodeRef(0);
		Assert.assertTrue("Added zip file to new RAR folder",
				nodeService.getPath(createdNodeRef).toString().endsWith(
						"workspace/SpacesStore/company_home/sites/digital-conservation-complex-bank/documentLibrary/rar/RAR_00001/sas_complex_"
								+ generationFolderName + ".zip"));
		resultQ = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_FTS_ALFRESCO,
				"PATH:\"RAR_00001/isas_complex_" + generationFolderName + ".xml\"");
		createdNodeRef = resultQ.getNodeRef(0);
		Assert.assertTrue("Added xml file in new RAR folder",
				nodeService.getPath(createdNodeRef).toString().endsWith(
						"workspace/SpacesStore/company_home/sites/digital-conservation-complex-bank/documentLibrary/rar/RAR_00001/isas_complex_"
								+ generationFolderName + ".xml"));

		// check that there is an empty folder inside sas
		resultQ = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_FTS_ALFRESCO,
				"PATH:\"" + generationFolderName + "\"");
		createdNodeRef = resultQ.getNodeRef(0);
		Assert.assertTrue("Folder inside store",
				nodeService.getPath(createdNodeRef).toString().endsWith(
						"workspace/SpacesStore/company_home/sites/digital-conservation-complex-bank/documentLibrary/sas/"
								+ generationFolderName));
		Assert.assertTrue("Empty folder inside store", nodeService.getChildAssocs(createdNodeRef).isEmpty());

		// I verify that a zip file remains in the activiti folder
		resultQ = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_FTS_ALFRESCO,
				"PATH:\"pkg_919f220e-870a-4c56-ba11-5030ee5325f0/sas_complex_" + generationFolderName + ".zip\"");
		createdNodeRef = resultQ.getNodeRef(0);
		Assert.assertTrue("Zip file inside activiti folder",
				nodeService.getPath(createdNodeRef).toString()
						.endsWith("workspace/SpacesStore/workflow/packages/pkg_919f220e-870a-4c56-ba11-5030ee5325f0/sas_complex_"
								+ generationFolderName + ".zip"));

		// I verify that a signed xml is created in the new rar folder
		resultQ = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_FTS_ALFRESCO,
				"PATH:\"IRaR_00001.xml." + SignConstants.P7M_EXTENSION + "\"");
		createdNodeRef = resultQ.getNodeRef(0);
		Assert.assertTrue("XML signed in the new rar folder", nodeService.getPath(createdNodeRef).toString().endsWith(
				"workspace/SpacesStore/company_home/sites/digital-conservation-complex-bank/documentLibrary/rar/RAR_00001/IRaR_00001.xml."
						+ SignConstants.P7M_EXTENSION));

		end();
	}
}
