package it.vige.common;

import org.alfresco.service.namespace.QName;

/**
 * ConservationModel implementation for testing purposes.
 *
 * @author vige
 */
public interface ConservationModel {
	static final String VIGE_CONSERVATION_URI = "http://www.vige.it/model/content/conservation/1.0";
	static final QName ASPECT_HASHABLE = QName.createQName(VIGE_CONSERVATION_URI, "hashable");
	static final QName TYPE_IRAR = QName.createQName(VIGE_CONSERVATION_URI, "IRaR");
	static final QName TYPE_RAR = QName.createQName(VIGE_CONSERVATION_URI, "RaR");
	static final QName PROP_RAR_ID = QName.createQName(VIGE_CONSERVATION_URI, "rarId");
	static final QName PROP_RAR_ID_COUNTER = QName.createQName(VIGE_CONSERVATION_URI, "rarIdCounter");
	static final QName PROP_IRAR_ID = QName.createQName(VIGE_CONSERVATION_URI, "irarId");
}