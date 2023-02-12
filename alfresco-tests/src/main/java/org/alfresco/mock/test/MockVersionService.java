package org.alfresco.mock.test;

import static org.alfresco.model.ContentModel.ASSOC_CONTAINS;
import static org.alfresco.model.ContentModel.PROP_CONTENT;
import static org.alfresco.model.ContentModel.PROP_NAME;
import static org.alfresco.model.ContentModel.PROP_VERSION_LABEL;
import static org.alfresco.model.ContentModel.PROP_VERSION_TYPE;
import static org.alfresco.model.ContentModel.TYPE_CONTENT;
import static org.alfresco.repo.version.Version2Model.PROP_QNAME_FROZEN_NODE_REF;
import static org.alfresco.repo.version.Version2Model.PROP_QNAME_VERSIONED_NODE_ID;
import static org.alfresco.repo.version.Version2Model.STORE_ID;
import static org.alfresco.service.cmr.repository.StoreRef.PROTOCOL_WORKSPACE;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.version.Version2Model;
import org.alfresco.repo.version.VersionServicePolicies.CalculateVersionLabelPolicy;
import org.alfresco.service.cmr.repository.AspectMissingException;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.MimetypeService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.version.ReservedVersionNameException;
import org.alfresco.service.cmr.version.Version;
import org.alfresco.service.cmr.version.VersionHistory;
import org.alfresco.service.cmr.version.VersionService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.springframework.beans.factory.annotation.Autowired;

public class MockVersionService implements VersionService, Serializable {

	@Autowired
	private NodeService nodeService;

	@Autowired
	private ContentService contentService;

	@Autowired
	private MimetypeService mimetypeService;

	@Autowired
	private NamespaceService namespaceService;

	private Map<NodeRef, VersionHistory> versionHistories = new HashMap<NodeRef, VersionHistory>();

	@Override
	public StoreRef getVersionStoreReference() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAVersion(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVersioned(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Version createVersion(NodeRef nodeRef, Map<String, Serializable> versionProperties)
			throws ReservedVersionNameException, AspectMissingException {
		Collection<Version> versions = createVersion(nodeRef, versionProperties, true);
		return versions.toArray(new Version[0])[versions.size() - 1];
	}

	@Override
	public Collection<Version> createVersion(NodeRef nodeRef, Map<String, Serializable> versionProperties,
			boolean versionChildren) throws ReservedVersionNameException, AspectMissingException {
		byte[] text = (byte[]) versionProperties.get(PROP_CONTENT.getLocalName());
		String name = versionProperties.get(PROP_NAME.getLocalName()).toString();
		InputStream inputStream = new ByteArrayInputStream(text);
		ContentWriter writer = contentService.getWriter(nodeRef, PROP_CONTENT, true);
		writer.setMimetype(mimetypeService.getMimetype(mimetypeService.getExtension(name)));
		writer.putContent(inputStream);
		String version = versionProperties.get(PROP_VERSION_LABEL.getLocalName()).toString();
		String versionType = versionProperties.get(Version2Model.PROP_VERSION_TYPE).toString();
		nodeService.setProperty(nodeRef, PROP_VERSION_LABEL, version);
		nodeService.setProperty(nodeRef, PROP_VERSION_TYPE, versionProperties.get(Version2Model.PROP_VERSION_TYPE));
		VersionHistory versionHistory = versionHistories.get(nodeRef);
		if (versionHistory == null)
			versionHistory = new MockVersionHistory();
		NodeRef versionStore = nodeService.getRootNode(new StoreRef(PROTOCOL_WORKSPACE, STORE_ID));
		Map<QName, Serializable> properties = new HashMap<QName, Serializable>();
		NodeRef parentVersion = nodeService.getChildByName(versionStore, ASSOC_CONTAINS, nodeRef.getId());
		if (parentVersion == null) {
			properties.put(PROP_QNAME_VERSIONED_NODE_ID, nodeRef.getId());
			properties.put(PROP_NAME, nodeRef.getId());
			parentVersion = nodeService.createNode(versionStore, ASSOC_CONTAINS,
					QName.createQName(NamespaceService.CONTENT_MODEL_PREFIX, nodeRef.getId(), namespaceService),
					TYPE_CONTENT, properties).getChildRef();
		}
		properties = new HashMap<QName, Serializable>();
		properties.put(PROP_NAME, name);
		properties.put(PROP_QNAME_FROZEN_NODE_REF, nodeRef.getId());
		properties.put(PROP_VERSION_LABEL, version);
		properties.put(PROP_VERSION_TYPE, versionType);
		NodeRef versionedNode = nodeService.createNode(parentVersion, ASSOC_CONTAINS,
				QName.createQName(NamespaceService.CONTENT_MODEL_PREFIX, name, namespaceService), TYPE_CONTENT,
				properties).getChildRef();
		inputStream = new ByteArrayInputStream(text);
		writer = contentService.getWriter(versionedNode, ContentModel.PROP_CONTENT, true);
		writer.setMimetype(mimetypeService.getMimetype(mimetypeService.getExtension(name)));
		writer.putContent(inputStream);
		MockVersion mockVersion = new MockVersion(versionedNode, nodeRef, versionProperties);
		List<Version> list = (List<Version>) versionHistory.getAllVersions();
		list.add(0, mockVersion);
		versionHistories.put(nodeRef, versionHistory);
		return versionHistory.getAllVersions();
	}

	@Override
	public Collection<Version> createVersion(Collection<NodeRef> nodeRefs, Map<String, Serializable> versionProperties)
			throws ReservedVersionNameException, AspectMissingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VersionHistory getVersionHistory(NodeRef nodeRef) throws AspectMissingException {
		return versionHistories.get(nodeRef);
	}

	@Override
	public Version getCurrentVersion(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void revert(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revert(NodeRef nodeRef, boolean deep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revert(NodeRef nodeRef, Version version) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revert(NodeRef nodeRef, Version version, boolean deep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NodeRef restore(NodeRef nodeRef, NodeRef parentNodeRef, QName assocTypeQName, QName assocQName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef restore(NodeRef nodeRef, NodeRef parentNodeRef, QName assocTypeQName, QName assocQName,
			boolean deep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVersionHistory(NodeRef nodeRef) throws AspectMissingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVersion(NodeRef nodeRef, Version version) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ensureVersioningEnabled(NodeRef nodeRef, Map<QName, Serializable> versionProperties) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerVersionLabelPolicy(QName typeQName, CalculateVersionLabelPolicy policy) {
		// TODO Auto-generated method stub

	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public void setMimetypeService(MimetypeService mimetypeService) {
		this.mimetypeService = mimetypeService;
	}

	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	public void init() {
		versionHistories.clear();
	}

}
