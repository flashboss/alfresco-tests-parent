package org.alfresco.mock.test;

import java.io.Serializable;

import org.alfresco.repo.processor.ScriptServiceImpl;
import org.alfresco.service.cmr.repository.ScriptProcessor;

/**
 * Mock implementation of ScriptService for testing purposes.
 * Provides configurable script processor lookup.
 *
 * @author lucastancapiano
 */
public class MockScriptService extends ScriptServiceImpl implements Serializable {

	/** The script processor. */
	private ScriptProcessor scriptProcessor;

	/**
	 * {@inheritDoc}
	 *
	 * @param name the processor name (ignored in mock)
	 * @return the configured script processor
	 */
	@Override
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
