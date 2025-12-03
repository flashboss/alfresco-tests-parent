
package it.vige.nam.stub;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per signPkcs1ArrayList complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * <pre>
 * &lt;complexType name="signPkcs1ArrayList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="credentials" type="{http://service.ws.nam/}credentials" minOccurs="0"/&gt;
 *         &lt;element name="hashList" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="preferences" type="{http://service.ws.nam/}signPreferences" minOccurs="0"/&gt;
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
@XmlType(name = "signPkcs1ArrayList", propOrder = { "credentials", "hashList", "preferences" })
public class SignPkcs1ArrayList {

	/** The credentials. */
	protected Credentials credentials;
	/** The hash list. */
	protected List<byte[]> hashList;
	/** The preferences. */
	protected SignPreferences preferences;

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
	 * Gets the value of the hashList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the hashList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getHashList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list byte[]
	 * 
	 */
	public List<byte[]> getHashList() {
		if (hashList == null) {
			hashList = new ArrayList<byte[]>();
		}
		return this.hashList;
	}

	/**
	 * Recupera il valore della proprietà preferences.
	 * 
	 * @return possible object is {@link SignPreferences }
	 * 
	 */
	public SignPreferences getPreferences() {
		return preferences;
	}

	/**
	 * Imposta il valore della proprietà preferences.
	 * 
	 * @param value allowed object is {@link SignPreferences }
	 * 
	 */
	public void setPreferences(SignPreferences value) {
		this.preferences = value;
	}

}
