
package it.vige.nam.stub;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

/**
* <p>
* Classe Java per cAdESPreferences complex type.
*
* <p>
* Il seguente frammento di schema specifica il contenuto previsto contenuto in
* questa classe.
*
* <pre>
* &lt;complexType name="cAdESPreferences"&gt;
*   &lt;complexContent&gt;
*     &lt;extension base="{http://service.ws.nam/}signPreferences"&gt;
*       &lt;sequence&gt;
*       &lt;/sequence&gt;
*     &lt;/extension&gt;
*   &lt;/complexContent&gt;
* &lt;/complexType&gt;
* </pre>
*
*
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cAdESPreferences")
public class CAdESPreferences extends SignPreferences {

}
