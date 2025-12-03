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
 * nodes in a mock Alfresco repository.
 *
 * @author vige
 */
public class NodeUtils {

	/**
	 * Inserts a new folder node.
	 *
	 * @param parent the parent node reference
	 * @param name the folder name
	 * @param fileFolderService the file folder service
	 * @return the created folder node reference
	 */
	public static NodeRef insertFolder(NodeRef parent, String name, FileFolderService fileFolderService) {
		return fileFolderService.create(parent, name, ContentModel.TYPE_FOLDER).getNodeRef();
	}

	/**
	 * Inserts a new document node with text content.
	 *
	 * @param parent the parent node reference
	 * @param name the document name
	 * @param text the text content
	 * @param properties the document properties
	 * @param serviceRegistry the service registry
	 * @return the created document node reference
	 */
	public static NodeRef insertDocument(NodeRef parent, String name, String text, Map<QName, Serializable> properties,
			ServiceRegistry serviceRegistry) {
		return insertDocument(parent, name, text.getBytes(), properties, serviceRegistry);
	}

	/**
	 * Inserts a new document node with byte array content.
	 *
	 * @param parent the parent node reference
	 * @param name the document name
	 * @param text the byte array content
	 * @param properties the document properties
	 * @param serviceRegistry the service registry
	 * @return the created document node reference
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
	 * Inserts a new version for a document node.
	 *
	 * @param nodeRef the node reference to version
	 * @param name the version name
	 * @param text the version content
	 * @param version the version label
	 * @param versionType the version type (MAJOR or MINOR)
	 * @param serviceRegistry the service registry
	 * @return the frozen state node reference of the created version
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
	 * Sorts node references by their ID.
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
	 * Generates a UUID based on the node path.
	 *
	 * @param nodePath the node path
	 * @return the generated UUID string
	 */
	public static String generateUUID(String nodePath) {
		if (nodePath.equals(FOLDER_TEST + PROTOCOL_WORKSPACE))
			return FOLDER_TEST + PROTOCOL_WORKSPACE.hashCode() + "";
		if (nodePath.equals(FOLDER_TEST + PROTOCOL_ARCHIVE))
			return FOLDER_TEST + PROTOCOL_ARCHIVE.hashCode() + "";
		return nodePath.replaceAll("/" + STORE_REF_WORKSPACE_SPACESSTORE.getIdentifier(), "").hashCode() + "";
	}

}
