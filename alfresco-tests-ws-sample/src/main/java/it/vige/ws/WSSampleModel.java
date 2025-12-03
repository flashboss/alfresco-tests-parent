package it.vige.ws;

import org.alfresco.service.namespace.QName;

/**
 * Content model interface for WebService Sample.
 * Defines namespace and QNames for the WS Sample content model.
 *
 * @author lucastancapiano
 */
public interface WSSampleModel {

	/** The namespace URI for the WS Sample model. */
	String PBPDV_NAMESPACE = "http://www.vige.it/pb/conservation/model/1.0";

	/** The aspect QName for WS Sample folder. */
	QName ASPECT_WSSAMPLEFOLDER = QName.createQName(PBPDV_NAMESPACE, "wssamplefolder");

	/** The property QName for update date. */
	QName PROP_UPDATE_PROPERTY = QName.createQName(PBPDV_NAMESPACE, "update_property_date");

}
