
package it.vige.nam.stub;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

/**
* <p>
* Classe Java per verifyWithPreferences complex type.
*
* <p>
* Il seguente frammento di schema specifica il contenuto previsto contenuto in
* questa classe.
*
* <pre>
* &lt;complexType name="verifyWithPreferences"&gt;
*   &lt;complexContent&gt;
*     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
*       &lt;sequence&gt;
*         &lt;element name="signedContent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
*         &lt;element name="preferences" type="{http://service.ws.nam/}verifyPreferences" minOccurs="0"/&gt;
*       &lt;/sequence&gt;
*     &lt;/restriction&gt;
*   &lt;/complexContent&gt;
* &lt;/complexType&gt;
* </pre>
*
*
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verifyWithPreferences", propOrder = { "signedContent", "preferences" })
public class VerifyWithPreferences {

	protected byte[] signedContent;
/**
* The preferences.
 */
	protected VerifyPreferences preferences;

/**
* Recupera il valore della proprietà signedContent.
*
* @return possible object is byte[]
 */
	public byte[] getSignedContent() {
		return signedContent;
	}

/**
* Imposta il valore della proprietà signedContent.
*
* @param value allowed object is byte[]
 */
	public void setSignedContent(byte[] value) {
		this.signedContent = value;
	}

/**
* Recupera il valore della proprietà preferences.
*
* @return possible object is {@link VerifyPreferences }
*
 */
	public VerifyPreferences getPreferences() {
		return preferences;
	}

/**
* Imposta il valore della proprietà preferences.
*
* @param value allowed object is {@link VerifyPreferences }
*
 */
	public void setPreferences(VerifyPreferences value) {
		this.preferences = value;
	}

}
