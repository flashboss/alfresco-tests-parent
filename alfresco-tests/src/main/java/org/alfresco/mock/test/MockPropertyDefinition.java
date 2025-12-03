package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.alfresco.repo.dictionary.IndexTokenisationMode;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of MockPropertyDefinition for testing purposes.
 *
 * @author vige
 */
public class MockPropertyDefinition implements PropertyDefinition, Serializable {

	private QName name;

	public MockPropertyDefinition(QName name) {
		this.name = name;
	}

	@Override
	public ModelDefinition getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QName getName() {
		return name;
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
	public String getTitle(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTypeDefinition getDataType() {
		return new MockDataTypeDefinition();
	}

	@Override
	public ClassDefinition getContainerClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOverride() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMultiValued() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMandatory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMandatoryEnforced() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isProtected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isIndexed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStoredInIndex() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IndexTokenisationMode getIndexTokenisationMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isIndexedAtomically() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ConstraintDefinition> getConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAnalyserResourceBundleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resolveAnalyserClassName(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resolveAnalyserClassName() {
		// TODO Auto-generated method stub
		return null;
	}

}
