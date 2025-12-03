package org.alfresco.mock.test.activiti;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Mock implementation of Initiator for testing purposes.
 *
 * @author vige
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