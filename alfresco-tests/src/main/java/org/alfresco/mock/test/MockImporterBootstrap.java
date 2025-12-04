package org.alfresco.mock.test;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.extensions.surf.util.AbstractLifecycleBean;

/**
 * Mock implementation of ImporterBootstrap for testing purposes.
 * 
 * @author vige
 */
public class MockImporterBootstrap extends AbstractLifecycleBean {
	/** The logger. */
	private Logger logger = getLogger(getClass());
	
    /** The use existing store. */
    private boolean useExistingStore = false;
    /** The bootstrap views. */
    private List<Properties> bootstrapViews;	/**
	 * On bootstrap.
	 *
	 * @param event the event
	 */
	@Override
	protected void onBootstrap(ApplicationEvent event) {
		logger.debug(useExistingStore + "");
		logger.debug(bootstrapViews + "");
	}
	/**
	 * On shutdown.
	 *
	 * @param event the event
	 */
	@Override
	protected void onShutdown(ApplicationEvent event) {
		// TODO Auto-generated method stub
		
	}
	/**
     * Set use existing store.
     *
     * @param useExistingStore the use existing store
     */
    public void setUseExistingStore(boolean useExistingStore)
    {
        this.useExistingStore = useExistingStore;
    }
	/**
     * Set bootstrap views.
     *
     * @param bootstrapViews the bootstrap views
     */
    public void setBootstrapViews(List<Properties> bootstrapViews)
    {
        this.bootstrapViews = bootstrapViews;
    }

}
