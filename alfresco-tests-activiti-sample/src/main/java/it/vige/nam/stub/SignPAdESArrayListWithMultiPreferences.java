package it.vige.nam.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe Java per signPAdESArrayListWithMultiPreferences complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="signPAdESArrayListWithMultiPreferences"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="credentials" type="{http://service.ws.nam/}credentials" minOccurs="0"/&gt;
 *         &lt;element name="bufferList" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="PAdESPreferences" type="{http://service.ws.nam/}pAdESPreferences" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
/**
 * SignPAdESArrayListWithMultiPreferences implementation for testing purposes.
 *
 * @author vige
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "signPAdESArrayListWithMultiPreferences",
    propOrder = {"credentials", "bufferList", "pAdESPreferences"})
public class SignPAdESArrayListWithMultiPreferences {

  /** The credentials. */
  protected Credentials credentials;

  /** The buffer list. */
  protected List<byte[]> bufferList;

  /** The p ad e s preferences. */
  @XmlElement(name = "PAdESPreferences")
  protected List<PAdESPreferences> pAdESPreferences;

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
   * Gets the value of the bufferList property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the bufferList property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getBufferList().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list byte[]
   */
  public List<byte[]> getBufferList() {
    if (bufferList == null) {
      bufferList = new ArrayList<byte[]>();
    }
    return this.bufferList;
  }

  /**
   * Gets the value of the pAdESPreferences property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the pAdESPreferences property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getPAdESPreferences().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link PAdESPreferences }
   */
  public List<PAdESPreferences> getPAdESPreferences() {
    if (pAdESPreferences == null) {
      pAdESPreferences = new ArrayList<PAdESPreferences>();
    }
    return this.pAdESPreferences;
  }
}
