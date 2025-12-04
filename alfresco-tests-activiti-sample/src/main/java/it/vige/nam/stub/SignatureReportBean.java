
package it.vige.nam.stub;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Classe Java per signatureReportBean complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * <pre>
 * &lt;complexType name="signatureReportBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="derEncodedSignerCert" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="integrity" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="issuerCertificateRevocationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="issuerCertificateStatus" type="{http://service.ws.nam/}certificateStatus" minOccurs="0"/&gt;
 *         &lt;element name="issuerDN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="issuerInTrustedList" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="keySize" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="qcComplianceStatus" type="{http://service.ws.nam/}result" minOccurs="0"/&gt;
 *         &lt;element name="serialNumber" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="signatureAlgorithmName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="signatureDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="signerCertificateNotAfter" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="signerCertificateNotBefore" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="signerCertificateRevocationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="signerCertificateStatus" type="{http://service.ws.nam/}certificateStatus" minOccurs="0"/&gt;
 *         &lt;element name="subjectDN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="timestampReportBeanList" type="{http://service.ws.nam/}timestampReportBean" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="trustedSignatureDate" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signatureReportBean", propOrder = { "derEncodedSignerCert", "id", "integrity",
		"issuerCertificateRevocationDate", "issuerCertificateStatus", "issuerDN", "issuerInTrustedList", "keySize",
		"qcComplianceStatus", "serialNumber", "signatureAlgorithmName", "signatureDate", "signerCertificateNotAfter",
		"signerCertificateNotBefore", "signerCertificateRevocationDate", "signerCertificateStatus", "subjectDN",
		"timestampReportBeanList", "trustedSignatureDate" })
/**
 * Bean class for data transfer.
 * 
 * @author vige
 */
public class SignatureReportBean {

	protected byte[] derEncodedSignerCert;
 /** The id. */
	protected String id;
 /** The integrity. */
	protected boolean integrity;
	@XmlSchemaType(name = "dateTime")
 /** The issuer certificate revocation date. */
	protected XMLGregorianCalendar issuerCertificateRevocationDate;
	@XmlSchemaType(name = "string")
 /** The issuer certificate status. */
	protected CertificateStatus issuerCertificateStatus;
 /** The issuer d n. */
	protected String issuerDN;
 /** The issuer in trusted list. */
	protected boolean issuerInTrustedList;
 /** The key size. */
	protected int keySize;
	@XmlSchemaType(name = "string")
 /** The qc compliance status. */
	protected Result qcComplianceStatus;
 /** The serial number. */
	protected BigInteger serialNumber;
 /** The signature algorithm name. */
	protected String signatureAlgorithmName;
	@XmlSchemaType(name = "dateTime")
 /** The signature date. */
	protected XMLGregorianCalendar signatureDate;
	@XmlSchemaType(name = "dateTime")
 /** The signer certificate not after. */
	protected XMLGregorianCalendar signerCertificateNotAfter;
	@XmlSchemaType(name = "dateTime")
 /** The signer certificate not before. */
	protected XMLGregorianCalendar signerCertificateNotBefore;
	@XmlSchemaType(name = "dateTime")
 /** The signer certificate revocation date. */
	protected XMLGregorianCalendar signerCertificateRevocationDate;
	@XmlSchemaType(name = "string")
 /** The signer certificate status. */
	protected CertificateStatus signerCertificateStatus;
 /** The subject d n. */
	protected String subjectDN;
	@XmlElement(nillable = true)
	protected List<TimestampReportBean> timestampReportBeanList;
 /** The trusted signature date. */
	protected boolean trustedSignatureDate;

	/**
	 * Recupera il valore della proprietà derEncodedSignerCert.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getDerEncodedSignerCert() {
		return derEncodedSignerCert;
	}

	/**
	 * Imposta il valore della proprietà derEncodedSignerCert.
	 * 
	 * @param value allowed object is byte[]
	 */
	public void setDerEncodedSignerCert(byte[] value) {
		this.derEncodedSignerCert = value;
	}

	/**
	 * Recupera il valore della proprietà id.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Imposta il valore della proprietà id.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Recupera il valore della proprietà integrity.
	 * 
	 */
	public boolean isIntegrity() {
		return integrity;
	}

	/**
	 * Imposta il valore della proprietà integrity.
	 * 
	 */
	public void setIntegrity(boolean value) {
		this.integrity = value;
	}

	/**
	 * Recupera il valore della proprietà issuerCertificateRevocationDate.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getIssuerCertificateRevocationDate() {
		return issuerCertificateRevocationDate;
	}

	/**
	 * Imposta il valore della proprietà issuerCertificateRevocationDate.
	 * 
	 * @param value allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setIssuerCertificateRevocationDate(XMLGregorianCalendar value) {
		this.issuerCertificateRevocationDate = value;
	}

	/**
	 * Recupera il valore della proprietà issuerCertificateStatus.
	 * 
	 * @return possible object is {@link CertificateStatus }
	 * 
	 */
	public CertificateStatus getIssuerCertificateStatus() {
		return issuerCertificateStatus;
	}

	/**
	 * Imposta il valore della proprietà issuerCertificateStatus.
	 * 
	 * @param value allowed object is {@link CertificateStatus }
	 * 
	 */
	public void setIssuerCertificateStatus(CertificateStatus value) {
		this.issuerCertificateStatus = value;
	}

	/**
	 * Recupera il valore della proprietà issuerDN.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIssuerDN() {
		return issuerDN;
	}

	/**
	 * Imposta il valore della proprietà issuerDN.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setIssuerDN(String value) {
		this.issuerDN = value;
	}

	/**
	 * Recupera il valore della proprietà issuerInTrustedList.
	 * 
	 */
	public boolean isIssuerInTrustedList() {
		return issuerInTrustedList;
	}

	/**
	 * Imposta il valore della proprietà issuerInTrustedList.
	 * 
	 */
	public void setIssuerInTrustedList(boolean value) {
		this.issuerInTrustedList = value;
	}

	/**
	 * Recupera il valore della proprietà keySize.
	 * 
	 */
	public int getKeySize() {
		return keySize;
	}

	/**
	 * Imposta il valore della proprietà keySize.
	 * 
	 */
	public void setKeySize(int value) {
		this.keySize = value;
	}

	/**
	 * Recupera il valore della proprietà qcComplianceStatus.
	 * 
	 * @return possible object is {@link Result }
	 * 
	 */
	public Result getQcComplianceStatus() {
		return qcComplianceStatus;
	}

	/**
	 * Imposta il valore della proprietà qcComplianceStatus.
	 * 
	 * @param value allowed object is {@link Result }
	 * 
	 */
	public void setQcComplianceStatus(Result value) {
		this.qcComplianceStatus = value;
	}

	/**
	 * Recupera il valore della proprietà serialNumber.
	 * 
	 * @return possible object is {@link BigInteger }
	 * 
	 */
	public BigInteger getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Imposta il valore della proprietà serialNumber.
	 * 
	 * @param value allowed object is {@link BigInteger }
	 * 
	 */
	public void setSerialNumber(BigInteger value) {
		this.serialNumber = value;
	}

	/**
	 * Recupera il valore della proprietà signatureAlgorithmName.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSignatureAlgorithmName() {
		return signatureAlgorithmName;
	}

	/**
	 * Imposta il valore della proprietà signatureAlgorithmName.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setSignatureAlgorithmName(String value) {
		this.signatureAlgorithmName = value;
	}

	/**
	 * Recupera il valore della proprietà signatureDate.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getSignatureDate() {
		return signatureDate;
	}

	/**
	 * Imposta il valore della proprietà signatureDate.
	 * 
	 * @param value allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setSignatureDate(XMLGregorianCalendar value) {
		this.signatureDate = value;
	}

	/**
	 * Recupera il valore della proprietà signerCertificateNotAfter.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getSignerCertificateNotAfter() {
		return signerCertificateNotAfter;
	}

	/**
	 * Imposta il valore della proprietà signerCertificateNotAfter.
	 * 
	 * @param value allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setSignerCertificateNotAfter(XMLGregorianCalendar value) {
		this.signerCertificateNotAfter = value;
	}

	/**
	 * Recupera il valore della proprietà signerCertificateNotBefore.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getSignerCertificateNotBefore() {
		return signerCertificateNotBefore;
	}

	/**
	 * Imposta il valore della proprietà signerCertificateNotBefore.
	 * 
	 * @param value allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setSignerCertificateNotBefore(XMLGregorianCalendar value) {
		this.signerCertificateNotBefore = value;
	}

	/**
	 * Recupera il valore della proprietà signerCertificateRevocationDate.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getSignerCertificateRevocationDate() {
		return signerCertificateRevocationDate;
	}

	/**
	 * Imposta il valore della proprietà signerCertificateRevocationDate.
	 * 
	 * @param value allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setSignerCertificateRevocationDate(XMLGregorianCalendar value) {
		this.signerCertificateRevocationDate = value;
	}

	/**
	 * Recupera il valore della proprietà signerCertificateStatus.
	 * 
	 * @return possible object is {@link CertificateStatus }
	 * 
	 */
	public CertificateStatus getSignerCertificateStatus() {
		return signerCertificateStatus;
	}

	/**
	 * Imposta il valore della proprietà signerCertificateStatus.
	 * 
	 * @param value allowed object is {@link CertificateStatus }
	 * 
	 */
	public void setSignerCertificateStatus(CertificateStatus value) {
		this.signerCertificateStatus = value;
	}

	/**
	 * Recupera il valore della proprietà subjectDN.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSubjectDN() {
		return subjectDN;
	}

	/**
	 * Imposta il valore della proprietà subjectDN.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setSubjectDN(String value) {
		this.subjectDN = value;
	}

	/**
	 * Gets the value of the timestampReportBeanList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the timestampReportBeanList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getTimestampReportBeanList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link TimestampReportBean }
	 * 
	 * 
	 */
	public List<TimestampReportBean> getTimestampReportBeanList() {
		if (timestampReportBeanList == null) {
			timestampReportBeanList = new ArrayList<TimestampReportBean>();
		}
		return this.timestampReportBeanList;
	}

	/**
	 * Recupera il valore della proprietà trustedSignatureDate.
	 * 
	 */
	public boolean isTrustedSignatureDate() {
		return trustedSignatureDate;
	}

	/**
	 * Imposta il valore della proprietà trustedSignatureDate.
	 * 
	 */
	public void setTrustedSignatureDate(boolean value) {
		this.trustedSignatureDate = value;
	}

}
