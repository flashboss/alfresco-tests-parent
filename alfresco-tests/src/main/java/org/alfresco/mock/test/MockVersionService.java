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
* Mock implementation of the MockVersionService class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockVersionService implements VersionService, Serializable {

/**
* The node service.
 */
	@Autowired
	private NodeService nodeService;

/**
* The content service.
 */
	@Autowired
	private ContentService contentService;

/**
* The mimetype service.
 */
	@Autowired
	private MimetypeService mimetypeService;

/**
* The namespace service.
 */
	@Autowired
	private NamespaceService namespaceService;

/**
* The version histories map.
 */
	private Map<NodeRef, VersionHistory> versionHistories = new HashMap<NodeRef, VersionHistory>();

/**
* {@inheritDoc}
* @return the result
 */
	@Override
	public StoreRef getVersionStoreReference() {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @return the result
 */
	@Override
	public boolean isAVersion(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @return the result
 */
	@Override
	public boolean isVersioned(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @return the result
 */
	@Override
	public Version createVersion(NodeRef nodeRef, Map<String, Serializable> versionProperties)
			throws ReservedVersionNameException, AspectMissingException {
		Collection<Version> versions = createVersion(nodeRef, versionProperties, true);
		return versions.toArray(new Version[0])[versions.size() - 1];
	}

/**
* {@inheritDoc}
 */
	@Override
	public Collection<Version> createVersion(NodeRef nodeRef, Map<String, Serializable> versionProperties,
			boolean versionChildren) throws ReservedVersionNameException, AspectMissingException {
		byte[] text = (byte[]) versionProperties.getOrDefault(PROP_CONTENT.getLocalName(), new byte[] {});
		String name = versionProperties.getOrDefault(PROP_NAME.getLocalName(), System.currentTimeMillis()).toString();
		InputStream inputStream = new ByteArrayInputStream(text);
		ContentWriter writer = contentService.getWriter(nodeRef, PROP_CONTENT, true);
		writer.setMimetype(mimetypeService.getMimetype(mimetypeService.getExtension(name)));
		writer.putContent(inputStream);
		String version = versionProperties.getOrDefault(PROP_VERSION_LABEL.getLocalName(), "").toString();
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

/**
* {@inheritDoc}
* @param nodeRefs the nodeRefs
* @return the result
 */
	@Override
	public Collection<Version> createVersion(Collection<NodeRef> nodeRefs, Map<String, Serializable> versionProperties)
			throws ReservedVersionNameException, AspectMissingException {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @return the result
* @throws AspectMissingException if an error occurs
 */
	@Override
	public VersionHistory getVersionHistory(NodeRef nodeRef) throws AspectMissingException {
		return versionHistories.get(nodeRef);
	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @return the result
 */
	@Override
	public Version getCurrentVersion(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
 */
	@Override
	public void revert(NodeRef nodeRef) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @param deep the deep
 */
	@Override
	public void revert(NodeRef nodeRef, boolean deep) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @param version the version
 */
	@Override
	public void revert(NodeRef nodeRef, Version version) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @param version the version
* @param deep the deep
 */
	@Override
	public void revert(NodeRef nodeRef, Version version, boolean deep) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @param parentNodeRef the parentNodeRef
* @param assocTypeQName the assocTypeQName
* @param assocQName the assocQName
* @return the result
 */
	@Override
	public NodeRef restore(NodeRef nodeRef, NodeRef parentNodeRef, QName assocTypeQName, QName assocQName) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
 */
	@Override
	public NodeRef restore(NodeRef nodeRef, NodeRef parentNodeRef, QName assocTypeQName, QName assocQName,
			boolean deep) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @throws AspectMissingException if an error occurs
 */
	@Override
	public void deleteVersionHistory(NodeRef nodeRef) throws AspectMissingException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @param version the version
 */
	@Override
	public void deleteVersion(NodeRef nodeRef, Version version) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
 */
	@Override
	public void ensureVersioningEnabled(NodeRef nodeRef, Map<QName, Serializable> versionProperties) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param typeQName the typeQName
* @param policy the policy
 */
	@Override
	public void registerVersionLabelPolicy(QName typeQName, CalculateVersionLabelPolicy policy) {
		// TODO Auto-generated method stub

	}

/**
* Sets the node service.
* @param nodeService the nodeService
 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

/**
* Sets the content service.
* @param contentService the contentService
 */
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

/**
* Sets the mimetype service.
* @param mimetypeService the mimetypeService
 */
	public void setMimetypeService(MimetypeService mimetypeService) {
		this.mimetypeService = mimetypeService;
	}

/**
* Sets the namespace service.
* @param namespaceService the namespaceService
 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

/**
* Initializes the component.
 */
	public void init() {
		versionHistories.clear();
	}

}
