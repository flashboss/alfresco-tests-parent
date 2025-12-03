package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.service.cmr.security.AccessPermission;
import org.alfresco.service.cmr.security.AccessStatus;
import org.alfresco.service.cmr.security.AuthorityType;

/**
 * Mock implementation of MockAccessPermission for testing purposes.
 *
 * @author lucastancapiano
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

	@Override
	public String getPermission() {
		return permission;
	}

	@Override
	public AccessStatus getAccessStatus() {
		return accessStatus;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	@Override
	public AuthorityType getAuthorityType() {
		return authorityType;
	}

	@Override
	public int getPosition() {
		return position;
	}

	@Override
	public boolean isInherited() {
		return inherited;
	}

	@Override
	public boolean isSetDirectly() {
		return setDirectly;
	}

	@Override
	public String toString() {
		return "MockAccessPermission [permission=" + permission + ", accessStatus=" + accessStatus + ", authority="
				+ authority + ", authorityType=" + authorityType + ", position=" + position + ", inherited=" + inherited
				+ ", setDirectly=" + setDirectly + "]";
	}

}
