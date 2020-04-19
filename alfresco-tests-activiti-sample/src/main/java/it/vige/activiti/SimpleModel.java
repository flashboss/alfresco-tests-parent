package it.vige.activiti;

import org.alfresco.service.namespace.QName;

public interface SimpleModel {
	static final String STARTING_URI = "http://www.my.it/model/content/conservazione/1.0";
	static final QName PROP_PDA_ID_COUNTER = QName.createQName(STARTING_URI, "pdaIdCounter");
}