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
    // TODO Auto-generated method stub
    return null;
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
   * Get java class name.
   *
   * @return the string
   */
  @Override
  public String getJavaClassName() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get default analyser class name.
   *
   * @return the string
   */
  @Override
  public String getDefaultAnalyserClassName() {
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
}
