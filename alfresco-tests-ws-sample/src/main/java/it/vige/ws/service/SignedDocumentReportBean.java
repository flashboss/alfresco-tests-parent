package it.vige.ws.service;

import java.util.ArrayList;
import java.util.List;

/**
 * SignedDocumentReportBean implementation for testing purposes.
 *
 * @author vige
 */
public class SignedDocumentReportBean {

	/**
	 * Is overall verified.
	 *
	 * @return the result
	 */
	public boolean isOverallVerified() {
		return true;
	}

	/**
	 * Get note report list.
	 *
	 * @return the result
	 */
	public List<NoteReportBean> getNoteReportList() {
		return new ArrayList<NoteReportBean>();
	}
	
	/**
	 * Get signature format.
	 *
	 * @return the result
	 */
	public String getSignatureFormat() {
		return "";
	}
}
