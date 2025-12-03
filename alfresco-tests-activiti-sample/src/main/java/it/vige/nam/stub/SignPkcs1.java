
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per signPkcs1 complex type.
 *
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 *
 * <pre>
 * &lt;complexType name="signPkcs1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="credentials" type="{http://service.ws.nam/}credentials" minOccurs="0"/&gt;
 *         &lt;element name="hash" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="preferences" type="{http://service.ws.nam/}signPreferences" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signPkcs1", propOrder = { "credentials", "hash", "preferences" })
public class SignPkcs1 {

	protected Credentials credentials;
	protected byte[] hash;
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
	 * Recupera il valore della proprietà hash.
	 *
	 * @return possible object is byte[]
	 */
	public byte[] getHash() {
		return hash;
	}

	/**
	 * Imposta il valore della proprietà hash.
	 *
	 * @param value allowed object is byte[]
	 */
	public void setHash(byte[] value) {
		this.hash = value;
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
