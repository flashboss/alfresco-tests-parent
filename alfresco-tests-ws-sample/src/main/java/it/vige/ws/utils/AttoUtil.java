package it.vige.ws.utils;

import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.QName;

/**
 * Classe che serve come DAO per ottenere la business logic che riguarda il ciclo di vita di un atto e tutti gli attori che sono coinvolti: commissioni, ...
 * Si trovano tutte le costanti per mappare il content model della Regione Lombardia
 * @author sourcesense
 *
 */
public class AttoUtil {

	private SearchService searchService;
	private NodeService nodeService; 
	public static final String CRL_TEMPLATE_MODEL = "http://www.regione.lombardia.it/content/model/template/1.0";
	public static final String CRL_ATTI_MODEL = "http://www.regione.lombardia.it/content/model/atti/1.0"; 
	public static final String COMMISSIONE_TYPE = "commissione";
	public static final String RELATORE_TYPE = "relatore";
	public static final String PARERE_TYPE = "parere";
	public static final String FIRMATARIO_TYPE = "firmatario";
	public static final String TYPE_LEGISLATURA = "legislaturaAnagrafica";
	public static final QName TYPE_ATTO = QName.createQName(CRL_ATTI_MODEL, "atto");
	public static final QName TYPE_ATTO_PDL = QName.createQName(CRL_ATTI_MODEL, "attoPdl");
	public static final QName TYPE_ATTO_EAC = QName.createQName(CRL_ATTI_MODEL, "attoEac");
	public static final QName TYPE_AULA = QName.createQName(CRL_ATTI_MODEL, "aula");
	public static final QName COMMISSIONE_TYPE_QNAME = QName.createQName(CRL_ATTI_MODEL, COMMISSIONE_TYPE);
	public static final QName TYPE_RELATORE_QNAME = QName.createQName(CRL_ATTI_MODEL, RELATORE_TYPE);
	public static final String ABBINAMENTO_TYPE = "abbinamento";
	public static final String TYPE_TESTO = "testo";
	public static final QName TYPE_TESTO_QNAME = QName.createQName(CRL_ATTI_MODEL, TYPE_TESTO);
	public static final String TYPE_FIRMATARIO = "firmatario";
	public static final QName TYPE_FIRMATARIO_QNAME = QName.createQName(CRL_ATTI_MODEL, TYPE_FIRMATARIO);
	public static final String TYPE_ALLEGATO = "allegato";
	public static final QName TYPE_ALLEGATO_QNAME = QName.createQName(CRL_ATTI_MODEL, TYPE_ALLEGATO); 
	public static final String PROP_STATO_ATTO = "statoAtto";
	public static final String PROP_ANNO = "anno";
	public static final String PROP_MESE = "mese";
	public static final String PROP_LEGISLATURA = "legislatura";
	public static final String PROP_NUM_ATTO = "numeroAtto";
	public static final String PROP_ESTENSIONE_ATTO = "estensioneAtto";
	public static final String PROP_OGGETTO_ATTO = "oggetto";
	public static final QName PROP_OGGETTO_ATTO_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_OGGETTO_ATTO);
	public static final String PROP_NUMERO_LCR_ATTO = "numeroLcr";
	public static final String PROP_DATA_SEDUTA_AULA_ATTO = "dataSedutaAula";
	public static final String PROP_DATA_INIZIATIVA_ATTO = "dataIniziativa";
	public static final QName PROP_DATA_INIZIATIVA_ATTO_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_DATA_INIZIATIVA_ATTO);
	public static final String PROP_DESCRIZIONE_INIZIATIVA = "descrizioneIniziativa";
	public static final String PROP_TIPO_INIZIATIVA = "tipoIniziativa";
	public static final String PROP_DATA_SCADENZA = "dataScadenza";
	public static final String PROP_NUMERO_DGR = "numeroDgr";
	public static final String PROP_DATA_DGR = "dataDgr";
	public static final String PROP_NUMERO_DCR = "numeroDcr";
	public static final String PROP_NUMERO_DCR_PASSAGIO_AULA = "numeroDcrPassaggioAula";
	public static final String PROP_COMMISSIONI_REFERENTI = "commReferente";
	public static final QName PROP_COMMISSIONI_REFERENTI_QNAME =  QName.createQName(CRL_ATTI_MODEL, PROP_COMMISSIONI_REFERENTI);
	public static final String PROP_DATA_ASSEGNAZIONE_COMMISSIONI_REFERENTI = "dataAssegnazioneCommissioneReferente";
	public static final QName PROP_DATA_ASSEGNAZIONE_COMMISSIONI_REFERENTI_QNAME =  QName.createQName(CRL_ATTI_MODEL, PROP_DATA_ASSEGNAZIONE_COMMISSIONI_REFERENTI);


	public static final String PROP_COMMISSIONI_CONSULTIVE = "commConsultiva";
	public static final QName PROP_COMMISSIONI_CONSULTIVE_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_COMMISSIONI_CONSULTIVE);
	public static final String PROP_COMMISSIONE_COREFERENTE = "commCoreferente";
	public static final QName PROP_COMMISSIONE_COREFERENTE_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_COMMISSIONE_COREFERENTE);
	public static final String PROP_COMMISSIONE_REDIGENTE = "commRedigente";
	public static final QName PROP_COMMISSIONE_REDIGENTE_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_COMMISSIONE_REDIGENTE);
	public static final String PROP_COMMISSIONE_DELIBERANTE = "commDeliberante";
	public static final QName PROP_COMMISSIONE_DELIBERANTE_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_COMMISSIONE_DELIBERANTE);
	public static final String PROP_ORGANISMI_STATUTARI = "organismiStatutari";
	public static final String PROP_FIRMATARI = "firmatari";
	public static final String PROP_NUMERO_REPERTORIO = "numeroRepertorio";
	public static final String PROP_DATA_VOTAZIONE_COMMISSIONE = "dataVotazioneCommissione";
	public static final String PROP_DATA_ASSEGNAZIONE_COMMISSIONE = "dataAssegnazioneCommissione";
	public static final String PROP_DATA_ASSEGNAZIONE_PARERE = "dataAssegnazioneParere";
	public static final String PROP_DATA_FIRMA_FIRMATARIO = "dataFirma";

	public static final String PROP_TIPO_ATTO_INDIRIZZO = "tipoAttoIndirizzo";
	public static final String PROP_NUMERO_ATTO_INDIRIZZO = "numeroAttoIndirizzo";
	public static final String PROP_OGGETTO_ATTO_INDIRIZZO = "oggettoAttoIndirizzo";

	public static final String PROP_PRIMO_FIRMATARIO = "primoFirmatario";
	public static final String PROP_IS_PRIMO_FIRMATARIO = "isPrimoFirmatario";
	public static final String PROP_IS_FIRMATARIO_POPOLARE = "isFirmatarioPopolare";
	public static final String PROP_NOME_FIRMATARIO = "nomeFirmatario";
	public static final String PROP_DATA_NOMINA_RELATORE = "dataNominaRelatore";
	public static final String PROP_DATA_USCITA_RELATORE = "dataUscitaRelatore";
	

	public static final String ESITO_VOTO_PASSAGGIO_AULA = "esitoVotoPassaggioAula";
	public static final String DATA_SEDUTA_PASSAGGIO_AULA = "dataSedutaPassaggioAula";
	public static final String PROP_TIPOLOGIA = "tipologia";
	public static final String PROP_PUBBLICO = "pubblico";
	public static final String PROP_PUBBLICO_OPENDATA = "pubblicoOpendata";
	public static final String PROP_DATA_LR = "dataLr";
	public static final String PROP_NUMERO_LR = "numeroLr";

	public static final String TESTO_TIPIZZABILE = "testoTipizzabile";
	public static final String TIPOLOGIA_TESTO = "tipologiaTesto";
	public static final QName ASPECT_TESTO_TIPIZZABILE = QName.createQName(CRL_ATTI_MODEL, TESTO_TIPIZZABILE);
	public static final QName PROP_TIPOLOGIA_TESTO = QName.createQName(CRL_ATTI_MODEL, TIPOLOGIA_TESTO);

	public static final QName PROP_STATO_ATTO_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_STATO_ATTO);
	public static final QName PROP_LEGISLATURA_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_LEGISLATURA);
	public static final QName PROP_NUMERO_ATTO_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_NUM_ATTO);
	public static final QName PROP_ESTENSIONE_ATTO_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_ESTENSIONE_ATTO);
	public static final QName PROP_TIPO_INIZIATIVA_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_TIPO_INIZIATIVA);
	public static final QName PROP_PRIMO_FIRMATARIO_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_PRIMO_FIRMATARIO);
	public static final QName PROP_IS_PRIMO_FIRMATARIO_QNAME = QName.createQName(CRL_ATTI_MODEL,
			PROP_IS_PRIMO_FIRMATARIO);

	public static final QName PROP_IS_FIRMATARIO_POPOLARE_QNAME = QName.createQName(CRL_ATTI_MODEL,
			PROP_IS_FIRMATARIO_POPOLARE);
	public static final QName PROP_NOME_FIRMATARIO_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_NOME_FIRMATARIO);
	public static final QName PROP_FIRMATARI_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_FIRMATARI);
	public static final QName PROP_DATA_NOMINA_RELATORE_QNAME = QName.createQName(CRL_ATTI_MODEL,
			PROP_DATA_NOMINA_RELATORE);
	public static final QName PROP_DATA_USCITA_RELATORE_QNAME = QName.createQName(CRL_ATTI_MODEL,
			PROP_DATA_USCITA_RELATORE);
	
	public static final QName ESITO_VOTO_PASSAGGIO_AULA_QNAME = QName.createQName(CRL_ATTI_MODEL,
			ESITO_VOTO_PASSAGGIO_AULA);

	public static final QName DATA_SEDUTA_PASSAGGIO_AULA_QNAME = QName.createQName(CRL_ATTI_MODEL,
			DATA_SEDUTA_PASSAGGIO_AULA);
	public static final QName PROP_TIPOLOGIA_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_TIPOLOGIA);

	public static final QName PROP_PUBBLICO_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_PUBBLICO);

	public static final QName PROP_PUBBLICO_OPENDATA_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_PUBBLICO_OPENDATA);

	public static final QName PROP_NUMERO_DCR_PASSAGIO_AULA_QNAME = QName.createQName(CRL_ATTI_MODEL,
			PROP_NUMERO_DCR_PASSAGIO_AULA);

	public static final QName PROP_DATA_LR_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_DATA_LR);

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public NodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

}
