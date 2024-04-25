package it.vige.ws.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.ResultSetRow;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.DynamicNamespacePrefixResolver;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Classe che serve come DAO per ottenere la business logic che riguarda il ciclo di vita di un atto e tutti gli attori che sono coinvolti: commissioni, ...
 * Si trovano tutte le costanti per mappare il content model della Regione Lombardia
 * @author sourcesense
 *
 */
public class AttoUtil {

	private static Log logger = LogFactory.getLog(AttoUtil.class);

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
	public static final String PROP_QUORUM_VOTAZIONE_COMMISSIONE = "tipoVotazioneCommissione";
	public static final String PROP_ESITO_VOTAZIONE_COMMISSIONE = "esitoVotazioneCommissione";
	public static final String PROP_RUOLO_COMMISSIONE = "ruoloCommissione";
	public static final String PROP_DATA_SEDUTA = "dataSedutaSedutaODG";
	public static final String PROP_ORARIO_INIZIO_SEDUTA = "dalleOreSedutaODG";
	public static final String PROP_ORARIO_FINE_SEDUTA = "alleOreSedutaODG";
	public static final String PROP_RELAZIONE_SCRITTA_AULA = "relazioneScrittaAula";
	public static final String PROP_COMMISSIONE_CONSULTAZIONE_ATTO = "commissioneConsultazione";
	public static final String PROP_DATA_CONSULTAZIONE_ATTO = "dataConsultazione";
	public static final String PROP_DATA_CHIUSURA_ATTO = "dataChiusura";

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
	
	public static final QName PROP_ESITO_VOTAZIONE_COMMISSIONE_QNAME = QName.createQName(CRL_ATTI_MODEL,
			PROP_ESITO_VOTAZIONE_COMMISSIONE);
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

	public static final QName PROP_NUMERO_LR_QNAME = QName.createQName(CRL_ATTI_MODEL, PROP_NUMERO_LR); 
	public static final String ASSOC_ATTO_TRATTATO_SEDUTA = "attoTrattatoSedutaODG";
	public static final String ASSOC_ATTO_INDIRIZZO_TRATTATO_SEDUTA = "attoIndirizzoTrattatoSedutaODG";
	public static final String ASSOC_ATTO_ASSOCIATO_ABBINAMENTO = "attoAssociatoAbbinamento"; 

	public static final String RUOLO_COMM_REFERENTE = "Referente";
	public static final String RUOLO_COMM_COREFERENTE = "Co-Referente";
	public static final String RUOLO_COMM_CONSULTIVA = "Consultiva";
	public static final String RUOLO_COMM_REDIGENTE = "Redigente";
	public static final String RUOLO_COMM_DELIBERANTE = "Deliberante";

	public static final String INIZIATIVA_CONSILIARE = "01_ATTO DI INIZIATIVA CONSILIARE";
	public static final String INIZIATIVA_POPOLARE = "03_ATTO DI INIZIATIVA POPOLARE";
	public static final String INIZIATIVA_PRESIDENTE_GIUNTA = "06_ATTO DI INIZIATIVA PRESIDENTE GIUNTA";

	public static final QName PROP_DATA_VOTAZIONE = QName.createQName(CRL_ATTI_MODEL, PROP_DATA_VOTAZIONE_COMMISSIONE);
	public static final QName PROP_TIPO_ATTO_COMM = QName.createQName(CRL_ATTI_MODEL, "tipo" + "" + "Commissione");

	/**
	 * cerca dentro Alfresco /app:company_home/cm:CRL/cm:Gestione_x0020_Atti/cm:Anagrafica/cm:Legislature/ gli atti che hanno come valore nel metadato correnteLegislatura true.
	 * La ricerca dovrebbe trovare un unica legistatura corrente
	 * @return NodeRef con la legislatura corrente.
	 */
	public NodeRef getLegislaturaCorrente() {

		NodeRef legislatura = null;

		String legislaturaType = "{" + CRL_ATTI_MODEL + "}" + TYPE_LEGISLATURA;

		StoreRef storeRef = new StoreRef("workspace://SpacesStore"); 
		ResultSet legislatureNodes = null;
		try {
			legislatureNodes = searchService.query(storeRef, SearchService.LANGUAGE_LUCENE,
					"PATH:\"/app:company_home/cm:CRL/cm:Gestione_x0020_Atti/cm:Anagrafica/cm:Legislature/*\" AND TYPE:\""
							+ legislaturaType + "\" AND @crlatti\\:correnteLegislatura:\"true\"");

			if (legislatureNodes.length() > 1) {
				logger.error(
						"Piu' di una legislatura attiva presente. Non e' stato possibile determinare la legislatura attiva.");
			} else if (legislatureNodes.length() == 0) {
				logger.error(
						"Nessuna legislatura attiva presente.. Non e' stato possibile determinare la legislatura attiva.");
			} else {
				legislatura = legislatureNodes.getNodeRef(0);
			}
		} finally {
			if (legislatureNodes != null) {
				legislatureNodes.close();
			}
		}
		return legislatura;
	} 
	/**
	 * Trova quale è il pasaggio ulmtimo creato. Creca dentro la sottocartella cm:Passaggi tutti i nodi figli e seleziona quello che abbia come nome il numero più alto.
	 * @param attoNodeRef
	 * @return NodeRef con l'ultimo passaggio.
	 */
	public NodeRef getLastPassaggio(NodeRef attoNodeRef) {

		NodeRef passaggio = null;

		DynamicNamespacePrefixResolver namespacePrefixResolver = new DynamicNamespacePrefixResolver(null);
		namespacePrefixResolver.registerNamespace(NamespaceService.SYSTEM_MODEL_PREFIX,
				NamespaceService.SYSTEM_MODEL_1_0_URI);
		namespacePrefixResolver.registerNamespace(NamespaceService.CONTENT_MODEL_PREFIX,
				NamespaceService.CONTENT_MODEL_1_0_URI);
		namespacePrefixResolver.registerNamespace(NamespaceService.APP_MODEL_PREFIX,
				NamespaceService.APP_MODEL_1_0_URI);

		String luceneAttoNodePath = nodeService.getPath(attoNodeRef).toPrefixString(namespacePrefixResolver);
		ResultSet passaggiNodes = null;
		try {
			passaggiNodes = searchService.query(attoNodeRef.getStoreRef(), SearchService.LANGUAGE_LUCENE,
					"PATH:\"" + luceneAttoNodePath + "/cm:Passaggi/*\"");

			int numeroPassaggio = 0;
			String nomePassaggio = "";
			int passaggioMax = 0;
			for (int i = 0; i < passaggiNodes.length(); i++) {

				nomePassaggio = (String) nodeService.getProperty(passaggiNodes.getNodeRef(i), ContentModel.PROP_NAME);
				numeroPassaggio = Integer.parseInt(nomePassaggio.substring(9));

				if (numeroPassaggio > passaggioMax) {
					passaggioMax = numeroPassaggio;
					passaggio = passaggiNodes.getNodeRef(i);
				}

			}
		} finally {
			if (passaggiNodes != null) {
				passaggiNodes.close();
			}
		}

		return passaggio;
	} 
	/**
	 * Ritorna la lista con tutte le commissioni referenti e le commissioni coreferenti che esistono dentro la sottocartella: cm:Commissioni. 
	 * @param attoNodeRef
	 * @return lista di NodeRef con tutti i nodi che rappresentano le commissioni referenti e coreferenti. 
	 */
	public List<NodeRef> getCommissioniPrincipali(NodeRef attoNodeRef) {

		List<NodeRef> commissioniPrincipaliList = new ArrayList<NodeRef>();

		DynamicNamespacePrefixResolver namespacePrefixResolver = new DynamicNamespacePrefixResolver(null);
		namespacePrefixResolver.registerNamespace(NamespaceService.SYSTEM_MODEL_PREFIX,
				NamespaceService.SYSTEM_MODEL_1_0_URI);
		namespacePrefixResolver.registerNamespace(NamespaceService.CONTENT_MODEL_PREFIX,
				NamespaceService.CONTENT_MODEL_1_0_URI);
		namespacePrefixResolver.registerNamespace(NamespaceService.APP_MODEL_PREFIX,
				NamespaceService.APP_MODEL_1_0_URI); 
		NodeRef passaggioNodeRef = getLastPassaggio(attoNodeRef);
		String lucenePassaggioNodePath = nodeService.getPath(passaggioNodeRef).toPrefixString(namespacePrefixResolver);

		ResultSet commissioneReferenteNodes = null;
		ResultSet commissioneCoreferenteNodes = null;
		ResultSet commissioneRedigenteNodes = null;
		ResultSet commissioneDeliberanteNodes = null;
		try { 
			commissioneReferenteNodes = searchService.query(attoNodeRef.getStoreRef(), SearchService.LANGUAGE_LUCENE,
					"PATH:\"" + lucenePassaggioNodePath + "/cm:Commissioni/*\" AND @crlatti\\:ruoloCommissione:\""
							+ RUOLO_COMM_REFERENTE + "\""); 
			if (commissioneReferenteNodes.length() > 0) {

				commissioniPrincipaliList.add(commissioneReferenteNodes.getNodeRef(0));

				commissioneCoreferenteNodes = searchService.query(attoNodeRef.getStoreRef(),
						SearchService.LANGUAGE_LUCENE,
						"PATH:\"" + lucenePassaggioNodePath + "/cm:Commissioni/*\" AND @crlatti\\:ruoloCommissione:\""
								+ RUOLO_COMM_COREFERENTE + "\"");

				if (commissioneCoreferenteNodes.length() > 0) {

					commissioniPrincipaliList.add(commissioneCoreferenteNodes.getNodeRef(0));
				} 
			} else {

				commissioneRedigenteNodes = searchService.query(attoNodeRef.getStoreRef(),
						SearchService.LANGUAGE_LUCENE,
						"PATH:\"" + lucenePassaggioNodePath + "/cm:Commissioni/*\" AND @crlatti\\:ruoloCommissione:\""
								+ RUOLO_COMM_REDIGENTE + "\""); 
				if (commissioneRedigenteNodes.length() > 0) {

					commissioniPrincipaliList.add(commissioneRedigenteNodes.getNodeRef(0)); 
				} else {

					commissioneDeliberanteNodes = searchService.query(attoNodeRef.getStoreRef(),
							SearchService.LANGUAGE_LUCENE,
							"PATH:\"" + lucenePassaggioNodePath
									+ "/cm:Commissioni/*\" AND @crlatti\\:ruoloCommissione:\"" + RUOLO_COMM_DELIBERANTE
									+ "\""); 
					if (commissioneDeliberanteNodes.length() > 0) {

						commissioniPrincipaliList.add(commissioneDeliberanteNodes.getNodeRef(0));
					}

				}

			}
		} finally {
			if (commissioneReferenteNodes != null) {
				commissioneReferenteNodes.close();
			}
			if (commissioneCoreferenteNodes != null) {
				commissioneCoreferenteNodes.close();
			}
			if (commissioneRedigenteNodes != null) {
				commissioneRedigenteNodes.close();
			}
			if (commissioneDeliberanteNodes != null) {
				commissioneDeliberanteNodes.close();
			}
		}
		return commissioniPrincipaliList;
	}

	/**
	 * Cerca il relatore corrente della commissione nella cartella cm:Relatori.Il relatore ha un tipo specifico in Alfresco. 
	 * Il relatore corrente è l'unico che ha come data di uscita null, perché ha ancora l'incarico.
	 * @param commissioneNodeRef
	 * @return NodeRef con il relatore corrente
	 */
	public NodeRef getRelatoreCorrente(NodeRef commissioneNodeRef) {

		NodeRef relatore = null;

		DynamicNamespacePrefixResolver namespacePrefixResolver = new DynamicNamespacePrefixResolver(null);
		namespacePrefixResolver.registerNamespace(NamespaceService.SYSTEM_MODEL_PREFIX,
				NamespaceService.SYSTEM_MODEL_1_0_URI);
		namespacePrefixResolver.registerNamespace(NamespaceService.CONTENT_MODEL_PREFIX,
				NamespaceService.CONTENT_MODEL_1_0_URI);
		namespacePrefixResolver.registerNamespace(NamespaceService.APP_MODEL_PREFIX,
				NamespaceService.APP_MODEL_1_0_URI);

		String luceneCommissioneNodePath = nodeService.getPath(commissioneNodeRef)
				.toPrefixString(namespacePrefixResolver);

		String relatoreType = "{" + CRL_ATTI_MODEL + "}" + RELATORE_TYPE; 
		ResultSet relatoreNodes = null;
		try {
			relatoreNodes = searchService.query(commissioneNodeRef.getStoreRef(), SearchService.LANGUAGE_LUCENE,
					"PATH:\"" + luceneCommissioneNodePath + "/cm:Relatori/*\" AND TYPE:\"" + relatoreType + "\"");
			logger.debug("getRelatoreCorrente: query eseguita:" + "PATH:\"" + luceneCommissioneNodePath + "/cm:Relatori/*\" AND TYPE:\"" + relatoreType + "\"");
			if (relatoreNodes.length() > 0) {
				relatore = relatoreNodes.getNodeRef(0);
				for (ResultSetRow resultSetRow : relatoreNodes) {
					Map<QName,Serializable> props = nodeService.getProperties(resultSetRow.getNodeRef());
					String name = (String) props.get(ContentModel.PROP_NAME);
					logger.debug("getRelatoreCorrente: esaminando relatore:" + name);
					Date dataUscita = (Date) props.get(PROP_DATA_USCITA_RELATORE_QNAME);
					if (dataUscita == null ) {
						logger.debug("getRelatoreCorrente: data fine incarico prelevata da relatore:" + name + " " + dataUscita);
						relatore = resultSetRow.getNodeRef();
					}
				}
			}
			else {
				logger.debug("getRelatoreCorrente: non trovati relatori.");
			}
		} finally {
			if (relatoreNodes != null) {
				relatoreNodes.close();
			}
		}

		return relatore;
	}

	/**
	 * Cerca il nodo che si trova nella cartella /cm:Aula\, che deve essere UNICO. ALtrimenti andrà in errore.
	 * @param attoNodeRef Cartella che contiene la sottocartella aula.
	 * @return NodeRef con l'aula
	 */
	public NodeRef getAula(NodeRef attoNodeRef) {

		boolean isRelazioneScritta;

		DynamicNamespacePrefixResolver namespacePrefixResolver = new DynamicNamespacePrefixResolver(null);
		namespacePrefixResolver.registerNamespace(NamespaceService.SYSTEM_MODEL_PREFIX,
				NamespaceService.SYSTEM_MODEL_1_0_URI);
		namespacePrefixResolver.registerNamespace(NamespaceService.CONTENT_MODEL_PREFIX,
				NamespaceService.CONTENT_MODEL_1_0_URI);
		namespacePrefixResolver.registerNamespace(NamespaceService.APP_MODEL_PREFIX,
				NamespaceService.APP_MODEL_1_0_URI); 
		NodeRef passaggioNodeRef = getLastPassaggio(attoNodeRef);
		String lucenePassaggioNodePath = nodeService.getPath(passaggioNodeRef).toPrefixString(namespacePrefixResolver);

		ResultSet aulaNodes = null;
		NodeRef aula = null;
		try { 
			aulaNodes = searchService.query(attoNodeRef.getStoreRef(), SearchService.LANGUAGE_LUCENE,
					"PATH:\"" + lucenePassaggioNodePath + "/cm:Aula\"");

			if (aulaNodes.length() > 0) {
				aula = aulaNodes.getNodeRef(0);
			}
		} finally {
			if (aulaNodes != null) {
				aulaNodes.close();
			}

		}
		return aula;
	}

	/**
	 * Trova la commissione con nome commissioneTarget che si trova nella sottocartella dell'atto: cm:Commissioni. Deve essere UNICA, altrimenti va in errore.
	 * @param attoNodeRef atto a partire dal quale trovare la commissione corrente
	 * @param commissioneTarget nome della commissione cercata
	 * @return NodeRef con la Commissione Corrente
	 */
	public NodeRef getCommissioneCorrente(NodeRef attoNodeRef, String commissioneTarget) {

		NodeRef commissione = null;

		DynamicNamespacePrefixResolver namespacePrefixResolver = new DynamicNamespacePrefixResolver(null);
		namespacePrefixResolver.registerNamespace(NamespaceService.SYSTEM_MODEL_PREFIX,
				NamespaceService.SYSTEM_MODEL_1_0_URI);
		namespacePrefixResolver.registerNamespace(NamespaceService.CONTENT_MODEL_PREFIX,
				NamespaceService.CONTENT_MODEL_1_0_URI);
		namespacePrefixResolver.registerNamespace(NamespaceService.APP_MODEL_PREFIX,
				NamespaceService.APP_MODEL_1_0_URI); 
		NodeRef passaggioNodeRef = getLastPassaggio(attoNodeRef);
		String lucenePassaggioNodePath = nodeService.getPath(passaggioNodeRef).toPrefixString(namespacePrefixResolver);
		ResultSet commissioneTargetNodes = null; 
		try {
			commissioneTargetNodes = searchService.query(attoNodeRef.getStoreRef(), SearchService.LANGUAGE_LUCENE,
					"PATH:\"" + lucenePassaggioNodePath + "/cm:Commissioni/*\" AND @cm\\:name:\"" + commissioneTarget
							+ "\"");

			if (commissioneTargetNodes.length() > 0) {
				commissione = commissioneTargetNodes.getNodeRef(0);
			}
		} finally {
			if (commissioneTargetNodes != null) {
				commissioneTargetNodes.close();
			}
		}

		return commissione;
	} 
	
	/**
	 * Trova la lista di tutti gli atti associati abbinati che si trovano dentro la cartella cm:Passaggi/cm:Abbinamenti/, 
	 * che devono avere tipo "abbinamento" e la proprietà crlatti:dataDisabbinamento NULL.
	 * @param attoNodeRef atto dal quale si cercano gli atti associati e abbinati.
	 * @return List <NodeRef> con tutti i nodi che rappresentano gli atti abbinati.
	 */
	public List<NodeRef> getAttiAbbinati(NodeRef attoNodeRef) {

		List<NodeRef> attiAbbinatiList = new ArrayList<NodeRef>();

		DynamicNamespacePrefixResolver namespacePrefixResolver = new DynamicNamespacePrefixResolver(null);
		namespacePrefixResolver.registerNamespace(NamespaceService.SYSTEM_MODEL_PREFIX,
				NamespaceService.SYSTEM_MODEL_1_0_URI);
		namespacePrefixResolver.registerNamespace(NamespaceService.CONTENT_MODEL_PREFIX,
				NamespaceService.CONTENT_MODEL_1_0_URI);
		namespacePrefixResolver.registerNamespace(NamespaceService.APP_MODEL_PREFIX,
				NamespaceService.APP_MODEL_1_0_URI); 
		NodeRef passaggioNodeRef = getLastPassaggio(attoNodeRef);
		if (passaggioNodeRef != null) {

			String lucenePassaggioNodePath = nodeService.getPath(passaggioNodeRef)
					.toPrefixString(namespacePrefixResolver);

			String abbinamentoType = "{" + CRL_ATTI_MODEL + "}" + ABBINAMENTO_TYPE;
			ResultSet abbinamentiNodes = null;
			try {

				abbinamentiNodes = searchService.query(attoNodeRef.getStoreRef(), SearchService.LANGUAGE_LUCENE,
						"PATH:\"" + lucenePassaggioNodePath + "/cm:Abbinamenti/*\" AND TYPE:\"" + abbinamentoType
								+ "\" AND ISNULL:\"crlatti:dataDisabbinamento\"");  

				for (int i = 0; i < abbinamentiNodes.length(); i++) {

					NodeRef abbinamento = abbinamentiNodes.getNodeRef(i);   

					List<AssociationRef> attiAbbinatiAssociati = nodeService.getTargetAssocs(abbinamento,
							QName.createQName(CRL_ATTI_MODEL, ASSOC_ATTO_ASSOCIATO_ABBINAMENTO));

					if (attiAbbinatiAssociati.size() > 0) {
						attiAbbinatiList.add(attiAbbinatiAssociati.get(0).getTargetRef());
					}

				}
			} finally {
				if (abbinamentiNodes != null) {
					abbinamentiNodes.close();
				}
			}
		}

		return attiAbbinatiList;
	}

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
