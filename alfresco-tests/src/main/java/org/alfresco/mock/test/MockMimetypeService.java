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
 * Mock implementation of MockMimetypeService for testing purposes.
 *
 * @author vige
 */
public class MockMimetypeService implements MimetypeService, Serializable {

  /**
   * Get extension.
   *
   * @param mimetype the mimetype
   * @return the result
   */
  @Override
  public String getExtension(String mimetype) {
    if (mimetype.indexOf(".") >= 0) return mimetype.substring(mimetype.lastIndexOf(".") + 1);
    else return null;
  }

  /**
   * Get mimetype.
   *
   * @param extension the extension
   * @return the result
   */
  @Override
  public String getMimetype(String extension) {
    if (extension != null && extension.equals("acp")) return MimetypeMap.MIMETYPE_ACP;
    else if (extension != null && extension.equals("zip")) return MimetypeMap.MIMETYPE_ZIP;
    else if (extension != null && extension.equals("pdf")) return MimetypeMap.MIMETYPE_PDF;
    else if (extension != null && extension.equals("txt")) return MimetypeMap.MIMETYPE_TEXT_PLAIN;
    else if (extension != null && extension.equals("p7m")) return "application/x-pkcs7-mime";
    else return null;
  }

  /** Get displays by extension. */
  @Override
  public Map<String, String> getDisplaysByExtension() {
    // TODO Auto-generated method stub
    return null;
  }

  /** Get displays by mimetype. */
  @Override
  public Map<String, String> getDisplaysByMimetype() {
    // TODO Auto-generated method stub
    return null;
  }

  /** Get extensions by mimetype. */
  @Override
  public Map<String, String> getExtensionsByMimetype() {
    // TODO Auto-generated method stub
    return null;
  }

  /** Get mimetypes by extension. */
  @Override
  public Map<String, String> getMimetypesByExtension() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Is text.
   *
   * @param mimetype the mimetype
   * @return the result
   */
  @Override
  public boolean isText(String mimetype) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Get mimetypes.
   *
   * @return the result
   */
  @Override
  public List<String> getMimetypes() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Guess mimetype.
   *
   * @param filename the filename
   * @return the result
   */
  @Override
  public String guessMimetype(String filename) {
    String extension = null;
    if (filename != null && filename.contains("."))
      extension = filename.substring(filename.lastIndexOf(".") + 1);
    return getMimetype(extension);
  }

  /**
   * Guess mimetype.
   *
   * @param filename the filename
   * @param reader the reader
   * @return the result
   */
  @Override
  public String guessMimetype(String filename, ContentReader reader) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Guess mimetype.
   *
   * @param filename the filename
   * @param input the input
   * @return the result
   */
  @Override
  public String guessMimetype(String filename, InputStream input) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get mimetype if not matches.
   *
   * @param reader the reader
   * @return the result
   */
  @Override
  public String getMimetypeIfNotMatches(ContentReader reader) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get content charset finder.
   *
   * @return the result
   */
  @Override
  public ContentCharsetFinder getContentCharsetFinder() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get mimetypes.
   *
   * @param extension the extension
   * @return the result
   */
  @Override
  public Collection<String> getMimetypes(String extension) {
    // TODO Auto-generated method stub
    return null;
  }
}
