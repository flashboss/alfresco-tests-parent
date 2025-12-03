package it.vige.alfresco.complexrar.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.repo.workflow.activiti.BaseJavaDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Mock implementation of the ComplexIRaRLabeling class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class ComplexIRaRLabeling extends BaseJavaDelegate {

	private static Log logger = LogFactory.getLog(ComplexIRaRLabeling.class);

	public void execute(DelegateExecution execution) throws Exception {
		logger.debug("Execute start");
		execution.setVariable("vigewf_rar_irar_labeling_errors", false);
		logger.debug("Execute end");
	}

}