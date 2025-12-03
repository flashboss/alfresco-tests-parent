package org.alfresco.mock.test.activiti;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Mock implementation of the Initiator class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class Initiator implements Serializable {

	private Map<String, String> properties = new HashMap<String, String>();

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}