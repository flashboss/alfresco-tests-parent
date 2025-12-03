
package it.vige.nam.stub;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per signCAdESByAttachmentResponse complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * <pre>
 * &lt;complexType name="signCAdESByAttachmentResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
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
@XmlType(name = "signCAdESByAttachmentResponse", propOrder = { "_return" })
public class SignCAdESByAttachmentResponse {

	@XmlElement(name = "return")
	@XmlMimeType("application/octet-stream")
	/** The  return. */
	protected DataHandler _return;

	/**
	 * Recupera il valore della proprietà return.
	 * 
	 * @return possible object is {@link DataHandler }
	 * 
	 */
	public DataHandler getReturn() {
		return _return;
	}

	/**
	 * Imposta il valore della proprietà return.
	 * 
	 * @param value allowed object is {@link DataHandler }
	 * 
	 */
	public void setReturn(DataHandler value) {
		this._return = value;
	}

}
