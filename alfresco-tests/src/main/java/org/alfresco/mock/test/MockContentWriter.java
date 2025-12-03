package org.alfresco.mock.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.content.filestore.FileContentReader;
import org.alfresco.repo.domain.node.ContentDataWithId;
import org.alfresco.service.cmr.repository.ContentData;
import org.alfresco.service.cmr.repository.ContentIOException;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentStreamListener;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.io.IOUtils;

/**
 * Mock implementation of the MockContentWriter class for testing purposes. This class provides a
 * mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockContentWriter implements ContentWriter {

  /** The file. */
  private File file;

  /** The mimetype. */
  private String mimetype;

  /** The content prop. */
  private ContentDataWithId contentProp;

  /** The node. */
  private NodeRef node;

  /** The node service. */
  private NodeService nodeService;

  /**
   * Constructs a new MockContentWriter with the specified file, node, and node service.
   *
   * @param file the file
   * @param node the node reference
   * @param nodeService the node service
   */
  public MockContentWriter(File file, NodeRef node, NodeService nodeService) {
    this.node = node;
    this.nodeService = nodeService;
    Path filePath = Paths.get(file.getAbsolutePath() + "/" + file.getName());
    try {
      this.file = Files.createFile(filePath).toFile();
    } catch (FileAlreadyExistsException faee) {
      this.file = filePath.toFile();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (this.file != null)
        try {
          this.contentProp =
              new ContentDataWithId(
                  new ContentData(
                      "store:/" + filePath,
                      "application/octet-stream",
                      Files.size(filePath),
                      "UTF-8",
                      Locale.getDefault()),
                  Long.parseLong(node.getId()));
        } catch (NumberFormatException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean isChannelOpen() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @param listener the listener
   */
  @Override
  public void addListener(ContentStreamListener listener) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public long getSize() {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public ContentData getContentData() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getContentUrl() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getMimetype() {
    return mimetype;
  }

  /**
   * {@inheritDoc}
   *
   * @param mimetype the mimetype
   */
  @Override
  public void setMimetype(String mimetype) {
    this.mimetype = mimetype;
    contentProp =
        new ContentDataWithId(
            ContentDataWithId.setMimetype(contentProp, mimetype), contentProp.getId());
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getEncoding() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param encoding the encoding
   */
  @Override
  public void setEncoding(String encoding) {
    contentProp =
        new ContentDataWithId(
            ContentDataWithId.setEncoding(contentProp, encoding), contentProp.getId());
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Locale getLocale() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param locale the locale
   */
  @Override
  public void setLocale(Locale locale) {
    contentProp =
        new ContentDataWithId(
            new ContentData(
                contentProp.getContentUrl(),
                contentProp.getMimetype(),
                contentProp.getSize(),
                contentProp.getEncoding(),
                locale),
            Long.parseLong(node.getId()));
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   * @throws ContentIOException if an error occurs
   */
  @Override
  public ContentReader getReader() throws ContentIOException {
    return new FileContentReader(file);
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean isClosed() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   * @throws ContentIOException if an error occurs
   */
  @Override
  public WritableByteChannel getWritableChannel() throws ContentIOException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param truncate the truncate
   * @return the result
   * @throws ContentIOException if an error occurs
   */
  @Override
  public FileChannel getFileChannel(boolean truncate) throws ContentIOException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   * @throws ContentIOException if an error occurs
   */
  @Override
  public OutputStream getContentOutputStream() throws ContentIOException {
    try (FileOutputStream fom = new FileOutputStream(file)) {
      return fom;
    } catch (IOException e) {
      throw new ContentIOException(e.getMessage(), e);
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param reader the reader
   * @throws ContentIOException if an error occurs
   */
  @Override
  public void putContent(ContentReader reader) throws ContentIOException {
    putContent(reader.getContentInputStream());
  }

  /**
   * {@inheritDoc}
   *
   * @param is the is
   * @throws ContentIOException if an error occurs
   */
  @Override
  public void putContent(InputStream is) throws ContentIOException {
    try (FileOutputStream fom = new FileOutputStream(file)) {
      IOUtils.copy(is, fom);
      nodeService.setProperty(node, ContentModel.PROP_CONTENT, contentProp);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param file the file
   * @throws ContentIOException if an error occurs
   */
  @Override
  public void putContent(File file) throws ContentIOException {
    try (FileInputStream fim = new FileInputStream(file)) {
      putContent(fim);
    } catch (IOException e) {
      throw new ContentIOException(e.getMessage(), e);
    }
  }

  /**
   * {@inheritDoc}
   *
   * @param content the content
   * @throws ContentIOException if an error occurs
   */
  @Override
  public void putContent(String content) throws ContentIOException {
    putContent(new ByteArrayInputStream(content.getBytes()));
  }

  /**
   * {@inheritDoc}
   *
   * @param filename the filename
   */
  @Override
  public void guessMimetype(String filename) {
    // TODO Auto-generated method stub

  }

  /** {@inheritDoc} */
  @Override
  public void guessEncoding() {
    // TODO Auto-generated method stub

  }

  /**
   * Adds an entry to a ZIP file.
   *
   * @param text the text content to add
   * @param output the output stream
   * @throws IOException if an I/O error occurs
   */
  public static void addEntryToZip(String text, OutputStream output) throws IOException {
    InputStream inputStream = new ByteArrayInputStream(text.getBytes());
    ZipOutputStream zipOut = new ZipOutputStream(output);
    ZipEntry zipEntry = new ZipEntry("prova");
    zipOut.putNextEntry(zipEntry);
    byte[] bytes = new byte[1024];
    int length;
    while ((length = inputStream.read(bytes)) >= 0) {
      zipOut.write(bytes, 0, length);
    }
    zipOut.close();
  }
}
