
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per timestampTSRVerify complex type.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * 
 * <pre>
 * &lt;complexType name="timestampTSRVerify"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tsr" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "timestampTSRVerify", propOrder = { "tsr", "content" })
public class TimestampTSRVerify {

	protected byte[] tsr;
	protected byte[] content;

	/**
	 * Recupera il valore della proprietà tsr.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getTsr() {
		return tsr;
	}

	/**
	 * Imposta il valore della proprietà tsr.
	 * 
	 * @param value allowed object is byte[]
	 */
	public void setTsr(byte[] value) {
		this.tsr = value;
	}

	/**
	 * Recupera il valore della proprietà content.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * Imposta il valore della proprietà content.
	 * 
	 * @param value allowed object is byte[]
	 */
	public void setContent(byte[] value) {
		this.content = value;
	}

}
