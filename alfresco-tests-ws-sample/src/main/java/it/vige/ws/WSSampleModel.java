package it.vige.ws;

import org.alfresco.service.namespace.QName;

/**
 * Model constants defining QNames and namespace URIs.
 *
 * @author vige
 */
public interface WSSampleModel {

  String PBPDV_NAMESPACE = "http://www.vige.it/pb/conservation/model/1.0";
  QName ASPECT_WSSAMPLEFOLDER = QName.createQName(PBPDV_NAMESPACE, "wssamplefolder");
  QName PROP_UPDATE_PROPERTY = QName.createQName(PBPDV_NAMESPACE, "update_property_date");
}
