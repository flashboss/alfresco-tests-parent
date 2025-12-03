package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of MockDictionaryService for testing purposes.
 *
 * @author vige
 */
public class MockDictionaryService implements DictionaryService, Serializable {

	/**
	 * Get message.
	 *
	 * @param messageKey the message key
	 * @return the result
	 */
	@Override
	public String getMessage(String messageKey) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get message.
	 *
	 * @param messageKey the message key
	 * @param locale the locale
	 * @return the result
	 */
	@Override
	public String getMessage(String messageKey, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get message.
	 *
	 * @param messageKey the message key
	 * @param params the params
	 * @return the result
	 */
	@Override
	public String getMessage(String messageKey, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get message.
	 *
	 * @param messageKey the message key
	 * @param locale the locale
	 * @param params the params
	 * @return the result
	 */
	@Override
	public String getMessage(String messageKey, Locale locale, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get all models.
	 *
	 * @return the result
	 */
	@Override
	public Collection<QName> getAllModels() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get model.
	 *
	 * @param model the model
	 * @return the result
	 */
	@Override
	public ModelDefinition getModel(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get all data types.
	 *
	 * @return the result
	 */
	@Override
	public Collection<QName> getAllDataTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get data types.
	 *
	 * @param model the model
	 * @return the result
	 */
	@Override
	public Collection<QName> getDataTypes(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get data type.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public DataTypeDefinition getDataType(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get data type.
	 *
	 * @param javaClass the java class
	 * @return the result
	 */
	@Override
	public DataTypeDefinition getDataType(Class<?> javaClass) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get all types.
	 *
	 * @return the result
	 */
	@Override
	public Collection<QName> getAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get sub types.
	 *
	 * @param type the type
	 * @param follow the follow
	 * @return the result
	 */
	@Override
	public Collection<QName> getSubTypes(QName type, boolean follow) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get types.
	 *
	 * @param model the model
	 * @return the result
	 */
	@Override
	public Collection<QName> getTypes(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get type.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public TypeDefinition getType(QName name) {
		return new MockTypeDefinition(name);
	}

	/**
	 * Get anonymous type.
	 *
	 * @param type the type
	 * @param aspects the aspects
	 * @return the result
	 */
	@Override
	public TypeDefinition getAnonymousType(QName type, Collection<QName> aspects) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get anonymous type.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public TypeDefinition getAnonymousType(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get all aspects.
	 *
	 * @return the result
	 */
	@Override
	public Collection<QName> getAllAspects() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get sub aspects.
	 *
	 * @param aspect the aspect
	 * @param follow the follow
	 * @return the result
	 */
	@Override
	public Collection<QName> getSubAspects(QName aspect, boolean follow) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get aspects.
	 *
	 * @param model the model
	 * @return the result
	 */
	@Override
	public Collection<QName> getAspects(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get associations.
	 *
	 * @param model the model
	 * @return the result
	 */
	@Override
	public Collection<QName> getAssociations(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get aspect.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public AspectDefinition getAspect(QName name) {
		return new MockAspectDefinition(name);
	}

	/**
	 * Get class.
	 *
	 * @param name the name
	 * @return the result
	 */
	@Override
	public ClassDefinition getClass(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Is sub class.
	 *
	 * @param className the class name
	 * @param ofClassName the of class name
	 * @return the result
	 */
	@Override
	public boolean isSubClass(QName className, QName ofClassName) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Get property.
	 *
	 * @param className the class name
	 * @param propertyName the property name
	 * @return the result
	 */
	@Override
	public PropertyDefinition getProperty(QName className, QName propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get property defs.
	 *
	 * @param className the class name
	 */
	@Override
	public Map<QName, PropertyDefinition> getPropertyDefs(QName className) {
		Map<QName, PropertyDefinition> propertyDefs = new HashMap<QName, PropertyDefinition>();
		propertyDefs.put(className, new MockPropertyDefinition(className));
		return propertyDefs;
	}

	/**
	 * Get property.
	 *
	 * @param propertyName the property name
	 * @return the result
	 */
	@Override
	public PropertyDefinition getProperty(QName propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get all properties.
	 *
	 * @param dataType the data type
	 * @return the result
	 */
	@Override
	public Collection<QName> getAllProperties(QName dataType) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get properties.
	 *
	 * @param model the model
	 * @param dataType the data type
	 * @return the result
	 */
	@Override
	public Collection<QName> getProperties(QName model, QName dataType) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get properties.
	 *
	 * @param model the model
	 * @return the result
	 */
	@Override
	public Collection<QName> getProperties(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get association.
	 *
	 * @param associationName the association name
	 * @return the result
	 */
	@Override
	public AssociationDefinition getAssociation(QName associationName) {
		return new MockAssociationDefinition();
	}

	/**
	 * Get all associations.
	 *
	 * @return the result
	 */
	@Override
	public Collection<QName> getAllAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get constraint.
	 *
	 * @param constraintQName the constraint q name
	 * @return the result
	 */
	@Override
	public ConstraintDefinition getConstraint(QName constraintQName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get constraints.
	 *
	 * @param model the model
	 * @return the result
	 */
	@Override
	public Collection<ConstraintDefinition> getConstraints(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get constraints.
	 *
	 * @param model the model
	 * @param referenceableDefsOnly the referenceable defs only
	 * @return the result
	 */
	@Override
	public Collection<ConstraintDefinition> getConstraints(QName model, boolean referenceableDefsOnly) {
		// TODO Auto-generated method stub
		return null;
	}

}
