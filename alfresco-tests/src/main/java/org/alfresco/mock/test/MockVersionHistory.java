package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionHistory;

/**
 * Mock implementation of MockVersionHistory for testing purposes.
 *
 * @author vige
 */
public class MockVersionHistory implements VersionHistory, Serializable {

  /** The versions. */
  private Collection<Version> versions = new ArrayList<Version>();

  /**
   * Get root version.
   *
   * @return the result
   */
  @Override
  public Version getRootVersion() {
    return versions.toArray(new Version[0])[0];
  }

  /**
   * Get head version.
   *
   * @return the result
   */
  @Override
  public Version getHeadVersion() {
    return versions.toArray(new Version[0])[versions.size() - 1];
  }

  /**
   * Get all versions.
   *
   * @return the result
   */
  @Override
  public Collection<Version> getAllVersions() {
    return versions;
  }

  /**
   * Get predecessor.
   *
   * @param version the version
   * @return the result
   */
  @Override
  public Version getPredecessor(Version version) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get successors.
   *
   * @param version the version
   * @return the result
   */
  @Override
  public Collection<Version> getSuccessors(Version version) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get version.
   *
   * @param versionLabel the version label
   * @return the result
   */
  @Override
  public Version getVersion(String versionLabel) {
    // TODO Auto-generated method stub
    return null;
  }
}
