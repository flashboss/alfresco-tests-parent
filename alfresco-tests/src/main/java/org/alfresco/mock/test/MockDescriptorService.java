package org.alfresco.mock.test;

import java.io.InputStream;

import org.alfresco.service.descriptor.Descriptor;
import org.alfresco.service.descriptor.DescriptorService;
import org.alfresco.service.license.LicenseDescriptor;
import org.alfresco.service.license.LicenseService.LicenseChangeHandler;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author vige
 */
public class MockDescriptorService implements DescriptorService, InitializingBean, LicenseChangeHandler {

	@Override
	public void onLicenseChange(LicenseDescriptor licenseDescriptor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLicenseFail() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Descriptor getServerDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Descriptor getCurrentRepositoryDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Descriptor getInstalledRepositoryDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LicenseDescriptor getLicenseDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loadLicense() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loadLicense(InputStream licenseStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBootstrapped() {
		// TODO Auto-generated method stub
		return false;
	}

}
