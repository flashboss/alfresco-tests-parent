package org.alfresco.mock.test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.alfresco.repo.i18n.MessageDeployer;
import org.alfresco.repo.i18n.MessageService;
import org.alfresco.service.cmr.repository.StoreRef;

public class MockMessageService implements MessageService {

	@Override
	public void onEnableTenant() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisableTenant() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getMessage(String messageKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage(String messageKey, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage(String messageKey, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessage(String messageKey, Locale locale, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocale(Locale locale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setContentLocale(Locale locale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Locale getContentLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getNearestLocale(Locale templateLocale, Set<Locale> options) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale parseLocale(String localeStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerResourceBundle(String bundleBasePath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterResourceBundle(String resBundlePath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResourceBundle getRepoResourceBundle(StoreRef storeRef, String path, Locale locale) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getRegisteredBundles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register(MessageDeployer messageDeployer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getBaseBundleName(String resourceName) {
		// TODO Auto-generated method stub
		return null;
	}

}
