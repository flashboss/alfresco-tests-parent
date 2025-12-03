package it.vige.ws.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock implementation of the SignedDocumentReportBean class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
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
