package it.vige.common;

import org.alfresco.service.namespace.QName;

/**
 * Model constants for conservation content model.
 * Defines QNames for IRaR and RaR types and properties.
 * 
 * @author vige
 */
public interface ConservationModel {

	/** The namespace URI for the conservation content model. */
	static final String VIGE_CONSERVATION_URI = "http://www.vige.it/model/content/conservation/1.0";

	/** QName for the hashable aspect. */
	static final QName ASPECT_HASHABLE = QName.createQName(VIGE_CONSERVATION_URI, "hashable");

	/** QName for the IRaR (Index of RaR) type. */
	static final QName TYPE_IRAR = QName.createQName(VIGE_CONSERVATION_URI, "IRaR");

	/** QName for the RaR (Rapporto di Archiviazione) type. */
	static final QName TYPE_RAR = QName.createQName(VIGE_CONSERVATION_URI, "RaR");

	/** QName for the RaR ID property. */
	static final QName PROP_RAR_ID = QName.createQName(VIGE_CONSERVATION_URI, "rarId");

	/** QName for the RaR ID counter property. */
	static final QName PROP_RAR_ID_COUNTER = QName.createQName(VIGE_CONSERVATION_URI, "rarIdCounter");

	/** QName for the IRaR ID property. */
	static final QName PROP_IRAR_ID = QName.createQName(VIGE_CONSERVATION_URI, "irarId");
}