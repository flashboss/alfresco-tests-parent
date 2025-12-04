package it.vige.nam.stub;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Classe Java per signEngineImpl.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <p>
 *
 * <pre>
 * &lt;simpleType name="signEngineImpl"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="LOCAL"/&gt;
 *     &lt;enumeration value="STATIC"/&gt;
 *     &lt;enumeration value="DYNAMIC"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 *
 * @author vige
 */
@XmlType(name = "signEngineImpl")
@XmlEnum
public enum SignEngineImpl {
  LOCAL,
  STATIC,
  DYNAMIC;

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
  public static SignEngineImpl fromValue(String v) {
    return valueOf(v);
  }
}
