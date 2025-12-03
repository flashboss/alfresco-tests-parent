package it.vige.ws.templateManager.drools;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.Map;

/**
 * Parameter class for Drools document processing.
 * Encapsulates the document, JSON data map, and converter for rule execution.
 *
 * @author lucastancapiano
 */
public class DocParam {

	/** The Word document to process. */
	private XWPFDocument doc;

	/** The JSON data map with field values. */
	private Map<String, String> jsonMap;

	/** The Drools converter implementation. */
	private DroolsConverterImpl converter;

	/**
	 * Constructs a new DocParam with the specified parameters.
	 *
	 * @param doc the Word document to process
	 * @param jsonMap the JSON data map with field values
	 * @param converter the Drools converter implementation
	 */
	public DocParam(XWPFDocument doc, Map<String, String> jsonMap, DroolsConverterImpl converter) {
		this.doc = doc;
		this.jsonMap = jsonMap;
		this.converter = converter;
	}

	/**
	 * Gets the Word document.
	 *
	 * @return the Word document
	 */
	public XWPFDocument getDoc() {
		return doc;
	}

	/**
	 * Sets the Word document.
	 *
	 * @param doc the Word document to set
	 */
	public void setDoc(XWPFDocument doc) {
		this.doc = doc;
	}

	/**
	 * Gets the JSON data map.
	 *
	 * @return the JSON data map
	 */
	public Map<String, String> getJsonMap() {
		return jsonMap;
	}

	/**
	 * Sets the JSON data map.
	 *
	 * @param jsonMap the JSON data map to set
	 */
	public void setJsonMap(Map<String, String> jsonMap) {
		this.jsonMap = jsonMap;
	}

	/**
	 * Gets the Drools converter.
	 *
	 * @return the Drools converter
	 */
	public DroolsConverterImpl getConverter() {
		return converter;
	}

	/**
	 * Sets the Drools converter.
	 *
	 * @param converter the Drools converter to set
	 */
	public void setConverter(DroolsConverterImpl converter) {
		this.converter = converter;
	}
}
