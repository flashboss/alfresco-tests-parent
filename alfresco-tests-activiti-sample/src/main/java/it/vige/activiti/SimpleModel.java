package it.vige.activiti;

import org.alfresco.service.namespace.QName;

/**
 * Model constants for simple Activiti workflow content model.
 * Defines the namespace URI and QNames for properties used in the workflow.
 * 
 * @author vige
 */
public interface SimpleModel {

	/** The namespace URI for the conservation content model. */
	static final String STARTING_URI = "http://www.my.it/model/content/conservation/1.0";

	/** QName for the RaR ID counter property. */
	static final QName PROP_RAR_ID_COUNTER = QName.createQName(STARTING_URI, "rarIdCounter");
}