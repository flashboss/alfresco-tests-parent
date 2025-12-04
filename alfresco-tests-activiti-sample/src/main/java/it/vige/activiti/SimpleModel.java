package it.vige.activiti;

import org.alfresco.service.namespace.QName;

/**
 * Model constants defining QNames and namespace URIs.
 * 
 * @author vige
 */
public interface SimpleModel {
 /** The starting uri. */
	static final String STARTING_URI = "http://www.my.it/model/content/conservation/1.0";
 /** The prop rar id counter. */
	static final QName PROP_RAR_ID_COUNTER = QName.createQName(STARTING_URI, "rarIdCounter");
}