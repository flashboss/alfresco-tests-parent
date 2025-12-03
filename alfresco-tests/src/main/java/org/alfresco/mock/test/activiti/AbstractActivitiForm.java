package org.alfresco.mock.test.activiti;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.getInstance;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.activiti.engine.impl.util.IoUtil.readInputStream;
import static org.alfresco.service.cmr.repository.StoreRef.STORE_REF_WORKSPACE_SPACESSTORE;

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
import org.alfresco.mock.test.MockNodeService;
import org.alfresco.mock.test.MockVersionService;
import org.alfresco.mock.test.script.MockLogger;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.jscript.ScriptUtils;
import org.alfresco.repo.jscript.Search;
import org.alfresco.repo.site.SiteModel;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.namespace.NamespaceService;
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
 * Mock implementation of AbstractActivitiForm for testing purposes.
 *
 * @author vige
 */
public abstract class AbstractActivitiForm extends ResourceActivitiTestCase {

	protected NodeRef spacesStore;
	protected NodeRef archive;
	protected NodeRef sites;
	protected NodeRef shared;
	protected NodeRef companyHome;
	protected ActivitiScriptNode bpmPackage;
	protected Initiator initiator;

	public AbstractActivitiForm() {
		super("test-module-context.xml");
	}

	public void init(Map<String, Object> variables) {
		ActivitiProcessEngineConfiguration activitiProcessEngineConfiguration = (ActivitiProcessEngineConfiguration) processEngineConfiguration;
		ServiceRegistry serviceRegistry = activitiProcessEngineConfiguration.getServiceRegistry();
		NamespaceService namespaceService = serviceRegistry.getNamespaceService();
		namespaceService.registerNamespace(NamespaceService.APP_MODEL_PREFIX, NamespaceService.APP_MODEL_1_0_URI);
		namespaceService.registerNamespace(SiteModel.SITE_MODEL_PREFIX, SiteModel.SITE_MODEL_URL);
		namespaceService.registerNamespace(NamespaceService.CONTENT_MODEL_PREFIX,
				NamespaceService.CONTENT_MODEL_1_0_URI);
		namespaceService.registerNamespace(NamespaceService.SYSTEM_MODEL_PREFIX, NamespaceService.SYSTEM_MODEL_1_0_URI);

		// remove the old documents
		MockNodeService nodeService = (MockNodeService) serviceRegistry.getNodeService();
		MockVersionService versionService = (MockVersionService) serviceRegistry.getVersionService();
		nodeService.init();
		versionService.init();
		try {
			FileUtils.deleteDirectory(new File(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_WORKSPACE));
			FileUtils.deleteDirectory(new File(MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_ARCHIVE));
			FileUtils.deleteDirectory(ZipUtils.TEMP_DIR);
		} catch (IOException e) {
			e.printStackTrace();
		}

		initiator = new Initiator();
		// create the initial folders
		NodeRef root = insertFolder(new NodeRef(new StoreRef("", ""), ""), ".");
		NodeRef workspaceRoot = insertFolder(root, StoreRef.PROTOCOL_WORKSPACE);
		spacesStore = insertFolder(workspaceRoot, NamespaceService.APP_MODEL_PREFIX, STORE_REF_WORKSPACE_SPACESSTORE.getIdentifier());
		companyHome = insertFolder(spacesStore, NamespaceService.APP_MODEL_PREFIX, "company_home");
		NodeRef system = insertFolder(spacesStore, NamespaceService.SYSTEM_MODEL_PREFIX, "system");
		archive = insertFolder(root, StoreRef.PROTOCOL_ARCHIVE);
		sites = insertFolder(companyHome, SiteModel.SITE_MODEL_PREFIX, SiteModel.TYPE_SITES.getLocalName());
		shared = insertFolder(companyHome, NamespaceService.APP_MODEL_PREFIX, "shared");
		insertFolder(system, SiteModel.SITE_MODEL_PREFIX, "authorities");
		NodeRef workflow = insertFolder(spacesStore, "workflow");
		NodeRef packages = insertFolder(workflow, "packages");
		NodeRef bpmPackageFolder = insertFolder(packages, "pkg_919f220e-870a-4c56-ba11-5030ee5325f0");
		bpmPackage = new MockActivitiScriptNode(bpmPackageFolder, serviceRegistry);
		variables.put("bpm_package", bpmPackage);

		// STARTING MAIL SERVER
		startMailServer();

		// TEST GROUPS AND USERS
		initDemoGroups(identityService);
		initDemoUsers(identityService);

		Search search = activitiProcessEngineConfiguration.getSearchScript();
		MockLogger logger = activitiProcessEngineConfiguration.getLoggerScript();
		ScriptUtils utils = activitiProcessEngineConfiguration.getUtilsScript();
		variables.put("initiator", initiator);
		variables.put("search", search);
		variables.put("logger", logger);
		variables.put("utils", utils);
	}

	public void end() {
		// CLEANING DB
		deleteAllIdentities(identityService);
		deleteAllHistories(historyService);
		deleteAllIDeployments(repositoryService);

		// STOP MAIL SERVER
		stopMailServer();

	}

	protected NodeRef insertFolder(NodeRef parent, String name) {
		return NodeUtils.insertFolder(parent, name, ((ActivitiProcessEngineConfiguration) processEngineConfiguration)
				.getServiceRegistry().getFileFolderService());
	}

	protected NodeRef insertFolder(NodeRef parent, String prefix, String localName) {
		ServiceRegistry serviceRegistry = ((ActivitiProcessEngineConfiguration) processEngineConfiguration)
				.getServiceRegistry();
		FileFolderService fileFolderService = serviceRegistry.getFileFolderService();
		NamespaceService namespaceService = serviceRegistry.getNamespaceService();
		QName qname = QName.createQName(prefix, localName, namespaceService);
		return fileFolderService.create(parent, qname.getPrefixString(), ContentModel.TYPE_FOLDER).getNodeRef();
	}

	protected NodeRef insertDocument(NodeRef parent, String name, String text, Map<QName, Serializable> properties) {
		ActivitiProcessEngineConfiguration activitiProcessEngineConfiguration = (ActivitiProcessEngineConfiguration) processEngineConfiguration;
		return NodeUtils.insertDocument(parent, name, text, properties,
				activitiProcessEngineConfiguration.getServiceRegistry());
	}

	protected NodeRef insertZip(NodeRef parent, String zipName, String entryName, String text,
			Map<QName, Serializable> properties) throws IOException {
		ActivitiProcessEngineConfiguration activitiProcessEngineConfiguration = (ActivitiProcessEngineConfiguration) processEngineConfiguration;
		return ZipUtils.insertZip(parent, zipName, entryName, text, properties,
				activitiProcessEngineConfiguration.getServiceRegistry());
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

	private SMTPServer smtpServer;

	public SMTPServer getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(SMTPServer smtpServer) {
		this.smtpServer = smtpServer;
	}

	public void startMailServer() {
		MockMessageHandlerFactory myFactory = new MockMessageHandlerFactory();
		smtpServer = new SMTPServer(myFactory);
		smtpServer.setPort(25000);
		smtpServer.start();
	}

	public void stopMailServer() {
		smtpServer.stop();
	}

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

	public void createGroup(IdentityService identityService, String groupId, String type) {
		if (identityService.createGroupQuery().groupId(groupId).count() == 0) {
			Group newGroup = identityService.newGroup(groupId);
			newGroup.setName(groupId.substring(0, 1).toUpperCase() + groupId.substring(1));
			newGroup.setType(type);
			identityService.saveGroup(newGroup);
		}
	}

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

	public void deleteAllHistories(HistoryService historyService) {
		List<HistoricProcessInstance> historicInstances = historyService.createHistoricProcessInstanceQuery().list();
		for (HistoricProcessInstance historicProcessInstance : historicInstances)
			try {
				historyService.deleteHistoricProcessInstance(historicProcessInstance.getId());
			} catch (ActivitiObjectNotFoundException ex) {

			}
	}

	public void deleteAllIDeployments(RepositoryService repositoryService) {
		List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
		for (Deployment deployment : deployments) {
			repositoryService.deleteDeployment(deployment.getId());
		}
	}

	public static Date getDate(int... number) {
		Calendar c1 = getInstance();
		c1.set(number[0], number[1], number[2], number.length > 3 ? number[3] : 0, number.length > 4 ? number[4] : 0);
		return c1.getTime();
	}

	public static Date addHours(Date date, int hours) {
		Calendar cal = getInstance(); // creates calendar
		cal.setTime(date); // sets calendar time/date
		cal.add(HOUR_OF_DAY, hours); // adds hour
		return cal.getTime(); // returns new date object, one hour in the future
	}

	public static long differenceBetween(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date1.getTime() - date2.getTime();
		return timeUnit.convert(diffInMillies, MILLISECONDS);
	}

	public static boolean isAdmin(String user, IdentityService identityService) {
		return identityService.createUserQuery().userId(Authentication.getAuthenticatedUserId()).memberOfGroup("admin")
				.count() > 0;
	}

	public abstract void initDemoUsers(IdentityService identityService);

	public abstract void initDemoGroups(IdentityService identityService);

}