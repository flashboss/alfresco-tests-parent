package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

/**
* Mock implementation of the MockDataTypeDefinition class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockDataTypeDefinition implements DataTypeDefinition, Serializable {

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ModelDefinition getModel() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public QName getName() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param messageLookup the messageLookup
* @return the result
 */
	@Override
	public String getTitle(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param messageLookup the messageLookup
* @return the result
 */
	@Override
	public String getDescription(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getJavaClassName() {
		// TODO Auto-generated method stub
		return null;
	}

}
