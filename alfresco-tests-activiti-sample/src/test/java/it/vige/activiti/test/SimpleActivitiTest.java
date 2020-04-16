package it.vige.activiti.test;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;
import org.alfresco.mock.test.activiti.AbstractActivitiForm;
import org.alfresco.mock.test.activiti.ActivitiProcessEngineConfiguration;
import org.alfresco.mock.test.activiti.Initiator;
import org.alfresco.mock.test.activiti.MockActivitiScriptNode;
import org.alfresco.mock.test.script.MockLogger;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.jscript.Search;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNodeList;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.junit.Assert;

import it.vige.activiti.SimpleModel;

public class SimpleActivitiTest extends AbstractActivitiForm {

	public final static String CONTRIBUTORS = "contributors";
	public final static String ACTIVITY_KEY = "generationWorkflow";

	/**
	 * Default admin user to start the scheduler process
	 */
	protected final static String ADMIN_USER_NAME = "kermit";
	/**
	 * Default traveler user to work with the reservations
	 */
	protected final static String USER_NAME = "gonzo";

	protected final DateFormat dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss ZZZ yyyy");

	protected NodeRef generationFolder;

	protected Initiator initiator = new Initiator();

	@Override
	public void init(Map<String, Object> variables) {
		super.init(variables);
		ActivitiProcessEngineConfiguration activitiProcessEngineConfiguration = (ActivitiProcessEngineConfiguration) processEngineConfiguration;
		NamespaceService namespaceService = activitiProcessEngineConfiguration.getServiceRegistry()
				.getNamespaceService();
		namespaceService.registerNamespace("mcccont", SimpleModel.STARTING_URI);
		String generationFolderName = "20191024_154711";
		NodeRef site = insertFolder(sites, "simple-site");
		NodeRef documentLibrary = insertFolder(site, "documentLibrary");
		NodeRef pdv = insertFolder(documentLibrary, "pdv");
		NodeRef pda = insertFolder(documentLibrary, "pda");
		NodeService nodeService = activitiProcessEngineConfiguration.getServiceRegistry().getNodeService();
		nodeService.setProperty(pda, SimpleModel.PROP_PDA_ID_COUNTER, 0);
		generationFolder = insertFolder(pdv, generationFolderName);
		Map<QName, Serializable> properties = new HashMap<QName, Serializable>();
		try {
			properties.put(ContentModel.PROP_NAME, "contracts_" + generationFolderName + ".zip");
			properties.put(ContentModel.TYPE_BASE, QName.createQName("mcccont", "contrattiPdvCons", namespaceService));
			insertZip(generationFolder, "contracts_" + generationFolderName + ".zip", "document", "text",
					properties);
		} catch (IOException e) {
		}
		// AUTHENTICATION
		// Always reset authenticated user to avoid any mistakes
		identityService.setAuthenticatedUserId(USER_NAME);

		Search search = activitiProcessEngineConfiguration.getSearchScript();
		MockLogger logger = activitiProcessEngineConfiguration.getLoggerScript();
		variables.put("initiator", initiator);
		variables.put("search", search);
		variables.put("logger", logger);
		variables.put("mccwf_starterPdA", "Human");
	}

	/**
	 * Create demo users for the application
	 * 
	 * @param identityService The service to create the users
	 */
	@Override
	public void initDemoUsers(IdentityService identityService) {
		createUser(identityService, ADMIN_USER_NAME, "Kermit", "The Frog", ADMIN_USER_NAME,
				ADMIN_USER_NAME + "@activiti.org", null, asList(CONTRIBUTORS, "user", "admin"),
				asList("birthDate", "10-10-1955", "jobTitle", "Muppet", "location", "Hollywoord", "phone", "+123456789",
						"twitterName", "alfresco", "skype", "activiti_" + ADMIN_USER_NAME + "_frog"));
		createUser(identityService, USER_NAME, "Gonzo", "The Great", USER_NAME, USER_NAME + "@activiti.org", null,
				asList(CONTRIBUTORS, "user"), asList("email", "frodobaggins@vige.it"));
		createUser(identityService, "fozzie", "Fozzie", "Bear", "fozzie", "fozzie@activiti.org", null,
				asList(CONTRIBUTORS, "user"), asList("email", "bilbobaggins@vige.it"));
		initiator.getProperties().put("firstName", "Gonzo");
		initiator.getProperties().put("lastName", "The Great");
		initiator.getProperties().put("userName", USER_NAME);
	}

	/**
	 * Create demo groups for teh application
	 * 
	 * @param identityService The service to create the groups
	 */
	@Override
	public void initDemoGroups(IdentityService identityService) {
		String[] assignmentGroups = new String[] { CONTRIBUTORS };
		for (String groupId : assignmentGroups) {
			createGroup(identityService, groupId, "assignment");
		}

		String[] securityGroups = new String[] { "user", "admin" };
		for (String groupId : securityGroups) {
			createGroup(identityService, groupId, "security-role");
		}
	}

	@Deployment(resources = { "alfresco/module/alfresco-tests-activiti-sample/workflow/SimpleProcess.bpmn" })
	public void testWorkflow() throws ParseException {
		Map<String, Object> variables = new HashMap<String, Object>();
		init(variables);
		ActivitiProcessEngineConfiguration activitiProcessEngineConfiguration = (ActivitiProcessEngineConfiguration) processEngineConfiguration;
		ServiceRegistry serviceRegistry = activitiProcessEngineConfiguration.getServiceRegistry();

		// Start process
		variables.put("mccwf_endDatePdV", dateFormat.parse("Mar 16 00:00:00 CET 2020"));
		MockActivitiScriptNode activitiScriptNode = new MockActivitiScriptNode(generationFolder, serviceRegistry);
		ActivitiScriptNodeList activitiScriptNodeList = new ActivitiScriptNodeList();
		activitiScriptNodeList.add(activitiScriptNode);
		variables.put("mccwf_relatedPdVFolder", activitiScriptNodeList);
		variables.put("mccwf_startDatePdV", dateFormat.parse("Mar 14 00:00:00 CET 2018"));
		variables.put("bpm_workflowDescription", "mkkmkmkmk");
		ProcessInstance instance = runtimeService.startProcessInstanceByKey(ACTIVITY_KEY, variables);

		// execute the user task
		List<Task> selectedPdV = taskService.createTaskQuery().taskDefinitionKey("selectedPdV")
				.includeProcessVariables().list();
		assertEquals(1, selectedPdV.size());
		Task firstTask = selectedPdV.get(0);
		taskService.complete(firstTask.getId());

		// process terminated
		instance = runtimeService.createProcessInstanceQuery().active().processInstanceId(instance.getId())
				.singleResult();
		Assert.assertNull(instance);

		// one file is created by the workflow
		SearchService searchService = serviceRegistry.getSearchService();
		ResultSet resultQ = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
				SearchService.LANGUAGE_FTS_ALFRESCO, "PATH:\"pda/contracts_" + generationFolder.getId() + ".zip\"");
		NodeRef createdNodeRef = resultQ.getNodeRef(0);
		Assert.assertTrue("Added a zip file in the PDA folder", createdNodeRef.toString().endsWith(
				"workspace/company_home/sites/simple-site/documentLibrary/pda/contracts_"
						+ generationFolder.getId() + ".zip"));

		// the file is inside the workflow/packages activiti folder
		resultQ = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_FTS_ALFRESCO,
				"PATH:\"pkg_919f220e-870a-4c56-ba11-5030ee5325f0/contracts_" + generationFolder.getId() + ".zip\"");
		createdNodeRef = resultQ.getNodeRef(0);
		Assert.assertTrue("File zip in the activiti folder",
				createdNodeRef.toString()
						.endsWith("workspace/workflow/packages/pkg_919f220e-870a-4c56-ba11-5030ee5325f0/contracts_"
								+ generationFolder.getId() + ".zip"));

		end();
	}
}