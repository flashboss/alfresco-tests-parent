package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.service.cmr.security.AccessPermission;
import org.alfresco.service.cmr.security.AccessStatus;
import org.alfresco.service.cmr.security.AuthorityType;

/**
 * Mock implementation of AccessPermission for testing purposes.
 *
 * @author vige
 */
public class MockAccessPermission implements AccessPermission, Serializable {

	/** The permission. */
	private String permission;
	/** The access status. */
	private AccessStatus accessStatus;
	/** The authority. */
	private String authority;
	/** The authority type. */
	private AuthorityType authorityType;
	/** The position. */
	private int position;
	/** The inherited. */
	private boolean inherited;
	/** The set directly. */
	private boolean setDirectly;

	/**
	 * Constructs a new MockAccessPermission.
	 *
	 * @param permission the permission name
	 * @param authority the authority name
	 */
	public MockAccessPermission(String permission, String authority) {
		super();
		this.permission = permission;
		this.authority = authority;
	}

	@Override
	/**
	 * Get permission.
	 *
	 * @return the result
	 */
	public String getPermission() {
		return permission;
	}

	@Override
	/**
	 * Get access status.
	 *
	 * @return the result
	 */
	public AccessStatus getAccessStatus() {
		return accessStatus;
	}

	@Override
	/**
	 * Get authority.
	 *
	 * @return the result
	 */
	public String getAuthority() {
		return authority;
	}

	@Override
	/**
	 * Get authority type.
	 *
	 * @return the result
	 */
	public AuthorityType getAuthorityType() {
		return authorityType;
	}

	@Override
	/**
	 * Get position.
	 *
	 * @return the result
	 */
	public int getPosition() {
		return position;
	}

	@Override
	/**
	 * Is inherited.
	 *
	 * @return the result
	 */
	public boolean isInherited() {
		return inherited;
	}

	@Override
	/**
	 * Is set directly.
	 *
	 * @return the result
	 */
	public boolean isSetDirectly() {
		return setDirectly;
	}

	@Override
	/**
	 * To string.
	 *
	 * @return the result
	 */
	public String toString() {
		return "MockAccessPermission [permission=" + permission + ", accessStatus=" + accessStatus + ", authority="
				+ authority + ", authorityType=" + authorityType + ", position=" + position + ", inherited=" + inherited
				+ ", setDirectly=" + setDirectly + "]";
	}

}
