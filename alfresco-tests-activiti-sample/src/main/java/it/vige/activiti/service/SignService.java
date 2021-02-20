package it.vige.activiti.service;

import java.io.InputStream;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;

import it.vige.common.SignConstants;

public class SignService {

	private ContentService contentService;
	private NodeService nodeService;

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

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}