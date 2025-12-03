package it.vige.nam.stub;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Classe Java per xAdESPreferences complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="xAdESPreferences"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://service.ws.nam/}signPreferences"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="signElement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "xAdESPreferences",
    propOrder = {"signElement"})
public class XAdESPreferences extends SignPreferences {

  /** The sign element. */
  protected String signElement;

  /**
   * Recupera il valore della proprietà signElement.
   *
   * @return possible object is {@link String }
   */
  public String getSignElement() {
    return signElement;
  }

  /**
   * Imposta il valore della proprietà signElement.
   *
   * @param value allowed object is {@link String }
   */
  public void setSignElement(String value) {
    this.signElement = value;
  }
}
