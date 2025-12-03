package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.repo.processor.ScriptServiceImpl;
import org.alfresco.service.cmr.repository.ScriptProcessor;

/**
 * Mock implementation of the Alfresco ScriptService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockScriptService extends ScriptServiceImpl implements Serializable {

	/** The script processor. */
	private ScriptProcessor scriptProcessor;

	@Override
	/**
	 * Lookup script processor.
	 *
	 * @param name the name
	 * @return the script processor
	 */
	protected ScriptProcessor lookupScriptProcessor(String name) {
		return scriptProcessor;
	}

	/**
	 * Get script processor.
	 *
	 * @return the script processor
	 */
	public ScriptProcessor getScriptProcessor() {
		return scriptProcessor;
	}

	/**
	 * Set script processor.
	 *
	 * @param scriptProcessor the script processor
	 */
	public void setScriptProcessor(ScriptProcessor scriptProcessor) {
		this.scriptProcessor = scriptProcessor;
	}

}
