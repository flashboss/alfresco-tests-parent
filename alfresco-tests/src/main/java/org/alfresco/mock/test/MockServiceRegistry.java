package org.alfresco.mock.test;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;

import org.alfresco.repo.admin.SysAdminParams;
import org.alfresco.repo.forms.FormService;
import org.alfresco.repo.i18n.MessageService;
import org.alfresco.repo.imap.ImapService;
import org.alfresco.repo.lock.JobLockService;
import org.alfresco.repo.nodelocator.NodeLocatorService;
import org.alfresco.repo.policy.PolicyComponent;
import org.alfresco.repo.rendition2.RenditionService2;
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

/**
* Mock implementation of the MockServiceRegistry class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockServiceRegistry implements BeanFactoryAware, ServiceRegistry, Externalizable {

/**
* The node service.
 */
	private NodeService nodeService;

/**
* The content service.
 */
	private ContentService contentService;

/**
* The search service.
 */
	private SearchService searchService;

/**
* The file folder service.
 */
	private FileFolderService fileFolderService;

/**
* The node locator service.
 */
	private NodeLocatorService nodeLocatorService;

/**
* The namespace service.
 */
	private NamespaceService namespaceService;

/**
* The script service.
 */
	private ScriptService scriptService;

/**
* The mimetype service.
 */
	private MimetypeService mimetypeService;

/**
* The importer service.
 */
	private ImporterService importerService;

/**
* The permission service.
 */
	private PermissionService permissionService;

/**
* The template service.
 */
	private TemplateService templateService;

/**
* The rendition service.
 */
	private RenditionService2 renditionService2;

/**
* The solr facet helper.
 */
	private SolrFacetHelper solrFacetHelper;

/**
* The transaction service.
 */
	private TransactionService transactionService;

/**
* The dictionary service.
 */
	private DictionaryService dictionaryService;

/**
* The authentication service.
 */
	private MutableAuthenticationService authenticationService;

/**
* The version service.
 */
	private VersionService versionService;

/**
* The copy service.
 */
	private CopyService copyService;

/**
* The check out check in service.
 */
	private CheckOutCheckInService checkOutCheckInService;

/**
* The site service.
 */
	private SiteService siteService;

/**
* The person service.
 */
	private PersonService personService;

/**
* The authority service.
 */
	private AuthorityService authorityService;

/**
* The action service.
 */
	private ActionService actionService;

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public Collection<QName> getServices() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param service the service
* @return the result
 */
	@Override
	public boolean isServiceProvided(QName service) {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param service the service
* @return the result
 */
	@Override
	public Object getService(QName service) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public DescriptorService getDescriptorService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public TransactionService getTransactionService() {
		return transactionService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public RetryingTransactionHelper getRetryingTransactionHelper() {
		return transactionService.getRetryingTransactionHelper();
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public NamespaceService getNamespaceService() {
		return namespaceService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public MutableAuthenticationService getAuthenticationService() {
		return authenticationService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public NodeService getNodeService() {
		return nodeService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ContentService getContentService() {
		return contentService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public MimetypeService getMimetypeService() {
		return mimetypeService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ContentFilterLanguagesService getContentFilterLanguagesService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public SearchService getSearchService() {
		return searchService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public VersionService getVersionService() {
		return versionService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public LockService getLockService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public JobLockService getJobLockService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public CopyService getCopyService() {
		return copyService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public CheckOutCheckInService getCheckOutCheckInService() {
		return checkOutCheckInService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public CategoryService getCategoryService() {
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ImporterService getImporterService() {
		return importerService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ExporterService getExporterService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public RuleService getRuleService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ActionService getActionService() {
		return actionService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public PermissionService getPermissionService() {
		return permissionService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public AuthorityService getAuthorityService() {
		return authorityService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public TemplateService getTemplateService() {
		return templateService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public FileFolderService getFileFolderService() {
		return fileFolderService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ScriptService getScriptService() {
		return scriptService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public WorkflowService getWorkflowService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public NotificationService getNotificationService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public AuditService getAuditService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public OwnableService getOwnableService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public PersonService getPersonService() {
		return personService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public SiteService getSiteService() {
		return siteService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public AttributeService getAttributeService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public MultilingualContentService getMultilingualContentService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public EditionService getEditionService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ThumbnailService getThumbnailService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public TaggingService getTaggingService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public FormService getFormService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public RenditionService getRenditionService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public RatingService getRatingService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public NodeLocatorService getNodeLocatorService() {
		return nodeLocatorService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public BlogService getBlogService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public CalendarService getCalendarService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public InvitationService getInvitationService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ImapService getImapService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public PublicServiceAccessService getPublicServiceAccessService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public RepoAdminService getRepoAdminService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public SysAdminParams getSysAdminParams() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public WebDavService getWebDavService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public ModuleService getModuleService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param beanFactory the beanFactory
* @throws BeansException if an error occurs
 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub

	}

/**
* Sets the file folder service.
* @param fileFolderService the fileFolderService
 */
	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}

/**
* Sets the content service.
* @param contentService the contentService
 */
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

/**
* Sets the node service.
* @param nodeService the nodeService
 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

/**
* Sets the search service.
* @param searchService the searchService
 */
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

/**
* Sets the node locator service.
* @param nodeLocatorService the nodeLocatorService
 */
	public void setNodeLocatorService(NodeLocatorService nodeLocatorService) {
		this.nodeLocatorService = nodeLocatorService;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public org.alfresco.opencmis.dictionary.CMISDictionaryService getCMISDictionaryService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public org.alfresco.opencmis.search.CMISQueryService getCMISQueryService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public DocumentLinkService getDocumentLinkService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public FacetLabelDisplayHandlerRegistry getFacetLabelDisplayHandlerRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public MessageService getMessageService() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public PolicyComponent getPolicyComponent() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* Performs write external.
* @param out the out
* @throws IOException if an error occurs
 */
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
		out.writeObject(renditionService2);
	}

/**
* {@inheritDoc}
* @param in the in
* @throws IOException if an error occurs
* @throws ClassNotFoundException if an error occurs
 */
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
		renditionService2 = (RenditionService2) in.readObject();
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public RenditionService2 getRenditionService2() {
		// TODO Auto-generated method stub
		return renditionService2;
	}

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public SolrFacetHelper getSolrFacetHelper() {
		return solrFacetHelper;
	}

/**
* Sets the namespace service.
* @param namespaceService the namespaceService
 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

/**
* Sets the mimetype service.
* @param mimetypeService the mimetypeService
 */
	public void setMimetypeService(MimetypeService mimetypeService) {
		this.mimetypeService = mimetypeService;
	}

/**
* Sets the script service.
* @param scriptService the scriptService
 */
	public void setScriptService(ScriptService scriptService) {
		this.scriptService = scriptService;
	}

/**
* Sets the importer service.
* @param importerService the importerService
 */
	public void setImporterService(ImporterService importerService) {
		this.importerService = importerService;
	}

/**
* Sets the permission service.
* @param permissionService the permissionService
 */
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

/**
* Sets the template service.
* @param templateService the templateService
 */
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

/**
* Sets the authentication service.
* @param authenticationService the authenticationService
 */
	public void setAuthenticationService(MutableAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

/**
* Sets the transaction service.
* @param transactionService the transactionService
 */
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

/**
* Sets the rendition service.
* @param renditionService2 the renditionService2
 */
	public void setRenditionService2(RenditionService2 renditionService2) {
		this.renditionService2 = renditionService2;
	}

/**
* Sets the solr facet helper.
* @param solrFacetHelper the solrFacetHelper
 */
	public void setSolrFacetHelper(SolrFacetHelper solrFacetHelper) {
		this.solrFacetHelper = solrFacetHelper;
	}

/**
* Sets the dictionary service.
* @param dictionaryService the dictionaryService
 */
	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

/**
* Sets the version service.
* @param versionService the versionService
 */
	public void setVersionService(VersionService versionService) {
		this.versionService = versionService;
	}

/**
* Sets the copy service.
* @param copyService the copyService
 */
	public void setCopyService(CopyService copyService) {
		this.copyService = copyService;
	}

/**
* Sets the check out check in service.
* @param checkOutCheckInService the checkOutCheckInService
 */
	public void setCheckOutCheckInService(CheckOutCheckInService checkOutCheckInService) {
		this.checkOutCheckInService = checkOutCheckInService;
	}

/**
* Sets the site service.
* @param siteService the siteService
 */
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

/**
* Sets the person service.
* @param personService the personService
 */
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

/**
* Sets the authority service.
* @param authorityService the authorityService
 */
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

/**
* Sets the action service.
* @param actionService the actionService
 */
	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}

}
