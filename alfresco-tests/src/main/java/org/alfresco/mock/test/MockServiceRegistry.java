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

public class MockServiceRegistry implements BeanFactoryAware, ServiceRegistry, Serializable {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RetryingTransactionHelper getRetryingTransactionHelper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NamespaceService getNamespaceService() {
		return namespaceService;
	}

	@Override
	public MutableAuthenticationService getAuthenticationService() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		return null;
	}

	@Override
	public CopyService getCopyService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CheckOutCheckInService getCheckOutCheckInService() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PermissionService getPermissionService() {
		return permissionService;
	}

	@Override
	public AuthorityService getAuthorityService() {
		// TODO Auto-generated method stub
		return null;
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
	public AVMService getAVMService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AVMService getAVMLockingAwareService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AVMSyncService getAVMSyncService() {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SiteService getSiteService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CrossRepositoryCopyService getCrossRepositoryCopyService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttributeService getAttributeService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AVMLockingService getAVMLockingService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VirtServerRegistry getVirtServerRegistry() {
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
	public DeploymentService getDeploymentService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebProjectService getWebProjectService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SandboxService getSandboxService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssetService getAssetService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PreviewURIService getPreviewURIService() {
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
	public CMISServices getCMISService() {
		// TODO Auto-generated method stub
		return null;
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
	public ModuleService getModuleService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub

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

}
