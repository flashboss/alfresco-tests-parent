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
 * Service for digital signature operations.
 * Provides methods to sign documents using CAdES format with timestamps.
 * 
 * @author vige
 */
public class SignService {

	/** Service for content operations. */
	private ContentService contentService;

	/** Service for node operations. */
	private NodeService nodeService;
	
	/**
	 * Signs a document using CAdES format with timestamp.
	 * Reads the content, applies the signature, updates the content and filename.
	 * 
	 * @param nodeRef the node reference of the document to sign
	 * @param username the username for signature credentials
	 * @param password the password for signature credentials
	 * @return true if signing was successful, false otherwise
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
	 * Sets the content service.
	 * 
	 * @param contentService the content service to use
	 */
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	/**
	 * Sets the node service.
	 * 
	 * @param nodeService the node service to use
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}