
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per verifyWithPreferencesResponse complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * <pre>
 * &lt;complexType name="verifyWithPreferencesResponse"&gt;
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
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verifyWithPreferencesResponse", propOrder = { "_return" })
/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public class VerifyWithPreferencesResponse {

	@XmlElement(name = "return")
 /** The return. */
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
