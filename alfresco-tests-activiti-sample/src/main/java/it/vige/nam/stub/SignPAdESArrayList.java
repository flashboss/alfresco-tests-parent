
package it.vige.nam.stub;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per signPAdESArrayList complex type.
 * 
 * @author vige
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * @author vige
 * <pre>
 * &lt;complexType name="signPAdESArrayList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="bufferList" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="PAdESPreferences" type="{http://service.ws.nam/}pAdESPreferences" minOccurs="0"/&gt;
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
@XmlType(name = "signPAdESArrayList", propOrder = { "username", "password", "bufferList", "pAdESPreferences" })
public class SignPAdESArrayList {

	protected String username;
	protected String password;
	protected List<byte[]> bufferList;
	@XmlElement(name = "PAdESPreferences")
	protected PAdESPreferences pAdESPreferences;

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
	 * Gets the value of the bufferList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the bufferList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getBufferList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list byte[]
	 * 
	 */
	public List<byte[]> getBufferList() {
		if (bufferList == null) {
			bufferList = new ArrayList<byte[]>();
		}
		return this.bufferList;
	}

	/**
	 * Recupera il valore della proprietà pAdESPreferences.
	 * 
	 * @return possible object is {@link PAdESPreferences }
	 * 
	 */
	public PAdESPreferences getPAdESPreferences() {
		return pAdESPreferences;
	}

	/**
	 * Imposta il valore della proprietà pAdESPreferences.
	 * 
	 * @param value allowed object is {@link PAdESPreferences }
	 * 
	 */
	public void setPAdESPreferences(PAdESPreferences value) {
		this.pAdESPreferences = value;
	}

}
