package it.vige.common;

import org.alfresco.service.namespace.QName;

/**
 * Model constants for hash-related content model properties.
 * Defines QNames for hash aspect and properties.
 * 
 * @author vige
 */
public interface HashModel {

	/** The namespace URI for the hash content model. */
	static final String VIGE_HASH_URI = "http://www.vige.it/model/content/hash/1.0";

	/** QName for the hashable aspect. */
	static final QName ASPECT_HASHABLE = QName.createQName(VIGE_HASH_URI, "hashable");

	/** QName for the hash type property (algorithm name). */
	static final QName PROP_HASH_TYPE = QName.createQName(VIGE_HASH_URI, "hashType");

	/** QName for the hash value property (computed digest). */
	static final QName PROP_HASH_VALUE = QName.createQName(VIGE_HASH_URI, "hashValue");
}