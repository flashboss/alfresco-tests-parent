package org.alfresco.mock.test;

import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of AssociationDefinition for testing purposes.
 *
 * @author vige
 */
public class MockAssociationDefinition implements AssociationDefinition {
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
   * Is child.
   *
   * @return the boolean
   */
  @Override
  public boolean isChild() {
    return true;
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
   * Get source class.
   *
   * @return the class definition
   */
  @Override
  public ClassDefinition getSourceClass() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get source role name.
   *
   * @return the q name
   */
  @Override
  public QName getSourceRoleName() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Is source mandatory.
   *
   * @return the boolean
   */
  @Override
  public boolean isSourceMandatory() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Is source many.
   *
   * @return the boolean
   */
  @Override
  public boolean isSourceMany() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Get target class.
   *
   * @return the class definition
   */
  @Override
  public ClassDefinition getTargetClass() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get target role name.
   *
   * @return the q name
   */
  @Override
  public QName getTargetRoleName() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Is target mandatory.
   *
   * @return the boolean
   */
  @Override
  public boolean isTargetMandatory() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Is target mandatory enforced.
   *
   * @return the boolean
   */
  @Override
  public boolean isTargetMandatoryEnforced() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Is target many.
   *
   * @return the boolean
   */
  @Override
  public boolean isTargetMany() {
    // TODO Auto-generated method stub
    return false;
  }
}
