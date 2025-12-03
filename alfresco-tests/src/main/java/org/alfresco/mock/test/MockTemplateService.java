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

/**
* Mock implementation of the MockTemplateService class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class MockTemplateService implements TemplateService, Serializable {

/**
* {@inheritDoc}
* @param template the template
* @param model the model
* @return the result
* @throws TemplateException if an error occurs
 */
	@Override
	public String processTemplate(String template, Object model) throws TemplateException {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param template the template
* @param model the model
* @param out the out
* @throws TemplateException if an error occurs
 */
	@Override
	public void processTemplate(String template, Object model, Writer out) throws TemplateException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param engine the engine
* @param template the template
* @param model the model
* @return the result
* @throws TemplateException if an error occurs
 */
	@Override
	public String processTemplate(String engine, String template, Object model) throws TemplateException {
		return engine;
	}

/**
* {@inheritDoc}
* @param engine the engine
* @param template the template
* @param model the model
* @param out the out
* @throws TemplateException if an error occurs
 */
	@Override
	public void processTemplate(String engine, String template, Object model, Writer out) throws TemplateException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param engine the engine
* @param templateRef the templateRef
* @param model the model
* @param locale the locale
* @return the result
 */
	@Override
	public String processTemplate(String engine, String templateRef, Object model, Locale locale)
			throws TemplateException {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param engine the engine
* @param template the template
* @param model the model
* @return the result
* @throws TemplateException if an error occurs
 */
	@Override
	public String processTemplateString(String engine, String template, Object model) throws TemplateException {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param engine the engine
* @param template the template
* @param model the model
* @param out the out
 */
	@Override
	public void processTemplateString(String engine, String template, Object model, Writer out)
			throws TemplateException {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
* @param engine the engine
* @return the result
 */
	@Override
	public TemplateProcessor getTemplateProcessor(String engine) {
		// TODO Auto-generated method stub
		return null;
	}

/**
* {@inheritDoc}
* @param templateProcessor the templateProcessor
 */
	@Override
	public void registerTemplateProcessor(TemplateProcessor templateProcessor) {
		// TODO Auto-generated method stub

	}

/**
* {@inheritDoc}
 */
	@Override
	public Map<String, Object> buildDefaultModel(NodeRef person, NodeRef companyHome, NodeRef userHome,
			NodeRef template, TemplateImageResolver imageResolver) {
		// TODO Auto-generated method stub
		return null;
	}

}
