package org.alfresco.mock.test;

import java.util.List;
import java.util.Properties;

import org.springframework.context.ApplicationEvent;
import org.springframework.extensions.surf.util.AbstractLifecycleBean;

/**
* Mock implementation of the MockImporterBootstrap class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockImporterBootstrap extends AbstractLifecycleBean {

/**
* Performs on bootstrap.
* @param event the event
 */
	@Override
	protected void onBootstrap(ApplicationEvent event) {
		// TODO Auto-generated method stub
		
	}

/**
* Performs on shutdown.
* @param event the event
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
    public void setUseExistingStore(boolean useExistingStore)
    {
    	// TODO Auto-generated method stub
    	
    }
    
/**
* Sets the bootstrap views.
*
* @param bootstrapViews the list of bootstrap views
 */
    public void setBootstrapViews(List<Properties> bootstrapViews)
    {
    	// TODO Auto-generated method stub
    	
    }

}
