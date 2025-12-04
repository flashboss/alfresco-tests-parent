
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per signCAdES complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * <pre>
 * &lt;complexType name="signCAdES"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="buffer" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="CAdESPreferences" type="{http://service.ws.nam/}cAdESPreferences" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signCAdES", propOrder = { "username", "password", "buffer", "cAdESPreferences" })
/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public class SignCAdES {
	/** The username. */
	protected String username;
	/** The password. */
	protected String password;
	protected byte[] buffer;
	@XmlElement(name = "CAdESPreferences")
	/** The c ad e s preferences. */
	protected CAdESPreferences cAdESPreferences;

	/**
	 * Recupera il valore della proprietà username.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Imposta il valore della proprietà username.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setUsername(String value) {
		this.username = value;
	}
	/**
	 * Recupera il valore della proprietà password.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Imposta il valore della proprietà password.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setPassword(String value) {
		this.password = value;
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
	 * Recupera il valore della proprietà cAdESPreferences.
	 * 
	 * @return possible object is {@link CAdESPreferences }
	 * 
	 */
	public CAdESPreferences getCAdESPreferences() {
		return cAdESPreferences;
	}
	/**
	 * Imposta il valore della proprietà cAdESPreferences.
	 * 
	 * @param value allowed object is {@link CAdESPreferences }
	 * 
	 */
	public void setCAdESPreferences(CAdESPreferences value) {
		this.cAdESPreferences = value;
	}

}
