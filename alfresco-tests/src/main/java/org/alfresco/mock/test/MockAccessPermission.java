package org.alfresco.mock.test;

import java.io.Serializable;
import org.alfresco.service.cmr.security.AccessPermission;
import org.alfresco.service.cmr.security.AccessStatus;
import org.alfresco.service.cmr.security.AuthorityType;

/**
 * Mock implementation of the MockAccessPermission class for testing purposes. This class provides a
 * mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
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
   * Constructs a new MockAccessPermission with the specified permission and authority.
   *
   * @param permission the permission string
   * @param authority the authority string
   */
  public MockAccessPermission(String permission, String authority) {
    super();
    this.permission = permission;
    this.authority = authority;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getPermission() {
    return permission;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public AccessStatus getAccessStatus() {
    return accessStatus;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String getAuthority() {
    return authority;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public AuthorityType getAuthorityType() {
    return authorityType;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public int getPosition() {
    return position;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean isInherited() {
    return inherited;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean isSetDirectly() {
    return setDirectly;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String toString() {
    return "MockAccessPermission [permission="
        + permission
        + ", accessStatus="
        + accessStatus
        + ", authority="
        + authority
        + ", authorityType="
        + authorityType
        + ", position="
        + position
        + ", inherited="
        + inherited
        + ", setDirectly="
        + setDirectly
        + "]";
  }
}
