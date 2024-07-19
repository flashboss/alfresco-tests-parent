package org.alfresco.mock.test;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;

import org.alfresco.opencmis.dictionary.CMISDictionaryService;
import org.alfresco.opencmis.search.CMISQueryService;
import org.alfresco.repo.admin.SysAdminParams;
import org.alfresco.repo.forms.FormService;
import org.alfresco.repo.i18n.MessageService;
import org.alfresco.repo.imap.ImapService;
import org.alfresco.repo.lock.JobLockService;
import org.alfresco.repo.nodelocator.NodeLocatorService;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.repo.search.impl.solr.facet.SolrFacetHelper;
import org.alfresco.repo.search.impl.solr.facet.handler.FacetLabelDisplayHandlerRegistry;
import org.alfresco.repo.transaction.RetryingTransactionHelper;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.action.ActionService;
import org.alfresco.service.cmr.admin.RepoAdminService;
import org.alfresco.service.cmr.attributes.AttributeService;
import org.alfresco.service.cmr.audit.AuditService;
import org.alfresco.service.cmr.blog.BlogService;
import org.alfresco.service.cmr.calendar.CalendarService;
import org.alfresco.service.cmr.coci.CheckOutCheckInService;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.invitation.InvitationService;
import org.alfresco.service.cmr.lock.LockService;
import org.alfresco.service.cmr.ml.ContentFilterLanguagesService;
import org.alfresco.service.cmr.ml.EditionService;
import org.alfresco.service.cmr.ml.MultilingualContentService;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.module.ModuleService;
import org.alfresco.service.cmr.notification.NotificationService;
import org.alfresco.service.cmr.rating.RatingService;
import org.alfresco.service.cmr.rendition.RenditionService;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.CopyService;
import org.alfresco.service.cmr.repository.DocumentLinkService;
import org.alfresco.service.cmr.repository.MimetypeService;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.ScriptService;
import org.alfresco.service.cmr.repository.TemplateService;
import org.alfresco.service.cmr.rule.RuleService;
import org.alfresco.service.cmr.search.CategoryService;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.security.AuthorityService;
import org.alfresco.service.cmr.security.MutableAuthenticationService;
import org.alfresco.service.cmr.security.OwnableService;
import org.alfresco.service.cmr.security.PermissionService;
import org.alfresco.service.cmr.security.PersonService;
import org.alfresco.service.cmr.security.PublicServiceAccessService;
import org.alfresco.service.cmr.site.SiteService;
import org.alfresco.service.cmr.tagging.TaggingService;
import org.alfresco.service.cmr.thumbnail.ThumbnailService;
import org.alfresco.service.cmr.version.VersionService;
import org.alfresco.service.cmr.view.ExporterService;
import org.alfresco.service.cmr.view.ImporterService;
import org.alfresco.service.cmr.webdav.WebDavService;
import org.alfresco.service.cmr.workflow.WorkflowService;
import org.alfresco.service.descriptor.DescriptorService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.transaction.TransactionService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class MockServiceRegistry implements BeanFactoryAware, ServiceRegistry, Externalizable {

	private NodeService nodeService;

	private ContentService contentService;

	private SearchService searchService;

	private FileFolderService fileFolderService;

	private NodeLocatorService nodeLocatorService;

	private NamespaceService namespaceService;

	private ScriptService scriptService;

	private MimetypeService mimetypeService;

	private ImporterService importerService;

	private PermissionService permissionService;

	private TemplateService templateService;

	private SolrFacetHelper solrFacetHelper;

	private TransactionService transactionService;

	private DictionaryService dictionaryService;

	private MutableAuthenticationService authenticationService;

	private VersionService versionService;

	private CopyService copyService;

	private CheckOutCheckInService checkOutCheckInService;

	private SiteService siteService;

	private PersonService personService;

	private AuthorityService authorityService;

	private ActionService actionService;

	@Override
	public Collection<QName> getServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isServiceProvided(QName service) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getService(QName service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DescriptorService getDescriptorService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionService getTransactionService() {
		return transactionService;
	}

	@Override
	public RetryingTransactionHelper getRetryingTransactionHelper() {
		return transactionService.getRetryingTransactionHelper();
	}

	@Override
	public NamespaceService getNamespaceService() {
		return namespaceService;
	}

	@Override
	public MutableAuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	@Override
	public NodeService getNodeService() {
		return nodeService;
	}

	@Override
	public ContentService getContentService() {
		return contentService;
	}

	@Override
	public MimetypeService getMimetypeService() {
		return mimetypeService;
	}

	@Override
	public ContentFilterLanguagesService getContentFilterLanguagesService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchService getSearchService() {
		return searchService;
	}

	@Override
	public VersionService getVersionService() {
		return versionService;
	}

	@Override
	public LockService getLockService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobLockService getJobLockService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}

	@Override
	public CopyService getCopyService() {
		return copyService;
	}

	@Override
	public CheckOutCheckInService getCheckOutCheckInService() {
		return checkOutCheckInService;
	}

	@Override
	public CategoryService getCategoryService() {
		return null;
	}

	@Override
	public ImporterService getImporterService() {
		return importerService;
	}

	@Override
	public ExporterService getExporterService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RuleService getRuleService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionService getActionService() {
		return actionService;
	}

	@Override
	public PermissionService getPermissionService() {
		return permissionService;
	}

	@Override
	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	@Override
	public TemplateService getTemplateService() {
		return templateService;
	}

	@Override
	public FileFolderService getFileFolderService() {
		return fileFolderService;
	}

	@Override
	public ScriptService getScriptService() {
		return scriptService;
	}

	@Override
	public WorkflowService getWorkflowService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotificationService getNotificationService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuditService getAuditService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OwnableService getOwnableService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonService getPersonService() {
		return personService;
	}

	@Override
	public SiteService getSiteService() {
		return siteService;
	}

	@Override
	public AttributeService getAttributeService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultilingualContentService getMultilingualContentService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EditionService getEditionService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ThumbnailService getThumbnailService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaggingService getTaggingService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormService getFormService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RenditionService getRenditionService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RatingService getRatingService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeLocatorService getNodeLocatorService() {
		return nodeLocatorService;
	}

	@Override
	public BlogService getBlogService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CalendarService getCalendarService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InvitationService getInvitationService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImapService getImapService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PublicServiceAccessService getPublicServiceAccessService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RepoAdminService getRepoAdminService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysAdminParams getSysAdminParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebDavService getWebDavService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub

	}

	@Override
	public CMISDictionaryService getCMISDictionaryService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CMISQueryService getCMISQueryService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SolrFacetHelper getSolrFacetHelper() {
		return solrFacetHelper;
	}

	@Override
	public FacetLabelDisplayHandlerRegistry getFacetLabelDisplayHandlerRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSolrFacetHelper(SolrFacetHelper solrFacetHelper) {
		this.solrFacetHelper = solrFacetHelper;
	}

	@Override
	public ModuleService getModuleService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageService getMessageService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentLinkService getDocumentLinkService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PolicyComponent getPolicyComponent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(namespaceService);
		out.writeObject(mimetypeService);
		out.writeObject(scriptService);
		out.writeObject(importerService);
		out.writeObject(permissionService);
		out.writeObject(templateService);
		out.writeObject(nodeService);
		out.writeObject(actionService);
		out.writeObject(authenticationService);
		out.writeObject(authorityService);
		out.writeObject(contentService);
		out.writeObject(copyService);
		out.writeObject(checkOutCheckInService);
		out.writeObject(dictionaryService);
		out.writeObject(fileFolderService);
		out.writeObject(nodeLocatorService);
		out.writeObject(personService);
		out.writeObject(searchService);
		out.writeObject(siteService);
		out.writeObject(transactionService);
		out.writeObject(versionService);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		namespaceService = (NamespaceService) in.readObject();
		mimetypeService = (MimetypeService) in.readObject();
		scriptService = (ScriptService) in.readObject();
		importerService = (ImporterService) in.readObject();
		permissionService = (PermissionService) in.readObject();
		templateService = (TemplateService) in.readObject();
		nodeService = (NodeService) in.readObject();
		actionService = (ActionService) in.readObject();
		authenticationService = (MutableAuthenticationService) in.readObject();
		authorityService = (AuthorityService) in.readObject();
		contentService = (ContentService) in.readObject();
		copyService = (CopyService) in.readObject();
		checkOutCheckInService = (CheckOutCheckInService) in.readObject();
		dictionaryService = (DictionaryService) in.readObject();
		fileFolderService = (FileFolderService) in.readObject();
		nodeLocatorService = (NodeLocatorService) in.readObject();
		personService = (PersonService) in.readObject();
		searchService = (SearchService) in.readObject();
		siteService = (SiteService) in.readObject();
		transactionService = (TransactionService) in.readObject();
		versionService = (VersionService) in.readObject();
	}

	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public void setNodeLocatorService(NodeLocatorService nodeLocatorService) {
		this.nodeLocatorService = nodeLocatorService;
	}

	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	public void setMimetypeService(MimetypeService mimetypeService) {
		this.mimetypeService = mimetypeService;
	}

	public void setScriptService(ScriptService scriptService) {
		this.scriptService = scriptService;
	}

	public void setImporterService(ImporterService importerService) {
		this.importerService = importerService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	public void setAuthenticationService(MutableAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public void setVersionService(VersionService versionService) {
		this.versionService = versionService;
	}

	public void setCopyService(CopyService copyService) {
		this.copyService = copyService;
	}

	public void setCheckOutCheckInService(CheckOutCheckInService checkOutCheckInService) {
		this.checkOutCheckInService = checkOutCheckInService;
	}

	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}

}
