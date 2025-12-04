package org.alfresco.mock.test;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.alfresco.repo.content.filestore.FileContentReader;
import org.alfresco.repo.content.transform.ContentTransformer;
import org.alfresco.service.cmr.dictionary.InvalidTypeException;
import org.alfresco.service.cmr.repository.ContentIOException;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.InvalidNodeRefException;
import org.alfresco.service.cmr.repository.MimetypeService;
import org.alfresco.service.cmr.repository.NoTransformerException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.TransformationOptions;
import org.alfresco.service.namespace.QName;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mock implementation of MockContentService for testing purposes.
 *
 * @author vige
 */
public class MockContentService implements ContentService, Serializable {

  public static final String FOLDER_TEST = "./target/test-classes/";

  /** The node service. */
  @Autowired private NodeService nodeService;

  /** The mimetype service. */
  @Autowired private MimetypeService mimetypeService;

  /**
   * Get store total space.
   *
   * @return the result
   */
  @Override
  public long getStoreTotalSpace() {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * Get store free space.
   *
   * @return the result
   */
  @Override
  public long getStoreFreeSpace() {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * Get raw reader.
   *
   * @param contentUrl the content url
   * @return the result
   */
  @Override
  public ContentReader getRawReader(String contentUrl) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get reader.
   *
   * @param nodeRef the node ref
   * @param propertyQName the property q name
   * @return the result
   */
  @Override
  public ContentReader getReader(NodeRef nodeRef, QName propertyQName)
      throws InvalidNodeRefException, InvalidTypeException {
    File file = getNodeService().getNodeRefs().get(nodeRef);
    File content = new File(file.getAbsolutePath() + "/" + file.getName());
    ContentReader contentReader = new FileContentReader(content);
    contentReader.setMimetype(mimetypeService.guessMimetype(file.getName()));
    return contentReader;
  }

  /**
   * Get writer.
   *
   * @param nodeRef the node ref
   * @param propertyQName the property q name
   * @param update the update
   * @return the result
   */
  @Override
  public ContentWriter getWriter(NodeRef nodeRef, QName propertyQName, boolean update)
      throws InvalidNodeRefException, InvalidTypeException {
    File file = getNodeService().getNodeRefs().get(nodeRef);
    return new MockContentWriter(file, nodeRef, nodeService);
  }

  /**
   * Get temp writer.
   *
   * @return the result
   */
  @Override
  public ContentWriter getTempWriter() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Transform.
   *
   * @param reader the reader
   * @param writer the writer
   */
  @Override
  public void transform(ContentReader reader, ContentWriter writer)
      throws NoTransformerException, ContentIOException {
    // TODO Auto-generated method stub

  }

  /**
   * Transform.
   *
   * @param reader the reader
   * @param writer the writer
   * @param options the options
   */
  @Override
  public void transform(ContentReader reader, ContentWriter writer, Map<String, Object> options)
      throws NoTransformerException, ContentIOException {
    // TODO Auto-generated method stub

  }

  /**
   * Transform.
   *
   * @param reader the reader
   * @param writer the writer
   * @param options the options
   */
  @Override
  public void transform(ContentReader reader, ContentWriter writer, TransformationOptions options)
      throws NoTransformerException, ContentIOException {
    // TODO Auto-generated method stub

  }

  /**
   * Get transformer.
   *
   * @param sourceMimetype the source mimetype
   * @param targetMimetype the target mimetype
   * @return the result
   */
  @Override
  public ContentTransformer getTransformer(String sourceMimetype, String targetMimetype) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get transformers.
   *
   * @param sourceUrl the source url
   * @param sourceMimetype the source mimetype
   * @param sourceSize the source size
   * @param targetMimetype the target mimetype
   * @param options the options
   * @return the result
   */
  @Override
  public List<ContentTransformer> getTransformers(
      String sourceUrl,
      String sourceMimetype,
      long sourceSize,
      String targetMimetype,
      TransformationOptions options) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get transformer.
   *
   * @param sourceUrl the source url
   * @param sourceMimetype the source mimetype
   * @param sourceSize the source size
   * @param targetMimetype the target mimetype
   * @param options the options
   * @return the result
   */
  @Override
  public ContentTransformer getTransformer(
      String sourceUrl,
      String sourceMimetype,
      long sourceSize,
      String targetMimetype,
      TransformationOptions options) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get transformer.
   *
   * @param sourceMimetype the source mimetype
   * @param targetMimetype the target mimetype
   * @param options the options
   * @return the result
   */
  @Override
  public ContentTransformer getTransformer(
      String sourceMimetype, String targetMimetype, TransformationOptions options) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get max source size bytes.
   *
   * @param sourceMimetype the source mimetype
   * @param targetMimetype the target mimetype
   * @param options the options
   * @return the result
   */
  @Override
  public long getMaxSourceSizeBytes(
      String sourceMimetype, String targetMimetype, TransformationOptions options) {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * Get active transformers.
   *
   * @param sourceMimetype the source mimetype
   * @param sourceSize the source size
   * @param targetMimetype the target mimetype
   * @param options the options
   * @return the result
   */
  @Override
  public List<ContentTransformer> getActiveTransformers(
      String sourceMimetype,
      long sourceSize,
      String targetMimetype,
      TransformationOptions options) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get active transformers.
   *
   * @param sourceMimetype the source mimetype
   * @param targetMimetype the target mimetype
   * @param options the options
   * @return the result
   */
  @Override
  public List<ContentTransformer> getActiveTransformers(
      String sourceMimetype, String targetMimetype, TransformationOptions options) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get image transformer.
   *
   * @return the result
   */
  @Override
  public ContentTransformer getImageTransformer() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Is transformable.
   *
   * @param reader the reader
   * @param writer the writer
   * @return the result
   */
  @Override
  public boolean isTransformable(ContentReader reader, ContentWriter writer) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Is transformable.
   *
   * @param reader the reader
   * @param writer the writer
   * @param options the options
   * @return the result
   */
  @Override
  public boolean isTransformable(
      ContentReader reader, ContentWriter writer, TransformationOptions options) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Get node service.
   *
   * @return the result
   */
  public MockNodeService getNodeService() {
    return (MockNodeService) nodeService;
  }

  /**
   * Get mimetype service.
   *
   * @return the result
   */
  public MimetypeService getMimetypeService() {
    return mimetypeService;
  }

  /**
   * Set mimetype service.
   *
   * @param mimetypeService the mimetype service
   */
  public void setMimetypeService(MimetypeService mimetypeService) {
    this.mimetypeService = mimetypeService;
  }

  /**
   * Set node service.
   *
   * @param nodeService the node service
   */
  public void setNodeService(MockNodeService nodeService) {
    this.nodeService = nodeService;
  }
}
