package org.alfresco.mock.test;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.extensions.surf.util.AbstractLifecycleBean;

/**
 * Mock implementation of importer bootstrap for testing purposes.
 * Provides lifecycle methods for bootstrap and shutdown events.
 *
 * @author lucastancapiano
 */
public class MockImporterBootstrap extends AbstractLifecycleBean {

	/** The logger instance. */
	private Logger logger = getLogger(getClass());

	/** Flag indicating whether to use existing store. */
	private boolean useExistingStore = false;

	/** The list of bootstrap view properties. */
	private List<Properties> bootstrapViews;

	/**
	 * {@inheritDoc}
	 *
	 * @param event the bootstrap application event
	 */
	@Override
	protected void onBootstrap(ApplicationEvent event) {
		logger.debug(useExistingStore + "");
		logger.debug(bootstrapViews + "");
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param event the shutdown application event
	 */
	@Override
	protected void onShutdown(ApplicationEvent event) {
		// TODO Auto-generated method stub
	}

	/**
	 * Sets whether to use existing store.
	 *
	 * @param useExistingStore true to use existing store, false otherwise
	 */
	public void setUseExistingStore(boolean useExistingStore) {
		this.useExistingStore = useExistingStore;
	}

	/**
	 * Sets the bootstrap views.
	 *
	 * @param bootstrapViews the list of bootstrap view properties
	 */
	public void setBootstrapViews(List<Properties> bootstrapViews) {
		this.bootstrapViews = bootstrapViews;
	}

}
