package it.vige.ws.utils;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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

	public OpenDataCommand() {
		super();
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

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
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
