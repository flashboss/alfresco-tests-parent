package it.vige.ws.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.Pair;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptException;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.WebScriptResponse;

/**
 * Parent of Declarative Webscripts that generate Excel files, usually based on
 * some sort of dictionary model.
 * 
 * @author Nick Burch, Jan Pfitzner
 */
public abstract class DeclarativeSpreadsheetWebScript extends DeclarativeWebScript {

	// Logger
	private static final Log logger = LogFactory.getLog(DeclarativeSpreadsheetWebScript.class);

	public static final String MODEL_CSV = "csv";
	public static final String MODEL_EXCEL = "excel";

	protected DictionaryService dictionaryService;
	protected String[] overHeadings;
	protected String[] overDescriptions;

	protected String filenameBase;

	protected String skipCountDef;
	protected String maxLinesDef;
	protected String maxItemsDef;
	
	public void setOverHeadings(String[] headingsString) {
		this.overHeadings = headingsString;
	}

	public String[] getOverHeadings() {
		return overHeadings;
	}

	public void setOverDescriptions(String[] descriptionsString) {
		this.overDescriptions = descriptionsString;
	}

	public void setSkipCountDef(String skipCountDef) {
		this.skipCountDef = skipCountDef;
	}

	public void setMaxLinesDef(String maxLinesDef) {
		this.maxLinesDef = maxLinesDef;
	}

	public void setMaxItemsDef(String maxItemsDef) {
		this.maxItemsDef = maxItemsDef;
	}

	/**
	 * @param dictionaryService
	 *            the DictionaryService to set
	 */
	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	/**
	 * Identifies the resource for the webscript.
	 */
	//protected abstract Object identifyResource(String format, WebScriptRequest req);

	/**
	 * Identifies the resource for the webscript.
	 */
	protected abstract List<NodeRef> identifyResource(String format, WebScriptRequest req, int maxItems, int skypCount);

	/**
	 * If the format is requested as HTML, should an exception be raised, or
	 * should an HTML version be called?
	 */
	protected abstract boolean allowHtmlFallback();

	/**
	 * Returns the QNames of the model properties to be output in the header,
	 * and if they're required or not
	 */
	protected abstract List<Pair<QName, Boolean>> buildPropertiesForHeader(Object resource, String format,
			WebScriptRequest req);

	/**
	 * Populates the body of the Excel Workbook, once the header has been
	 * output. This is called if the format is .xls or .xlsx
	 */
	protected abstract Sheet populateBody(Object resource, Workbook workbook, Sheet sheet, List<QName> properties, Map<String,CellStyle> style)
			throws IOException;

	/**
	 * Populates the body of the CSV file, once the header has been output. This
	 * is called if the format is .csv
	 */
	protected abstract void populateBody(Object resource, CSVPrinter csv, List<QName> properties) throws IOException;

	protected abstract Map<String,CellStyle> generateStyleFromWB ( Workbook workbook);

	/**
	 * @see org.alfresco.web.scripts.DeclarativeWebScript#executeImpl(org.alfresco.web.scripts.WebScriptRequest,
	 *      org.alfresco.web.scripts.Status)
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
 
			Drawing draw = null;
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
		private String format;
		private String filenameBase;
		private WebScriptResponse res;
		private Map<String, Object> model;

		private WriteExcel(WebScriptResponse res, Map<String, Object> model, String format, String filenameBase) {
			this.res = res;
			this.model = model;
			this.format = format;
			this.filenameBase = filenameBase;
		}

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
