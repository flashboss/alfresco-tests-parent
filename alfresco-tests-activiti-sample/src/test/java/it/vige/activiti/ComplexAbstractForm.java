package it.vige.activiti;

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
import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

import it.vige.common.ConservationModel;

/**
 * Abstract base class providing common functionality for tests.
 * 
 * @author vige
 */
public class ComplexAbstractForm extends AbstractActivitiForm {

	public final static String ACTIVITY_KEY = "complexRarGenerationWorkflow";

	public final static String CONTRIBUTORS = "contributors";

	/**
	 * Default admin user to start the scheduler process
	 */
	protected final static String ADMIN_USER_NAME = "kermit";
	/**
	 * Default traveler user to work with the reservations
	 */
	protected final static String USER_NAME = "gonzo";

	/** The date format. */
	protected final DateFormat dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss ZZZ yyyy");

	/** The generation folder. */
	protected NodeRef generationFolder;	/**
	 * Init.
	 *
	 * @param variables the variables
	 */
	@Override
	public void init(Map<String, Object> variables) {
		super.init(variables);
		ActivitiProcessEngineConfiguration activitiProcessEngineConfiguration = (ActivitiProcessEngineConfiguration) processEngineConfiguration;
		NamespaceService namespaceService = activitiProcessEngineConfiguration.getServiceRegistry()
				.getNamespaceService();
		namespaceService.registerNamespace("vigecont", ConservationModel.VIGE_CONSERVATION_URI);

	/** The generation folder name. */
		String generationFolderName = "20191024_154711";
		NodeRef site = insertFolder(sites, "digital-conservation-complex-bank");
		NodeRef documentLibrary = insertFolder(site, "documentLibrary");
		NodeRef sas = insertFolder(documentLibrary, "sas");
		NodeRef rar = insertFolder(documentLibrary, "rar");
		insertFolder(documentLibrary, "irar");
		NodeService nodeService = activitiProcessEngineConfiguration.getServiceRegistry().getNodeService();
		nodeService.setProperty(rar, ConservationModel.PROP_RAR_ID_COUNTER, 0);
		generationFolder = insertFolder(sas, generationFolderName);
		Map<QName, Serializable> properties = new HashMap<QName, Serializable>();
		try {
			properties.put(ContentModel.PROP_NAME, "sas_complex_" + generationFolderName + ".zip");
			properties.put(ContentModel.TYPE_BASE, QName.createQName("vigecont", "complexPdvCons", namespaceService));
			insertZip(generationFolder, "sas_complex_" + generationFolderName + ".zip", "document", "text", properties);
		} catch (IOException e) {
		}
		properties = new HashMap<QName, Serializable>();
		properties.put(ContentModel.PROP_NAME, "isas_complex_" + generationFolderName + ".xml");
		insertDocument(generationFolder, "isas_complex_" + generationFolderName + ".xml", "", properties);
		// AUTHENTICATION
		// Always reset authenticated user to avoid any mistakes
		identityService.setAuthenticatedUserId(USER_NAME);
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
