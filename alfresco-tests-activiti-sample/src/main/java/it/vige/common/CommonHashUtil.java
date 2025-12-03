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
 * Utility class for computing and setting hash values on nodes.
 * Provides methods to calculate and store hash digests on content.
 * 
 * @author vige
 */
public class CommonHashUtil {

	/** Logger for this class. */
	private static Log logger = LogFactory.getLog(CommonHashUtil.class);

	/** The hash algorithm type (e.g., SHA-256). */
	private String hashType;

	/** Buffer size for reading content streams. */
	private static final int BUFFER_SIZE = 1 << 8;

	/** Service for node operations. */
	private NodeService nodeService;

	/** Service for content operations. */
	private ContentService contentService;

	/**
	 * Computes and sets the hash value on a node.
	 * If content is null or empty, removes the hashable aspect.
	 * 
	 * @param nodeRef the node reference to compute hash for
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
	 * Removes the hashable aspect from a node.
	 * 
	 * @param nodeRef the node reference to remove the aspect from
	 */
	private void removeAspect(NodeRef nodeRef) {
		nodeService.removeAspect(nodeRef, HashModel.ASPECT_HASHABLE);
	}

	/**
	 * Computes the hash value for a content stream.
	 * 
	 * @param contentStream the input stream to compute hash for
	 * @return the computed hash as a hex string, or null on error
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
	 * Converts a byte array to a hexadecimal string.
	 * 
	 * @param array the byte array to convert
	 * @return the hex string representation in uppercase
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
	 * Sets the hash algorithm type.
	 * 
	 * @param hashType the hash algorithm (e.g., "SHA-256")
	 */
	public void setHashType(String hashType) {
		this.hashType = hashType;
	}

	/**
	 * Sets the node service.
	 * 
	 * @param nodeService the node service to use
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * Sets the content service.
	 * 
	 * @param contentService the content service to use
	 */
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

}
