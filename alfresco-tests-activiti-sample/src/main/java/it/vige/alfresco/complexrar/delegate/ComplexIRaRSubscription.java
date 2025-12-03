package it.vige.alfresco.complexrar.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.repo.workflow.activiti.BaseJavaDelegate;
import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.vige.activiti.service.SignService;

/**
 * Activiti delegate for IRaR subscription (digital signing).
 * Signs the IRaR document using CAdES format with timestamp.
 * 
 * @author vige
 */
public class ComplexIRaRSubscription extends BaseJavaDelegate {

	/** Logger for this class. */
	private static Log logger = LogFactory.getLog(ComplexIRaRSubscription.class);

	/** Service for digital signature operations. */
	private SignService signService;

	/**
	 * Executes the IRaR subscription delegate.
	 * Signs the IRaR document and sets the subscription error flag.
	 * 
	 * {@inheritDoc}
	 * 
	 * @param execution the Activiti delegate execution context
	 * @throws Exception if signing fails
	 */
	public void execute(DelegateExecution execution) throws Exception {
		logger.debug("Execute start");
		String irarNodeRefString = (String) execution.getVariable("vigewf_relatedIRaR");
		String namUsername = (String) execution.getVariable("vigewf_namUsername");
		String namPassword = (String) execution.getVariable("vigewf_namPassword");
		NodeRef irarNodeRef = new NodeRef(irarNodeRefString);
		boolean result = signService.signCAdESWithTimeStamp(irarNodeRef, namUsername, namPassword);
		execution.setVariable("vigewf_rar_irar_subscription_error", !result);
		logger.debug("Execute end");
	}

	/**
	 * Sets the sign service.
	 * 
	 * @param signService the sign service to use
	 */
	public void setSignService(SignService signService) {
		this.signService = signService;
	}

}