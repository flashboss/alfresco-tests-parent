package it.vige.ws.utils;

import org.alfresco.service.namespace.QName;
/**
 * Utility class providing helper methods.
 * 
 * @author vige
 */
public class ActUtil {
	/** The crl acts model. */
	public static final String CRL_ACTS_MODEL = "http://www.vige.it/content/model/acts/1.0";
	/** The type act pdl. */
	public static final QName TYPE_ACT_PDL = QName.createQName(CRL_ACTS_MODEL, "actPdl");
	/** The prop state act. */
	public static final String PROP_STATE_ACT = "stateAct";
	/** The prop legislature. */
	public static final String PROP_LEGISLATURE = "legislature";
	/** The prop num act. */
	public static final String PROP_NUM_ACT = "numeroAct";
	/** The prop oggetto act. */
	public static final String PROP_OGGETTO_ACT = "oggetto";
	/** The prop oggetto act qname. */
	public static final QName PROP_OGGETTO_ACT_QNAME = QName.createQName(CRL_ACTS_MODEL, PROP_OGGETTO_ACT);

	/** The prop legislature qname. */
	public static final QName PROP_LEGISLATURE_QNAME = QName.createQName(CRL_ACTS_MODEL, PROP_LEGISLATURE);
	/** The prop numero act qname. */
	public static final QName PROP_NUMERO_ACT_QNAME = QName.createQName(CRL_ACTS_MODEL, PROP_NUM_ACT);
	
	/** The prop state act qname. */
	public static final QName PROP_STATE_ACT_QNAME = QName.createQName(CRL_ACTS_MODEL, PROP_STATE_ACT);
}
