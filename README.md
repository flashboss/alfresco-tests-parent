Tests
=============
A library to test the alfresco components. It offers mock files to execute unit and integration tests in a faster mode, avoiding to start the alfresco server.

This version is for:

- Java 7
- Alfresco SDK 1.1.1
- Alfresco community 4.2.c

To start add the dependency in your project sdk amp:
```
   <dependency>
		<groupId>it.vige</groupId>
		<artifactId>alfresco-tests</artifactId>
		<version>${alfresco.version}..</version>
		<scope>test</scope>
   </dependency>
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
import org.springframework.beans.factory.annotation.Autowired;

import it.vige.sample.BackupAction;

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

You can find a complete sample in https://github.com/flashboss/alfresco-tests-parent/tree/4.2.c.3/alfresco-tests-sample.

Webscript test
===========

Create a junit test in an webscript project after added the alfresco-tests library in the pom:

```
package it.vige.ws.test;

import static org.springframework.extensions.webscripts.Status.STATUS_OK;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import it.vige.ws.PreviousWSSample;
import it.vige.ws.WSSampleModel;
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
		MockWebScriptResponse webScriptResponse = new MockWebScriptResponse();
		previousWSSample.execute(webScriptRequest, webScriptResponse);

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
		Map<String, Object> model = webScriptResponse.getModel();
		Assert.assertEquals("Status ok", STATUS_OK + "", model.get("status").toString());
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
You can find a complete sample in https://github.com/flashboss/alfresco-tests-parent/tree/4.2.c/alfresco-tests-ws-sample.

