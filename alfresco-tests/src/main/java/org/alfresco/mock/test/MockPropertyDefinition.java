package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.alfresco.repo.dictionary.IndexTokenisationMode;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of MockPropertyDefinition for testing purposes.
 *
 * @author vige
 */
public class MockPropertyDefinition implements PropertyDefinition, Serializable {

	/** The name. */
	private QName name;

	/**
	 * Constructs a new MockPropertyDefinition.
	 *
	 * @param name the name
	 */
	public MockPropertyDefinition(QName name) {
		this.name = name;
	}

	/**
	 * Get model.
	 *
	 * @return the result
	 */
	@Override
	public ModelDefinition getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get name.
	 *
	 * @return the result
	 */
	@Override
	public QName getName() {
		return name;
	}

	/**
	 * Get title.
	 *
	 * @return the result
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get description.
	 *
	 * @return the result
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get title.
	 *
	 * @param messageLookup the message lookup
	 * @return the result
	 */
	@Override
	public String getTitle(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get description.
	 *
	 * @param messageLookup the message lookup
	 * @return the result
	 */
	@Override
	public String getDescription(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get default value.
	 *
	 * @return the result
	 */
	@Override
	public String getDefaultValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get data type.
	 *
	 * @return the result
	 */
	@Override
	public DataTypeDefinition getDataType() {
		return new MockDataTypeDefinition();
	}

	/**
	 * Get container class.
	 *
	 * @return the result
	 */
	@Override
	public ClassDefinition getContainerClass() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Is override.
	 *
	 * @return the result
	 */
	@Override
	public boolean isOverride() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Is multi valued.
	 *
	 * @return the result
	 */
	@Override
	public boolean isMultiValued() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Is mandatory.
	 *
	 * @return the result
	 */
	@Override
	public boolean isMandatory() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Is mandatory enforced.
	 *
	 * @return the result
	 */
	@Override
	public boolean isMandatoryEnforced() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Is protected.
	 *
	 * @return the result
	 */
	@Override
	public boolean isProtected() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Is indexed.
	 *
	 * @return the result
	 */
	@Override
	public boolean isIndexed() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Is stored in index.
	 *
	 * @return the result
	 */
	@Override
	public boolean isStoredInIndex() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Get index tokenisation mode.
	 *
	 * @return the result
	 */
	@Override
	public IndexTokenisationMode getIndexTokenisationMode() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Is indexed atomically.
	 *
	 * @return the result
	 */
	@Override
	public boolean isIndexedAtomically() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Get constraints.
	 *
	 * @return the result
	 */
	@Override
	public List<ConstraintDefinition> getConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get analyser resource bundle name.
	 *
	 * @return the result
	 */
	@Override
	public String getAnalyserResourceBundleName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Resolve analyser class name.
	 *
	 * @param locale the locale
	 * @return the result
	 */
	@Override
	public String resolveAnalyserClassName(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Resolve analyser class name.
	 *
	 * @return the result
	 */
	@Override
	public String resolveAnalyserClassName() {
		// TODO Auto-generated method stub
		return null;
	}

}
