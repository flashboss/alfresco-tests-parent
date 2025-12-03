package it.vige.ws.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean representing a report for a signed document.
 * Contains verification status and signature format information.
 * 
 * @author vige
 */
public class SignedDocumentReportBean {

	public boolean isOverallVerified() {
		return true;
	}

	public List<NoteReportBean> getNoteReportList() {
		return new ArrayList<NoteReportBean>();
	}
	
	public String getSignatureFormat() {
		return "";
	}
}
