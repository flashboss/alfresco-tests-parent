package org.alfresco.mock.test;

import java.util.Map;

import org.alfresco.scripts.ScriptException;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.ScriptLocation;
import org.alfresco.service.cmr.repository.ScriptProcessor;
import org.alfresco.service.cmr.repository.ScriptService;
import org.alfresco.service.namespace.QName;

public class MockScriptService implements ScriptService {

	@Override
	public Object executeScript(String scriptClasspath, Map<String, Object> model) throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object executeScript(String engine, String scriptClasspath, Map<String, Object> model)
			throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object executeScript(NodeRef scriptRef, QName contentProp, Map<String, Object> model)
			throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object executeScript(String engine, NodeRef scriptRef, QName contentProp, Map<String, Object> model)
			throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object executeScript(ScriptLocation scriptLocation, Map<String, Object> model) throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object executeScript(String engine, ScriptLocation scriptLocation, Map<String, Object> model)
			throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object executeScriptString(String script, Map<String, Object> model) throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object executeScriptString(String engine, String script, Map<String, Object> model) throws ScriptException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerScriptProcessor(ScriptProcessor scriptProcessor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetScriptProcessors() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buildCoreModel(Map<String, Object> inputMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> buildDefaultModel(NodeRef person, NodeRef companyHome, NodeRef userHome, NodeRef script,
			NodeRef document, NodeRef space) {
		// TODO Auto-generated method stub
		return null;
	}

}
