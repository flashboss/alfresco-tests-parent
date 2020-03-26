Tests
=============
A library to test the alfresco components. It offer mock file to execute unit and integration tests in a faster mode, avoiding to start the alfresco server.

This version is for:

- Java 7
- ALfresco SDK 1.1.1
- Alfresco enterprise 4.2.6.6

To start add the dependency in your project sdk amp:
```
   <dependency>
		<groupId>it.vige</groupId>
		<artifactId>alfresco-tests</artifactId>
		<version>${alfresco.version}.1</version>
		<scope>test</scope>
   </dependency>
```
create a junit test, for example:

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
		ResultSet docs = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
				SearchService.LANGUAGE_FTS_ALFRESCO, "\"/" + documentName + "\"");
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
		ResultSet docs = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
				SearchService.LANGUAGE_FTS_ALFRESCO, "\"/" + documentName + ".bak\"");
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

You can find a complete sample in https://github.com/flashboss/alfresco-tests-parent/tree/master/alfresco-tests-sample