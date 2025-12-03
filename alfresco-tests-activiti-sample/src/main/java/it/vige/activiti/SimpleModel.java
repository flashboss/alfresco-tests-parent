package it.vige.activiti;

import org.alfresco.service.namespace.QName;

/**
* Mock implementation of the SimpleModel interface for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public interface SimpleModel {
	static final String STARTING_URI = "http://www.my.it/model/content/conservation/1.0";
	static final QName PROP_RAR_ID_COUNTER = QName.createQName(STARTING_URI, "rarIdCounter");
}