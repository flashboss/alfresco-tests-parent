package it.vige.ws;

import org.alfresco.service.namespace.QName;

public interface WSSampleModel {

	String PBPDV_NAMESPACE = "http://www.mcc.it/pb/conservazione/model/1.0";
	QName ASPECT_WSSAMPLEFOLDER = QName.createQName(PBPDV_NAMESPACE, "wssamplefolder");
	QName PROP_DATA_CEDACRI = QName.createQName(PBPDV_NAMESPACE, "data_cedacri");

}
