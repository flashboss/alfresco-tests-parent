package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.service.cmr.security.AccessPermission;
import org.alfresco.service.cmr.security.AccessStatus;
import org.alfresco.service.cmr.security.AuthorityType;

/**
 * Mock implementation of AccessPermission for testing purposes.
 * Provides a simple implementation of permission data.
 *
 * @author lucastancapiano
 */
public class MockAccessPermission implements AccessPermission, Serializable {

	/** The permission name. */
	private String permission;

	/** The access status. */
	private AccessStatus accessStatus;

	/** The authority name. */
	private String authority;

	/** The authority type. */
	private AuthorityType authorityType;

	/** The position in the permission list. */
	private int position;

	/** Whether the permission is inherited. */
	private boolean inherited;

	/** Whether the permission is set directly. */
	private boolean setDirectly;

	/**
	 * Constructs a new MockAccessPermission with the specified permission and authority.
	 *
	 * @param permission the permission name
	 * @param authority the authority name
	 */
	public MockAccessPermission(String permission, String authority) {
		super();
		this.permission = permission;
		this.authority = authority;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the permission name
	 */
	@Override
	public String getPermission() {
		return permission;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the access status
	 */
	@Override
	public AccessStatus getAccessStatus() {
		return accessStatus;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the authority name
	 */
	@Override
	public String getAuthority() {
		return authority;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the authority type
	 */
	@Override
	public AuthorityType getAuthorityType() {
		return authorityType;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the position
	 */
	@Override
	public int getPosition() {
		return position;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if inherited, false otherwise
	 */
	@Override
	public boolean isInherited() {
		return inherited;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return true if set directly, false otherwise
	 */
	@Override
	public boolean isSetDirectly() {
		return setDirectly;
	}

	/**
	 * Returns a string representation of this access permission.
	 *
	 * @return a string representation
	 */
	@Override
	public String toString() {
		return "MockAccessPermission [permission=" + permission + ", accessStatus=" + accessStatus + ", authority="
				+ authority + ", authorityType=" + authorityType + ", position=" + position + ", inherited=" + inherited
				+ ", setDirectly=" + setDirectly + "]";
	}

}
