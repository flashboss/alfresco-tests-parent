package org.alfresco.mock.test.script;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.alfresco.repo.jscript.Search;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.StoreRef;

/**
 * Mock implementation of the MockSearch class for testing purposes. This class provides a mock
 * implementation that allows unit and integration tests to run without requiring a full Alfresco
 * server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockSearch extends Search implements Externalizable {

  /**
   * {@inheritDoc}
   *
   * @param out the out
   * @throws IOException if an error occurs
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
   * @param in the in
   * @throws IOException if an error occurs
   * @throws ClassNotFoundException if an error occurs
   */
  @Override
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    services = (ServiceRegistry) in.readObject();
    setExtensionName(in.readLine());
    storeRef = (StoreRef) in.readObject();
  }
}
