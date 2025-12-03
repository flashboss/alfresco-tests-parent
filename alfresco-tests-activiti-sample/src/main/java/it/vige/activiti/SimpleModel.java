package it.vige.activiti;

import org.alfresco.service.namespace.QName;

/**
 * @author vige
 */
public interface SimpleModel {
	static final String STARTING_URI = "http://www.my.it/model/content/conservation/1.0";
	static final QName PROP_RAR_ID_COUNTER = QName.createQName(STARTING_URI, "rarIdCounter");
}