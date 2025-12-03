package it.vige.activiti.service;

import java.io.InputStream;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;

import it.vige.common.SignConstants;

/**
* Mock implementation of the SignService class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class SignService {

/**
* The content service.
 */
	private ContentService contentService;
/**
* The node service.
 */
	private NodeService nodeService;

/**
* Performs sign c ad es with time stamp.
* @param nodeRef the nodeRef
* @param username the username
* @param password the password
* @return the result
 */
	public boolean signCAdESWithTimeStamp(NodeRef nodeRef, String username, String password) {
		boolean status = false;
		ContentReader reader = contentService.getReader(nodeRef, ContentModel.PROP_CONTENT);
		InputStream signedInputStream = null;
		try (InputStream contentInputStream = reader.getContentInputStream()) {
			signedInputStream = contentInputStream;
			ContentWriter contentWriter = contentService.getWriter(nodeRef, ContentModel.PROP_CONTENT, Boolean.TRUE);
			contentWriter.setMimetype(SignConstants.P7M_MIMETYPE);
			contentWriter.putContent(signedInputStream);
			nodeService.setProperty(nodeRef, ContentModel.PROP_NAME,
					(String) nodeService.getProperty(nodeRef, ContentModel.PROP_NAME) + "."
							+ SignConstants.P7M_EXTENSION);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

/**
* Sets the content service.
* @param contentService the contentService
 */
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

/**
* Sets the node service.
* @param nodeService the nodeService
 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}