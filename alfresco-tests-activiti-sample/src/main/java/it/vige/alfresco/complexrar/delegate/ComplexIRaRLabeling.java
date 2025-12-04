package it.vige.alfresco.complexrar.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.repo.workflow.activiti.BaseJavaDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public class ComplexIRaRLabeling extends BaseJavaDelegate {

 /** The logger. */
	private static Log logger = LogFactory.getLog(ComplexIRaRLabeling.class);

 /**
 * Execute.
 *
 * @param execution the execution
 */
	public void execute(DelegateExecution execution) throws Exception {
		logger.debug("Execute start");
		execution.setVariable("vigewf_rar_irar_labeling_errors", false);
		logger.debug("Execute end");
	}

}