package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Locale;

import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of DataTypeDefinition for testing purposes.
 * 
 * @author vige
 */
public class MockDataTypeDefinition implements DataTypeDefinition, Serializable {

	@Override
	/**
	 * Get model.
	 *
	 * @return the model definition
	 */
	public ModelDefinition getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get name.
	 *
	 * @return the q name
	 */
	public QName getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get title.
	 *
	 * @return the string
	 */
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get description.
	 *
	 * @return the string
	 */
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get title.
	 *
	 * @param messageLookup the message lookup
	 * @return the string
	 */
	public String getTitle(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get description.
	 *
	 * @param messageLookup the message lookup
	 * @return the string
	 */
	public String getDescription(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get analyser resource bundle name.
	 *
	 * @return the string
	 */
	public String getAnalyserResourceBundleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get java class name.
	 *
	 * @return the string
	 */
	public String getJavaClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get default analyser class name.
	 *
	 * @return the string
	 */
	public String getDefaultAnalyserClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Resolve analyser class name.
	 *
	 * @param locale the locale
	 * @return the string
	 */
	public String resolveAnalyserClassName(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Resolve analyser class name.
	 *
	 * @return the string
	 */
	public String resolveAnalyserClassName() {
		// TODO Auto-generated method stub
		return null;
	}

}
