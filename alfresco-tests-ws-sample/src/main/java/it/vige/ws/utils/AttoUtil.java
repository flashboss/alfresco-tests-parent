package it.vige.ws.utils;

import org.alfresco.service.namespace.QName;
public class AttoUtil {

	public static final String CRL_TEMPLATE_MODEL = "http://www.regione.lombardia.it/content/model/template/1.0";
	public static final String CRL_ATTI_MODEL = "http://www.regione.lombardia.it/content/model/atti/1.0";
	public static final QName TYPE_ATTO_PDL = QName.createQName(CRL_ATTI_MODEL, "attoPdl");
	public static final String PROP_STATO_ATTO = "statoAtto";
	public static final String PROP_LEGISLATURA = "legislatura";
	public static final String PROP_NUM_ATTO = "numeroAtto";
	public static final String PROP_OGGETTO_ATTO = "oggetto";
	public static final QName PROP_OGGETTO_ATTO_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_OGGETTO_ATTO);
	public static final String PROP_NUMERO_LCR_ATTO = "numeroLcr";
	public static final String PROP_TIPO_INIZIATIVA = "tipoIniziativa";

	public static final QName PROP_LEGISLATURA_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_LEGISLATURA);
	public static final QName PROP_NUMERO_ATTO_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_NUM_ATTO);
	
	public static final QName PROP_STATO_ATTO_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_STATO_ATTO);
	public static final QName PROP_TIPO_INIZIATIVA_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_TIPO_INIZIATIVA);

}
