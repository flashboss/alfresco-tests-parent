package it.vige.ws.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.InvalidNodeRefException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.repository.XPathException;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gdata.util.common.base.StringUtil;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Classe che fornisce tutti i metodi per ottenre l'informazione degli atti.
 * Mini API per atti collegata con la logica del concetto nodo di Alfresco.
 * 
 * @author sourcesense
 *
 */
public class OpenDataCommand {

	private static Log logger = LogFactory.getLog(OpenDataCommand.class);

	public final String UPSERT_ATTO = "UpsertATTO";
	public final String DELETE_ATTO = "DeleteATTO";

	private NodeService nodeService;
	private String listSeparator;
	private String linkVotoFinale;

	private String linkAtto;
	private SearchService searchService;
	private NamespaceService namespaceService;
	private AttoUtil attoUtil;

	public void setLinkVotoFinale(String linkVotoFinale) {
		this.linkVotoFinale = linkVotoFinale;
	}

	public void setAttoUtil(AttoUtil attoUtil) {
		this.attoUtil = attoUtil;
	}

	public void setNamespaceService(NamespaceService namespaceService) {
		this.namespaceService = namespaceService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public OpenDataCommand() {
		super();
	}

	/**
	 * Genera i ID - atto rappresentato da: tipoAtto + "-" + numeroAtto + "-" +
	 * legislatura
	 * 
	 * @param nodeRef
	 *                atto dal quale calcolare l'ID
	 * @return String con l'ID atto.
	 */
	public String getIdAtto(NodeRef nodeRef) {
		String idAtto = "";
		try {
			String tipoAtto = getTipoAtto(nodeRef);
			if (StringUtils.isNotBlank(tipoAtto)) {
				String legislatura = getLegislatura(nodeRef);
				String numeroAtto = getNumeroAtto(nodeRef);
				idAtto = tipoAtto.toLowerCase() + "-" + numeroAtto + "-" + legislatura;
			} else {
				logger.error("Impossibile generare idAtto");
			}
		} catch (Exception e) {
			logger.error("Errore nella generazione del idAtto", e);
		}
		return idAtto;
	}

	/**
	 * Ritorna il nome del tipo levando il prefisso della definizione del content
	 * model.
	 * 
	 * @param nodeRef
	 *                nodo dal quale ottenere il tipo
	 * @return String con il tipo di nodo che rappresenta nel content model di
	 *         Alfresco o CRL extenzion model
	 */
	public String getTipoAtto(NodeRef nodeRef) {
		String tipoAtto = "";
		try {
			QName nodeType = nodeService.getType(nodeRef);
			if (nodeType != null) {
				String nodeTypeString = nodeType.toString();
				tipoAtto = nodeTypeString.substring(nodeTypeString.length() - 3);
			} else {
				logger.error("Impossibile recuperare il tipo del atto");
			}
		} catch (Exception e) {
			logger.error("Eccezione nel recuperare il tipo del atto", e);
		}
		return tipoAtto;
	}

	/**
	 * Ottiene il nome della legislatura leggendo la proprietà del nodo
	 * "legislatura"
	 * 
	 * @param nodeRef
	 *                Nodo dal quale sapere la legislatura.
	 * @return String legislatura dell'atto
	 */
	public String getLegislatura(NodeRef nodeRef) {
		return (String) nodeService.getProperty(nodeRef, AttoUtil.PROP_LEGISLATURA_QNAME);
	}

	/**
	 * Ottiene il numero dell'atto insieme alla estensione del medesimo.
	 * 
	 * @param nodeRef
	 *                atto dal quale sapere il numero e la estensione (se esiste)
	 * @return String con il numero di atto + la estensione (se esiste)
	 */
	public String getNumeroAtto(NodeRef nodeRef) {
		int numeroAtto = (int) nodeService.getProperty(nodeRef, AttoUtil.PROP_NUMERO_ATTO_QNAME);
		String estensioneAtto = getEstensioneAtto(nodeRef);
		if (estensioneAtto == null)
			estensioneAtto = StringUtil.EMPTY_STRING;
		return numeroAtto + estensioneAtto;
	}

	/**
	 * Ottiene la estensione di un atto.
	 * 
	 * @param nodeRef
	 *                atto dal quale ottenere la estensione
	 * @return Strin la estensione dell'atto.
	 */
	public String getEstensioneAtto(NodeRef nodeRef) {
		return (String) nodeService.getProperty(nodeRef, AttoUtil.PROP_ESTENSIONE_ATTO_QNAME);
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	public void setListSeparator(String listSeparator) {
		this.listSeparator = listSeparator;
	}

	public void setLinkAtto(String linkAtto) {
		this.linkAtto = linkAtto;
	}

	/**
	 * Ottiene il link all'atto
	 * 
	 * @param nodeRef
	 *                atto dal quale ottenere il link
	 * @return String link per l'atto
	 */
	public String getLinkAtto(NodeRef nodeRef) {
		try {
			MessageFormat mf = new MessageFormat(this.linkAtto);
			String parameterFormatting[] = new String[] {
					(String) nodeService.getProperty(nodeRef, ContentModel.PROP_NODE_UUID) };
			return mf.format(parameterFormatting);
		} catch (InvalidNodeRefException e) {
			logger.error("Eccezione nella formattazione", e);
			return null;
		}
	}

	/**
	 * Ottiene il tipo di iniziativa di un nodo: Consiliare, Popolare, Ufficio di
	 * Presidenza, Consiglio delle Autonomie locali, Presidente della Giunta,
	 * Giunta, Commissioni, Altra Iniziativa
	 * 
	 * @param nodeRef
	 *                Nodo dal quale ottenere l'iniziativa
	 * @return String con l'iniziativa dell'atto
	 */
	public String getTipoIniziativa(NodeRef nodeRef) {
		String tipoIniziativa = (String) nodeService.getProperty(nodeRef, AttoUtil.PROP_TIPO_INIZIATIVA_QNAME);
		if (tipoIniziativa != null) {
			if ("01_ATTO DI INIZIATIVA CONSILIARE".equals(tipoIniziativa)) {

				return "Consiliare";

			} else if ("03_ATTO DI INIZIATIVA POPOLARE".equals(tipoIniziativa)) {

				return "Popolare";

			} else if ("05_ATTO DI INIZIATIVA UFFICIO PRESIDENZA".equals(tipoIniziativa)) {

				return "Ufficio di Presidenza";

			} else if ("07_ATTO DI INIZIATIVA AUTONOMIE LOCALI".equals(tipoIniziativa)) {

				return "Consiglio delle Autonomie locali";

			} else if ("06_ATTO DI INIZIATIVA PRESIDENTE GIUNTA".equals(tipoIniziativa)) {

				return "Presidente della Giunta";

			} else if ("02_ATTO DI INIZIATIVA DI GIUNTA".equals(tipoIniziativa)) {

				return "Giunta";

			} else if ("04_ATTO DI INIZIATIVA COMMISSIONI".equals(tipoIniziativa)) {

				return "Commissioni";

			} else if ("08_ATTO DI ALTRA INIZIATIVA".equals(tipoIniziativa)) {

				return "Altra Iniziativa";

			}
		}
		return null;

	}

	/**
	 * Trova il nome del promotore dell'iniziativa se esiste. Cerca fra i firmatari
	 * (che dovrebbe esistere al meno uno). Prende i nodi figli della cartella
	 * firmatari, e cerca se è il primo firmatario popolare inoltre ad essere il
	 * primo firmtario. Se si confermano queste due proprità
	 * (PROP_IS_FIRMATARIO_POPOLARE_QNAME + PROP_NOME_FIRMATARIO_QNAME) si ritorna
	 * il nome del firmatario: PROP_NOME_FIRMATARIO_QNAME
	 * 
	 * @param nodeRef
	 *                atto dal quale trovare il nome del promotore
	 * @return String con il nome del promotore o la catena vuota se non esiste
	 *         promotore.
	 */
	public String getPrimoPromotore(NodeRef nodeRef) {
		try {
			List<NodeRef> firmatariFolder = searchService.selectNodes(nodeRef, "*[@cm:name='Firmatari']", null,
					this.namespaceService, false);
			if (!firmatariFolder.isEmpty()) {
				Set<QName> qnames = new HashSet<QName>(1, 1.0f);
				qnames.add(AttoUtil.TYPE_FIRMATARIO_QNAME);
				List<ChildAssociationRef> firmatariList = nodeService.getChildAssocs(firmatariFolder.get(0), qnames);
				for (ChildAssociationRef firmatario : firmatariList) {
					NodeRef firmatarioNodeRef = firmatario.getChildRef();
					boolean isFirmatarioPopolare = nodeService.getProperty(firmatarioNodeRef,
							AttoUtil.PROP_IS_FIRMATARIO_POPOLARE_QNAME) != null
									? (boolean) nodeService.getProperty(firmatarioNodeRef,
											AttoUtil.PROP_IS_FIRMATARIO_POPOLARE_QNAME)
									: false;
					boolean isPrimoFirmatario = nodeService.getProperty(firmatarioNodeRef,
							AttoUtil.PROP_IS_PRIMO_FIRMATARIO_QNAME) != null
									? (boolean) nodeService.getProperty(firmatarioNodeRef,
											AttoUtil.PROP_IS_PRIMO_FIRMATARIO_QNAME)
									: false;
					if (isFirmatarioPopolare && isPrimoFirmatario) {
						return (String) nodeService.getProperty(firmatarioNodeRef, AttoUtil.PROP_NOME_FIRMATARIO_QNAME);
					}
				}
			}
		} catch (XPathException | InvalidNodeRefException e) {
			logger.error("Eccezione nella ricerca dei nodi dei firmatari", e);
		}
		return "";
	}

	/**
	 * Ritorna l'elenco di tutti i promotori firmatari popolari
	 * 
	 * @param nodeRef
	 *                atto dal quale trovare la cartella che contiene tutti i
	 *                firmatari
	 * @return String con tutti i nomi dei promotori che sono firmatari popolari,
	 *         separati da "listSeparator"
	 */
	public String getTuttiPromotori(NodeRef nodeRef) {
		try {
			List<NodeRef> firmatariFolder = searchService.selectNodes(nodeRef, "*[@cm:name='Firmatari']", null,
					this.namespaceService, false);
			String promotoriString = "";
			if (!firmatariFolder.isEmpty()) {
				Set<QName> qnames = new HashSet<QName>(1, 1.0f);
				qnames.add(AttoUtil.TYPE_FIRMATARIO_QNAME);
				List<ChildAssociationRef> firmatariList = nodeService.getChildAssocs(firmatariFolder.get(0), qnames);
				for (ChildAssociationRef firmatarioChildRef : firmatariList) {
					NodeRef firmatarioNodeRef = firmatarioChildRef.getChildRef();
					boolean isFirmatarioPopolare = nodeService.getProperty(firmatarioNodeRef,
							AttoUtil.PROP_IS_FIRMATARIO_POPOLARE_QNAME) != null
									? (boolean) nodeService.getProperty(firmatarioNodeRef,
											AttoUtil.PROP_IS_FIRMATARIO_POPOLARE_QNAME)
									: false;
					String nomeFirmatario = (String) nodeService.getProperty(firmatarioNodeRef,
							AttoUtil.PROP_NOME_FIRMATARIO_QNAME);
					if (isFirmatarioPopolare) {
						if (StringUtils.isEmpty(promotoriString)) {
							promotoriString += nomeFirmatario;
						} else {
							promotoriString += listSeparator + nomeFirmatario;
						}
					}
				}
			}
			return promotoriString;
		} catch (XPathException | InvalidNodeRefException e) {
			logger.error("Eccezione nella ricerca dei nodi dei firmatari", e);
			return null;
		}
	}

	/**
	 * Ritorna il primo firmatario: il firmatario che non è firmatario popolare
	 * (PROP_IS_FIRMATARIO_POPOLARE_QNAME) e che ha la proprietà
	 * PROP_IS_PRIMO_FIRMATARIO_QNAME.
	 * 
	 * @param nodeRef
	 *                atto dal quale trovare i FIrmatari.
	 * @return String con "- idAnagrafico - primoFirmatario"
	 */
	@SuppressWarnings("finally")
	public String getPrimoFirmatario(NodeRef nodeRef) {
		List<NodeRef> firmatariFolder = searchService.selectNodes(nodeRef, "*[@cm:name='Firmatari']", null,
				this.namespaceService, false);
		String firmatario = "";
		if (!firmatariFolder.isEmpty()) {
			Set<QName> qnames = new HashSet<QName>(1, 1.0f);
			qnames.add(AttoUtil.TYPE_FIRMATARIO_QNAME);
			List<ChildAssociationRef> firmatariList = nodeService.getChildAssocs(firmatariFolder.get(0), qnames);
			for (ChildAssociationRef firmatarioChildRef : firmatariList) {
				NodeRef firmatarioNodeRef = firmatarioChildRef.getChildRef();
				boolean isFirmatarioPopolare = nodeService.getProperty(firmatarioNodeRef,
						AttoUtil.PROP_IS_FIRMATARIO_POPOLARE_QNAME) != null
								? (boolean) nodeService.getProperty(firmatarioNodeRef,
										AttoUtil.PROP_IS_FIRMATARIO_POPOLARE_QNAME)
								: false;
				boolean isPrimoFirmatario = nodeService.getProperty(firmatarioNodeRef,
						AttoUtil.PROP_IS_PRIMO_FIRMATARIO_QNAME) != null
								? (boolean) nodeService.getProperty(firmatarioNodeRef,
										AttoUtil.PROP_IS_PRIMO_FIRMATARIO_QNAME)
								: false;
				if ((!isFirmatarioPopolare) && isPrimoFirmatario) {
					String primoFirmatario = (String) nodeService.getProperty(firmatarioNodeRef,
							AttoUtil.PROP_NOME_FIRMATARIO_QNAME);
					if (StringUtils.isNotBlank(primoFirmatario)) {
						String idAnagrafica = getIdAnagrafica(primoFirmatario);
						if (StringUtils.isNotEmpty(idAnagrafica)) {
							firmatario = "-" + idAnagrafica + "-" + primoFirmatario;
							return firmatario;
						}
					} else {
						logger.warn("Impossibile trovare Primo Firmatario per il nodo: " + nodeRef.toString());
					}
				}
			}
		}
		return firmatario;
	}

	/**
	 * Ritorna l'elenco con tutti i firmatari (tranne quello poplare) dell'atto
	 * passato come parametro.
	 * 
	 * @param nodeRef
	 *                tto dal quale ritrovare i firmatari
	 * @return String con tutti i firmatari tranne il firmatario popolare.
	 */
	@SuppressWarnings("finally")
	public String getTuttiFirmatari(NodeRef nodeRef) {

		List<NodeRef> firmatariFolder = searchService.selectNodes(nodeRef, "*[@cm:name='Firmatari']", null,
				this.namespaceService, false);
		String firmatariString = "";
		if (!firmatariFolder.isEmpty()) {
			Set<QName> qnames = new HashSet<QName>(1, 1.0f);
			qnames.add(AttoUtil.TYPE_FIRMATARIO_QNAME);
			List<ChildAssociationRef> firmatariList = nodeService.getChildAssocs(firmatariFolder.get(0), qnames);
			for (ChildAssociationRef firmatarioChildRef : firmatariList) {
				NodeRef firmatarioNodeRef = firmatarioChildRef.getChildRef();
				boolean isFirmatarioPopolare = nodeService.getProperty(firmatarioNodeRef,
						AttoUtil.PROP_IS_FIRMATARIO_POPOLARE_QNAME) != null
								? (boolean) nodeService.getProperty(firmatarioNodeRef,
										AttoUtil.PROP_IS_FIRMATARIO_POPOLARE_QNAME)
								: false;
				if (!isFirmatarioPopolare) {
					String nomeFirmatario = (String) nodeService.getProperty(firmatarioNodeRef,
							AttoUtil.PROP_NOME_FIRMATARIO_QNAME);
					if (StringUtils.isNotBlank(nomeFirmatario)) {
						String idAnagrafica = getIdAnagrafica(nomeFirmatario);
						if (StringUtils.isNotEmpty(idAnagrafica)) {
							if (StringUtils.isEmpty(firmatariString)) {
								firmatariString += "-" + idAnagrafica + "-" + nomeFirmatario;
							} else {
								firmatariString += listSeparator + "-" + idAnagrafica + "-" + nomeFirmatario;
							}
						}
					} else {
						logger.warn(
								"Impossibile recuperare il firmatario per il nodo: " + firmatarioNodeRef.toString());
					}

				}
			}
		}
		return firmatariString;
	}

	/**
	 * Ritorna l'id anagrafico del nome di un firmatario. Si fa un parsing previo
	 * del nome per levare i caratteri che pososno creare problemi nelle query
	 * 
	 * @param nomeAnagrafica
	 *                       nome del firmatario
	 * @return String con l'id anagrafico.
	 */
	public String getIdAnagrafica(String nomeAnagrafica) {
		String nomeAnagraficaCorretto = nomeAnagrafica.trim().replaceAll("[ \t]+", " ");
		String nome = nomeAnagraficaCorretto
				.replaceAll("([ \t][A-Z0-9_\\p{Punct}]{2,})+(([ \t][a-z]{2,})?([ \t][A-Z0-9_\\p{Punct}]{2,})+)*", "");
		String cognome = nomeAnagraficaCorretto.replaceAll("^[A-Z][a-z]{1,}([ \t][A-Z][a-z]{1,})*[ \t]", "");
		ResultSet consigliereNodes = null;
		try {
			consigliereNodes = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
					SearchService.LANGUAGE_LUCENE,
					"PATH:\"/app:company_home/cm:CRL/cm:Gestione_x0020_Atti/cm:Anagrafica//*\" AND TYPE:\"crlatti:consigliereAnagrafica\" AND @crlatti\\:nomeConsigliereAnagrafica:\""
							+ nome + "\" AND @crlatti\\:cognomeConsigliereAnagrafica:\"" + cognome + "\"");
			if (consigliereNodes.length() > 0) {
				return String.valueOf(
						(int) nodeService.getProperty(consigliereNodes.getNodeRef(0), Constant.PROP_ID_ANAGRAFICA));
			} else {
				logger.warn("Impossibile trovare in anagrafica " + nomeAnagrafica);
				return null;
			}
		} finally {
			if (consigliereNodes != null) {
				consigliereNodes.close();
			}
		}
	}

	/**
	 * Trova il nodo che si corrisponde con il relatore. Cerca tutti i relatori e
	 * seleziona il nodo che ha il tipo TYPE_RELATORE_QNAME
	 * 
	 * @param item
	 *             atto dal quale si prende la Commissione Referente e dal quale si
	 *             trovano i relatori
	 * @return NodeRef del relatore. Null se non si trova.
	 */
	public NodeRef getRelatoreNodeRef(NodeRef item) {
		NodeRef commissioneNodeRef = getCommissioneReferente(item);
		if (commissioneNodeRef != null) {
			try {
				List<NodeRef> relatori = searchService.selectNodes(commissioneNodeRef, "*[@cm:name='Relatori']", null,
						this.namespaceService, false);
				if ((relatori != null) && (!relatori.isEmpty())) {
					Set<QName> qnames = new HashSet<QName>(1, 1.0f);
					qnames.add(AttoUtil.TYPE_RELATORE_QNAME);
					List<ChildAssociationRef> relatoriList = nodeService.getChildAssocs(relatori.get(0), qnames);
					if ((relatoriList != null) && (!relatoriList.isEmpty())) {
						return relatoriList.get(0).getChildRef();
					}
				}
			} catch (XPathException | InvalidNodeRefException e) {
				logger.error("Eccezione nella ricerca dei relatori", e);
			}
		}
		return null;
	}

	/**
	 * Trova il nodo che contiene il passaggio dall'atto che riceve come parametro.
	 * Si assume che dentro la cartella con i passaggi si prenda il primo che
	 * ritorna il nodeService.getChildAssocs(...).
	 * 
	 * @param attoNoderef
	 *                    atto dal quale trovare il passaggio
	 * @return NodeRef con il passagio dell'atto.
	 */
	public NodeRef getPassaggio(NodeRef attoNoderef) {
		try {
			List<NodeRef> passaggi = searchService.selectNodes(attoNoderef, "*[@cm:name='Passaggi']", null,
					this.namespaceService, false);
			if (!passaggi.isEmpty()) {
				Set<QName> passaggiFolderQnames = new HashSet<QName>(1, 1.0f);
				passaggiFolderQnames.add(ContentModel.TYPE_FOLDER);
				List<ChildAssociationRef> passaggiList = nodeService.getChildAssocs(passaggi.get(0),
						passaggiFolderQnames);
				if ((passaggiList != null) && (!passaggiList.isEmpty())) {
					return passaggiList.get(0).getChildRef();
				}
			}
		} catch (XPathException | InvalidNodeRefException e) {
			logger.error("Eccezione nella ricerca dei passaggi", e);
		}
		return null;
	}

	/**
	 * Trova il nodo che contiene l'ultimo passaggio dall'atto che riceve come
	 * parametro. Si assume che dentro la cartella con i passaggi si prenda l'ultimo
	 * che ritorna il nodeService.getChildAssocs(...).
	 * 
	 * @param attoNoderef
	 *                    atto dal quale trovare l'ultimo passaggio
	 * @return NodeRef con lúltimo passagio dell'atto.
	 */
	public NodeRef getUltimoPassaggio(NodeRef attoNoderef) {
		try {
			List<NodeRef> passaggi = searchService.selectNodes(attoNoderef, "*[@cm:name='Passaggi']", null,
					this.namespaceService, false);
			if ((passaggi != null) && (!passaggi.isEmpty())) {
				Set<QName> passaggiFolderQnames = new HashSet<QName>(1, 1.0f);
				passaggiFolderQnames.add(ContentModel.TYPE_FOLDER);
				List<ChildAssociationRef> passaggiList = nodeService.getChildAssocs(passaggi.get(0), passaggiFolderQnames);
				if ((passaggiList != null) && (!passaggiList.isEmpty())) {
					return passaggiList.get(passaggiList.size() - 1).getChildRef();
				}
			}
		} catch (XPathException | InvalidNodeRefException e) {
			logger.error("Eccezione nella ricerca dei passaggi", e);
		}
		return null;
	}

	/**
	 * Ritorna l'elenco dei nodi figli della cartella Passaggi dell'atto ricevuto
	 * come parametro
	 * 
	 * @param attoNoderef
	 *                    atto dal quale trovare tutti i passagii
	 * @return Lista con i passaggi dell'atto.
	 */
	public List<ChildAssociationRef> getPassaggi(NodeRef attoNoderef) {
		try {
			List<NodeRef> passaggi = searchService.selectNodes(attoNoderef, "*[@cm:name='Passaggi']", null,
					this.namespaceService, false);
			if ((passaggi != null) && (!passaggi.isEmpty())) {
				Set<QName> passaggiFolderQnames = new HashSet<QName>(1, 1.0f);
				passaggiFolderQnames.add(ContentModel.TYPE_FOLDER);
				List<ChildAssociationRef> passaggiList = nodeService.getChildAssocs(passaggi.get(0),
						passaggiFolderQnames);
				if ((passaggiList != null) && (!passaggiList.isEmpty())) {
					return passaggiList;
				}
			}
		} catch (XPathException | InvalidNodeRefException e) {
			logger.error("Eccezione nella ricerca dei passaggi", e);
		}
		return new ArrayList<ChildAssociationRef>();
	}

	/**
	 * Trova il Referente della commissione o il Co-Referente. Ci deve essere una
	 * data di votazione e un esito della votazione.
	 * 
	 * @param attoNoderef
	 *                    atto dal quale trovare il referente.
	 * @return NodeRef Il referento o Co-referente di una commissione
	 */
	public NodeRef getCommissioneReferente(NodeRef attoNoderef) {
		NodeRef passaggio = getPassaggio(attoNoderef);
		if (passaggio != null) {
			try {
				List<NodeRef> commissioni = searchService.selectNodes(passaggio,
						"*[@cm:name='Commissioni']/*[@crlatti:ruoloCommissione='Referente' or @crlatti:ruoloCommissione='Co-Referente']",
						null, this.namespaceService, false);
				if ((commissioni != null) && (!commissioni.isEmpty())) {
					for (int i = commissioni.size() - 1; i > -1; i--) {
						if (nodeService.getProperty(commissioni.get(i), AttoUtil.PROP_DATA_VOTAZIONE) != null
								&& nodeService
										.getProperty(commissioni.get(i),
												AttoUtil.PROP_ESITO_VOTAZIONE_COMMISSIONE_QNAME) != null)
							return commissioni.get(i);
					}
					return commissioni.get(commissioni.size() - 1);
				}
			} catch (XPathException | InvalidNodeRefException e) {
				logger.error("Eccezione nella ricerca dei referenti", e);
			}
		}
		return null;
	}

	/**
	 * Ritona il relatore con idAnagrafica e nome
	 * 
	 * @param item atto dal quale trovare il relatore.
	 * @return String Il relatore: "-idAnagraficaRelatore+nomeRelatore"
	 */
	public String getRelatore(NodeRef item) {
		String relatore = "";
		NodeRef relatoreNodeRef = getRelatoreNodeRef(item);
		if (relatoreNodeRef != null) {

			String relatoreName = (String) nodeService.getProperty(relatoreNodeRef, ContentModel.PROP_NAME);
			relatoreName = relatoreName.trim().replaceAll(" +", " ");
			try {
				String idAnagrafica = getIdAnagrafica(relatoreName);
				if (StringUtils.isNotEmpty(idAnagrafica)) {
					relatore = "-" + idAnagrafica + "-" + relatoreName;
				}
			} catch (Exception e) {
				logger.warn("Impossibile recuperare il relatore " + relatoreName, e);
			}
		}
		return relatore;
	}

	/**
	 * Ottiene la data in cui è stato nominato un relatore controllando la
	 * proprietà: PROP_DATA_NOMINA_RELATORE_QNAME.
	 * 
	 * @param item atto dal quale ritrovare il relatore
	 * @return Date la data in cui è stato nominato il relatore
	 * 
	 */
	public Date getDataNominaRelatore(NodeRef item) {
		NodeRef relatoreNodeRef = getRelatoreNodeRef(item);
		if (relatoreNodeRef != null) {
			return (Date) nodeService.getProperty(relatoreNodeRef, AttoUtil.PROP_DATA_NOMINA_RELATORE_QNAME);
		}
		return null;
	}

	/**
	 * Ottiene l'elenco degli atti abinati ad un atto
	 * 
	 * @param item: atto dal quale ricavare gli atti abbinati.
	 * @return ArrayList lista con tutti gli atti abbinati
	 */
	public ArrayList<String> getAbbinamenti(NodeRef item) {
		List<NodeRef> attiAbbinati = attoUtil.getAttiAbbinati(item);
		int n = attiAbbinati.size();
		ArrayList<String> abbinamenti = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			abbinamenti.add(getIdAtto(attiAbbinati.get(i)));
		}
		return abbinamenti;
	}

	/**
	 * Ottiene il valore dell'esito della votazione della commissione dell'atto
	 * verificando il valore della proprietà PROP_ESITO_VOTAZIONE_COMMISSIONE_QNAME.
	 * 
	 * @param item: atto dal quale trovare l'esito della votazione
	 * @return String l'esito della votazione dell Commissione Referente.
	 */
	public String getEsitoVotazioneCommissioneReferente(NodeRef item) {
		NodeRef commissione = getCommissioneReferente(item);
		if (commissione != null) {
			return (String) nodeService.getProperty(commissione, AttoUtil.PROP_ESITO_VOTAZIONE_COMMISSIONE_QNAME);
		} else {
			return null;
		}
	}

	/**
	 * Ottiene la data in cui è stata esguita la votazione verificando la proprietà:
	 * PROP_DATA_VOTAZIONE
	 * 
	 * @param item atto dal quale ricavare la data della votazione.
	 * @return Date la data della votazione.
	 */
	public Date getDataVotazioneCommissioneReferente(NodeRef item) {
		NodeRef commissione = getCommissioneReferente(item);
		if (commissione != null) {
			return (Date) nodeService.getProperty(commissione, AttoUtil.PROP_DATA_VOTAZIONE);
		} else {
			return null;
		}
	}

	/**
	 * Trova l'aula di un atto dove si è svolto l'ultimo passaggio. Se non esiste
	 * l'ultimo passaggio, il valoere è null.
	 * 
	 * @param attoNodeRef atto dal quale trovare l'aula.
	 * @return NodeRef con il valore dell'aula. null se non esiste l'ultimo
	 *         passaggio dell'atto.
	 */
	public NodeRef getAula(NodeRef attoNoderef) {
		NodeRef passaggio = getUltimoPassaggio(attoNoderef);
		if (passaggio != null) {
			Set<QName> aulaFolderQnames = new HashSet<QName>(1, 1.0f);
			aulaFolderQnames.add(AttoUtil.TYPE_AULA);
			List<ChildAssociationRef> aulaList = nodeService.getChildAssocs(passaggio, aulaFolderQnames);
			if ((aulaList != null) && (!aulaList.isEmpty())) {
				return aulaList.get(0).getChildRef();
			}
		}
		return null;
	}

	/**
	 * 
	 * Trova l'aula dell'atto a partire dal passaggio
	 * 
	 * @param passaggioNoderef passaggio di un atto
	 * @return NodeRef con l'aula dell'atto. Null se non lo trova
	 */
	public NodeRef getAulaByPassaggio(NodeRef passaggioNoderef) {
		if (passaggioNoderef != null) {
			Set<QName> aulaFolderQnames = new HashSet<QName>(1, 1.0f);
			aulaFolderQnames.add(AttoUtil.TYPE_AULA);
			List<ChildAssociationRef> aulaList = nodeService.getChildAssocs(passaggioNoderef, aulaFolderQnames);
			if ((aulaList != null) && (!aulaList.isEmpty())) {
				return aulaList.get(0).getChildRef();
			}
		}
		return null;
	}

	/**
	 * Trova l'esito della votazione in aula di un atto analizzando la proprietà
	 * ESITO_VOTO_PASSAGGIO_AULA_QNAME
	 * 
	 * @param NodeRef atto
	 * @return String esito della votazione.
	 */
	public String getEsitoVotazioneAula(NodeRef item) {
		NodeRef aula = getAula(item);
		if (aula != null) {
			return (String) nodeService.getProperty(aula, AttoUtil.ESITO_VOTO_PASSAGGIO_AULA_QNAME);
		} else {
			return null;
		}
	}

	/**
	 * Trova la data della votazione eseguita in aula
	 * 
	 * @param atto presuntamente votato in aula
	 * @return Date la data della votazione in aula. Null se l'atto non è stato
	 *         votato in aula.
	 */
	public Date getDataVotazioneAula(NodeRef item) {
		NodeRef aula = getAula(item);
		if (aula != null) {
			return (Date) nodeService.getProperty(aula, AttoUtil.DATA_SEDUTA_PASSAGGIO_AULA_QNAME);
		} else {
			return null;
		}
	}

	/**
	 * Trova il numero DCR di passaggio in aula analizzando la proprietà
	 * PROP_NUMERO_DCR_PASSAGIO_AULA_QNAME
	 * 
	 * @param item atto
	 * @return String numero di passaggio in aula dell'atto. Null se l'atto non è
	 *         andato in aula
	 */
	public String getNumeroDcrPassaggioAula(NodeRef item) {
		NodeRef aula = getAula(item);
		if (aula != null) {
			return (String) nodeService.getProperty(aula, AttoUtil.PROP_NUMERO_DCR_PASSAGIO_AULA_QNAME);
		} else {
			return null;
		}
	}

	/**
	 * Trova il link del voto finale in aula dell'atto. Si assume l'esistenza di
	 * allegati del tipo allegato_aula
	 * 
	 * @param atto votato in aula
	 * @return String con il link al voto finale
	 */
	public String getLinkVotoFinaleAula(NodeRef attoNodeRef) {
		List<ChildAssociationRef> passaggi = getPassaggi(attoNodeRef);
		for (ChildAssociationRef passaggio : passaggi) {
			NodeRef aula = getAulaByPassaggio(passaggio.getChildRef());
			if (aula != null) {
				try {
					List<NodeRef> allegati = searchService.selectNodes(aula, "*[@cm:name='Allegati']", null,
							this.namespaceService, false);
					if ((allegati != null) && (!allegati.isEmpty())) {
						Set<QName> allegatoFolderQnames = new HashSet<QName>(1, 1.0f);
						allegatoFolderQnames.add(AttoUtil.TYPE_ALLEGATO_QNAME);
						List<ChildAssociationRef> allegatiList = nodeService.getChildAssocs(allegati.get(0),
								allegatoFolderQnames);
						for (ChildAssociationRef allegato : allegatiList) {
							if (((String) nodeService.getProperty(allegato.getChildRef(),
									AttoUtil.PROP_TIPOLOGIA_QNAME))
									.equalsIgnoreCase("allegato_aula")) {
								MessageFormat mf = new MessageFormat(this.linkVotoFinale);
								String parameterFormatting[] = new String[] {
										(String) nodeService.getProperty(allegato.getChildRef(),
												ContentModel.PROP_NODE_UUID),
										(String) nodeService.getProperty(allegato.getChildRef(),
												ContentModel.PROP_NAME) };
								return mf.format(parameterFormatting);
							}

						}
					}
				} catch (XPathException | InvalidNodeRefException e) {
					logger.error("Eccezione nella ricerca dei testi dell'Atto", e);
				} catch (IllegalArgumentException e) {
					logger.error("Eccezione nella formattazione", e);
				}
			}
		}
		return null;
	}

	/**
	 * Trova il link al testo dell'atto referente commissione. Seleziona i nodi che
	 * abbiano la proprietà crlatti:pubblicoOpendata
	 * 
	 * @param attoNodeRef atto
	 * @return String il link al testo dell'atto referente commissione.
	 */
	public String getLinkTestoAttoComReferente(NodeRef attoNodeRef) {
		return trovaTestiByQuery(attoNodeRef, "*//*[@crlatti:tipologia='testo_atto_votato_commissione']");
	}

	/**
	 * Trova il link al testo della relazione illustrativa dell'atto. Seleziona i
	 * nodi che abbiano la proprietà crlatti:tipologiaTesto='Relazione Illustrativa'
	 * 
	 * @param attoNodeRef atto
	 */
	public String getLinkTestoRelazioneIllustrativa(NodeRef attoNodeRef) {
		return trovaTestiByQuery(attoNodeRef,
				"*//*[@crlatti:tipologiaTesto='testo_relazione_illustrativa_tecnico_finanziaria_esame_commissioni']");
	}

	/**
	 * Trova il link al testo della scheda istruttoria dell'atto. Seleziona i nodi
	 * che abbiano la proprietà crlatti:tipologiaTesto='Scheda Istruttoria'
	 * 
	 * @param attoNodeRef atto
	 */
	public String getLinkTestoSchedaIstruttoria(NodeRef attoNodeRef) {
		return trovaTestiByQuery(attoNodeRef,
				"*//*[@crlatti:tipologiaTesto='testo_scheda_istruttoria_progetto_legge_esame_commissioni']");
	}

	/**
	 * Trova gli id LR cercando la proprietà PROP_NUMERO_LR_QNAME
	 * 
	 * @param item atto dal quale cercare PROP_NUMERO_LR_QNAME
	 * @return String ID LR dell'atto item. "" Nel caso in cui non si trovi una
	 *         proprietà PROP_NUMERO_LR_QNAME nel,nodo
	 */
	public String getIdsLr(NodeRef item) {
		String idsLr = "";
		Date dataLr = (Date) nodeService.getProperty(item, AttoUtil.PROP_DATA_LR_QNAME);
		if (dataLr != null) {
			String dateLrString = DateFormatUtils.format(dataLr, "yyyyMMdd");
			String numeroLr = (String) nodeService.getProperty(item, AttoUtil.PROP_NUMERO_LR_QNAME);
			idsLr = "lr00" + dateLrString + StringUtils.leftPad(numeroLr, 5, "0");
		}
		return idsLr;
	}

	public String buildSoapEnvelope(String nomeChiamata, String odAtto, String privateToken, String ambiente,
			String url) {
		StringBuilder xmlBuilder = new StringBuilder();
		try {
			xmlBuilder.append(
					"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:open=\"")
					.append(url)
					.append("\">\n")
					.append("   <soapenv:Header/>\n")
					.append("   <soapenv:Body>\n")
					.append(this.buildMethodElement(nomeChiamata, odAtto, privateToken, ambiente))
					.append("   </soapenv:Body>\n")
					.append("</soapenv:Envelope>");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		}
		return xmlBuilder.toString();
	}

	@SuppressWarnings("deprecation")
	public String executeOpenDataCall(String bodyString, String url) throws Exception {

		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("text/xml");
		RequestBody body = RequestBody.create(mediaType, bodyString);
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.addHeader("Content-Type", "text/xml")
				.addHeader("Accept", "*/*")
				.build();

		Response response = client.newCall(request).execute();

		return response.body().string();

	}

	private String buildMethodElement(String nomeChiamata, String odAtto, String privateToken, String ambiente) {
		StringBuilder methodBuilder = new StringBuilder();

		String open = (nomeChiamata == "UpsertATTO") ? "ODAtto" : "idAtto";

		methodBuilder.append("      <open:")
				.append(nomeChiamata)
				.append(">\n")
				.append(this.buildElement("open:" + open, odAtto))
				.append(this.buildElement("open:PRIVATE_TOKEN", privateToken))
				.append(this.buildElement("open:ambiente", ambiente))
				.append("      </open:")
				.append(nomeChiamata)
				.append(">\n");
		return methodBuilder.toString();
	}

	private String buildElement(String tagName, String value) {
		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("Value cannot be null or empty for tag: " + tagName);
		}
		return "         <!--Optional:-->\n         <" + tagName + ">" + value + "</" + tagName + ">\n";
	}

	private String trovaTestiByQuery(NodeRef attoNodeRef, String query) {
		if (StringUtils.isNotBlank(query)) {
			try {
				List<NodeRef> testiRelazioneIllustrativaNodeRefs = searchService.selectNodes(attoNodeRef, query, null,
						this.namespaceService, false);

				if (testiRelazioneIllustrativaNodeRefs.size() > 0) {
					for (NodeRef testoRelazioneIllustrativaNodeRef : testiRelazioneIllustrativaNodeRefs) {
						if (nodeService.getProperty(testoRelazioneIllustrativaNodeRef,
								AttoUtil.PROP_PUBBLICO_OPENDATA_QNAME) != null &&
								(Boolean) nodeService.getProperty(testoRelazioneIllustrativaNodeRef,
										AttoUtil.PROP_PUBBLICO_OPENDATA_QNAME)) {

							MessageFormat mf = new MessageFormat(this.linkVotoFinale);
							String parameterFormatting[] = new String[] {
									(String) nodeService.getProperty(testoRelazioneIllustrativaNodeRef,
											ContentModel.PROP_NODE_UUID),
									(String) nodeService.getProperty(testoRelazioneIllustrativaNodeRef,
											ContentModel.PROP_NAME) };
							return mf.format(parameterFormatting);
						}
					}
				}
			} catch (XPathException | InvalidNodeRefException e) {
				logger.error("Eccezione nella ricerca dei testi dell'Atto", e);
			} catch (IllegalArgumentException e) {
				logger.error("Eccezione nella formattazione", e);
			}
		} else {
			logger.error("Query non può essere null o stringa vuota");
		}
		return null;
	}

	public boolean checkNodeCoreProps (NodeRef node) {
		boolean result = false;
		if (nodeService.exists(node) &&
			StringUtils.isNotBlank((String) nodeService.getProperty(node, AttoUtil.PROP_OGGETTO_ATTO_QNAME))&&
			nodeService.getProperty(node, AttoUtil.PROP_NUMERO_ATTO_QNAME)!=null && ((int) nodeService.getProperty(node, AttoUtil.PROP_NUMERO_ATTO_QNAME)!=0) &&
			StringUtils.isNotBlank((String) nodeService.getProperty(node, AttoUtil.PROP_LEGISLATURA_QNAME))){
				result = true;
		} else {
			logger.error("Il nodo non ha una proprietà necessaria, nodeRef: " + node.toString());
		}
		return result;
	}

}
