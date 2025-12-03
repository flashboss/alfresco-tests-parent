
package it.vige.nam.stub;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per signCAdESArrayListWithMultiPreferences complex type.
 *
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 *
 * <pre>
 * &lt;complexType name="signCAdESArrayListWithMultiPreferences"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="credentials" type="{http://service.ws.nam/}credentials" minOccurs="0"/&gt;
 *         &lt;element name="bufferList" type="{http://www.w3.org/2001/XMLSchema}base64Binary" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="CAdESPreferences" type="{http://service.ws.nam/}cAdESPreferences" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signCAdESArrayListWithMultiPreferences", propOrder = { "credentials", "bufferList",
		"cAdESPreferences" })
public class SignCAdESArrayListWithMultiPreferences {

	protected Credentials credentials;
	protected List<byte[]> bufferList;
	@XmlElement(name = "CAdESPreferences")
	protected List<CAdESPreferences> cAdESPreferences;

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
	 * Gets the value of the cAdESPreferences property.
	 *
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the cAdESPreferences property.
	 *
	 * <p>
	 * For example, to add a new item, do as follows:
	 *
	 * <pre>
	 * getCAdESPreferences().add(newItem);
	 * </pre>
	 *
	 *
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link CAdESPreferences }
	 *
	 *
	 */
	public List<CAdESPreferences> getCAdESPreferences() {
		if (cAdESPreferences == null) {
			cAdESPreferences = new ArrayList<CAdESPreferences>();
		}
		return this.cAdESPreferences;
	}

}
