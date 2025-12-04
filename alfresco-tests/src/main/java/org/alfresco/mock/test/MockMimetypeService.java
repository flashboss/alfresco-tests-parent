package org.alfresco.mock.test;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.repo.content.encoding.ContentCharsetFinder;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.MimetypeService;

/**
 * Mock implementation of the Alfresco MimetypeService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockMimetypeService implements MimetypeService, Serializable {

	@Override
 /**
 * Get extension.
 *
 * @param mimetype the mimetype
 * @return the string
 */
	public String getExtension(String mimetype) {
		if (mimetype.indexOf(".") >= 0)
			return mimetype.substring(mimetype.lastIndexOf(".") + 1);
		else
			return null;
	}

	@Override
 /**
 * Get mimetype.
 *
 * @param extension the extension
 * @return the string
 */
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

	@Override
 /** Get displays by extension. */
	public Map<String, String> getDisplaysByExtension() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /** Get displays by mimetype. */
	public Map<String, String> getDisplaysByMimetype() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /** Get extensions by mimetype. */
	public Map<String, String> getExtensionsByMimetype() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /** Get mimetypes by extension. */
	public Map<String, String> getMimetypesByExtension() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Is text.
 *
 * @param mimetype the mimetype
 * @return the boolean
 */
	public boolean isText(String mimetype) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Get mimetypes.
 *
 * @return the list
 */
	public List<String> getMimetypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Guess mimetype.
 *
 * @param filename the filename
 * @return the string
 */
	public String guessMimetype(String filename) {
		String extension = null;
  /** The extension. */
		if (filename != null && filename.contains("."))
   /** The extension. */
			extension = filename.substring(filename.lastIndexOf(".") + 1);
  /** The extension. */
		return getMimetype(extension);
	}

	@Override
 /**
 * Guess mimetype.
 *
 * @param filename the filename
 * @param reader the reader
 * @return the string
 */
	public String guessMimetype(String filename, ContentReader reader) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Guess mimetype.
 *
 * @param filename the filename
 * @param input the input
 * @return the string
 */
	public String guessMimetype(String filename, InputStream input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get mimetype if not matches.
 *
 * @param reader the reader
 * @return the string
 */
	public String getMimetypeIfNotMatches(ContentReader reader) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get content charset finder.
 *
 * @return the content charset finder
 */
	public ContentCharsetFinder getContentCharsetFinder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get mimetypes.
 *
 * @param extension the extension
 * @return the collection
 */
	public Collection<String> getMimetypes(String extension) {
		// TODO Auto-generated method stub
		return null;
	}

}
