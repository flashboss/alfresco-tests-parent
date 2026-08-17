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
	/**
	 * Production cm:name values for well-known Alfresco spaces.
	 */
	private static final Map<String, String> WELL_KNOWN_CM_NAMES = new HashMap<String, String>();

	static {
		WELL_KNOWN_CM_NAMES.put("company_home", "Company Home");
		WELL_KNOWN_CM_NAMES.put("shared", "Shared");
		WELL_KNOWN_CM_NAMES.put("sites", "Sites");
		WELL_KNOWN_CM_NAMES.put("system", "System");
		WELL_KNOWN_CM_NAMES.put("authorities", "Authorities");
		WELL_KNOWN_CM_NAMES.put("guest_home", "Guest Home");
		WELL_KNOWN_CM_NAMES.put("user_homes", "User Homes");
		WELL_KNOWN_CM_NAMES.put("dictionary", "Data Dictionary");
	}


	public static NodeRef insertFolder(NodeRef parent, String name, FileFolderService fileFolderService) {
		return fileFolderService.create(parent, name, ContentModel.TYPE_FOLDER).getNodeRef();
	}

	/**
	 * Inserts a well-known Alfresco space using the association QName and production cm:name.
	 */
	public static NodeRef insertFolder(NodeRef parent, String prefix, String localName,
			NodeService nodeService, NamespaceService namespaceService) {
		QName assocQName = QName.createQName(prefix, localName, namespaceService);
		QName assocTypeQName = "company_home".equals(localName) || "system".equals(localName)
				? ContentModel.ASSOC_CHILDREN : ContentModel.ASSOC_CONTAINS;
		Map<QName, Serializable> properties = new HashMap<QName, Serializable>();
		properties.put(ContentModel.PROP_NAME, toAlfrescoCmName(localName));
		return nodeService.createNode(parent, assocTypeQName, assocQName, ContentModel.TYPE_FOLDER, properties)
				.getChildRef();
	}

	/**
	 * Converts an association local name to the cm:name used in a real Alfresco bootstrap.
	 */
	public static String toAlfrescoCmName(String localName) {
		if (localName == null || localName.isEmpty()) {
			return localName;
		}
		String known = WELL_KNOWN_CM_NAMES.get(localName);
		if (known != null) {
			return known;
		}
		String[] parts = localName.split("_");
		StringBuilder sb = new StringBuilder(localName.length() + 4);
		for (int i = 0; i < parts.length; i++) {
			if (i > 0) {
				sb.append(' ');
			}
			String part = parts[i];
			if (!part.isEmpty()) {
				sb.append(Character.toUpperCase(part.charAt(0)));
				if (part.length() > 1) {
					sb.append(part.substring(1));
				}
			}
		}
		return sb.toString();
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
