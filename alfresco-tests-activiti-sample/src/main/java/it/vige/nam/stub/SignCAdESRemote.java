package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe Java per signCAdESRemote complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="signCAdESRemote"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="credentials" type="{http://service.ws.nam/}credentials" minOccurs="0"/&gt;
 *         &lt;element name="buffer" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="CAdESPreferences" type="{http://service.ws.nam/}cAdESPreferences" minOccurs="0"/&gt;
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
    name = "signCAdESRemote",
    propOrder = {"credentials", "buffer", "cAdESPreferences"})
public class SignCAdESRemote {

  /** The credentials. */
  protected Credentials credentials;

  protected byte[] buffer;

  /** The c ad e s preferences. */
  @XmlElement(name = "CAdESPreferences")
  protected CAdESPreferences cAdESPreferences;

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
   * Recupera il valore della proprietà cAdESPreferences.
   *
   * @return possible object is {@link CAdESPreferences }
   */
  public CAdESPreferences getCAdESPreferences() {
    return cAdESPreferences;
  }

  /**
   * Imposta il valore della proprietà cAdESPreferences.
   *
   * @param value allowed object is {@link CAdESPreferences }
   */
  public void setCAdESPreferences(CAdESPreferences value) {
    this.cAdESPreferences = value;
  }
}
