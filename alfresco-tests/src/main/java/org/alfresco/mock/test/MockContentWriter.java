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
 * @author vige
 */
public class MockContentWriter implements ContentWriter {

	private File file;

	private String mimetype;

	private ContentDataWithId contentProp;

	private NodeRef node;

	private NodeService nodeService;

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
	public boolean isChannelOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addListener(ContentStreamListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ContentData getContentData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContentUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMimetype() {
		return mimetype;
	}

	@Override
	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
		contentProp = new ContentDataWithId(ContentDataWithId.setMimetype(contentProp, mimetype), contentProp.getId());
	}

	@Override
	public String getEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEncoding(String encoding) {
		contentProp = new ContentDataWithId(ContentDataWithId.setEncoding(contentProp, encoding), contentProp.getId());
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocale(Locale locale) {
		contentProp = new ContentDataWithId(new ContentData(contentProp.getContentUrl(), contentProp.getMimetype(),
				contentProp.getSize(), contentProp.getEncoding(), locale), Long.parseLong(node.getId()));

	}

	@Override
	public ContentReader getReader() throws ContentIOException {
		return new FileContentReader(file);
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WritableByteChannel getWritableChannel() throws ContentIOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileChannel getFileChannel(boolean truncate) throws ContentIOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputStream getContentOutputStream() throws ContentIOException {
		try (FileOutputStream fom = new FileOutputStream(file)) {
			return fom;
		} catch (IOException e) {
			throw new ContentIOException(e.getMessage(), e);
		}
	}

	@Override
	public void putContent(ContentReader reader) throws ContentIOException {
		putContent(reader.getContentInputStream());
	}

	@Override
	public void putContent(InputStream is) throws ContentIOException {
		try (FileOutputStream fom = new FileOutputStream(file)) {
			IOUtils.copy(is, fom);
			nodeService.setProperty(node, ContentModel.PROP_CONTENT, contentProp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void putContent(File file) throws ContentIOException {
		try (FileInputStream fim = new FileInputStream(file)) {
			putContent(fim);
		} catch (IOException e) {
			throw new ContentIOException(e.getMessage(), e);
		}
	}

	@Override
	public void putContent(String content) throws ContentIOException {
		putContent(new ByteArrayInputStream(content.getBytes()));
	}

	@Override
	public void guessMimetype(String filename) {
		// TODO Auto-generated method stub

	}

	@Override
	public void guessEncoding() {
		// TODO Auto-generated method stub

	}

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
