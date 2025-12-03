package it.vige.ws.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Mock implementation of the SignService class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class SignService {

	private final Log logger = LogFactory.getLog(SignService.class);

	public SignedDocumentReportBean verifica(byte[] contentToVerify) throws Exception {

		logger.debug("start verifica");

		return new SignedDocumentReportBean();
	}

	public InputStream signPADES(InputStream inputStream, String username, String password) throws Exception {
		logger.debug("start single sign");
		return new ByteArrayInputStream(new byte[0]);
	}

	/**
	 *
	 * @param inputStream
	 * @return
	 */
	public byte[] applicaMarcaTemporale(byte[] inputByte) throws Exception {

		return new byte[0];
	}

}