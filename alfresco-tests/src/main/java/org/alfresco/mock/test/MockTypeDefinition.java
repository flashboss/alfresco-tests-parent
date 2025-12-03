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
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of MockTypeDefinition for testing purposes.
 *
 * @author vige
 */
public class MockTypeDefinition implements TypeDefinition {

	/** The default aspect. */
	private QName defaultAspect;
	
	/**
	 * Constructs a new MockTypeDefinition.
	 *
	 * @param defaultAspect the default aspect
	 */
	public MockTypeDefinition(QName defaultAspect) {
		this.defaultAspect = defaultAspect;
	}

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
	 * Get parent name.
	 *
	 * @return the result
	 */
	public QName getParentName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Is aspect.
	 *
	 * @return the result
	 */
	public boolean isAspect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get archive.
	 *
	 * @return the result
	 */
	public Boolean getArchive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get included in super type query.
	 *
	 * @return the result
	 */
	public Boolean getIncludedInSuperTypeQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get properties.
	 *
	 */
	public Map<QName, PropertyDefinition> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get default values.
	 *
	 */
	public Map<QName, Serializable> getDefaultValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get associations.
	 *
	 */
	public Map<QName, AssociationDefinition> getAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Is container.
	 *
	 * @return the result
	 */
	public boolean isContainer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get child associations.
	 *
	 */
	public Map<QName, ChildAssociationDefinition> getChildAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get default aspects.
	 *
	 * @return the result
	 */
	public List<AspectDefinition> getDefaultAspects() {
		return getDefaultAspects(true);
	}

	@Override
	/**
	 * Get default aspect names.
	 *
	 * @return the result
	 */
	public Set<QName> getDefaultAspectNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get default aspects.
	 *
	 * @param inherited the inherited
	 * @return the result
	 */
	public List<AspectDefinition> getDefaultAspects(boolean inherited) {
		List<AspectDefinition> aspectDefinitions = new ArrayList<AspectDefinition>();
		aspectDefinitions.add(new MockAspectDefinition(defaultAspect));
		return aspectDefinitions;
	}

	@Override
	/**
	 * Get analyser resource bundle name.
	 *
	 * @return the result
	 */
	public String getAnalyserResourceBundleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get parent class definition.
	 *
	 * @return the result
	 */
	public ClassDefinition getParentClassDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

}
