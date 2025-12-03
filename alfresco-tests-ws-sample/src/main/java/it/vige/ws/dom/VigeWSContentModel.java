package it.vige.ws.dom;

import org.alfresco.service.namespace.QName;

/**
 * Model constants defining QNames and namespace URIs.
 * 
 * @author vige
 */
public abstract class VigeWSContentModel {

    private VigeWSContentModel() { }

    /** The vige ws namespace. */
    public static final String VIGE_WS_NAMESPACE = "http://vige.it/model/alfresco-tests-vigews/1.0";

    /** The doc aspect. */
    public static final QName DOC_ASPECT= QName.createQName(VIGE_WS_NAMESPACE,"documentsbank");

    // metadata documents
    public static final QName ID_DOC= QName.createQName(VIGE_WS_NAMESPACE,"id_doc");
    /** The categoria tipo doc. */
    public static final QName CATEGORIA_TIPO_DOC= QName.createQName(VIGE_WS_NAMESPACE,"categoria_tipo_doc");
    /** The codice doc. */
    public static final QName CODICE_DOC= QName.createQName(VIGE_WS_NAMESPACE,"codice_doc");
    /** The data emissione doc. */
    public static final QName DATA_EMISSIONE_DOC= QName.createQName(VIGE_WS_NAMESPACE,"data_emissione_doc");
    /** The data scadenza doc. */
    public static final QName DATA_SCADENZA_DOC= QName.createQName(VIGE_WS_NAMESPACE,"data_scadenza_doc");
    /** The data creazione pratica. */
    public static final QName DATA_CREAZIONE_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"data_creazione_pratica");
    /** The nome file. */
    public static final QName NOME_FILE= QName.createQName(VIGE_WS_NAMESPACE,"nome_file");
    /** The desc doc. */
    public static final QName DESC_DOC= QName.createQName(VIGE_WS_NAMESPACE,"desc_doc");
    /** The desc tipo doc. */
    public static final QName DESC_TIPO_DOC= QName.createQName(VIGE_WS_NAMESPACE,"desc_tipo_doc");
    /** The id partner. */
    public static final QName ID_PARTNER= QName.createQName(VIGE_WS_NAMESPACE,"id_partner");
    /** The id pratica. */
    public static final QName ID_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"id_pratica");
    /** The id user. */
    public static final QName ID_USER= QName.createQName(VIGE_WS_NAMESPACE,"id_user");
    /** The tipo doc. */
    public static final QName TIPO_DOC= QName.createQName(VIGE_WS_NAMESPACE,"tipo_doc");
    /** The trattamento doc. */
    public static final QName TRATTAMENTO_DOC= QName.createQName(VIGE_WS_NAMESPACE,"trattamento_doc");
    /** The numero doc. */
    public static final QName NUMERO_DOC= QName.createQName(VIGE_WS_NAMESPACE,"numero_doc");
    /** The n o t e. */
    public static final QName NOTE= QName.createQName(VIGE_WS_NAMESPACE,"note");
    /** The cod generation. */
    public static final QName COD_GENERATION= QName.createQName(VIGE_WS_NAMESPACE,"cod_generation");

    // metadata practice
    public static final QName ID_PRAT= QName.createQName(VIGE_WS_NAMESPACE,"id_prat");
    /** The id partner pratica. */
    public static final QName ID_PARTNER_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"id_partner_pratica");
    /** The desc partner pratica. */
    public static final QName DESC_PARTNER_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"desc_partner_pratica");
    /** The desc convenz pratica. */
    public static final QName DESC_CONVENZ_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"desc_convenzione_pratica");
    /** The rag soc pratica. */
    public static final QName RAG_SOC_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"rag_soc_pratica");
    /** The cod fis pratica. */
    public static final QName COD_FIS_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"cod_fis_pratica");
    /** The data creaz pratica. */
    public static final QName DATA_CREAZ_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"data_creazione_pratica");
}
