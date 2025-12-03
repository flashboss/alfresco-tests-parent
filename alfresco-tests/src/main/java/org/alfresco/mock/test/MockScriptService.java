package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.repo.processor.ScriptServiceImpl;
import org.alfresco.service.cmr.repository.ScriptProcessor;

/**
 * Mock implementation of the Alfresco ScriptService for testing purposes.
 * Extends ScriptServiceImpl to provide a configurable script processor.
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
	 * @return the result
	 */
	protected ScriptProcessor lookupScriptProcessor(String name) {
		return scriptProcessor;
	}

	/**
	 * Gets the script processor.
	 *
	 * @return the script processor
	 */
	public ScriptProcessor getScriptProcessor() {
		return scriptProcessor;
	}

	/**
	 * Sets the script processor.
	 *
	 * @param scriptProcessor the script processor to set
	 */
	public void setScriptProcessor(ScriptProcessor scriptProcessor) {
		this.scriptProcessor = scriptProcessor;
	}

}
