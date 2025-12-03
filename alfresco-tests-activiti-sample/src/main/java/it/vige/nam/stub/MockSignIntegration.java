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
 * Mock implementation of the SignIntegration web service interface.
 * Provides mock implementations for digital signature operations in test scenarios.
 * 
 * @author vige
 */
public class MockSignIntegration implements SignIntegration {

	/** Date format for parsing dates in mock operations. */
	private final static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public byte[] signPkcs1(Credentials credentials, byte[] hash, SignPreferences preferences) throws WSException {
		return null;
	}

	@Override
	public void sendOtpBySMS(Credentials credentials) throws WSException {
		
	}

	@Override
	public byte[] signCAdES(String username, String password, byte[] buffer, CAdESPreferences cAdESPreferences)
			throws WSException {
		return null;
	}

	@Override
	public byte[] signCAdESRemote(Credentials credentials, byte[] buffer, CAdESPreferences cAdESPreferences)
			throws WSException {
		return null;
	}

	@Override
	public void changePassword(String securityCode, String username, String password, String newPassword)
			throws WSException {
		
	}

	@Override
	public SignedDocumentReportBean verifyOnDate(byte[] signedContent, XMLGregorianCalendar verifyDate)
			throws WSException {
		return null;
	}

	@Override
	public void disable(String securityCode, String username, String password) throws WSException {
		
	}

	@Override
	public String openSession(Credentials credentials) throws WSException {
		return null;
	}

	@Override
	public void checkFirstFactor(Credentials credentials) throws WSException {
		
	}

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
	}

	@Override
	public byte[] signWithCredentials(Credentials credentials, byte[] buffer, SignPreferences adESPreferences)
			throws WSException {
		String bufferStr = new String(buffer);
		String decodedBufferStr = "";
		try {
			decodedBufferStr = com.adobe.xmp.impl.Base64.decode(bufferStr);
		} catch (IllegalArgumentException ex) {
			decodedBufferStr = "";
		}
		if (decodedBufferStr != null && decodedBufferStr.equals(Result.INVALID.name())) {
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
	}

	@Override
	public long getSignatures(Credentials credentials) throws WSException {
		return 0;
	}

	@Override
	public byte[] sign(String username, String password, byte[] buffer, SignPreferences adESPreferences)
			throws WSException {
		return null;
	}

	@Override
	public TimestampReportBean timestampTSRVerify(byte[] tsr, byte[] content) throws WSException {
		return null;
	}

	@Override
	public List<byte[]> signXAdESArrayListWithCredentials(Credentials credentials, List<byte[]> bufferList,
			XAdESPreferences xAdESPreferences) throws WSException {
		return null;
	}

	@Override
	public long getAvailableSignatures(Credentials credentials) throws WSException {
		return 0;
	}

	@Override
	public byte[] signXAdES(String username, String password, byte[] buffer, XAdESPreferences xAdESPreferences)
			throws WSException {
		return null;
	}

	@Override
	public byte[] signXAdESRemote(Credentials credentials, byte[] buffer, XAdESPreferences xAdESPreferences)
			throws WSException {
		return null;
	}

	@Override
	public List<byte[]> signPAdESArrayList(String username, String password, List<byte[]> bufferList,
			PAdESPreferences pAdESPreferences) throws WSException {
		return null;
	}

	@Override
	public void enableRemote(Credentials credentials) throws WSException {
		
	}

	@Override
	public List<byte[]> signXAdESArrayList(String username, String password, List<byte[]> bufferList,
			XAdESPreferences xAdESPreferences) throws WSException {
		return null;
	}

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
	}

	@Override
	public byte[] signPAdES(String username, String password, byte[] buffer, PAdESPreferences pAdESPreferences)
			throws WSException {
		return null;
	}

	@Override
	public List<byte[]> signPkcs1ArrayList(Credentials credentials, List<byte[]> hashList, SignPreferences preferences)
			throws WSException {
		return null;
	}

	@Override
	public byte[] getCertificate(String username, String password) throws WSException {
		return null;
	}

	@Override
	public void changePasswordRemote(Credentials credentials, String newPassword) throws WSException {
		
	}

	@Override
	public List<byte[]> signCAdESArrayList(String username, String password, List<byte[]> bufferList,
			CAdESPreferences cAdESPreferences) throws WSException {
		return null;
	}

	@Override
	public void enable(String securityCode, String username, String password) throws WSException {
		
	}

	@Override
	public void checkOTP(Credentials credentials) throws WSException {
		
	}

	@Override
	public SignedDocumentReportBean verify(byte[] signedContent) throws WSException {
		return null;
	}

	@Override
	public void closeSession(Credentials credentials) throws WSException {
		
	}

	@Override
	public void disableRemote(Credentials credentials) throws WSException {
		
	}

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
	}

	@Override
	public byte[] timestamp(byte[] content, TimeStampPreferences preferences) throws WSException {
		return null;
	}

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
