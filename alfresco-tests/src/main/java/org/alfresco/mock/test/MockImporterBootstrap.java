package org.alfresco.mock.test;

import java.util.List;
import java.util.Properties;

import org.springframework.context.ApplicationEvent;
import org.springframework.extensions.surf.util.AbstractLifecycleBean;

public class MockImporterBootstrap extends AbstractLifecycleBean {
	
    private boolean useExistingStore = false;
    private List<Properties> bootstrapViews;

	@Override
	protected void onBootstrap(ApplicationEvent event) {
		// TODO Auto-generated method stub
		
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
