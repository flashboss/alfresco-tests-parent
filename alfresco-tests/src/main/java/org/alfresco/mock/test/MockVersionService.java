package org.alfresco.mock.test;

import static org.alfresco.model.ContentModel.ASSOC_CONTAINS;
import static org.alfresco.model.ContentModel.PROP_CONTENT;
import static org.alfresco.model.ContentModel.PROP_CREATED;
import static org.alfresco.model.ContentModel.PROP_NAME;
import static org.alfresco.model.ContentModel.PROP_VERSION_LABEL;
import static org.alfresco.model.ContentModel.PROP_VERSION_TYPE;
import static org.alfresco.model.ContentModel.TYPE_CONTENT;
import static org.alfresco.repo.version.Version2Model.CHILD_QNAME_VERSIONS;
import static org.alfresco.repo.version.Version2Model.CHILD_QNAME_VERSION_HISTORIES;
import static org.alfresco.repo.version.Version2Model.PROP_QNAME_FROZEN_MODIFIED;
import static org.alfresco.repo.version.Version2Model.PROP_QNAME_FROZEN_NODE_REF;
import static org.alfresco.repo.version.Version2Model.PROP_QNAME_VERSIONED_NODE_ID;
import static org.alfresco.repo.version.Version2Model.STORE_ID;
import static org.alfresco.service.cmr.repository.StoreRef.PROTOCOL_WORKSPACE;
import static org.alfresco.service.namespace.NamespaceService.CONTENT_MODEL_PREFIX;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

/**
 * Mock implementation of the Alfresco VersionService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockVersionService implements VersionService, Serializable {

	@Autowired
	/** The node service. */
	private NodeService nodeService;

	@Autowired
	/** The content service. */
	private ContentService contentService;

	@Autowired
	/** The mimetype service. */
	private MimetypeService mimetypeService;

	@Autowired
	/** The namespace service. */
	private NamespaceService namespaceService;

	/** The version histories. */
	private Map<NodeRef, VersionHistory> versionHistories = new HashMap<NodeRef, VersionHistory>();

	@Override
	/**
	 * Get version store reference.
	 *
	 * @return the store ref
	 */
	public StoreRef getVersionStoreReference() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Is a version.
	 *
	 * @param nodeRef the node ref
	 * @return the boolean
	 */
	public boolean isAVersion(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Is versioned.
	 *
	 * @param nodeRef the node ref
	 * @return the boolean
	 */
	public boolean isVersioned(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Create version.
	 *
	 * @param nodeRef the node ref
	 * @param versionProperties the version properties
	 * @return the version
	 */
	public Version createVersion(NodeRef nodeRef, Map<String, Serializable> versionProperties)
			throws ReservedVersionNameException, AspectMissingException {
		Collection<Version> versions = createVersion(nodeRef, versionProperties, true);
		return versions.toArray(new Version[0])[versions.size() - 1];
	}

	@Override
	public Collection<Version> createVersion(NodeRef nodeRef, Map<String, Serializable> versionProperties,
			boolean versionChildren) throws ReservedVersionNameException, AspectMissingException {
		byte[] text = (byte[]) versionProperties.getOrDefault(PROP_CONTENT.getLocalName(), new byte[] {});

	/** The name. */
		String name = versionProperties.getOrDefault(PROP_NAME.getLocalName(), System.currentTimeMillis()).toString();
		InputStream inputStream = new ByteArrayInputStream(text);
		ContentWriter writer = contentService.getWriter(nodeRef, PROP_CONTENT, true);
		writer.setMimetype(mimetypeService.getMimetype(mimetypeService.getExtension(name)));
		writer.putContent(inputStream);

	/** The version. */
		String version = versionProperties.getOrDefault(PROP_VERSION_LABEL.getLocalName(), "").toString();

	 * @param null the null
	/** The version type. */
		String versionType = versionProperties.getOrDefault(Version2Model.PROP_VERSION_TYPE, "").toString();
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
			properties.put(PROP_CREATED, new Date());
			parentVersion = nodeService.createNode(versionStore, CHILD_QNAME_VERSION_HISTORIES,
					QName.createQName(CONTENT_MODEL_PREFIX, nodeRef.getId(), namespaceService), TYPE_CONTENT,
					properties).getChildRef();
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
			}
		}
		properties = new HashMap<QName, Serializable>();
		properties.put(PROP_NAME, name);
		properties.put(PROP_QNAME_FROZEN_NODE_REF, nodeRef.getId());
		properties.put(PROP_VERSION_LABEL, version);
		properties.put(PROP_VERSION_TYPE, versionType);
		properties.put(PROP_QNAME_FROZEN_MODIFIED, new Date());
		properties.put(PROP_CREATED, new Date());
		NodeRef versionedNode = nodeService
				.createNode(parentVersion, CHILD_QNAME_VERSIONS,
						QName.createQName(CONTENT_MODEL_PREFIX, name, namespaceService), TYPE_CONTENT, properties)
				.getChildRef();
		inputStream = new ByteArrayInputStream(text);
		writer = contentService.getWriter(versionedNode, PROP_CONTENT, true);
		writer.setMimetype(mimetypeService.getMimetype(mimetypeService.getExtension(name)));
		writer.putContent(inputStream);
		MockVersion mockVersion = new MockVersion(versionedNode, nodeRef, versionProperties);
		List<Version> list = (List<Version>) versionHistory.getAllVersions();
		list.add(0, mockVersion);
		versionHistories.put(nodeRef, versionHistory);
		return versionHistory.getAllVersions();
	}

	@Override
	/**
	 * Create version.
	 *
	 * @param nodeRefs the node refs
	 * @param versionProperties the version properties
	 * @return the collection
	 */
	public Collection<Version> createVersion(Collection<NodeRef> nodeRefs, Map<String, Serializable> versionProperties)
			throws ReservedVersionNameException, AspectMissingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get version history.
	 *
	 * @param nodeRef the node ref
	 * @return the version history
	 */
	public VersionHistory getVersionHistory(NodeRef nodeRef) throws AspectMissingException {
		return versionHistories.get(nodeRef);
	}

	@Override
	/**
	 * Get current version.
	 *
	 * @param nodeRef the node ref
	 * @return the version
	 */
	public Version getCurrentVersion(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Revert.
	 *
	 * @param nodeRef the node ref
	 */
	public void revert(NodeRef nodeRef) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Revert.
	 *
	 * @param nodeRef the node ref
	 * @param deep the deep
	 */
	public void revert(NodeRef nodeRef, boolean deep) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Revert.
	 *
	 * @param nodeRef the node ref
	 * @param version the version
	 */
	public void revert(NodeRef nodeRef, Version version) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Revert.
	 *
	 * @param nodeRef the node ref
	 * @param version the version
	 * @param deep the deep
	 */
	public void revert(NodeRef nodeRef, Version version, boolean deep) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Restore.
	 *
	 * @param nodeRef the node ref
	 * @param parentNodeRef the parent node ref
	 * @param assocTypeQName the assoc type q name
	 * @param assocQName the assoc q name
	 * @return the node ref
	 */
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
	/**
	 * Delete version history.
	 *
	 * @param nodeRef the node ref
	 */
	public void deleteVersionHistory(NodeRef nodeRef) throws AspectMissingException {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Delete version.
	 *
	 * @param nodeRef the node ref
	 * @param version the version
	 */
	public void deleteVersion(NodeRef nodeRef, Version version) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Ensure versioning enabled.
	 *
	 * @param nodeRef the node ref
	 * @param versionProperties the version properties
	 */
	public void ensureVersioningEnabled(NodeRef nodeRef, Map<QName, Serializable> versionProperties) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Register version label policy.
	 *
	 * @param typeQName the type q name
	 * @param policy the policy
	 */
	public void registerVersionLabelPolicy(QName typeQName, CalculateVersionLabelPolicy policy) {
		// TODO Auto-generated method stub

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
	 * Set content service.
	 *
	 * @param contentService the content service
	 */
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
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
	 * Set namespace service.
	 *
	 * @param namespaceService the namespace service
	 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	/**
	 * Init.
	 *
	 */
	public void init() {
		versionHistories.clear();
	}

}
