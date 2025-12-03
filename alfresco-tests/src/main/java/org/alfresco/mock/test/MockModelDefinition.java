package org.alfresco.mock.test;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.alfresco.repo.dictionary.DictionaryDAO;
import org.alfresco.service.cmr.dictionary.ModelDefinition;
import org.alfresco.service.cmr.dictionary.NamespaceDefinition;
import org.alfresco.service.cmr.i18n.MessageLookup;
import org.alfresco.service.namespace.QName;

/**
 * Mock implementation of MockModelDefinition for testing purposes.
 *
 * @author vige
 */
public class MockModelDefinition implements ModelDefinition, Serializable {

	/** The name. */
	private QName name;

	/**
	 * Constructs a new MockModelDefinition.
	 *
	 * @param name the name
	 */
	public MockModelDefinition(QName name) {
		this.name = name;
	}

	@Override
	/**
	 * Get name.
	 *
	 * @return the result
	 */
	public QName getName() {
		return name;
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
	 * Get author.
	 *
	 * @return the result
	 */
	public String getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get published date.
	 *
	 * @return the result
	 */
	public Date getPublishedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get version.
	 *
	 * @return the result
	 */
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get namespaces.
	 *
	 * @return the result
	 */
	public Collection<NamespaceDefinition> getNamespaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Is namespace defined.
	 *
	 * @param uri the uri
	 * @return the result
	 */
	public boolean isNamespaceDefined(String uri) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get imported namespaces.
	 *
	 * @return the result
	 */
	public Collection<NamespaceDefinition> getImportedNamespaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Is namespace imported.
	 *
	 * @param uri the uri
	 * @return the result
	 */
	public boolean isNamespaceImported(String uri) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * To x m l.
	 *
	 * @param bindingType the binding type
	 * @param xml the xml
	 */
	public void toXML(XMLBindingType bindingType, OutputStream xml) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get checksum.
	 *
	 * @param bindingType the binding type
	 * @return the result
	 */
	public long getChecksum(XMLBindingType bindingType) {
		// TODO Auto-generated method stub
		return 0;
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
	 * Get dictionary d a o.
	 *
	 * @return the result
	 */
	public DictionaryDAO getDictionaryDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
