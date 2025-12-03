package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Locale;

import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of DataTypeDefinition for testing purposes.
 * Provides data type definition stub.
 * 
 * @author vige
 */
public class MockDataTypeDefinition implements DataTypeDefinition, Serializable {

	@Override
	public ModelDefinition getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QName getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAnalyserResourceBundleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJavaClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultAnalyserClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resolveAnalyserClassName(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resolveAnalyserClassName() {
		// TODO Auto-generated method stub
		return null;
	}

}
