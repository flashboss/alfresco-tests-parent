package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Collection;
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

public class MockDictionaryService implements DictionaryService, Serializable {

	@Override
	public String getMessage(String messageKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage(String messageKey, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage(String messageKey, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage(String messageKey, Locale locale, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAllModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelDefinition getModel(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAllDataTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getDataTypes(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTypeDefinition getDataType(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTypeDefinition getDataType(Class<?> javaClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getSubTypes(QName type, boolean follow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getTypes(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeDefinition getType(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeDefinition getAnonymousType(QName type, Collection<QName> aspects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeDefinition getAnonymousType(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAllAspects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getSubAspects(QName aspect, boolean follow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAspects(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAssociations(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AspectDefinition getAspect(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassDefinition getClass(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSubClass(QName className, QName ofClassName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PropertyDefinition getProperty(QName className, QName propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<QName, PropertyDefinition> getPropertyDefs(QName className) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyDefinition getProperty(QName propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAllProperties(QName dataType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getProperties(QName model, QName dataType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getProperties(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssociationDefinition getAssociation(QName associationName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAllAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintDefinition getConstraint(QName constraintQName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConstraintDefinition> getConstraints(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConstraintDefinition> getConstraints(QName model, boolean referenceableDefsOnly) {
		// TODO Auto-generated method stub
		return null;
	}

}
