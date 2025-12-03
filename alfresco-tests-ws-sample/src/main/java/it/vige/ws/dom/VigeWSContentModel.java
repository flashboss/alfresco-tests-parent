package it.vige.ws.dom;

import org.alfresco.service.namespace.QName;

/**
 * Content model constants for Vige WebService.
 * Defines all QNames used for document and practice metadata in the Alfresco content model.
 *
 * @author lucastancapiano
 */
public abstract class VigeWSContentModel {

    /**
     * Private constructor to prevent instantiation.
     */
    private VigeWSContentModel() { }

    /** The Vige WebService namespace URI. */
    public static final String VIGE_WS_NAMESPACE = "http://vige.it/model/alfresco-tests-vigews/1.0";

    /** The document aspect QName. */
    public static final QName DOC_ASPECT= QName.createQName(VIGE_WS_NAMESPACE,"documentsbank");

    // metadata documents
    /** The document identifier property. */
    public static final QName ID_DOC= QName.createQName(VIGE_WS_NAMESPACE,"id_doc");

    /** The document type category property. */
    public static final QName CATEGORIA_TIPO_DOC= QName.createQName(VIGE_WS_NAMESPACE,"categoria_tipo_doc");

    /** The document code property. */
    public static final QName CODICE_DOC= QName.createQName(VIGE_WS_NAMESPACE,"codice_doc");

    /** The document issue date property. */
    public static final QName DATA_EMISSIONE_DOC= QName.createQName(VIGE_WS_NAMESPACE,"data_emissione_doc");

    /** The document expiry date property. */
    public static final QName DATA_SCADENZA_DOC= QName.createQName(VIGE_WS_NAMESPACE,"data_scadenza_doc");

    /** The practice creation date property. */
    public static final QName DATA_CREAZIONE_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"data_creazione_pratica");

    /** The file name property. */
    public static final QName NOME_FILE= QName.createQName(VIGE_WS_NAMESPACE,"nome_file");

    /** The document description property. */
    public static final QName DESC_DOC= QName.createQName(VIGE_WS_NAMESPACE,"desc_doc");

    /** The document type description property. */
    public static final QName DESC_TIPO_DOC= QName.createQName(VIGE_WS_NAMESPACE,"desc_tipo_doc");

    /** The partner identifier property. */
    public static final QName ID_PARTNER= QName.createQName(VIGE_WS_NAMESPACE,"id_partner");

    /** The practice identifier property. */
    public static final QName ID_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"id_pratica");

    /** The user identifier property. */
    public static final QName ID_USER= QName.createQName(VIGE_WS_NAMESPACE,"id_user");

    /** The document type property. */
    public static final QName TIPO_DOC= QName.createQName(VIGE_WS_NAMESPACE,"tipo_doc");

    /** The document treatment property. */
    public static final QName TRATTAMENTO_DOC= QName.createQName(VIGE_WS_NAMESPACE,"trattamento_doc");

    /** The document number property. */
    public static final QName NUMERO_DOC= QName.createQName(VIGE_WS_NAMESPACE,"numero_doc");

    /** The notes property. */
    public static final QName NOTE= QName.createQName(VIGE_WS_NAMESPACE,"note");

    /** The generation code property. */
    public static final QName COD_GENERATION= QName.createQName(VIGE_WS_NAMESPACE,"cod_generation");

    // metadata practice
    /** The practice ID property. */
    public static final QName ID_PRAT= QName.createQName(VIGE_WS_NAMESPACE,"id_prat");

    /** The partner ID for practice property. */
    public static final QName ID_PARTNER_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"id_partner_pratica");

    /** The partner description for practice property. */
    public static final QName DESC_PARTNER_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"desc_partner_pratica");

    /** The agreement description for practice property. */
    public static final QName DESC_CONVENZ_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"desc_convenzione_pratica");

    /** The business name for practice property. */
    public static final QName RAG_SOC_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"rag_soc_pratica");

    /** The fiscal code for practice property. */
    public static final QName COD_FIS_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"cod_fis_pratica");

    /** The creation date for practice property. */
    public static final QName DATA_CREAZ_PRATICA= QName.createQName(VIGE_WS_NAMESPACE,"data_creazione_pratica");
}
