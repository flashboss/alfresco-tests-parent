package it.vige.ws.templateManager.drools;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.Map;

/**
 * Parameter object for Drools rule execution.
 * Contains document, JSON data map, and converter instance.
 * 
 * @author vige
 */
public class DocParam {

	private XWPFDocument doc;
	private Map<String,String> jsonMap;

	private DroolsConverterImpl converter;

	public DocParam(XWPFDocument doc, Map<String,String> jsonMap, DroolsConverterImpl converter) {
		this.doc = doc;
		this.jsonMap = jsonMap;
		this.converter = converter;
	}

	public XWPFDocument getDoc() {
		return doc;
	}

	public void setDoc(XWPFDocument doc) {
		this.doc = doc;
	}

	public Map<String, String> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, String> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public DroolsConverterImpl getConverter() {
		return converter;
	}

	public void setConverter(DroolsConverterImpl converter) {
		this.converter = converter;
	}
}
