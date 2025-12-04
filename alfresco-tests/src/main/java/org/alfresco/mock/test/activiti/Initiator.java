package org.alfresco.mock.test.activiti;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public class Initiator implements Serializable {

 /** The properties. */
	private Map<String, String> properties = new HashMap<String, String>();

 /** Get properties. */
	public Map<String, String> getProperties() {
		return properties;
	}

 /**
 * Set properties.
 *
 * @param properties the properties
 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

}