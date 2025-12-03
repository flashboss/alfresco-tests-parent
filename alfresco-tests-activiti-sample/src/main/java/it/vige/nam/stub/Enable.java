
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
* <p>
* Classe Java per enable complex type.
*
* <p>
* Il seguente frammento di schema specifica il contenuto previsto contenuto in
* questa classe.
*
* <pre>
* &lt;complexType name="enable"&gt;
*   &lt;complexContent&gt;
*     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
*       &lt;sequence&gt;
*         &lt;element name="securityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
*         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
*         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
*       &lt;/sequence&gt;
*     &lt;/restriction&gt;
*   &lt;/complexContent&gt;
* &lt;/complexType&gt;
* </pre>
*
*
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enable", propOrder = { "securityCode", "username", "password" })
public class Enable {

/**
* The security code.
 */
	protected String securityCode;
/**
* The username.
 */
	protected String username;
/**
* The password.
 */
	protected String password;

/**
* Recupera il valore della proprietà securityCode.
*
* @return possible object is {@link String }
*
 */
	public String getSecurityCode() {
		return securityCode;
	}

/**
* Imposta il valore della proprietà securityCode.
*
* @param value allowed object is {@link String }
*
 */
	public void setSecurityCode(String value) {
		this.securityCode = value;
	}

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

}
