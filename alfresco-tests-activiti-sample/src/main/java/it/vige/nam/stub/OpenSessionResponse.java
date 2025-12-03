
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per openSessionResponse complex type.
 * 
 * @author vige
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * @author vige
 * <pre>
 * &lt;complexType name="openSessionResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * @author vige
 * 
 * @author vige
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "openSessionResponse", propOrder = { "_return" })
public class OpenSessionResponse {

	@XmlElement(name = "return")
	protected String _return;

	/**
	 * Recupera il valore della proprietà return.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReturn() {
		return _return;
	}

	/**
	 * Imposta il valore della proprietà return.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setReturn(String value) {
		this._return = value;
	}

}
