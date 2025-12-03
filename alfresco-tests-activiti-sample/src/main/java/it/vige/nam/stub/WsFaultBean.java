
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per wsFaultBean complex type.
 * 
 * @author vige
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * @author vige
 * <pre>
 * &lt;complexType name="wsFaultBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "wsFaultBean", propOrder = { "error", "message" })
public class WsFaultBean {

	protected int error;
	protected String message;

	/**
	 * Recupera il valore della proprietà error.
	 * 
	 */
	public int getError() {
		return error;
	}

	/**
	 * Imposta il valore della proprietà error.
	 * 
	 */
	public void setError(int value) {
		this.error = value;
	}

	/**
	 * Recupera il valore della proprietà message.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Imposta il valore della proprietà message.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setMessage(String value) {
		this.message = value;
	}

}
