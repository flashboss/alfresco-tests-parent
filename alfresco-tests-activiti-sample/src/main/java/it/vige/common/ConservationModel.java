package it.vige.common;

import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of the ConservationModel interface for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
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