package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Collection;

import org.alfresco.cmis.CMISDictionaryService;
import org.alfresco.cmis.CMISQueryService;
import org.alfresco.cmis.CMISServices;
import org.alfresco.mbeans.VirtServerRegistry;
import org.alfresco.repo.admin.SysAdminParams;
import org.alfresco.repo.forms.FormService;
import org.alfresco.repo.imap.ImapService;
import org.alfresco.repo.lock.JobLockService;
import org.alfresco.repo.nodelocator.NodeLocatorService;
import org.alfresco.repo.transaction.RetryingTransactionHelper;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.action.ActionService;
import org.alfresco.service.cmr.admin.RepoAdminService;
import org.alfresco.service.cmr.attributes.AttributeService;
import org.alfresco.service.cmr.audit.AuditService;
import org.alfresco.service.cmr.avm.AVMService;
import org.alfresco.service.cmr.avm.deploy.DeploymentService;
import org.alfresco.service.cmr.avm.locking.AVMLockingService;
import org.alfresco.service.cmr.avmsync.AVMSyncService;
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
import org.alfresco.service.cmr.notification.NotificationService;
import org.alfresco.service.cmr.rating.RatingService;
import org.alfresco.service.cmr.rendition.RenditionService;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.CopyService;
import org.alfresco.service.cmr.repository.CrossRepositoryCopyService;
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
import org.alfresco.wcm.asset.AssetService;
import org.alfresco.wcm.preview.PreviewURIService;
import org.alfresco.wcm.sandbox.SandboxService;
import org.alfresco.wcm.webproject.WebProjectService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * Mock implementation of the {@link ServiceRegistry} interface for testing purposes.
 * This class provides access to all Alfresco services in a mock environment,
 * allowing tests to wire up specific mock service implementations.
 *
 * @author lucastancapiano
 */
public class MockServiceRegistry implements BeanFactoryAware, ServiceRegistry, Serializable {

	/** The node service. */
	private NodeService nodeService;

	/** The content service. */
	private ContentService contentService;

	/** The search service. */
	private SearchService searchService;

	/** The file folder service. */
	private FileFolderService fileFolderService;

	/** The node locator service. */
	private NodeLocatorService nodeLocatorService;

	/** The namespace service. */
	private NamespaceService namespaceService;

	/** The script service. */
	private ScriptService scriptService;

	/** The mimetype service. */
	private MimetypeService mimetypeService;

	/** The importer service. */
	private ImporterService importerService;

	/** The permission service. */
	private PermissionService permissionService;

	/** The template service. */
	private TemplateService templateService;

	/** The transaction service. */
	private TransactionService transactionService;

	/** The dictionary service. */
	private DictionaryService dictionaryService;

	/** The authentication service. */
	private MutableAuthenticationService authenticationService;

	/** The version service. */
	private VersionService versionService;

	/** The copy service. */
	private CopyService copyService;

	/** The check out check in service. */
	private CheckOutCheckInService checkOutCheckInService;

	/** The site service. */
	private SiteService siteService;

	/** The person service. */
	private PersonService personService;

	/** The authority service. */
	private AuthorityService authorityService;

	/** The action service. */
	private ActionService actionService;

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Collection<QName> getServices() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @param service The service QName.
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isServiceProvided(QName service) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @param service The service QName.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Object getService(QName service) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public DescriptorService getDescriptorService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return The transaction service.
	 */
	@Override
	public TransactionService getTransactionService() {
		return transactionService;
	}

	/**
	 * {@inheritDoc}
	 * @return The retrying transaction helper from the transaction service.
	 */
	@Override
	public RetryingTransactionHelper getRetryingTransactionHelper() {
		return transactionService.getRetryingTransactionHelper();
	}

	/**
	 * {@inheritDoc}
	 * @return The namespace service.
	 */
	@Override
	public NamespaceService getNamespaceService() {
		return namespaceService;
	}

	/**
	 * {@inheritDoc}
	 * @return The authentication service.
	 */
	@Override
	public MutableAuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	/**
	 * {@inheritDoc}
	 * @return The node service.
	 */
	@Override
	public NodeService getNodeService() {
		return nodeService;
	}

	/**
	 * {@inheritDoc}
	 * @return The content service.
	 */
	@Override
	public ContentService getContentService() {
		return contentService;
	}

	/**
	 * {@inheritDoc}
	 * @return The mimetype service.
	 */
	@Override
	public MimetypeService getMimetypeService() {
		return mimetypeService;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public ContentFilterLanguagesService getContentFilterLanguagesService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return The search service.
	 */
	@Override
	public SearchService getSearchService() {
		return searchService;
	}

	/**
	 * {@inheritDoc}
	 * @return The version service.
	 */
	@Override
	public VersionService getVersionService() {
		return versionService;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public LockService getLockService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public JobLockService getJobLockService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return The dictionary service.
	 */
	@Override
	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}

	/**
	 * {@inheritDoc}
	 * @return The copy service.
	 */
	@Override
	public CopyService getCopyService() {
		return copyService;
	}

	/**
	 * {@inheritDoc}
	 * @return The check out check in service.
	 */
	@Override
	public CheckOutCheckInService getCheckOutCheckInService() {
		return checkOutCheckInService;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public CategoryService getCategoryService() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return The importer service.
	 */
	@Override
	public ImporterService getImporterService() {
		return importerService;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public ExporterService getExporterService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public RuleService getRuleService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return The action service.
	 */
	@Override
	public ActionService getActionService() {
		return actionService;
	}

	/**
	 * {@inheritDoc}
	 * @return The permission service.
	 */
	@Override
	public PermissionService getPermissionService() {
		return permissionService;
	}

	/**
	 * {@inheritDoc}
	 * @return The authority service.
	 */
	@Override
	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	/**
	 * {@inheritDoc}
	 * @return The template service.
	 */
	@Override
	public TemplateService getTemplateService() {
		return templateService;
	}

	/**
	 * {@inheritDoc}
	 * @return The file folder service.
	 */
	@Override
	public FileFolderService getFileFolderService() {
		return fileFolderService;
	}

	/**
	 * {@inheritDoc}
	 * @return The script service.
	 */
	@Override
	public ScriptService getScriptService() {
		return scriptService;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public WorkflowService getWorkflowService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public NotificationService getNotificationService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public AuditService getAuditService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public AVMService getAVMService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public AVMService getAVMLockingAwareService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public AVMSyncService getAVMSyncService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public OwnableService getOwnableService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return The person service.
	 */
	@Override
	public PersonService getPersonService() {
		return personService;
	}

	/**
	 * {@inheritDoc}
	 * @return The site service.
	 */
	@Override
	public SiteService getSiteService() {
		return siteService;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public CrossRepositoryCopyService getCrossRepositoryCopyService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public AttributeService getAttributeService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public AVMLockingService getAVMLockingService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public VirtServerRegistry getVirtServerRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public MultilingualContentService getMultilingualContentService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public EditionService getEditionService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public ThumbnailService getThumbnailService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public TaggingService getTaggingService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public DeploymentService getDeploymentService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public WebProjectService getWebProjectService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public SandboxService getSandboxService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public AssetService getAssetService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public PreviewURIService getPreviewURIService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public FormService getFormService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public RenditionService getRenditionService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public RatingService getRatingService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return The node locator service.
	 */
	@Override
	public NodeLocatorService getNodeLocatorService() {
		return nodeLocatorService;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public BlogService getBlogService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public CalendarService getCalendarService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public InvitationService getInvitationService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public CMISServices getCMISService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public CMISDictionaryService getCMISDictionaryService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public CMISQueryService getCMISQueryService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public ImapService getImapService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public PublicServiceAccessService getPublicServiceAccessService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public RepoAdminService getRepoAdminService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public SysAdminParams getSysAdminParams() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public WebDavService getWebDavService() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * @param beanFactory The bean factory.
	 * @throws BeansException If an error occurs.
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets the file folder service.
	 * @param fileFolderService The file folder service to set.
	 */
	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}

	/**
	 * Sets the content service.
	 * @param contentService The content service to set.
	 */
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	/**
	 * Sets the node service.
	 * @param nodeService The node service to set.
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * Sets the search service.
	 * @param searchService The search service to set.
	 */
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	/**
	 * Sets the node locator service.
	 * @param nodeLocatorService The node locator service to set.
	 */
	public void setNodeLocatorService(NodeLocatorService nodeLocatorService) {
		this.nodeLocatorService = nodeLocatorService;
	}

	/**
	 * Sets the namespace service.
	 * @param namespaceService The namespace service to set.
	 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	/**
	 * Sets the mimetype service.
	 * @param mimetypeService The mimetype service to set.
	 */
	public void setMimetypeService(MimetypeService mimetypeService) {
		this.mimetypeService = mimetypeService;
	}

	/**
	 * Sets the script service.
	 * @param scriptService The script service to set.
	 */
	public void setScriptService(ScriptService scriptService) {
		this.scriptService = scriptService;
	}

	/**
	 * Sets the importer service.
	 * @param importerService The importer service to set.
	 */
	public void setImporterService(ImporterService importerService) {
		this.importerService = importerService;
	}

	/**
	 * Sets the permission service.
	 * @param permissionService The permission service to set.
	 */
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	/**
	 * Sets the template service.
	 * @param templateService The template service to set.
	 */
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	/**
	 * Sets the authentication service.
	 * @param authenticationService The authentication service to set.
	 */
	public void setAuthenticationService(MutableAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	/**
	 * Sets the transaction service.
	 * @param transactionService The transaction service to set.
	 */
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	/**
	 * Sets the dictionary service.
	 * @param dictionaryService The dictionary service to set.
	 */
	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	/**
	 * Sets the version service.
	 * @param versionService The version service to set.
	 */
	public void setVersionService(VersionService versionService) {
		this.versionService = versionService;
	}

	/**
	 * Sets the copy service.
	 * @param copyService The copy service to set.
	 */
	public void setCopyService(CopyService copyService) {
		this.copyService = copyService;
	}

	/**
	 * Sets the check out check in service.
	 * @param checkOutCheckInService The check out check in service to set.
	 */
	public void setCheckOutCheckInService(CheckOutCheckInService checkOutCheckInService) {
		this.checkOutCheckInService = checkOutCheckInService;
	}

	/**
	 * Sets the site service.
	 * @param siteService The site service to set.
	 */
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	/**
	 * Sets the person service.
	 * @param personService The person service to set.
	 */
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	/**
	 * Sets the authority service.
	 * @param authorityService The authority service to set.
	 */
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	/**
	 * Sets the action service.
	 * @param actionService The action service to set.
	 */
	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}

}
