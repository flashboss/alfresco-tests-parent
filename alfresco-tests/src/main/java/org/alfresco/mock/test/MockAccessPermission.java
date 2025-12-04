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
 * Constructs a new mock access permission.
 *
 * @param permission the permission
 * @param authority the authority
 * @return the result
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
 * @return the string
 */
	public String getPermission() {
		return permission;
	}

	@Override
 /**
 * Get access status.
 *
 * @return the access status
 */
	public AccessStatus getAccessStatus() {
		return accessStatus;
	}

	@Override
 /**
 * Get authority.
 *
 * @return the string
 */
	public String getAuthority() {
		return authority;
	}

	@Override
 /**
 * Get authority type.
 *
 * @return the authority type
 */
	public AuthorityType getAuthorityType() {
		return authorityType;
	}

	@Override
 /**
 * Get position.
 *
 * @return the int
 */
	public int getPosition() {
		return position;
	}

	@Override
 /**
 * Is inherited.
 *
 * @return the boolean
 */
	public boolean isInherited() {
		return inherited;
	}

	@Override
 /**
 * Is set directly.
 *
 * @return the boolean
 */
	public boolean isSetDirectly() {
		return setDirectly;
	}

	@Override
 /**
 * To string.
 *
 * @return the string
 */
	public String toString() {
		return "MockAccessPermission [permission=" + permission + ", accessStatus=" + accessStatus + ", authority="
				+ authority + ", authorityType=" + authorityType + ", position=" + position + ", inherited=" + inherited
				+ ", setDirectly=" + setDirectly + "]";
	}

}
