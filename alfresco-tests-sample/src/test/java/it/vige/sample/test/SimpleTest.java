package it.vige.sample.test;

import static org.alfresco.model.ContentModel.PROP_DESCRIPTION;
import static org.alfresco.model.ContentModel.PROP_NAME;
import static org.alfresco.model.ContentModel.PROP_NODE_UUID;
import static org.alfresco.model.ContentModel.PROP_VERSION_LABEL;
import static org.alfresco.model.ContentModel.PROP_VERSION_TYPE;
import static org.alfresco.service.cmr.repository.StoreRef.STORE_REF_WORKSPACE_SPACESSTORE;
import static org.alfresco.service.cmr.version.VersionType.MAJOR;
import static org.alfresco.service.cmr.version.VersionType.MINOR;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.mock.test.AbstractForm;
import org.alfresco.repo.action.ActionImpl;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionHistory;
import org.alfresco.service.namespace.QName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import it.vige.sample.BackupAction;

/**
 * Test class for verifying functionality.
 * 
 * @author vige
 */
public class SimpleTest extends AbstractForm {

	@Autowired
	/** The my action. */
	private BackupAction myAction;

	private final static String documentName = "VALID.pdf";

	private final static String versionName = "firstVersion.pdf";
	private final static String versionContent = "new content";
	private final static String versionLabel = "1.0";

	private final static String version2Name = "secondVersion.pdf";
	private final static String version2Content = "new content 2";
	private final static String version2Label = "1.1";

	private final static String version3Name = "thirdVersion.pdf";
	private final static String version3Content = "new content 3";
	private final static String version3Label = "2.0";

	/** The document. */
	private NodeRef document;

	@Before
	/**
	 * Init.
	 *
	 */
	public void init() {
		super.init();

		// insert a document
		Map<QName, Serializable> properties = new HashMap<QName, Serializable>();
		properties.put(PROP_NAME, documentName);
		properties.put(PROP_DESCRIPTION, documentName);

	/** The content. */
		String content = new String(com.adobe.xmp.impl.Base64.encode(documentName));
		document = insertDocument(spacesStore, documentName, content, properties);

		// verify the document is created
		ResultSet docs = serviceRegistry.getSearchService().query(STORE_REF_WORKSPACE_SPACESSTORE,
				SearchService.LANGUAGE_FTS_ALFRESCO, "PATH:\"/" + documentName + "\"");
		Assert.assertEquals("A document is created", 1, docs.length());

	/** The node name. */
		String nodeName = (String) serviceRegistry.getNodeService().getProperty(docs.getNodeRefs().get(0), PROP_NAME);
		Assert.assertEquals("VALID.pdf is created", documentName, nodeName);
	}

	@Test
	/**
	 * Execute.
	 *
	 */
	public void execute() {

		// execute the injected action
		Map<String, Serializable> parameterValues = new HashMap<String, Serializable>();
		parameterValues.put(BackupAction.DOCUMENT_NAME, documentName);
		Action action = new ActionImpl(null, null, null, parameterValues);
		myAction.executeImpl(action, spacesStore);

		// verify the document is created
		ResultSet docs = serviceRegistry.getSearchService().query(STORE_REF_WORKSPACE_SPACESSTORE,
				SearchService.LANGUAGE_FTS_ALFRESCO, "PATH:\"/" + documentName + ".bak\"");
		Assert.assertEquals("A backup document is created", 1, docs.length());

	/** The node name. */
		String nodeName = (String) serviceRegistry.getNodeService().getProperty(docs.getNodeRefs().get(0), PROP_NAME);
		Assert.assertEquals("VALID.pdf.bak is created", documentName + ".bak", nodeName);

	}

	@Test
	/**
	 * Versioned.
	 *
	 */
	public void versioned() {
		insertVersion(document, versionName, versionContent, versionLabel, MINOR);
		insertVersion(document, version2Name, version2Content, version2Label, MINOR);
		insertVersion(document, version3Name, version3Content, version3Label, MAJOR);

		VersionHistory versionHistory = serviceRegistry.getVersionService()
				.getVersionHistory(new NodeRef("workspace://SpacesStore/unexistentNode"));
		Assert.assertNull("There is no version history", versionHistory);
		versionHistory = serviceRegistry.getVersionService().getVersionHistory(document);
		Assert.assertNotNull("There one version history", versionHistory);
		Collection<Version> allVersions = versionHistory.getAllVersions();
		Assert.assertEquals("three versions are inserted", 3, allVersions.size());
		NodeService nodeService = serviceRegistry.getNodeService();

		Version version = allVersions.toArray(new Version[0])[0];
		Map<QName, Serializable> properties = nodeService.getProperties(version.getVersionedNodeRef());
		Assert.assertEquals("prop name", documentName, properties.get(PROP_NAME));
		Assert.assertEquals("prop node uuid", document.getId(), properties.get(PROP_NODE_UUID));
		Assert.assertEquals("prop version label", version3Label, properties.get(PROP_VERSION_LABEL));
		Assert.assertEquals("prop version type", MAJOR.name(), properties.get(PROP_VERSION_TYPE));
		Assert.assertEquals("prop description", documentName, properties.get(PROP_DESCRIPTION));
		Map<QName, Serializable> propertiesFrozen = nodeService.getProperties(version.getFrozenStateNodeRef());
		Assert.assertEquals("prop name", version3Name, propertiesFrozen.get(PROP_NAME));
		Assert.assertEquals("prop version label", version3Label, propertiesFrozen.get(PROP_VERSION_LABEL));
		Assert.assertEquals("prop version type", MAJOR.name(), propertiesFrozen.get(PROP_VERSION_TYPE));
		Assert.assertNull("prop description", propertiesFrozen.get(PROP_DESCRIPTION));

		Version version2 = allVersions.toArray(new Version[0])[1];
		Map<QName, Serializable> properties2 = nodeService.getProperties(version2.getVersionedNodeRef());
		Map<QName, Serializable> propertiesFrozen2 = nodeService.getProperties(version2.getFrozenStateNodeRef());
		Assert.assertEquals("versioned node ref is the same", properties, properties2);
		Assert.assertEquals("prop name", version2Name, propertiesFrozen2.get(PROP_NAME));
		Assert.assertEquals("prop version label", version2Label, propertiesFrozen2.get(PROP_VERSION_LABEL));
		Assert.assertEquals("prop version type", MINOR.name(), propertiesFrozen2.get(PROP_VERSION_TYPE));
		Assert.assertNull("prop description", propertiesFrozen2.get(PROP_DESCRIPTION));

		Version version3 = allVersions.toArray(new Version[0])[2];
		Map<QName, Serializable> properties3 = nodeService.getProperties(version3.getVersionedNodeRef());
		Map<QName, Serializable> propertiesFrozen3 = nodeService.getProperties(version3.getFrozenStateNodeRef());
		Assert.assertEquals("versioned node ref is the same", properties, properties3);
		Assert.assertEquals("prop name", versionName, propertiesFrozen3.get(PROP_NAME));
		Assert.assertEquals("prop version label", versionLabel, propertiesFrozen3.get(PROP_VERSION_LABEL));
		Assert.assertEquals("prop version type", MINOR.name(), propertiesFrozen3.get(PROP_VERSION_TYPE));
		Assert.assertNull("prop description", propertiesFrozen3.get(PROP_DESCRIPTION));

	}
}
