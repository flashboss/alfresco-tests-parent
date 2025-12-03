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
import org.alfresco.service.cmr.module.ModuleService;
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
 * Mock implementation of MockServiceRegistry for testing purposes.
 *
 * @author vige
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

	/** The retrying transaction helper. */
	private RetryingTransactionHelper retryingTransactionHelper;

	@Override
	/**
	 * Get services.
	 *
	 * @return the result
	 */
	public Collection<QName> getServices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Is service provided.
	 *
	 * @param service the service
	 * @return the result
	 */
	public boolean isServiceProvided(QName service) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Get service.
	 *
	 * @param service the service
	 * @return the result
	 */
	public Object getService(QName service) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get descriptor service.
	 *
	 * @return the result
	 */
	public DescriptorService getDescriptorService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get transaction service.
	 *
	 * @return the result
	 */
	public TransactionService getTransactionService() {
		return transactionService;
	}

	@Override
	/**
	 * Get retrying transaction helper.
	 *
	 * @return the result
	 */
	public RetryingTransactionHelper getRetryingTransactionHelper() {
		return retryingTransactionHelper;
	}

	@Override
	/**
	 * Get namespace service.
	 *
	 * @return the result
	 */
	public NamespaceService getNamespaceService() {
		return namespaceService;
	}

	@Override
	/**
	 * Get authentication service.
	 *
	 * @return the result
	 */
	public MutableAuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	@Override
	/**
	 * Get node service.
	 *
	 * @return the result
	 */
	public NodeService getNodeService() {
		return nodeService;
	}

	@Override
	/**
	 * Get content service.
	 *
	 * @return the result
	 */
	public ContentService getContentService() {
		return contentService;
	}

	@Override
	/**
	 * Get mimetype service.
	 *
	 * @return the result
	 */
	public MimetypeService getMimetypeService() {
		return mimetypeService;
	}

	@Override
	/**
	 * Get content filter languages service.
	 *
	 * @return the result
	 */
	public ContentFilterLanguagesService getContentFilterLanguagesService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get search service.
	 *
	 * @return the result
	 */
	public SearchService getSearchService() {
		return searchService;
	}

	@Override
	/**
	 * Get version service.
	 *
	 * @return the result
	 */
	public VersionService getVersionService() {
		return versionService;
	}

	@Override
	/**
	 * Get lock service.
	 *
	 * @return the result
	 */
	public LockService getLockService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get job lock service.
	 *
	 * @return the result
	 */
	public JobLockService getJobLockService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get dictionary service.
	 *
	 * @return the result
	 */
	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}

	@Override
	/**
	 * Get copy service.
	 *
	 * @return the result
	 */
	public CopyService getCopyService() {
		return copyService;
	}

	@Override
	/**
	 * Get check out check in service.
	 *
	 * @return the result
	 */
	public CheckOutCheckInService getCheckOutCheckInService() {
		return checkOutCheckInService;
	}

	@Override
	/**
	 * Get category service.
	 *
	 * @return the result
	 */
	public CategoryService getCategoryService() {
		return null;
	}

	@Override
	/**
	 * Get importer service.
	 *
	 * @return the result
	 */
	public ImporterService getImporterService() {
		return importerService;
	}

	@Override
	/**
	 * Get exporter service.
	 *
	 * @return the result
	 */
	public ExporterService getExporterService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get rule service.
	 *
	 * @return the result
	 */
	public RuleService getRuleService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get action service.
	 *
	 * @return the result
	 */
	public ActionService getActionService() {
		return actionService;
	}

	@Override
	/**
	 * Get permission service.
	 *
	 * @return the result
	 */
	public PermissionService getPermissionService() {
		return permissionService;
	}

	@Override
	/**
	 * Get authority service.
	 *
	 * @return the result
	 */
	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	@Override
	/**
	 * Get template service.
	 *
	 * @return the result
	 */
	public TemplateService getTemplateService() {
		return templateService;
	}

	@Override
	/**
	 * Get file folder service.
	 *
	 * @return the result
	 */
	public FileFolderService getFileFolderService() {
		return fileFolderService;
	}

	@Override
	/**
	 * Get script service.
	 *
	 * @return the result
	 */
	public ScriptService getScriptService() {
		return scriptService;
	}

	@Override
	/**
	 * Get workflow service.
	 *
	 * @return the result
	 */
	public WorkflowService getWorkflowService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get notification service.
	 *
	 * @return the result
	 */
	public NotificationService getNotificationService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get audit service.
	 *
	 * @return the result
	 */
	public AuditService getAuditService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get a v m service.
	 *
	 * @return the result
	 */
	public AVMService getAVMService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get a v m locking aware service.
	 *
	 * @return the result
	 */
	public AVMService getAVMLockingAwareService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get a v m sync service.
	 *
	 * @return the result
	 */
	public AVMSyncService getAVMSyncService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get ownable service.
	 *
	 * @return the result
	 */
	public OwnableService getOwnableService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get person service.
	 *
	 * @return the result
	 */
	public PersonService getPersonService() {
		return personService;
	}

	@Override
	/**
	 * Get site service.
	 *
	 * @return the result
	 */
	public SiteService getSiteService() {
		return siteService;
	}

	@Override
	/**
	 * Get cross repository copy service.
	 *
	 * @return the result
	 */
	public CrossRepositoryCopyService getCrossRepositoryCopyService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get attribute service.
	 *
	 * @return the result
	 */
	public AttributeService getAttributeService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get a v m locking service.
	 *
	 * @return the result
	 */
	public AVMLockingService getAVMLockingService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get virt server registry.
	 *
	 * @return the result
	 */
	public VirtServerRegistry getVirtServerRegistry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get multilingual content service.
	 *
	 * @return the result
	 */
	public MultilingualContentService getMultilingualContentService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get edition service.
	 *
	 * @return the result
	 */
	public EditionService getEditionService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get thumbnail service.
	 *
	 * @return the result
	 */
	public ThumbnailService getThumbnailService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get tagging service.
	 *
	 * @return the result
	 */
	public TaggingService getTaggingService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get deployment service.
	 *
	 * @return the result
	 */
	public DeploymentService getDeploymentService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get web project service.
	 *
	 * @return the result
	 */
	public WebProjectService getWebProjectService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get sandbox service.
	 *
	 * @return the result
	 */
	public SandboxService getSandboxService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get asset service.
	 *
	 * @return the result
	 */
	public AssetService getAssetService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get preview u r i service.
	 *
	 * @return the result
	 */
	public PreviewURIService getPreviewURIService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get form service.
	 *
	 * @return the result
	 */
	public FormService getFormService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get rendition service.
	 *
	 * @return the result
	 */
	public RenditionService getRenditionService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get rating service.
	 *
	 * @return the result
	 */
	public RatingService getRatingService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get node locator service.
	 *
	 * @return the result
	 */
	public NodeLocatorService getNodeLocatorService() {
		return nodeLocatorService;
	}

	@Override
	/**
	 * Get blog service.
	 *
	 * @return the result
	 */
	public BlogService getBlogService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get calendar service.
	 *
	 * @return the result
	 */
	public CalendarService getCalendarService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get invitation service.
	 *
	 * @return the result
	 */
	public InvitationService getInvitationService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get c m i s service.
	 *
	 * @return the result
	 */
	public CMISServices getCMISService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get c m i s dictionary service.
	 *
	 * @return the result
	 */
	public CMISDictionaryService getCMISDictionaryService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get c m i s query service.
	 *
	 * @return the result
	 */
	public CMISQueryService getCMISQueryService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get imap service.
	 *
	 * @return the result
	 */
	public ImapService getImapService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get public service access service.
	 *
	 * @return the result
	 */
	public PublicServiceAccessService getPublicServiceAccessService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get repo admin service.
	 *
	 * @return the result
	 */
	public RepoAdminService getRepoAdminService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get sys admin params.
	 *
	 * @return the result
	 */
	public SysAdminParams getSysAdminParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get web dav service.
	 *
	 * @return the result
	 */
	public WebDavService getWebDavService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get module service.
	 *
	 * @return the result
	 */
	public ModuleService getModuleService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Set bean factory.
	 *
	 * @param beanFactory the bean factory
	 */
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub

	}

	/**
	 * Set file folder service.
	 *
	 * @param fileFolderService the file folder service
	 */
	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}

	/**
	 * Set content service.
	 *
	 * @param contentService the content service
	 */
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	/**
	 * Set node service.
	 *
	 * @param nodeService the node service
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * Set search service.
	 *
	 * @param searchService the search service
	 */
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	/**
	 * Set node locator service.
	 *
	 * @param nodeLocatorService the node locator service
	 */
	public void setNodeLocatorService(NodeLocatorService nodeLocatorService) {
		this.nodeLocatorService = nodeLocatorService;
	}

	/**
	 * Set namespace service.
	 *
	 * @param namespaceService the namespace service
	 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	/**
	 * Set mimetype service.
	 *
	 * @param mimetypeService the mimetype service
	 */
	public void setMimetypeService(MimetypeService mimetypeService) {
		this.mimetypeService = mimetypeService;
	}

	/**
	 * Set script service.
	 *
	 * @param scriptService the script service
	 */
	public void setScriptService(ScriptService scriptService) {
		this.scriptService = scriptService;
	}

	/**
	 * Set importer service.
	 *
	 * @param importerService the importer service
	 */
	public void setImporterService(ImporterService importerService) {
		this.importerService = importerService;
	}

	/**
	 * Set permission service.
	 *
	 * @param permissionService the permission service
	 */
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	/**
	 * Set template service.
	 *
	 * @param templateService the template service
	 */
	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

	/**
	 * Set authentication service.
	 *
	 * @param authenticationService the authentication service
	 */
	public void setAuthenticationService(MutableAuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	/**
	 * Set transaction service.
	 *
	 * @param transactionService the transaction service
	 */
	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	/**
	 * Set dictionary service.
	 *
	 * @param dictionaryService the dictionary service
	 */
	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	/**
	 * Set version service.
	 *
	 * @param versionService the version service
	 */
	public void setVersionService(VersionService versionService) {
		this.versionService = versionService;
	}

	/**
	 * Set copy service.
	 *
	 * @param copyService the copy service
	 */
	public void setCopyService(CopyService copyService) {
		this.copyService = copyService;
	}

	/**
	 * Set check out check in service.
	 *
	 * @param checkOutCheckInService the check out check in service
	 */
	public void setCheckOutCheckInService(CheckOutCheckInService checkOutCheckInService) {
		this.checkOutCheckInService = checkOutCheckInService;
	}

	/**
	 * Set site service.
	 *
	 * @param siteService the site service
	 */
	public void setSiteService(SiteService siteService) {
		this.siteService = siteService;
	}

	/**
	 * Set person service.
	 *
	 * @param personService the person service
	 */
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	/**
	 * Set authority service.
	 *
	 * @param authorityService the authority service
	 */
	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	/**
	 * Set action service.
	 *
	 * @param actionService the action service
	 */
	public void setActionService(ActionService actionService) {
		this.actionService = actionService;
	}

	/**
	 * Set retrying transaction helper.
	 *
	 * @param retryingTransactionHelper the retrying transaction helper
	 */
	public void setRetryingTransactionHelper(RetryingTransactionHelper retryingTransactionHelper) {
		this.retryingTransactionHelper = retryingTransactionHelper;
	}

}
