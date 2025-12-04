package it.vige.common;

import org.alfresco.service.namespace.QName;

/**
 * Model constants defining QNames and namespace URIs.
 * 
 * @author vige
 */
public interface HashModel {
	/**
	 * Model constants defining QNames and namespace URIs.
	 *
	 * @author vige
	 */
	static final String VIGE_HASH_URI = "http://www.vige.it/model/content/hash/1.0";
	/**
	 * Model constants defining QNames and namespace URIs.
	 *
	 * @author vige
	 */
	static final QName ASPECT_HASHABLE = QName.createQName(VIGE_HASH_URI, "hashable");
	/**
	 * Model constants defining QNames and namespace URIs.
	 *
	 * @author vige
	 */
	static final QName PROP_HASH_TYPE = QName.createQName(VIGE_HASH_URI, "hashType");
	/**
	 * Model constants defining QNames and namespace URIs.
	 *
	 * @author vige
	 */
	static final QName PROP_HASH_VALUE = QName.createQName(VIGE_HASH_URI, "hashValue");
}