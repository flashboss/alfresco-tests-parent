
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
* <p>
* Classe Java per timestampTSDVerify complex type.
*
* <p>
* Il seguente frammento di schema specifica il contenuto previsto contenuto in
* questa classe.
*
* <pre>
* &lt;complexType name="timestampTSDVerify"&gt;
*   &lt;complexContent&gt;
*     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
*       &lt;sequence&gt;
*         &lt;element name="tsd" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
*       &lt;/sequence&gt;
*     &lt;/restriction&gt;
*   &lt;/complexContent&gt;
* &lt;/complexType&gt;
* </pre>
*
*
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "timestampTSDVerify", propOrder = { "tsd" })
public class TimestampTSDVerify {

	protected byte[] tsd;

/**
* Recupera il valore della proprietà tsd.
*
* @return possible object is byte[]
 */
	public byte[] getTsd() {
		return tsd;
	}

/**
* Imposta il valore della proprietà tsd.
*
* @param value allowed object is byte[]
 */
	public void setTsd(byte[] value) {
		this.tsd = value;
	}

}
