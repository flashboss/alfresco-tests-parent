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
 * Utility class for handling ZIP file operations in a mock environment.
 *
 * @author vige
 */
public class ZipUtils {

  /** Temporary directory for ZIP operations. */
  public static final File TEMP_DIR = new File(MockContentService.FOLDER_TEST + "temp");

  /**
   * Extracts a ZIP file to a target directory.
   *
   * @param inputStream the input stream of the ZIP file
   * @param targetDirectory the target directory for extraction
   * @throws IOException if an I/O error occurs
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
   * Gets the content of a ZIP entry matching the document name.
   *
   * @param parent the parent directory containing extracted files
   * @param documentName the document name to search for
   * @return the content of the ZIP entry
   * @throws IOException if an I/O error occurs
   */
  public static String getZipEntryContent(File parent, final String documentName)
      throws IOException {
    FileFilter fileFiler =
        new FileFilter() {
          /**
           * Accept.
           *
           * @param pathname the pathname
           * @return the result
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

  /**
   * Gets the content of the first file in a directory.
   *
   * @param parent the parent directory
   * @return the content of the first file
   * @throws IOException if an I/O error occurs
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
   * @param output the output stream to write to
   * @throws IOException if an I/O error occurs
   */
  public static void addEntryToZip(String text, String entryName, OutputStream output)
      throws IOException {
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
   * Inserts a ZIP file as a document node in the repository.
   *
   * @param parent the parent node reference
   * @param zipName the name of the ZIP file
   * @param entryName the name of the entry inside the ZIP
   * @param text the text content to include in the ZIP
   * @param properties the document properties
   * @param serviceRegistry the service registry
   * @return the created node reference
   * @throws IOException if an I/O error occurs
   */
  public static NodeRef insertZip(
      NodeRef parent,
      String zipName,
      String entryName,
      String text,
      Map<QName, Serializable> properties,
      ServiceRegistry serviceRegistry)
      throws IOException {
    NodeService nodeService = serviceRegistry.getNodeService();
    ContentService contentService = serviceRegistry.getContentService();
    QName type = null;
    if (properties != null) type = (QName) properties.get(ContentModel.TYPE_BASE);
    if (type == null) type = ContentModel.TYPE_CONTENT;
    NodeRef node =
        nodeService
            .createNode(
                parent,
                ContentModel.ASSOC_CONTAINS,
                QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, zipName),
                type,
                properties)
            .getChildRef();
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    addEntryToZip(text, entryName, output);
    ContentWriter writer = contentService.getWriter(node, ContentModel.PROP_CONTENT, true);
    writer.setMimetype(MimetypeMap.MIMETYPE_ZIP);
    writer.putContent(new ByteArrayInputStream(output.toByteArray()));
    return node;
  }
}
