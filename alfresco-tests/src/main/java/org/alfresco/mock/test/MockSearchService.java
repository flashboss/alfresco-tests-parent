package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.alfresco.mock.NodeUtils;
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
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.ISO9075;
import org.alfresco.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mock implementation of the {@link SearchService} interface for testing purposes.
 * This class provides search functionality over a mock Alfresco repository,
 * supporting XPATH, FTS (Full Text Search), and Lucene query languages.
 *
 * @author lucastancapiano
 */
public class MockSearchService implements SearchService, Serializable {

	/**
	 * The node service for accessing node data.
	 */
	@Autowired
	private NodeService nodeService;

	/**
	 * The namespace service for resolving namespace prefixes.
	 */
	@Autowired
	private NamespaceService namespaceService;

	/**
	 * {@inheritDoc}
	 * Executes a search query against the mock repository.
	 *
	 * @param store The store reference to search in.
	 * @param language The query language (XPATH, FTS_ALFRESCO, or LUCENE).
	 * @param query The query string.
	 * @return A {@link ResultSet} containing the matching nodes.
	 */
	@Override
	public ResultSet query(StoreRef store, String language, String query) {
		MockNodeService nodeService = getNodeService();
		List<ResultSetRow> rows = new ArrayList<ResultSetRow>();
		List<NodeRef> nodeRefs = NodeUtils.sortByName(nodeService.getNodeRefs().keySet());
		if (language.equals(SearchService.LANGUAGE_XPATH))
			XPATHQuery(store, query, nodeRefs, rows);
		else if (language.equals(SearchService.LANGUAGE_FTS_ALFRESCO) || language.equals(SearchService.LANGUAGE_LUCENE))
			FTSQuery(store, query, nodeRefs, rows);
		return new MockResultSet(rows);
	}

	/**
	 * {@inheritDoc}
	 * Executes a search query with parameter definitions.
	 *
	 * @param store The store reference to search in.
	 * @param language The query language.
	 * @param query The query string.
	 * @param queryParameterDefinitions The query parameter definitions.
	 * @return A {@link ResultSet} containing the matching nodes.
	 */
	@Override
	public ResultSet query(StoreRef store, String language, String query,
			QueryParameterDefinition[] queryParameterDefinitions) {
		return query(store, language, query);
	}

	/**
	 * {@inheritDoc}
	 * Executes a stored query by ID with parameters.
	 *
	 * @param store The store reference to search in.
	 * @param queryId The stored query identifier.
	 * @param queryParameters The query parameters.
	 * @return {@code null} as this is a mock implementation.
	 */
	@Override
	public ResultSet query(StoreRef store, QName queryId, QueryParameter[] queryParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Executes a search using search parameters object.
	 *
	 * @param searchParameters The search parameters including store, language, query, and pagination.
	 * @return A {@link ResultSet} containing the matching nodes, limited by maxItems if specified.
	 */
	@Override
	public ResultSet query(SearchParameters searchParameters) {
		ResultSet resultSet = query(searchParameters.getStores().get(0), searchParameters.getLanguage(),
				searchParameters.getQuery());
		int maxItems = searchParameters.getMaxItems();
		if (maxItems > -1 && resultSet.length() > maxItems) {
			List<ResultSetRow> rows = new ArrayList<ResultSetRow>();
			for (int i = 0; i < maxItems; i++)
				rows.add(new MockResultSetRow(resultSet.getNodeRef(i)));
			resultSet = new MockResultSet(rows);
		}
		return resultSet;
	}

	/**
	 * {@inheritDoc}
	 * Selects nodes matching an XPath expression.
	 *
	 * @param contextNodeRef The context node reference.
	 * @param xpath The XPath expression.
	 * @param parameters The query parameters.
	 * @param namespacePrefixResolver The namespace prefix resolver.
	 * @param followAllParentLinks Whether to follow all parent links.
	 * @return A list of matching node references.
	 * @throws InvalidNodeRefException If the context node reference is invalid.
	 * @throws XPathException If the XPath expression is invalid.
	 */
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

	/**
	 * {@inheritDoc}
	 * Selects nodes matching an expression in a specified language.
	 *
	 * @param contextNodeRef The context node reference.
	 * @param xpath The expression.
	 * @param parameters The query parameters.
	 * @param namespacePrefixResolver The namespace prefix resolver.
	 * @param followAllParentLinks Whether to follow all parent links.
	 * @param language The query language.
	 * @return {@code null} as this is a mock implementation.
	 * @throws InvalidNodeRefException If the context node reference is invalid.
	 * @throws XPathException If the expression is invalid.
	 */
	@Override
	public List<NodeRef> selectNodes(NodeRef contextNodeRef, String xpath, QueryParameterDefinition[] parameters,
			NamespacePrefixResolver namespacePrefixResolver, boolean followAllParentLinks, String language)
			throws InvalidNodeRefException, XPathException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Selects properties matching an XPath expression.
	 *
	 * @param contextNodeRef The context node reference.
	 * @param xpath The XPath expression.
	 * @param parameters The query parameters.
	 * @param namespacePrefixResolver The namespace prefix resolver.
	 * @param followAllParentLinks Whether to follow all parent links.
	 * @return {@code null} as this is a mock implementation.
	 * @throws InvalidNodeRefException If the context node reference is invalid.
	 * @throws XPathException If the XPath expression is invalid.
	 */
	@Override
	public List<Serializable> selectProperties(NodeRef contextNodeRef, String xpath,
			QueryParameterDefinition[] parameters, NamespacePrefixResolver namespacePrefixResolver,
			boolean followAllParentLinks) throws InvalidNodeRefException, XPathException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Selects properties matching an expression in a specified language.
	 *
	 * @param contextNodeRef The context node reference.
	 * @param xpath The expression.
	 * @param parameters The query parameters.
	 * @param namespacePrefixResolver The namespace prefix resolver.
	 * @param followAllParentLinks Whether to follow all parent links.
	 * @param language The query language.
	 * @return {@code null} as this is a mock implementation.
	 * @throws InvalidNodeRefException If the context node reference is invalid.
	 * @throws XPathException If the expression is invalid.
	 */
	@Override
	public List<Serializable> selectProperties(NodeRef contextNodeRef, String xpath,
			QueryParameterDefinition[] parameters, NamespacePrefixResolver namespacePrefixResolver,
			boolean followAllParentLinks, String language) throws InvalidNodeRefException, XPathException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 * Checks if a node property contains a Google-like pattern.
	 *
	 * @param nodeRef The node reference.
	 * @param propertyQName The property QName.
	 * @param googleLikePattern The Google-like search pattern.
	 * @return {@code false} as this is a mock implementation.
	 * @throws InvalidNodeRefException If the node reference is invalid.
	 */
	@Override
	public boolean contains(NodeRef nodeRef, QName propertyQName, String googleLikePattern)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Checks if a node property contains a Google-like pattern with a default operator.
	 *
	 * @param nodeRef The node reference.
	 * @param propertyQName The property QName.
	 * @param googleLikePattern The Google-like search pattern.
	 * @param defaultOperator The default operator for the search.
	 * @return {@code false} as this is a mock implementation.
	 * @throws InvalidNodeRefException If the node reference is invalid.
	 */
	@Override
	public boolean contains(NodeRef nodeRef, QName propertyQName, String googleLikePattern, Operator defaultOperator)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 * Checks if a node property matches a SQL-like pattern.
	 *
	 * @param nodeRef The node reference.
	 * @param propertyQName The property QName.
	 * @param sqlLikePattern The SQL-like pattern.
	 * @param includeFTS Whether to include full text search.
	 * @return {@code false} as this is a mock implementation.
	 * @throws InvalidNodeRefException If the node reference is invalid.
	 */
	@Override
	public boolean like(NodeRef nodeRef, QName propertyQName, String sqlLikePattern, boolean includeFTS)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Gets the mock node service.
	 *
	 * @return The {@link MockNodeService} instance.
	 */
	public MockNodeService getNodeService() {
		return (MockNodeService) nodeService;
	}

	/**
	 * Sets the mock node service.
	 *
	 * @param nodeService The {@link MockNodeService} instance to set.
	 */
	public void setNodeService(MockNodeService nodeService) {
		this.nodeService = nodeService;
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
	 * Mock implementation of the {@link ResultSet} interface for testing purposes.
	 * Wraps a list of result set rows returned by search queries.
	 */
	public class MockResultSet implements ResultSet {

		/**
		 * The list of result set rows.
		 */
		private List<ResultSetRow> rows;

		/**
		 * Constructs a new MockResultSet with the specified rows.
		 *
		 * @param rows The list of result set rows.
		 */
		public MockResultSet(List<ResultSetRow> rows) {
			this.rows = rows;
		}

		/**
		 * {@inheritDoc}
		 * @return The number of results.
		 */
		@Override
		public int length() {
			return rows.size();
		}

		/**
		 * {@inheritDoc}
		 * @param n The index.
		 * @return The node reference at the specified index, or {@code null} if out of bounds.
		 */
		@Override
		public NodeRef getNodeRef(int n) {
			if (rows.size() <= n)
				return null;
			return rows.get(n).getNodeRef();
		}

		/**
		 * {@inheritDoc}
		 * @param n The index.
		 * @return Always returns 0 as scores are not implemented.
		 */
		@Override
		public float getScore(int n) {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * {@inheritDoc}
		 * Closes the result set. This is a no-op in the mock implementation.
		 */
		@Override
		public void close() {
			// TODO Auto-generated method stub

		}

		/**
		 * {@inheritDoc}
		 * @param i The index.
		 * @return {@code null} as this is a mock implementation.
		 */
		@Override
		public ResultSetRow getRow(int i) {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * {@inheritDoc}
		 * @return A list of all node references in the result set.
		 */
		@Override
		public List<NodeRef> getNodeRefs() {
			List<NodeRef> result = new ArrayList<NodeRef>();
			for (ResultSetRow row : rows)
				result.add(row.getNodeRef());
			return result;
		}

		/**
		 * {@inheritDoc}
		 * @return {@code null} as this is a mock implementation.
		 */
		@Override
		public List<ChildAssociationRef> getChildAssocRefs() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * {@inheritDoc}
		 * @param n The index.
		 * @return {@code null} as this is a mock implementation.
		 */
		@Override
		public ChildAssociationRef getChildAssocRef(int n) {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * {@inheritDoc}
		 * @return {@code null} as this is a mock implementation.
		 */
		@Override
		public ResultSetMetaData getResultSetMetaData() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * {@inheritDoc}
		 * @return Always returns 0.
		 */
		@Override
		public int getStart() {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * {@inheritDoc}
		 * @return Always returns {@code false}.
		 */
		@Override
		public boolean hasMore() {
			// TODO Auto-generated method stub
			return false;
		}

		/**
		 * {@inheritDoc}
		 * @param bulkFetch Whether to enable bulk fetch.
		 * @return Always returns {@code false}.
		 */
		@Override
		public boolean setBulkFetch(boolean bulkFetch) {
			// TODO Auto-generated method stub
			return false;
		}

		/**
		 * {@inheritDoc}
		 * @return Always returns {@code false}.
		 */
		@Override
		public boolean getBulkFetch() {
			// TODO Auto-generated method stub
			return false;
		}

		/**
		 * {@inheritDoc}
		 * @param bulkFetchSize The bulk fetch size to set.
		 * @return Always returns 0.
		 */
		@Override
		public int setBulkFetchSize(int bulkFetchSize) {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * {@inheritDoc}
		 * @return Always returns 0.
		 */
		@Override
		public int getBulkFetchSize() {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * {@inheritDoc}
		 * @param field The facet field.
		 * @return {@code null} as this is a mock implementation.
		 */
		@Override
		public List<Pair<String, Integer>> getFieldFacet(String field) {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * {@inheritDoc}
		 * @return An iterator over the result set rows.
		 */
		@Override
		public Iterator<ResultSetRow> iterator() {
			// TODO Auto-generated method stub
			return rows.iterator();
		}
	}

	/**
	 * Mock implementation of the {@link ResultSetRow} interface for testing purposes.
	 * Represents a single row in a search result set.
	 */
	public class MockResultSetRow implements ResultSetRow {

		/**
		 * The node reference for this row.
		 */
		private NodeRef nodeRef;

		/**
		 * Constructs a new MockResultSetRow for the specified node.
		 *
		 * @param nodeRef The node reference.
		 */
		public MockResultSetRow(NodeRef nodeRef) {
			this.nodeRef = nodeRef;
		}

		/**
		 * {@inheritDoc}
		 * @return A map containing the node reference.
		 */
		@Override
		public Map<String, Serializable> getValues() {
			Map<String, Serializable> map = new HashMap<String, Serializable>();
			map.put("", nodeRef);
			return map;
		}

		/**
		 * {@inheritDoc}
		 * @param columnName The column name.
		 * @return The node reference.
		 */
		@Override
		public Serializable getValue(String columnName) {
			return nodeRef;
		}

		/**
		 * {@inheritDoc}
		 * @param qname The property QName.
		 * @return The property value for the node.
		 */
		@Override
		public Serializable getValue(QName qname) {
			return nodeService.getProperty(nodeRef, qname);
		}

		/**
		 * {@inheritDoc}
		 * @return The node reference.
		 */
		@Override
		public NodeRef getNodeRef() {
			return nodeRef;
		}

		/**
		 * {@inheritDoc}
		 * @return {@code null} as this is a mock implementation.
		 */
		@Override
		public Map<String, NodeRef> getNodeRefs() {
			return null;
		}

		/**
		 * {@inheritDoc}
		 * @param selectorName The selector name.
		 * @return The node reference.
		 */
		@Override
		public NodeRef getNodeRef(String selectorName) {
			return nodeRef;
		}

		/**
		 * {@inheritDoc}
		 * @return Always returns 0.
		 */
		@Override
		public float getScore() {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * {@inheritDoc}
		 * @return {@code null} as this is a mock implementation.
		 */
		@Override
		public Map<String, Float> getScores() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * {@inheritDoc}
		 * @param selectorName The selector name.
		 * @return Always returns 0.
		 */
		@Override
		public float getScore(String selectorName) {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * {@inheritDoc}
		 * @return {@code null} as this is a mock implementation.
		 */
		@Override
		public ResultSet getResultSet() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * {@inheritDoc}
		 * @return {@code null} as this is a mock implementation.
		 */
		@Override
		public QName getQName() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * {@inheritDoc}
		 * @return Always returns 0.
		 */
		@Override
		public int getIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * {@inheritDoc}
		 * @return {@code null} as this is a mock implementation.
		 */
		@Override
		public ChildAssociationRef getChildAssocRef() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	/**
	 * Extracts property filters from a query string.
	 *
	 * @param query The query string to parse.
	 * @return A list of mock property filters.
	 */
	private List<MockProperty> getPropertiesFromQuery(String query) {
		List<MockProperty> properties = new ArrayList<MockProperty>();
		String[] segments = query.split(" (?i)AND | (?i)OR ");
		for (String segm : segments) {
			String seg = segm.trim();
			if (seg.startsWith("(") && seg.endsWith(")")) {
				seg = seg.replaceFirst("\\(", "");
				seg = seg.substring(0, seg.length() - 1);
			}
			if (!seg.startsWith("PATH:") && !seg.substring(1, seg.length()).startsWith("PATH:")
					&& !seg.startsWith("TYPE:") && !seg.contains("[") && !seg.startsWith("ASPECT:")
					&& !seg.startsWith("(ASPECT:") && !seg.startsWith("-ASPECT:") && !seg.startsWith("(-ASPECT:")
					&& !seg.toLowerCase().startsWith("not aspect:") && !seg.toLowerCase().startsWith("(not aspect:")) {
				String[] splitted = seg.split(":");
				String uri = namespaceService.getNamespaceURI(splitted[0].replaceAll("(?i)not @", "")
						.replaceAll("@", "").replaceAll("=", "").replaceAll("\\+", "").replaceAll("\\\\", ""));
				QName key = QName.createQName(uri, splitted[1]);
				properties.add(new MockProperty(key, splitted[2].replaceAll("\"", "").replaceAll("\\\\", ""),
						seg.startsWith("-") || seg.startsWith("(-") || seg.toLowerCase().startsWith("not")
								|| seg.toLowerCase().startsWith("(not") ? true : false));
			}
		}
		return properties;
	}

	/**
	 * Extracts aspect filters from a query string.
	 *
	 * @param query The query string to parse.
	 * @return A list of mock aspect filters.
	 */
	private List<MockAspect> getAspectsFromQuery(String query) {
		List<MockAspect> aspects = new ArrayList<MockAspect>();
		String[] segments = query.split(" (?i)AND | (?i)OR ");
		for (String segm : segments) {
			String seg = segm.trim();
			if (seg.startsWith("(") && seg.endsWith(")")) {
				seg = seg.replaceFirst("\\(", "");
				seg = seg.substring(0, seg.length() - 1);
			}
			if (seg.startsWith("ASPECT:") || seg.startsWith("(ASPECT:") || seg.startsWith("-ASPECT:")
					|| seg.startsWith("(-ASPECT:") || seg.toLowerCase().startsWith("not aspect:")
					|| seg.toLowerCase().startsWith("(not aspect:")) {
				String[] splitted = seg.split(":");
				String uri = namespaceService.getNamespaceURI(splitted[1].replaceAll("@", "").replaceAll("=", "")
						.replaceAll("\\+", "").replaceAll("\\\\", "").replaceAll("\"", ""));
				QName key = QName.createQName(uri, splitted[2].replaceAll("\"", ""));
				aspects.add(new MockAspect(key,
						seg.startsWith("-ASPECT:") || seg.startsWith("(-ASPECT:")
								|| seg.toLowerCase().startsWith("not aspect:")
								|| seg.toLowerCase().startsWith("(not aspect:") ? true : false));
			}
		}
		return aspects;
	}

	/**
	 * Extracts a specific segment from a query string.
	 *
	 * @param query The query string.
	 * @param segment The segment prefix to look for.
	 * @return The extracted segment value, or {@code null} if not found.
	 */
	private String getSegmentFromQuery(String query, String segment) {
		if (query.contains(segment)) {
			query = query.substring(query.indexOf(segment) + segment.length());
			query = query.substring(0, query.indexOf("\""));
			return query;
		}
		return null;
	}

	/**
	 * Checks if a node matches the specified type.
	 *
	 * @param type The type to check (prefix:localname format).
	 * @param nodeRef The node reference.
	 * @return {@code true} if the node matches the type or type is null.
	 */
	private boolean hasType(String type, NodeRef nodeRef) {
		if (type == null)
			return true;
		else {
			QName typeNode = nodeService.getType(nodeRef);
			String typeNodeStr = typeNode.getPrefixString();
			return type.equals(typeNodeStr);
		}
	}

	/**
	 * Checks if a node has all the specified properties.
	 *
	 * @param properties The list of property filters.
	 * @param nodeRef The node reference.
	 * @return {@code true} if all property conditions are met.
	 */
	private boolean hasProperties(List<MockProperty> properties, NodeRef nodeRef) {
		List<Boolean> results = new ArrayList<Boolean>();
		if (properties.isEmpty())
			return true;
		for (MockProperty property : properties) {
			Object value = (Object) nodeService.getProperty(nodeRef, property.getQname());
			if (value instanceof String || value == null) {
				String pattern = property.getValue();
				String[] subpatterns = pattern.split("\\*");
				boolean result = false;
				if (subpatterns.length > 1)
					for (String subpattern : subpatterns)
						if (value != null && ((String) value).contains(subpattern))
							if (!property.isToDelete()) {
								results.add(true);
								result = true;
							}
				if (!result) {
					result = pattern.equals(value);
					if (!property.isToDelete())
						results.add(result);
					else
						results.add(!result);
				}
			} else if (!property.isToDelete())
				results.add(true);
			else
				results.add(false);
		}
		boolean result = true;
		for (boolean rs : results)
			result = result && rs;
		return result;
	}

	/**
	 * Checks if a node has all the specified aspects.
	 *
	 * @param aspects The list of aspect filters.
	 * @param nodeRef The node reference.
	 * @return {@code true} if all aspect conditions are met.
	 */
	private boolean hasAspects(List<MockAspect> aspects, NodeRef nodeRef) {
		List<Boolean> results = new ArrayList<Boolean>();
		if (aspects.isEmpty())
			return true;
		for (MockAspect aspect : aspects) {
			boolean result = nodeService.hasAspect(nodeRef, aspect.getQname());
			if (aspect.isToDelete())
				results.add(!result);
			else
				results.add(result);
		}
		boolean result = true;
		for (boolean rs : results)
			result = result && rs;
		return result;
	}

	/**
	 * Checks if a node's path matches the specified path pattern.
	 *
	 * @param store The store reference.
	 * @param path The path pattern.
	 * @param subpaths The subpath segments.
	 * @param wildcardsNumber The number of wildcards at the end of the path.
	 * @param nodeRef The node reference.
	 * @return {@code true} if the node path matches the pattern.
	 */
	private boolean hasPath(StoreRef store, String path, String[] subpaths, int wildcardsNumber, NodeRef nodeRef) {
		String nodepath = getNodeService().getPathAsString(nodeRef);
		if (store == null || path == null)
			return true;
		else if (store != null && !nodepath.contains(MockContentService.FOLDER_TEST + store.getProtocol()))
			return false;
		nodepath = nodepath
				.substring(nodepath.indexOf(MockContentService.FOLDER_TEST) + MockContentService.FOLDER_TEST.length());
		String lastNodepath = "";
		if (nodepath.indexOf("/") >= 0) {
			nodepath = nodepath.substring(nodepath.indexOf("/"));
			lastNodepath = nodepath.substring(nodepath.lastIndexOf("/"));
		} else
			nodepath = "";
		boolean result = true;
		for (int i = 0; i < subpaths.length; i++) {
			String subpath = subpaths[i];
			result = result && nodepath.contains(subpath.replaceAll("//", ""));
			if (result && i == subpaths.length - 1 && nodepath.indexOf("/") >= 0
					&& subpath.indexOf("/") >= 0)
				if (!subpath.endsWith("/")) {
					String nameSubpath = subpath.substring(subpath.lastIndexOf("/"));
					result = result && lastNodepath.equals(nameSubpath);
				} else if (path.endsWith("/*") && !path.endsWith("//*") && !path.equals("/*")
						&& !subpath.equals("/")) {
					String nameSubpath = nodepath;
					for (int j = 0; j < wildcardsNumber; j++)
						nameSubpath = nameSubpath.substring(0, nameSubpath.lastIndexOf("/"));
					result = result && (nameSubpath + "/").endsWith(subpath);
				} else {
					String firstLine = subpath.replaceAll("//", "");
					String nameSubpath = firstLine.substring(firstLine.lastIndexOf("/"));
					if (lastNodepath.equals(nameSubpath))
						result = false;
				}
		}
		return result;
	}

	/**
	 * Splits a path into subpaths based on wildcards.
	 *
	 * @param path The path to split.
	 * @return An array of subpath segments.
	 */
	private String[] getSubpaths(String path) {
		if (path != null) {
			String subpath = path;
			String[] splittedPath = subpath.split("(\\*)|(//)");
			if (path.endsWith("//*")) {
				path = path.substring(0, path.length() - 3);
				splittedPath[splittedPath.length - 1] = splittedPath[splittedPath.length - 1] + "//";
			} else if (!path.equals("/") && !path.equals("/*")) {
				String lastPath = splittedPath[splittedPath.length - 1];
				while (lastPath.equals("/")) {
					splittedPath = Arrays.copyOf(splittedPath, splittedPath.length - 1);
					lastPath = splittedPath[splittedPath.length - 1];
				}
			}
			return splittedPath;
		} else
			return null;
	}

	/**
	 * Executes an XPATH query against the mock repository.
	 *
	 * @param store The store reference.
	 * @param query The XPATH query.
	 * @param nodeRefs The list of all node references.
	 * @param rows The list to populate with matching rows.
	 */
	private void XPATHQuery(StoreRef store, String query, List<NodeRef> nodeRefs, List<ResultSetRow> rows) {
		query = prepare(query, store);
		String[] subpaths = getSubpaths(query);
		int wildcardsNumber = 0;
		String processQuery = query;
		while (processQuery.endsWith("/*")) {
			wildcardsNumber++;
			processQuery = processQuery.substring(0, processQuery.lastIndexOf("/*"));
		}
		for (NodeRef nodeRef : nodeRefs) {
			if (hasPath(store, query, subpaths, wildcardsNumber, nodeRef))
				rows.add(new MockResultSetRow(nodeRef));
		}
	}

	/**
	 * Executes an FTS (Full Text Search) query against the mock repository.
	 *
	 * @param store The store reference.
	 * @param query The FTS query.
	 * @param nodeRefs The list of all node references.
	 * @param rows The list to populate with matching rows.
	 */
	private void FTSQuery(StoreRef store, String query, List<NodeRef> nodeRefs, List<ResultSetRow> rows) {
		String path = getSegmentFromQuery(query, "PATH:\"");
		if (path != null) {
			path = prepare(path, store);
			if (path.endsWith("."))
				path = path.substring(0, path.lastIndexOf(".")) + "*";
			if (!path.startsWith("/"))
				path = "/" + path;
		}
		String processQuery = path;
		String[] subpaths = getSubpaths(path);
		String type = getSegmentFromQuery(query, "TYPE:\"");
		List<MockProperty> properties = getPropertiesFromQuery(query);
		List<MockAspect> aspects = getAspectsFromQuery(query);
		int wildcardsNumber = 0;
		if (processQuery != null)
			while (processQuery.endsWith("/*")) {
				wildcardsNumber++;
				processQuery = processQuery.substring(0, processQuery.lastIndexOf("/*"));
			}
		for (NodeRef nodeRef : nodeRefs) {
			if (hasType(type, nodeRef) && hasProperties(properties, nodeRef) && hasAspects(aspects, nodeRef)
					&& hasPath(store, path, subpaths, wildcardsNumber, nodeRef))
				rows.add(new MockResultSetRow(nodeRef));
		}
	}

	/**
	 * Prepares a query string by decoding and normalizing it.
	 *
	 * @param query The query string to prepare.
	 * @param store The store reference.
	 * @return The prepared query string.
	 */
	private String prepare(String query, StoreRef store) {
		query = ISO9075.decode(query).trim();
		String prefix = MockContentService.FOLDER_TEST;
		if (store != null)
			prefix = prefix + store.getProtocol();
		int index = query.indexOf(prefix);
		if (index >= 0)
			query = query.substring(index + prefix.length());
		String result = "";
		String[] slashes = query.split("/");
		for (int i = 0; i < slashes.length; i++) {
			String slashed = slashes[i];
			if (slashed.contains(":"))
				result += slashed.split(":")[1];
			else
				result += slashed;
			result += "/";
		}
		result = result.substring(0, result.length() - 1);
		return result;
	}

	/**
	 * Internal class representing a property filter for search queries.
	 */
	private class MockProperty {

		/**
		 * The property QName.
		 */
		private QName qname;

		/**
		 * The property value to match.
		 */
		private String value;

		/**
		 * Whether this is a negation filter.
		 */
		private boolean toDelete;

		/**
		 * Constructs a new MockProperty.
		 *
		 * @param qname The property QName.
		 * @param value The value to match.
		 * @param toDelete Whether this is a negation filter.
		 */
		public MockProperty(QName qname, String value, boolean toDelete) {
			this.qname = qname;
			this.value = value;
			this.toDelete = toDelete;
		}

		/**
		 * Gets the property QName.
		 *
		 * @return The QName.
		 */
		public QName getQname() {
			return qname;
		}

		/**
		 * Gets the property value.
		 *
		 * @return The value.
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Checks if this is a negation filter.
		 *
		 * @return {@code true} if negation, {@code false} otherwise.
		 */
		public boolean isToDelete() {
			return toDelete;
		}
	}

	/**
	 * Internal class representing an aspect filter for search queries.
	 */
	private class MockAspect {

		/**
		 * The aspect QName.
		 */
		private QName qname;

		/**
		 * Whether this is a negation filter.
		 */
		private boolean toDelete;

		/**
		 * Constructs a new MockAspect.
		 *
		 * @param qname The aspect QName.
		 * @param toDelete Whether this is a negation filter.
		 */
		public MockAspect(QName qname, boolean toDelete) {
			this.qname = qname;
			this.toDelete = toDelete;
		}

		/**
		 * Gets the aspect QName.
		 *
		 * @return The QName.
		 */
		public QName getQname() {
			return qname;
		}

		/**
		 * Checks if this is a negation filter.
		 *
		 * @return {@code true} if negation, {@code false} otherwise.
		 */
		public boolean isToDelete() {
			return toDelete;
		}
	}

}
