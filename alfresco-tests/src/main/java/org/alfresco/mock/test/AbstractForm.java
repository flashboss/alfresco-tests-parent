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
 * Provides initialization of the mock repository structure including
 * workspace, archive, sites, and shared folders. Also provides utility
 * methods for inserting folders, documents, versions, and ZIP files.
 * 
 * @author vige
 */
@RunWith(RemoteTestRunner.class)
@Remote(runnerClass = ClasspathTestRunner.class)
@ContextConfiguration("classpath:test-module-context.xml")
public abstract class AbstractForm {

	/** The Alfresco service registry for accessing services. */
	@Autowired
	protected ServiceRegistry serviceRegistry;

	/** Reference to the workspace spaces store root. */
	protected NodeRef spacesStore;
	
	/** Reference to the archive store root. */
	protected NodeRef archive;
	
	/** Reference to the sites folder. */
	protected NodeRef sites;
	
	/** Reference to the shared folder. */
	protected NodeRef shared;
	
	/** Current date used for test operations. */
	protected Date today;
	
	/** String representation of today's date. */
	protected String todayStr;
	
	/** Reference to the company home folder. */
	protected NodeRef companyHome;

	/**
	 * Initializes the mock repository structure.
	 * Creates the standard Alfresco folder hierarchy including workspace,
	 * archive, company home, sites, and shared folders.
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
	 * Creates a folder under the specified parent node.
	 * 
	 * @param parent the parent node reference
	 * @param name the name of the folder to create
	 * @return the NodeRef of the created folder
	 */
	protected NodeRef insertFolder(NodeRef parent, String name) {
		FileFolderService fileFolderService = serviceRegistry.getFileFolderService();
		return fileFolderService.create(parent, name, ContentModel.TYPE_FOLDER).getNodeRef();
	}

	/**
	 * Creates a folder under the specified parent node with a qualified name.
	 * 
	 * @param parent the parent node reference
	 * @param prefix the namespace prefix
	 * @param localName the local name of the folder
	 * @return the NodeRef of the created folder
	 */
	protected NodeRef insertFolder(NodeRef parent, String prefix, String localName) {
		FileFolderService fileFolderService = serviceRegistry.getFileFolderService();
		NamespaceService namespaceService = serviceRegistry.getNamespaceService();
		QName qname = QName.createQName(prefix, localName, namespaceService);
		return fileFolderService.create(parent, qname.getPrefixString(), ContentModel.TYPE_FOLDER).getNodeRef();
	}

	/**
	 * Creates a document with text content under the specified parent node.
	 * 
	 * @param parent the parent node reference
	 * @param name the name of the document
	 * @param text the text content of the document
	 * @param properties the properties to set on the document
	 * @return the NodeRef of the created document
	 */
	protected NodeRef insertDocument(NodeRef parent, String name, String text, Map<QName, Serializable> properties) {
		return NodeUtils.insertDocument(parent, name, text, properties, serviceRegistry);
	}

	/**
	 * Creates a document with binary content under the specified parent node.
	 * 
	 * @param parent the parent node reference
	 * @param name the name of the document
	 * @param text the binary content of the document
	 * @param properties the properties to set on the document
	 * @return the NodeRef of the created document
	 */
	protected NodeRef insertDocument(NodeRef parent, String name, byte[] text, Map<QName, Serializable> properties) {
		return NodeUtils.insertDocument(parent, name, text, properties, serviceRegistry);
	}

	/**
	 * Creates a new version of an existing document.
	 * 
	 * @param nodeRef the node reference of the document to version
	 * @param name the name of the versioned document
	 * @param text the text content for the new version
	 * @param version the version label
	 * @param versionType the type of version (MAJOR or MINOR)
	 * @return the NodeRef of the frozen state of the created version
	 */
	protected NodeRef insertVersion(NodeRef nodeRef, String name, String text, String version,
			VersionType versionType) {
		return NodeUtils.insertVersion(nodeRef, name, text, version, versionType, serviceRegistry);
	}

	/**
	 * Creates a ZIP file node in the repository.
	 * 
	 * @param parent the parent node reference
	 * @param zipName the name of the ZIP file
	 * @param entryName the name of the entry inside the ZIP
	 * @param text the text content to add to the ZIP entry
	 * @param properties the properties to set on the node
	 * @return the NodeRef of the created ZIP node
	 * @throws IOException if an I/O error occurs
	 */
	protected NodeRef insertZip(NodeRef parent, String zipName, String entryName, String text,
			Map<QName, Serializable> properties) throws IOException {
		return ZipUtils.insertZip(parent, zipName, entryName, text, properties, serviceRegistry);
	}

	/**
	 * Computes a SHA-256 hash of the input stream content and returns it as Base64.
	 * 
	 * @param inputStream the input stream to hash
	 * @return the Base64-encoded SHA-256 hash
	 * @throws Exception if an error occurs during hashing
	 */
	protected String encrypt(InputStream inputStream) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] contentBytes = IOUtils.toByteArray(inputStream);
		byte[] hash = digest.digest(contentBytes);
		String hashOrigString = Base64.encodeBase64String(hash);
		return hashOrigString;
	}

	/**
	 * Unmarshals an XML document from a node's content into a Java object.
	 * 
	 * @param <T> the type of object to unmarshal
	 * @param createdNodeRef the node reference containing the XML content
	 * @param objectClass the class of the object to unmarshal
	 * @return the unmarshalled object
	 * @throws Exception if an error occurs during unmarshalling
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
	 * Executes an action and sets the current date.
	 * Subclasses should override this method to implement specific action logic.
	 */
	protected void executeAction() {
		// data per cercare i file generati
		today = new Date();
	}

}
