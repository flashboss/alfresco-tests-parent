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
 * Utility class for handling ZIP files in a mock Alfresco environment.
 * Provides methods for unzipping, reading entry content, adding entries,
 * and creating ZIP nodes in the repository.
 * 
 * @author vige
 */
public class ZipUtils {

	/** Temporary directory for ZIP operations. */
	public final static File TEMP_DIR = new File(MockContentService.FOLDER_TEST + "temp");

	/**
	 * Extracts all entries from a ZIP input stream to a target directory.
	 * 
	 * @param inputStream the ZIP input stream to extract
	 * @param targetDirectory the directory to extract files to
	 * @throws IOException if an I/O error occurs during extraction
	 */
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

	/**
	 * Reads the content of a specific file from an unzipped directory.
	 * 
	 * @param parent the directory containing the unzipped files
	 * @param documentName the name prefix of the file to read
	 * @return the content of the file as a string
	 * @throws IOException if an I/O error occurs while reading
	 */
	public static String getZipEntryContent(File parent, final String documentName) throws IOException {
		FileFilter fileFiler = new FileFilter() {
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

	/**
	 * Reads the content of the first file from an unzipped directory.
	 * 
	 * @param parent the directory containing the unzipped files
	 * @return the content of the first file as a string
	 * @throws IOException if an I/O error occurs while reading
	 */
	public static String getZipEntryContent(File parent) throws IOException {
		File[] unzippedFiles = parent.listFiles();
		byte[] bytes = new byte[1024];
		try (InputStream inputStream = new FileInputStream(unzippedFiles[0])) {
			inputStream.read(bytes);
		}
		return new String(bytes);
	}

	/**
	 * Adds a text entry to a ZIP output stream.
	 * 
	 * @param text the text content to add
	 * @param entryName the name of the ZIP entry
	 * @param output the output stream to write the ZIP to
	 * @throws IOException if an I/O error occurs while writing
	 */
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

	/**
	 * Creates a ZIP file node in the Alfresco repository with the specified content.
	 * 
	 * @param parent the parent node reference
	 * @param zipName the name of the ZIP file
	 * @param entryName the name of the entry inside the ZIP
	 * @param text the text content to add to the ZIP entry
	 * @param properties the properties to set on the node
	 * @param serviceRegistry the service registry to use
	 * @return the NodeRef of the created ZIP node
	 * @throws IOException if an I/O error occurs while creating the ZIP
	 */
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
