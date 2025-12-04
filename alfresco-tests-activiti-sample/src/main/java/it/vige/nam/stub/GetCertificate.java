package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe Java per getCertificate complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="getCertificate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    name = "getCertificate",
    propOrder = {"username", "password"})
public class GetCertificate {

  /** The username. */
  protected String username;

  /** The password. */
  protected String password;

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
}
