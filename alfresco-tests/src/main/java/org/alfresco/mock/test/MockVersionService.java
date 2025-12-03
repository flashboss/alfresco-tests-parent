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
 * Mock implementation of the {@link VersionService} interface for testing purposes.
 * This class provides version management functionality for nodes in a mock Alfresco repository,
 * supporting version creation, history retrieval, and version store management.
 *
 * @author lucastancapiano
 */
public class MockVersionService implements VersionService, Serializable {

	/**
	 * The node service for node operations.
	 */
	@Autowired
	private NodeService nodeService;

	/**
	 * The content service for content operations.
	 */
	@Autowired
	private ContentService contentService;

	/**
	 * The mimetype service for mimetype resolution.
	 */
	@Autowired
	private MimetypeService mimetypeService;

	/**
	 * The namespace service for QName resolution.
	 */
	@Autowired
	private NamespaceService namespaceService;

	/**
	 * Map storing version histories keyed by node reference.
	 */
	private Map<NodeRef, VersionHistory> versionHistories = new HashMap<NodeRef, VersionHistory>();

	/**
	 * {@inheritDoc}
	 * Gets the version store reference.
	 *
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public StoreRef getVersionStoreReference() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Checks if a node is a version node.
	 *
	 * @param nodeRef The node reference to check.
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isAVersion(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Checks if a node is versioned.
	 *
	 * @param nodeRef The node reference to check.
	 * @return {@code false} as this is a mock implementation.
	 */
	@Override
	public boolean isVersioned(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Creates a new version of the specified node.
	 *
	 * @param nodeRef The node reference to version.
	 * @param versionProperties Properties for the new version (including content, name, label, type).
	 * @return The newly created {@link Version}.
	 * @throws ReservedVersionNameException If the version name is reserved.
	 * @throws AspectMissingException If required aspects are missing.
	 */
	@Override
	public Version createVersion(NodeRef nodeRef, Map<String, Serializable> versionProperties)
			throws ReservedVersionNameException, AspectMissingException {
		Collection<Version> versions = createVersion(nodeRef, versionProperties, true);
		return versions.toArray(new Version[0])[versions.size() - 1];
	}

	/**
	 * {@inheritDoc}
	 * Creates a new version of the specified node with optional child versioning.
	 *
	 * @param nodeRef The node reference to version.
	 * @param versionProperties Properties for the new version.
	 * @param versionChildren Whether to version children as well.
	 * @return A collection of all versions for the node.
	 * @throws ReservedVersionNameException If the version name is reserved.
	 * @throws AspectMissingException If required aspects are missing.
	 */
	@Override
	public Collection<Version> createVersion(NodeRef nodeRef, Map<String, Serializable> versionProperties,
			boolean versionChildren) throws ReservedVersionNameException, AspectMissingException {
		byte[] text = (byte[]) versionProperties.get(PROP_CONTENT.getLocalName());
		if (text == null)
			text = new byte[] {};
		String name = versionProperties.get(PROP_NAME.getLocalName()).toString();
		if (name == null)
			name = System.currentTimeMillis() + "";
		InputStream inputStream = new ByteArrayInputStream(text);
		ContentWriter writer = contentService.getWriter(nodeRef, PROP_CONTENT, true);
		writer.setMimetype(mimetypeService.getMimetype(mimetypeService.getExtension(name)));
		writer.putContent(inputStream);
		String version = versionProperties.get(PROP_VERSION_LABEL.getLocalName()).toString();
		if (version == null)
			version = "";
		String versionType = versionProperties.get(Version2Model.PROP_VERSION_TYPE).toString();
		if (versionType == null)
			versionType = "";
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
					QName.createQName(CONTENT_MODEL_PREFIX, nodeRef.getId(), namespaceService),
					TYPE_CONTENT, properties).getChildRef();
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
		NodeRef versionedNode = nodeService.createNode(parentVersion, CHILD_QNAME_VERSIONS,
				QName.createQName(CONTENT_MODEL_PREFIX, name, namespaceService), TYPE_CONTENT,
				properties).getChildRef();
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

	/**
	 * {@inheritDoc}
	 * Creates versions for multiple nodes.
	 *
	 * @param nodeRefs The collection of node references to version.
	 * @param versionProperties Properties for the new versions.
	 * @return {@code null} as this is a mock implementation.
	 * @throws ReservedVersionNameException If a version name is reserved.
	 * @throws AspectMissingException If required aspects are missing.
	 */
	@Override
	public Collection<Version> createVersion(Collection<NodeRef> nodeRefs, Map<String, Serializable> versionProperties)
			throws ReservedVersionNameException, AspectMissingException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Gets the version history for a node.
	 *
	 * @param nodeRef The node reference.
	 * @return The {@link VersionHistory} for the node, or {@code null} if none exists.
	 * @throws AspectMissingException If required aspects are missing.
	 */
	@Override
	public VersionHistory getVersionHistory(NodeRef nodeRef) throws AspectMissingException {
		return versionHistories.get(nodeRef);
	}

	/**
	 * {@inheritDoc}
	 * Gets the current version of a node.
	 *
	 * @param nodeRef The node reference.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public Version getCurrentVersion(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Reverts a node to its previous version.
	 *
	 * @param nodeRef The node reference to revert.
	 */
	@Override
	public void revert(NodeRef nodeRef) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 * Reverts a node to its previous version with optional deep revert.
	 *
	 * @param nodeRef The node reference to revert.
	 * @param deep Whether to revert children as well.
	 */
	@Override
	public void revert(NodeRef nodeRef, boolean deep) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 * Reverts a node to a specific version.
	 *
	 * @param nodeRef The node reference to revert.
	 * @param version The version to revert to.
	 */
	@Override
	public void revert(NodeRef nodeRef, Version version) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 * Reverts a node to a specific version with optional deep revert.
	 *
	 * @param nodeRef The node reference to revert.
	 * @param version The version to revert to.
	 * @param deep Whether to revert children as well.
	 */
	@Override
	public void revert(NodeRef nodeRef, Version version, boolean deep) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 * Restores a deleted node from the version store.
	 *
	 * @param nodeRef The version node reference.
	 * @param parentNodeRef The parent node reference for restoration.
	 * @param assocTypeQName The association type QName.
	 * @param assocQName The association QName.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public NodeRef restore(NodeRef nodeRef, NodeRef parentNodeRef, QName assocTypeQName, QName assocQName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Restores a deleted node from the version store with optional deep restore.
	 *
	 * @param nodeRef The version node reference.
	 * @param parentNodeRef The parent node reference for restoration.
	 * @param assocTypeQName The association type QName.
	 * @param assocQName The association QName.
	 * @param deep Whether to restore children as well.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public NodeRef restore(NodeRef nodeRef, NodeRef parentNodeRef, QName assocTypeQName, QName assocQName,
			boolean deep) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Deletes the entire version history for a node.
	 *
	 * @param nodeRef The node reference.
	 * @throws AspectMissingException If required aspects are missing.
	 */
	@Override
	public void deleteVersionHistory(NodeRef nodeRef) throws AspectMissingException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 * Deletes a specific version from a node's history.
	 *
	 * @param nodeRef The node reference.
	 * @param version The version to delete.
	 */
	@Override
	public void deleteVersion(NodeRef nodeRef, Version version) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 * Ensures versioning is enabled for a node.
	 *
	 * @param nodeRef The node reference.
	 * @param versionProperties The versioning properties to apply.
	 */
	@Override
	public void ensureVersioningEnabled(NodeRef nodeRef, Map<QName, Serializable> versionProperties) {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 * Registers a version label calculation policy for a type.
	 *
	 * @param typeQName The type QName.
	 * @param policy The version label policy.
	 */
	@Override
	public void registerVersionLabelPolicy(QName typeQName, CalculateVersionLabelPolicy policy) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets the node service.
	 *
	 * @param nodeService The {@link NodeService} instance to set.
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * Sets the content service.
	 *
	 * @param contentService The {@link ContentService} instance to set.
	 */
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	/**
	 * Sets the mimetype service.
	 *
	 * @param mimetypeService The {@link MimetypeService} instance to set.
	 */
	public void setMimetypeService(MimetypeService mimetypeService) {
		this.mimetypeService = mimetypeService;
	}

	/**
	 * Sets the namespace service.
	 *
	 * @param namespaceService The {@link NamespaceService} instance to set.
	 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	/**
	 * Initializes the mock version service by clearing all version histories.
	 */
	public void init() {
		versionHistories.clear();
	}

}
