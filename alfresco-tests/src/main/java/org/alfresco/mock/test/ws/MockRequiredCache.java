package org.alfresco.mock.test.ws;

import org.springframework.extensions.webscripts.Description.RequiredCache;

/**
 * Mock implementation of the {@link RequiredCache} interface for testing purposes.
 * This class provides default cache settings for WebScripts in test scenarios,
 * all returning {@code false} to indicate no caching requirements.
 *
 * @author lucastancapiano
 */
public class MockRequiredCache implements RequiredCache {

	/**
	 * {@inheritDoc}
	 * Indicates whether the response should never be cached.
	 *
	 * @return {@code false} indicating caching is allowed.
	 */
	@Override
	public boolean getNeverCache() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Indicates whether the cache is public.
	 *
	 * @return {@code false} indicating the cache is not public.
	 */
	@Override
	public boolean getIsPublic() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Indicates whether revalidation is required for cached content.
	 *
	 * @return {@code false} indicating revalidation is not required.
	 */
	@Override
	public boolean getMustRevalidate() {
		return false;
	}

}
