package it.vige.nam.stub;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "getAvailableSignaturesResponse",
    propOrder = {"_return"})
/**
 * Classe Java per getAvailableSignaturesResponse complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="getAvailableSignaturesResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
public class GetAvailableSignaturesResponse {

  @XmlElement(name = "return")
  /** The return. */
  protected long _return;

  /**
   * Recupera il valore della proprietà return.
   *
   * @return the result
   */
  public long getReturn() {
    return _return;
  }

  /**
   * Imposta il valore della proprietà return.
   *
   * @param value the value
   */
  public void setReturn(long value) {
    this._return = value;
  }
}
