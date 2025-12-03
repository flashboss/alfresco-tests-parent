package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ChildAssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of AspectDefinition for testing purposes.
 * Provides a simple aspect definition with configurable name.
 *
 * @author lucastancapiano
 */
public class MockAspectDefinition implements AspectDefinition, Serializable {

	/** The aspect name. */
	private QName name;

	/**
	 * Constructs a new MockAspectDefinition with the specified name.
	 *
	 * @param name the aspect QName
	 */
	public MockAspectDefinition(QName name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the model definition
	 */
	@Override
	public ModelDefinition getModel() {
		return new MockModelDefinition(name);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the aspect name
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
	 * @return the parent name
	 */
	@Override
	public QName getParentName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if aspect, false otherwise
	 */
	@Override
	public boolean isAspect() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the archive flag
	 */
	@Override
	public Boolean getArchive() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the included in super type query flag
	 */
	@Override
	public Boolean getIncludedInSuperTypeQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the properties map
	 */
	@Override
	public Map<QName, PropertyDefinition> getProperties() {
		return new HashMap<QName, PropertyDefinition>();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the default values map
	 */
	@Override
	public Map<QName, Serializable> getDefaultValues() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the associations map
	 */
	@Override
	public Map<QName, AssociationDefinition> getAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if container, false otherwise
	 */
	@Override
	public boolean isContainer() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the child associations map
	 */
	@Override
	public Map<QName, ChildAssociationDefinition> getChildAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the default aspects list
	 */
	@Override
	public List<AspectDefinition> getDefaultAspects() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the default aspect names set
	 */
	@Override
	public Set<QName> getDefaultAspectNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param inherited whether to include inherited aspects
	 * @return the default aspects list
	 */
	@Override
	public List<AspectDefinition> getDefaultAspects(boolean inherited) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the analyser resource bundle name
	 */
	@Override
	public String getAnalyserResourceBundleName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the parent class definition
	 */
	@Override
	public ClassDefinition getParentClassDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

}
