
package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java per result.
 * 
 * <p>
 * Il seguente frammento di schema specifica il contenuto previsto contenuto in
 * questa classe.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="result"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="VALID"/&gt;
 *     &lt;enumeration value="INVALID"/&gt;
 *     &lt;enumeration value="UNDETERMINED"/&gt;
 *     &lt;enumeration value="VALID_WITH_WARNINGS"/&gt;
 *     &lt;enumeration value="INFORMATION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 *
 *
 * @author vige
 
 */
@XmlType(name = "result")
@XmlEnum
public enum Result {

	VALID, INVALID, UNDETERMINED, VALID_WITH_WARNINGS, INFORMATION;

	/**
	 * Value.
	 *
	 * @return the result
	 */
	public String value() {
		return name();
	}

	/**
	 * From value.
	 *
	 * @param v the v
	 * @return the result
	 */
	public static Result fromValue(String v) {
		return valueOf(v);
	}

}
