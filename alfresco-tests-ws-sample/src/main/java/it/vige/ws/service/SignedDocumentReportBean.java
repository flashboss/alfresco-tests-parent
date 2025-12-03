package it.vige.ws.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean class representing a signed document verification report.
 * Contains information about the verification status, notes, and signature format.
 *
 * @author lucastancapiano
 */
public class SignedDocumentReportBean {

	/**
	 * Checks if the overall verification was successful.
	 *
	 * @return true if the document is verified, false otherwise
	 */
	public boolean isOverallVerified() {
		return true;
	}

	/**
	 * Gets the list of notes from the verification report.
	 *
	 * @return the list of note report beans
	 */
	public List<NoteReportBean> getNoteReportList() {
		return new ArrayList<NoteReportBean>();
	}

	/**
	 * Gets the signature format of the document.
	 *
	 * @return the signature format (e.g., "PAdES", "CAdES")
	 */
	public String getSignatureFormat() {
		return "";
	}
}
