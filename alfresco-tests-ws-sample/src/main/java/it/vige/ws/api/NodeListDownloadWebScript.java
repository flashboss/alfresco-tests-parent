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
 * WebScript for downloading node lists in various formats (CSV, XLS, XLSX).
 * Provides functionality to export Alfresco node data to spreadsheet formats.
 *
 * @author lucastancapiano
 */
public class NodeListDownloadWebScript extends DeclarativeWebScript {

	/** The logger instance. */
	private static final Log logger = LogFactory.getLog(NodeListDownloadWebScript.class);

	/** Model key for CSV output. */
	public static final String MODEL_CSV = "csv";

	/** Model key for Excel output. */
	public static final String MODEL_EXCEL = "excel";

	/** Override headings for export. */
	private String[] overHeadings;

	/** Base filename for exports. */
	private String filenameBase;

	/** Default skip count. */
	private String skipCountDef;

	/** Default max lines. */
	private String maxLinesDef;

	/** Default max items. */
	private String maxItemsDef;

	/** The node service. */
	private NodeService nodeService;

	/** The open data command. */
	private OpenDataCommand openDataCommand;

	/** The namespace service. */
	private NamespaceService namespaceService;

	/** Model properties for export. */
	private String[] modelProperties;

	/** Date format for open data. */
	private String openDataDateFormat;

	/** List separator for multi-value fields. */
	private String listSeparator;

	/** The search service. */
	private SearchService searchService;

	/**
	 * Sets the override headings.
	 *
	 * @param headingsString the headings array to set
	 */
	public void setOverHeadings(String[] headingsString) {
		this.overHeadings = headingsString;
	}

	/**
	 * Gets the override headings.
	 *
	 * @return the override headings array
	 */
	public String[] getOverHeadings() {
		return overHeadings;
	}

	/**
	 * Sets the default skip count.
	 *
	 * @param skipCountDef the skip count to set
	 */
	public void setSkipCountDef(String skipCountDef) {
		this.skipCountDef = skipCountDef;
	}

	/**
	 * Sets the default max lines.
	 *
	 * @param maxLinesDef the max lines to set
	 */
	public void setMaxLinesDef(String maxLinesDef) {
		this.maxLinesDef = maxLinesDef;
	}

	/**
	 * Sets the default max items.
	 *
	 * @param maxItemsDef the max items to set
	 */
	public void setMaxItemsDef(String maxItemsDef) {
		this.maxItemsDef = maxItemsDef;
	}

	/**
	 * Sets the search service.
	 *
	 * @param searchService the search service to set
	 */
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	/**
	 * Sets the model properties.
	 *
	 * @param modelPropertiesString the model properties array to set
	 */
	public void setModelProperties(String[] modelPropertiesString) {
		this.modelProperties = modelPropertiesString;
	}

	/**
	 * Sets the open data command.
	 *
	 * @param openDataCommand the open data command to set
	 */
	public void setOpenDataCommand(OpenDataCommand openDataCommand) {
		this.openDataCommand = openDataCommand;
	}

	/**
	 * Default constructor.
	 */
	public NodeListDownloadWebScript() {
		this.filenameBase = "DataListExport";
	}

	/**
	 * Sets the open data date format.
	 *
	 * @param openDataDateFormat the date format to set
	 */
	public void setOpenDataDateFormat(String openDataDateFormat) {
		this.openDataDateFormat = openDataDateFormat;
	}

	/**
	 * Sets the node service.
	 *
	 * @param nodeService the node service to set
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * Sets the namespace service.
	 *
	 * @param namespaceService the namespace service to set
	 */
	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	/**
	 * Sets the list separator.
	 *
	 * @param listSeparator the list separator to set
	 */
	public void setListSeparator(String listSeparator) {
		this.listSeparator = listSeparator;
	}

	/**
	 * Identifies resources based on the request parameters.
	 *
	 * @param format the output format
	 * @param req the webscript request
	 * @param maxItems the maximum items to return
	 * @param skypCount the number of items to skip
	 * @return a list of node references
	 */
	protected List<NodeRef> identifyResource(String format, WebScriptRequest req, int maxItems, int skypCount) {
		List<NodeRef> result = new ArrayList<NodeRef>();
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
			result.addAll(results.getNodeRefs());
		} finally {
			if (results != null) {
				results.close();
			}
		}
		return result;
	}

	/**
	 * Checks if HTML fallback is allowed.
	 *
	 * @return false, HTML is not supported
	 */
	protected boolean allowHtmlFallback() {
		return false;
	}

	/**
	 * Builds properties list for the spreadsheet header.
	 *
	 * @param resource the resource object
	 * @param format the output format
	 * @param req the webscript request
	 * @return a list of QName and required flag pairs
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
	 * Populates the CSV body with data.
	 *
	 * @param resource the resource object
	 * @param csv the CSV printer
	 * @param properties the list of properties
	 * @throws IOException if an I/O error occurs
	 */
	protected void populateBody(Object resource, CSVPrinter csv, List<QName> properties) throws IOException {
		throw new WebScriptException(Status.STATUS_BAD_REQUEST, "CSV not currently supported");
	}

	/**
	 * Populates the Excel sheet body with data.
	 *
	 * @param resource the resource object (list of NodeRefs)
	 * @param workbook the Excel workbook
	 * @param sheet the Excel sheet
	 * @param properties the list of properties
	 * @param style the cell styles map
	 * @return the populated sheet
	 * @throws IOException if an I/O error occurs
	 */
	protected Sheet populateBody(Object resource, Workbook workbook, Sheet sheet, List<QName> properties, Map<String, CellStyle> style)
			throws IOException {
		@SuppressWarnings("unchecked")
		List<NodeRef> items = (List<NodeRef>) resource;
		CellStyle styleInt = style.get("styleInt");
		CellStyle styleDouble = style.get("styleDouble");
		CellStyle styleDate = style.get("styleDate");
		CellStyle styleNewLines = style.get("styleNewLines");

		int rowNum = sheet.getLastRowNum() + 1, colNum = 0;
		for (NodeRef item : items) {
			if (openDataCommand.checkNodeCoreProps(item)) {
				Row r = sheet.createRow(rowNum);

				colNum = 0;
				for (QName prop : properties) {
					Cell c = r.createCell(colNum);

					Serializable val = nodeService.getProperty(item, prop);

					if (val == null) {
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
							c.setCellType(Cell.CELL_TYPE_BLANK);
						}
					} else {
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
							System.err.println("TODO: handle " + val.getClass().getName() + " - " + val);
						}
					}

					colNum++;
				}

				rowNum++;
			}
		}

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
	 * Generates cell styles for the workbook.
	 *
	 * @param workbook the Excel workbook
	 * @return a map of style names to CellStyle objects
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
		Map<String, CellStyle> style = new HashMap<>();
		style.put("styleInt", styleInt);
		style.put("styleDate", styleDate);
		style.put("styleDouble", styleDouble);
		style.put("styleNewLines", styleNewLines);
		return style;
	}

	/**
	 * Executes the webscript.
	 *
	 * @param req the webscript request
	 * @param status the status object
	 * @return the model map
	 */
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", Boolean.TRUE);

		String format = req.getFormat();
		if ("csv".equals(format) || "xls".equals(format) || "xlsx".equals(format) || "excel".equals(format)) {

			try {
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
	 * Generates the spreadsheet output.
	 *
	 * @param format the output format (csv, xls, xlsx)
	 * @param req the webscript request
	 * @param status the status object
	 * @param model the model map
	 * @throws IOException if an I/O error occurs
	 */
	public void generateSpreadsheet(String format, WebScriptRequest req, Status status,
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
		int maxItems = Integer.parseInt(maxItemsDef);
		int maxLines = Integer.parseInt(maxLinesDef);
		if (req.getParameter("maxItems") != null) {
			maxItems = Integer.parseInt(req.getParameter("maxItems"));
		}
		if (req.getParameter("skipCount") != null) {
			skipCount = Integer.parseInt(req.getParameter("skipCount"));
		}
		if (req.getParameter("maxLines") != null) {
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
				iteration++;
				if (logger.isDebugEnabled()) {
					logger.debug("Executing iteration of resources with num: " + num + " and skipCount: " + skipCount);
					if (iteration == maxIterations)
						logger.debug("Exiting because max iteration is reached");
				}
				populateBody(resource, csv, properties);

			} while (num == maxItems & iteration < maxIterations);

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

			Map<String, CellStyle> style = generateStyleFromWB(wb);
			do {
				resource = identifyResource(format, req, maxItems, skipCount);
				num = resource.size();
				if (logger.isDebugEnabled()) {
					logger.debug("Executing iteration of resources with num: " + num + " and skipCount: " + skipCount);
					if (iteration == maxIterations)
						logger.debug("Exiting because iteration is: " + iteration);
				}
				skipCount += num;
				iteration++;
				sheet = populateBody(resource, wb, sheet, properties, style);
			} while (num == maxItems & iteration < maxIterations);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			model.put(MODEL_EXCEL, baos.toByteArray());
		}
	}

	/**
	 * Creates template parameters for the webscript response.
	 *
	 * @param req the webscript request
	 * @param res the webscript response
	 * @param customParams the custom parameters
	 * @return the model map with template parameters
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

	/**
	 * Helper class for writing Excel output to the response.
	 */
	public static class WriteExcel {
		/** The output format. */
		private String format;

		/** The base filename. */
		private String filenameBase;

		/** The webscript response. */
		private WebScriptResponse res;

		/** The model map. */
		private Map<String, Object> model;

		/**
		 * Constructs a new WriteExcel instance.
		 *
		 * @param res the webscript response
		 * @param model the model map
		 * @param format the output format
		 * @param filenameBase the base filename
		 */
		private WriteExcel(WebScriptResponse res, Map<String, Object> model, String format, String filenameBase) {
			this.res = res;
			this.model = model;
			this.format = format;
			this.filenameBase = filenameBase;
		}

		/**
		 * Writes the spreadsheet to the response.
		 *
		 * @throws IOException if an I/O error occurs
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
