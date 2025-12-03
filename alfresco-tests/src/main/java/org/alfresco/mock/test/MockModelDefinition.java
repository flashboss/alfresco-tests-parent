package org.alfresco.mock.test;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.alfresco.repo.dictionary.DictionaryDAO;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.NamespaceDefinition;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of the {@link ModelDefinition} interface for testing purposes.
 * Provides a basic model definition with a configurable name.
 *
 * @author lucastancapiano
 */
public class MockModelDefinition implements ModelDefinition, Serializable {

	/**
	 * The model name QName.
	 */
	private QName name;

	/**
	 * Constructs a new MockModelDefinition with the specified name.
	 *
	 * @param name The QName of the model.
	 */
	public MockModelDefinition(QName name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 * @return The model QName.
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
	public String getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Date getPublishedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Collection<NamespaceDefinition> getNamespaces() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @param uri The namespace URI to check.
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isNamespaceDefined(String uri) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Collection<NamespaceDefinition> getImportedNamespaces() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @param uri The namespace URI to check.
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isNamespaceImported(String uri) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Exports the model to XML. This is a stub implementation.
	 *
	 * @param bindingType The XML binding type.
	 * @param xml The output stream to write XML to.
	 */
	@Override
	public void toXML(XMLBindingType bindingType, OutputStream xml) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 * @param bindingType The XML binding type.
	 * @return 0 as this is a mock implementation.
	 */
	@Override
	public long getChecksum(XMLBindingType bindingType) {
		// TODO Auto-generated method stub
		return 0;
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
	public DictionaryDAO getDictionaryDAO() {
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

}
