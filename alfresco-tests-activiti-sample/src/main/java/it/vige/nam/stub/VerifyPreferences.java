
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Classe Java per verifyPreferences complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * <pre>
 * &lt;complexType name="verifyPreferences"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nam" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="pdfEncryptionPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="verifyOnDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "verifyPreferences", propOrder = { "nam", "pdfEncryptionPassword", "verifyOnDate" })
/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public class VerifyPreferences {

	protected boolean nam;
	protected String pdfEncryptionPassword;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar verifyOnDate;

	/**
	 * Recupera il valore della proprietà nam.
	 * 
	 */
	public boolean isNam() {
		return nam;
	}

	/**
	 * Imposta il valore della proprietà nam.
	 * 
	 */
	public void setNam(boolean value) {
		this.nam = value;
	}

	/**
	 * Recupera il valore della proprietà pdfEncryptionPassword.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPdfEncryptionPassword() {
		return pdfEncryptionPassword;
	}

	/**
	 * Imposta il valore della proprietà pdfEncryptionPassword.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setPdfEncryptionPassword(String value) {
		this.pdfEncryptionPassword = value;
	}

	/**
	 * Recupera il valore della proprietà verifyOnDate.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getVerifyOnDate() {
		return verifyOnDate;
	}

	/**
	 * Imposta il valore della proprietà verifyOnDate.
	 * 
	 * @param value allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setVerifyOnDate(XMLGregorianCalendar value) {
		this.verifyOnDate = value;
	}

}
