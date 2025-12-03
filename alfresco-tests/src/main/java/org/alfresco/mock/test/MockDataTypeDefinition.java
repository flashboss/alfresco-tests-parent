package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Locale;

import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of DataTypeDefinition for testing purposes.
 * Provides a simple data type definition stub.
 *
 * @author lucastancapiano
 */
public class MockDataTypeDefinition implements DataTypeDefinition, Serializable {

	/**
	 * {@inheritDoc}
	 *
	 * @return the model definition
	 */
	@Override
	public ModelDefinition getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the data type name
	 */
	@Override
	public QName getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the title
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the description
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the analyser resource bundle name
	 */
	@Override
	public String getAnalyserResourceBundleName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the Java class name
	 */
	@Override
	public String getJavaClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the default analyser class name
	 */
	@Override
	public String getDefaultAnalyserClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param locale the locale
	 * @return the analyser class name for the locale
	 */
	@Override
	public String resolveAnalyserClassName(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the resolved analyser class name
	 */
	@Override
	public String resolveAnalyserClassName() {
		// TODO Auto-generated method stub
		return null;
	}

}
