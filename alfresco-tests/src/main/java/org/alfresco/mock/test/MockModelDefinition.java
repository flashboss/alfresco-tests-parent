package org.alfresco.mock.test;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import org.alfresco.repo.dictionary.DictionaryDAO;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.NamespaceDefinition;
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of the MockModelDefinition class for testing purposes. This class provides a
 * mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockModelDefinition implements ModelDefinition, Serializable {

  /** The name. */
  private QName name;

  /**
   * Constructs a new MockModelDefinition instance.
   *
   * @param name the model name
   */
  public MockModelDefinition(QName name) {
    this.name = name;
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
  public String getAuthor() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Date getPublishedDate() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getVersion() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Collection<NamespaceDefinition> getNamespaces() {
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
  public boolean isNamespaceDefined(String uri) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Collection<NamespaceDefinition> getImportedNamespaces() {
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
  public boolean isNamespaceImported(String uri) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * {@inheritDoc}
   *
   * @param bindingType the bindingType
   * @param xml the xml
   */
  @Override
  public void toXML(XMLBindingType bindingType, OutputStream xml) {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @param bindingType the bindingType
   * @return the result
   */
  @Override
  public long getChecksum(XMLBindingType bindingType) {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public DictionaryDAO getDictionaryDAO() {
    // TODO Auto-generated method stub
    return null;
  }
}
