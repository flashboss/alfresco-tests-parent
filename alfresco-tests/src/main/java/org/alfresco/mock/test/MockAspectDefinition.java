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

public class MockAspectDefinition implements AspectDefinition, Serializable {

	private QName name;

	public MockAspectDefinition(QName name) {
		this.name = name;
	}

	@Override
	public ModelDefinition getModel() {
		return new MockModelDefinition(name);
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
	public QName getParentName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAspect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean getArchive() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getIncludedInSuperTypeQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<QName, PropertyDefinition> getProperties() {
		return new HashMap<QName, PropertyDefinition>();
	}

	@Override
	public Map<QName, Serializable> getDefaultValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<QName, AssociationDefinition> getAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isContainer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<QName, ChildAssociationDefinition> getChildAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AspectDefinition> getDefaultAspects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<QName> getDefaultAspectNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AspectDefinition> getDefaultAspects(boolean inherited) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassDefinition getParentClassDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

}
