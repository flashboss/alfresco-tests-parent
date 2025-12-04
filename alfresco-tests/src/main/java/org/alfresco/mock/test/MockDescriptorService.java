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
public class MockDescriptorService implements DescriptorService, InitializingBean, LicenseChangeHandler {	/**
	 * On license change.
	 *
	 * @param licenseDescriptor the license descriptor
	 */


	@Override
	public void onLicenseChange(LicenseDescriptor licenseDescriptor) {
		// TODO Auto-generated method stub
		
	}	/**
	 * On license fail.
	 *
	 */


	@Override
	public void onLicenseFail() {
		// TODO Auto-generated method stub
		
	}	/**
	 * After properties set.
	 *
	 */


	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}	/**
	 * Get server descriptor.
	 *
	 * @return the descriptor
	 */


	@Override
	public Descriptor getServerDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get current repository descriptor.
	 *
	 * @return the descriptor
	 */


	@Override
	public Descriptor getCurrentRepositoryDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get installed repository descriptor.
	 *
	 * @return the descriptor
	 */


	@Override
	public Descriptor getInstalledRepositoryDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Get license descriptor.
	 *
	 * @return the license descriptor
	 */


	@Override
	public LicenseDescriptor getLicenseDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Load license.
	 *
	 * @return the string
	 */


	@Override
	public String loadLicense() {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Load license.
	 *
	 * @param licenseStream the license stream
	 * @return the string
	 */


	@Override
	public String loadLicense(InputStream licenseStream) {
		// TODO Auto-generated method stub
		return null;
	}	/**
	 * Is bootstrapped.
	 *
	 * @return the boolean
	 */


	@Override
	public boolean isBootstrapped() {
		// TODO Auto-generated method stub
		return false;
	}

}
