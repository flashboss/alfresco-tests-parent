
package it.vige.nam.stub;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per signXAdESRemote complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * <pre>
 * &lt;complexType name="signXAdESRemote"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="credentials" type="{http://service.ws.nam/}credentials" minOccurs="0"/&gt;
 *         &lt;element name="buffer" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="XAdESPreferences" type="{http://service.ws.nam/}xAdESPreferences" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signXAdESRemote", propOrder = { "credentials", "buffer", "xAdESPreferences" })
public class SignXAdESRemote {

	protected Credentials credentials;
	protected byte[] buffer;
	@XmlElement(name = "XAdESPreferences")
	protected XAdESPreferences xAdESPreferences;

	/**
	 * Recupera il valore della proprietà credentials.
	 * 
	 * @return possible object is {@link Credentials }
	 * 
	 */
	public Credentials getCredentials() {
		return credentials;
	}

	/**
	 * Imposta il valore della proprietà credentials.
	 * 
	 * @param value allowed object is {@link Credentials }
	 * 
	 */
	public void setCredentials(Credentials value) {
		this.credentials = value;
	}

	/**
	 * Recupera il valore della proprietà buffer.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getBuffer() {
		return buffer;
	}

	/**
	 * Imposta il valore della proprietà buffer.
	 * 
	 * @param value allowed object is byte[]
	 */
	public void setBuffer(byte[] value) {
		this.buffer = value;
	}

	/**
	 * Recupera il valore della proprietà xAdESPreferences.
	 * 
	 * @return possible object is {@link XAdESPreferences }
	 * 
	 */
	public XAdESPreferences getXAdESPreferences() {
		return xAdESPreferences;
	}

	/**
	 * Imposta il valore della proprietà xAdESPreferences.
	 * 
	 * @param value allowed object is {@link XAdESPreferences }
	 * 
	 */
	public void setXAdESPreferences(XAdESPreferences value) {
		this.xAdESPreferences = value;
	}

}
