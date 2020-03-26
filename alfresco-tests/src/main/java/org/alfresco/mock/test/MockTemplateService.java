package org.alfresco.mock.test;

import java.io.Serializable;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.TemplateException;
import org.alfresco.service.cmr.repository.TemplateImageResolver;
import org.alfresco.service.cmr.repository.TemplateProcessor;
import org.alfresco.service.cmr.repository.TemplateService;

public class MockTemplateService implements TemplateService, Serializable {

	@Override
	public String processTemplate(String template, Object model) throws TemplateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processTemplate(String template, Object model, Writer out) throws TemplateException {
		// TODO Auto-generated method stub

	}

	@Override
	public String processTemplate(String engine, String template, Object model) throws TemplateException {
		return engine;
	}

	@Override
	public void processTemplate(String engine, String template, Object model, Writer out) throws TemplateException {
		// TODO Auto-generated method stub

	}

	@Override
	public String processTemplate(String engine, String templateRef, Object model, Locale locale)
			throws TemplateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String processTemplateString(String engine, String template, Object model) throws TemplateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void processTemplateString(String engine, String template, Object model, Writer out)
			throws TemplateException {
		// TODO Auto-generated method stub

	}

	@Override
	public TemplateProcessor getTemplateProcessor(String engine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerTemplateProcessor(TemplateProcessor templateProcessor) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> buildDefaultModel(NodeRef person, NodeRef companyHome, NodeRef userHome,
			NodeRef template, TemplateImageResolver imageResolver) {
		// TODO Auto-generated method stub
		return null;
	}

}
