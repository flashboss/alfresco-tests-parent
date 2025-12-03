package org.alfresco.mock.test;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.extensions.surf.util.AbstractLifecycleBean;

/**
 * Mock implementation of MockImporterBootstrap for testing purposes.
 *
 * @author lucastancapiano
 */
public class MockImporterBootstrap extends AbstractLifecycleBean {

	private Logger logger = getLogger(getClass());

	private boolean useExistingStore = false;
	private List<Properties> bootstrapViews;

	@Override
	protected void onBootstrap(ApplicationEvent event) {
		logger.debug(useExistingStore + "");
		logger.debug(bootstrapViews + "");
	}

	@Override
	protected void onShutdown(ApplicationEvent event) {
		// TODO Auto-generated method stub
		
	}
	
    public void setUseExistingStore(boolean useExistingStore)
    {
        this.useExistingStore = useExistingStore;
    }
    
    public void setBootstrapViews(List<Properties> bootstrapViews)
    {
        this.bootstrapViews = bootstrapViews;
    }

}
