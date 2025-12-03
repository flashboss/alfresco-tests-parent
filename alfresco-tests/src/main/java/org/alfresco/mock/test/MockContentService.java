package org.alfresco.mock.test;

import java.io.File;
import java.io.Serializable;

import org.alfresco.repo.content.filestore.FileContentReader;
import org.alfresco.service.cmr.dictionary.InvalidTypeException;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.DirectAccessUrl;
import org.alfresco.service.cmr.repository.InvalidNodeRefException;
import org.alfresco.service.cmr.repository.MimetypeService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.springframework.beans.factory.annotation.Autowired;

/**
* Mock implementation of the MockContentService class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockContentService implements ContentService, Serializable {

/**
* The test folder path.
 */
	public final static String FOLDER_TEST = "./target/test-classes/";

	/**
	 * The node service.
	 */
	@Autowired
	private NodeService nodeService;

	/**
	 * The mimetype service.
	 */
	@Autowired
	private MimetypeService mimetypeService;

	/**
	 * {@inheritDoc}
	 *
	 * @return the result
	 */
	@Override
	public long getStoreTotalSpace() {
		// TODO Auto-generated method stub
		return 0;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public long getStoreFreeSpace() {
		// TODO Auto-generated method stub
		return 0;
	}

/**
* {@inheritDoc}
* @param contentUrl the contentUrl
* @return the result
 */
	@Override
	public ContentReader getRawReader(String contentUrl) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @param propertyQName the propertyQName
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
* {@inheritDoc}
* @param nodeRef the nodeRef
* @param propertyQName the propertyQName
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
* {@inheritDoc}
* @return the result
 */
	@Override
	public ContentWriter getTempWriter() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* Gets the node service.
*
* @return the node service
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
* @param mimetypeService the mimetype service
 */
	public void setMimetypeService(MimetypeService mimetypeService) {
		this.mimetypeService = mimetypeService;
	}

/**
* Sets the node service.
*
* @param nodeService the node service
 */
	public void setNodeService(MockNodeService nodeService) {
		this.nodeService = nodeService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public boolean isContentDirectUrlEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @return the result
 */
	@Override
	public boolean isContentDirectUrlEnabled(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @param attachment the attachment
* @param validFor the validFor
* @return the result
 */
	@Override
	public DirectAccessUrl requestContentDirectUrl(NodeRef nodeRef, boolean attachment, Long validFor) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @param propertyQName the propertyQName
* @return the result
 */
	@Override
	public boolean isContentDirectUrlEnabled(NodeRef nodeRef, QName propertyQName) {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
 */
	@Override
	public DirectAccessUrl requestContentDirectUrl(NodeRef nodeRef, QName propertyQName, boolean attachment,
			Long validFor) {
		// TODO Auto-generated method stub
		return null;
	}

}
