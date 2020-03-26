package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
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
import org.alfresco.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;

public class MockSearchService implements SearchService, Serializable {

	@Autowired
	private NodeService nodeService;

	@Override
	public ResultSet query(StoreRef store, String language, String query) {
		MockNodeService nodeService = getNodeService();
		List<ResultSetRow> rows = new ArrayList<ResultSetRow>();
		Collection<NodeRef> nodeRefs = nodeService.getNodeRefs().keySet();
		for (NodeRef nodeRef : nodeRefs) {
			String name = (String) nodeService.getProperty(nodeRef, ContentModel.PROP_NAME);
			String[] paths = query.split("/");
			if ((name != null && query.replaceAll("\"", "").endsWith(name)) || query.endsWith("\"*\"")
					|| (paths.length >= 3 && nodeRef.toString().contains(paths[paths.length - 3] + "/")))
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
			return nodeRef.getId();
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

}
