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
 * Mock implementation of ContentService for testing purposes.
 * Provides file-based content storage using the file system
 * for reading and writing content associated with nodes.
 * 
 * @author vige
 */
public class MockContentService implements ContentService, Serializable {

	/** Base folder path for test content storage. */
	public final static String FOLDER_TEST = "./target/test-classes/";

	/** Node service for node operations. */
	@Autowired
	private NodeService nodeService;

	/** Mimetype service for content type detection. */
	@Autowired
	private MimetypeService mimetypeService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getStoreTotalSpace() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getStoreFreeSpace() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContentReader getRawReader(String contentUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public ContentWriter getWriter(NodeRef nodeRef, QName propertyQName, boolean update)
			throws InvalidNodeRefException, InvalidTypeException {
		File file = getNodeService().getNodeRefs().get(nodeRef);
		return new MockContentWriter(file, nodeRef, nodeService);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContentWriter getTempWriter() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void transform(ContentReader reader, ContentWriter writer)
			throws NoTransformerException, ContentIOException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void transform(ContentReader reader, ContentWriter writer, Map<String, Object> options)
			throws NoTransformerException, ContentIOException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void transform(ContentReader reader, ContentWriter writer, TransformationOptions options)
			throws NoTransformerException, ContentIOException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContentTransformer getTransformer(String sourceMimetype, String targetMimetype) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ContentTransformer> getTransformers(String sourceUrl, String sourceMimetype, long sourceSize,
			String targetMimetype, TransformationOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContentTransformer getTransformer(String sourceUrl, String sourceMimetype, long sourceSize,
			String targetMimetype, TransformationOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContentTransformer getTransformer(String sourceMimetype, String targetMimetype,
			TransformationOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getMaxSourceSizeBytes(String sourceMimetype, String targetMimetype, TransformationOptions options) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ContentTransformer> getActiveTransformers(String sourceMimetype, long sourceSize, String targetMimetype,
			TransformationOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ContentTransformer> getActiveTransformers(String sourceMimetype, String targetMimetype,
			TransformationOptions options) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContentTransformer getImageTransformer() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTransformable(ContentReader reader, ContentWriter writer) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTransformable(ContentReader reader, ContentWriter writer, TransformationOptions options) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Gets the mock node service.
	 * 
	 * @return the mock node service
	 */
	public MockNodeService getNodeService() {
		return (MockNodeService) nodeService;
	}

	/**
	 * Gets the mimetype service.
	 * 
	 * @return the mimetype service
	 */
	public MimetypeService getMimetypeService() {
		return mimetypeService;
	}

	/**
	 * Sets the mimetype service.
	 * 
	 * @param mimetypeService the mimetype service to set
	 */
	public void setMimetypeService(MimetypeService mimetypeService) {
		this.mimetypeService = mimetypeService;
	}

	/**
	 * Sets the mock node service.
	 * 
	 * @param nodeService the node service to set
	 */
	public void setNodeService(MockNodeService nodeService) {
		this.nodeService = nodeService;
	}

}
