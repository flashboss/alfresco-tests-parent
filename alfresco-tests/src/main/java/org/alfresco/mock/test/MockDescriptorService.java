package org.alfresco.mock.test;

import java.io.InputStream;
import org.alfresco.service.descriptor.Descriptor;
import org.alfresco.service.descriptor.DescriptorService;
import org.alfresco.service.license.LicenseDescriptor;
import org.alfresco.service.license.LicenseService.LicenseChangeHandler;
import org.springframework.beans.factory.InitializingBean;

/**
 * Mock implementation of the MockDescriptorService class for testing purposes. This class provides
 * a mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockDescriptorService
    implements DescriptorService, InitializingBean, LicenseChangeHandler {

  /**
   * {@inheritDoc}
   *
   * @param licenseDescriptor the licenseDescriptor
   */
  @Override
  public void onLicenseChange(LicenseDescriptor licenseDescriptor) {
    // TODO Auto-generated method stub

  }

  /** {@inheritDoc} */
  @Override
  public void onLicenseFail() {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @throws Exception if an error occurs
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Descriptor getServerDescriptor() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Descriptor getCurrentRepositoryDescriptor() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public Descriptor getInstalledRepositoryDescriptor() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public LicenseDescriptor getLicenseDescriptor() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public String loadLicense() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @param licenseStream the licenseStream
   * @return the result
   */
  @Override
  public String loadLicense(InputStream licenseStream) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   *
   * @return the result
   */
  @Override
  public boolean isBootstrapped() {
    // TODO Auto-generated method stub
    return false;
  }
}
