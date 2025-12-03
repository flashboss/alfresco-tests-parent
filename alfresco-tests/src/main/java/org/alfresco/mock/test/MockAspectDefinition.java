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
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

/**
* Mock implementation of the MockAspectDefinition class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockAspectDefinition implements AspectDefinition, Serializable {

/**
* The name.
 */
	private QName name;

/**
* Constructs a new MockAspectDefinition with the specified name.
*
* @param name the aspect name
 */
	public MockAspectDefinition(QName name) {
		this.name = name;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ModelDefinition getModel() {
		return new MockModelDefinition(name);
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public QName getName() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param messageLookup the messageLookup
* @return the result
 */
	@Override
	public String getTitle(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param messageLookup the messageLookup
* @return the result
 */
	@Override
	public String getDescription(MessageLookup messageLookup) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public QName getParentName() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public boolean isAspect() {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Boolean getArchive() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Boolean getIncludedInSuperTypeQuery() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Map<QName, PropertyDefinition> getProperties() {
		return new HashMap<QName, PropertyDefinition>();
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Map<QName, Serializable> getDefaultValues() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Map<QName, AssociationDefinition> getAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public boolean isContainer() {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Map<QName, ChildAssociationDefinition> getChildAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public List<AspectDefinition> getDefaultAspects() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Set<QName> getDefaultAspectNames() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param inherited the inherited
* @return the result
 */
	@Override
	public List<AspectDefinition> getDefaultAspects(boolean inherited) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ClassDefinition getParentClassDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

}
