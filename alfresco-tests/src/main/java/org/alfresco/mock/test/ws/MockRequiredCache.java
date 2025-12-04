package org.alfresco.mock.test.ws;

import org.springframework.extensions.webscripts.Description.RequiredCache;

/**
 * Mock implementation of RequiredCache for testing purposes.
 * 
 * @author vige
 */
public class MockRequiredCache implements RequiredCache {

	@Override
 /**
 * Get never cache.
 *
 * @return the boolean
 */
	public boolean getNeverCache() {
		return false;
	}

	@Override
 /**
 * Get is public.
 *
 * @return the boolean
 */
	public boolean getIsPublic() {
		return false;
	}

	@Override
 /**
 * Get must revalidate.
 *
 * @return the boolean
 */
	public boolean getMustRevalidate() {
		return false;
	}

}
