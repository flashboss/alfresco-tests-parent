package org.alfresco.mock.test.ws;

import org.springframework.extensions.webscripts.Description.RequiredCache;

public class MockRequiredCache implements RequiredCache {

	@Override
	public boolean getNeverCache() {
		return false;
	}

	@Override
	public boolean getIsPublic() {
		return false;
	}

	@Override
	public boolean getMustRevalidate() {
		return false;
	}

}
