package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.repo.processor.ScriptServiceImpl;
import org.alfresco.service.cmr.repository.ScriptProcessor;

/**
 * Mock implementation of the MockScriptService class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockScriptService extends ScriptServiceImpl implements Serializable {

	private ScriptProcessor scriptProcessor;

	@Override
	protected ScriptProcessor lookupScriptProcessor(String name) {
		return scriptProcessor;
	}

	public ScriptProcessor getScriptProcessor() {
		return scriptProcessor;
	}

	public void setScriptProcessor(ScriptProcessor scriptProcessor) {
		this.scriptProcessor = scriptProcessor;
	}

}
