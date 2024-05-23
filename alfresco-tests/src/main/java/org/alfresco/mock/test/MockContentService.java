package org.alfresco.mock.test;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

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

public class MockContentService implements ContentService, Serializable {

	public final static String FOLDER_TEST = "./target/test-classes/";

	@Autowired
	private NodeService nodeService;

	@Autowired
	private MimetypeService mimetypeService;

	@Override
	public long getStoreTotalSpace() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getStoreFreeSpace() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ContentReader getRawReader(String contentUrl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContentReader getReader(NodeRef nodeRef, QName propertyQName)
			throws InvalidNodeRefException, InvalidTypeException {
		File file = getNodeService().getNodeRefs().get(nodeRef);
		File content = new File(file.getAbsolutePath() + "/" + file.getName());
		ContentReader contentReader = new FileContentReader(content);
		contentReader.setMimetype(mimetypeService.guessMimetype(file.getName()));
		return contentReader;
	}

	@Override
	public ContentWriter getWriter(NodeRef nodeRef, QName propertyQName, boolean update)
			throws InvalidNodeRefException, InvalidTypeException {
		File file = getNodeService().getNodeRefs().get(nodeRef);
		return new MockContentWriter(file, nodeRef, nodeService);
	}

	@Override
	public ContentWriter getTempWriter() {
		// TODO Auto-generated method stub
		return null;
	}

	public MockNodeService getNodeService() {
		return (MockNodeService) nodeService;
	}

	public MimetypeService getMimetypeService() {
		return mimetypeService;
	}

	public void setMimetypeService(MimetypeService mimetypeService) {
		this.mimetypeService = mimetypeService;
	}

	public void setNodeService(MockNodeService nodeService) {
		this.nodeService = nodeService;
	}

	@Override
	public DirectAccessUrl getDirectAccessUrl(NodeRef nodeRef, Date expiresAt) {
		// TODO Auto-generated method stub
		return null;
	}

}
