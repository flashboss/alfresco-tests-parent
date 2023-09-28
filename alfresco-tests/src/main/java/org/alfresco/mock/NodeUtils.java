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
import java.util.Date;
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

public class NodeUtils {

	public static NodeRef insertFolder(NodeRef parent, String name, FileFolderService fileFolderService) {
		return fileFolderService.create(parent, name, ContentModel.TYPE_FOLDER).getNodeRef();
	}

	public static NodeRef insertDocument(NodeRef parent, String name, String text, Map<QName, Serializable> properties,
			ServiceRegistry serviceRegistry) {
		return insertDocument(parent, name, text.getBytes(), properties, serviceRegistry);
	}

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

	public static String generateUUID(String nodePath) {
		if (nodePath.equals(FOLDER_TEST + PROTOCOL_WORKSPACE))
			return FOLDER_TEST + PROTOCOL_WORKSPACE.hashCode() + "";
		if (nodePath.equals(FOLDER_TEST + PROTOCOL_ARCHIVE))
			return FOLDER_TEST + PROTOCOL_ARCHIVE.hashCode() + "";
		return nodePath.replaceAll("/" + STORE_REF_WORKSPACE_SPACESSTORE.getIdentifier(), "").hashCode() + "";
	}

}
