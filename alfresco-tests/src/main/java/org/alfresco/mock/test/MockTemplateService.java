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

	/**
	 * Process template.
	 *
	 * @param template the template
	 * @param model the model
	 * @return the result
	 */
	@Override
	public String processTemplate(String template, Object model) throws TemplateException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Process template.
	 *
	 * @param template the template
	 * @param model the model
	 * @param out the out
	 */
	@Override
	public void processTemplate(String template, Object model, Writer out) throws TemplateException {
		// TODO Auto-generated method stub

	}

	/**
	 * Process template.
	 *
	 * @param engine the engine
	 * @param template the template
	 * @param model the model
	 * @return the result
	 */
	@Override
	public String processTemplate(String engine, String template, Object model) throws TemplateException {
		return engine;
	}

	/**
	 * Process template.
	 *
	 * @param engine the engine
	 * @param template the template
	 * @param model the model
	 * @param out the out
	 */
	@Override
	public void processTemplate(String engine, String template, Object model, Writer out) throws TemplateException {
		// TODO Auto-generated method stub

	}

	/**
	 * Process template.
	 *
	 * @param engine the engine
	 * @param templateRef the template ref
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
	 * Process template string.
	 *
	 * @param engine the engine
	 * @param template the template
	 * @param model the model
	 * @return the result
	 */
	@Override
	public String processTemplateString(String engine, String template, Object model) throws TemplateException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Process template string.
	 *
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
	 * Get template processor.
	 *
	 * @param engine the engine
	 * @return the result
	 */
	@Override
	public TemplateProcessor getTemplateProcessor(String engine) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Register template processor.
	 *
	 * @param templateProcessor the template processor
	 */
	@Override
	public void registerTemplateProcessor(TemplateProcessor templateProcessor) {
		// TODO Auto-generated method stub

	}

	/**
	 * Build default model.
	 *
	 * @param person the person
	 * @param companyHome the company home
	 * @param userHome the user home
	 * @param template the template
	 * @param imageResolver the image resolver
	 */
	@Override
	public Map<String, Object> buildDefaultModel(NodeRef person, NodeRef companyHome, NodeRef userHome,
			NodeRef template, TemplateImageResolver imageResolver) {
		// TODO Auto-generated method stub
		return null;
	}

}
