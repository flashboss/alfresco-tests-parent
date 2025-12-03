package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.repo.processor.ScriptServiceImpl;
import org.alfresco.service.cmr.repository.ScriptProcessor;

/**
 * Mock implementation of ScriptService for testing purposes.
 * Extends ScriptServiceImpl with custom script processor support.
 * 
 * @author vige
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
