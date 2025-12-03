package it.vige.ws.templateManager.drools;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.Map;

/**
* Mock implementation of the DocParam class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class DocParam {

/**
* The doc.
 */
	private XWPFDocument doc;
	private Map<String,String> jsonMap;

/**
* The converter.
 */
	private DroolsConverterImpl converter;

/**
* Constructs a new DocParam instance.
* @param doc the doc
* @param converter the converter
 */
	public DocParam(XWPFDocument doc, Map<String,String> jsonMap, DroolsConverterImpl converter) {
		this.doc = doc;
		this.jsonMap = jsonMap;
		this.converter = converter;
	}

/**
* Gets the doc.
* @return the result
 */
	public XWPFDocument getDoc() {
		return doc;
	}

/**
* Sets the doc.
* @param doc the doc
 */
	public void setDoc(XWPFDocument doc) {
		this.doc = doc;
	}

	public Map<String, String> getJsonMap() {
		return jsonMap;
	}

/**
* Sets the json map.
 */
	public void setJsonMap(Map<String, String> jsonMap) {
		this.jsonMap = jsonMap;
	}

/**
* Gets the converter.
* @return the result
 */
	public DroolsConverterImpl getConverter() {
		return converter;
	}

/**
* Sets the converter.
* @param converter the converter
 */
	public void setConverter(DroolsConverterImpl converter) {
		this.converter = converter;
	}
}
