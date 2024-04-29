package it.vige.ws.api;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ApplicationModel;
import org.alfresco.model.ContentModel;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptException;
import org.springframework.extensions.webscripts.WebScriptRequest;

import it.vige.ws.utils.OpenDataCommand;

public class NodeListDownloadWebScript extends DeclarativeSpreadsheetWebScript
// implements InitializingBean
{
	// Logger
	private static final Log logger = LogFactory.getLog(NodeListDownloadWebScript.class);

	private NodeService nodeService;
	private OpenDataCommand openDataCommand;

	private NamespaceService namespaceService;
	private String[] modelProperties;
	private String openDataDateFormat;
	private String listSeparator;

	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public void setModelProperties(String[] modelPropertiesString) {
		this.modelProperties = modelPropertiesString;
	}

	public void setOpenDataCommand(OpenDataCommand openDataCommand) {
		this.openDataCommand = openDataCommand;
	}

	public NodeListDownloadWebScript() {
		this.filenameBase = "DataListExport";
	}

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

	public void setListSeparator(String listSeparator) {
		this.listSeparator = listSeparator;
	}

	/**
	 * Identify the datalist
	 */
	@Override
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
	@Override
	protected boolean allowHtmlFallback() {
		return false;
	}

	/**
	 * Fetch the properties, in the requested order, from the data list
	 * definition
	 */
	@Override
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

	@Override
	protected void populateBody(Object resource, CSVPrinter csv, List<QName> properties) throws IOException {
		throw new WebScriptException(Status.STATUS_BAD_REQUEST, "CSV not currently supported");
	}

	@Override
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
			//Controllo per evitare template senza numero atto che mandano in errore il webscript
			if (openDataCommand.checkNodeCoreProps(item)) {
				Row r = sheet.createRow(rowNum);

				colNum = 0;
				for (QName prop : properties) {
					Cell c = r.createCell(colNum);

					Serializable val = nodeService.getProperty(item, prop);

					switch (prop.toString()) {
						case "{openDataCommand}getTipoAtto": {
							val = openDataCommand.getTipoAtto(item);
							break;
						}
						case "{openDataCommand}getIdAtto": {
							val = openDataCommand.getIdAtto(item);
							break;
						}
						case "{openDataCommand}getNumeroAtto": {
							val = openDataCommand.getNumeroAtto(item);
							break;
						}
						case "{openDataCommand}tipoIniziativa": {
							val = openDataCommand.getTipoIniziativa(item);
							break;
						}
						case "{openDataCommand}linkTestoAttoComReferente": {
							val = openDataCommand.getLinkTestoAttoComReferente(item);
							break;
						}
						case "{openDataCommand}linkTestoRelazioneIllustrativa": {
							val = openDataCommand.getLinkTestoRelazioneIllustrativa(item);
							break;
						}
						case "{openDataCommand}linkTestoSchedaIstruttoria": {
							val = openDataCommand.getLinkTestoSchedaIstruttoria(item);
							break;
						}
						case "{openDataCommand}getLinkVotoFinaleAula": {
							val = openDataCommand.getLinkVotoFinaleAula(item);
							break;
						}
						case "{openDataCommand}linkAtto": {
							val = openDataCommand.getLinkAtto(item);
							break;
						}
						case "{openDataCommand}getPrimoPromotore": {
							val = openDataCommand.getPrimoPromotore(item);
							break;
						}
						case "{openDataCommand}getTuttiPromotori": {
							val = openDataCommand.getTuttiPromotori(item);
							break;
						}
						case "{openDataCommand}getAbbinamenti": {
							val = openDataCommand.getAbbinamenti(item);
							break;
						}
						case "{openDataCommand}getEsitoVotazioneCommissioneReferente": {
							val = openDataCommand.getEsitoVotazioneCommissioneReferente(item);
							break;
						}
						case "{openDataCommand}getDataVotazioneCommissioneReferente": {
							val = openDataCommand.getDataVotazioneCommissioneReferente(item);
							break;
						}
						case "{openDataCommand}getEsitoVotazioneAula": {
							val = openDataCommand.getEsitoVotazioneAula(item);
							break;
						}
						case "{openDataCommand}getDataVotazioneAula": {
							val = openDataCommand.getDataVotazioneAula(item);
							break;
						}
						case "{openDataCommand}getNumeroDcrPassaggioAula": {
							val = openDataCommand.getNumeroDcrPassaggioAula(item);
							break;
						}

						case "{openDataCommand}getIdsLr": {
							val = openDataCommand.getIdsLr(item);
							break;
						}

					}

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

	@Override
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

}