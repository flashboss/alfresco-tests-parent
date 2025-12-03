package it.vige.ws.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean class for data transfer.
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
