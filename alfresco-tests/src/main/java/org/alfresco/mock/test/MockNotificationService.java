package org.alfresco.mock.test;

import java.util.List;

import org.alfresco.service.cmr.notification.NotificationContext;
import org.alfresco.service.cmr.notification.NotificationProvider;
import org.alfresco.service.cmr.notification.NotificationService;

public class MockNotificationService implements NotificationService {

	@Override
	public void register(NotificationProvider notificationProvider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getNotificationProviders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String notificationProvider) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendNotification(String notificationProvider, NotificationContext notificationContext) {
		// TODO Auto-generated method stub
		
	}

}
