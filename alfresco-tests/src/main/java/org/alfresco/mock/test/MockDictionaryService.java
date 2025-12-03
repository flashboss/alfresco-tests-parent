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
 * Mock implementation of the Alfresco DictionaryService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockDictionaryService implements DictionaryService, Serializable {

	@Override
	/**
	 * Get message.
	 *
	 * @param messageKey the message key
	 * @return the string
	 */
	public String getMessage(String messageKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get message.
	 *
	 * @param messageKey the message key
	 * @param locale the locale
	 * @return the string
	 */
	public String getMessage(String messageKey, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get message.
	 *
	 * @param messageKey the message key
	 * @param params the params
	 * @return the string
	 */
	public String getMessage(String messageKey, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get message.
	 *
	 * @param messageKey the message key
	 * @param locale the locale
	 * @param params the params
	 * @return the string
	 */
	public String getMessage(String messageKey, Locale locale, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get all models.
	 *
	 * @return the collection
	 */
	public Collection<QName> getAllModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get model.
	 *
	 * @param model the model
	 * @return the model definition
	 */
	public ModelDefinition getModel(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get all data types.
	 *
	 * @return the collection
	 */
	public Collection<QName> getAllDataTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get data types.
	 *
	 * @param model the model
	 * @return the collection
	 */
	public Collection<QName> getDataTypes(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get data type.
	 *
	 * @param name the name
	 * @return the data type definition
	 */
	public DataTypeDefinition getDataType(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get data type.
	 *
	 * @param javaClass the java class
	 * @return the data type definition
	 */
	public DataTypeDefinition getDataType(Class<?> javaClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get all types.
	 *
	 * @return the collection
	 */
	public Collection<QName> getAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get sub types.
	 *
	 * @param type the type
	 * @param follow the follow
	 * @return the collection
	 */
	public Collection<QName> getSubTypes(QName type, boolean follow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get types.
	 *
	 * @param model the model
	 * @return the collection
	 */
	public Collection<QName> getTypes(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get type.
	 *
	 * @param name the name
	 * @return the type definition
	 */
	public TypeDefinition getType(QName name) {
		return new MockTypeDefinition(name);
	}

	@Override
	/**
	 * Get anonymous type.
	 *
	 * @param type the type
	 * @param aspects the aspects
	 * @return the type definition
	 */
	public TypeDefinition getAnonymousType(QName type, Collection<QName> aspects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get anonymous type.
	 *
	 * @param name the name
	 * @return the type definition
	 */
	public TypeDefinition getAnonymousType(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get all aspects.
	 *
	 * @return the collection
	 */
	public Collection<QName> getAllAspects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get sub aspects.
	 *
	 * @param aspect the aspect
	 * @param follow the follow
	 * @return the collection
	 */
	public Collection<QName> getSubAspects(QName aspect, boolean follow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get aspects.
	 *
	 * @param model the model
	 * @return the collection
	 */
	public Collection<QName> getAspects(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get associations.
	 *
	 * @param model the model
	 * @return the collection
	 */
	public Collection<QName> getAssociations(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get aspect.
	 *
	 * @param name the name
	 * @return the aspect definition
	 */
	public AspectDefinition getAspect(QName name) {
		return new MockAspectDefinition(name);
	}

	@Override
	/**
	 * Get class.
	 *
	 * @param name the name
	 * @return the class definition
	 */
	public ClassDefinition getClass(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Is sub class.
	 *
	 * @param className the class name
	 * @param ofClassName the of class name
	 * @return the boolean
	 */
	public boolean isSubClass(QName className, QName ofClassName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get property.
	 *
	 * @param className the class name
	 * @param propertyName the property name
	 * @return the property definition
	 */
	public PropertyDefinition getProperty(QName className, QName propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get property defs.
	 *
	 * @param className the class name
	 */
	public Map<QName, PropertyDefinition> getPropertyDefs(QName className) {
		Map<QName, PropertyDefinition> propertyDefs = new HashMap<QName, PropertyDefinition>();
		propertyDefs.put(className, new MockPropertyDefinition(className));
		return propertyDefs;
	}

	@Override
	/**
	 * Get property.
	 *
	 * @param propertyName the property name
	 * @return the property definition
	 */
	public PropertyDefinition getProperty(QName propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get all properties.
	 *
	 * @param dataType the data type
	 * @return the collection
	 */
	public Collection<QName> getAllProperties(QName dataType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get properties.
	 *
	 * @param model the model
	 * @param dataType the data type
	 * @return the collection
	 */
	public Collection<QName> getProperties(QName model, QName dataType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get properties.
	 *
	 * @param model the model
	 * @return the collection
	 */
	public Collection<QName> getProperties(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get association.
	 *
	 * @param associationName the association name
	 * @return the association definition
	 */
	public AssociationDefinition getAssociation(QName associationName) {
		return new MockAssociationDefinition();
	}

	@Override
	/**
	 * Get all associations.
	 *
	 * @return the collection
	 */
	public Collection<QName> getAllAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get constraint.
	 *
	 * @param constraintQName the constraint q name
	 * @return the constraint definition
	 */
	public ConstraintDefinition getConstraint(QName constraintQName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get constraints.
	 *
	 * @param model the model
	 * @return the collection
	 */
	public Collection<ConstraintDefinition> getConstraints(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get constraints.
	 *
	 * @param model the model
	 * @param referenceableDefsOnly the referenceable defs only
	 * @return the collection
	 */
	public Collection<ConstraintDefinition> getConstraints(QName model, boolean referenceableDefsOnly) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get all types.
	 *
	 * @param includeInherited the include inherited
	 * @return the collection
	 */
	public Collection<QName> getAllTypes(boolean includeInherited) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get all aspects.
	 *
	 * @param includeInherited the include inherited
	 * @return the collection
	 */
	public Collection<QName> getAllAspects(boolean includeInherited) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get all associations.
	 *
	 * @param includeInherited the include inherited
	 * @return the collection
	 */
	public Collection<QName> getAllAssociations(boolean includeInherited) {
		// TODO Auto-generated method stub
		return null;
	}

}
