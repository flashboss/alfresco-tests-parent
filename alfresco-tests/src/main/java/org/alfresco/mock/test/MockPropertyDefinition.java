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
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of the {@link PropertyDefinition} interface for testing purposes.
 * Provides a basic property definition with a configurable name and default data type.
 *
 * @author lucastancapiano
 */
public class MockPropertyDefinition implements PropertyDefinition, Serializable {

	/**
	 * The property name QName.
	 */
	private QName name;

	/**
	 * Constructs a new MockPropertyDefinition with the specified name.
	 *
	 * @param name The QName of the property.
	 */
	public MockPropertyDefinition(QName name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public ModelDefinition getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return The property QName.
	 */
	@Override
	public QName getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public String getDefaultValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return A new {@link MockDataTypeDefinition} instance.
	 */
	@Override
	public DataTypeDefinition getDataType() {
		return new MockDataTypeDefinition();
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public ClassDefinition getContainerClass() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isOverride() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isMultiValued() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isMandatory() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isMandatoryEnforced() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isProtected() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isIndexed() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isStoredInIndex() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public IndexTokenisationMode getIndexTokenisationMode() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isIndexedAtomically() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public List<ConstraintDefinition> getConstraints() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public String getAnalyserResourceBundleName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @param locale The locale for which to resolve the analyser class.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public String resolveAnalyserClassName(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public String resolveAnalyserClassName() {
		// TODO Auto-generated method stub
		return null;
	}

}
