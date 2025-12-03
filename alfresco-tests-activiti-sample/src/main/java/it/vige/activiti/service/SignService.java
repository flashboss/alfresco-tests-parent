package it.vige.activiti.service;

import java.io.InputStream;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.io.IOUtils;

import it.vige.common.SignConstants;

/**
 * SignService implementation for testing purposes.
 *
 * @author vige
 */
public class SignService {

	/** The content service. */
	private ContentService contentService;
	/** The node service. */
	private NodeService nodeService;
	
	/**
	 * Sign c ad e s with time stamp.
	 *
	 * @param nodeRef the node ref
	 * @param username the username
	 * @param password the password
	 * @return the result
	 */
	public boolean signCAdESWithTimeStamp(NodeRef nodeRef, String username, String password) {
		boolean status = false;
		ContentReader reader = contentService.getReader(nodeRef, ContentModel.PROP_CONTENT);
		InputStream contentInputStream = null;
		InputStream signedInputStream = null;
		try {
			contentInputStream = reader.getContentInputStream();
			signedInputStream = contentInputStream;
			ContentWriter contentWriter = contentService.getWriter(nodeRef, ContentModel.PROP_CONTENT, Boolean.TRUE);
			contentWriter.setMimetype(SignConstants.P7M_MIMETYPE);
			contentWriter.putContent(signedInputStream);
			nodeService.setProperty(nodeRef, ContentModel.PROP_NAME,
					(String) nodeService.getProperty(nodeRef, ContentModel.PROP_NAME) + "."
							+ SignConstants.P7M_EXTENSION);
			status = true;
		} finally {
			IOUtils.closeQuietly(contentInputStream);
			IOUtils.closeQuietly(signedInputStream);
		}
		return status;
	}

	/**
	 * Set content service.
	 *
	 * @param contentService the content service
	 */
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	/**
	 * Set node service.
	 *
	 * @param nodeService the node service
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}