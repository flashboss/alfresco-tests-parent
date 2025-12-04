package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe Java per timeStampPreferences complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="timeStampPreferences"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="filenameInTSD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="outputAsTSD" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="outputBase64Encoded" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="timestampHashAlgo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="timestampPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="timestampUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="timestampUsername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
/**
 * Class providing functionality for Alfresco testing.
 *
 * @author vige
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "timeStampPreferences",
    propOrder = {
      "filenameInTSD",
      "outputAsTSD",
      "outputBase64Encoded",
      "timestampHashAlgo",
      "timestampPassword",
      "timestampUrl",
      "timestampUsername"
    })
@XmlSeeAlso({SignPreferences.class})
public class TimeStampPreferences {

  /** The filename in t s d. */
  protected String filenameInTSD;

  /** The output as t s d. */
  protected boolean outputAsTSD;

  /** The output base64 encoded. */
  protected boolean outputBase64Encoded;

  /** The timestamp hash algo. */
  protected String timestampHashAlgo;

  /** The timestamp password. */
  protected String timestampPassword;

  /** The timestamp url. */
  protected String timestampUrl;

  /** The timestamp username. */
  protected String timestampUsername;

  /**
   * Recupera il valore della proprietà filenameInTSD.
   *
   * @return possible object is {@link String }
   */
  public String getFilenameInTSD() {
    return filenameInTSD;
  }

  /**
   * Imposta il valore della proprietà filenameInTSD.
   *
   * @param value allowed object is {@link String }
   */
  public void setFilenameInTSD(String value) {
    this.filenameInTSD = value;
  }

  /**
   * Recupera il valore della proprietà outputAsTSD.
   *
   * @return the result
   */
  public boolean isOutputAsTSD() {
    return outputAsTSD;
  }

  /**
   * Imposta il valore della proprietà outputAsTSD.
   *
   * @param value the value
   */
  public void setOutputAsTSD(boolean value) {
    this.outputAsTSD = value;
  }

  /**
   * Recupera il valore della proprietà outputBase64Encoded.
   *
   * @return the result
   */
  public boolean isOutputBase64Encoded() {
    return outputBase64Encoded;
  }

  /**
   * Imposta il valore della proprietà outputBase64Encoded.
   *
   * @param value the value
   */
  public void setOutputBase64Encoded(boolean value) {
    this.outputBase64Encoded = value;
  }

  /**
   * Recupera il valore della proprietà timestampHashAlgo.
   *
   * @return possible object is {@link String }
   */
  public String getTimestampHashAlgo() {
    return timestampHashAlgo;
  }

  /**
   * Imposta il valore della proprietà timestampHashAlgo.
   *
   * @param value allowed object is {@link String }
   */
  public void setTimestampHashAlgo(String value) {
    this.timestampHashAlgo = value;
  }

  /**
   * Recupera il valore della proprietà timestampPassword.
   *
   * @return possible object is {@link String }
   */
  public String getTimestampPassword() {
    return timestampPassword;
  }

  /**
   * Imposta il valore della proprietà timestampPassword.
   *
   * @param value allowed object is {@link String }
   */
  public void setTimestampPassword(String value) {
    this.timestampPassword = value;
  }

  /**
   * Recupera il valore della proprietà timestampUrl.
   *
   * @return possible object is {@link String }
   */
  public String getTimestampUrl() {
    return timestampUrl;
  }

  /**
   * Imposta il valore della proprietà timestampUrl.
   *
   * @param value allowed object is {@link String }
   */
  public void setTimestampUrl(String value) {
    this.timestampUrl = value;
  }

  /**
   * Recupera il valore della proprietà timestampUsername.
   *
   * @return possible object is {@link String }
   */
  public String getTimestampUsername() {
    return timestampUsername;
  }

  /**
   * Imposta il valore della proprietà timestampUsername.
   *
   * @param value allowed object is {@link String }
   */
  public void setTimestampUsername(String value) {
    this.timestampUsername = value;
  }
}
