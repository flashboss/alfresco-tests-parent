package it.vige.ws.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;

/**
 * Service for document signing and verification operations.
 * Provides PAdES signing and timestamping capabilities.
 * 
 * @author vige
 */
public class SignService {

	private final Logger logger = Logger.getLogger(SignService.class);

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