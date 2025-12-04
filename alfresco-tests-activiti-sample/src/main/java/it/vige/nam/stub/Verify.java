package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe Java per verify complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="verify"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="signedContent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
/**
 * Class providing functionality for Alfresco testing.
 *
 * @author vige
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "verify",
    propOrder = {"signedContent"})
public class Verify {

  protected byte[] signedContent;

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
}
