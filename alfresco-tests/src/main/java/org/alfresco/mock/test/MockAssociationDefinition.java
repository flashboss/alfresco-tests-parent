package org.alfresco.mock.test;

import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of AssociationDefinition for testing purposes.
 * Provides a simple association definition implementation.
 *
 * @author lucastancapiano
 */
public class MockAssociationDefinition implements AssociationDefinition {

	/**
	 * {@inheritDoc}
	 *
	 * @return the model definition
	 */
	@Override
	public ModelDefinition getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the association name
	 */
	@Override
	public QName getName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the title
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the description
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if child association, false otherwise
	 */
	@Override
	public boolean isChild() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if protected, false otherwise
	 */
	@Override
	public boolean isProtected() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the source class definition
	 */
	@Override
	public ClassDefinition getSourceClass() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the source role name
	 */
	@Override
	public QName getSourceRoleName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if source is mandatory, false otherwise
	 */
	@Override
	public boolean isSourceMandatory() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if source allows many, false otherwise
	 */
	@Override
	public boolean isSourceMany() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the target class definition
	 */
	@Override
	public ClassDefinition getTargetClass() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the target role name
	 */
	@Override
	public QName getTargetRoleName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if target is mandatory, false otherwise
	 */
	@Override
	public boolean isTargetMandatory() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if target mandatory is enforced, false otherwise
	 */
	@Override
	public boolean isTargetMandatoryEnforced() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if target allows many, false otherwise
	 */
	@Override
	public boolean isTargetMany() {
		// TODO Auto-generated method stub
		return false;
	}

}
