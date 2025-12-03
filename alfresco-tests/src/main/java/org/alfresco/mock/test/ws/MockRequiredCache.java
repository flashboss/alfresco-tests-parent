package org.alfresco.mock.test.ws;

import org.springframework.extensions.webscripts.Description.RequiredCache;

/**
 * Mock implementation of the MockRequiredCache class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockRequiredCache implements RequiredCache {

	/**


	 * {@inheritDoc}


	 */


	@Override
	public boolean getNeverCache() {
		return false;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public boolean getIsPublic() {
		return false;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public boolean getMustRevalidate() {
		return false;
	}

}
