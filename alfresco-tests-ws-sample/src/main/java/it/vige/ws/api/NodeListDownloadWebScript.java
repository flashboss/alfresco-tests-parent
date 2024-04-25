package it.vige.ws.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.model.ApplicationModel;
import org.alfresco.model.ContentModel;
import org.alfresco.model.ForumModel;
import org.alfresco.repo.content.MimetypeMap;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.ContentData;
import org.alfresco.service.cmr.repository.ContentIOException;
import org.alfresco.service.cmr.repository.ContentReader;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchParameters;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.security.PersonService;
import org.alfresco.service.namespace.NamespaceException;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.alfresco.util.Pair;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tika.parser.ParsingReader;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptException;
import org.springframework.extensions.webscripts.WebScriptRequest;

import it.vige.ws.utils.OpenDataCommand;

/**
 * Data List Download
 * 
 * Exports the contents of a Data List as an Excel file
 * 
 * @author Nick Burch, Jan Pfitzner
 */
public class NodeListDownloadWebScript extends DeclarativeSpreadsheetWebScript
// implements InitializingBean
{
	// Logger
	private static final Log logger = LogFactory.getLog(NodeListDownloadWebScript.class);

	private NodeService nodeService;
	private ContentService contentService;
	private OpenDataCommand openDataCommand;

	private NamespaceService namespaceService;
	private PersonService personService;
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

	/**
	 * @param contentService
	 *                       the contentService to set
	 */
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	/**
	 * @param personService
	 *                      the personService to set
	 */
	public void setPersonService(PersonService personService) {
		this.personService = personService;
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

	@SuppressWarnings("deprecation")
	@Override
	protected Sheet populateBody(Object resource, Workbook workbook, Sheet sheet, List<QName> properties, Map<String,CellStyle> style)
			throws IOException {
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
						case "{openDataCommand}getPrimoFirmatario": {
							val = openDataCommand.getPrimoFirmatario(item);
							break;
						}
						case "{openDataCommand}getTuttiFirmatari": {
							val = openDataCommand.getTuttiFirmatari(item);
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
						case "{openDataCommand}getRelatore": {
							val = openDataCommand.getRelatore(item);
							break;
						}
						case "{openDataCommand}getDataNominaRelatore": {
							val = openDataCommand.getDataNominaRelatore(item);
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
						Set<QName> qnames = new HashSet<QName>(1, 1.0f);
						qnames.add(prop);
						List<ChildAssociationRef> childAssocs = nodeService.getChildAssocs(item, qnames);
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
						} else if (childAssocs.size() > 0) {
							StringBuffer text = new StringBuffer();
							for (ChildAssociationRef childAssociationRef : childAssocs) {
								NodeRef child = childAssociationRef.getChildRef();
								QName type = nodeService.getType(child);
								if (type.equals(ForumModel.TYPE_FORUM)) {
									List<ChildAssociationRef> topics = nodeService.getChildAssocs(child);
									if (topics.size() > 0) {
										ChildAssociationRef topicRef = topics.get(0);
										List<ChildAssociationRef> comments = nodeService
												.getChildAssocs(topicRef.getChildRef());
										for (ChildAssociationRef commentChildRef : comments) {
											NodeRef commentRef = commentChildRef.getChildRef();

											ContentData data = (ContentData) nodeService.getProperty(commentRef,
													ContentModel.PROP_CONTENT);
											TemplateContentData contentData = new TemplateContentData(data,
													ContentModel.PROP_CONTENT);

											String commentString = "";
											try {
												commentString = contentData.getContentAsText(commentRef, -1);
											} catch (Exception e) {
												logger.warn("failed to extract content for nodeRef " + commentRef, e);
											}

											String creator = (String) nodeService.getProperty(commentRef,
													ContentModel.PROP_CREATOR);
											NodeRef person = personService.getPerson(creator, false);
											if (person != null) {
												creator = nodeService.getProperty(person, ContentModel.PROP_FIRSTNAME)
														+ " "
														+ nodeService.getProperty(person, ContentModel.PROP_LASTNAME);
											}
											Date created = (Date) nodeService.getProperty(commentRef,
													ContentModel.PROP_CREATED);

											text.append(creator).append(" (")
													.append(DateFormatUtils.format(created, "yyyy-MM-dd"))
													.append("):\n ");
											text.append(commentString).append("\n");
										}
									}
								}
							}
							String v = text.toString();
							c.setCellValue(v);
							c.setCellStyle(styleNewLines);

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
							int n = ((ArrayList) val).size();
							for (int i = 0; i < n; i++) {
								((ArrayList) val).get(i);
								tmpVal.append(((ArrayList) val).get(i).toString());
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
		colNum = 0;
		for (QName prop : properties) {
			try {
				sheet.autoSizeColumn(colNum);
			} catch (IllegalArgumentException e) {
				sheet.setColumnWidth(colNum, 40 * 256);
			}

			colNum++;
		}
		return sheet;
	}

	

	/**
	 * Inner class wrapping and providing access to a ContentData property.
	 * Slightly modified copy of
	 * {@link org.alfresco.repo.template.BaseContentNode.TemplateContentData}
	 */
	private class TemplateContentData {

		private ContentData contentData;
		private QName property;

		/**
		 * Constructor
		 * 
		 * @param contentData
		 *                    The ContentData object this object wraps
		 * @param property
		 *                    The property the ContentData is attached too
		 */
		public TemplateContentData(ContentData contentData, QName property) {
			this.contentData = contentData;
			this.property = property;
		}

		/**
		 * @param nodeRef
		 * @param length
		 * @return the content stream to the specified maximum length in
		 *         characters
		 */
		public String getContentMaxLength(final NodeRef nodeRef, int length) {
			ContentReader reader = contentService.getReader(nodeRef, property);

			return (reader != null && reader.exists()) ? reader.getContentString(length) : "";
		}

		/**
		 * @param nodeRef
		 * @param length
		 *                Length of the character stream to return, or -1 for all
		 * @return the binary content stream converted to text using any
		 *         available transformer if fails to convert then null will be
		 *         returned
		 */
		public String getContentAsText(final NodeRef nodeRef, int length) {
			String result = null;

			String mimetype = contentData.getMimetype();
			if (MimetypeMap.MIMETYPE_TEXT_PLAIN.equals(mimetype)) {
				result = getContentMaxLength(nodeRef, length);
			} else {
				// try to use Apache Tika Framework
				if (!MimetypeMap.MIMETYPE_HTML.equals(mimetype)) {
					result = getContentTextViaTika(nodeRef, length);
				}
				if (result == null || "".equalsIgnoreCase(result.trim())) {
					result = getContentTextViaAlfresco(nodeRef, length);
				}
			}

			return result;
		}

		private String getContentTextViaAlfresco(final NodeRef nodeRef, int length) {
			String result = null;
			// get the content reader
			ContentReader reader = contentService.getReader(nodeRef, property);

			// get the writer and set it up for text convert
			ContentWriter writer = contentService.getWriter(null, ContentModel.PROP_CONTENT, true);
			writer.setMimetype("text/plain");
			writer.setEncoding(reader.getEncoding());

			// try and transform the content
			if (contentService.isTransformable(reader, writer)) {
				contentService.transform(reader, writer);

				ContentReader resultReader = writer.getReader();
				if (resultReader != null && reader.exists()) {
					if (length != -1) {
						result = getContentString(resultReader, length);
					} else {
						result = resultReader.getContentString();
					}

				}
			}
			return result;
		}

		private String getContentTextViaTika(final NodeRef nodeRef, int length) {
			// get the content reader
			String result = null;
			ContentReader reader = contentService.getReader(nodeRef, property);

			ParsingReader parsingReader = null;
			try {
				parsingReader = new ParsingReader(reader.getContentInputStream());
				result = getContentString(parsingReader, length);

			} catch (ContentIOException e) {
				logger.error(e);
			} catch (IOException e) {
				logger.error(e);
			} finally {
				if (parsingReader != null) {
					try {
						parsingReader.close();
					} catch (IOException e) {
						logger.error(e);
					}
				}
			}
			return result;
		}

		private String getContentString(ParsingReader parsingReader, int length) throws ContentIOException {
			if (length < 0 || length > Integer.MAX_VALUE) {
				throw new IllegalArgumentException("Character count must be positive and within range");
			}
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(parsingReader);
				StringBuilder stringBuilder = new StringBuilder();
				String line = null;

				while ((line = reader.readLine()) != null && stringBuilder.length() < length) {
					stringBuilder.append(line + "\n");
				}
				return stringBuilder.toString();
			} catch (IOException e) {
				logger.error(e);
				throw new ContentIOException(
						"Failed to copy content to string: \n" + "   accessor: " + this + "\n" + "   length: " + length,
						e);
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (Throwable e) {
						logger.error(e);
					}
				}
			}

		}

		private String getContentString(ContentReader contentReader, int length) throws ContentIOException {
			if (length < 0 || length > Integer.MAX_VALUE) {
				throw new IllegalArgumentException("Character count must be positive and within range");
			}
			BufferedReader reader = null;
			try {
				String encoding = contentReader.getEncoding();
				// create a reader from the input stream
				if (encoding == null) {
					reader = new BufferedReader(new InputStreamReader(contentReader.getContentInputStream()));
				} else {
					reader = new BufferedReader(new InputStreamReader(contentReader.getContentInputStream(), encoding));
				}
				StringBuilder stringBuilder = new StringBuilder();
				String line = null;

				while ((line = reader.readLine()) != null && stringBuilder.length() < length) {
					stringBuilder.append(line + "\n");
				}
				return stringBuilder.toString();
			} catch (IOException e) {
				throw new ContentIOException(
						"Failed to copy content to string: \n" + "   accessor: " + this + "\n" + "   length: " + length,
						e);
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (Throwable e) {
						logger.error(e);
					}
				}
			}
		}
	}



	@Override
	protected Map<String, CellStyle> generateStyleFromWB(Workbook workbook) {
		DataFormat formatter = workbook.createDataFormat();
		CreationHelper createHelper = workbook.getCreationHelper();

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