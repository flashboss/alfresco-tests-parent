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
 * Mock implementation of MockTemplateService for testing purposes.
 *
 * @author vige
 */
public class MockTemplateService implements TemplateService, Serializable {

	@Override
	/**
	 * Process template.
	 *
	 * @param template the template
	 * @param model the model
	 * @return the result
	 */
	public String processTemplate(String template, Object model) throws TemplateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Process template.
	 *
	 * @param template the template
	 * @param model the model
	 * @param out the out
	 */
	public void processTemplate(String template, Object model, Writer out) throws TemplateException {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Process template.
	 *
	 * @param engine the engine
	 * @param template the template
	 * @param model the model
	 * @return the result
	 */
	public String processTemplate(String engine, String template, Object model) throws TemplateException {
		return engine;
	}

	@Override
	/**
	 * Process template.
	 *
	 * @param engine the engine
	 * @param template the template
	 * @param model the model
	 * @param out the out
	 */
	public void processTemplate(String engine, String template, Object model, Writer out) throws TemplateException {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Process template.
	 *
	 * @param engine the engine
	 * @param templateRef the template ref
	 * @param model the model
	 * @param locale the locale
	 * @return the result
	 */
	public String processTemplate(String engine, String templateRef, Object model, Locale locale)
			throws TemplateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Process template string.
	 *
	 * @param engine the engine
	 * @param template the template
	 * @param model the model
	 * @return the result
	 */
	public String processTemplateString(String engine, String template, Object model) throws TemplateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Process template string.
	 *
	 * @param engine the engine
	 * @param template the template
	 * @param model the model
	 * @param out the out
	 */
	public void processTemplateString(String engine, String template, Object model, Writer out)
			throws TemplateException {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Get template processor.
	 *
	 * @param engine the engine
	 * @return the result
	 */
	public TemplateProcessor getTemplateProcessor(String engine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Register template processor.
	 *
	 * @param templateProcessor the template processor
	 */
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
