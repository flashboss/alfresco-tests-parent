
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per verifyResponse complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * <pre>
 * &lt;complexType name="verifyResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://service.ws.nam/}signedDocumentReportBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 *
 *
 * @author vige
 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verifyResponse", propOrder = { "_return" })
public class VerifyResponse {

	@XmlElement(name = "return")
	protected SignedDocumentReportBean _return;

	/**
	 * Recupera il valore della proprietà return.
	 * 
	 * @return possible object is {@link SignedDocumentReportBean }
	 * 
	 */
	public SignedDocumentReportBean getReturn() {
		return _return;
	}

	/**
	 * Imposta il valore della proprietà return.
	 * 
	 * @param value allowed object is {@link SignedDocumentReportBean }
	 * 
	 */
	public void setReturn(SignedDocumentReportBean value) {
		this._return = value;
	}

}
