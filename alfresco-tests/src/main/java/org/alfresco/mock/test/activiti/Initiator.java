package org.alfresco.mock.test.activiti;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the initiator of an Activiti workflow process.
 * Contains properties for the workflow initiator user.
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