package it.vige.ws.utils;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Mock implementation of the OpenDataCommand class for testing purposes. This class provides a mock
 * implementation that allows unit and integration tests to run without requiring a full Alfresco
 * server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class OpenDataCommand {

  private static Log logger = LogFactory.getLog(OpenDataCommand.class);

  /** The node service. */
  private NodeService nodeService;

  /** Constructs a new OpenDataCommand instance. */
  public OpenDataCommand() {
    super();
  }

  /**
   * Sets the node service.
   *
   * @param nodeService the nodeService
   */
  public void setNodeService(NodeService nodeService) {
    this.nodeService = nodeService;
  }

  /**
   * Performs check node core props.
   *
   * @param node the node
   * @return the result
   */
  public boolean checkNodeCoreProps(NodeRef node) {
    boolean result = false;
    if (nodeService.exists(node)
        && StringUtils.isNotBlank(
            (String) nodeService.getProperty(node, ActUtil.PROP_OGGETTO_ACT_QNAME))
        && nodeService.getProperty(node, ActUtil.PROP_NUMERO_ACT_QNAME) != null
        && ((int) nodeService.getProperty(node, ActUtil.PROP_NUMERO_ACT_QNAME) != 0)
        && StringUtils.isNotBlank(
            (String) nodeService.getProperty(node, ActUtil.PROP_LEGISLATURE_QNAME))) {
      result = true;
    } else {
      logger.error("Il nodo non ha una proprietà necessaria, nodeRef: " + node.toString());
    }
    return result;
  }
}
