package org.alfresco.mock.test.activiti;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.getInstance;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.activiti.engine.impl.util.IoUtil.readInputStream;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.SpringBeanFactoryProxyMap;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.ResourceActivitiTestCase;
import org.activiti.engine.repository.Deployment;
import org.alfresco.mock.NodeUtils;
import org.alfresco.mock.ZipUtils;
import org.alfresco.mock.test.MockContentService;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.MimetypeService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.subethamail.smtp.server.SMTPServer;

/**
 * Parent test case for all the tests of the application. It has the groups
 * constant names used for the test, the initial creation of the environment and
 * the final clean of teh environment
 * 
 * @author lucastancapiano
 *
 */
public abstract class AbstractActivitiForm extends ResourceActivitiTestCase {

	protected NodeRef workspace;
	protected NodeRef archive;
	protected NodeRef site;

	public AbstractActivitiForm() {
		super("test-module-context.xml");
	}

	/**
	 * Inits the mail server and create the demo users and groups
	 */
	public void init() {
		ActivitiProcessEngineConfiguration activitiProcessEngineConfiguration = (ActivitiProcessEngineConfiguration) processEngineConfiguration;
		FileFolderService fileFolderService = activitiProcessEngineConfiguration.getFileFolderService();
		SearchService searchService = activitiProcessEngineConfiguration.getSearchService();
		// elimino i vecchi documenti
		ResultSet nodes = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
				SearchService.LANGUAGE_FTS_ALFRESCO, "PATH:\"*\"");
		if (nodes.length() > 0)
			for (NodeRef node : nodes.getNodeRefs())
				fileFolderService.delete(node);
		try {
			FileUtils.deleteDirectory(new File(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_WORKSPACE));
			FileUtils.deleteDirectory(new File(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_ARCHIVE));
			FileUtils.deleteDirectory(ZipUtils.TEMP_DIR);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// creo le directory iniziali per i pdv, gli rdv e il repository
		NodeRef root = insertFolder(new NodeRef(new StoreRef("", ""), ""), ".");
		workspace = insertFolder(root, StoreRef.PROTOCOL_WORKSPACE);
		archive = insertFolder(root, StoreRef.PROTOCOL_ARCHIVE);
		site = insertFolder(workspace, "cm:Site");

		// STARTING MAIL SERVER
		startMailServer();

		// TEST GROUPS AND USERS
		initDemoGroups(identityService);
		initDemoUsers(identityService);
	}

	/**
	 * Ends the environment cleaning the database and stopping the mail server
	 */
	public void end() {
		// CLEANING DB
		deleteAllIdentities(identityService);
		deleteAllHistories(historyService);
		deleteAllIDeployments(repositoryService);

		// STOP MAIL SERVER
		stopMailServer();

	}

	protected NodeRef insertFolder(NodeRef parent, String name) {
		return NodeUtils.insertFolder(parent, name,
				((ActivitiProcessEngineConfiguration) processEngineConfiguration).getFileFolderService());
	}

	protected NodeRef insertDocument(NodeRef parent, String name, String text, Map<QName, Serializable> properties) {
		ActivitiProcessEngineConfiguration activitiProcessEngineConfiguration = (ActivitiProcessEngineConfiguration) processEngineConfiguration;
		NodeService nodeService = activitiProcessEngineConfiguration.getNodeService();
		ContentService contentService = activitiProcessEngineConfiguration.getContentService();
		MimetypeService mimetypeService = activitiProcessEngineConfiguration.getMimetypeService();
		return NodeUtils.insertDocument(parent, name, text, properties, nodeService, contentService, mimetypeService);
	}

	protected NodeRef insertZip(NodeRef parent, String zipName, String entryName, String text,
			Map<QName, Serializable> properties) throws IOException {
		ActivitiProcessEngineConfiguration activitiProcessEngineConfiguration = (ActivitiProcessEngineConfiguration) processEngineConfiguration;
		NodeService nodeService = activitiProcessEngineConfiguration.getNodeService();
		ContentService contentService = activitiProcessEngineConfiguration.getContentService();
		return ZipUtils.insertZip(parent, zipName, entryName, text, properties, nodeService, contentService);
	}

	@Override
	protected void initializeProcessEngine() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		xmlBeanDefinitionReader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_XSD);
		Resource springResource = new ClassPathResource(activitiConfigurationResource);
		xmlBeanDefinitionReader.loadBeanDefinitions(springResource);
		ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) beanFactory
				.getBean("processEngineConfiguration");
		Map<Object, Object> beans = new SpringBeanFactoryProxyMap(beanFactory);
		for (String bName : beanFactory.getBeanDefinitionNames()) {
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(bName);
			boolean isAbstract = beanDefinition.isAbstract();
			String className = beanDefinition.getBeanClassName();
			if (className != null) {
				String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
				String parentName = beanDefinition.getParentName();
				if (!isAbstract) {
					if (!bName.equals(simpleClassName) && parentName != null && parentName.equals("baseJavaDelegate")) {
						beanFactory.registerAlias(bName, simpleClassName);
					}
					if (className.equals(PropertyPlaceholderConfigurer.class.getName())) {
						Object bean = beanFactory.getBean(bName);
						PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = (PropertyPlaceholderConfigurer) bean;
						propertyPlaceholderConfigurer.postProcessBeanFactory(beanFactory);
					}
				}
			}
		}
		processEngineConfiguration.setBeans(beans);
		processEngine = processEngineConfiguration.buildProcessEngine();
	}

	/**
	 * The SMTP server to start to send the mails to the users.
	 */
	private SMTPServer smtpServer;

	public SMTPServer getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(SMTPServer smtpServer) {
		this.smtpServer = smtpServer;
	}

	/**
	 * Starts the mail server
	 */
	public void startMailServer() {
		MockMessageHandlerFactory myFactory = new MockMessageHandlerFactory();
		smtpServer = new SMTPServer(myFactory);
		smtpServer.setPort(25000);
		smtpServer.start();
	}

	/**
	 * Stops the mail server
	 */
	public void stopMailServer() {
		smtpServer.stop();
	}

	/**
	 * Create the user
	 * 
	 * @param identityService The service to create the users
	 * @param userId          Id of the user
	 * @param firstName       First name of the user
	 * @param lastName        Last name of the user
	 * @param password        Password of the user to login
	 * @param email           Email of the user. It will be used to receive the
	 *                        messages
	 * @param imageResource   Image available in the profile view of the user
	 * @param groups          groups of the users. The user can be an admin, staff
	 *                        or traveler
	 * @param userInfo        Other custom properties for the user
	 */
	public void createUser(IdentityService identityService, String userId, String firstName, String lastName,
			String password, String email, String imageResource, List<String> groups, List<String> userInfo) {

		if (identityService.createUserQuery().userId(userId).count() == 0) {

			// Following data can already be set by demo setup script

			User user = identityService.newUser(userId);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setEmail(email);
			identityService.saveUser(user);

			if (groups != null) {
				for (String group : groups) {
					identityService.createMembership(userId, group);
				}
			}
		}

		// Following data is not set by demo setup script

		// image
		if (imageResource != null) {
			byte[] pictureBytes = readInputStream(this.getClass().getClassLoader().getResourceAsStream(imageResource),
					null);
			Picture picture = new Picture(pictureBytes, "image/jpeg");
			identityService.setUserPicture(userId, picture);
		}

		// user info
		if (userInfo != null) {
			for (int i = 0; i < userInfo.size(); i += 2) {
				identityService.setUserInfo(userId, userInfo.get(i), userInfo.get(i + 1));
			}
		}

	}

	/**
	 * Create the group of the user
	 * 
	 * @param identityService The service to create the groups
	 * @param groupId         Id of the group
	 * @param type            Type of the group. It can be assignment for travelers
	 *                        and staff or security role for admin and user
	 */
	public void createGroup(IdentityService identityService, String groupId, String type) {
		if (identityService.createGroupQuery().groupId(groupId).count() == 0) {
			Group newGroup = identityService.newGroup(groupId);
			newGroup.setName(groupId.substring(0, 1).toUpperCase() + groupId.substring(1));
			newGroup.setType(type);
			identityService.saveGroup(newGroup);
		}
	}

	/**
	 * Delete all current users and groups from the database. To use only for tests
	 * 
	 * @param identityService The service to delete users and groups
	 */
	public void deleteAllIdentities(IdentityService identityService) {
		List<User> users = identityService.createUserQuery().list();
		for (User user : users) {
			identityService.deleteUser(user.getId());
		}
		List<Group> groups = identityService.createGroupQuery().list();
		for (Group group : groups) {
			identityService.deleteGroup(group.getId());
		}
	}

	/**
	 * Delete all history of the workflows. To use only only for tests
	 * 
	 * @param historyService The service where delete all historical data
	 */
	public void deleteAllHistories(HistoryService historyService) {
		List<HistoricProcessInstance> historicInstances = historyService.createHistoricProcessInstanceQuery().list();
		for (HistoricProcessInstance historicProcessInstance : historicInstances)
			try {
				historyService.deleteHistoricProcessInstance(historicProcessInstance.getId());
			} catch (ActivitiObjectNotFoundException ex) {

			}
	}

	/**
	 * Delete all workflows. To use only for tests
	 * 
	 * @param repositoryService The service where delete all workflows.
	 */
	public void deleteAllIDeployments(RepositoryService repositoryService) {
		List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
		for (Deployment deployment : deployments) {
			repositoryService.deleteDeployment(deployment.getId());
		}
	}

	/**
	 * Utility function to get a date through parameters
	 * 
	 * @param number A list of number representing in the order the year, month,
	 *               day, hour and minutes. Year, month and day are mandatory. Hour
	 *               and minutes are optional
	 * @return The calculated date with the sent parameters
	 */
	public static Date getDate(int... number) {
		Calendar c1 = getInstance();
		c1.set(number[0], number[1], number[2], number.length > 3 ? number[3] : 0, number.length > 4 ? number[4] : 0);
		return c1.getTime();
	}

	/**
	 * Utility function to add hours on a date
	 * 
	 * @param date  The date where add the hours
	 * @param hours The hours to add on the date
	 * @return The calculated date with the hours to add
	 */
	public static Date addHours(Date date, int hours) {
		Calendar cal = getInstance(); // creates calendar
		cal.setTime(date); // sets calendar time/date
		cal.add(HOUR_OF_DAY, hours); // adds hour
		return cal.getTime(); // returns new date object, one hour in the future
	}

	/**
	 * Get a diff between two dates
	 * 
	 * @param date1    the oldest date
	 * @param date2    the newest date
	 * @param timeUnit the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long differenceBetween(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date1.getTime() - date2.getTime();
		return timeUnit.convert(diffInMillies, MILLISECONDS);
	}

	/**
	 * Verify if the passed user is an admin
	 * 
	 * @param user            The user to verify
	 * @param identityService The service where find the user informations
	 * @return true if the user is an admin, false if a simple user
	 */
	public static boolean isAdmin(String user, IdentityService identityService) {
		return identityService.createUserQuery().userId(Authentication.getAuthenticatedUserId()).memberOfGroup("admin")
				.count() > 0;
	}

	public abstract void initDemoUsers(IdentityService identityService);

	public abstract void initDemoGroups(IdentityService identityService);

}