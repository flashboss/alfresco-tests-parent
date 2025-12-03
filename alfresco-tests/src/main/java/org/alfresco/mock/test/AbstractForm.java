package org.alfresco.mock.test;

import static org.alfresco.service.cmr.repository.StoreRef.PROTOCOL_ARCHIVE;
import static org.alfresco.service.cmr.repository.StoreRef.PROTOCOL_WORKSPACE;
import static org.alfresco.service.cmr.repository.StoreRef.STORE_REF_ARCHIVE_SPACESSTORE;
import static org.alfresco.service.cmr.repository.StoreRef.STORE_REF_WORKSPACE_SPACESSTORE;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.alfresco.mock.ClasspathTestRunner;
import org.alfresco.mock.NodeUtils;
import org.alfresco.mock.ZipUtils;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.site.SiteModel;
import org.alfresco.repo.version.Version2Model;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.version.VersionType;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.tradeshift.test.remote.Remote;
import com.tradeshift.test.remote.RemoteTestRunner;

/**
 * Abstract base class for Alfresco test forms.
 * Provides common setup and utility methods for testing Alfresco components.
 *
 * @author vige
 */
@RunWith(RemoteTestRunner.class)
@Remote(runnerClass = ClasspathTestRunner.class)
@ContextConfiguration("classpath:test-module-context.xml")
public abstract class AbstractForm {

	/** The service registry. */
	@Autowired
	protected ServiceRegistry serviceRegistry;

	/** The spaces store. */
	protected NodeRef spacesStore;
	/** The archive. */
	protected NodeRef archive;
	/** The sites. */
	protected NodeRef sites;
	/** The shared. */
	protected NodeRef shared;
	/** The today. */
	protected Date today;
	/** The today str. */
	protected String todayStr;
	/** The company home. */
	protected NodeRef companyHome;

	/**
	 * Initializes the test environment with default folders and namespaces.
	 */
	public void init() {

		NamespaceService namespaceService = serviceRegistry.getNamespaceService();
		namespaceService.registerNamespace(NamespaceService.APP_MODEL_PREFIX, NamespaceService.APP_MODEL_1_0_URI);
		namespaceService.registerNamespace(SiteModel.SITE_MODEL_PREFIX, SiteModel.SITE_MODEL_URL);
		namespaceService.registerNamespace(NamespaceService.CONTENT_MODEL_PREFIX,
				NamespaceService.CONTENT_MODEL_1_0_URI);
		namespaceService.registerNamespace(NamespaceService.SYSTEM_MODEL_PREFIX, NamespaceService.SYSTEM_MODEL_1_0_URI);

		MockNodeService nodeService = (MockNodeService) serviceRegistry.getNodeService();
		MockVersionService versionService = (MockVersionService) serviceRegistry.getVersionService();
		nodeService.init();
		versionService.init();
		try {
			FileUtils.deleteDirectory(new File(MockContentService.FOLDER_TEST + PROTOCOL_WORKSPACE));
			FileUtils.deleteDirectory(new File(MockContentService.FOLDER_TEST + PROTOCOL_ARCHIVE));
			FileUtils.deleteDirectory(ZipUtils.TEMP_DIR);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// creo le directory iniziali per i pdv, gli rdv e il repository
		NodeRef root = insertFolder(new NodeRef(new StoreRef("", ""), ""), ".");
		NodeRef workspaceRoot = insertFolder(root, PROTOCOL_WORKSPACE);
		spacesStore = insertFolder(workspaceRoot, NamespaceService.APP_MODEL_PREFIX,
				STORE_REF_WORKSPACE_SPACESSTORE.getIdentifier());
		insertFolder(workspaceRoot, NamespaceService.APP_MODEL_PREFIX, Version2Model.STORE_ID);
		companyHome = insertFolder(spacesStore, NamespaceService.APP_MODEL_PREFIX, "company_home");
		NodeRef system = insertFolder(spacesStore, NamespaceService.SYSTEM_MODEL_PREFIX, "system");
		NodeRef archiveRoot = insertFolder(root, PROTOCOL_ARCHIVE);
		archive = insertFolder(archiveRoot, STORE_REF_ARCHIVE_SPACESSTORE.getIdentifier());
		sites = insertFolder(companyHome, SiteModel.SITE_MODEL_PREFIX, SiteModel.TYPE_SITES.getLocalName());
		shared = insertFolder(companyHome, NamespaceService.APP_MODEL_PREFIX, "shared");
		insertFolder(system, SiteModel.SITE_MODEL_PREFIX, "authorities");
	}

	/**
	 * Inserts a new folder.
	 *
	 * @param parent the parent node reference
	 * @param name the folder name
	 * @return the created folder node reference
	 */
	protected NodeRef insertFolder(NodeRef parent, String name) {
		FileFolderService fileFolderService = serviceRegistry.getFileFolderService();
		return fileFolderService.create(parent, name, ContentModel.TYPE_FOLDER).getNodeRef();
	}

	/**
	 * Inserts a new folder with a qualified name.
	 *
	 * @param parent the parent node reference
	 * @param prefix the namespace prefix
	 * @param localName the local name
	 * @return the created folder node reference
	 */
	protected NodeRef insertFolder(NodeRef parent, String prefix, String localName) {
		FileFolderService fileFolderService = serviceRegistry.getFileFolderService();
		NamespaceService namespaceService = serviceRegistry.getNamespaceService();
		QName qname = QName.createQName(prefix, localName, namespaceService);
		return fileFolderService.create(parent, qname.getPrefixString(), ContentModel.TYPE_FOLDER).getNodeRef();
	}

	/**
	 * Inserts a new document with text content.
	 *
	 * @param parent the parent node reference
	 * @param name the document name
	 * @param text the text content
	 * @param properties the document properties
	 * @return the created document node reference
	 */
	protected NodeRef insertDocument(NodeRef parent, String name, String text, Map<QName, Serializable> properties) {
		return NodeUtils.insertDocument(parent, name, text, properties, serviceRegistry);
	}

	/**
	 * Inserts a new document with byte array content.
	 *
	 * @param parent the parent node reference
	 * @param name the document name
	 * @param text the byte array content
	 * @param properties the document properties
	 * @return the created document node reference
	 */
	protected NodeRef insertDocument(NodeRef parent, String name, byte[] text, Map<QName, Serializable> properties) {
		return NodeUtils.insertDocument(parent, name, text, properties, serviceRegistry);
	}

	/**
	 * Inserts a new version for a document.
	 *
	 * @param nodeRef the node reference to version
	 * @param name the version name
	 * @param text the version content
	 * @param version the version label
	 * @param versionType the version type
	 * @return the frozen state node reference
	 */
	protected NodeRef insertVersion(NodeRef nodeRef, String name, String text, String version,
			VersionType versionType) {
		return NodeUtils.insertVersion(nodeRef, name, text, version, versionType, serviceRegistry);
	}

	/**
	 * Inserts a ZIP file as a document.
	 *
	 * @param parent the parent node reference
	 * @param zipName the ZIP file name
	 * @param entryName the entry name inside the ZIP
	 * @param text the text content
	 * @param properties the document properties
	 * @return the created node reference
	 * @throws IOException if an I/O error occurs
	 */
	protected NodeRef insertZip(NodeRef parent, String zipName, String entryName, String text,
			Map<QName, Serializable> properties) throws IOException {
		return ZipUtils.insertZip(parent, zipName, entryName, text, properties, serviceRegistry);
	}

	/**
	 * Encrypts an input stream using SHA-256.
	 *
	 * @param inputStream the input stream to encrypt
	 * @return the Base64 encoded hash
	 * @throws Exception if encryption fails
	 */
	protected String encrypt(InputStream inputStream) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] contentBytes = IOUtils.toByteArray(inputStream);
		byte[] hash = digest.digest(contentBytes);
		String hashOrigString = Base64.encodeBase64String(hash);
		return hashOrigString;
	}

	/**
	 * Unmarshals an XML document from a node into a Java object.
	 *
	 * @param <T> the type of the object
	 * @param createdNodeRef the node reference containing the XML
	 * @param objectClass the target class
	 * @return the unmarshalled object
	 * @throws Exception if unmarshalling fails
	 */
	protected <T> T getObjectFromXml(NodeRef createdNodeRef, Class<T> objectClass) throws Exception {
		ContentService contentService = serviceRegistry.getContentService();
		final JAXBContext contextPdv = JAXBContext.newInstance(objectClass);
		final Unmarshaller unmarshallerPdv = contextPdv.createUnmarshaller();
		InputStream inputStream = contentService.getReader(createdNodeRef, ContentModel.PROP_CONTENT)
				.getContentInputStream();
		JAXBElement<T> jaxbResult = (JAXBElement<T>) unmarshallerPdv.unmarshal(new StreamSource(inputStream),
				objectClass);
		T result = jaxbResult.getValue();
		return result;
	}

	/**
	 * Executes the test action. Sets the current date.
	 */
	protected void executeAction() {
		// data per cercare i file generati
		today = new Date();
	}

}
