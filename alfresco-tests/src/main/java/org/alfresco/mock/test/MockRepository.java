package org.alfresco.mock.test;

import org.alfresco.repo.model.Repository;

/**
 * Mock implementation of Repository for testing purposes.
 * Provides an empty context initialization.
 *
 * @author lucastancapiano
 */
public class MockRepository extends Repository {

	/**
	 * Initializes the context.
	 * This mock implementation does nothing.
	 */
	protected void initContext() {
	}

}
