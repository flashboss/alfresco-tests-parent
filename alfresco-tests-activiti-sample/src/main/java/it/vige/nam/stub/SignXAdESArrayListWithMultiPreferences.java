package it.vige.nam.stub;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "signXAdESArrayListWithMultiPreferences",
    propOrder = {
      "credentials",
      "bufferList",
      /**
       * Classe Java per signXAdESArrayListWithMultiPreferences complex type.
       *
       * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa
       * classe.
       *
       * <pre>
       * &lt;complexType name="signXAdESArrayListWithMultiPreferences"&gt;
       *   &lt;complexContent&gt;
       *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
       *       &lt;sequence&gt;
       *         &lt;element name="credentials" type="{http://service.ws.nam/}credentials" minOccurs="0"/&gt;
       *         &lt;element name="bufferList" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/&gt;
       *         &lt;element name="XAdESPreferences" type="{http://service.ws.nam/}xAdESPreferences" maxOccurs="unbounded" minOccurs="0"/&gt;
       *       &lt;/sequence&gt;
       *     &lt;/restriction&gt;
       *   &lt;/complexContent&gt;
       * &lt;/complexType&gt;
       * </pre>
       */
      "xAdESPreferences"
    })
public class SignXAdESArrayListWithMultiPreferences {

  /** The credentials. */
  protected Credentials credentials;

  protected List<byte[]> bufferList;

  @XmlElement(name = "XAdESPreferences")
  protected List<XAdESPreferences> xAdESPreferences;

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
   *
   * @return the result
   */
  public List<byte[]> getBufferList() {
    if (bufferList == null) {
      bufferList = new ArrayList<byte[]>();
    }
    return this.bufferList;
  }

  /**
   * Gets the value of the xAdESPreferences property.
   *
   * <p>This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the xAdESPreferences property.
   *
   * <p>For example, to add a new item, do as follows:
   *
   * <pre>
   * getXAdESPreferences().add(newItem);
   * </pre>
   *
   * <p>Objects of the following type(s) are allowed in the list {@link XAdESPreferences }
   *
   * @return the result
   */
  public List<XAdESPreferences> getXAdESPreferences() {
    if (xAdESPreferences == null) {
      xAdESPreferences = new ArrayList<XAdESPreferences>();
    }
    return this.xAdESPreferences;
  }
}
