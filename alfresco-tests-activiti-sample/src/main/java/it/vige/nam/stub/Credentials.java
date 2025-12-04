package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "credentials",
    propOrder = {"idOtp", "otp", "password", "securityCode", "sessionKey", "username"})
/**
 * Classe Java per credentials complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="credentials"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idOtp" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="otp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="securityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sessionKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
public class Credentials {

  /** The id otp. */
  protected int idOtp;

  /** The otp. */
  protected String otp;

  /** The password. */
  protected String password;

  /** The security code. */
  protected String securityCode;

  /** The session key. */
  protected String sessionKey;

  /** The username. */
  protected String username;

  /**
   * Recupera il valore della proprietà idOtp.
   *
   * @return the result
   */
  public int getIdOtp() {
    return idOtp;
  }

  /**
   * Imposta il valore della proprietà idOtp.
   *
   * @param value the value
   */
  public void setIdOtp(int value) {
    this.idOtp = value;
  }

  /**
   * Recupera il valore della proprietà otp.
   *
   * @return possible object is {@link String }
   */
  public String getOtp() {
    return otp;
  }

  /**
   * Imposta il valore della proprietà otp.
   *
   * @param value allowed object is {@link String }
   */
  public void setOtp(String value) {
    this.otp = value;
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
   * Recupera il valore della proprietà securityCode.
   *
   * @return possible object is {@link String }
   */
  public String getSecurityCode() {
    return securityCode;
  }

  /**
   * Imposta il valore della proprietà securityCode.
   *
   * @param value allowed object is {@link String }
   */
  public void setSecurityCode(String value) {
    this.securityCode = value;
  }

  /**
   * Recupera il valore della proprietà sessionKey.
   *
   * @return possible object is {@link String }
   */
  public String getSessionKey() {
    return sessionKey;
  }

  /**
   * Imposta il valore della proprietà sessionKey.
   *
   * @param value allowed object is {@link String }
   */
  public void setSessionKey(String value) {
    this.sessionKey = value;
  }

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
}
