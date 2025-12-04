package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe Java per signWithCredentials complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="signWithCredentials"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="credentials" type="{http://service.ws.nam/}credentials" minOccurs="0"/&gt;
 *         &lt;element name="buffer" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="AdESPreferences" type="{http://service.ws.nam/}signPreferences" minOccurs="0"/&gt;
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
    name = "signWithCredentials",
    propOrder = {"credentials", "buffer", "adESPreferences"})
public class SignWithCredentials {

  /** The credentials. */
  protected Credentials credentials;

  protected byte[] buffer;

  /** The ad e s preferences. */
  @XmlElement(name = "AdESPreferences")
  protected SignPreferences adESPreferences;

  /**
   * Recupera il valore della proprietà credentials.
   *
   * @return possible object is {@link Credentials }
   */
  public Credentials getCredentials() {
    return credentials;
  }

  /**
   * Imposta il valore della proprietà credentials.
   *
   * @param value allowed object is {@link Credentials }
   */
  public void setCredentials(Credentials value) {
    this.credentials = value;
  }

  /**
   * Recupera il valore della proprietà buffer.
   *
   * @return possible object is byte[]
   */
  public byte[] getBuffer() {
    return buffer;
  }

  /**
   * Imposta il valore della proprietà buffer.
   *
   * @param value allowed object is byte[]
   */
  public void setBuffer(byte[] value) {
    this.buffer = value;
  }

  /**
   * Recupera il valore della proprietà adESPreferences.
   *
   * @return possible object is {@link SignPreferences }
   */
  public SignPreferences getAdESPreferences() {
    return adESPreferences;
  }

  /**
   * Imposta il valore della proprietà adESPreferences.
   *
   * @param value allowed object is {@link SignPreferences }
   */
  public void setAdESPreferences(SignPreferences value) {
    this.adESPreferences = value;
  }
}
