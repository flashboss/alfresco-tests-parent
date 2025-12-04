package it.vige.ws.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;

/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public class SignService {

	/** The logger. */
	private final Logger logger = Logger.getLogger(SignService.class);

	/**
	 * Verifica.
	 *
	 * @param contentToVerify the content to verify
	 * @return the signed document report bean
	 */
	public SignedDocumentReportBean verifica(byte[] contentToVerify) throws Exception {

		logger.debug("start verifica");

		/**
		 * Verifica.
		 *
		 * @param contentToVerify the content to verify
		 * @return the signed document report bean
		 */
		return new SignedDocumentReportBean();
	}

	/**
	 * Sign p a d e s.
	 *
	 * @param inputStream the input stream
	 * @param username    the username
	 * @param password    the password
	 * @return the input stream
	 */
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