package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.service.cmr.security.AccessPermission;
import org.alfresco.service.cmr.security.AccessStatus;
import org.alfresco.service.cmr.security.AuthorityType;

/**
 * Mock implementation of AccessPermission for testing purposes.
 * Provides a simple access permission representation with permission string and authority.
 * 
 * @author vige
 */
public class MockAccessPermission implements AccessPermission, Serializable {

	/** The permission string. */
	private String permission;
	
	/** The access status (ALLOWED or DENIED). */
	private AccessStatus accessStatus;
	
	/** The authority name. */
	private String authority;
	
	/** The type of authority. */
	private AuthorityType authorityType;
	
	/** The position in the permission list. */
	private int position;
	
	/** Whether the permission is inherited. */
	private boolean inherited;
	
	/** Whether the permission was set directly. */
	private boolean setDirectly;

	/**
	 * Creates a new MockAccessPermission with the specified permission and authority.
	 * 
	 * @param permission the permission string
	 * @param authority the authority name
	 */
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
