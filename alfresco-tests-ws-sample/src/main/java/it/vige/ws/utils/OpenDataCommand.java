package it.vige.ws.utils;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * OpenDataCommand implementation for testing purposes.
 *
 * @author vige
 */
public class OpenDataCommand {

	/** The logger. */
	private static Log logger = LogFactory.getLog(OpenDataCommand.class);

	/** The node service. */
	private NodeService nodeService;

	/**
	 * Constructs a new OpenDataCommand.
	 *
	 */
	public OpenDataCommand() {
		super();
	}

	/**
	 * Set node service.
	 *
	 * @param nodeService the node service
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * Check node core props.
	 *
	 * @param node the node
	 * @return the result
	 */
	public boolean checkNodeCoreProps (NodeRef node) {
		boolean result = false;
		if (nodeService.exists(node) &&
			StringUtils.isNotBlank((String) nodeService.getProperty(node, ActUtil.PROP_OGGETTO_ACT_QNAME))&&
			nodeService.getProperty(node, ActUtil.PROP_NUMERO_ACT_QNAME)!=null && ((int) nodeService.getProperty(node, ActUtil.PROP_NUMERO_ACT_QNAME)!=0) &&
			StringUtils.isNotBlank((String) nodeService.getProperty(node, ActUtil.PROP_LEGISLATURE_QNAME))){
				result = true;
		} else {
			logger.error("Il nodo non ha una proprietà necessaria, nodeRef: " + node.toString());
		}
		return result;
	}

}
