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
import org.alfresco.repo.site.SiteGroupMembership;
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

public class MockSiteService implements SiteService, Serializable {

	@Autowired
	private SearchService searchService;

	@Autowired
	private NodeService nodeService;

	@Override
	public SiteInfo createSite(String sitePreset, String shortName, String title, String description,
			boolean isPublic) {
		ResultSet resultQ = searchService.query(STORE_REF_WORKSPACE_SPACESSTORE, LANGUAGE_FTS_ALFRESCO,
				"PATH:\"company_home/sites");
		NodeRef result = resultQ.getNodeRef(0);
		QName assocQName = createQName(CONTENT_MODEL_1_0_URI, shortName);
		ChildAssociationRef nodeRef = nodeService.createNode(result, ASSOC_CONTAINS, assocQName, TYPE_FOLDER);
		SiteInfo siteInfo = new MockSiteInfo(nodeRef.getChildRef(), shortName);
		return siteInfo;
	}

	@Override
	public boolean canAddMember(String shortName, String authorityName, String role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SiteInfo createSite(String sitePreset, String shortName, String title, String description,
			SiteVisibility visibility) {
		return createSite(sitePreset, shortName, title, description, visibility == PUBLIC);
	}

	@Override
	public SiteInfo createSite(String sitePreset, String shortName, String title, String description,
			SiteVisibility visibility, QName siteType) {
		return createSite(sitePreset, shortName, title, description, visibility == PUBLIC);
	}

	@Override
	public boolean hasCreateSitePermissions() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SiteInfo> findSites(String filter, String sitePresetFilter, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SiteInfo> listSites(String filter, String sitePresetFilter, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SiteInfo> listSites(String filter, String sitePresetFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SiteInfo> listSites(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<SiteInfo> listSites(List<FilterProp> filterProps, List<Pair<QName, Boolean>> sortProps,
			PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SiteInfo> listSites(String userName, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SiteInfo getSite(String shortName) {
		ResultSet resultQ = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, LANGUAGE_FTS_ALFRESCO,
				"PATH:\"company_home/sites/" + shortName + "\"");
		NodeRef result = resultQ.getNodeRef(0);
		SiteInfo siteInfo = new MockSiteInfo(result, shortName);
		return siteInfo;
	}

	@Override
	public SiteInfo getSite(NodeRef nodeRef) {
		return new MockSiteInfo(nodeRef, nodeService.getProperty(nodeRef, PROP_NAME) + "");
	}

	@Override
	public String getSiteShortName(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasSite(String shortName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateSite(SiteInfo siteInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSite(String shortName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void listMembers(String shortName, String nameFilter, String roleFilter, boolean collapseGroups,
			SiteMembersCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, String> listMembers(String shortName, String nameFilter, String roleFilter, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> listMembers(String shortName, String nameFilter, String roleFilter, int size,
			boolean collapseGroups) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SiteMemberInfo> listMembersInfo(String shortName, String nameFilter, String roleFilter, int size,
			boolean collapseGroups) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<SiteMembership> listMembersPaged(String shortName, boolean collapseGroups,
			List<Pair<SortFields, Boolean>> sortProps, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SiteMemberInfo getMembersRoleInfo(String shortName, String authorityName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMember(String shortName, String authorityName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setMembership(String shortName, String authorityName, String role) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeMembership(String shortName, String authorityName) {
		// TODO Auto-generated method stub

	}

	@Override
	public NodeRef createContainer(String shortName, String componentId, QName containerType,
			Map<QName, Serializable> containerProperties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getContainer(String shortName, String componentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResults<FileInfo> listContainers(String shortName, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasContainer(String shortName, String componentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getSiteRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getSiteRoles(String shortName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSiteGroup(String shortName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSiteRoleGroup(String shortName, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getSiteRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cleanSitePermissions(NodeRef relocatedNode, SiteInfo containingSite) {
		// TODO Auto-generated method stub

	}

	@Override
	public PagingResults<SiteMembership> listSitesPaged(String userName, List<Pair<SortFields, Boolean>> sortProps,
			PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String resolveSite(String group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMembersRole(String shortName, String authorityName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAuthoritiesWithRole(String shortName, String role) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SiteInfo> findSites(String filter, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSiteAdmin(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SiteMembership> listSiteMemberships(String userName, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void listMembers(String shortName, String nameFilter, String roleFilter, boolean includeUsers,
			boolean includeGroups, boolean expandGroups, SiteMembersCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PagingResults<SiteGroupMembership> listGroupMembersPaged(String shortName,
			List<Pair<SortFields, Boolean>> sortProps, PagingRequest pagingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
