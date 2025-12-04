package it.vige.alfresco.complexrar.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.repo.workflow.activiti.BaseJavaDelegate;
import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.vige.activiti.service.SignService;

/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public class ComplexIRaRSubscription extends BaseJavaDelegate {

 /** The logger. */
	private static Log logger = LogFactory.getLog(ComplexIRaRSubscription.class);

 /** The sign service. */
	private SignService signService;

 /**
 * Execute.
 *
 * @param execution the execution
 */
	public void execute(DelegateExecution execution) throws Exception {
		logger.debug("Execute start");
  /** The irar node ref string. */
		String irarNodeRefString = (String) execution.getVariable("vigewf_relatedIRaR");
  /** The nam username. */
		String namUsername = (String) execution.getVariable("vigewf_namUsername");
  /** The nam password. */
		String namPassword = (String) execution.getVariable("vigewf_namPassword");
  /** The nam password. */
		NodeRef irarNodeRef = new NodeRef(irarNodeRefString);
  /** The nam password. */
		boolean result = signService.signCAdESWithTimeStamp(irarNodeRef, namUsername, namPassword);
		execution.setVariable("vigewf_rar_irar_subscription_error", !result);
		logger.debug("Execute end");
	}

 /**
 * Set sign service.
 *
 * @param signService the sign service
 */
	public void setSignService(SignService signService) {
		this.signService = signService;
	}

}