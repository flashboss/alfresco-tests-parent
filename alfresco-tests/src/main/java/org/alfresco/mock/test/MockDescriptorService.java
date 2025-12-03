package org.alfresco.mock.test;

import java.io.InputStream;

import org.alfresco.service.descriptor.Descriptor;
import org.alfresco.service.descriptor.DescriptorService;
import org.alfresco.service.license.LicenseDescriptor;
import org.alfresco.service.license.LicenseService.LicenseChangeHandler;
import org.springframework.beans.factory.InitializingBean;

/**
 * Mock implementation of the Alfresco DescriptorService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockDescriptorService implements DescriptorService, InitializingBean, LicenseChangeHandler {

	@Override
	/**
	 * On license change.
	 *
	 * @param licenseDescriptor the license descriptor
	 */
	public void onLicenseChange(LicenseDescriptor licenseDescriptor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * On license fail.
	 *
	 */
	public void onLicenseFail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * After properties set.
	 *
	 */
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Get server descriptor.
	 *
	 * @return the descriptor
	 */
	public Descriptor getServerDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get current repository descriptor.
	 *
	 * @return the descriptor
	 */
	public Descriptor getCurrentRepositoryDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get installed repository descriptor.
	 *
	 * @return the descriptor
	 */
	public Descriptor getInstalledRepositoryDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get license descriptor.
	 *
	 * @return the license descriptor
	 */
	public LicenseDescriptor getLicenseDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Load license.
	 *
	 * @return the string
	 */
	public String loadLicense() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Load license.
	 *
	 * @param licenseStream the license stream
	 * @return the string
	 */
	public String loadLicense(InputStream licenseStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Is bootstrapped.
	 *
	 * @return the boolean
	 */
	public boolean isBootstrapped() {
		// TODO Auto-generated method stub
		return false;
	}

}
