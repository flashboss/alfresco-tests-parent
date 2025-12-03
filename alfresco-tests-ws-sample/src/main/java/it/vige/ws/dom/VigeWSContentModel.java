package it.vige.ws.dom;

import org.alfresco.service.namespace.QName;

public abstract class VigeWSContentModel {

  /** Constructs a new VigeWSContentModel instance. */
  private VigeWSContentModel() {}

  public static final String VIGE_WS_NAMESPACE = "http://vige.it/model/alfresco-tests-vigews/1.0";

  public static final QName DOC_ASPECT = QName.createQName(VIGE_WS_NAMESPACE, "documentsbank");

  // metadata documents
  public static final QName ID_DOC = QName.createQName(VIGE_WS_NAMESPACE, "id_doc");
  public static final QName CATEGORIA_TIPO_DOC =
      QName.createQName(VIGE_WS_NAMESPACE, "categoria_tipo_doc");
  public static final QName CODICE_DOC = QName.createQName(VIGE_WS_NAMESPACE, "codice_doc");
  public static final QName DATA_EMISSIONE_DOC =
      QName.createQName(VIGE_WS_NAMESPACE, "data_emissione_doc");
  public static final QName DATA_SCADENZA_DOC =
      QName.createQName(VIGE_WS_NAMESPACE, "data_scadenza_doc");
  public static final QName DATA_CREAZIONE_PRATICA =
      QName.createQName(VIGE_WS_NAMESPACE, "data_creazione_pratica");
  public static final QName NOME_FILE = QName.createQName(VIGE_WS_NAMESPACE, "nome_file");
  public static final QName DESC_DOC = QName.createQName(VIGE_WS_NAMESPACE, "desc_doc");
  public static final QName DESC_TIPO_DOC = QName.createQName(VIGE_WS_NAMESPACE, "desc_tipo_doc");
  public static final QName ID_PARTNER = QName.createQName(VIGE_WS_NAMESPACE, "id_partner");
  public static final QName ID_PRATICA = QName.createQName(VIGE_WS_NAMESPACE, "id_pratica");
  public static final QName ID_USER = QName.createQName(VIGE_WS_NAMESPACE, "id_user");
  public static final QName TIPO_DOC = QName.createQName(VIGE_WS_NAMESPACE, "tipo_doc");
  public static final QName TRATTAMENTO_DOC =
      QName.createQName(VIGE_WS_NAMESPACE, "trattamento_doc");
  public static final QName NUMERO_DOC = QName.createQName(VIGE_WS_NAMESPACE, "numero_doc");
  public static final QName NOTE = QName.createQName(VIGE_WS_NAMESPACE, "note");
  public static final QName COD_GENERATION = QName.createQName(VIGE_WS_NAMESPACE, "cod_generation");

  // metadata practice
  public static final QName ID_PRAT = QName.createQName(VIGE_WS_NAMESPACE, "id_prat");
  public static final QName ID_PARTNER_PRATICA =
      QName.createQName(VIGE_WS_NAMESPACE, "id_partner_pratica");
  public static final QName DESC_PARTNER_PRATICA =
      QName.createQName(VIGE_WS_NAMESPACE, "desc_partner_pratica");
  public static final QName DESC_CONVENZ_PRATICA =
      QName.createQName(VIGE_WS_NAMESPACE, "desc_convenzione_pratica");
  public static final QName RAG_SOC_PRATICA =
      QName.createQName(VIGE_WS_NAMESPACE, "rag_soc_pratica");
  public static final QName COD_FIS_PRATICA =
      QName.createQName(VIGE_WS_NAMESPACE, "cod_fis_pratica");
  public static final QName DATA_CREAZ_PRATICA =
      QName.createQName(VIGE_WS_NAMESPACE, "data_creazione_pratica");
}
