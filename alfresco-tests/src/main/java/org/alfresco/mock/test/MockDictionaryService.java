package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ChildAssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

public class MockDictionaryService implements DictionaryService {

	private Map<QName, TypeDefinition> typeDefinitions = new HashMap<QName, TypeDefinition>();
	
	private Map<QName, PropertyDefinition> propertyDefinitions = new HashMap<QName, PropertyDefinition>();

	@Override
	public String getMessage(String messageKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage(String messageKey, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage(String messageKey, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage(String messageKey, Locale locale, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAllModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelDefinition getModel(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAllDataTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getDataTypes(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTypeDefinition getDataType(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTypeDefinition getDataType(Class<?> javaClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAllTypes() {
		return typeDefinitions.keySet();
	}

	@Override
	public Collection<QName> getSubTypes(QName type, boolean follow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getTypes(QName model) {
		return typeDefinitions.keySet();
	}

	@Override
	public TypeDefinition getType(QName name) {
		TypeDefinition typeDefinition = typeDefinitions.get(name);
		if (typeDefinition == null) {
			typeDefinition = new MockTypeDefinition(name);
			typeDefinitions.put(name, typeDefinition);
		}
		return typeDefinition;
	}

	@Override
	public TypeDefinition getAnonymousType(QName type, Collection<QName> aspects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeDefinition getAnonymousType(QName name) {
		return typeDefinitions.get(name);
	}

	@Override
	public Collection<QName> getAllAspects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getSubAspects(QName aspect, boolean follow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAspects(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAssociations(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AspectDefinition getAspect(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassDefinition getClass(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSubClass(QName className, QName ofClassName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PropertyDefinition getProperty(QName className, QName propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<QName, PropertyDefinition> getPropertyDefs(QName className) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PropertyDefinition getProperty(QName propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAllProperties(QName dataType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getProperties(QName model, QName dataType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getProperties(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssociationDefinition getAssociation(QName associationName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getAllAssociations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintDefinition getConstraint(QName constraintQName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConstraintDefinition> getConstraints(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ConstraintDefinition> getConstraints(QName model, boolean referenceableDefsOnly) {
		// TODO Auto-generated method stub
		return null;
	}

	public class MockTypeDefinition implements TypeDefinition {

		private QName name = null;

		public MockTypeDefinition(QName name) {
			this.name = name;
		}

		@Override
		public ModelDefinition getModel() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public QName getName() {
			// TODO Auto-generated method stub
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
			return propertyDefinitions;
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
		public String getAnalyserResourceBundleName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ClassDefinition getParentClassDefinition() {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
