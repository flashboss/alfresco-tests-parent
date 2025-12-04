package org.alfresco.mock.test.script;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.alfresco.repo.jscript.Search;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.StoreRef;

/**
 * Mock implementation of Search for testing purposes.
 *
 * @author vige
 */
public class MockSearch extends Search implements Externalizable {
  /**
   * Write external.
   *
   * @param out the out
   */
  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    out.writeObject(services);
    out.writeChars(getExtensionName());
    out.writeObject(storeRef);
  }

  /**
   * Read external.
   *
   * @param in the in
   */
  @Override
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    services = (ServiceRegistry) in.readObject();
    setExtensionName(in.readLine());
    storeRef = (StoreRef) in.readObject();
  }
}
