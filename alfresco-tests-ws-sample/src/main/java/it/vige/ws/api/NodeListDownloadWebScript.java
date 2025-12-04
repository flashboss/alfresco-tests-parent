package it.vige.ws.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ApplicationModel;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceException;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.Pair;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptException;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

import it.vige.ws.utils.OpenDataCommand;

/**
 * NodeListDownloadWebScript implementation for testing purposes.
 *
 * @author vige
 */
public class NodeListDownloadWebScript extends DeclarativeWebScript
// implements InitializingBean
{

	// Logger
	private static final Log logger = LogFactory.getLog(NodeListDownloadWebScript.class);

	public static final String MODEL_CSV = "csv";
	public static final String MODEL_EXCEL = "excel";

	private String[] overHeadings;
	/** The filename base. */
	private String filenameBase;

	/** The skip count def. */
	private String skipCountDef;
	/** The max lines def. */
	private String maxLinesDef;
	/** The max items def. */
	private String maxItemsDef;

	/** The node service. */
	private NodeService nodeService;
	/** The open data command. */
	private OpenDataCommand openDataCommand;

	/** The namespace service. */
	private NamespaceService namespaceService;
	private String[] modelProperties;
	/** The open data date format. */
	private String openDataDateFormat;
	/** The list separator. */
	private String listSeparator;

	/** The search service. */
	private SearchService searchService;
	
	/**
	 * Set over headings.
	 *
	 * @param headingsString the headings string
	 */
	public void setOverHeadings(String[] headingsString) {
		this.overHeadings = headingsString;
	}

	public String[] getOverHeadings() {
		return overHeadings;
	}

	/**
	 * Set skip count def.
	 *
	 * @param skipCountDef the skip count def
	 */
	public void setSkipCountDef(String skipCountDef) {
		this.skipCountDef = skipCountDef;
	}

	/**
	 * Set max lines def.
	 *
	 * @param maxLinesDef the max lines def
	 */
	public void setMaxLinesDef(String maxLinesDef) {
		this.maxLinesDef = maxLinesDef;
	}

	/**
	 * Set max items def.
	 *
	 * @param maxItemsDef the max items def
	 */
	public void setMaxItemsDef(String maxItemsDef) {
		this.maxItemsDef = maxItemsDef;
	}

	/**
	 * Set search service.
	 *
	 * @param searchService the search service
	 */
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	/**
	 * Set model properties.
	 *
	 * @param modelPropertiesString the model properties string
	 */
	public void setModelProperties(String[] modelPropertiesString) {
		this.modelProperties = modelPropertiesString;
	}

	/**
	 * Set open data command.
	 *
	 * @param openDataCommand the open data command
	 */
	public void setOpenDataCommand(OpenDataCommand openDataCommand) {
		this.openDataCommand = openDataCommand;
	}

	/**
	 * Constructs a new NodeListDownloadWebScript.
	 *
	 */
	public NodeListDownloadWebScript() {
		this.filenameBase = "DataListExport";
	}

	/**
	 * Set open data date format.
	 *
	 * @param openDataDateFormat the open data date format
	 */
	public void setOpenDataDateFormat(String openDataDateFormat) {
		this.openDataDateFormat = openDataDateFormat;
	}

	/**
	 * @param nodeService
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * @param namespaceService
	 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	/**
	 * Set list separator.
	 *
	 * @param listSeparator the list separator
	 */
	public void setListSeparator(String listSeparator) {
		this.listSeparator = listSeparator;
	}

	/**
	 * Identify the datalist
	 */
	protected List<NodeRef> identifyResource(String format, WebScriptRequest req, int maxItems, int skypCount) {
		List<NodeRef> result = new ArrayList<NodeRef>();
		// Try to find the datalist they requested
		String type = req.getParameter("type");
		logger.debug("Type: " + type);
		String query = req.getParameter("q");
		logger.debug("q: " + query);
		String luceneQuery = "";
		if (type != null) {
			luceneQuery = "TYPE:\"" + type + "\"";
			if (query != null) {
				luceneQuery += " AND " + query;
			}
		} else {
			if (query != null) {
				luceneQuery = query;
			}
		}
		if (luceneQuery.length() == 0) {
			throw new WebScriptException(Status.STATUS_BAD_REQUEST, "No valid parameters");
		}
		logger.debug("lucene query: " + luceneQuery);
		ResultSet results = null;
		try {
			SearchParameters parameters = new SearchParameters();
			parameters.setMaxItems(maxItems);
			parameters.setSkipCount(skypCount);
			parameters.setLanguage(SearchService.LANGUAGE_LUCENE);
			parameters.setQuery(luceneQuery);
			parameters.addStore(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE);
			results = searchService.query(parameters);
			// results = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
			// SearchService.LANGUAGE_LUCENE,
			// luceneQuery, parameters);
			result.addAll(results.getNodeRefs());
		} finally {
			if (results != null) {
				results.close();
			}
		}
		return result;

	}

	/**
	 * We don't have a HTML version
	 */
	protected boolean allowHtmlFallback() {
		return false;
	}

	/**
	 * Fetch the properties, in the requested order, from the data list
	 * definition
	 */
	protected List<Pair<QName, Boolean>> buildPropertiesForHeader(Object resource, String format,
			WebScriptRequest req) {
		List<Pair<QName, Boolean>> properties = new ArrayList<Pair<QName, Boolean>>();
		for (String qnameString : modelProperties) {
			String[] qnameStringSplitted = qnameString.split(":");
			String prefix = qnameStringSplitted[0];
			String localName = qnameStringSplitted[1];
			try {
				properties.add(new Pair<QName, Boolean>(QName.createQName(prefix, localName, namespaceService), true));
			} catch (NamespaceException e) {
				properties.add(new Pair<QName, Boolean>(QName.createQName(prefix, localName), true));
			}
		}
		return properties;
	}

	/**
	 * Populate body.
	 *
	 * @param resource the resource
	 * @param csv the csv
	 * @param properties the properties
	 */
	protected void populateBody(Object resource, CSVPrinter csv, List<QName> properties) throws IOException {
		throw new WebScriptException(Status.STATUS_BAD_REQUEST, "CSV not currently supported");
	}

	/**
	 * Populate body.
	 *
	 * @param resource the resource
	 * @param workbook the workbook
	 * @param sheet the sheet
	 * @param properties the properties
	 * @param style the style
	 * @return the result
	 */
	protected Sheet populateBody(Object resource, Workbook workbook, Sheet sheet, List<QName> properties, Map<String,CellStyle> style)
			throws IOException {
		@SuppressWarnings("unchecked")
		List<NodeRef> items = (List<NodeRef>) resource;
		CellStyle styleInt = style.get("styleInt");
		CellStyle styleDouble = style.get("styleDouble");
		CellStyle styleDate = style.get("styleDate");
		CellStyle styleNewLines = style.get("styleNewLines");

		// Export the items
		int rowNum = sheet.getLastRowNum()+1, colNum = 0;
		for (NodeRef item : items) {
			if (openDataCommand.checkNodeCoreProps(item)) {
				Row r = sheet.createRow(rowNum);

				colNum = 0;
				for (QName prop : properties) {
					Cell c = r.createCell(colNum);

					Serializable val = nodeService.getProperty(item, prop);

					if (val == null) {
						// Is it an association, or just missing?
						List<AssociationRef> assocs = nodeService.getTargetAssocs(item, prop);
						if (assocs.size() > 0) {
							StringBuffer text = new StringBuffer();
							int lines = 1;

							for (AssociationRef ref : assocs) {
								NodeRef child = ref.getTargetRef();
								QName type = nodeService.getType(child);
								if (ContentModel.TYPE_PERSON.equals(type)) {
									if (text.length() > 0) {
										text.append('\n');
										lines++;
									}
									text.append(nodeService.getProperty(child, ContentModel.PROP_FIRSTNAME));
									text.append(" ");
									text.append(nodeService.getProperty(child, ContentModel.PROP_LASTNAME));
								} else if (ContentModel.TYPE_CONTENT.equals(type)) {
									// TODO Link to the content
									if (text.length() > 0) {
										text.append('\n');
										lines++;
									}
									text.append(nodeService.getProperty(child, ContentModel.PROP_NAME));
									text.append(" (");
									text.append(nodeService.getProperty(child, ContentModel.PROP_TITLE));
									text.append(") ");

								} else if (ApplicationModel.TYPE_FILELINK.equals(type)) {
									NodeRef linkRef = (NodeRef) nodeService.getProperty(child,
											ContentModel.PROP_LINK_DESTINATION);
									if (linkRef != null) {
										if (text.length() > 0) {
											text.append('\n');
											lines++;
										}
										text.append("link to: ");
										try {
											text.append(nodeService.getProperty(linkRef, ContentModel.PROP_NAME));
											text.append(" (");
											text.append(nodeService.getProperty(linkRef, ContentModel.PROP_TITLE));
											text.append(") ");
										} catch (Exception e) {
											text.append(nodeService.getProperty(child, ContentModel.PROP_NAME));
											text.append(" (");
											text.append(nodeService.getProperty(child, ContentModel.PROP_TITLE));
											text.append(") ");

										}
									}
								} else {
									System.err.println("TODO: handle " + type + " for " + child);
								}
							}

							String v = text.toString();
							c.setCellValue(v);
							if (lines > 1) {
								c.setCellStyle(styleNewLines);
								r.setHeightInPoints(lines * sheet.getDefaultRowHeightInPoints());
							}
						} else {
							// This property isn't set
							c.setCellType(Cell.CELL_TYPE_BLANK);
						}
					} else {
						// Regular property, set
						if (val instanceof String) {
							c.setCellValue((String) val);
							c.setCellStyle(styleNewLines);
						} else if (val instanceof Date) {
							c.setCellValue((Date) val);
							c.setCellStyle(styleDate);
						} else if (val instanceof Integer || val instanceof Long) {
							double v = 0.0;
							if (val instanceof Long)
								v = (double) (Long) val;
							if (val instanceof Integer)
								v = (double) (Integer) val;
							c.setCellValue(v);
							c.setCellStyle(styleInt);
						} else if (val instanceof Float || val instanceof Double) {
							double v = 0.0;
							if (val instanceof Float)
								v = (double) (Float) val;
							if (val instanceof Double)
								v = (double) (Double) val;
							c.setCellValue(v);
							c.setCellStyle(styleDouble);
						} else if (val instanceof ArrayList) {
							StringBuilder tmpVal = new StringBuilder("");
							int n = ((ArrayList<?>) val).size();
							for (int i = 0; i < n; i++) {
								((ArrayList<?>) val).get(i);
								tmpVal.append(((ArrayList<?>) val).get(i).toString());
								if (i < n - 1) {
									tmpVal.append(listSeparator);
								}
							}
							val = tmpVal.toString();
							c.setCellValue((String) val);
							c.setCellStyle(styleNewLines);
						} else {
							// TODO
							System.err.println("TODO: handle " + val.getClass().getName() + " - " + val);
						}
					}

					colNum++;
				}

				rowNum++;
			}		
		}

		// Sensible column widths please!
		for (int colNumb = 0; colNumb < properties.size(); colNumb++) {
			try {
				sheet.autoSizeColumn(colNumb);
			} catch (IllegalArgumentException e) {
				sheet.setColumnWidth(colNumb, 40 * 256);
			}
		}
		return sheet;
	}

	/**
	 * Generate style from w b.
	 *
	 * @param workbook the workbook
	 */
	protected Map<String, CellStyle> generateStyleFromWB(Workbook workbook) {
		DataFormat formatter = workbook.createDataFormat();

		CellStyle styleInt = workbook.createCellStyle();
		styleInt.setDataFormat(formatter.getFormat("0"));
		CellStyle styleDate = workbook.createCellStyle();
		styleDate.setDataFormat(formatter.getFormat(openDataDateFormat));
		CellStyle styleDouble = workbook.createCellStyle();
		styleDouble.setDataFormat(formatter.getFormat("General"));
		CellStyle styleNewLines = workbook.createCellStyle();
		styleNewLines.setWrapText(true);

		CellStyle hlink_style = workbook.createCellStyle();
		Font hlink_font = workbook.createFont();
		hlink_font.setUnderline(Font.U_SINGLE);
		hlink_font.setColor(IndexedColors.BLUE.getIndex());
		hlink_style.setFont(hlink_font);
		Map<String,CellStyle> style = new HashMap<>();
		style.put("styleInt", styleInt);
		style.put("styleDate", styleDate);
		style.put("styleDouble", styleDouble);
		style.put("styleNewLines", styleNewLines);
		return style;
	}
	/**
	 * Execute impl.
	 *
	 * @param req the req
	 * @param status the status
	 */
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", Boolean.TRUE);
 
		String format = req.getFormat();
		if ("csv".equals(format) || "xls".equals(format) || "xlsx".equals(format) || "excel".equals(format)) {
 
			//Object resource = identifyResource(format, req);
 
			try {
				//generateSpreadsheet(resource, format, req, status, model);
				generateSpreadsheet(format, req, status, model);
				return model;
			} catch (IOException e) {
				throw new WebScriptException(Status.STATUS_BAD_REQUEST, "Unable to generate template file", e);
			}
		}
 
		if (allowHtmlFallback()) {
 
			return model;
		} else {
			throw new WebScriptException("Web Script format '" + format + "' is not supported");
		}
	}

	/**
	 * Generates the spreadsheet, based on the properties in the header and a
	 * callback for the body.
	 */
	public void generateSpreadsheet(/*Object resource,*/ String format, WebScriptRequest req, Status status,
			Map<String, Object> model) throws IOException {
		List<NodeRef> resource = new ArrayList<NodeRef>();
		List<Pair<QName, Boolean>> propertyDetails = buildPropertiesForHeader(resource, format, req);
		String[] headings;
		boolean[] required = new boolean[propertyDetails.size()];
		if (overHeadings.length == 0) {
			headings = new String[propertyDetails.size()];

			for (int i = 0; i < headings.length; i++) {
				Pair<QName, Boolean> property = propertyDetails.get(i);
				if (property == null || property.getFirst() == null) {
					headings[i] = "";
					required[i] = false;
				}
			}
		} else {
			headings = overHeadings;
			for (int i = 0; i < propertyDetails.size(); i++) {
				required[i] = false;
			}
		}
 
		List<QName> properties = new ArrayList<QName>(propertyDetails.size());
		for (Pair<QName, Boolean> p : propertyDetails) {
			QName qn = null;
			if (p != null) {
				qn = p.getFirst();
			}
			properties.add(qn);
		}

		int num = 0;
		int skipCount = Integer.parseInt(skipCountDef);
		int iteration = 0;
		int maxItems =  Integer.parseInt(maxItemsDef);		
		int maxLines = Integer.parseInt(maxLinesDef);
		if (req.getParameter("maxItems")!=null) {
			maxItems = Integer.parseInt(req.getParameter("maxItems"));
		}
		if (req.getParameter("skipCount")!=null) {
			skipCount = Integer.parseInt(req.getParameter("skipCount"));
		}
		if (req.getParameter("maxLines")!=null) {
			maxLines = Integer.parseInt(req.getParameter("maxLines"));
		}
		int maxIterations = (maxLines / maxItems) + 1;
		if (logger.isDebugEnabled()) {
			logger.debug("Executing NodeList WS with parameters: skipCount= " + skipCount + " , maxItems= " + maxItems + " , maxLines= " + maxLines);
			logger.debug("MaxIteration for NodeList ws= " + maxIterations);
		}
		if ("csv".equals(format)) {
			StringWriter sw = new StringWriter();
			CSVPrinter csv = new CSVPrinter(sw, CSVStrategy.EXCEL_STRATEGY);
			csv.println(headings);
			
			do {
				resource = identifyResource(format, req, maxItems, skipCount);
				num = resource.size();
				skipCount += num;
				iteration ++;
				if (logger.isDebugEnabled()) {
					logger.debug("Executing iteration of resources with num: " + num + " and skipCount: " + skipCount);
					if (iteration==maxIterations)
						logger.debug("Exiting because max iteration is reached");
				}	
				populateBody(resource, csv, properties);
				
			} while (num==maxItems & iteration<maxIterations);
			//populateBody(resource, csv, properties);

			model.put(MODEL_CSV, sw.toString());
		} else {
			Workbook wb;
			if ("xlsx".equals(format)) {
				wb = new XSSFWorkbook();
 
			} else {
				wb = new HSSFWorkbook();
 
			}
 
			Sheet sheet = wb.createSheet("Export");
			Row hr = sheet.createRow(0);
			try {
				sheet.createFreezePane(0, 1);
			} catch (IndexOutOfBoundsException e) {
 
			}
			Font fb = wb.createFont();
			fb.setBoldweight(Font.BOLDWEIGHT_BOLD);
			Font fi = wb.createFont();
			fi.setBoldweight(Font.BOLDWEIGHT_BOLD);
			fi.setItalic(true);

			CellStyle csReq = wb.createCellStyle();
			csReq.setFont(fb);
			CellStyle csOpt = wb.createCellStyle();
			csOpt.setFont(fi);
 
			for (int i = 0; i < headings.length; i++) {
				Cell c = hr.createCell(i);
				c.setCellValue(headings[i]);
 
				c.setCellStyle(csReq);

				if (headings[i].length() == 0) {
					sheet.setColumnWidth(i, 3 * 250);
				} else {
					sheet.setColumnWidth(i, 18 * 250);
				}
			}
			
			Map<String,CellStyle> style = generateStyleFromWB(wb);
			do {
				resource = identifyResource(format, req, maxItems, skipCount);
				num = resource.size();
				if (logger.isDebugEnabled()) {
					logger.debug("Executing iteration of resources with num: " + num + " and skipCount: " + skipCount);
					if (iteration==maxIterations)
						logger.debug("Exiting because iteration is: " + iteration);
				}	
				skipCount += num;
				iteration ++;		
				sheet = populateBody(resource, wb, sheet, properties, style);
			} while (num==maxItems & iteration<maxIterations);
			//populateBody(resource, wb, sheet, properties);
 
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			model.put(MODEL_EXCEL, baos.toByteArray());
		}
	}

	/**
	 * Create template parameters.
	 *
	 * @param req the req
	 * @param res the res
	 * @param customParams the custom params
	 */
	@Override
	protected Map<String, Object> createTemplateParameters(WebScriptRequest req, WebScriptResponse res,
			Map<String, Object> customParams) {
		Map<String, Object> model = super.createTemplateParameters(req, res, customParams);
 
		model.put("req", req);
		model.put("res", res);
		model.put("writeExcel", new WriteExcel(res, model, req.getFormat(), filenameBase));
		return model;
	}

	public static class WriteExcel {
	/** The format. */
		private String format;
	/** The filename base. */
		private String filenameBase;
	/** The res. */
		private WebScriptResponse res;
	/** The model. */
		private Map<String, Object> model;

		private WriteExcel(WebScriptResponse res, Map<String, Object> model, String format, String filenameBase) {
			this.res = res;
			this.model = model;
			this.format = format;
			this.filenameBase = filenameBase;
		}

	/**
	 * Write.
	 *
	 */
		public void write() throws IOException {
			String filename = filenameBase + "." + format;
 
			if (!"csv".equals(format)) {
				res.reset();
			}
 
			res.addHeader("Content-Disposition", "attachment; filename=" + filename);
 
			if ("csv".equals(format)) {
				res.getWriter().append((String) model.get(MODEL_CSV));
			} else {
 
				if ("xlsx".equals(format)) {
					res.setContentType(MimetypeMap.MIMETYPE_OPENXML_SPREADSHEET);
				} else {
					res.setContentType(MimetypeMap.MIMETYPE_EXCEL);
				}
 
				byte[] excel = (byte[]) model.get(MODEL_EXCEL);
				res.getOutputStream().write(excel);
			}
		}
	}

}