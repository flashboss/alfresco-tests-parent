package it.vige.ws.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.InvalidNodeRefException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.XPathException;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gdata.util.common.base.StringUtil;

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
