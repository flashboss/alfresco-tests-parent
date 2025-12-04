package org.alfresco.mock.test.ws;

import org.springframework.extensions.webscripts.Description.RequiredCache;

/**
 * Mock implementation of RequiredCache for testing purposes.
 * 
 * @author vige
 */
public class MockRequiredCache implements RequiredCache {
	/**
	 * Get never cache.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean getNeverCache() {
		return false;
	}
	/**
	 * Get is public.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean getIsPublic() {
		return false;
	}
	/**
	 * Get must revalidate.
	 *
	 * @return the boolean
	 */
	@Override
	public boolean getMustRevalidate() {
		return false;
	}

}
