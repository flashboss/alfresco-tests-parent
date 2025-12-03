package it.vige.alfresco.complexrar.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.repo.workflow.activiti.BaseJavaDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Activiti delegate for IRaR labeling operations.
 * Sets the labeling errors flag in the workflow execution context.
 * 
 * @author vige
 */
public class ComplexIRaRLabeling extends BaseJavaDelegate {

	/** Logger for this class. */
	private static Log logger = LogFactory.getLog(ComplexIRaRLabeling.class);

	/**
	 * Executes the IRaR labeling delegate.
	 * Sets the labeling errors flag to false.
	 * 
	 * {@inheritDoc}
	 * 
	 * @param execution the Activiti delegate execution context
	 * @throws Exception if labeling fails
	 */
	public void execute(DelegateExecution execution) throws Exception {
		logger.debug("Execute start");
		execution.setVariable("vigewf_rar_irar_labeling_errors", false);
		logger.debug("Execute end");
	}

}