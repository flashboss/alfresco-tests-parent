package org.alfresco.mock.test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.mock.NodeUtils;
import org.alfresco.mock.test.MockNodeService.MockElement;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.tenant.TenantService;
import org.alfresco.repo.version.Version2Model;
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
import org.alfresco.service.cmr.repository.NodeService.FindNodeParameters;
import org.alfresco.service.cmr.repository.Path;
import org.alfresco.service.cmr.repository.Path.Element;
import org.alfresco.service.cmr.repository.StoreExistsException;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.security.AccessPermission;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.service.namespace.QNamePattern;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mock implementation of the Alfresco NodeService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockNodeService implements NodeService, Serializable {

	private static Map<NodeRef, Map<QName, Serializable>> sampleProperties = new HashMap<NodeRef, Map<QName, Serializable>>();

	private static Map<NodeRef, Map<QName, Map<QName, Serializable>>> sampleAspects = new HashMap<NodeRef, Map<QName, Map<QName, Serializable>>>();

	private static Map<NodeRef, Set<AccessPermission>> samplePermissions = new HashMap<NodeRef, Set<AccessPermission>>();

	private static Map<NodeRef, File> nodeRefs = new FilteredHashMap();

	private static Map<NodeRef, Map<QName, Set<NodeRef>>> srcAssociations = new HashMap<NodeRef, Map<QName, Set<NodeRef>>>();
	private static Map<NodeRef, Map<QName, Set<NodeRef>>> trgAssociations = new HashMap<NodeRef, Map<QName, Set<NodeRef>>>();

	public final static QName PRIMARY_PARENT = QName.createQName("primary_parent");

	@Autowired
	private NamespaceService namespaceService;

	private long countDbids;

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
		return true;
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
		for (NodeRef nodeRef : nodeRefs.keySet()) {
			String path = getPathAsString(nodeRef);
			if (path != null && path.endsWith(storeRef.getProtocol() + "/" + storeRef.getIdentifier()))
				return nodeRef;
		}
		return null;
	}

	@Override
	public Set<NodeRef> getAllRootNodes(StoreRef storeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildAssociationRef createNode(NodeRef parentRef, QName assocTypeQName, QName assocQName,
			QName nodeTypeQName) throws InvalidNodeRefException, InvalidTypeException {
		return createNode(parentRef, assocTypeQName, assocQName, nodeTypeQName, null);
	}

	@Override
	public ChildAssociationRef createNode(NodeRef parentRef, QName assocTypeQName, QName assocQName,
			QName nodeTypeQName, Map<QName, Serializable> properties)
			throws InvalidNodeRefException, InvalidTypeException {
		String nodeUUID = assocQName.getLocalName();
		Path path = getPath(parentRef);
		String pathStr = "";
		if (path != null)
			pathStr = getPathAsString(parentRef) + "/" + nodeUUID;
		else if (path == null && assocQName.getLocalName().equals(StoreRef.PROTOCOL_ARCHIVE))
			pathStr = MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_ARCHIVE;
		else if (path == null && assocQName.getLocalName().equals(StoreRef.PROTOCOL_WORKSPACE))
			pathStr = MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_WORKSPACE;
		else if (parentRef.getStoreRef().getProtocol().equals(StoreRef.PROTOCOL_ARCHIVE)
				&& assocQName.getLocalName().equals(StoreRef.STORE_REF_ARCHIVE_SPACESSTORE.getIdentifier()))
			pathStr = MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_ARCHIVE + "/" + assocQName.getLocalName();
		else if (parentRef.getStoreRef().getProtocol().equals(StoreRef.PROTOCOL_WORKSPACE)
				&& assocQName.getLocalName().equals(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE.getIdentifier()))
			pathStr = MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_WORKSPACE + "/" + assocQName.getLocalName();
		else if (parentRef.getStoreRef().getProtocol().equals(StoreRef.PROTOCOL_WORKSPACE)
				&& assocQName.getLocalName().equals(Version2Model.STORE_ID))
			pathStr = MockContentService.FOLDER_TEST + StoreRef.PROTOCOL_WORKSPACE + "/" + assocQName.getLocalName();
		else if (path == null)
			pathStr = MockContentService.FOLDER_TEST;
		StoreRef storeRef = StoreRef.STORE_REF_WORKSPACE_SPACESSTORE;
		if (pathStr.contains(StoreRef.PROTOCOL_ARCHIVE))
			storeRef = StoreRef.STORE_REF_ARCHIVE_SPACESSTORE;
		if (pathStr.contains(StoreRef.PROTOCOL_WORKSPACE + "/" + Version2Model.STORE_ID))
			storeRef = new StoreRef(StoreRef.PROTOCOL_WORKSPACE, Version2Model.STORE_ID);
		NodeRef nodeRef = new NodeRef(storeRef, NodeUtils.generateUUID(pathStr));
		if (properties == null) {
			setProperty(nodeRef, ContentModel.PROP_NAME, assocQName.getLocalName());
			setProperty(nodeRef, ContentModel.TYPE_BASE, nodeTypeQName);
			setProperty(nodeRef, ContentModel.PROP_CREATED, new Date());
		} else
			setProperties(nodeRef, new HashMap<>(properties));
		if (getProperty(nodeRef, PRIMARY_PARENT) == null)
			setProperty(nodeRef, PRIMARY_PARENT, parentRef);
		File file = new File(pathStr);
		setProperty(nodeRef, ContentModel.TYPE_BASE, nodeTypeQName);
		setProperty(nodeRef, ContentModel.PROP_NODE_UUID, nodeRef.getId());
		setProperty(nodeRef, ContentModel.PROP_NODE_DBID, countDbids++);
		file.mkdir();
		nodeRefs.put(nodeRef, new File(pathStr));
		srcAssociations.put(nodeRef, new HashMap<QName, Set<NodeRef>>());
		trgAssociations.put(nodeRef, new HashMap<QName, Set<NodeRef>>());
		return new ChildAssociationRef(assocTypeQName, parentRef, assocQName, nodeRef);
	}

	@Override
	public ChildAssociationRef moveNode(NodeRef nodeToMoveRef, NodeRef newParentRef, QName assocTypeQName,
			QName assocQName) throws InvalidNodeRefException {
		deleteNode(nodeToMoveRef);
		return createNode(newParentRef, ContentModel.ASSOC_CONTAINS, assocQName, assocTypeQName);
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
		Map<QName, Map<QName, Serializable>> aspects = sampleAspects.get(nodeRef);
		if (aspects == null) {
			aspects = new HashMap<QName, Map<QName, Serializable>>();
			sampleAspects.put(nodeRef, aspects);
		}
		aspects.put(aspectTypeQName, aspectProperties);
		Map<QName, Serializable> properties = getNotNullProperties(nodeRef);
		properties.putAll(aspectProperties);

	}

	@Override
	public void removeAspect(NodeRef nodeRef, QName aspectTypeQName)
			throws InvalidNodeRefException, InvalidAspectException {
		Map<QName, Map<QName, Serializable>> aspects = sampleAspects.get(nodeRef);
		Map<QName, Serializable> properties = sampleProperties.get(nodeRef);
		Map<QName, Serializable> aspectProperties = aspects.get(aspectTypeQName);
		for (QName qname : aspectProperties.keySet())
			properties.remove(qname);
		aspects.remove(aspectTypeQName);
	}

	@Override
	public boolean hasAspect(NodeRef nodeRef, QName aspectTypeQName)
			throws InvalidNodeRefException, InvalidAspectException {
		return getAspects(nodeRef).contains(aspectTypeQName);
	}

	@Override
	public Set<QName> getAspects(NodeRef nodeRef) throws InvalidNodeRefException {
		Map<QName, Map<QName, Serializable>> aspects = sampleAspects.get(nodeRef);
		if (aspects == null) {
			aspects = new HashMap<QName, Map<QName, Serializable>>();
			sampleAspects.put(nodeRef, aspects);
		}
		return new HashSet<QName>(aspects.keySet());
	}

	@Override
	public void deleteNode(NodeRef nodeRef) throws InvalidNodeRefException {
		try {
			FileUtils.deleteDirectory(nodeRefs.get(nodeRef));
			nodeRefs.remove(nodeRef);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ChildAssociationRef addChild(NodeRef parentRef, NodeRef childRef, QName assocTypeQName, QName qname)
			throws InvalidNodeRefException {
		ChildAssociationRef association = createNode(parentRef, ContentModel.ASSOC_CONTAINS, qname,
				ContentModel.TYPE_CONTENT, getProperties(childRef));
		try {
			FileUtils.copyDirectory(nodeRefs.get(childRef), nodeRefs.get(association.getChildRef()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return association;
	}

	@Override
	public List<ChildAssociationRef> addChild(Collection<NodeRef> parentRefs, NodeRef childRef, QName assocTypeQName,
			QName qname) throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeChild(NodeRef parentRef, NodeRef childRef) throws InvalidNodeRefException {
		NodeRef parent = getPrimaryParent(childRef).getParentRef();
		if (parent.equals(parentRef))
			deleteNode(childRef);
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
		if (value instanceof QName) {
			QName qValue = (QName) value;
			Collection<String> prefixes = namespaceService.getPrefixes(qValue.getNamespaceURI());
			if (!qValue.toPrefixString().contains(":") && !prefixes.isEmpty())
				value = QName.createQName(prefixes.toArray(new String[0])[0], qValue.getLocalName(), namespaceService);
		}
		getNotNullProperties(nodeRef).put(qname, value);
		if (qname.equals(ContentModel.PROP_NAME)) {
			File file = nodeRefs.get(nodeRef);
			if (file != null && !file.getName().equals(value)) {
				File fileContent = new File(file.getAbsolutePath() + "/" + file.getName());
				File renamedFile = new File(file.getParent() + "/" + value);
				File renamedFileContent = new File(fileContent.getParent() + "/" + value);
				if (file.exists()) {
					if (fileContent.exists())
						fileContent.renameTo(renamedFileContent);
					file.renameTo(renamedFile);
				}
				nodeRefs.put(nodeRef, renamedFile);
			}
		}
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
			String parentPath = path.subPath(path.size() - 2).toString();
			if (getPathAsString(nodeRef).equals(parentPath))
				result.add(new ChildAssociationRef(ContentModel.ASSOC_CONTAINS, nodeRef, getType(node), node));
		}
		return result;
	}

	@Override
	public List<ChildAssociationRef> getChildAssocs(NodeRef nodeRef, QNamePattern typeQNamePattern,
			QNamePattern qnamePattern) throws InvalidNodeRefException {
		return getChildAssocs(nodeRef);
	}

	@Override
	public List<ChildAssociationRef> getChildAssocs(NodeRef nodeRef, QNamePattern typeQNamePattern,
			QNamePattern qnamePattern, int maxResults, boolean preload) throws InvalidNodeRefException {
		return getChildAssocs(nodeRef, typeQNamePattern, qnamePattern, preload);
	}

	@Override
	public List<ChildAssociationRef> getChildAssocs(NodeRef nodeRef, QNamePattern typeQNamePattern,
			QNamePattern qnamePattern, boolean preload) throws InvalidNodeRefException {
		return getChildAssocs(nodeRef, typeQNamePattern, qnamePattern);
	}

	@Override
	public List<ChildAssociationRef> getChildAssocs(NodeRef nodeRef, Set<QName> childNodeTypeQNames) {
		List<ChildAssociationRef> result = new ArrayList<ChildAssociationRef>();
		List<ChildAssociationRef> children = getChildAssocs(nodeRef);
		for (ChildAssociationRef child : children)
			for (QName qname : childNodeTypeQNames)
				if (getType(child.getChildRef()).equals(qname))
					result.add(child);
		return result;
	}

	@Override
	public List<ChildAssociationRef> getChildAssocsByPropertyValue(NodeRef nodeRef, QName propertyQName,
			Serializable value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeRef getChildByName(NodeRef nodeRef, QName assocTypeQName, String childName) {
		List<ChildAssociationRef> children = getChildAssocs(nodeRef);
		for (ChildAssociationRef ref : children) {
			String name = (String) getProperty(ref.getChildRef(), ContentModel.PROP_NAME);
			if (name.equals(childName))
				return ref.getChildRef();
		}
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
		NodeRef result = (NodeRef) getProperty(nodeRef, PRIMARY_PARENT);
		QName childQName = QName.createQName((String) getProperty(nodeRef, ContentModel.PROP_NAME));
		ChildAssociationRef childAssociationRef = new ChildAssociationRef(ContentModel.ASSOC_CONTAINS, result,
				childQName, nodeRef, true, -1);
		return childAssociationRef;
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
		Map<QName, Set<NodeRef>> srcQnameAssocs = srcAssociations.get(sourceRef);
		Map<QName, Set<NodeRef>> trgQnameAssocs = trgAssociations.get(targetRef);
		createAllAssociation(srcQnameAssocs, targetRef, assocTypeQName);
		createAllAssociation(trgQnameAssocs, sourceRef, assocTypeQName);
		return new AssociationRef(sourceRef, assocTypeQName, targetRef);
	}

	private void createAllAssociation(Map<QName, Set<NodeRef>> qnameAssocs, NodeRef nodeRef, QName assocTypeQName)
			throws InvalidNodeRefException, AssociationExistsException {
		Set<NodeRef> nodeRefs = qnameAssocs.get(assocTypeQName);
		if (nodeRefs == null)
			nodeRefs = new HashSet<NodeRef>();
		if (nodeRefs.contains(nodeRef))
			throw new AssociationExistsException(0l, 0l, assocTypeQName);
		nodeRefs.add(nodeRef);
		qnameAssocs.put(assocTypeQName, nodeRefs);
	}

	@Override
	public void removeAssociation(NodeRef sourceRef, NodeRef targetRef, QName assocTypeQName)
			throws InvalidNodeRefException {
		Map<QName, Set<NodeRef>> qnameSrcAssocs = srcAssociations.get(sourceRef);
		Map<QName, Set<NodeRef>> qnameTrgAssocs = trgAssociations.get(targetRef);
		removeAllAssociation(qnameSrcAssocs, targetRef, assocTypeQName);
		removeAllAssociation(qnameTrgAssocs, sourceRef, assocTypeQName);

	}

	private void removeAllAssociation(Map<QName, Set<NodeRef>> qnameAssocs, NodeRef nodeRef, QName assocTypeQName)
			throws InvalidNodeRefException {
		Set<NodeRef> nodeRefs = qnameAssocs.get(assocTypeQName);
		if (nodeRefs != null)
			nodeRefs.remove(nodeRef);
	}

	@Override
	public void setAssociations(NodeRef sourceRef, QName assocTypeQName, List<NodeRef> targetRefs) {
		Map<QName, Set<NodeRef>> qnameAssocs = srcAssociations.get(sourceRef);
		Set<NodeRef> nodeRefs = qnameAssocs.get(assocTypeQName);
		if (nodeRefs == null)
			nodeRefs = new HashSet<NodeRef>();
		for (NodeRef node : targetRefs)
			if (nodeRefs.contains(node))
				throw new AssociationExistsException(0l, 0l, assocTypeQName);
		nodeRefs.addAll(targetRefs);
		qnameAssocs.put(assocTypeQName, nodeRefs);

		for (NodeRef targetRef : targetRefs) {
			qnameAssocs = trgAssociations.get(targetRef);
			nodeRefs = qnameAssocs.get(assocTypeQName);
			if (nodeRefs == null)
				nodeRefs = new HashSet<NodeRef>();
			if (nodeRefs.contains(sourceRef))
				throw new AssociationExistsException(0l, 0l, assocTypeQName);
			nodeRefs.add(sourceRef);
			qnameAssocs.put(assocTypeQName, nodeRefs);
		}

	}

	@Override
	public AssociationRef getAssoc(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssociationRef> getTargetAssocs(NodeRef sourceRef, QNamePattern qnamePattern)
			throws InvalidNodeRefException {
		List<AssociationRef> assRefs = new ArrayList<AssociationRef>();
		Map<QName, Set<NodeRef>> qnames = srcAssociations.get(sourceRef);
		for (QName qname : qnames.keySet())
			if (qnamePattern.isMatch(qname)) {
				Set<NodeRef> targets = qnames.get(qname);
				for (NodeRef nodeRef : targets)
					assRefs.add(new AssociationRef(sourceRef, qname, nodeRef));
			}
		return assRefs;
	}

	@Override
	public List<AssociationRef> getSourceAssocs(NodeRef targetRef, QNamePattern qnamePattern)
			throws InvalidNodeRefException {
		List<AssociationRef> assRefs = new ArrayList<AssociationRef>();
		Map<QName, Set<NodeRef>> qnames = trgAssociations.get(targetRef);
		for (QName qname : qnames.keySet())
			if (qnamePattern.isMatch(qname)) {
				Set<NodeRef> sources = qnames.get(qname);
				for (NodeRef nodeRef : sources)
					assRefs.add(new AssociationRef(nodeRef, qname, targetRef));
			}
		return assRefs;
	}

	@Override
	public Path getPath(NodeRef nodeRef) throws InvalidNodeRefException {
		File file = nodeRefs.get(nodeRef);
		if (file != null && file.exists()) {
			Path path = new Path();
			String[] paths = file.getPath().replace("\\", "/").split("/");
			for (String folder : paths)
				path.append(new MockElement(folder));
			return path;
		} else
			return null;
	}

	public String getPathAsString(NodeRef nodeRef) throws InvalidNodeRefException {
		Path path = getPath(nodeRef);
		if (path != null) {
			return path.toString().replace("\\", "/");
		}
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

	public void init() {
		sampleAspects.clear();
		sampleProperties.clear();
		samplePermissions.clear();
		nodeRefs.clear();
	}

	public String getPermissions() {
		return samplePermissions + "";
	}

	public Set<AccessPermission> getPermissions(NodeRef nodeRef) {
		return samplePermissions.get(nodeRef);
	}

	public void setPermission(NodeRef nodeRef, AccessPermission accessPermission) {
		Set<AccessPermission> permissions = samplePermissions.get(nodeRef);
		if (permissions == null) {
			permissions = new HashSet<AccessPermission>();
			samplePermissions.put(nodeRef, permissions);
		}
		permissions.add(accessPermission);
	}

	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

}
