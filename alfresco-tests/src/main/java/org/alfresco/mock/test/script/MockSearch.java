package org.alfresco.mock.test.script;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.alfresco.repo.jscript.Search;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.StoreRef;

/**
 * @author vige
 */
public class MockSearch extends Search implements Externalizable {

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(services);
		out.writeChars(getExtensionName());
		out.writeObject(storeRef);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		services = (ServiceRegistry) in.readObject();
		setExtensionName(in.readLine());
		storeRef = (StoreRef) in.readObject();
	}

}