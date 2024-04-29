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
