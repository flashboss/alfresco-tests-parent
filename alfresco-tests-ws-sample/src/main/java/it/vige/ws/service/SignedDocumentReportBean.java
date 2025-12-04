package it.vige.ws.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean class for data transfer.
 * 
 * @author vige
 */
public class SignedDocumentReportBean {

	/**
	 * Is overall verified.
	 *
	 * @return the boolean
	 */
	public boolean isOverallVerified() {
		return true;
	}

	/**
	 * Get note report list.
	 *
	 * @return the list
	 */
	public List<NoteReportBean> getNoteReportList() {
		/**
		 * Get note report list.
		 *
		 * @return the list
		 */
		return new ArrayList<NoteReportBean>();
	}

	/**
	 * Get signature format.
	 *
	 * @return the string
	 */
	public String getSignatureFormat() {
		return "";
	}
}
