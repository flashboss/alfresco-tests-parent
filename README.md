Tests
=============
A library to test the alfresco components. It offers mock files to execute unit and integration tests in a faster mode, avoiding to start the alfresco server.

This version is for:

- Java 8
- Alfresco SDK 3.1.0
- Alfresco community 5.2.f

To start add the dependency in your project sdk amp:
```
   <dependency>
		<groupId>it.vige</groupId>
		<artifactId>alfresco-tests</artifactId>
		<version>${alfresco.version}.22</version>
		<scope>test</scope>
   </dependency>
```

In the same pom add the test resources under the 'build' tag after the 'resources' tag:
```
   <testResources>
               <!-- Filter the test resource files in this project and do property substitutions -->
               <testResource>
                   <directory>src/test/resources</directory>
                   <filtering>true</filtering>
               </testResource>
   </testResources>
```

Simple test
===========

Create a junit test, for example:

```
package it.vige.sample.test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.mock.test.AbstractForm;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.ActionImpl;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.QName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tradeshift.test.remote.Remote;
import com.tradeshift.test.remote.RemoteTestRunner;

import it.vige.sample.BackupAction;

@RunWith(RemoteTestRunner.class)
@Remote(runnerClass = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-module-context.xml")
public class SimpleTest extends AbstractForm {

	@Autowired
	private BackupAction myAction;

	private String documentName = "VALID.pdf";

	@Before
	public void init() {
		super.init();

		// insert a document
		Map<QName, Serializable> properties = new HashMap<QName, Serializable>();
		properties.put(ContentModel.PROP_NAME, documentName);
		properties.put(ContentModel.PROP_DESCRIPTION, documentName);
		String content = new String(com.adobe.xmp.impl.Base64.encode(documentName));
		insertDocument(workspace, documentName, content, properties);

		// verify the document is created
		ResultSet docs = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
				SearchService.LANGUAGE_FTS_ALFRESCO, "PATH:\"/" + documentName + "\"");
		Assert.assertEquals("A document is created", 1, docs.length());
		Assert.assertTrue("VALID.pdf is created", docs.getNodeRefs().get(0).getId().equals(documentName));
	}

	@Test
	public void execute() {

		// execute the injected action
		Map<String, Serializable> parameterValues = new HashMap<String, Serializable>();
		parameterValues.put(BackupAction.DOCUMENT_NAME, documentName);
		Action action = new ActionImpl(null, null, null, parameterValues);
		myAction.executeImpl(action, workspace);

		// verify the document is created
		ResultSet docs = serviceRegistry.getSearchService().query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
				SearchService.LANGUAGE_FTS_ALFRESCO, "PATH:\"/" + documentName + ".bak\"");
		Assert.assertEquals("A backup document is created", 1, docs.length());
		Assert.assertTrue("VALID.pdf.bak is created", docs.getNodeRefs().get(0).getId().equals(documentName + ".bak"));

	}
}
```

This sample test execute an alfresco action and tests the result. Here the sample action:

```
package it.vige.sample;

import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.springframework.beans.factory.annotation.Autowired;

public class BackupAction extends ActionExecuterAbstractBase {

	public static String DOCUMENT_NAME = "documentName";

	@Autowired
	private FileFolderService fileFolderService;

	@Override
	public void executeImpl(Action action, NodeRef actionedUponNodeRef) {
		String documentName = (String) action.getParameterValue(DOCUMENT_NAME);
		fileFolderService.create(actionedUponNodeRef, documentName + ".bak", ContentModel.TYPE_CONTENT);

	}

	@Override
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
		// TODO Auto-generated method stub

	}

}

```
Here the configuration of the action:

src -> main -> amp -> config -> alfresco -> module -> alfresco-tests-sample -> module-context.xml

```
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans.dtd'>

<beans>
	<import resource="classpath:alfresco/module/${project.artifactId}/context/service-context.xml" />
</beans>
```

and:

src -> main -> amp -> config -> alfresco -> module -> alfresco-tests-sample -> context -> service-context.xml

```
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans.dtd'>

<beans>
	<bean id="backupAction" class="it.vige.sample.BackupAction"
		parent="action-executer">
		<property name="extension">
			<value>${backup.extension}</value>
		</property>
	</bean>
</beans>
```

Before to start your test remember to add a alfresco-global.properties with the variables of the action to test:

src -> test -> resources -> alfresco -> module -> alfresco-tests-sample -> alfresco-global.properties

```
backup.extension=bak
```

and the spring descriptor to connect to the action:

src -> test -> resources -> alfresco -> module -> test-module-context.xml

```
<!DOCTYPE beans PUBLIC '-//SPRING//DTD BEAN//EN' 'http://www.springframework.org/dtd/spring-beans.dtd'>

<beans>
	<import resource="classpath:test-context.xml" />
	<import
		resource="classpath:alfresco/module/${project.artifactId}/context/service-context.xml" />
</beans>
```

You can find a complete sample in https://github.com/flashboss/alfresco-tests-parent/tree/5.2.f/alfresco-tests-sample.

Activiti test
===========

Create a junit test in an activiti project after added the alfresco-tests library in the pom:

```
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
```
In your src/test/resources folder add the follow spring descriptor file test-module-context.xml:

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	<import resource="classpath:test-activiti-context.xml" />
	<import resource="classpath:alfresco/module/${project.artifactId}/module-context.xml" />
</beans>
```
You can find a complete sample in https://github.com/flashboss/alfresco-tests-parent/tree/5.2.f/alfresco-tests-activiti-sample.

Webscript test
===========

Create a junit test in an webscript project after added the alfresco-tests library in the pom:

```
package it.vige.ws.test;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.mock.test.ws.AbstractWSForm;
import org.alfresco.mock.test.ws.MockWebScriptRequest;
import org.alfresco.mock.test.ws.MockWebScriptResponse;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tradeshift.test.remote.Remote;
import com.tradeshift.test.remote.RemoteTestRunner;

import it.vige.ws.PreviousWSSample;
import it.vige.ws.WSSampleModel;

@RunWith(RemoteTestRunner.class)
@Remote(runnerClass = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-module-context.xml")
public class PreviousWSSampleTest extends AbstractWSForm {

	private final static String CARTELLA_WSSAMPLE = "WSSAMPLE-20157726";
	private final static String dataModifica = "2020-06-19";

	@Autowired
	private PreviousWSSample previousWSSample;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private NodeRef repository;

	@Before
	public void init() {
		super.init();
		NamespaceService namespaceService = serviceRegistry.getNamespaceService();
		namespaceService.registerNamespace("mccpb", WSSampleModel.PBPDV_NAMESPACE);

		// Creating initial folders and sites
		NodeRef site = insertFolder(sites, "digital-conservation");
		insertFolder(site, "documentLibrary");

		NodeRef bankSite = insertFolder(sites, "bank-site");
		NodeRef bankSiteDL = insertFolder(bankSite, "documentLibrary");
		repository = insertFolder(bankSiteDL, "repository");
		insertFolder(repository, CARTELLA_WSSAMPLE);
	}

	@Override
	protected AbstractWebScript getAbstractWebScript() {
		return previousWSSample;
	}

	@Test
	public void execute() throws ParseException, IOException {

		SearchService searchService = serviceRegistry.getSearchService();
		NodeService nodeService = serviceRegistry.getNodeService();
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		{
			fields.put("date_modified", dataModifica);
			fields.put("date_ws_end", dataModifica);
			fields.put("codicews", CARTELLA_WSSAMPLE);
			fields.put("date_ws_start", "1970-01-01");
		}
		WebScriptRequest webScriptRequest = new MockWebScriptRequest("json", null, previousWSSample, fields,
				serviceRegistry);
		previousWSSample.execute(webScriptRequest, new MockWebScriptResponse());

		// Verify
		List<NodeRef> nodeRefs = searchService
				.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_XPATH, CARTELLA_WSSAMPLE)
				.getNodeRefs();
		NodeRef result = nodeRefs.get(0);
		Set<QName> aspects = nodeService.getAspects(result);
		Assert.assertEquals("One aspect for the folder", 1, aspects.size());
		QName aspect = aspects.iterator().next();
		Assert.assertEquals("Added an aspect to the WS Sample folder", WSSampleModel.ASPECT_WSSAMPLEFOLDER, aspect);
		Date dataCedacri = (Date) nodeService.getProperty(result, WSSampleModel.PROP_UPDATE_PROPERTY);
		Assert.assertEquals("Added the date property", dataModifica, dateFormat.format(dataCedacri));
	}

}
```
In your src/test/resources folder add the follow spring descriptor file test-module-context.xml:

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	<import resource="classpath:test-ws-context.xml" />
	<import
		resource="classpath:alfresco/module/${project.artifactId}/context/webscript-context.xml" />
</beans>
```
You can find a complete sample in https://github.com/flashboss/alfresco-tests-parent/tree/5.2.f/alfresco-tests-ws-sample.
