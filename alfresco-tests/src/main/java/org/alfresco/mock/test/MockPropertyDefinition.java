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
 * Mock implementation of the MockPropertyDefinition class for testing purposes. This class provides
 * a mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockPropertyDefinition implements PropertyDefinition, Serializable {

  /** The name. */
  private QName name;

  /**
   * Constructs a new MockPropertyDefinition instance.
   *
   * @param name the property name
   */
  public MockPropertyDefinition(QName name) {
    this.name = name;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public ModelDefinition getModel() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public QName getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getTitle() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param messageLookup the messageLookup
   * @return the result
   */
  @Override
  public String getTitle(MessageLookup messageLookup) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param messageLookup the messageLookup
   * @return the result
   */
  @Override
  public String getDescription(MessageLookup messageLookup) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getDefaultValue() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public DataTypeDefinition getDataType() {
    return new MockDataTypeDefinition();
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public ClassDefinition getContainerClass() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean isOverride() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean isMultiValued() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean isMandatory() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean isMandatoryEnforced() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean isProtected() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean isIndexed() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean isStoredInIndex() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public IndexTokenisationMode getIndexTokenisationMode() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean isIndexedAtomically() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public List<ConstraintDefinition> getConstraints() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param messageLookup the messageLookup
   * @param locale the locale
   * @return the result
   */
  @Override
  public String getTitle(MessageLookup messageLookup, Locale locale) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param messageLookup the messageLookup
   * @param locale the locale
   * @return the result
   */
  @Override
  public String getDescription(MessageLookup messageLookup, Locale locale) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Facetable getFacetable() {
    // TODO Auto-generated method stub
    return null;
  }
}
