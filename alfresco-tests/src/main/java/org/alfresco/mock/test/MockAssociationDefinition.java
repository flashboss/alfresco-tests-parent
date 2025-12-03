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

	@Override
	/**
	 * Get model.
	 *
	 * @return the result
	 */
	public ModelDefinition getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get name.
	 *
	 * @return the result
	 */
	public QName getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get title.
	 *
	 * @return the result
	 */
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get description.
	 *
	 * @return the result
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
	 * @return the result
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
	 * @return the result
	 */
	public String getDescription(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Is child.
	 *
	 * @return the result
	 */
	public boolean isChild() {
		return true;
	}

	@Override
	/**
	 * Is protected.
	 *
	 * @return the result
	 */
	public boolean isProtected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get source class.
	 *
	 * @return the result
	 */
	public ClassDefinition getSourceClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get source role name.
	 *
	 * @return the result
	 */
	public QName getSourceRoleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Is source mandatory.
	 *
	 * @return the result
	 */
	public boolean isSourceMandatory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Is source many.
	 *
	 * @return the result
	 */
	public boolean isSourceMany() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get target class.
	 *
	 * @return the result
	 */
	public ClassDefinition getTargetClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get target role name.
	 *
	 * @return the result
	 */
	public QName getTargetRoleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Is target mandatory.
	 *
	 * @return the result
	 */
	public boolean isTargetMandatory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Is target mandatory enforced.
	 *
	 * @return the result
	 */
	public boolean isTargetMandatoryEnforced() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Is target many.
	 *
	 * @return the result
	 */
	public boolean isTargetMany() {
		// TODO Auto-generated method stub
		return false;
	}

}
