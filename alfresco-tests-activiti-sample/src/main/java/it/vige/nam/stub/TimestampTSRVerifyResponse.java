package it.vige.nam.stub;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Classe Java per timestampTSRVerifyResponse complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="timestampTSRVerifyResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://service.ws.nam/}timestampReportBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "timestampTSRVerifyResponse",
    propOrder = {"_return"})
public class TimestampTSRVerifyResponse {

  @XmlElement(name = "return")
  /** The return. */
  protected TimestampReportBean _return;

  /**
   * Recupera il valore della proprietà return.
   *
   * @return possible object is {@link TimestampReportBean }
   */
  public TimestampReportBean getReturn() {
    return _return;
  }

  /**
   * Imposta il valore della proprietà return.
   *
   * @param value allowed object is {@link TimestampReportBean }
   */
  public void setReturn(TimestampReportBean value) {
    this._return = value;
  }
}
