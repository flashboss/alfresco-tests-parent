
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Classe Java per verifyOnDate complex type.
 *
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 *
 * <pre>
 * &lt;complexType name="verifyOnDate"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="signedContent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="verifyDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verifyOnDate", propOrder = { "signedContent", "verifyDate" })
public class VerifyOnDate {

	protected byte[] signedContent;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar verifyDate;

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
	 * Recupera il valore della proprietà verifyDate.
	 *
	 * @return possible object is {@link XMLGregorianCalendar }
	 *
	 */
	public XMLGregorianCalendar getVerifyDate() {
		return verifyDate;
	}

	/**
	 * Imposta il valore della proprietà verifyDate.
	 *
	 * @param value allowed object is {@link XMLGregorianCalendar }
	 *
	 */
	public void setVerifyDate(XMLGregorianCalendar value) {
		this.verifyDate = value;
	}

}
