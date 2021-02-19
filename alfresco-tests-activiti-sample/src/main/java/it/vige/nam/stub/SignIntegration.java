package it.vige.nam.stub;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.11 2017-04-20T12:22:19.855+02:00
 * Generated source version: 3.1.11
 * 
 */
@WebService(targetNamespace = "http://service.ws.nam/", name = "SignIntegration")
@XmlSeeAlso({ ObjectFactory.class })
public interface SignIntegration {

	@WebMethod
	@RequestWrapper(localName = "signPkcs1", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPkcs1")
	@ResponseWrapper(localName = "signPkcs1Response", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPkcs1Response")
	@WebResult(name = "return", targetNamespace = "")
	public byte[] signPkcs1(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "hash", targetNamespace = "") byte[] hash,
			@WebParam(name = "preferences", targetNamespace = "") it.vige.nam.stub.SignPreferences preferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "sendOtpBySMS", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SendOtpBySMS")
	@ResponseWrapper(localName = "sendOtpBySMSResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SendOtpBySMSResponse")
	public void sendOtpBySMS(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signCAdES", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignCAdES")
	@ResponseWrapper(localName = "signCAdESResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignCAdESResponse")
	@WebResult(name = "return", targetNamespace = "")
	public byte[] signCAdES(@WebParam(name = "username", targetNamespace = "") java.lang.String username,
			@WebParam(name = "password", targetNamespace = "") java.lang.String password,
			@WebParam(name = "buffer", targetNamespace = "") byte[] buffer,
			@WebParam(name = "CAdESPreferences", targetNamespace = "") it.vige.nam.stub.CAdESPreferences cAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signCAdESRemote", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignCAdESRemote")
	@ResponseWrapper(localName = "signCAdESRemoteResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignCAdESRemoteResponse")
	@WebResult(name = "return", targetNamespace = "")
	public byte[] signCAdESRemote(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "buffer", targetNamespace = "") byte[] buffer,
			@WebParam(name = "CAdESPreferences", targetNamespace = "") it.vige.nam.stub.CAdESPreferences cAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "changePassword", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.ChangePassword")
	@ResponseWrapper(localName = "changePasswordResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.ChangePasswordResponse")
	public void changePassword(@WebParam(name = "securityCode", targetNamespace = "") java.lang.String securityCode,
			@WebParam(name = "username", targetNamespace = "") java.lang.String username,
			@WebParam(name = "password", targetNamespace = "") java.lang.String password,
			@WebParam(name = "newPassword", targetNamespace = "") java.lang.String newPassword) throws WSException;

	@WebMethod
	@RequestWrapper(localName = "verifyOnDate", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.VerifyOnDate")
	@ResponseWrapper(localName = "verifyOnDateResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.VerifyOnDateResponse")
	@WebResult(name = "return", targetNamespace = "")
	public it.vige.nam.stub.SignedDocumentReportBean verifyOnDate(
			@WebParam(name = "signedContent", targetNamespace = "") byte[] signedContent,
			@WebParam(name = "verifyDate", targetNamespace = "") javax.xml.datatype.XMLGregorianCalendar verifyDate)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "disable", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.Disable")
	@ResponseWrapper(localName = "disableResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.DisableResponse")
	public void disable(@WebParam(name = "securityCode", targetNamespace = "") java.lang.String securityCode,
			@WebParam(name = "username", targetNamespace = "") java.lang.String username,
			@WebParam(name = "password", targetNamespace = "") java.lang.String password) throws WSException;

	@WebMethod
	@RequestWrapper(localName = "openSession", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.OpenSession")
	@ResponseWrapper(localName = "openSessionResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.OpenSessionResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.lang.String openSession(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "checkFirstFactor", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.CheckFirstFactor")
	@ResponseWrapper(localName = "checkFirstFactorResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.CheckFirstFactorResponse")
	public void checkFirstFactor(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signPAdESRemote", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPAdESRemote")
	@ResponseWrapper(localName = "signPAdESRemoteResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPAdESRemoteResponse")
	@WebResult(name = "return", targetNamespace = "")
	public byte[] signPAdESRemote(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "buffer", targetNamespace = "") byte[] buffer,
			@WebParam(name = "PAdESPreferences", targetNamespace = "") it.vige.nam.stub.PAdESPreferences pAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signCAdESArrayListWithMultiPreferences", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignCAdESArrayListWithMultiPreferences")
	@ResponseWrapper(localName = "signCAdESArrayListWithMultiPreferencesResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignCAdESArrayListWithMultiPreferencesResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.util.List<byte[]> signCAdESArrayListWithMultiPreferences(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "bufferList", targetNamespace = "") java.util.List<byte[]> bufferList,
			@WebParam(name = "CAdESPreferences", targetNamespace = "") java.util.List<it.vige.nam.stub.CAdESPreferences> cAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signPAdESArrayListWithCredentials", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPAdESArrayListWithCredentials")
	@ResponseWrapper(localName = "signPAdESArrayListWithCredentialsResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPAdESArrayListWithCredentialsResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.util.List<byte[]> signPAdESArrayListWithCredentials(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "bufferList", targetNamespace = "") java.util.List<byte[]> bufferList,
			@WebParam(name = "PAdESPreferences", targetNamespace = "") it.vige.nam.stub.PAdESPreferences pAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signCAdESArrayListWithCredentials", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignCAdESArrayListWithCredentials")
	@ResponseWrapper(localName = "signCAdESArrayListWithCredentialsResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignCAdESArrayListWithCredentialsResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.util.List<byte[]> signCAdESArrayListWithCredentials(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "bufferList", targetNamespace = "") java.util.List<byte[]> bufferList,
			@WebParam(name = "CAdESPreferences", targetNamespace = "") it.vige.nam.stub.CAdESPreferences cAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signWithCredentials", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignWithCredentials")
	@ResponseWrapper(localName = "signWithCredentialsResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignWithCredentialsResponse")
	@WebResult(name = "return", targetNamespace = "")
	public byte[] signWithCredentials(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "buffer", targetNamespace = "") byte[] buffer,
			@WebParam(name = "AdESPreferences", targetNamespace = "") it.vige.nam.stub.SignPreferences adESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signXAdESArrayListWithMultiPreferences", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignXAdESArrayListWithMultiPreferences")
	@ResponseWrapper(localName = "signXAdESArrayListWithMultiPreferencesResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignXAdESArrayListWithMultiPreferencesResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.util.List<byte[]> signXAdESArrayListWithMultiPreferences(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "bufferList", targetNamespace = "") java.util.List<byte[]> bufferList,
			@WebParam(name = "XAdESPreferences", targetNamespace = "") java.util.List<it.vige.nam.stub.XAdESPreferences> xAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signArrayListWithCredentials", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignArrayListWithCredentials")
	@ResponseWrapper(localName = "signArrayListWithCredentialsResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignArrayListWithCredentialsResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.util.List<byte[]> signArrayListWithCredentials(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "bufferList", targetNamespace = "") java.util.List<byte[]> bufferList,
			@WebParam(name = "AdESPreferences", targetNamespace = "") it.vige.nam.stub.SignPreferences adESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "getSignatures", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.GetSignatures")
	@ResponseWrapper(localName = "getSignaturesResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.GetSignaturesResponse")
	@WebResult(name = "return", targetNamespace = "")
	public long getSignatures(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "sign", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.Sign")
	@ResponseWrapper(localName = "signResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignResponse")
	@WebResult(name = "return", targetNamespace = "")
	public byte[] sign(@WebParam(name = "username", targetNamespace = "") java.lang.String username,
			@WebParam(name = "password", targetNamespace = "") java.lang.String password,
			@WebParam(name = "buffer", targetNamespace = "") byte[] buffer,
			@WebParam(name = "AdESPreferences", targetNamespace = "") it.vige.nam.stub.SignPreferences adESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "timestampTSRVerify", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.TimestampTSRVerify")
	@ResponseWrapper(localName = "timestampTSRVerifyResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.TimestampTSRVerifyResponse")
	@WebResult(name = "return", targetNamespace = "")
	public it.vige.nam.stub.TimestampReportBean timestampTSRVerify(
			@WebParam(name = "tsr", targetNamespace = "") byte[] tsr,
			@WebParam(name = "content", targetNamespace = "") byte[] content) throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signXAdESArrayListWithCredentials", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignXAdESArrayListWithCredentials")
	@ResponseWrapper(localName = "signXAdESArrayListWithCredentialsResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignXAdESArrayListWithCredentialsResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.util.List<byte[]> signXAdESArrayListWithCredentials(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "bufferList", targetNamespace = "") java.util.List<byte[]> bufferList,
			@WebParam(name = "XAdESPreferences", targetNamespace = "") it.vige.nam.stub.XAdESPreferences xAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "getAvailableSignatures", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.GetAvailableSignatures")
	@ResponseWrapper(localName = "getAvailableSignaturesResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.GetAvailableSignaturesResponse")
	@WebResult(name = "return", targetNamespace = "")
	public long getAvailableSignatures(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signXAdES", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignXAdES")
	@ResponseWrapper(localName = "signXAdESResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignXAdESResponse")
	@WebResult(name = "return", targetNamespace = "")
	public byte[] signXAdES(@WebParam(name = "username", targetNamespace = "") java.lang.String username,
			@WebParam(name = "password", targetNamespace = "") java.lang.String password,
			@WebParam(name = "buffer", targetNamespace = "") byte[] buffer,
			@WebParam(name = "XAdESPreferences", targetNamespace = "") it.vige.nam.stub.XAdESPreferences xAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signXAdESRemote", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignXAdESRemote")
	@ResponseWrapper(localName = "signXAdESRemoteResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignXAdESRemoteResponse")
	@WebResult(name = "return", targetNamespace = "")
	public byte[] signXAdESRemote(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "buffer", targetNamespace = "") byte[] buffer,
			@WebParam(name = "XAdESPreferences", targetNamespace = "") it.vige.nam.stub.XAdESPreferences xAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signPAdESArrayList", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPAdESArrayList")
	@ResponseWrapper(localName = "signPAdESArrayListResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPAdESArrayListResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.util.List<byte[]> signPAdESArrayList(
			@WebParam(name = "username", targetNamespace = "") java.lang.String username,
			@WebParam(name = "password", targetNamespace = "") java.lang.String password,
			@WebParam(name = "bufferList", targetNamespace = "") java.util.List<byte[]> bufferList,
			@WebParam(name = "PAdESPreferences", targetNamespace = "") it.vige.nam.stub.PAdESPreferences pAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "enableRemote", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.EnableRemote")
	@ResponseWrapper(localName = "enableRemoteResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.EnableRemoteResponse")
	public void enableRemote(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signXAdESArrayList", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignXAdESArrayList")
	@ResponseWrapper(localName = "signXAdESArrayListResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignXAdESArrayListResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.util.List<byte[]> signXAdESArrayList(
			@WebParam(name = "username", targetNamespace = "") java.lang.String username,
			@WebParam(name = "password", targetNamespace = "") java.lang.String password,
			@WebParam(name = "bufferList", targetNamespace = "") java.util.List<byte[]> bufferList,
			@WebParam(name = "XAdESPreferences", targetNamespace = "") it.vige.nam.stub.XAdESPreferences xAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "timestampTSDVerify", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.TimestampTSDVerify")
	@ResponseWrapper(localName = "timestampTSDVerifyResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.TimestampTSDVerifyResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.util.List<it.vige.nam.stub.TimestampReportBean> timestampTSDVerify(
			@WebParam(name = "tsd", targetNamespace = "") byte[] tsd) throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signPAdESArrayListWithMultiPreferences", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPAdESArrayListWithMultiPreferences")
	@ResponseWrapper(localName = "signPAdESArrayListWithMultiPreferencesResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPAdESArrayListWithMultiPreferencesResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.util.List<byte[]> signPAdESArrayListWithMultiPreferences(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "bufferList", targetNamespace = "") java.util.List<byte[]> bufferList,
			@WebParam(name = "PAdESPreferences", targetNamespace = "") java.util.List<it.vige.nam.stub.PAdESPreferences> pAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signArrayList", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignArrayList")
	@ResponseWrapper(localName = "signArrayListResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignArrayListResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.util.List<byte[]> signArrayList(
			@WebParam(name = "username", targetNamespace = "") java.lang.String username,
			@WebParam(name = "password", targetNamespace = "") java.lang.String password,
			@WebParam(name = "bufferList", targetNamespace = "") java.util.List<byte[]> bufferList,
			@WebParam(name = "AdESPreferences", targetNamespace = "") it.vige.nam.stub.SignPreferences adESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signPAdES", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPAdES")
	@ResponseWrapper(localName = "signPAdESResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPAdESResponse")
	@WebResult(name = "return", targetNamespace = "")
	public byte[] signPAdES(@WebParam(name = "username", targetNamespace = "") java.lang.String username,
			@WebParam(name = "password", targetNamespace = "") java.lang.String password,
			@WebParam(name = "buffer", targetNamespace = "") byte[] buffer,
			@WebParam(name = "PAdESPreferences", targetNamespace = "") it.vige.nam.stub.PAdESPreferences pAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signPkcs1ArrayList", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPkcs1ArrayList")
	@ResponseWrapper(localName = "signPkcs1ArrayListResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignPkcs1ArrayListResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.util.List<byte[]> signPkcs1ArrayList(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "hashList", targetNamespace = "") java.util.List<byte[]> hashList,
			@WebParam(name = "preferences", targetNamespace = "") it.vige.nam.stub.SignPreferences preferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "getCertificate", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.GetCertificate")
	@ResponseWrapper(localName = "getCertificateResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.GetCertificateResponse")
	@WebResult(name = "return", targetNamespace = "")
	public byte[] getCertificate(@WebParam(name = "username", targetNamespace = "") java.lang.String username,
			@WebParam(name = "password", targetNamespace = "") java.lang.String password) throws WSException;

	@WebMethod
	@RequestWrapper(localName = "changePasswordRemote", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.ChangePasswordRemote")
	@ResponseWrapper(localName = "changePasswordRemoteResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.ChangePasswordRemoteResponse")
	public void changePasswordRemote(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials,
			@WebParam(name = "newPassword", targetNamespace = "") java.lang.String newPassword) throws WSException;

	@WebMethod
	@RequestWrapper(localName = "signCAdESArrayList", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignCAdESArrayList")
	@ResponseWrapper(localName = "signCAdESArrayListResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.SignCAdESArrayListResponse")
	@WebResult(name = "return", targetNamespace = "")
	public java.util.List<byte[]> signCAdESArrayList(
			@WebParam(name = "username", targetNamespace = "") java.lang.String username,
			@WebParam(name = "password", targetNamespace = "") java.lang.String password,
			@WebParam(name = "bufferList", targetNamespace = "") java.util.List<byte[]> bufferList,
			@WebParam(name = "CAdESPreferences", targetNamespace = "") it.vige.nam.stub.CAdESPreferences cAdESPreferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "enable", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.Enable")
	@ResponseWrapper(localName = "enableResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.EnableResponse")
	public void enable(@WebParam(name = "securityCode", targetNamespace = "") java.lang.String securityCode,
			@WebParam(name = "username", targetNamespace = "") java.lang.String username,
			@WebParam(name = "password", targetNamespace = "") java.lang.String password) throws WSException;

	@WebMethod
	@RequestWrapper(localName = "checkOTP", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.CheckOTP")
	@ResponseWrapper(localName = "checkOTPResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.CheckOTPResponse")
	public void checkOTP(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "verify", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.Verify")
	@ResponseWrapper(localName = "verifyResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.VerifyResponse")
	@WebResult(name = "return", targetNamespace = "")
	public it.vige.nam.stub.SignedDocumentReportBean verify(
			@WebParam(name = "signedContent", targetNamespace = "") byte[] signedContent) throws WSException;

	@WebMethod
	@RequestWrapper(localName = "closeSession", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.CloseSession")
	@ResponseWrapper(localName = "closeSessionResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.CloseSessionResponse")
	public void closeSession(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "disableRemote", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.DisableRemote")
	@ResponseWrapper(localName = "disableRemoteResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.DisableRemoteResponse")
	public void disableRemote(
			@WebParam(name = "credentials", targetNamespace = "") it.vige.nam.stub.Credentials credentials)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "verifyWithPreferences", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.VerifyWithPreferences")
	@ResponseWrapper(localName = "verifyWithPreferencesResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.VerifyWithPreferencesResponse")
	@WebResult(name = "return", targetNamespace = "")
	public it.vige.nam.stub.SignedDocumentReportBean verifyWithPreferences(
			@WebParam(name = "signedContent", targetNamespace = "") byte[] signedContent,
			@WebParam(name = "preferences", targetNamespace = "") it.vige.nam.stub.VerifyPreferences preferences)
			throws WSException;

	@WebMethod
	@RequestWrapper(localName = "timestamp", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.Timestamp")
	@ResponseWrapper(localName = "timestampResponse", targetNamespace = "http://service.ws.nam/", className = "it.vige.nam.stub.TimestampResponse")
	@WebResult(name = "return", targetNamespace = "")
	public byte[] timestamp(@WebParam(name = "content", targetNamespace = "") byte[] content,
			@WebParam(name = "preferences", targetNamespace = "") it.vige.nam.stub.TimeStampPreferences preferences)
			throws WSException;
}
