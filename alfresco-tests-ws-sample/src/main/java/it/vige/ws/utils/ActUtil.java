package it.vige.ws.utils;

import org.alfresco.service.namespace.QName;

/**
 * Utility class providing helper methods.
 *
 * @author vige
 */
public class ActUtil {

  public static final String CRL_ACTS_MODEL = "http://www.vige.it/content/model/acts/1.0";
  public static final QName TYPE_ACT_PDL = QName.createQName(CRL_ACTS_MODEL, "actPdl");
  public static final String PROP_STATE_ACT = "stateAct";
  public static final String PROP_LEGISLATURE = "legislature";
  public static final String PROP_NUM_ACT = "numeroAct";
  public static final String PROP_OGGETTO_ACT = "oggetto";
  public static final QName PROP_OGGETTO_ACT_QNAME =
      QName.createQName(CRL_ACTS_MODEL, PROP_OGGETTO_ACT);

  public static final QName PROP_LEGISLATURE_QNAME =
      QName.createQName(CRL_ACTS_MODEL, PROP_LEGISLATURE);
  public static final QName PROP_NUMERO_ACT_QNAME = QName.createQName(CRL_ACTS_MODEL, PROP_NUM_ACT);

  public static final QName PROP_STATE_ACT_QNAME =
      QName.createQName(CRL_ACTS_MODEL, PROP_STATE_ACT);
}
