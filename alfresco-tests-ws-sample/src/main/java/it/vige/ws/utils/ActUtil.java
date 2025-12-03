package it.vige.ws.utils;

import org.alfresco.service.namespace.QName;

/**
 * Utility class containing constants for the Acts content model.
 * Defines QNames and property names for working with act documents.
 *
 * @author lucastancapiano
 */
public class ActUtil {

	/** The namespace URI for the CRL Acts content model. */
	public static final String CRL_ACTS_MODEL = "http://www.vige.it/content/model/acts/1.0";

	/** The QName for the act PDL type. */
	public static final QName TYPE_ACT_PDL = QName.createQName(CRL_ACTS_MODEL, "actPdl");

	/** The property name for the act state. */
	public static final String PROP_STATE_ACT = "stateAct";

	/** The property name for the legislature. */
	public static final String PROP_LEGISLATURE = "legislature";

	/** The property name for the act number. */
	public static final String PROP_NUM_ACT = "numeroAct";

	/** The property name for the act object/subject. */
	public static final String PROP_OGGETTO_ACT = "oggetto";

	/** The QName for the act object/subject property. */
	public static final QName PROP_OGGETTO_ACT_QNAME = QName.createQName(CRL_ACTS_MODEL, PROP_OGGETTO_ACT);

	/** The QName for the legislature property. */
	public static final QName PROP_LEGISLATURE_QNAME = QName.createQName(CRL_ACTS_MODEL, PROP_LEGISLATURE);

	/** The QName for the act number property. */
	public static final QName PROP_NUMERO_ACT_QNAME = QName.createQName(CRL_ACTS_MODEL, PROP_NUM_ACT);

	/** The QName for the act state property. */
	public static final QName PROP_STATE_ACT_QNAME = QName.createQName(CRL_ACTS_MODEL, PROP_STATE_ACT);
}
