package it.vige.nam.stub;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Classe Java per timestampReportBean complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="timestampReportBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="contentFilename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="contentMimeType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="hashAlgorithm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="index" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="issuer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="serialNumber" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="signatureAlgorithm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="signatureVerificationStatus" type="{http://service.ws.nam/}result" minOccurs="0"/&gt;
 *         &lt;element name="timestampCertData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="timestampCertificateStatus" type="{http://service.ws.nam/}certificateStatus" minOccurs="0"/&gt;
 *         &lt;element name="trustedListVerificationStatus" type="{http://service.ws.nam/}result" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
/**
 * TimestampReportBean implementation for testing purposes.
 *
 * @author vige
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "timestampReportBean",
    propOrder = {
      "comment",
      "content",
      "contentFilename",
      "contentMimeType",
      "date",
      "hashAlgorithm",
      "index",
      "issuer",
      "serialNumber",
      "signatureAlgorithm",
      "signatureVerificationStatus",
      "timestampCertData",
      "timestampCertificateStatus",
      "trustedListVerificationStatus"
    })
public class TimestampReportBean {

  /** The comment. */
  protected String comment;

  protected byte[] content;

  /** The content filename. */
  protected String contentFilename;

  /** The content mime type. */
  protected String contentMimeType;

  /** The date. */
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar date;

  /** The hash algorithm. */
  protected String hashAlgorithm;

  /** The index. */
  protected int index;

  /** The issuer. */
  protected String issuer;

  /** The serial number. */
  protected BigInteger serialNumber;

  /** The signature algorithm. */
  protected String signatureAlgorithm;

  /** The signature verification status. */
  @XmlSchemaType(name = "string")
  protected Result signatureVerificationStatus;

  protected byte[] timestampCertData;

  /** The timestamp certificate status. */
  @XmlSchemaType(name = "string")
  protected CertificateStatus timestampCertificateStatus;

  /** The trusted list verification status. */
  @XmlSchemaType(name = "string")
  protected Result trustedListVerificationStatus;

  /**
   * Recupera il valore della proprietà comment.
   *
   * @return possible object is {@link String }
   */
  public String getComment() {
    return comment;
  }

  /**
   * Imposta il valore della proprietà comment.
   *
   * @param value allowed object is {@link String }
   */
  public void setComment(String value) {
    this.comment = value;
  }

  /**
   * Recupera il valore della proprietà content.
   *
   * @return possible object is byte[]
   */
  public byte[] getContent() {
    return content;
  }

  /**
   * Imposta il valore della proprietà content.
   *
   * @param value allowed object is byte[]
   */
  public void setContent(byte[] value) {
    this.content = value;
  }

  /**
   * Recupera il valore della proprietà contentFilename.
   *
   * @return possible object is {@link String }
   */
  public String getContentFilename() {
    return contentFilename;
  }

  /**
   * Imposta il valore della proprietà contentFilename.
   *
   * @param value allowed object is {@link String }
   */
  public void setContentFilename(String value) {
    this.contentFilename = value;
  }

  /**
   * Recupera il valore della proprietà contentMimeType.
   *
   * @return possible object is {@link String }
   */
  public String getContentMimeType() {
    return contentMimeType;
  }

  /**
   * Imposta il valore della proprietà contentMimeType.
   *
   * @param value allowed object is {@link String }
   */
  public void setContentMimeType(String value) {
    this.contentMimeType = value;
  }

  /**
   * Recupera il valore della proprietà date.
   *
   * @return possible object is {@link XMLGregorianCalendar }
   */
  public XMLGregorianCalendar getDate() {
    return date;
  }

  /**
   * Imposta il valore della proprietà date.
   *
   * @param value allowed object is {@link XMLGregorianCalendar }
   */
  public void setDate(XMLGregorianCalendar value) {
    this.date = value;
  }

  /**
   * Recupera il valore della proprietà hashAlgorithm.
   *
   * @return possible object is {@link String }
   */
  public String getHashAlgorithm() {
    return hashAlgorithm;
  }

  /**
   * Imposta il valore della proprietà hashAlgorithm.
   *
   * @param value allowed object is {@link String }
   */
  public void setHashAlgorithm(String value) {
    this.hashAlgorithm = value;
  }

  /** Recupera il valore della proprietà index. */
  public int getIndex() {
    return index;
  }

  /** Imposta il valore della proprietà index. */
  public void setIndex(int value) {
    this.index = value;
  }

  /**
   * Recupera il valore della proprietà issuer.
   *
   * @return possible object is {@link String }
   */
  public String getIssuer() {
    return issuer;
  }

  /**
   * Imposta il valore della proprietà issuer.
   *
   * @param value allowed object is {@link String }
   */
  public void setIssuer(String value) {
    this.issuer = value;
  }

  /**
   * Recupera il valore della proprietà serialNumber.
   *
   * @return possible object is {@link BigInteger }
   */
  public BigInteger getSerialNumber() {
    return serialNumber;
  }

  /**
   * Imposta il valore della proprietà serialNumber.
   *
   * @param value allowed object is {@link BigInteger }
   */
  public void setSerialNumber(BigInteger value) {
    this.serialNumber = value;
  }

  /**
   * Recupera il valore della proprietà signatureAlgorithm.
   *
   * @return possible object is {@link String }
   */
  public String getSignatureAlgorithm() {
    return signatureAlgorithm;
  }

  /**
   * Imposta il valore della proprietà signatureAlgorithm.
   *
   * @param value allowed object is {@link String }
   */
  public void setSignatureAlgorithm(String value) {
    this.signatureAlgorithm = value;
  }

  /**
   * Recupera il valore della proprietà signatureVerificationStatus.
   *
   * @return possible object is {@link Result }
   */
  public Result getSignatureVerificationStatus() {
    return signatureVerificationStatus;
  }

  /**
   * Imposta il valore della proprietà signatureVerificationStatus.
   *
   * @param value allowed object is {@link Result }
   */
  public void setSignatureVerificationStatus(Result value) {
    this.signatureVerificationStatus = value;
  }

  /**
   * Recupera il valore della proprietà timestampCertData.
   *
   * @return possible object is byte[]
   */
  public byte[] getTimestampCertData() {
    return timestampCertData;
  }

  /**
   * Imposta il valore della proprietà timestampCertData.
   *
   * @param value allowed object is byte[]
   */
  public void setTimestampCertData(byte[] value) {
    this.timestampCertData = value;
  }

  /**
   * Recupera il valore della proprietà timestampCertificateStatus.
   *
   * @return possible object is {@link CertificateStatus }
   */
  public CertificateStatus getTimestampCertificateStatus() {
    return timestampCertificateStatus;
  }

  /**
   * Imposta il valore della proprietà timestampCertificateStatus.
   *
   * @param value allowed object is {@link CertificateStatus }
   */
  public void setTimestampCertificateStatus(CertificateStatus value) {
    this.timestampCertificateStatus = value;
  }

  /**
   * Recupera il valore della proprietà trustedListVerificationStatus.
   *
   * @return possible object is {@link Result }
   */
  public Result getTrustedListVerificationStatus() {
    return trustedListVerificationStatus;
  }

  /**
   * Imposta il valore della proprietà trustedListVerificationStatus.
   *
   * @param value allowed object is {@link Result }
   */
  public void setTrustedListVerificationStatus(Result value) {
    this.trustedListVerificationStatus = value;
  }
}
