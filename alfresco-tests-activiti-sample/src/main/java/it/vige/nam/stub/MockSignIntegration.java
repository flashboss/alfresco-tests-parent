package it.vige.nam.stub;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Mock implementation of SignIntegration for testing purposes.
 * 
 * @author vige
 */
public class MockSignIntegration implements SignIntegration {

	private final static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	/**
	 * Sign pkcs1.
	 *
	 * @param credentials the credentials
	 * @param hash the hash
	 * @param preferences the preferences
	 * @return the byte[]
	 */


	@Override
	public byte[] signPkcs1(Credentials credentials, byte[] hash, SignPreferences preferences) throws WSException {
		return null;
	}	/**
	 * Send otp by s m s.
	 *
	 * @param credentials the credentials
	 */


	@Override
	public void sendOtpBySMS(Credentials credentials) throws WSException {
		
	}	/**
	 * Sign c ad e s.
	 *
	 * @param username the username
	 * @param password the password
	 * @param buffer the buffer
	 * @param cAdESPreferences the c ad e s preferences
	 * @return the byte[]
	 */


	@Override
	public byte[] signCAdES(String username, String password, byte[] buffer, CAdESPreferences cAdESPreferences)
			throws WSException {
		return null;
	}	/**
	 * Sign c ad e s remote.
	 *
	 * @param credentials the credentials
	 * @param buffer the buffer
	 * @param cAdESPreferences the c ad e s preferences
	 * @return the byte[]
	 */


	@Override
	public byte[] signCAdESRemote(Credentials credentials, byte[] buffer, CAdESPreferences cAdESPreferences)
			throws WSException {
		return null;
	}	/**
	 * Change password.
	 *
	 * @param securityCode the security code
	 * @param username the username
	 * @param password the password
	 * @param newPassword the new password
	 */


	@Override
	public void changePassword(String securityCode, String username, String password, String newPassword)
			throws WSException {
		
	}	/**
	 * Verify on date.
	 *
	 * @param signedContent the signed content
	 * @param verifyDate the verify date
	 * @return the signed document report bean
	 */


	@Override
	public SignedDocumentReportBean verifyOnDate(byte[] signedContent, XMLGregorianCalendar verifyDate)
			throws WSException {
		return null;
	}	/**
	 * Disable.
	 *
	 * @param securityCode the security code
	 * @param username the username
	 * @param password the password
	 */


	@Override
	public void disable(String securityCode, String username, String password) throws WSException {
		
	}	/**
	 * Open session.
	 *
	 * @param credentials the credentials
	 * @return the string
	 */


	@Override
	public String openSession(Credentials credentials) throws WSException {
		return null;
	}	/**
	 * Check first factor.
	 *
	 * @param credentials the credentials
	 */


	@Override
	public void checkFirstFactor(Credentials credentials) throws WSException {
		
	}	/**
	 * Sign p ad e s remote.
	 *
	 * @param credentials the credentials
	 * @param buffer the buffer
	 * @param pAdESPreferences the p ad e s preferences
	 * @return the byte[]
	 */


	@Override
	public byte[] signPAdESRemote(Credentials credentials, byte[] buffer, PAdESPreferences pAdESPreferences)
			throws WSException {
		return null;
	}

	@Override
	public List<byte[]> signCAdESArrayListWithMultiPreferences(Credentials credentials, List<byte[]> bufferList,
			List<CAdESPreferences> cAdESPreferences) throws WSException {
		return null;
	}

	@Override
	public List<byte[]> signPAdESArrayListWithCredentials(Credentials credentials, List<byte[]> bufferList,
			PAdESPreferences pAdESPreferences) throws WSException {
		return null;
	}

	@Override
	public List<byte[]> signCAdESArrayListWithCredentials(Credentials credentials, List<byte[]> bufferList,
			CAdESPreferences cAdESPreferences) throws WSException {
		return null;
	}	/**
	 * Sign with credentials.
	 *
	 * @param credentials the credentials
	 * @param buffer the buffer
	 * @param adESPreferences the ad e s preferences
	 * @return the byte[]
	 */


	@Override
	public byte[] signWithCredentials(Credentials credentials, byte[] buffer, SignPreferences adESPreferences)
			throws WSException {

	/** The buffer str. */
		String bufferStr = new String(buffer);

	/** The decoded buffer str. */
		String decodedBufferStr = "";
		try {
			decodedBufferStr = com.adobe.xmp.impl.Base64.decode(bufferStr);
		} catch (IllegalArgumentException ex) {
			decodedBufferStr = "";
		}
		if (decodedBufferStr != null && decodedBufferStr.equals(Result.INVALID.name())) {
			/** The message. */
			String message = "Il mock simula un errore nel contenuto del file";
			WsFaultBean wsFaultBean = new WsFaultBean();
			wsFaultBean.setError(2);
			wsFaultBean.setMessage(message);
			throw new WSException(message, wsFaultBean);
		}
		return bufferStr.getBytes();
	}

	@Override
	public List<byte[]> signXAdESArrayListWithMultiPreferences(Credentials credentials, List<byte[]> bufferList,
			List<XAdESPreferences> xAdESPreferences) throws WSException {
		return null;
	}

	@Override
	public List<byte[]> signArrayListWithCredentials(Credentials credentials, List<byte[]> bufferList,
			SignPreferences adESPreferences) throws WSException {
		return null;
	}	/**
	 * Get signatures.
	 *
	 * @param credentials the credentials
	 * @return the long
	 */


	@Override
	public long getSignatures(Credentials credentials) throws WSException {
		return 0;
	}	/**
	 * Sign.
	 *
	 * @param username the username
	 * @param password the password
	 * @param buffer the buffer
	 * @param adESPreferences the ad e s preferences
	 * @return the byte[]
	 */


	@Override
	public byte[] sign(String username, String password, byte[] buffer, SignPreferences adESPreferences)
			throws WSException {
		return null;
	}	/**
	 * Timestamp t s r verify.
	 *
	 * @param tsr the tsr
	 * @param content the content
	 * @return the timestamp report bean
	 */


	@Override
	public TimestampReportBean timestampTSRVerify(byte[] tsr, byte[] content) throws WSException {
		return null;
	}

	@Override
	public List<byte[]> signXAdESArrayListWithCredentials(Credentials credentials, List<byte[]> bufferList,
			XAdESPreferences xAdESPreferences) throws WSException {
		return null;
	}	/**
	 * Get available signatures.
	 *
	 * @param credentials the credentials
	 * @return the long
	 */


	@Override
	public long getAvailableSignatures(Credentials credentials) throws WSException {
		return 0;
	}	/**
	 * Sign x ad e s.
	 *
	 * @param username the username
	 * @param password the password
	 * @param buffer the buffer
	 * @param xAdESPreferences the x ad e s preferences
	 * @return the byte[]
	 */


	@Override
	public byte[] signXAdES(String username, String password, byte[] buffer, XAdESPreferences xAdESPreferences)
			throws WSException {
		return null;
	}	/**
	 * Sign x ad e s remote.
	 *
	 * @param credentials the credentials
	 * @param buffer the buffer
	 * @param xAdESPreferences the x ad e s preferences
	 * @return the byte[]
	 */


	@Override
	public byte[] signXAdESRemote(Credentials credentials, byte[] buffer, XAdESPreferences xAdESPreferences)
			throws WSException {
		return null;
	}

	@Override
	public List<byte[]> signPAdESArrayList(String username, String password, List<byte[]> bufferList,
			PAdESPreferences pAdESPreferences) throws WSException {
		return null;
	}	/**
	 * Enable remote.
	 *
	 * @param credentials the credentials
	 */


	@Override
	public void enableRemote(Credentials credentials) throws WSException {
		
	}

	@Override
	public List<byte[]> signXAdESArrayList(String username, String password, List<byte[]> bufferList,
			XAdESPreferences xAdESPreferences) throws WSException {
		return null;
	}	/**
	 * Timestamp t s d verify.
	 *
	 * @param tsd the tsd
	 * @return the list
	 */


			/** The content. */
	@Override
	public List<TimestampReportBean> timestampTSDVerify(byte[] tsd) throws WSException {
		TimestampReportBean timestampReportBean = new TimestampReportBean();
		timestampReportBean.setContent(tsd);
		try {
			String content = new String(tsd);
			Result result = Result.valueOf(content);
			timestampReportBean.setSignatureVerificationStatus(result);
			if (result == Result.INVALID)
				throw new WSException("L'errore deriva da un test unitario");
		} catch (IllegalArgumentException ex) {
			timestampReportBean.setSignatureVerificationStatus(Result.VALID);
		}
		return Arrays.asList(new TimestampReportBean[] { timestampReportBean });
	}

	@Override
	public List<byte[]> signPAdESArrayListWithMultiPreferences(Credentials credentials, List<byte[]> bufferList,
			List<PAdESPreferences> pAdESPreferences) throws WSException {
		return null;
	}

	@Override
	public List<byte[]> signArrayList(String username, String password, List<byte[]> bufferList,
			SignPreferences adESPreferences) throws WSException {
		return null;
	}	/**
	 * Sign p ad e s.
	 *
	 * @param username the username
	 * @param password the password
	 * @param buffer the buffer
	 * @param pAdESPreferences the p ad e s preferences
	 * @return the byte[]
	 */


	@Override
	public byte[] signPAdES(String username, String password, byte[] buffer, PAdESPreferences pAdESPreferences)
			throws WSException {
		return null;
	}	/**
	 * Sign pkcs1 array list.
	 *
	 * @param credentials the credentials
	 * @param hashList the hash list
	 * @param preferences the preferences
	 * @return the list
	 */


	@Override
	public List<byte[]> signPkcs1ArrayList(Credentials credentials, List<byte[]> hashList, SignPreferences preferences)
			throws WSException {
		return null;
	}	/**
	 * Get certificate.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the byte[]
	 */


	@Override
	public byte[] getCertificate(String username, String password) throws WSException {
		return null;
	}	/**
	 * Change password remote.
	 *
	 * @param credentials the credentials
	 * @param newPassword the new password
	 */


	@Override
	public void changePasswordRemote(Credentials credentials, String newPassword) throws WSException {
		
	}

	@Override
	public List<byte[]> signCAdESArrayList(String username, String password, List<byte[]> bufferList,
			CAdESPreferences cAdESPreferences) throws WSException {
		return null;
	}	/**
	 * Enable.
	 *
	 * @param securityCode the security code
	 * @param username the username
	 * @param password the password
	 */


	@Override
	public void enable(String securityCode, String username, String password) throws WSException {
		
	}	/**
	 * Check o t p.
	 *
	 * @param credentials the credentials
	 */


	@Override
	public void checkOTP(Credentials credentials) throws WSException {
		
	}	/**
	 * Verify.
	 *
	 * @param signedContent the signed content
	 * @return the signed document report bean
	 */


	@Override
	public SignedDocumentReportBean verify(byte[] signedContent) throws WSException {
		return null;
	}	/**
	 * Close session.
	 *
	 * @param credentials the credentials
	 */


	@Override
	public void closeSession(Credentials credentials) throws WSException {
		
	}	/**
	 * Disable remote.
	 *
	 * @param credentials the credentials
	 */


	@Override
	public void disableRemote(Credentials credentials) throws WSException {
		
	}	/**
	 * Verify with preferences.
	 *
	 * @param signedContent the signed content
	 * @param preferences the preferences
	 * @return the signed document report bean
	 */


	@Override
	public SignedDocumentReportBean verifyWithPreferences(byte[] signedContent, VerifyPreferences preferences)
			throws WSException {
		SignedDocumentReportBean bean = new SignedDocumentReportBean();
		bean.setPlainDocument(new byte[] {});
		SignatureReportBean report = new SignatureReportBean();
		Result result = Result.VALID;
		try {
			result = Result.valueOf(new String(signedContent));
		} catch (IllegalArgumentException ex) {
		}
		try {
			report.setSignerCertificateNotBefore(dateToCalendar(dateFormat.parse("10/10/2000")));
			bean.setVerificationDate(dateToCalendar(dateFormat.parse("12/10/2000")));
			if (result == Result.INFORMATION)
				report.setSignerCertificateNotAfter(dateToCalendar(dateFormat.parse("14/10/1999")));
			else
				report.setSignerCertificateNotAfter(dateToCalendar(dateFormat.parse("14/10/2000")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		bean.getSignatureReportList().add(report);
		if (result.equals(Result.INVALID)) {
			bean.getSignatureReportList().add(new SignatureReportBean());
			bean.getSignatureReportList().add(new SignatureReportBean());
		}
		bean.setNrOfSignatures(bean.getSignatureReportList().size());
		return bean;
	}	/**
	 * Timestamp.
	 *
	 * @param content the content
	 * @param preferences the preferences
	 * @return the byte[]
	 */


	@Override
	public byte[] timestamp(byte[] content, TimeStampPreferences preferences) throws WSException {
		return null;
	}

	/**
	 * Date to calendar.
	 *
	 * @param date the date
	 * @return the x m l gregorian calendar
	 */
	private XMLGregorianCalendar dateToCalendar(Date date) {
		XMLGregorianCalendar xmlDate = null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);

		try {
			xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlDate;
	}

}
