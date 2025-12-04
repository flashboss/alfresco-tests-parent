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
import org.alfresco.service.cmr.search.SpellCheckResult;
import org.alfresco.service.namespace.NamespacePrefixResolver;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.ISO9075;
import org.alfresco.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mock implementation of the Alfresco SearchService for testing purposes.
 * Provides stub implementations for testing without a running Alfresco server.
 * 
 * @author vige
 */
public class MockSearchService implements SearchService, Serializable {

	@Autowired
	private NodeService nodeService;

	@Autowired
	private NamespaceService namespaceService;

	@Override
 /**
 * Query.
 *
 * @param store the store
 * @param language the language
 * @param query the query
 * @return the result set
 */
	public ResultSet query(StoreRef store, String language, String query) {
  /**
  * Query.
  *
  * @param store the store
  * @param language the language
  * @param query the query
  * @return the result set
  */
		MockNodeService nodeService = getNodeService();
		List<ResultSetRow> rows = new ArrayList<ResultSetRow>();
		List<NodeRef> nodeRefs = NodeUtils.sortByName(nodeService.getNodeRefs().keySet());
		if (language.equals(SearchService.LANGUAGE_XPATH))
			XPATHQuery(store, query, nodeRefs, rows);
		else if (language.equals(SearchService.LANGUAGE_FTS_ALFRESCO) || language.equals(SearchService.LANGUAGE_LUCENE))
			FTSQuery(store, query, nodeRefs, rows);
		return new MockResultSet(rows);
	}

	@Override
	public ResultSet query(StoreRef store, String language, String query,
			QueryParameterDefinition[] queryParameterDefinitions) {
		return query(store, language, query);
	}

	@Override
 /**
 * Query.
 *
 * @param store the store
 * @param queryId the query id
 * @param queryParameters the query parameters
 * @return the result set
 */
	public ResultSet query(StoreRef store, QName queryId, QueryParameter[] queryParameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Query.
 *
 * @param searchParameters the search parameters
 * @return the result set
 */
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

	@Override
	public List<NodeRef> selectNodes(NodeRef contextNodeRef, String xpath, QueryParameterDefinition[] parameters,
			NamespacePrefixResolver namespacePrefixResolver, boolean followAllParentLinks)
			throws InvalidNodeRefException, XPathException {
  /**
  * Get node refs.
  *
  * @return the list
  */
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
 /**
 * Contains.
 *
 * @param nodeRef the node ref
 * @param propertyQName the property q name
 * @param googleLikePattern the google like pattern
 * @return the boolean
 */
	public boolean contains(NodeRef nodeRef, QName propertyQName, String googleLikePattern)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Contains.
 *
 * @param nodeRef the node ref
 * @param propertyQName the property q name
 * @param googleLikePattern the google like pattern
 * @return the boolean
 */
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

 /**
 * Get node service.
 *
 * @return the mock node service
 */
	public MockNodeService getNodeService() {
  /**
  * Get node service.
  *
  * @return the mock node service
  */
		return (MockNodeService) nodeService;
	}

 /**
 * Set node service.
 *
 * @param nodeService the node service
 */
	public void setNodeService(MockNodeService nodeService) {
		this.nodeService = nodeService;
	}

 /**
 * Set namespace service.
 *
 * @param namespaceService the namespace service
 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	public class MockResultSet implements ResultSet {

		private List<ResultSetRow> rows;

		private Map<NodeRef, List<Pair<String, List<String>>>> highLights = new HashMap<NodeRef, List<Pair<String, List<String>>>>();

  /** The facet queries. */
		private Map<String, Integer> facetQueries = new HashMap<String, Integer>();

  /** The spell check result. */
		private SpellCheckResult spellCheckResult = new SpellCheckResult(null, null, true);

  /**
  * Constructs a new mock result set.
  *
  * @param rows the rows
  * @return the result
  */
		public MockResultSet(List<ResultSetRow> rows) {
			this.rows = rows;
		}

		@Override
  /**
  * Length.
  *
  * @return the int
  */
		public int length() {
   /**
   * Length.
   *
   * @return the int
   */
			return rows.size();
		}

		@Override
  /**
  * Get number found.
  *
  * @return the long
  */
		public long getNumberFound() {
   /**
   * Length.
   *
   * @return the int
   */
			return rows.size();
		}

		@Override
  /**
  * Get node ref.
  *
  * @param n the n
  * @return the node ref
  */
		public NodeRef getNodeRef(int n) {
			if (rows.size() <= n)
				return null;
   /**
   * Get node ref.
   *
   * @param n the n
   * @return the node ref
   */
			return rows.get(n).getNodeRef();
		}

		@Override
  /**
  * Get score.
  *
  * @param n the n
  * @return the float
  */
		public float getScore(int n) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
  /** Close. */
		public void close() {
			// TODO Auto-generated method stub

		}

		@Override
  /**
  * Get row.
  *
  * @param i the i
  * @return the result set row
  */
		public ResultSetRow getRow(int i) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
  /**
  * Get node refs.
  *
  * @return the list
  */
		public List<NodeRef> getNodeRefs() {
   /**
   * Get node refs.
   *
   * @return the list
   */
			List<NodeRef> result = new ArrayList<NodeRef>();
			for (ResultSetRow row : rows)
				result.add(row.getNodeRef());
			return result;
		}

		@Override
  /**
  * Get child assoc refs.
  *
  * @return the list
  */
		public List<ChildAssociationRef> getChildAssocRefs() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
  /**
  * Get child assoc ref.
  *
  * @param n the n
  * @return the child association ref
  */
		public ChildAssociationRef getChildAssocRef(int n) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
  /**
  * Get result set meta data.
  *
  * @return the result set meta data
  */
		public ResultSetMetaData getResultSetMetaData() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
  /**
  * Get start.
  *
  * @return the int
  */
		public int getStart() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
  /**
  * Has more.
  *
  * @return the boolean
  */
		public boolean hasMore() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
  /**
  * Set bulk fetch.
  *
  * @param bulkFetch the bulk fetch
  * @return the boolean
  */
		public boolean setBulkFetch(boolean bulkFetch) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
  /**
  * Get bulk fetch.
  *
  * @return the boolean
  */
		public boolean getBulkFetch() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
  /**
  * Set bulk fetch size.
  *
  * @param bulkFetchSize the bulk fetch size
  * @return the int
  */
		public int setBulkFetchSize(int bulkFetchSize) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
  /**
  * Get bulk fetch size.
  *
  * @return the int
  */
		public int getBulkFetchSize() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
  /**
  * Get field facet.
  *
  * @param field the field
  */
		public List<Pair<String, Integer>> getFieldFacet(String field) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
  /**
  * Iterator.
  *
  * @return the iterator
  */
		public Iterator<ResultSetRow> iterator() {
			// TODO Auto-generated method stub
   /**
   * Iterator.
   *
   * @return the iterator
   */
			return rows.iterator();
		}

		@Override
  /** Get facet queries. */
		public Map<String, Integer> getFacetQueries() {
			return facetQueries;
		}

		@Override
  /**
  * Get spell check result.
  *
  * @return the spell check result
  */
		public SpellCheckResult getSpellCheckResult() {
			return spellCheckResult;
		}

		@Override
  /** Get highlighting. */
		public Map<NodeRef, List<Pair<String, List<String>>>> getHighlighting() {
			return highLights;
		}
	}

	public class MockResultSetRow implements ResultSetRow {

  /** The node ref. */
		private NodeRef nodeRef;

  /**
  * Constructs a new mock result set row.
  *
  * @param nodeRef the node ref
  * @return the result
  */
		public MockResultSetRow(NodeRef nodeRef) {
			this.nodeRef = nodeRef;
		}

		@Override
  /** Get values. */
		public Map<String, Serializable> getValues() {
   /** Get values. */
			Map<String, Serializable> map = new HashMap<String, Serializable>();
			map.put("", nodeRef);
			return map;
		}

		@Override
  /**
  * Get value.
  *
  * @param columnName the column name
  * @return the serializable
  */
		public Serializable getValue(String columnName) {
			return nodeRef;
		}

		@Override
  /**
  * Get value.
  *
  * @param qname the qname
  * @return the serializable
  */
		public Serializable getValue(QName qname) {
   /**
   * Get value.
   *
   * @param qname the qname
   * @return the serializable
   */
			return nodeService.getProperty(nodeRef, qname);
		}

		@Override
  /**
  * Get node ref.
  *
  * @return the node ref
  */
		public NodeRef getNodeRef() {
			return nodeRef;
		}

		@Override
  /** Get node refs. */
		public Map<String, NodeRef> getNodeRefs() {
			return null;
		}

		@Override
  /**
  * Get node ref.
  *
  * @param selectorName the selector name
  * @return the node ref
  */
		public NodeRef getNodeRef(String selectorName) {
			return nodeRef;
		}

		@Override
  /**
  * Get score.
  *
  * @return the float
  */
		public float getScore() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
  /** Get scores. */
		public Map<String, Float> getScores() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
  /**
  * Get score.
  *
  * @param selectorName the selector name
  * @return the float
  */
		public float getScore(String selectorName) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
  /**
  * Get result set.
  *
  * @return the result set
  */
		public ResultSet getResultSet() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
  /**
  * Get q name.
  *
  * @return the q name
  */
		public QName getQName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
  /**
  * Get index.
  *
  * @return the int
  */
		public int getIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
  /**
  * Get child assoc ref.
  *
  * @return the child association ref
  */
		public ChildAssociationRef getChildAssocRef() {
			// TODO Auto-generated method stub
			return null;
		}

	}

 /**
 * Get properties from query.
 *
 * @param query the query
 * @return the list
 */
	private List<MockProperty> getPropertiesFromQuery(String query) {
  /**
  * Get properties from query.
  *
  * @param query the query
  * @return the list
  */
		List<MockProperty> properties = new ArrayList<MockProperty>();
  /**
  * Get properties from query.
  *
  * @param query the query
  * @return the list
  */
		String[] segments = query.split(" (?i)AND | (?i)OR ");
  /**
  * Get properties from query.
  *
  * @param query the query
  * @return the list
  */
		for (String segm : segments) {
   /** The seg. */
			String seg = segm.trim();
   /** The seg. */
			if (seg.startsWith("(") && seg.endsWith(")")) {
    /** The seg. */
				seg = seg.replaceFirst("\\(", "");
    /** The seg. */
				seg = seg.substring(0, seg.length() - 1);
			}
			if (!seg.startsWith("PATH:") && !seg.substring(1, seg.length()).startsWith("PATH:")
					&& !seg.startsWith("TYPE:") && !seg.contains("[") && !seg.startsWith("ASPECT:")
					&& !seg.startsWith("(ASPECT:") && !seg.startsWith("-ASPECT:") && !seg.startsWith("(-ASPECT:")
					&& !seg.toLowerCase().startsWith("not aspect:") && !seg.toLowerCase().startsWith("(not aspect:")) {
				String[] splitted = seg.split(":");
				String uri = namespaceService.getNamespaceURI(splitted[0].replaceAll("(?i)not @", "")
						.replaceAll("@", "").replaceAll("=", "").replaceAll("\\+", "").replaceAll("\\\\", ""));
    /** The key. */
				QName key = QName.createQName(uri, splitted[1]);
				properties.add(new MockProperty(key, splitted[2].replaceAll("\"", "").replaceAll("\\\\", ""),
						seg.startsWith("-") || seg.startsWith("(-") || seg.toLowerCase().startsWith("not")
								|| seg.toLowerCase().startsWith("(not") ? true : false));
			}
		}
		return properties;
	}

 /**
 * Get aspects from query.
 *
 * @param query the query
 * @return the list
 */
	private List<MockAspect> getAspectsFromQuery(String query) {
  /**
  * Get aspects from query.
  *
  * @param query the query
  * @return the list
  */
		List<MockAspect> aspects = new ArrayList<MockAspect>();
  /**
  * Get properties from query.
  *
  * @param query the query
  * @return the list
  */
		String[] segments = query.split(" (?i)AND | (?i)OR ");
  /**
  * Get properties from query.
  *
  * @param query the query
  * @return the list
  */
		for (String segm : segments) {
   /** The seg. */
			String seg = segm.trim();
   /** The seg. */
			if (seg.startsWith("(") && seg.endsWith(")")) {
    /** The seg. */
				seg = seg.replaceFirst("\\(", "");
    /** The seg. */
				seg = seg.substring(0, seg.length() - 1);
			}
			if (seg.startsWith("ASPECT:") || seg.startsWith("(ASPECT:") || seg.startsWith("-ASPECT:")
					|| seg.startsWith("(-ASPECT:") || seg.toLowerCase().startsWith("not aspect:")
					|| seg.toLowerCase().startsWith("(not aspect:")) {
				String[] splitted = seg.split(":");
				String uri = namespaceService.getNamespaceURI(splitted[1].replaceAll("@", "").replaceAll("=", "")
						.replaceAll("\\+", "").replaceAll("\\\\", "").replaceAll("\"", ""));
    /** The key. */
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
 * Get segment from query.
 *
 * @param query the query
 * @param segment the segment
 * @return the string
 */
	private String getSegmentFromQuery(String query, String segment) {
  /**
  * Get segment from query.
  *
  * @param query the query
  * @param segment the segment
  * @return the string
  */
		if (query.contains(segment)) {
   /**
   * Get segment from query.
   *
   * @param query the query
   * @param segment the segment
   * @return the string
   */
			query = query.substring(query.indexOf(segment) + segment.length());
   /**
   * Get segment from query.
   *
   * @param query the query
   * @param segment the segment
   * @return the string
   */
			query = query.substring(0, query.indexOf("\""));
			return query;
		}
		return null;
	}

 /**
 * Has type.
 *
 * @param type the type
 * @param nodeRef the node ref
 * @return the boolean
 */
	private boolean hasType(String type, NodeRef nodeRef) {
		if (type == null)
			return true;
		else {
   /** The type node. */
			QName typeNode = nodeService.getType(nodeRef);
   /** The type node str. */
			String typeNodeStr = typeNode.getPrefixString();
   /** The type node str. */
			return type.equals(typeNodeStr);
		}
	}

 /**
 * Has properties.
 *
 * @param properties the properties
 * @param nodeRef the node ref
 * @return the boolean
 */
	private boolean hasProperties(List<MockProperty> properties, NodeRef nodeRef) {
  /**
  * Has properties.
  *
  * @param properties the properties
  * @param nodeRef the node ref
  * @return the boolean
  */
		List<Boolean> results = new ArrayList<Boolean>();
		if (properties.isEmpty())
			return true;
  /**
  * Has properties.
  *
  * @param properties the properties
  * @param nodeRef the node ref
  * @return the boolean
  */
		for (MockProperty property : properties) {
			Object value = (Object) nodeService.getProperty(nodeRef, property.getQname());
			if (value instanceof String || value == null) {
    /** The pattern. */
				String pattern = property.getValue();
    /** The pattern. */
				String[] subpatterns = pattern.split("\\*");
				boolean result = false;
    /** The pattern. */
				if (subpatterns.length > 1)
     /** The pattern. */
					for (String subpattern : subpatterns)
      /** The pattern. */
						if (value != null && ((String) value).contains(subpattern))
       /** The pattern. */
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
 * Has aspects.
 *
 * @param aspects the aspects
 * @param nodeRef the node ref
 * @return the boolean
 */
	private boolean hasAspects(List<MockAspect> aspects, NodeRef nodeRef) {
  /**
  * Has properties.
  *
  * @param properties the properties
  * @param nodeRef the node ref
  * @return the boolean
  */
		List<Boolean> results = new ArrayList<Boolean>();
		if (aspects.isEmpty())
			return true;
  /**
  * Has aspects.
  *
  * @param aspects the aspects
  * @param nodeRef the node ref
  * @return the boolean
  */
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

	private boolean hasPath(StoreRef store, String path, String[] subpaths, int wildcardsNumber, NodeRef nodeRef) {
		String nodepath = nodeService.getPath(nodeRef).toString();
		if (store == null || path == null)
			return true;
		else if (store != null && !nodepath.contains(MockContentService.FOLDER_TEST + store.getProtocol()))
			return false;
		nodepath = nodepath
				.substring(nodepath.indexOf(MockContentService.FOLDER_TEST) + MockContentService.FOLDER_TEST.length());
		String lastNodepath = "";
  /** The last nodepath. */
		if (nodepath.indexOf("/") >= 0) {
   /** The last nodepath. */
			nodepath = nodepath.substring(nodepath.indexOf("/"));
   /** The last nodepath. */
			lastNodepath = nodepath.substring(nodepath.lastIndexOf("/"));
		} else
			nodepath = "";
		boolean result = true;
  /** The last nodepath. */
		for (int i = 0; i < subpaths.length; i++) {
			String subpath = subpaths[i];
   /** The subpath. */
			result = result && nodepath.contains(subpath.replaceAll("//", ""));
			if (result && i == subpaths.length - 1 && nodepath.indexOf("/") >= 0
					&& subpath.indexOf("/") >= 0)
    /** The subpath. */
				if (!subpath.endsWith("/")) {
     /** The name subpath. */
					String nameSubpath = subpath.substring(subpath.lastIndexOf("/"));
     /** The name subpath. */
					result = result && lastNodepath.equals(nameSubpath);
				} else if (path.endsWith("/*") && !path.endsWith("//*") && !path.equals("/*")
						&& !subpath.equals("/")) {
					String nameSubpath = nodepath;
     /** The name subpath. */
					for (int j = 0; j < wildcardsNumber; j++)
      /** The name subpath. */
						nameSubpath = nameSubpath.substring(0, nameSubpath.lastIndexOf("/"));
     /** The name subpath. */
					result = result && (nameSubpath + "/").endsWith(subpath);
				} else {
     /** The first line. */
					String firstLine = subpath.replaceAll("//", "");
     /** The name subpath. */
					String nameSubpath = firstLine.substring(firstLine.lastIndexOf("/"));
					if (lastNodepath.equals(nameSubpath))
						result = false;
				}
		}
		return result;
	}

 /**
 * Get subpaths.
 *
 * @param path the path
 * @return the string[]
 */
	private String[] getSubpaths(String path) {
  /**
  * Get subpaths.
  *
  * @param path the path
  * @return the string[]
  */
		if (path != null) {
			String subpath = path;
   /** The subpath. */
			String[] splittedPath = subpath.split("(\\*)|(//)");
   /** The subpath. */
			if (path.endsWith("//*")) {
    /** The subpath. */
				path = path.substring(0, path.length() - 3);
				splittedPath[splittedPath.length - 1] = splittedPath[splittedPath.length - 1] + "//";
   /** The subpath. */
			} else if (!path.equals("/") && !path.equals("/*")) {
				String lastPath = splittedPath[splittedPath.length - 1];
    /** The last path. */
				while (lastPath.equals("/")) {
     /** The last path. */
					splittedPath = Arrays.copyOf(splittedPath, splittedPath.length - 1);
					lastPath = splittedPath[splittedPath.length - 1];
				}
			}
			return splittedPath;
		} else
			return null;
	}

	private void XPATHQuery(StoreRef store, String query, List<NodeRef> nodeRefs, List<ResultSetRow> rows) {
  /**
  * X p a t h query.
  *
  * @param store the store
  * @param query the query
  * @param nodeRefs the node refs
  * @param rows the rows
  */
		query = prepare(query, store);
		String[] subpaths = getSubpaths(query);
		int wildcardsNumber = 0;
		String processQuery = query;
  /** The process query. */
		while (processQuery.endsWith("/*")) {
			wildcardsNumber++;
   /** The process query. */
			processQuery = processQuery.substring(0, processQuery.lastIndexOf("/*"));
		}
  /** The process query. */
		for (NodeRef nodeRef : nodeRefs) {
   /** The process query. */
			if (hasPath(store, query, subpaths, wildcardsNumber, nodeRef))
    /** The process query. */
				rows.add(new MockResultSetRow(nodeRef));
		}
	}

	private void FTSQuery(StoreRef store, String query, List<NodeRef> nodeRefs, List<ResultSetRow> rows) {
  /** The path. */
		String path = getSegmentFromQuery(query, "PATH:\"");
  /**
  * Get subpaths.
  *
  * @param path the path
  * @return the string[]
  */
		if (path != null) {
   /** The path. */
			path = prepare(path, store);
			if (path.endsWith("."))
				path = path.substring(0, path.lastIndexOf(".")) + "*";
			if (!path.startsWith("/"))
				path = "/" + path;
		}
		String processQuery = path;
  /** The process query. */
		String[] subpaths = getSubpaths(path);
  /** The type. */
		String type = getSegmentFromQuery(query, "TYPE:\"");
  /** The type. */
		List<MockProperty> properties = getPropertiesFromQuery(query);
  /** The type. */
		List<MockAspect> aspects = getAspectsFromQuery(query);
		int wildcardsNumber = 0;
  /** The type. */
		if (processQuery != null)
   /** The process query. */
			while (processQuery.endsWith("/*")) {
				wildcardsNumber++;
    /** The process query. */
				processQuery = processQuery.substring(0, processQuery.lastIndexOf("/*"));
			}
  /** The process query. */
		for (NodeRef nodeRef : nodeRefs) {
			if (hasType(type, nodeRef) && hasProperties(properties, nodeRef) && hasAspects(aspects, nodeRef)
					&& hasPath(store, path, subpaths, wildcardsNumber, nodeRef))
    /** The process query. */
				rows.add(new MockResultSetRow(nodeRef));
		}
	}

 /**
 * Prepare.
 *
 * @param query the query
 * @param store the store
 * @return the string
 */
	private String prepare(String query, StoreRef store) {
  /**
  * Prepare.
  *
  * @param query the query
  * @param store the store
  * @return the string
  */
		query = ISO9075.decode(query).trim();
		String prefix = MockContentService.FOLDER_TEST;
		if (store != null)
			prefix = prefix + store.getProtocol();
  /** The prefix. */
		int index = query.indexOf(prefix);
		if (index >= 0)
			query = query.substring(index + prefix.length());
		String result = "";
  /** The result. */
		String[] slashes = query.split("/");
  /** The result. */
		for (int i = 0; i < slashes.length; i++) {
			String slashed = slashes[i];
			if (slashed.contains(":"))
				result += slashed.split(":")[1];
			else
				result += slashed;
			result += "/";
		}
  /** The slashed. */
		result = result.substring(0, result.length() - 1);
		return result;
	}

	private class MockProperty {

  /** The qname. */
		private QName qname;
  /** The value. */
		private String value;
  /** The to delete. */
		private boolean toDelete;

  /**
  * Constructs a new mock property.
  *
  * @param qname the qname
  * @param value the value
  * @param toDelete the to delete
  * @return the result
  */
		public MockProperty(QName qname, String value, boolean toDelete) {
			this.qname = qname;
			this.value = value;
			this.toDelete = toDelete;
		}

  /**
  * Get qname.
  *
  * @return the q name
  */
		public QName getQname() {
			return qname;
		}

  /**
  * Get value.
  *
  * @return the string
  */
		public String getValue() {
			return value;
		}

  /**
  * Is to delete.
  *
  * @return the boolean
  */
		public boolean isToDelete() {
			return toDelete;
		}
	}

	private class MockAspect {

  /** The qname. */
		private QName qname;
  /** The to delete. */
		private boolean toDelete;

  /**
  * Constructs a new mock aspect.
  *
  * @param qname the qname
  * @param toDelete the to delete
  * @return the result
  */
		public MockAspect(QName qname, boolean toDelete) {
			this.qname = qname;
			this.toDelete = toDelete;
		}

  /**
  * Get qname.
  *
  * @return the q name
  */
		public QName getQname() {
			return qname;
		}

  /**
  * Is to delete.
  *
  * @return the boolean
  */
		public boolean isToDelete() {
			return toDelete;
		}
	}

}
