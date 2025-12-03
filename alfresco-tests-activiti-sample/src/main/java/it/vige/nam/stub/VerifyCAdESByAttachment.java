package it.vige.nam.stub;

import jakarta.activation.DataHandler;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlMimeType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Classe Java per verifyCAdESByAttachment complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="verifyCAdESByAttachment"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="inputDataHandler" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="preferences" type="{http://service.ws.nam/}verifyPreferences" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "verifyCAdESByAttachment",
    propOrder = {"inputDataHandler", "preferences"})
public class VerifyCAdESByAttachment {

  @XmlMimeType("application/octet-stream")
  /** The input data handler. */
  protected DataHandler inputDataHandler;

  /** The preferences. */
  protected VerifyPreferences preferences;

  /**
   * Recupera il valore della proprietà inputDataHandler.
   *
   * @return possible object is {@link DataHandler }
   */
  public DataHandler getInputDataHandler() {
    return inputDataHandler;
  }

  /**
   * Imposta il valore della proprietà inputDataHandler.
   *
   * @param value allowed object is {@link DataHandler }
   */
  public void setInputDataHandler(DataHandler value) {
    this.inputDataHandler = value;
  }

  /**
   * Recupera il valore della proprietà preferences.
   *
   * @return possible object is {@link VerifyPreferences }
   */
  public VerifyPreferences getPreferences() {
    return preferences;
  }

  /**
   * Imposta il valore della proprietà preferences.
   *
   * @param value allowed object is {@link VerifyPreferences }
   */
  public void setPreferences(VerifyPreferences value) {
    this.preferences = value;
  }
}
