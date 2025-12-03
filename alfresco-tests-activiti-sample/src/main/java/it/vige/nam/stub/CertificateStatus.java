
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per certificateStatus.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="certificateStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="VALID"/&gt;
 *     &lt;enumeration value="REVOKED"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "certificateStatus")
@XmlEnum
/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public enum CertificateStatus {

	VALID, REVOKED, UNKNOWN;

	/**
	 * Value.
	 *
	 * @return the string
	 */
	public String value() {
		return name();
	}

	/**
	 * From value.
	 *
	 * @param v the v
	 * @return the certificate status
	 */
	public static CertificateStatus fromValue(String v) {
		return valueOf(v);
	}

}
