package it.vige.common;

import org.alfresco.service.namespace.QName;

/**
 * @author vige
 */
public interface HashModel {
	static final String VIGE_HASH_URI = "http://www.vige.it/model/content/hash/1.0";
	static final QName ASPECT_HASHABLE = QName.createQName(VIGE_HASH_URI, "hashable");
	static final QName PROP_HASH_TYPE = QName.createQName(VIGE_HASH_URI, "hashType");
	static final QName PROP_HASH_VALUE = QName.createQName(VIGE_HASH_URI, "hashValue");
}