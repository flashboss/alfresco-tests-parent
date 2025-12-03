package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.service.cmr.security.AccessPermission;
import org.alfresco.service.cmr.security.AccessStatus;
import org.alfresco.service.cmr.security.AuthorityType;

/**
 * Mock implementation of the MockAccessPermission class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockAccessPermission implements AccessPermission, Serializable {

	private String permission;
	private AccessStatus accessStatus;
	private String authority;
	private AuthorityType authorityType;
	private int position;
	private boolean inherited;
	private boolean setDirectly;

	public MockAccessPermission(String permission, String authority) {
		super();
		this.permission = permission;
		this.authority = authority;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getPermission() {
		return permission;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public AccessStatus getAccessStatus() {
		return accessStatus;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String getAuthority() {
		return authority;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public AuthorityType getAuthorityType() {
		return authorityType;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public int getPosition() {
		return position;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public boolean isInherited() {
		return inherited;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public boolean isSetDirectly() {
		return setDirectly;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public String toString() {
		return "MockAccessPermission [permission=" + permission + ", accessStatus=" + accessStatus + ", authority="
				+ authority + ", authorityType=" + authorityType + ", position=" + position + ", inherited=" + inherited
				+ ", setDirectly=" + setDirectly + "]";
	}

}
