package org.alfresco.mock.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Locale;

import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.service.cmr.repository.ContentData;
import org.alfresco.service.cmr.repository.ContentIOException;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentStreamListener;
import org.apache.cxf.helpers.FileUtils;

public class MockContentReader implements ContentReader {

	private File file;

	private String mimetype;

	public MockContentReader(File file) {
		this.file = file;
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
	}

	@Override
	public String getEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEncoding(String encoding) {
		// TODO Auto-generated method stub

	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocale(Locale locale) {
		// TODO Auto-generated method stub

	}

	@Override
	public ContentReader getReader() throws ContentIOException {
		return this;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getLastModified() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReadableByteChannel getReadableChannel() throws ContentIOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileChannel getFileChannel() throws ContentIOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getContentInputStream() throws ContentIOException {
		try {
			return new FileInputStream(file);
		} catch (java.io.FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void getContent(OutputStream os) throws ContentIOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getContent(File file) throws ContentIOException {
		try {
			if (mimetype != null
					&& (mimetype.equals(MimetypeMap.MIMETYPE_ACP) || mimetype.equals(MimetypeMap.MIMETYPE_ZIP))) {
				ZipUtils.unzip(new FileInputStream(this.file), ZipUtils.TEMP_DIR);
				String entryContent = ZipUtils.getZipEntryContent(ZipUtils.TEMP_DIR);
				ZipUtils.addEntryToZip(entryContent, ZipUtils.TEMP_DIR.listFiles()[0].getName(),
						new FileOutputStream(file));
			}
		} catch (IOException ex) {
			throw new ContentIOException(ex.getMessage(), ex);
		}
	}

	@Override
	public String getContentString() throws ContentIOException {
		return FileUtils.getStringFromFile(file);
	}

	@Override
	public String getContentString(int length) throws ContentIOException {
		// TODO Auto-generated method stub
		return null;
	}

}
