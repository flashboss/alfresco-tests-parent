package org.alfresco.mock.test;

import static org.alfresco.model.ContentModel.ASSOC_CONTAINS;
import static org.alfresco.model.ContentModel.PROP_NAME;
import static org.alfresco.model.ContentModel.TYPE_FOLDER;
import static org.alfresco.service.cmr.repository.StoreRef.STORE_REF_WORKSPACE_SPACESSTORE;
import static org.alfresco.service.cmr.search.SearchService.LANGUAGE_FTS_ALFRESCO;
import static org.alfresco.service.cmr.site.SiteVisibility.PUBLIC;
import static org.alfresco.service.namespace.NamespaceService.CONTENT_MODEL_1_0_URI;
import static org.alfresco.service.namespace.QName.createQName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.alfresco.query.PagingRequest;
import org.alfresco.query.PagingResults;
import org.alfresco.repo.node.getchildren.FilterProp;
import org.alfresco.repo.site.SiteMembership;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.site.SiteInfo;
import org.alfresco.service.cmr.site.SiteMemberInfo;
import org.alfresco.service.cmr.site.SiteService;
import org.alfresco.service.cmr.site.SiteVisibility;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mock implementation of the Alfresco SiteService for testing purposes. Provides stub
 * implementations for testing without a running Alfresco server.
 *
 * @author vige
 */
public class MockSiteService implements SiteService, Serializable {

  /** The search service. */
  @Autowired private SearchService searchService;

  /** The node service. */
  @Autowired private NodeService nodeService;

  @Override
  public SiteInfo createSite(
      String sitePreset, String shortName, String title, String description, boolean isPublic) {
    ResultSet resultQ =
        searchService.query(
            STORE_REF_WORKSPACE_SPACESSTORE, LANGUAGE_FTS_ALFRESCO, "PATH:\"company_home/sites\"");
    NodeRef result = resultQ.getNodeRef(0);

    /** The assoc q name. */
    QName assocQName = createQName(CONTENT_MODEL_1_0_URI, shortName);
    ChildAssociationRef nodeRef =
        nodeService.createNode(result, ASSOC_CONTAINS, assocQName, TYPE_FOLDER);
    SiteInfo siteInfo = new MockSiteInfo(nodeRef.getChildRef(), shortName);
    return siteInfo;
  }

  /**
   * Can add member.
   *
   * @param shortName the short name
   * @param authorityName the authority name
   * @param role the role
   * @return the boolean
   */
  @Override
  public boolean canAddMember(String shortName, String authorityName, String role) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public SiteInfo createSite(
      String sitePreset,
      String shortName,
      String title,
      String description,
      SiteVisibility visibility) {
    return createSite(sitePreset, shortName, title, description, visibility == PUBLIC);
  }

  @Override
  public SiteInfo createSite(
      String sitePreset,
      String shortName,
      String title,
      String description,
      SiteVisibility visibility,
      QName siteType) {
    return createSite(sitePreset, shortName, title, description, visibility == PUBLIC);
  }

  /**
   * Has create site permissions.
   *
   * @return the boolean
   */
  @Override
  public boolean hasCreateSitePermissions() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Find sites.
   *
   * @param filter the filter
   * @param sitePresetFilter the site preset filter
   * @param size the size
   * @return the list
   */
  @Override
  public List<SiteInfo> findSites(String filter, String sitePresetFilter, int size) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * List sites.
   *
   * @param filter the filter
   * @param sitePresetFilter the site preset filter
   * @param size the size
   * @return the list
   */
  @Override
  public List<SiteInfo> listSites(String filter, String sitePresetFilter, int size) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * List sites.
   *
   * @param filter the filter
   * @param sitePresetFilter the site preset filter
   * @return the list
   */
  @Override
  public List<SiteInfo> listSites(String filter, String sitePresetFilter) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * List sites.
   *
   * @param userName the user name
   * @return the list
   */
  @Override
  public List<SiteInfo> listSites(String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PagingResults<SiteInfo> listSites(
      List<FilterProp> filterProps,
      List<Pair<QName, Boolean>> sortProps,
      PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * List sites.
   *
   * @param userName the user name
   * @param size the size
   * @return the list
   */
  @Override
  public List<SiteInfo> listSites(String userName, int size) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get site.
   *
   * @param shortName the short name
   * @return the site info
   */
  @Override
  public SiteInfo getSite(String shortName) {
    ResultSet resultQ =
        searchService.query(
            StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
            LANGUAGE_FTS_ALFRESCO,
            "PATH:\"company_home/sites/" + shortName + "\"");
    NodeRef result = resultQ.getNodeRef(0);
    SiteInfo siteInfo = new MockSiteInfo(result, shortName);
    return siteInfo;
  }

  /**
   * Get site.
   *
   * @param nodeRef the node ref
   * @return the site info
   */
  @Override
  public SiteInfo getSite(NodeRef nodeRef) {
    return new MockSiteInfo(nodeRef, nodeService.getProperty(nodeRef, PROP_NAME) + "");
  }

  /**
   * Get site short name.
   *
   * @param nodeRef the node ref
   * @return the string
   */
  @Override
  public String getSiteShortName(NodeRef nodeRef) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Has site.
   *
   * @param shortName the short name
   * @return the boolean
   */
  @Override
  public boolean hasSite(String shortName) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Update site.
   *
   * @param siteInfo the site info
   */
  @Override
  public void updateSite(SiteInfo siteInfo) {
    // TODO Auto-generated method stub

  }

  /**
   * Delete site.
   *
   * @param shortName the short name
   */
  @Override
  public void deleteSite(String shortName) {
    // TODO Auto-generated method stub

  }

  @Override
  public void listMembers(
      String shortName,
      String nameFilter,
      String roleFilter,
      boolean collapseGroups,
      SiteMembersCallback callback) {
    // TODO Auto-generated method stub

  }

  /**
   * List members.
   *
   * @param shortName the short name
   * @param nameFilter the name filter
   * @param roleFilter the role filter
   * @param size the size
   */
  @Override
  public Map<String, String> listMembers(
      String shortName, String nameFilter, String roleFilter, int size) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<String, String> listMembers(
      String shortName, String nameFilter, String roleFilter, int size, boolean collapseGroups) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<SiteMemberInfo> listMembersInfo(
      String shortName, String nameFilter, String roleFilter, int size, boolean collapseGroups) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PagingResults<SiteMembership> listMembersPaged(
      String shortName,
      boolean collapseGroups,
      List<Pair<SortFields, Boolean>> sortProps,
      PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get members role info.
   *
   * @param shortName the short name
   * @param authorityName the authority name
   * @return the site member info
   */
  @Override
  public SiteMemberInfo getMembersRoleInfo(String shortName, String authorityName) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Is member.
   *
   * @param shortName the short name
   * @param authorityName the authority name
   * @return the boolean
   */
  @Override
  public boolean isMember(String shortName, String authorityName) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Set membership.
   *
   * @param shortName the short name
   * @param authorityName the authority name
   * @param role the role
   */
  @Override
  public void setMembership(String shortName, String authorityName, String role) {
    // TODO Auto-generated method stub

  }

  /**
   * Remove membership.
   *
   * @param shortName the short name
   * @param authorityName the authority name
   */
  @Override
  public void removeMembership(String shortName, String authorityName) {
    // TODO Auto-generated method stub

  }

  @Override
  public NodeRef createContainer(
      String shortName,
      String componentId,
      QName containerType,
      Map<QName, Serializable> containerProperties) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get container.
   *
   * @param shortName the short name
   * @param componentId the component id
   * @return the node ref
   */
  @Override
  public NodeRef getContainer(String shortName, String componentId) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * List containers.
   *
   * @param shortName the short name
   * @param pagingRequest the paging request
   * @return the paging results
   */
  @Override
  public PagingResults<FileInfo> listContainers(String shortName, PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Has container.
   *
   * @param shortName the short name
   * @param componentId the component id
   * @return the boolean
   */
  @Override
  public boolean hasContainer(String shortName, String componentId) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Get site roles.
   *
   * @return the list
   */
  @Override
  public List<String> getSiteRoles() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get site roles.
   *
   * @param shortName the short name
   * @return the list
   */
  @Override
  public List<String> getSiteRoles(String shortName) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get site group.
   *
   * @param shortName the short name
   * @return the string
   */
  @Override
  public String getSiteGroup(String shortName) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get site role group.
   *
   * @param shortName the short name
   * @param role the role
   * @return the string
   */
  @Override
  public String getSiteRoleGroup(String shortName, String role) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get site root.
   *
   * @return the node ref
   */
  @Override
  public NodeRef getSiteRoot() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Clean site permissions.
   *
   * @param relocatedNode the relocated node
   * @param containingSite the containing site
   */
  @Override
  public void cleanSitePermissions(NodeRef relocatedNode, SiteInfo containingSite) {
    // TODO Auto-generated method stub

  }

  @Override
  public PagingResults<SiteMembership> listSitesPaged(
      String userName, List<Pair<SortFields, Boolean>> sortProps, PagingRequest pagingRequest) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Resolve site.
   *
   * @param group the group
   * @return the string
   */
  @Override
  public String resolveSite(String group) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get members role.
   *
   * @param shortName the short name
   * @param authorityName the authority name
   * @return the string
   */
  @Override
  public String getMembersRole(String shortName, String authorityName) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Count authorities with role.
   *
   * @param shortName the short name
   * @param role the role
   * @return the int
   */
  @Override
  public int countAuthoritiesWithRole(String shortName, String role) {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * Find sites.
   *
   * @param filter the filter
   * @param size the size
   * @return the list
   */
  @Override
  public List<SiteInfo> findSites(String filter, int size) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Is site admin.
   *
   * @param userName the user name
   * @return the boolean
   */
  @Override
  public boolean isSiteAdmin(String userName) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * List site memberships.
   *
   * @param userName the user name
   * @param size the size
   * @return the list
   */
  @Override
  public List<SiteMembership> listSiteMemberships(String userName, int size) {
    // TODO Auto-generated method stub
    return null;
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
   * Set node service.
   *
   * @param nodeService the node service
   */
  public void setNodeService(NodeService nodeService) {
    this.nodeService = nodeService;
  }
}
