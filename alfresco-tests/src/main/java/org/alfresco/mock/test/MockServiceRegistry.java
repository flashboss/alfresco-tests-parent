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

/**
 * Mock implementation of the Alfresco ServiceRegistry for testing purposes. Provides stub
 * implementations for testing without a running Alfresco server.
 *
 * @author vige
 */
public class MockServiceRegistry implements BeanFactoryAware, ServiceRegistry, Externalizable {

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

  /** The solr facet helper. */
  private SolrFacetHelper solrFacetHelper;

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
   * Get services.
   *
   * @return the collection
   */
  @Override
  public Collection<QName> getServices() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Is service provided.
   *
   * @param service the service
   * @return the boolean
   */
  @Override
  public boolean isServiceProvided(QName service) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Get service.
   *
   * @param service the service
   * @return the object
   */
  @Override
  public Object getService(QName service) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get descriptor service.
   *
   * @return the descriptor service
   */
  @Override
  public DescriptorService getDescriptorService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get transaction service.
   *
   * @return the transaction service
   */
  @Override
  public TransactionService getTransactionService() {
    return transactionService;
  }

  /**
   * Get retrying transaction helper.
   *
   * @return the retrying transaction helper
   */
  @Override
  public RetryingTransactionHelper getRetryingTransactionHelper() {
    return transactionService.getRetryingTransactionHelper();
  }

  /**
   * Get namespace service.
   *
   * @return the namespace service
   */
  @Override
  public NamespaceService getNamespaceService() {
    return namespaceService;
  }

  /**
   * Get authentication service.
   *
   * @return the mutable authentication service
   */
  @Override
  public MutableAuthenticationService getAuthenticationService() {
    return authenticationService;
  }

  /**
   * Get node service.
   *
   * @return the node service
   */
  @Override
  public NodeService getNodeService() {
    return nodeService;
  }

  /**
   * Get content service.
   *
   * @return the content service
   */
  @Override
  public ContentService getContentService() {
    return contentService;
  }

  /**
   * Get mimetype service.
   *
   * @return the mimetype service
   */
  @Override
  public MimetypeService getMimetypeService() {
    return mimetypeService;
  }

  /**
   * Get content filter languages service.
   *
   * @return the content filter languages service
   */
  @Override
  public ContentFilterLanguagesService getContentFilterLanguagesService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get search service.
   *
   * @return the search service
   */
  @Override
  public SearchService getSearchService() {
    return searchService;
  }

  /**
   * Get version service.
   *
   * @return the version service
   */
  @Override
  public VersionService getVersionService() {
    return versionService;
  }

  /**
   * Get lock service.
   *
   * @return the lock service
   */
  @Override
  public LockService getLockService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get job lock service.
   *
   * @return the job lock service
   */
  @Override
  public JobLockService getJobLockService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get dictionary service.
   *
   * @return the dictionary service
   */
  @Override
  public DictionaryService getDictionaryService() {
    return dictionaryService;
  }

  /**
   * Get copy service.
   *
   * @return the copy service
   */
  @Override
  public CopyService getCopyService() {
    return copyService;
  }

  /**
   * Get check out check in service.
   *
   * @return the check out check in service
   */
  @Override
  public CheckOutCheckInService getCheckOutCheckInService() {
    return checkOutCheckInService;
  }

  /**
   * Get category service.
   *
   * @return the category service
   */
  @Override
  public CategoryService getCategoryService() {
    return null;
  }

  /**
   * Get importer service.
   *
   * @return the importer service
   */
  @Override
  public ImporterService getImporterService() {
    return importerService;
  }

  /**
   * Get exporter service.
   *
   * @return the exporter service
   */
  @Override
  public ExporterService getExporterService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get rule service.
   *
   * @return the rule service
   */
  @Override
  public RuleService getRuleService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get action service.
   *
   * @return the action service
   */
  @Override
  public ActionService getActionService() {
    return actionService;
  }

  /**
   * Get permission service.
   *
   * @return the permission service
   */
  @Override
  public PermissionService getPermissionService() {
    return permissionService;
  }

  /**
   * Get authority service.
   *
   * @return the authority service
   */
  @Override
  public AuthorityService getAuthorityService() {
    return authorityService;
  }

  /**
   * Get template service.
   *
   * @return the template service
   */
  @Override
  public TemplateService getTemplateService() {
    return templateService;
  }

  /**
   * Get file folder service.
   *
   * @return the file folder service
   */
  @Override
  public FileFolderService getFileFolderService() {
    return fileFolderService;
  }

  /**
   * Get script service.
   *
   * @return the script service
   */
  @Override
  public ScriptService getScriptService() {
    return scriptService;
  }

  /**
   * Get workflow service.
   *
   * @return the workflow service
   */
  @Override
  public WorkflowService getWorkflowService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get notification service.
   *
   * @return the notification service
   */
  @Override
  public NotificationService getNotificationService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get audit service.
   *
   * @return the audit service
   */
  @Override
  public AuditService getAuditService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get ownable service.
   *
   * @return the ownable service
   */
  @Override
  public OwnableService getOwnableService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get person service.
   *
   * @return the person service
   */
  @Override
  public PersonService getPersonService() {
    return personService;
  }

  /**
   * Get site service.
   *
   * @return the site service
   */
  @Override
  public SiteService getSiteService() {
    return siteService;
  }

  /**
   * Get attribute service.
   *
   * @return the attribute service
   */
  @Override
  public AttributeService getAttributeService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get multilingual content service.
   *
   * @return the multilingual content service
   */
  @Override
  public MultilingualContentService getMultilingualContentService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get edition service.
   *
   * @return the edition service
   */
  @Override
  public EditionService getEditionService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get thumbnail service.
   *
   * @return the thumbnail service
   */
  @Override
  public ThumbnailService getThumbnailService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get tagging service.
   *
   * @return the tagging service
   */
  @Override
  public TaggingService getTaggingService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get form service.
   *
   * @return the form service
   */
  @Override
  public FormService getFormService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get rendition service.
   *
   * @return the rendition service
   */
  @Override
  public RenditionService getRenditionService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get rating service.
   *
   * @return the rating service
   */
  @Override
  public RatingService getRatingService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get node locator service.
   *
   * @return the node locator service
   */
  @Override
  public NodeLocatorService getNodeLocatorService() {
    return nodeLocatorService;
  }

  /**
   * Get blog service.
   *
   * @return the blog service
   */
  @Override
  public BlogService getBlogService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get calendar service.
   *
   * @return the calendar service
   */
  @Override
  public CalendarService getCalendarService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get invitation service.
   *
   * @return the invitation service
   */
  @Override
  public InvitationService getInvitationService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get imap service.
   *
   * @return the imap service
   */
  @Override
  public ImapService getImapService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get public service access service.
   *
   * @return the public service access service
   */
  @Override
  public PublicServiceAccessService getPublicServiceAccessService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get repo admin service.
   *
   * @return the repo admin service
   */
  @Override
  public RepoAdminService getRepoAdminService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get sys admin params.
   *
   * @return the sys admin params
   */
  @Override
  public SysAdminParams getSysAdminParams() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get web dav service.
   *
   * @return the web dav service
   */
  @Override
  public WebDavService getWebDavService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Set bean factory.
   *
   * @param beanFactory the bean factory
   */
  @Override
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
   * Get c m i s dictionary service.
   *
   * @return the c m i s dictionary service
   */
  @Override
  public CMISDictionaryService getCMISDictionaryService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get c m i s query service.
   *
   * @return the c m i s query service
   */
  @Override
  public CMISQueryService getCMISQueryService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get solr facet helper.
   *
   * @return the solr facet helper
   */
  @Override
  public SolrFacetHelper getSolrFacetHelper() {
    return solrFacetHelper;
  }

  /**
   * Get facet label display handler registry.
   *
   * @return the facet label display handler registry
   */
  @Override
  public FacetLabelDisplayHandlerRegistry getFacetLabelDisplayHandlerRegistry() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Set solr facet helper.
   *
   * @param solrFacetHelper the solr facet helper
   */
  public void setSolrFacetHelper(SolrFacetHelper solrFacetHelper) {
    this.solrFacetHelper = solrFacetHelper;
  }

  /**
   * Get module service.
   *
   * @return the module service
   */
  @Override
  public ModuleService getModuleService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get message service.
   *
   * @return the message service
   */
  @Override
  public MessageService getMessageService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get document link service.
   *
   * @return the document link service
   */
  @Override
  public DocumentLinkService getDocumentLinkService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get policy component.
   *
   * @return the policy component
   */
  @Override
  public PolicyComponent getPolicyComponent() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Write external.
   *
   * @param out the out
   */
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

  /**
   * Read external.
   *
   * @param in the in
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
}
