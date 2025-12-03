package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ChildAssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of the {@link TypeDefinition} interface for testing purposes.
 * Provides a basic type definition with a configurable default aspect.
 *
 * @author lucastancapiano
 */
public class MockTypeDefinition implements TypeDefinition {

	/**
	 * The default aspect QName for this type.
	 */
	private QName defaultAspect;

	/**
	 * Constructs a new MockTypeDefinition with the specified default aspect.
	 *
	 * @param defaultAspect The QName of the default aspect for this type.
	 */
	public MockTypeDefinition(QName defaultAspect) {
		this.defaultAspect = defaultAspect;
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
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public QName getName() {
		// TODO Auto-generated method stub
		return null;
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
	public QName getParentName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code false} as this represents a type, not an aspect.
	 */
	@Override
	public boolean isAspect() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Boolean getArchive() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Boolean getIncludedInSuperTypeQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Map<QName, PropertyDefinition> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Map<QName, Serializable> getDefaultValues() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Map<QName, AssociationDefinition> getAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isContainer() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Map<QName, ChildAssociationDefinition> getChildAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Gets the default aspects for this type including inherited aspects.
	 *
	 * @return A list containing a single mock aspect definition based on the default aspect.
	 */
	@Override
	public List<AspectDefinition> getDefaultAspects() {
		return getDefaultAspects(true);
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Set<QName> getDefaultAspectNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Gets the default aspects for this type.
	 *
	 * @param inherited Whether to include inherited aspects.
	 * @return A list containing a single mock aspect definition based on the default aspect.
	 */
	@Override
	public List<AspectDefinition> getDefaultAspects(boolean inherited) {
		List<AspectDefinition> aspectDefinitions = new ArrayList<AspectDefinition>();
		aspectDefinitions.add(new MockAspectDefinition(defaultAspect));
		return aspectDefinitions;
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
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public ClassDefinition getParentClassDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

}
