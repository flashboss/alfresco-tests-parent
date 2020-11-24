package org.alfresco.mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.MimetypeService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
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
		return nodePath.hashCode() + "";
	}

}
