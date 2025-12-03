package it.vige.alfresco.complexrar.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.repo.workflow.activiti.BaseJavaDelegate;
import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.vige.activiti.service.SignService;

/**
 * Mock implementation of the ComplexIRaRSubscription class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class ComplexIRaRSubscription extends BaseJavaDelegate {

	private static Log logger = LogFactory.getLog(ComplexIRaRSubscription.class);

	private SignService signService;

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

	public void setSignService(SignService signService) {
		this.signService = signService;
	}

}