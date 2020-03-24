package org.alfresco.mock.test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.tenant.TenantService;
import org.alfresco.service.cmr.dictionary.InvalidAspectException;
import org.alfresco.service.cmr.dictionary.InvalidTypeException;
import org.alfresco.service.cmr.repository.AssociationExistsException;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.InvalidChildAssociationRefException;
import org.alfresco.service.cmr.repository.InvalidNodeRefException;
import org.alfresco.service.cmr.repository.InvalidStoreRefException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeRef.Status;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.Path;
import org.alfresco.service.cmr.repository.Path.Element;
import org.alfresco.service.cmr.repository.StoreExistsException;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.namespace.QNamePattern;

public class MockNodeService implements NodeService, Serializable {

	private Map<NodeRef, Map<QName, Serializable>> sampleProperties = new HashMap<NodeRef, Map<QName, Serializable>>();

	private Map<QName, Map<QName, Serializable>> sampleAspects = new HashMap<QName, Map<QName, Serializable>>();

	private Map<NodeRef, File> nodeRefs = new HashMap<NodeRef, File>();

	@Override
	public List<StoreRef> getStores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreRef createStore(String protocol, String identifier) throws StoreExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStore(StoreRef storeRef) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean exists(NodeRef nodeRef) {
		return nodeRefs.containsKey(nodeRef);
	}

	@Override
	public Status getNodeStatus(NodeRef nodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getNodeRef(Long nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getRootNode(StoreRef storeRef) throws InvalidStoreRefException {
		return nodeRefs.keySet().toArray(new NodeRef[0])[1];
	}

	@Override
	public Set<NodeRef> getAllRootNodes(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildAssociationRef createNode(NodeRef parentRef, QName assocTypeQName, QName assocQName,
			QName nodeTypeQName) throws InvalidNodeRefException, InvalidTypeException {
		String nodeUUID = assocQName.getLocalName();
		Path path = getPath(parentRef);
		String pathStr = "";
		if (path != null)
			pathStr = path.toString() + File.separator + nodeUUID;
		else
			pathStr = MockContentService.FOLDER_TEST;
		StoreRef storeRef = StoreRef.STORE_REF_WORKSPACE_SPACESSTORE;
		if (pathStr.contains(StoreRef.PROTOCOL_ARCHIVE))
			storeRef = StoreRef.STORE_REF_ARCHIVE_SPACESSTORE;
		NodeRef nodeRef = new NodeRef(storeRef + File.separator + pathStr);
		setProperty(nodeRef, ContentModel.PROP_NAME, nodeUUID);
		setProperty(nodeRef, ContentModel.TYPE_BASE, nodeTypeQName);
		File file = new File(pathStr);
		if (nodeTypeQName.equals(ContentModel.TYPE_FOLDER))
			file.mkdir();
		nodeRefs.put(nodeRef, new File(pathStr));
		return new ChildAssociationRef(assocTypeQName, parentRef, assocQName, nodeRef);
	}

	@Override
	public ChildAssociationRef createNode(NodeRef parentRef, QName assocTypeQName, QName assocQName,
			QName nodeTypeQName, Map<QName, Serializable> properties)
			throws InvalidNodeRefException, InvalidTypeException {
		String nodeUUID = assocQName.getLocalName();
		Path path = getPath(parentRef);
		String pathStr = "";
		if (path != null)
			pathStr = path.toString() + File.separator + nodeUUID;
		else
			pathStr = MockContentService.FOLDER_TEST;
		StoreRef storeRef = StoreRef.STORE_REF_WORKSPACE_SPACESSTORE;
		if (pathStr.contains(StoreRef.PROTOCOL_ARCHIVE))
			storeRef = StoreRef.STORE_REF_ARCHIVE_SPACESSTORE;
		NodeRef nodeRef = new NodeRef(storeRef + File.separator + pathStr);
		setProperties(nodeRef, properties);
		File file = new File(pathStr);
		setProperty(nodeRef, ContentModel.TYPE_BASE, nodeTypeQName);
		if (nodeTypeQName.equals(ContentModel.TYPE_FOLDER))
			file.mkdir();
		else
			try {
				file.createNewFile();
			} catch (IOException ex) {
				throw new InvalidNodeRefException(nodeRef);
			}
		nodeRefs.put(nodeRef, new File(pathStr));
		return new ChildAssociationRef(assocTypeQName, parentRef, assocQName, nodeRef);
	}

	@Override
	public ChildAssociationRef moveNode(NodeRef nodeToMoveRef, NodeRef newParentRef, QName assocTypeQName,
			QName assocQName) throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setChildAssociationIndex(ChildAssociationRef childAssocRef, int index)
			throws InvalidChildAssociationRefException {
		// TODO Auto-generated method stub

	}

	@Override
	public QName getType(NodeRef nodeRef) throws InvalidNodeRefException {
		Serializable object = getProperty(nodeRef, ContentModel.TYPE_BASE);
		if (object instanceof QName)
			return (QName) object;
		else
			return QName.createQName(object + "");
	}

	@Override
	public void setType(NodeRef nodeRef, QName typeQName) throws InvalidNodeRefException {
		setProperty(nodeRef, ContentModel.TYPE_BASE, typeQName);
	}

	@Override
	public void addAspect(NodeRef nodeRef, QName aspectTypeQName, Map<QName, Serializable> aspectProperties)
			throws InvalidNodeRefException, InvalidAspectException {
		sampleAspects.put(aspectTypeQName, aspectProperties);
		Map<QName, Serializable> properties = getNotNullProperties(nodeRef);
		properties.putAll(aspectProperties);
	}

	@Override
	public void removeAspect(NodeRef nodeRef, QName aspectTypeQName)
			throws InvalidNodeRefException, InvalidAspectException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasAspect(NodeRef nodeRef, QName aspectTypeQName)
			throws InvalidNodeRefException, InvalidAspectException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<QName> getAspects(NodeRef nodeRef) throws InvalidNodeRefException {
		return sampleAspects.keySet();
	}

	@Override
	public void deleteNode(NodeRef nodeRef) throws InvalidNodeRefException {
		nodeRefs.remove(nodeRef);
	}

	@Override
	public ChildAssociationRef addChild(NodeRef parentRef, NodeRef childRef, QName assocTypeQName, QName qname)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChildAssociationRef> addChild(Collection<NodeRef> parentRefs, NodeRef childRef, QName assocTypeQName,
			QName qname) throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeChild(NodeRef parentRef, NodeRef childRef) throws InvalidNodeRefException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean removeChildAssociation(ChildAssociationRef childAssocRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeSeconaryChildAssociation(ChildAssociationRef childAssocRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeSecondaryChildAssociation(ChildAssociationRef childAssocRef) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<QName, Serializable> getProperties(NodeRef nodeRef) throws InvalidNodeRefException {
		return sampleProperties.get(nodeRef);
	}

	@Override
	public Long getNodeAclId(NodeRef nodeRef) throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable getProperty(NodeRef nodeRef, QName qname) throws InvalidNodeRefException {
		return getNotNullProperties(nodeRef).get(qname);
	}

	@Override
	public void setProperties(NodeRef nodeRef, Map<QName, Serializable> properties) throws InvalidNodeRefException {
		sampleProperties.put(nodeRef, properties);
	}

	@Override
	public void addProperties(NodeRef nodeRef, Map<QName, Serializable> properties) throws InvalidNodeRefException {
		getNotNullProperties(nodeRef).putAll(properties);
	}

	@Override
	public void setProperty(NodeRef nodeRef, QName qname, Serializable value) throws InvalidNodeRefException {
		getNotNullProperties(nodeRef).put(qname, value);
	}

	@Override
	public void removeProperty(NodeRef nodeRef, QName qname) throws InvalidNodeRefException {
		getNotNullProperties(nodeRef).remove(qname);
	}

	@Override
	public List<ChildAssociationRef> getParentAssocs(NodeRef nodeRef) throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChildAssociationRef> getParentAssocs(NodeRef nodeRef, QNamePattern typeQNamePattern,
			QNamePattern qnamePattern) throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChildAssociationRef> getChildAssocs(NodeRef nodeRef) throws InvalidNodeRefException {
		List<ChildAssociationRef> result = new ArrayList<ChildAssociationRef>();
		for (NodeRef node : nodeRefs.keySet()) {
			Path path = getPath(node);
			String parentId = path.get(path.size() - 2).toString();
			if (nodeRef.getId().equals(parentId))
				result.add(
						new ChildAssociationRef(ContentModel.ASSOC_CONTAINS, nodeRef, ContentModel.TYPE_CONTENT, node));
		}
		return result;
	}

	@Override
	public List<ChildAssociationRef> getChildAssocs(NodeRef nodeRef, QNamePattern typeQNamePattern,
			QNamePattern qnamePattern) throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChildAssociationRef> getChildAssocs(NodeRef nodeRef, QNamePattern typeQNamePattern,
			QNamePattern qnamePattern, int maxResults, boolean preload) throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChildAssociationRef> getChildAssocs(NodeRef nodeRef, QNamePattern typeQNamePattern,
			QNamePattern qnamePattern, boolean preload) throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChildAssociationRef> getChildAssocs(NodeRef nodeRef, Set<QName> childNodeTypeQNames) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChildAssociationRef> getChildAssocsByPropertyValue(NodeRef nodeRef, QName propertyQName,
			Serializable value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getChildByName(NodeRef nodeRef, QName assocTypeQName, String childName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChildAssociationRef> getChildrenByName(NodeRef nodeRef, QName assocTypeQName,
			Collection<String> childNames) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildAssociationRef getPrimaryParent(NodeRef nodeRef) throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ChildAssociationRef> getChildAssocsWithoutParentAssocsOfType(NodeRef parent,
			QName assocTypeQName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssociationRef createAssociation(NodeRef sourceRef, NodeRef targetRef, QName assocTypeQName)
			throws InvalidNodeRefException, AssociationExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAssociation(NodeRef sourceRef, NodeRef targetRef, QName assocTypeQName)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAssociations(NodeRef sourceRef, QName assocTypeQName, List<NodeRef> targetRefs) {
		// TODO Auto-generated method stub

	}

	@Override
	public AssociationRef getAssoc(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssociationRef> getTargetAssocs(NodeRef sourceRef, QNamePattern qnamePattern)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssociationRef> getTargetAssocsByPropertyValue(NodeRef sourceRef, QNamePattern qnamePattern,
			QName propertyQName, Serializable propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssociationRef> getSourceAssocs(NodeRef targetRef, QNamePattern qnamePattern)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path getPath(NodeRef nodeRef) throws InvalidNodeRefException {
		File file = nodeRefs.get(nodeRef);
		if (file != null && file.exists()) {
			Path path = new Path();
			String[] paths = file.getPath().split(File.separator);
			for (String folder : paths)
				path.append(new MockElement(folder));
			return path;
		} else
			return null;
	}

	@Override
	public List<Path> getPaths(NodeRef nodeRef, boolean primaryOnly) throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getStoreArchiveNode(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef restoreNode(NodeRef archivedNodeRef, NodeRef destinationParentNodeRef, QName assocTypeQName,
			QName assocQName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NodeRef> findNodes(FindNodeParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countChildAssocs(NodeRef nodeRef, boolean isPrimary) throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return 0;
	}

	public class MockElement extends Path.Element {

		private String path;

		public MockElement(String path) {
			this.path = path;
		}

		@Override
		public String getElementString() {
			return path;
		}

		@Override
		public Element getBaseNameElement(TenantService tenantService) {
			return this;
		}

	}

	private Map<QName, Serializable> getNotNullProperties(NodeRef nodeRef) {
		Map<QName, Serializable> properties = getProperties(nodeRef);
		if (properties == null) {
			properties = new HashMap<QName, Serializable>();
			sampleProperties.put(nodeRef, properties);
		}
		return properties;
	}

	public Map<NodeRef, File> getNodeRefs() {
		return nodeRefs;
	}

}
