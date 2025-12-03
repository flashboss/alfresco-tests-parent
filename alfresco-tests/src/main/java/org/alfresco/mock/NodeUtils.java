package org.alfresco.mock;

import static org.alfresco.mock.test.MockContentService.FOLDER_TEST;
import static org.alfresco.service.cmr.repository.StoreRef.PROTOCOL_ARCHIVE;
import static org.alfresco.service.cmr.repository.StoreRef.PROTOCOL_WORKSPACE;
import static org.alfresco.service.cmr.repository.StoreRef.STORE_REF_WORKSPACE_SPACESSTORE;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.version.Version2Model;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.MimetypeService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionService;
import org.alfresco.service.cmr.version.VersionType;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

/**
 * Utility class providing static helper methods for creating and manipulating
 * nodes in a mock Alfresco repository. This class provides methods for inserting
 * folders, documents, and versions, as well as sorting and UUID generation.
 * 
 * @author vige
 */
public class NodeUtils {

	/**
	 * Creates a new folder under the specified parent node.
	 * 
	 * @param parent the parent node reference
	 * @param name the name of the folder to create
	 * @param fileFolderService the file folder service to use
	 * @return the NodeRef of the created folder
	 */
	public static NodeRef insertFolder(NodeRef parent, String name, FileFolderService fileFolderService) {
		return fileFolderService.create(parent, name, ContentModel.TYPE_FOLDER).getNodeRef();
	}

	/**
	 * Creates a new document with text content under the specified parent node.
	 * 
	 * @param parent the parent node reference
	 * @param name the name of the document to create
	 * @param text the text content of the document
	 * @param properties the properties to set on the document
	 * @param serviceRegistry the service registry to use
	 * @return the NodeRef of the created document
	 */
	public static NodeRef insertDocument(NodeRef parent, String name, String text, Map<QName, Serializable> properties,
			ServiceRegistry serviceRegistry) {
		return insertDocument(parent, name, text.getBytes(), properties, serviceRegistry);
	}

	/**
	 * Creates a new document with binary content under the specified parent node.
	 * 
	 * @param parent the parent node reference
	 * @param name the name of the document to create
	 * @param text the binary content of the document
	 * @param properties the properties to set on the document
	 * @param serviceRegistry the service registry to use
	 * @return the NodeRef of the created document
	 */
	public static NodeRef insertDocument(NodeRef parent, String name, byte[] text, Map<QName, Serializable> properties,
			ServiceRegistry serviceRegistry) {
		NodeService nodeService = serviceRegistry.getNodeService();
		ContentService contentService = serviceRegistry.getContentService();
		MimetypeService mimetypeService = serviceRegistry.getMimetypeService();
		NamespaceService namespaceService = serviceRegistry.getNamespaceService();
		QName type = null;
		if (properties != null)
			type = (QName) properties.get(ContentModel.TYPE_BASE);
		if (type == null)
			type = QName.createQName("cm", ContentModel.TYPE_CONTENT.getLocalName(), namespaceService);
		NodeRef node = nodeService.createNode(parent, ContentModel.ASSOC_CONTAINS,
				QName.createQName(NamespaceService.CONTENT_MODEL_PREFIX, name, namespaceService), type, properties)
				.getChildRef();
		InputStream inputStream = new ByteArrayInputStream(text);
		ContentWriter writer = contentService.getWriter(node, ContentModel.PROP_CONTENT, true);
		writer.setMimetype(mimetypeService.getMimetype(mimetypeService.getExtension(name)));
		writer.putContent(inputStream);
		return node;
	}

	/**
	 * Creates a new version of an existing document.
	 * 
	 * @param nodeRef the node reference of the document to version
	 * @param name the name of the versioned document
	 * @param text the text content for the new version
	 * @param version the version label
	 * @param versionType the type of version (MAJOR or MINOR)
	 * @param serviceRegistry the service registry to use
	 * @return the NodeRef of the frozen state of the created version
	 */
	public static NodeRef insertVersion(NodeRef nodeRef, String name, String text, String version, VersionType versionType,
			ServiceRegistry serviceRegistry) {
		VersionService versionService = serviceRegistry.getVersionService();
		Map<String, Serializable> versionProperties = new HashMap<String, Serializable>();
		versionProperties.put(Version2Model.PROP_QNAME_VERSION_LABEL.getLocalName(), version);
		versionProperties.put(Version2Model.PROP_VERSION_TYPE, versionType.name());
		versionProperties.put(ContentModel.PROP_CONTENT.getLocalName(), text.getBytes());
		versionProperties.put(ContentModel.PROP_NAME.getLocalName(), name);
		Version versionRef = versionService.createVersion(nodeRef, versionProperties);
		return versionRef.getFrozenStateNodeRef();
	}

	/**
	 * Sorts a set of node references by their ID.
	 * 
	 * @param nodeRefs the set of node references to sort
	 * @return a sorted list of node references
	 */
	public static List<NodeRef> sortByName(Set<NodeRef> nodeRefs) {
		NodeRef[] nodeArray = nodeRefs.toArray(new NodeRef[0]);
		Arrays.sort(nodeArray, new Comparator<NodeRef>() {

			@Override
			public int compare(NodeRef o1, NodeRef o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return Arrays.asList(nodeArray);
	}

	/**
	 * Generates a UUID for a node based on its path.
	 * Uses hash codes to generate deterministic UUIDs for mock nodes.
	 * 
	 * @param nodePath the path of the node
	 * @return a generated UUID string
	 */
	public static String generateUUID(String nodePath) {
		if (nodePath.equals(FOLDER_TEST + PROTOCOL_WORKSPACE))
			return FOLDER_TEST + PROTOCOL_WORKSPACE.hashCode() + "";
		if (nodePath.equals(FOLDER_TEST + PROTOCOL_ARCHIVE))
			return FOLDER_TEST + PROTOCOL_ARCHIVE.hashCode() + "";
		return nodePath.replaceAll("/" + STORE_REF_WORKSPACE_SPACESSTORE.getIdentifier(), "").hashCode() + "";
	}

}
