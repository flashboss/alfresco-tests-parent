package org.alfresco.mock.test.script;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import org.alfresco.repo.jscript.Search;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.StoreRef;

/**
 * Mock implementation of Search for testing purposes.
 * Provides externalizable search functionality for JavaScript scripts.
 *
 * @author lucastancapiano
 */
public class MockSearch extends Search implements Serializable, Externalizable {

	/**
	 * {@inheritDoc}
	 *
	 * @param out the output to write to
	 * @throws IOException if writing fails
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(services);
		out.writeChars(getExtensionName());
		out.writeObject(storeRef);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param in the input to read from
	 * @throws IOException if reading fails
	 * @throws ClassNotFoundException if class not found
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		services = (ServiceRegistry) in.readObject();
		setExtensionName(in.readLine());
		storeRef = (StoreRef) in.readObject();
	}

}
