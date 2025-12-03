package it.vige.ws.utils;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Command class for Open Data operations.
 * Provides methods to validate node properties required for open data export.
 *
 * @author lucastancapiano
 */
public class OpenDataCommand {

	/** The logger instance. */
	private static Log logger = LogFactory.getLog(OpenDataCommand.class);

	/** The node service. */
	private NodeService nodeService;

	/**
	 * Default constructor.
	 */
	public OpenDataCommand() {
		super();
	}

	/**
	 * Sets the node service.
	 *
	 * @param nodeService the node service to set
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * Checks if a node has all required core properties for open data export.
	 * The node must exist and have non-empty values for oggetto, numeroAct, and legislature.
	 *
	 * @param node the node reference to check
	 * @return true if the node has all required properties, false otherwise
	 */
	public boolean checkNodeCoreProps(NodeRef node) {
		boolean result = false;
		if (nodeService.exists(node) &&
			StringUtils.isNotBlank((String) nodeService.getProperty(node, ActUtil.PROP_OGGETTO_ACT_QNAME)) &&
			nodeService.getProperty(node, ActUtil.PROP_NUMERO_ACT_QNAME) != null && ((int) nodeService.getProperty(node, ActUtil.PROP_NUMERO_ACT_QNAME) != 0) &&
			StringUtils.isNotBlank((String) nodeService.getProperty(node, ActUtil.PROP_LEGISLATURE_QNAME))) {
				result = true;
		} else {
			logger.error("Il nodo non ha una proprietà necessaria, nodeRef: " + node.toString());
		}
		return result;
	}

}
