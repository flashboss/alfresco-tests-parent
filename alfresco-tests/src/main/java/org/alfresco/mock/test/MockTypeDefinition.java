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
 * Mock implementation of TypeDefinition for testing purposes.
 * 
 * @author vige
 */
public class MockTypeDefinition implements TypeDefinition {
	/** The default aspect. */
	private QName defaultAspect;
	
	/**
	 * Constructs a new mock type definition.
	 *
	 * @param defaultAspect the default aspect
	 * @return the result
	 */
	public MockTypeDefinition(QName defaultAspect) {
		this.defaultAspect = defaultAspect;
	}
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
	 * Get parent name.
	 *
	 * @return the q name
	 */
	@Override
	public QName getParentName() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Is aspect.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean isAspect() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Get archive.
	 *
	 * @return the boolean
	 */
	@Override
	public Boolean getArchive() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get included in super type query.
	 *
	 * @return the boolean
	 */
	@Override
	public Boolean getIncludedInSuperTypeQuery() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get properties.
	 *
	 */
	@Override
	public Map<QName, PropertyDefinition> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get default values.
	 *
	 */
	@Override
	public Map<QName, Serializable> getDefaultValues() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get associations.
	 *
	 */
	@Override
	public Map<QName, AssociationDefinition> getAssociations() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Is container.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean isContainer() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Get child associations.
	 *
	 */
	@Override
	public Map<QName, ChildAssociationDefinition> getChildAssociations() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get default aspects.
	 *
	 * @return the list
	 */
	@Override
	public List<AspectDefinition> getDefaultAspects() {
		return getDefaultAspects(true);
	}
	/**
	 * Get default aspect names.
	 *
	 * @return the set
	 */
	@Override
	public Set<QName> getDefaultAspectNames() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get default aspects.
	 *
	 * @param inherited the inherited
	 * @return the list
	 */
	@Override
	public List<AspectDefinition> getDefaultAspects(boolean inherited) {
		List<AspectDefinition> aspectDefinitions = new ArrayList<AspectDefinition>();
		aspectDefinitions.add(new MockAspectDefinition(defaultAspect));
		return aspectDefinitions;
	}
	/**
	 * Get analyser resource bundle name.
	 *
	 * @return the string
	 */
	@Override
	public String getAnalyserResourceBundleName() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get parent class definition.
	 *
	 * @return the class definition
	 */
	@Override
	public ClassDefinition getParentClassDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

}
