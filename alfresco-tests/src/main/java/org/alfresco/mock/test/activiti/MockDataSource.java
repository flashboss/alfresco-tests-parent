package org.alfresco.mock.test.activiti;

import javax.naming.NamingException;

import org.alfresco.config.JndiObjectFactoryBean;

public class MockDataSource extends JndiObjectFactoryBean {

	@Override
	public void afterPropertiesSet() throws IllegalArgumentException, NamingException {
	}

}
