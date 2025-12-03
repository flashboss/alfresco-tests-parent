package it.vige.ws;

import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of the WSSampleModel interface for testing purposes. This class provides a
 * mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public interface WSSampleModel {

  String PBPDV_NAMESPACE = "http://www.vige.it/pb/conservation/model/1.0";
  QName ASPECT_WSSAMPLEFOLDER = QName.createQName(PBPDV_NAMESPACE, "wssamplefolder");
  QName PROP_UPDATE_PROPERTY = QName.createQName(PBPDV_NAMESPACE, "update_property_date");
}
