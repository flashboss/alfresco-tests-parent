package org.alfresco.mock.test.activiti;

import java.util.Collection;
import java.util.List;

import org.alfresco.repo.dictionary.DictionaryDAO;
import org.alfresco.repo.dictionary.DictionaryListener;
import org.alfresco.repo.dictionary.M2Model;
import org.alfresco.repo.dictionary.M2ModelDiff;
import org.alfresco.service.cmr.dictionary.AspectDefinition;
import org.alfresco.service.cmr.dictionary.AssociationDefinition;
import org.alfresco.service.cmr.dictionary.ClassDefinition;
import org.alfresco.service.cmr.dictionary.ConstraintDefinition;
import org.alfresco.service.cmr.dictionary.DataTypeDefinition;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.NamespaceDefinition;
import org.alfresco.service.cmr.dictionary.PropertyDefinition;
import org.alfresco.service.cmr.dictionary.TypeDefinition;
import org.alfresco.service.namespace.QName;

public class MockDictionaryDAO implements DictionaryDAO {

	@Override
	public DataTypeDefinition getDataType(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataTypeDefinition getDataType(Class javaClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeDefinition getType(QName name) {
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
	public PropertyDefinition getProperty(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintDefinition getConstraint(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssociationDefinition getAssociation(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelDefinition getModel(QName name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DataTypeDefinition> getDataTypes(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<TypeDefinition> getTypes(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getSubTypes(QName superType, boolean follow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<AspectDefinition> getAspects(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<AssociationDefinition> getAssociations(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<QName> getSubAspects(QName superAspect, boolean follow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<PropertyDefinition> getProperties(QName model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeDefinition getAnonymousType(QName type, Collection<QName> aspects) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QName putModel(M2Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QName putModelIgnoringConstraints(M2Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeModel(QName model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<PropertyDefinition> getProperties(QName modelName, QName dataType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<PropertyDefinition> getPropertiesOfDataType(QName dataType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<NamespaceDefinition> getNamespaces(QName modelName) {
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

	@Override
	public List<M2ModelDiff> diffModel(M2Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<M2ModelDiff> diffModelIgnoringConstraints(M2Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register(DictionaryListener dictionaryListener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isModelInherited(QName name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDefaultAnalyserResourceBundleName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassLoader getResourceClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResourceClassLoader(ClassLoader resourceClassLoader) {
		// TODO Auto-generated method stub
		
	}

}
