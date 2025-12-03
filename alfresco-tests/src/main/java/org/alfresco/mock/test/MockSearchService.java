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
* Mock implementation of the MockSearchService class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockSearchService implements SearchService, Serializable {

/**
* The node service.
 */
	@Autowired
	private NodeService nodeService;

/**
* The namespace service.
 */
	@Autowired
	private NamespaceService namespaceService;

/**
* {@inheritDoc}
* @param store the store
* @param language the language
* @param query the query
* @return the result
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
 */
	@Override
	public ResultSet query(StoreRef store, String language, String query,
			QueryParameterDefinition[] queryParameterDefinitions) {
		return query(store, language, query);
	}

/**
* {@inheritDoc}
* @param store the store
* @param queryId the queryId
* @return the result
 */
	@Override
	public ResultSet query(StoreRef store, QName queryId, QueryParameter[] queryParameters) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param searchParameters the searchParameters
* @return the result
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
* @param nodeRef the nodeRef
* @param propertyQName the propertyQName
* @param googleLikePattern the googleLikePattern
* @return the result
 */
	@Override
	public boolean contains(NodeRef nodeRef, QName propertyQName, String googleLikePattern)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @param propertyQName the propertyQName
* @param googleLikePattern the googleLikePattern
* @param defaultOperator the defaultOperator
* @return the result
 */
	@Override
	public boolean contains(NodeRef nodeRef, QName propertyQName, String googleLikePattern, Operator defaultOperator)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return false;
	}

/**
* {@inheritDoc}
* @param nodeRef the nodeRef
* @param propertyQName the propertyQName
* @param sqlLikePattern the sqlLikePattern
* @param includeFTS the includeFTS
* @return the result
 */
	@Override
	public boolean like(NodeRef nodeRef, QName propertyQName, String sqlLikePattern, boolean includeFTS)
			throws InvalidNodeRefException {
		// TODO Auto-generated method stub
		return false;
	}

/**
* Gets the node service.
*
* @return the node service
 */
	public MockNodeService getNodeService() {
		return (MockNodeService) nodeService;
	}

/**
* Sets the node service.
*
* @param nodeService the node service
 */
	public void setNodeService(MockNodeService nodeService) {
		this.nodeService = nodeService;
	}

/**
* Sets the namespace service.
*
* @param namespaceService the namespace service
 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

/**
* Mock result set implementation.
 */
	public class MockResultSet implements ResultSet {

		private List<ResultSetRow> rows;

		private Map<NodeRef, List<Pair<String, List<String>>>> highLights = new HashMap<NodeRef, List<Pair<String, List<String>>>>();

		private Map<String, Integer> facetQueries = new HashMap<String, Integer>();

		private SpellCheckResult spellCheckResult = new SpellCheckResult(null, null, true);

/**
* Constructs a new MockResultSet instance.
*
* @param rows the list of result set rows
 */
		public MockResultSet(List<ResultSetRow> rows) {
			this.rows = rows;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public int length() {
			return rows.size();
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public long getNumberFound() {
			return rows.size();
		}

/**
* {@inheritDoc}
* @param n the n
* @return the result
 */
		@Override
		public NodeRef getNodeRef(int n) {
			if (rows.size() <= n)
				return null;
			return rows.get(n).getNodeRef();
		}

/**
* {@inheritDoc}
* @param n the n
* @return the result
 */
		@Override
		public float getScore(int n) {
			// TODO Auto-generated method stub
			return 0;
		}

/**
* {@inheritDoc}
 */
		@Override
		public void close() {
			// TODO Auto-generated method stub

		}

/**
* {@inheritDoc}
* @param i the i
* @return the result
 */
		@Override
		public ResultSetRow getRow(int i) {
			// TODO Auto-generated method stub
			return null;
		}

/**
* {@inheritDoc}
* @return the result
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
* @return the result
 */
		@Override
		public List<ChildAssociationRef> getChildAssocRefs() {
			// TODO Auto-generated method stub
			return null;
		}

/**
* {@inheritDoc}
* @param n the n
* @return the result
 */
		@Override
		public ChildAssociationRef getChildAssocRef(int n) {
			// TODO Auto-generated method stub
			return null;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public ResultSetMetaData getResultSetMetaData() {
			// TODO Auto-generated method stub
			return null;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public int getStart() {
			// TODO Auto-generated method stub
			return 0;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public boolean hasMore() {
			// TODO Auto-generated method stub
			return false;
		}

/**
* {@inheritDoc}
* @param bulkFetch the bulkFetch
* @return the result
 */
		@Override
		public boolean setBulkFetch(boolean bulkFetch) {
			// TODO Auto-generated method stub
			return false;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public boolean getBulkFetch() {
			// TODO Auto-generated method stub
			return false;
		}

/**
* {@inheritDoc}
* @param bulkFetchSize the bulkFetchSize
* @return the result
 */
		@Override
		public int setBulkFetchSize(int bulkFetchSize) {
			// TODO Auto-generated method stub
			return 0;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public int getBulkFetchSize() {
			// TODO Auto-generated method stub
			return 0;
		}

/**
* {@inheritDoc}
* @param field the field
 */
		@Override
		public List<Pair<String, Integer>> getFieldFacet(String field) {
			// TODO Auto-generated method stub
			return null;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public Iterator<ResultSetRow> iterator() {
			// TODO Auto-generated method stub
			return rows.iterator();
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public Map<String, Integer> getFacetQueries() {
			return facetQueries;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public SpellCheckResult getSpellCheckResult() {
			return spellCheckResult;
		}

/**
* {@inheritDoc}
 */
		@Override
		public Map<NodeRef, List<Pair<String, List<String>>>> getHighlighting() {
			return highLights;
		}
	}

/**
* Mock result set row implementation.
 */
	public class MockResultSetRow implements ResultSetRow {

/**
* The node ref.
 */
		private NodeRef nodeRef;

/**
* Constructs a new MockResultSetRow instance.
*
* @param nodeRef the node reference
 */
		public MockResultSetRow(NodeRef nodeRef) {
			this.nodeRef = nodeRef;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public Map<String, Serializable> getValues() {
			Map<String, Serializable> map = new HashMap<String, Serializable>();
			map.put("", nodeRef);
			return map;
		}

/**
* {@inheritDoc}
* @param columnName the columnName
* @return the result
 */
		@Override
		public Serializable getValue(String columnName) {
			return nodeRef;
		}

/**
* {@inheritDoc}
* @param qname the qname
* @return the result
 */
		@Override
		public Serializable getValue(QName qname) {
			return nodeService.getProperty(nodeRef, qname);
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public NodeRef getNodeRef() {
			return nodeRef;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public Map<String, NodeRef> getNodeRefs() {
			return null;
		}

/**
* {@inheritDoc}
* @param selectorName the selectorName
* @return the result
 */
		@Override
		public NodeRef getNodeRef(String selectorName) {
			return nodeRef;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public float getScore() {
			// TODO Auto-generated method stub
			return 0;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public Map<String, Float> getScores() {
			// TODO Auto-generated method stub
			return null;
		}

/**
* {@inheritDoc}
* @param selectorName the selectorName
* @return the result
 */
		@Override
		public float getScore(String selectorName) {
			// TODO Auto-generated method stub
			return 0;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public ResultSet getResultSet() {
			// TODO Auto-generated method stub
			return null;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public QName getQName() {
			// TODO Auto-generated method stub
			return null;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public int getIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

/**
* {@inheritDoc}
* @return the result
 */
		@Override
		public ChildAssociationRef getChildAssocRef() {
			// TODO Auto-generated method stub
			return null;
		}

	}

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
* Gets the segment from query.
* @param query the query
* @param segment the segment
* @return the result
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
* Performs has type.
* @param type the type
* @param nodeRef the nodeRef
* @return the result
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
* Performs has properties.
* @param properties the properties
* @param nodeRef the nodeRef
* @return the result
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
* Performs has aspects.
* @param aspects the aspects
* @param nodeRef the nodeRef
* @return the result
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
* Performs has path.
* @param store the store
* @param path the path
* @param wildcardsNumber the wildcardsNumber
* @param nodeRef the nodeRef
* @return the result
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
* Performs xpath query.
* @param store the store
* @param query the query
* @param nodeRefs the nodeRefs
* @param rows the rows
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
* Performs fts query.
* @param store the store
* @param query the query
* @param nodeRefs the nodeRefs
* @param rows the rows
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
* Performs prepare.
* @param query the query
* @param store the store
* @return the result
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

	private class MockProperty {

/**
* The qname.
 */
		private QName qname;
/**
* The value.
 */
		private String value;
/**
* The to delete.
 */
		private boolean toDelete;

/**
* Constructs a new MockProperty instance.
* @param qname the qname
* @param value the value
* @param toDelete the toDelete
 */
		public MockProperty(QName qname, String value, boolean toDelete) {
			this.qname = qname;
			this.value = value;
			this.toDelete = toDelete;
		}

/**
* Gets the qname.
* @return the result
 */
		public QName getQname() {
			return qname;
		}

/**
* Gets the value.
* @return the result
 */
		public String getValue() {
			return value;
		}

/**
* Checks if to delete.
* @return the result
 */
		public boolean isToDelete() {
			return toDelete;
		}
	}

	private class MockAspect {

/**
* The qname.
 */
		private QName qname;
/**
* The to delete.
 */
		private boolean toDelete;

/**
* Constructs a new MockAspect instance.
* @param qname the qname
* @param toDelete the toDelete
 */
		public MockAspect(QName qname, boolean toDelete) {
			this.qname = qname;
			this.toDelete = toDelete;
		}

/**
* Gets the qname.
* @return the result
 */
		public QName getQname() {
			return qname;
		}

/**
* Checks if to delete.
* @return the result
 */
		public boolean isToDelete() {
			return toDelete;
		}
	}

}
