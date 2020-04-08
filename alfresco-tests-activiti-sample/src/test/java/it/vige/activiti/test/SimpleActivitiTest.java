package it.vige.activiti.test;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.alfresco.mock.test.activiti.AbstractActivitiForm;
import org.alfresco.mock.test.activiti.ActivitiProcessEngineConfiguration;
import org.alfresco.mock.test.activiti.Initiator;
import org.alfresco.mock.test.script.MockLogger;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.jscript.Search;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

public class SimpleActivitiTest extends AbstractActivitiForm {

	public final static String CONTRIBUTORS = "contributors";

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
		new SignIntegrationFactory().setSignIntegration(null);
		ActivitiProcessEngineConfiguration activitiProcessEngineConfiguration = (ActivitiProcessEngineConfiguration) processEngineConfiguration;
		NamespaceService namespaceService = activitiProcessEngineConfiguration.getServiceRegistry()
				.getNamespaceService();
		namespaceService.registerNamespace("mcccont", ConservazioneModel.MCC_CONSERVAZIONE_URI);
		String generationFolderName = "20191024_154711";
		NodeRef site = insertFolder(sites, "conservazione-digitale-contratti-banca");
		NodeRef documentLibrary = insertFolder(site, "documentLibrary");
		NodeRef pdv = insertFolder(documentLibrary, "pdv");
		NodeRef pda = insertFolder(documentLibrary, "pda");
		insertFolder(documentLibrary, "ipda");
		NodeService nodeService = activitiProcessEngineConfiguration.getServiceRegistry().getNodeService();
		nodeService.setProperty(pda, ConservazioneModel.PROP_PDA_ID_COUNTER, 0);
		generationFolder = insertFolder(pdv, generationFolderName);
		Map<QName, Serializable> properties = new HashMap<QName, Serializable>();
		try {
			properties.put(ContentModel.PROP_NAME, "pdv_contratti_" + generationFolderName + ".zip");
			properties.put(ContentModel.TYPE_BASE, QName.createQName("mcccont", "contrattiPdvCons", namespaceService));
			insertZip(generationFolder, "pdv_contratti_" + generationFolderName + ".zip", "document", "text", properties);
		} catch (IOException e) {
		}
		properties = new HashMap<QName, Serializable>();
		properties.put(ContentModel.PROP_NAME, "ipdv_contratti_" + generationFolderName + ".xml");
		insertDocument(generationFolder, "ipdv_contratti_" + generationFolderName + ".xml", "", properties);
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
}