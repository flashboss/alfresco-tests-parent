package it.vige.ws.templateManager.drools;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.Map;

/**
 * DocParam implementation for testing purposes.
 *
 * @author vige
 */
public class DocParam {

	/** The doc. */
	private XWPFDocument doc;
	/** The json map. */
	private Map<String,String> jsonMap;

	/** The converter. */
	private DroolsConverterImpl converter;

	/**
	 * Constructs a new DocParam.
	 *
	 * @param doc the doc
	 * @param jsonMap the json map
	 * @param converter the converter
	 */
	public DocParam(XWPFDocument doc, Map<String,String> jsonMap, DroolsConverterImpl converter) {
		this.doc = doc;
		this.jsonMap = jsonMap;
		this.converter = converter;
	}

	/**
	 * Get doc.
	 *
	 * @return the result
	 */
	public XWPFDocument getDoc() {
		return doc;
	}

	/**
	 * Set doc.
	 *
	 * @param doc the doc
	 */
	public void setDoc(XWPFDocument doc) {
		this.doc = doc;
	}

	/**
	 * Get json map.
	 *
	 */
	public Map<String, String> getJsonMap() {
		return jsonMap;
	}

	/**
	 * Set json map.
	 *
	 * @param jsonMap the json map
	 */
	public void setJsonMap(Map<String, String> jsonMap) {
		this.jsonMap = jsonMap;
	}

	/**
	 * Get converter.
	 *
	 * @return the result
	 */
	public DroolsConverterImpl getConverter() {
		return converter;
	}

	/**
	 * Set converter.
	 *
	 * @param converter the converter
	 */
	public void setConverter(DroolsConverterImpl converter) {
		this.converter = converter;
	}
}
