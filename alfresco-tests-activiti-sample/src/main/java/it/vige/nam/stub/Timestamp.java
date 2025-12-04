package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe Java per timestamp complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="timestamp"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="preferences" type="{http://service.ws.nam/}timeStampPreferences" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "timestamp",
    propOrder = {"content", "preferences"})
/**
 * Class providing functionality for Alfresco testing.
 *
 * @author vige
 */
public class Timestamp {

  protected byte[] content;

  /** The preferences. */
  protected TimeStampPreferences preferences;

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

  /**
   * Recupera il valore della proprietà preferences.
   *
   * @return possible object is {@link TimeStampPreferences }
   */
  public TimeStampPreferences getPreferences() {
    return preferences;
  }

  /**
   * Imposta il valore della proprietà preferences.
   *
   * @param value allowed object is {@link TimeStampPreferences }
   */
  public void setPreferences(TimeStampPreferences value) {
    this.preferences = value;
  }
}
