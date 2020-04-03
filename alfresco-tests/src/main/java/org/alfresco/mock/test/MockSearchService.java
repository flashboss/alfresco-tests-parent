package org.alfresco.mock.test;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.InvalidNodeRefException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.repository.XPathException;
import org.alfresco.service.cmr.search.QueryParameter;
import org.alfresco.service.cmr.search.QueryParameterDefinition;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.ResultSetMetaData;
import org.alfresco.service.cmr.search.ResultSetRow;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchParameters.Operator;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespacePrefixResolver;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.ISO9075;
import org.alfresco.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;

public class MockSearchService implements SearchService, Serializable {

	@Autowired
	private NodeService nodeService;

	@Override
	public ResultSet query(StoreRef store, String language, String query) {
		query = ISO9075.decode(query);
		MockNodeService nodeService = getNodeService();
		List<ResultSetRow> rows = new ArrayList<ResultSetRow>();
		Collection<NodeRef> nodeRefs = nodeService.getNodeRefs().keySet();
		String path = getSegmentFromQuery(query, "PATH:\"");
		if (!path.startsWith(File.separator))
			path = File.separator + path;
		String[] subpaths = getSubpaths(path);
		int wildcardsNumber = 1;
		String type = getSegmentFromQuery(query, "TYPE:\"");
		for (int j = subpaths.length - 1; j > 0; j--)
			if (subpaths[j].equals("/"))
				wildcardsNumber++;
		for (NodeRef nodeRef : nodeRefs) {
			if (hasType(type, nodeRef) && hasPath(path, subpaths, wildcardsNumber, nodeRef))
				rows.add(new MockResultSetRow(nodeRef));
		}
		return new MockResultSet(rows);
	}

	@Override
	public ResultSet query(StoreRef store, String language, String query,
			QueryParameterDefinition[] queryParameterDefinitions) {
		return query(store, language, query);
	}

	@Override
	public ResultSet query(StoreRef store, QName queryId, QueryParameter[] queryParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet query(SearchParameters searchParameters) {
		return query(searchParameters.getStores().get(0), searchParameters.getLanguage(), searchParameters.getQuery());
	}

	@Override
	public List<NodeRef> selectNodes(NodeRef contextNodeRef, String xpath, QueryParameterDefinition[] parameters,
			NamespacePrefixResolver namespacePrefixResolver, boolean followAllParentLinks)
			throws InvalidNodeRefException, XPathException {
		List<NodeRef> result = new ArrayList<NodeRef>();
		ResultSet resultSet = query(contextNodeRef.getStoreRef(), SearchService.LANGUAGE_XPATH, xpath, parameters);
		for (ResultSetRow row : resultSet)
			result.add(row.getNodeRef());
		return result;
	}

	@Override
	public List<NodeRef> selectNodes(NodeRef contextNodeRef, String xpath, QueryParameterDefinition[] parameters,
			NamespacePrefixResolver namespacePrefixResolver, boolean followAllParentLinks, String language)
			throws InvalidNodeRefException, XPathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Serializable> selectProperties(NodeRef contextNodeRef, String xpath,
			QueryParameterDefinition[] parameters, NamespacePrefixResolver namespacePrefixResolver,
			boolean followAllParentLinks) throws InvalidNodeRefException, XPathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Serializable> selectProperties(NodeRef contextNodeRef, String xpath,
			QueryParameterDefinition[] parameters, NamespacePrefixResolver namespacePrefixResolver,
			boolean followAllParentLinks, String language) throws InvalidNodeRefException, XPathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(NodeRef nodeRef, QName propertyQName, String googleLikePattern)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(NodeRef nodeRef, QName propertyQName, String googleLikePattern, Operator defaultOperator)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean like(NodeRef nodeRef, QName propertyQName, String sqlLikePattern, boolean includeFTS)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return false;
	}

	public MockNodeService getNodeService() {
		return (MockNodeService) nodeService;
	}

	public void setNodeService(MockNodeService nodeService) {
		this.nodeService = nodeService;
	}

	public class MockResultSet implements ResultSet {

		private List<ResultSetRow> rows;

		public MockResultSet(List<ResultSetRow> rows) {
			this.rows = rows;
		}

		@Override
		public int length() {
			return rows.size();
		}

		@Override
		public long getNumberFound() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public NodeRef getNodeRef(int n) {
			if (rows.size() <= n)
				return null;
			return rows.get(n).getNodeRef();
		}

		@Override
		public float getScore(int n) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void close() {
			// TODO Auto-generated method stub

		}

		@Override
		public ResultSetRow getRow(int i) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<NodeRef> getNodeRefs() {
			List<NodeRef> result = new ArrayList<NodeRef>();
			for (ResultSetRow row : rows)
				result.add(row.getNodeRef());
			return result;
		}

		@Override
		public List<ChildAssociationRef> getChildAssocRefs() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ChildAssociationRef getChildAssocRef(int n) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ResultSetMetaData getResultSetMetaData() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getStart() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean hasMore() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean setBulkFetch(boolean bulkFetch) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean getBulkFetch() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int setBulkFetchSize(int bulkFetchSize) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getBulkFetchSize() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public List<Pair<String, Integer>> getFieldFacet(String field) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Iterator<ResultSetRow> iterator() {
			// TODO Auto-generated method stub
			return rows.iterator();
		}
	}

	public class MockResultSetRow implements ResultSetRow {

		private NodeRef nodeRef;

		public MockResultSetRow(NodeRef nodeRef) {
			this.nodeRef = nodeRef;
		}

		@Override
		public Map<String, Serializable> getValues() {
			Map<String, Serializable> map = new HashMap<String, Serializable>();
			map.put("", nodeRef);
			return map;
		}

		@Override
		public Serializable getValue(String columnName) {
			return nodeRef;
		}

		@Override
		public Serializable getValue(QName qname) {
			return nodeService.getProperty(nodeRef, qname);
		}

		@Override
		public NodeRef getNodeRef() {
			return nodeRef;
		}

		@Override
		public Map<String, NodeRef> getNodeRefs() {
			return null;
		}

		@Override
		public NodeRef getNodeRef(String selectorName) {
			return nodeRef;
		}

		@Override
		public float getScore() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Map<String, Float> getScores() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public float getScore(String selectorName) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public ResultSet getResultSet() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public QName getQName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public ChildAssociationRef getChildAssocRef() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private String getSegmentFromQuery(String query, String segment) {
		if (query.contains(segment)) {
			query = query.substring(query.indexOf(segment) + 6);
			query = query.substring(0, query.indexOf("\""));
			return query;
		}
		return null;
	}

	private boolean hasType(String type, NodeRef nodeRef) {
		if (type == null)
			return true;
		else {
			QName typeNode = nodeService.getType(nodeRef);
			String typeNodeStr = typeNode.getPrefixString();
			return type.equals(typeNodeStr);
		}
	}

	private boolean hasPath(String path, String[] subpaths, int wildcardsNumber, NodeRef nodeRef) {
		String nodepath = nodeRef.toString();
		nodepath = nodepath
				.substring(nodepath.indexOf(MockContentService.FOLDER_TEST) + MockContentService.FOLDER_TEST.length());
		if (nodepath.indexOf(File.separator) >= 0)
			nodepath = nodepath.substring(nodepath.indexOf(File.separator));
		else
			nodepath = "";
		boolean result = true;
		for (int i = 0; i < subpaths.length; i++) {
			String subpath = subpaths[i];
			result = result && nodepath.contains(subpath.replaceAll("//", ""));
			if (result && i == subpaths.length - 1 && nodepath.indexOf(File.separator) >= 0
					&& subpath.indexOf("/") >= 0)
				if (!subpath.endsWith(File.separator)) {
					String nameNodePath = nodepath.substring(nodepath.lastIndexOf(File.separator));
					String nameSubpath = subpath.substring(subpath.lastIndexOf(File.separator));
					result = result && nameNodePath.equals(nameSubpath);
				} else if (path.endsWith("/*") && !path.endsWith("//*") && !path.equals("/*")) {
					String[] splittedNodePath = nodepath.split("/");
					String pathNode = splittedNodePath[splittedNodePath.length - wildcardsNumber];
					String nameSubpath = subpaths[subpaths.length - wildcardsNumber];
					String nameNodePath = nodepath.substring(0, nodepath.indexOf(pathNode));
					result = result && nameNodePath.equals(nameSubpath);
				}
		}
		return result;
	}

	private String[] getSubpaths(String path) {
		return path.split("(\\*)|(///)");
	}

}
