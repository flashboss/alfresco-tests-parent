package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.repo.content.encoding.ContentCharsetFinder;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.MimetypeService;

/**
 * Mock implementation of MimetypeService for testing purposes.
 * Provides basic mimetype resolution for common file types.
 *
 * @author lucastancapiano
 */
public class MockMimetypeService implements MimetypeService, Serializable {

	/**
	 * {@inheritDoc}
	 *
	 * @param mimetype the mimetype to get extension for
	 * @return the file extension for the mimetype
	 */
	@Override
	public String getExtension(String mimetype) {
		if (mimetype.indexOf(".") >= 0)
			return mimetype.substring(mimetype.lastIndexOf(".") + 1);
		else
			return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param extension the file extension
	 * @return the mimetype for the extension
	 */
	@Override
	public String getMimetype(String extension) {
		if (extension != null && extension.equals("acp"))
			return MimetypeMap.MIMETYPE_ACP;
		else if (extension != null && extension.equals("zip"))
			return MimetypeMap.MIMETYPE_ZIP;
		else if (extension != null && extension.equals("pdf"))
			return MimetypeMap.MIMETYPE_PDF;
		else if (extension != null && extension.equals("txt"))
			return MimetypeMap.MIMETYPE_TEXT_PLAIN;
		else if (extension != null && extension.equals("p7m"))
			return "application/x-pkcs7-mime";
		else
			return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the displays by extension map
	 */
	@Override
	public Map<String, String> getDisplaysByExtension() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the displays by mimetype map
	 */
	@Override
	public Map<String, String> getDisplaysByMimetype() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the extensions by mimetype map
	 */
	@Override
	public Map<String, String> getExtensionsByMimetype() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the mimetypes by extension map
	 */
	@Override
	public Map<String, String> getMimetypesByExtension() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param mimetype the mimetype to check
	 * @return true if text mimetype, false otherwise
	 */
	@Override
	public boolean isText(String mimetype) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the list of mimetypes
	 */
	@Override
	public List<String> getMimetypes() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param filename the filename to guess mimetype for
	 * @return the guessed mimetype
	 */
	@Override
	public String guessMimetype(String filename) {
		String extension = null;
		if (filename != null && filename.contains("."))
			extension = filename.substring(filename.lastIndexOf(".") + 1);
		return getMimetype(extension);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param filename the filename
	 * @param reader the content reader
	 * @return the guessed mimetype
	 */
	@Override
	public String guessMimetype(String filename, ContentReader reader) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param reader the content reader
	 * @return the mimetype if not matching, null otherwise
	 */
	@Override
	public String getMimetypeIfNotMatches(ContentReader reader) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the content charset finder
	 */
	@Override
	public ContentCharsetFinder getContentCharsetFinder() {
		// TODO Auto-generated method stub
		return null;
	}

}
