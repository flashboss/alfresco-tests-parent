package it.vige.common;

import org.alfresco.service.namespace.QName;

/**
 * Model constants defining QNames and namespace URIs.
 * 
 * @author vige
 */
public interface ConservationModel {
	/**
	 * Model constants defining QNames and namespace URIs.
	 *
	 * @author vige
	 */
	static final String VIGE_CONSERVATION_URI = "http://www.vige.it/model/content/conservation/1.0";
	/**
	 * Model constants defining QNames and namespace URIs.
	 *
	 * @author vige
	 */
	static final QName ASPECT_HASHABLE = QName.createQName(VIGE_CONSERVATION_URI, "hashable");
	/**
	 * Model constants defining QNames and namespace URIs.
	 *
	 * @author vige
	 */
	static final QName TYPE_IRAR = QName.createQName(VIGE_CONSERVATION_URI, "IRaR");
	/**
	 * Model constants defining QNames and namespace URIs.
	 *
	 * @author vige
	 */
	static final QName TYPE_RAR = QName.createQName(VIGE_CONSERVATION_URI, "RaR");
	/**
	 * Model constants defining QNames and namespace URIs.
	 *
	 * @author vige
	 */
	static final QName PROP_RAR_ID = QName.createQName(VIGE_CONSERVATION_URI, "rarId");
	/**
	 * Model constants defining QNames and namespace URIs.
	 *
	 * @author vige
	 */
	static final QName PROP_RAR_ID_COUNTER = QName.createQName(VIGE_CONSERVATION_URI, "rarIdCounter");
	/**
	 * Model constants defining QNames and namespace URIs.
	 *
	 * @author vige
	 */
	static final QName PROP_IRAR_ID = QName.createQName(VIGE_CONSERVATION_URI, "irarId");
}