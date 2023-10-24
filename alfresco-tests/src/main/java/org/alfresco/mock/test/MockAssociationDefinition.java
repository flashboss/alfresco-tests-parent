package org.alfresco.mock.test;

import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

public class MockAssociationDefinition implements AssociationDefinition {

	@Override
	public ModelDefinition getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QName getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isChild() {
		return true;
	}

	@Override
	public boolean isProtected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ClassDefinition getSourceClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QName getSourceRoleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSourceMandatory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSourceMany() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ClassDefinition getTargetClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QName getTargetRoleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTargetMandatory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTargetMandatoryEnforced() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTargetMany() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTitle(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

}
