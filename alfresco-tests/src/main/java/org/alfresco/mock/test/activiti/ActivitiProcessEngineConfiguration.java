package org.alfresco.mock.test.activiti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.activiti.engine.impl.bpmn.data.ItemInstance;
import org.activiti.engine.impl.bpmn.deployer.BpmnDeployer;
import org.activiti.engine.impl.bpmn.parser.BpmnParseHandlers;
import org.activiti.engine.impl.bpmn.parser.BpmnParser;
import org.activiti.engine.impl.bpmn.parser.factory.DefaultActivityBehaviorFactory;
import org.activiti.engine.impl.bpmn.parser.factory.DefaultListenerFactory;
import org.activiti.engine.impl.bpmn.webservice.MessageInstance;
import org.activiti.engine.impl.cfg.DefaultBpmnParseFactory;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.impl.persistence.deploy.Deployer;
import org.activiti.engine.impl.variable.CustomObjectType;
import org.activiti.engine.impl.variable.DefaultVariableTypes;
import org.activiti.engine.impl.variable.VariableType;
import org.activiti.engine.parse.BpmnParseHandler;
import org.alfresco.mock.test.activiti.type.SerializedBooleanType;
import org.alfresco.mock.test.activiti.type.SerializedByteArrayType;
import org.alfresco.mock.test.activiti.type.SerializedDateType;
import org.alfresco.mock.test.activiti.type.SerializedDoubleType;
import org.alfresco.mock.test.activiti.type.SerializedIntegerType;
import org.alfresco.mock.test.activiti.type.SerializedLongType;
import org.alfresco.mock.test.activiti.type.SerializedNullType;
import org.alfresco.mock.test.activiti.type.SerializedSerializableType;
import org.alfresco.mock.test.activiti.type.SerializedShortType;
import org.alfresco.mock.test.activiti.type.SerializedStringType;
import org.alfresco.mock.test.script.MockLogger;
import org.alfresco.repo.jscript.Search;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.MimetypeService;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.SearchService;

public class ActivitiProcessEngineConfiguration extends StandaloneProcessEngineConfiguration {

	private SearchService searchService;
	private FileFolderService fileFolderService;
	private NodeService nodeService;
	private ContentService contentService;
	private MimetypeService mimetypeService;
	private ServiceRegistry serviceRegistry;
	private Search searchScript;
	private MockLogger loggerScript;

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public FileFolderService getFileFolderService() {
		return fileFolderService;
	}

	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public ContentService getContentService() {
		return contentService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public MimetypeService getMimetypeService() {
		return mimetypeService;
	}

	public void setMimetypeService(MimetypeService mimetypeService) {
		this.mimetypeService = mimetypeService;
	}

	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	public Search getSearchScript() {
		return searchScript;
	}

	public void setSearchScript(Search searchScript) {
		this.searchScript = searchScript;
	}

	public MockLogger getLoggerScript() {
		return loggerScript;
	}

	public void setLoggerScript(MockLogger loggerScript) {
		this.loggerScript = loggerScript;
	}

	@Override
	protected void initExpressionManager() {
		if (expressionManager == null) {
			expressionManager = new SerializableExpressionManager(beans);
		}
	}

	@Override
	protected void initVariableTypes() {
		if (variableTypes == null) {
			variableTypes = new DefaultVariableTypes();
			if (customPreVariableTypes != null) {
				for (VariableType customVariableType : customPreVariableTypes) {
					variableTypes.addType(customVariableType);
				}
			}
			variableTypes.addType(new SerializedNullType());
			variableTypes.addType(new SerializedStringType());
			variableTypes.addType(new SerializedBooleanType());
			variableTypes.addType(new SerializedShortType());
			variableTypes.addType(new SerializedIntegerType());
			variableTypes.addType(new SerializedLongType());
			variableTypes.addType(new SerializedDateType());
			variableTypes.addType(new SerializedDoubleType());
			variableTypes.addType(new SerializedByteArrayType());
			variableTypes.addType(new SerializedSerializableType());
			variableTypes.addType(new CustomObjectType("item", ItemInstance.class));
			variableTypes.addType(new CustomObjectType("message", MessageInstance.class));
			if (customPostVariableTypes != null) {
				for (VariableType customVariableType : customPostVariableTypes) {
					variableTypes.addType(customVariableType);
				}
			}
		}
	}

	protected Collection<? extends Deployer> getDefaultDeployers() {
		List<Deployer> defaultDeployers = new ArrayList<Deployer>();

		if (bpmnDeployer == null) {
			bpmnDeployer = new BpmnDeployer();
		}

		bpmnDeployer.setExpressionManager(expressionManager);
		bpmnDeployer.setIdGenerator(idGenerator);

		if (bpmnParseFactory == null) {
			bpmnParseFactory = new DefaultBpmnParseFactory();
		}

		if (activityBehaviorFactory == null) {
			DefaultActivityBehaviorFactory defaultActivityBehaviorFactory = new MockActivityBehaviorFactory();
			defaultActivityBehaviorFactory.setExpressionManager(expressionManager);
			activityBehaviorFactory = defaultActivityBehaviorFactory;
		}

		if (listenerFactory == null) {
			DefaultListenerFactory defaultListenerFactory = new MockListenerFactory();
			defaultListenerFactory.setExpressionManager(expressionManager);
			listenerFactory = defaultListenerFactory;
		}

		if (bpmnParser == null) {
			bpmnParser = new BpmnParser();
		}

		bpmnParser.setExpressionManager(expressionManager);
		bpmnParser.setBpmnParseFactory(bpmnParseFactory);
		bpmnParser.setActivityBehaviorFactory(activityBehaviorFactory);
		bpmnParser.setListenerFactory(listenerFactory);

		List<BpmnParseHandler> parseHandlers = new ArrayList<BpmnParseHandler>();
		if (getPreBpmnParseHandlers() != null) {
			parseHandlers.addAll(getPreBpmnParseHandlers());
		}
		parseHandlers.addAll(getDefaultBpmnParseHandlers());
		if (getPostBpmnParseHandlers() != null) {
			parseHandlers.addAll(getPostBpmnParseHandlers());
		}

		BpmnParseHandlers bpmnParseHandlers = new BpmnParseHandlers();
		bpmnParseHandlers.addHandlers(parseHandlers);
		bpmnParser.setBpmnParserHandlers(bpmnParseHandlers);

		bpmnDeployer.setBpmnParser(bpmnParser);

		defaultDeployers.add(bpmnDeployer);
		return defaultDeployers;
	}

}
