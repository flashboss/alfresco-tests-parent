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
 * Mock implementation of ModelDefinition for testing purposes.
 * 
 * @author vige
 */
public class MockModelDefinition implements ModelDefinition, Serializable {

	/** The name. */
	private QName name;

	/**
	 * Constructs a new mock model definition.
	 *
	 * @param name the name
	 * @return the result
	 */
	public MockModelDefinition(QName name) {
		this.name = name;
	}

	/**
	 * Get name.
	 *
	 * @return the q name
	 */
@Override
	public QName getName() {
		return name;
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
	 * Get author.
	 *
	 * @return the string
	 */
@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get published date.
	 *
	 * @return the date
	 */
@Override
	public Date getPublishedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get version.
	 *
	 * @return the string
	 */
@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get namespaces.
	 *
	 * @return the collection
	 */
@Override
	public Collection<NamespaceDefinition> getNamespaces() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Is namespace defined.
	 *
	 * @param uri the uri
	 * @return the boolean
	 */
@Override
	public boolean isNamespaceDefined(String uri) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Get imported namespaces.
	 *
	 * @return the collection
	 */
@Override
	public Collection<NamespaceDefinition> getImportedNamespaces() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Is namespace imported.
	 *
	 * @param uri the uri
	 * @return the boolean
	 */
@Override
	public boolean isNamespaceImported(String uri) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * To x m l.
	 *
	 * @param bindingType the binding type
	 * @param xml the xml
	 */
@Override
	public void toXML(XMLBindingType bindingType, OutputStream xml) {
		// TODO Auto-generated method stub

	}

	/**
	 * Get checksum.
	 *
	 * @param bindingType the binding type
	 * @return the long
	 */
@Override
	public long getChecksum(XMLBindingType bindingType) {
		// TODO Auto-generated method stub
		return 0;
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
	 * Get dictionary d a o.
	 *
	 * @return the dictionary d a o
	 */
@Override
	public DictionaryDAO getDictionaryDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
