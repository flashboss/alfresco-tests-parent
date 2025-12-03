
package it.vige.nam.stub;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Classe Java per signedDocumentReportBean complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * <pre>
 * &lt;complexType name="signedDocumentReportBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="checkDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="noteReportList" type="{http://service.ws.nam/}noteReportBean" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="nrOfSignatures" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="overallVerified" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="plainDocument" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="signatureFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="signatureReportList" type="{http://service.ws.nam/}signatureReportBean" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="verificationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "signedDocumentReportBean", propOrder = { "checkDate", "noteReportList", "nrOfSignatures",
		"overallVerified", "plainDocument", "signatureFormat", "signatureReportList", "verificationDate" })
/**
 * SignedDocumentReportBean implementation for testing purposes.
 *
 * @author vige
 */
public class SignedDocumentReportBean {

	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar checkDate;
	@XmlElement(nillable = true)
	protected List<NoteReportBean> noteReportList;
	protected int nrOfSignatures;
	protected boolean overallVerified;
	protected byte[] plainDocument;
	protected String signatureFormat;
	@XmlElement(nillable = true)
	protected List<SignatureReportBean> signatureReportList;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar verificationDate;

	/**
	 * Recupera il valore della proprietà checkDate.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCheckDate() {
		return checkDate;
	}

	/**
	 * Imposta il valore della proprietà checkDate.
	 * 
	 * @param value allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCheckDate(XMLGregorianCalendar value) {
		this.checkDate = value;
	}

	/**
	 * Gets the value of the noteReportList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the noteReportList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getNoteReportList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link NoteReportBean }
	 * 
	 * 
	 */
	public List<NoteReportBean> getNoteReportList() {
		if (noteReportList == null) {
			noteReportList = new ArrayList<NoteReportBean>();
		}
		return this.noteReportList;
	}

	/**
	 * Recupera il valore della proprietà nrOfSignatures.
	 * 
	 */
	public int getNrOfSignatures() {
		return nrOfSignatures;
	}

	/**
	 * Imposta il valore della proprietà nrOfSignatures.
	 * 
	 */
	public void setNrOfSignatures(int value) {
		this.nrOfSignatures = value;
	}

	/**
	 * Recupera il valore della proprietà overallVerified.
	 * 
	 */
	public boolean isOverallVerified() {
		return overallVerified;
	}

	/**
	 * Imposta il valore della proprietà overallVerified.
	 * 
	 */
	public void setOverallVerified(boolean value) {
		this.overallVerified = value;
	}

	/**
	 * Recupera il valore della proprietà plainDocument.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getPlainDocument() {
		return plainDocument;
	}

	/**
	 * Imposta il valore della proprietà plainDocument.
	 * 
	 * @param value allowed object is byte[]
	 */
	public void setPlainDocument(byte[] value) {
		this.plainDocument = value;
	}

	/**
	 * Recupera il valore della proprietà signatureFormat.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSignatureFormat() {
		return signatureFormat;
	}

	/**
	 * Imposta il valore della proprietà signatureFormat.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setSignatureFormat(String value) {
		this.signatureFormat = value;
	}

	/**
	 * Gets the value of the signatureReportList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a snapshot.
	 * Therefore any modification you make to the returned list will be present
	 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
	 * for the signatureReportList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getSignatureReportList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link SignatureReportBean }
	 * 
	 * 
	 */
	public List<SignatureReportBean> getSignatureReportList() {
		if (signatureReportList == null) {
			signatureReportList = new ArrayList<SignatureReportBean>();
		}
		return this.signatureReportList;
	}

	/**
	 * Recupera il valore della proprietà verificationDate.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getVerificationDate() {
		return verificationDate;
	}

	/**
	 * Imposta il valore della proprietà verificationDate.
	 * 
	 * @param value allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setVerificationDate(XMLGregorianCalendar value) {
		this.verificationDate = value;
	}

}
