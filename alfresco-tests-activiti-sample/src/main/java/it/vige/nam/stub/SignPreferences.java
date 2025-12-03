
package it.vige.nam.stub;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signPreferences", propOrder = { "hashAlgorithm", "signEngineImpl", "signType", "withTimestamp" })
@XmlSeeAlso({ CAdESPreferences.class })
/**
* <p>
* Classe Java per signPreferences complex type.
*
* <p>
* Il seguente frammento di schema specifica il contenuto previsto contenuto in
* questa classe.
*
* <pre>
* &lt;complexType name="signPreferences"&gt;
*   &lt;complexContent&gt;
*     &lt;extension base="{http://service.ws.nam/}timeStampPreferences"&gt;
*       &lt;sequence&gt;
*         &lt;element name="hashAlgorithm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
*         &lt;element name="signEngineImpl" type="{http://service.ws.nam/}signEngineImpl" minOccurs="0"/&gt;
*         &lt;element name="signType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
*         &lt;element name="withTimestamp" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
*       &lt;/sequence&gt;
*     &lt;/extension&gt;
*   &lt;/complexContent&gt;
* &lt;/complexType&gt;
* </pre>
*
*
 */
public class SignPreferences extends TimeStampPreferences {

/**
* The hash algorithm.
 */
	protected String hashAlgorithm;
	@XmlSchemaType(name = "string")
/**
* The sign engine impl.
 */
	protected SignEngineImpl signEngineImpl;
/**
* The sign type.
 */
	protected int signType;
/**
* The with timestamp.
 */
	protected boolean withTimestamp;

/**
* Recupera il valore della proprietà hashAlgorithm.
*
* @return possible object is {@link String }
*
 */
	public String getHashAlgorithm() {
		return hashAlgorithm;
	}

/**
* Imposta il valore della proprietà hashAlgorithm.
*
* @param value allowed object is {@link String }
*
 */
	public void setHashAlgorithm(String value) {
		this.hashAlgorithm = value;
	}

/**
* Recupera il valore della proprietà signEngineImpl.
*
* @return possible object is {@link SignEngineImpl }
*
 */
	public SignEngineImpl getSignEngineImpl() {
		return signEngineImpl;
	}

/**
* Imposta il valore della proprietà signEngineImpl.
*
* @param value allowed object is {@link SignEngineImpl }
*
 */
	public void setSignEngineImpl(SignEngineImpl value) {
		this.signEngineImpl = value;
	}

/**
* Recupera il valore della proprietà signType.
* @return the result
 */
	public int getSignType() {
		return signType;
	}

/**
* Imposta il valore della proprietà signType.
* @param value the value
 */
	public void setSignType(int value) {
		this.signType = value;
	}

/**
* Recupera il valore della proprietà withTimestamp.
* @return the result
 */
	public boolean isWithTimestamp() {
		return withTimestamp;
	}

/**
* Imposta il valore della proprietà withTimestamp.
* @param value the value
 */
	public void setWithTimestamp(boolean value) {
		this.withTimestamp = value;
	}

}
