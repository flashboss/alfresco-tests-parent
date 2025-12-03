package it.vige.alfresco.complexrar.delegate;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.repo.workflow.activiti.BaseJavaDelegate;
import org.mozilla.javascript.NativeArray;

/**
 * Mock implementation of the ComplexRaRCleaner class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
 */
public class ComplexRaRCleaner extends BaseJavaDelegate {

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void execute(DelegateExecution execution) throws Exception {
		ActivitiScriptNode bpmPackage = (ActivitiScriptNode) execution.getVariable("bpm_package");
		@SuppressWarnings("unchecked")
		List<String> relatedSaSsCleaned = (List<String>) execution.getVariable("vigewf_relatedSaSsCleaned");
		NativeArray children = (NativeArray) bpmPackage.getChildren();
		for (int i = (int) (children.getLength() - 1); i > 0; i--) {
			if (!relatedSaSsCleaned.contains(((ScriptNode) children.get(i, null)).getNodeRef().toString())) {
				bpmPackage.removeNode((ScriptNode) children.get(i, null));
			}
		}
		bpmPackage.save();
	}

}
