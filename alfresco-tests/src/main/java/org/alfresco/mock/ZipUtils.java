package org.alfresco.mock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.alfresco.mock.test.MockContentService;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of the ZipUtils class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class ZipUtils {

	public final static File TEMP_DIR = new File(MockContentService.FOLDER_TEST + "temp");

	public static void unzip(InputStream inputStream, File targetDirectory) throws IOException {
		targetDirectory.mkdir();
		byte[] buffer = new byte[1024];
		ZipInputStream zis = new ZipInputStream(inputStream);
		ZipEntry zipEntry = zis.getNextEntry();
		while (zipEntry != null) {
			String[] filePaths = zipEntry.getName().split("/");
			String fileName = filePaths[filePaths.length - 1];
			File newFile = new File(targetDirectory + "/" + fileName);
			FileOutputStream fos = new FileOutputStream(newFile);
			int len;
			while ((len = zis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			zipEntry = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
	}

	public static String getZipEntryContent(File parent, final String documentName) throws IOException {
		FileFilter fileFiler = new FileFilter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().startsWith(documentName);
			}
		};
		File[] unzippedFiles = parent.listFiles(fileFiler);
		byte[] bytes = new byte[1024];
		try (InputStream inputStream = new FileInputStream(unzippedFiles[0])) {
			inputStream.read(bytes);
		}
		return new String(bytes);
	}

	public static String getZipEntryContent(File parent) throws IOException {
		File[] unzippedFiles = parent.listFiles();
		byte[] bytes = new byte[1024];
		try (InputStream inputStream = new FileInputStream(unzippedFiles[0])) {
			inputStream.read(bytes);
		}
		return new String(bytes);
	}

	public static void addEntryToZip(String text, String entryName, OutputStream output) throws IOException {
		InputStream inputStream = new ByteArrayInputStream(text.getBytes());
		ZipOutputStream zipOut = new ZipOutputStream(output);
		ZipEntry zipEntry = new ZipEntry(entryName);
		zipOut.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = inputStream.read(bytes)) >= 0) {
			zipOut.write(bytes, 0, length);
		}
		zipOut.close();
	}

	public static NodeRef insertZip(NodeRef parent, String zipName, String entryName, String text,
			Map<QName, Serializable> properties, ServiceRegistry serviceRegistry)
			throws IOException {
		NodeService nodeService = serviceRegistry.getNodeService(); 
		ContentService contentService = serviceRegistry.getContentService();
		QName type = null;
		if (properties != null)
			type = (QName) properties.get(ContentModel.TYPE_BASE);
		if (type == null)
			type = ContentModel.TYPE_CONTENT;
		NodeRef node = nodeService
				.createNode(parent, ContentModel.ASSOC_CONTAINS,
						QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, zipName), type, properties)
				.getChildRef();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		addEntryToZip(text, entryName, output);
		ContentWriter writer = contentService.getWriter(node, ContentModel.PROP_CONTENT, true);
		writer.setMimetype(MimetypeMap.MIMETYPE_ZIP);
		writer.putContent(new ByteArrayInputStream(output.toByteArray()));
		return node;
	}

}
