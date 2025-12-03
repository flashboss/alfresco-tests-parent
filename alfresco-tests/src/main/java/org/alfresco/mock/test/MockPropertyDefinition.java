package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.alfresco.repo.dictionary.Facetable;
import org.alfresco.repo.dictionary.IndexTokenisationMode;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of PropertyDefinition for testing purposes.
 * 
 * @author vige
 */
public class MockPropertyDefinition implements PropertyDefinition, Serializable {

	/** The name. */
	private QName name;

	/**
	 * Constructs a new mock property definition.
	 *
	 * @param name the name
	 * @return the result
	 */
	public MockPropertyDefinition(QName name) {
		this.name = name;
	}

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
		return name;
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
	 * Get default value.
	 *
	 * @return the string
	 */
	public String getDefaultValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get data type.
	 *
	 * @return the data type definition
	 */
	public DataTypeDefinition getDataType() {
		return new MockDataTypeDefinition();
	}

	@Override
	/**
	 * Get container class.
	 *
	 * @return the class definition
	 */
	public ClassDefinition getContainerClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Is override.
	 *
	 * @return the boolean
	 */
	public boolean isOverride() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Is multi valued.
	 *
	 * @return the boolean
	 */
	public boolean isMultiValued() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Is mandatory.
	 *
	 * @return the boolean
	 */
	public boolean isMandatory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Is mandatory enforced.
	 *
	 * @return the boolean
	 */
	public boolean isMandatoryEnforced() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Is protected.
	 *
	 * @return the boolean
	 */
	public boolean isProtected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Is indexed.
	 *
	 * @return the boolean
	 */
	public boolean isIndexed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Is stored in index.
	 *
	 * @return the boolean
	 */
	public boolean isStoredInIndex() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get index tokenisation mode.
	 *
	 * @return the index tokenisation mode
	 */
	public IndexTokenisationMode getIndexTokenisationMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Is indexed atomically.
	 *
	 * @return the boolean
	 */
	public boolean isIndexedAtomically() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get constraints.
	 *
	 * @return the list
	 */
	public List<ConstraintDefinition> getConstraints() {
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

	@Override
	/**
	 * Get title.
	 *
	 * @param messageLookup the message lookup
	 * @param locale the locale
	 * @return the string
	 */
	public String getTitle(MessageLookup messageLookup, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get description.
	 *
	 * @param messageLookup the message lookup
	 * @param locale the locale
	 * @return the string
	 */
	public String getDescription(MessageLookup messageLookup, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get facetable.
	 *
	 * @return the facetable
	 */
	public Facetable getFacetable() {
		// TODO Auto-generated method stub
		return null;
	}

}
