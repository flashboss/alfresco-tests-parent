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
 * Mock implementation of the MockDictionaryService class for testing purposes. This class provides
 * a mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockDictionaryService implements DictionaryService, Serializable {

  /**
   * {@inheritDoc}
   *
   * @param messageKey the messageKey
   * @return the result
   */
  @Override
  public String getMessage(String messageKey) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param messageKey the messageKey
   * @param locale the locale
   * @return the result
   */
  @Override
  public String getMessage(String messageKey, Locale locale) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param messageKey the messageKey
   * @return the result
   */
  @Override
  public String getMessage(String messageKey, Object... params) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param messageKey the messageKey
   * @param locale the locale
   * @return the result
   */
  @Override
  public String getMessage(String messageKey, Locale locale, Object... params) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Collection<QName> getAllModels() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Collection<QName> getAllDataTypes() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
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
   * {@inheritDoc}
   *
   * @param javaClass the javaClass
   * @return the result
   */
  @Override
  public DataTypeDefinition getDataType(Class<?> javaClass) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Collection<QName> getAllTypes() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
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
   * {@inheritDoc}
   *
   * @param name the name
   * @return the result
   */
  @Override
  public TypeDefinition getType(QName name) {
    return new MockTypeDefinition(name);
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
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
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Collection<QName> getAllAspects() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
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
   * {@inheritDoc}
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
   * {@inheritDoc}
   *
   * @param name the name
   * @return the result
   */
  @Override
  public AspectDefinition getAspect(QName name) {
    return new MockAspectDefinition(name);
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
   *
   * @param className the className
   * @param ofClassName the ofClassName
   * @return the result
   */
  @Override
  public boolean isSubClass(QName className, QName ofClassName) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @param className the className
   * @param propertyName the propertyName
   * @return the result
   */
  @Override
  public PropertyDefinition getProperty(QName className, QName propertyName) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param className the className
   * @return the result
   */
  @Override
  public Map<QName, PropertyDefinition> getPropertyDefs(QName className) {
    Map<QName, PropertyDefinition> propertyDefs = new HashMap<QName, PropertyDefinition>();
    propertyDefs.put(className, new MockPropertyDefinition(className));
    return propertyDefs;
  }

  /**
   * {@inheritDoc}
   *
   * @param propertyName the propertyName
   * @return the result
   */
  @Override
  public PropertyDefinition getProperty(QName propertyName) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param dataType the dataType
   * @return the result
   */
  @Override
  public Collection<QName> getAllProperties(QName dataType) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param model the model
   * @param dataType the dataType
   * @return the result
   */
  @Override
  public Collection<QName> getProperties(QName model, QName dataType) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
   *
   * @param associationName the associationName
   * @return the result
   */
  @Override
  public AssociationDefinition getAssociation(QName associationName) {
    return new MockAssociationDefinition();
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Collection<QName> getAllAssociations() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param constraintQName the constraintQName
   * @return the result
   */
  @Override
  public ConstraintDefinition getConstraint(QName constraintQName) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
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
   * {@inheritDoc}
   *
   * @param model the model
   * @param referenceableDefsOnly the referenceableDefsOnly
   * @return the result
   */
  @Override
  public Collection<ConstraintDefinition> getConstraints(
      QName model, boolean referenceableDefsOnly) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param includeInherited the includeInherited
   * @return the result
   */
  @Override
  public Collection<QName> getAllTypes(boolean includeInherited) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param includeInherited the includeInherited
   * @return the result
   */
  @Override
  public Collection<QName> getAllAspects(boolean includeInherited) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param includeInherited the includeInherited
   * @return the result
   */
  @Override
  public Collection<QName> getAllAssociations(boolean includeInherited) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param uri the uri
   * @return the result
   */
  @Override
  public ModelDefinition getModelByNamespaceUri(String uri) {
    // TODO Auto-generated method stub
    return null;
  }
}
