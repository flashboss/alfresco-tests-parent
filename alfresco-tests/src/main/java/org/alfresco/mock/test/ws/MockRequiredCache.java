package org.alfresco.mock.test.ws;

import org.springframework.extensions.webscripts.Description.RequiredCache;

/**
 * Mock implementation of MockRequiredCache for testing purposes.
 *
 * @author vige
 */
public class MockRequiredCache implements RequiredCache {

	@Override
	/**
	 * Get never cache.
	 *
	 * @return the result
	 */
	public boolean getNeverCache() {
		return false;
	}

	@Override
	/**
	 * Get is public.
	 *
	 * @return the result
	 */
	public boolean getIsPublic() {
		return false;
	}

	@Override
	/**
	 * Get must revalidate.
	 *
	 * @return the result
	 */
	public boolean getMustRevalidate() {
		return false;
	}

}
