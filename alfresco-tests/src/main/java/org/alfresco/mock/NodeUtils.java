package org.alfresco.mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

import org.alfresco.model.ContentModel;
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
			NodeService nodeService, ContentService contentService, MimetypeService mimetypeService) {
		NodeRef node = nodeService.createNode(parent, ContentModel.ASSOC_CONTAINS,
				QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, name), ContentModel.TYPE_CONTENT, properties)
				.getChildRef();
		InputStream inputStream = new ByteArrayInputStream(text.getBytes());
		ContentWriter writer = contentService.getWriter(node, ContentModel.PROP_CONTENT, true);
		writer.setMimetype(mimetypeService.getMimetype(mimetypeService.getExtension(name)));
		writer.putContent(inputStream);
		return node;
	}

}
