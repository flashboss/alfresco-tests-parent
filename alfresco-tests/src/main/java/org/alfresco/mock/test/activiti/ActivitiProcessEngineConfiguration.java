package org.alfresco.mock.test.activiti;

import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
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
}
