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
	/**
	 * Get model.
	 *
	 * @return the model definition
	 */
	@Override
	public ModelDefinition getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get name.
	 *
	 * @return the q name
	 */
	@Override
	public QName getName() {
		return name;
	}
	/**
	 * Get title.
	 *
	 * @return the string
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get description.
	 *
	 * @return the string
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
	 * @return the string
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
	 * @return the string
	 */
	@Override
	public String getDescription(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get default value.
	 *
	 * @return the string
	 */
	@Override
	public String getDefaultValue() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get data type.
	 *
	 * @return the data type definition
	 */
	@Override
	public DataTypeDefinition getDataType() {
		return new MockDataTypeDefinition();
	}
	/**
	 * Get container class.
	 *
	 * @return the class definition
	 */
	@Override
	public ClassDefinition getContainerClass() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Is override.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean isOverride() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Is multi valued.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean isMultiValued() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Is mandatory.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean isMandatory() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Is mandatory enforced.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean isMandatoryEnforced() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Is protected.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean isProtected() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Is indexed.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean isIndexed() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Is stored in index.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean isStoredInIndex() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Get index tokenisation mode.
	 *
	 * @return the index tokenisation mode
	 */
	@Override
	public IndexTokenisationMode getIndexTokenisationMode() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Is indexed atomically.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean isIndexedAtomically() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Get constraints.
	 *
	 * @return the list
	 */
	@Override
	public List<ConstraintDefinition> getConstraints() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get analyser resource bundle name.
	 *
	 * @return the string
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
	 * @return the string
	 */
	@Override
	public String resolveAnalyserClassName(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Resolve analyser class name.
	 *
	 * @return the string
	 */
	@Override
	public String resolveAnalyserClassName() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get title.
	 *
	 * @param messageLookup the message lookup
	 * @param locale the locale
	 * @return the string
	 */
	@Override
	public String getTitle(MessageLookup messageLookup, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get description.
	 *
	 * @param messageLookup the message lookup
	 * @param locale the locale
	 * @return the string
	 */
	@Override
	public String getDescription(MessageLookup messageLookup, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get facetable.
	 *
	 * @return the facetable
	 */
	@Override
	public Facetable getFacetable() {
		// TODO Auto-generated method stub
		return null;
	}

}
