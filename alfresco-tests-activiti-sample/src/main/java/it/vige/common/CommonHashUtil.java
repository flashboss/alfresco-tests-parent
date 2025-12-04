package it.vige.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CommonHashUtil implementation for testing purposes.
 *
 * @author vige
 */
public class CommonHashUtil {

	/** The logger. */
	private static Log logger = LogFactory.getLog(CommonHashUtil.class);
	/** The hash type. */
	private String hashType;
	/** The buffer size. */
	private static final int BUFFER_SIZE = 1 << 8;

	/** The node service. */
	private NodeService nodeService;
	/** The content service. */
	private ContentService contentService;

	/**
	 * Set hash.
	 *
	 * @param nodeRef the node ref
	 */
	public void setHash(NodeRef nodeRef) {
		ContentReader contentReader = contentService.getReader(nodeRef, ContentModel.PROP_CONTENT);
		if (contentReader == null || contentReader.getSize() == 0) {
			logger.error("Content is null or empty, removing aspect.");
			removeAspect(nodeRef);
			return;
		}
		InputStream contentStream = contentReader.getContentInputStream();
		String hashValue = computeHash(contentStream);
		if (hashValue == null) {
			removeAspect(nodeRef);
			return;
		}

		Map<QName, Serializable> hashPropeties = new HashMap<QName, Serializable>();
		hashPropeties.put(HashModel.PROP_HASH_TYPE, hashType);
		hashPropeties.put(HashModel.PROP_HASH_VALUE, hashValue);
		if (nodeService.hasAspect(nodeRef, HashModel.ASPECT_HASHABLE)) {
			nodeService.addAspect(nodeRef, HashModel.ASPECT_HASHABLE, hashPropeties);
		}

		nodeService.setProperty(nodeRef, HashModel.PROP_HASH_TYPE, hashPropeties.get(HashModel.PROP_HASH_TYPE));
		nodeService.setProperty(nodeRef, HashModel.PROP_HASH_VALUE, hashPropeties.get(HashModel.PROP_HASH_VALUE));
	}

	/**
	 * Remove aspect.
	 *
	 * @param nodeRef the node ref
	 */
	private void removeAspect(NodeRef nodeRef) {
		nodeService.removeAspect(nodeRef, HashModel.ASPECT_HASHABLE);
	}

	/**
	 * Compute hash.
	 *
	 * @param contentStream the content stream
	 * @return the result
	 */
	private String computeHash(InputStream contentStream) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(hashType);
		} catch (NoSuchAlgorithmException e) {
			logger.error("Unable to process algorith type: " + hashType);
			return null;
		}
		messageDigest.reset();
		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		try {
			while ((bytesRead = contentStream.read(buffer)) > -1) {
				messageDigest.update(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			logger.error("Unable to read content stream.", e);
			return null;
		} finally {
			try {
				contentStream.close();
			} catch (IOException e) {
			}
		}
		byte[] digest = messageDigest.digest();
		return convertByteArrayToHex(digest);
	}

	/**
	 * Convert byte array to hex.
	 *
	 * @param array the array
	 * @return the result
	 */
	private String convertByteArrayToHex(byte[] array) {
		StringBuffer hashValue = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			String hex = Integer.toHexString(0xFF & array[i]);
			if (hex.length() == 1) {
				hashValue.append('0');
			}
			hashValue.append(hex);
		}
		return hashValue.toString().toUpperCase();
	}

	/**
	 * Set hash type.
	 *
	 * @param hashType the hash type
	 */
	public void setHashType(String hashType) {
		this.hashType = hashType;
	}

	/**
	 * Set node service.
	 *
	 * @param nodeService the node service
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * Set content service.
	 *
	 * @param contentService the content service
	 */
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

}
