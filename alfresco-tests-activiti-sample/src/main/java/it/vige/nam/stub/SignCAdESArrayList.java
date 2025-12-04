package it.vige.nam.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe Java per signCAdESArrayList complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="signCAdESArrayList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="bufferList" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CAdESPreferences" type="{http://service.ws.nam/}cAdESPreferences" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "signCAdESArrayList",
    propOrder = {"username", "password", "bufferList", "cAdESPreferences"})
/**
 * Class providing functionality for Alfresco testing.
 *
 * @author vige
 */
public class SignCAdESArrayList {
  /** The username. */
  protected String username;

  /** The password. */
  protected String password;

  /** The buffer list. */
  protected List<byte[]> bufferList;

  @XmlElement(name = "CAdESPreferences")
  /** The c ad e s preferences. */
  protected CAdESPreferences cAdESPreferences;

  /**
   * Recupera il valore della proprietà username.
   *
   * @return possible object is {@link String }
   */
  public String getUsername() {
    return username;
  }

  /**
   * Imposta il valore della proprietà username.
   *
   * @param value allowed object is {@link String }
   */
  public void setUsername(String value) {
    this.username = value;
  }

  /**
   * Recupera il valore della proprietà password.
   *
   * @return possible object is {@link String }
   */
  public String getPassword() {
    return password;
  }

  /**
   * Imposta il valore della proprietà password.
   *
   * @param value allowed object is {@link String }
   */
  public void setPassword(String value) {
    this.password = value;
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
