package it.vige.alfresco.complexrar.delegate;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.repo.workflow.activiti.ActivitiScriptNode;
import org.alfresco.repo.workflow.activiti.BaseJavaDelegate;
import org.mozilla.javascript.NativeArray;

/**
 * ComplexRaRCleaner implementation for testing purposes.
 *
 * @author vige
 */
public class ComplexRaRCleaner extends BaseJavaDelegate {

	@Override
	/**
	 * Execute.
	 *
	 * @param execution the execution
	 */
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
