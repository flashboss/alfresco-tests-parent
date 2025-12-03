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
 * Mock implementation of MockContentWriter for testing purposes.
 *
 * @author vige
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
	 * Constructs a new MockContentWriter.
	 *
	 * @param file the file
	 * @param node the node
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
					this.contentProp = new ContentDataWithId(new ContentData("store:/" + filePath,
							Files.probeContentType(filePath), Files.size(filePath), "UTF-8", Locale.getDefault()),
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

	@Override
	/**
	 * Is channel open.
	 *
	 * @return the result
	 */
	public boolean isChannelOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Add listener.
	 *
	 * @param listener the listener
	 */
	public void addListener(ContentStreamListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get size.
	 *
	 * @return the result
	 */
	public long getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	/**
	 * Get content data.
	 *
	 * @return the result
	 */
	public ContentData getContentData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get content url.
	 *
	 * @return the result
	 */
	public String getContentUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get mimetype.
	 *
	 * @return the result
	 */
	public String getMimetype() {
		return mimetype;
	}

	@Override
	/**
	 * Set mimetype.
	 *
	 * @param mimetype the mimetype
	 */
	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
		contentProp = new ContentDataWithId(ContentDataWithId.setMimetype(contentProp, mimetype), contentProp.getId());
	}

	@Override
	/**
	 * Get encoding.
	 *
	 * @return the result
	 */
	public String getEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Set encoding.
	 *
	 * @param encoding the encoding
	 */
	public void setEncoding(String encoding) {
		contentProp = new ContentDataWithId(ContentDataWithId.setEncoding(contentProp, encoding), contentProp.getId());
	}

	@Override
	/**
	 * Get locale.
	 *
	 * @return the result
	 */
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Set locale.
	 *
	 * @param locale the locale
	 */
	public void setLocale(Locale locale) {
		contentProp = new ContentDataWithId(new ContentData(contentProp.getContentUrl(), contentProp.getMimetype(),
				contentProp.getSize(), contentProp.getEncoding(), locale), Long.parseLong(node.getId()));

	}

	@Override
	/**
	 * Get reader.
	 *
	 * @return the result
	 */
	public ContentReader getReader() throws ContentIOException {
		return new FileContentReader(file);
	}

	@Override
	/**
	 * Is closed.
	 *
	 * @return the result
	 */
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get writable channel.
	 *
	 * @return the result
	 */
	public WritableByteChannel getWritableChannel() throws ContentIOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get file channel.
	 *
	 * @param truncate the truncate
	 * @return the result
	 */
	public FileChannel getFileChannel(boolean truncate) throws ContentIOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get content output stream.
	 *
	 * @return the result
	 */
	public OutputStream getContentOutputStream() throws ContentIOException {
		try (FileOutputStream fom = new FileOutputStream(file)) {
			return fom;
		} catch (IOException e) {
			throw new ContentIOException(e.getMessage(), e);
		}
	}

	@Override
	/**
	 * Put content.
	 *
	 * @param reader the reader
	 */
	public void putContent(ContentReader reader) throws ContentIOException {
		putContent(reader.getContentInputStream());
	}

	@Override
	/**
	 * Put content.
	 *
	 * @param is the is
	 */
	public void putContent(InputStream is) throws ContentIOException {
		try (FileOutputStream fom = new FileOutputStream(file)) {
			IOUtils.copy(is, fom);
			nodeService.setProperty(node, ContentModel.PROP_CONTENT, contentProp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Put content.
	 *
	 * @param file the file
	 */
	public void putContent(File file) throws ContentIOException {
		try (FileInputStream fim = new FileInputStream(file)) {
			putContent(fim);
		} catch (IOException e) {
			throw new ContentIOException(e.getMessage(), e);
		}
	}

	@Override
	/**
	 * Put content.
	 *
	 * @param content the content
	 */
	public void putContent(String content) throws ContentIOException {
		putContent(new ByteArrayInputStream(content.getBytes()));
	}

	@Override
	/**
	 * Guess mimetype.
	 *
	 * @param filename the filename
	 */
	public void guessMimetype(String filename) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Guess encoding.
	 *
	 */
	public void guessEncoding() {
		// TODO Auto-generated method stub

	}

	/**
	 * Add entry to zip.
	 *
	 * @param text the text
	 * @param output the output
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
