package org.alfresco.mock.test;

import org.alfresco.service.descriptor.Descriptor;
import org.alfresco.service.descriptor.DescriptorService;
import org.alfresco.service.license.LicenseDescriptor;
import org.alfresco.service.license.LicenseService.LicenseChangeHandler;
import org.springframework.beans.factory.InitializingBean;

/**
 * Mock implementation of DescriptorService for testing purposes.
 * Provides stub methods for descriptor and license operations.
 *
 * @author lucastancapiano
 */
public class MockDescriptorService implements DescriptorService, InitializingBean, LicenseChangeHandler {

	/**
	 * {@inheritDoc}
	 *
	 * @param licenseDescriptor the license descriptor
	 */
	@Override
	public void onLicenseChange(LicenseDescriptor licenseDescriptor) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onLicenseFail() {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws Exception if initialization fails
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the server descriptor
	 */
	@Override
	public Descriptor getServerDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the current repository descriptor
	 */
	@Override
	public Descriptor getCurrentRepositoryDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the installed repository descriptor
	 */
	@Override
	public Descriptor getInstalledRepositoryDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the license descriptor
	 */
	@Override
	public LicenseDescriptor getLicenseDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the loaded license string
	 */
	@Override
	public String loadLicense() {
		// TODO Auto-generated method stub
		return null;
	}

}
