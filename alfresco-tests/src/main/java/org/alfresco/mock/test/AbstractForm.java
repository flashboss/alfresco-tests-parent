package org.alfresco.mock.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

import org.alfresco.model.ContentModel;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.MimetypeService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractForm {

	@Autowired
	protected NodeService nodeService;

	@Autowired
	protected ContentService contentService;

	@Autowired
	protected FileFolderService fileFolderService;

	@Autowired
	protected SearchService searchService;

	@Autowired
	protected MimetypeService mimetypeService;

	protected NodeRef workspace;
	protected NodeRef archive;
	protected NodeRef site;
	protected Date today;
	protected String todayStr;

	public void init() {
		// elimino i vecchi documenti
		ResultSet nodes = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
				SearchService.LANGUAGE_FTS_ALFRESCO, "PATH:\"*\"");
		if (nodes.length() > 0)
			for (NodeRef node : nodes.getNodeRefs())
				fileFolderService.delete(node);
		try {
			FileUtils.deleteDirectory(new File(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_WORKSPACE));
			FileUtils.deleteDirectory(new File(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_ARCHIVE));
			FileUtils.deleteDirectory(ZipUtils.TEMP_DIR);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// creo le directory iniziali per i pdv, gli rdv e il repository
		NodeRef root = insertFolder(new NodeRef(new StoreRef("", ""), ""), ".");
		workspace = insertFolder(root, StoreRef.PROTOCOL_WORKSPACE);
		archive = insertFolder(root, StoreRef.PROTOCOL_ARCHIVE);
		site = insertFolder(workspace, "cm:Site");
	}

	protected NodeRef insertFolder(NodeRef parent, String name) {
		return fileFolderService.create(parent, name, ContentModel.TYPE_FOLDER).getNodeRef();
	}

	protected NodeRef insertDocument(NodeRef parent, String name, String text, Map<QName, Serializable> properties) {
		NodeRef node = nodeService.createNode(parent, ContentModel.ASSOC_CONTAINS,
				QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, name), ContentModel.TYPE_CONTENT, properties)
				.getChildRef();
		InputStream inputStream = new ByteArrayInputStream(text.getBytes());
		ContentWriter writer = contentService.getWriter(node, ContentModel.PROP_CONTENT, true);
		writer.setMimetype(mimetypeService.getMimetype(mimetypeService.getExtension(name)));
		writer.putContent(inputStream);
		return node;
	}

	protected NodeRef insertZip(NodeRef parent, String zipName, String entryName, String text,
			Map<QName, Serializable> properties) throws IOException {
		NodeRef node = nodeService.createNode(parent, ContentModel.ASSOC_CONTAINS,
				QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, zipName), ContentModel.TYPE_CONTENT,
				properties).getChildRef();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ZipUtils.addEntryToZip(text, entryName, output);
		ContentWriter writer = contentService.getWriter(node, ContentModel.PROP_CONTENT, true);
		writer.setMimetype(MimetypeMap.MIMETYPE_ZIP);
		writer.putContent(new ByteArrayInputStream(output.toByteArray()));
		return node;
	}

	protected String encrypt(InputStream inputStream) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] contentBytes = IOUtils.toByteArray(inputStream);
		byte[] hash = digest.digest(contentBytes);
		String hashOrigString = Base64.encodeBase64String(hash);
		return hashOrigString;
	}

	protected <T> T getObjectFromXml(NodeRef createdNodeRef, Class<T> objectClass) throws Exception {
		final JAXBContext contextPdv = JAXBContext.newInstance(objectClass);
		final Unmarshaller unmarshallerPdv = contextPdv.createUnmarshaller();
		InputStream inputStream = contentService.getReader(createdNodeRef, ContentModel.PROP_CONTENT)
				.getContentInputStream();
		JAXBElement<T> jaxbResult = (JAXBElement<T>) unmarshallerPdv.unmarshal(new StreamSource(inputStream),
				objectClass);
		T result = jaxbResult.getValue();
		return result;
	}

	protected void executeAction() {
		// data per cercare i file generati
		today = new Date();
	}

}
