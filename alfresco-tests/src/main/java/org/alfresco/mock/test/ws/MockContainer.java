package org.alfresco.mock.test.ws;

import java.util.HashMap;
import java.util.Map;
import org.alfresco.repo.content.MimetypeMap;
import org.springframework.extensions.config.ConfigService;
import org.springframework.extensions.webscripts.Container;
import org.springframework.extensions.webscripts.FormatRegistry;
import org.springframework.extensions.webscripts.Registry;
import org.springframework.extensions.webscripts.ScriptParameterFactoryRegistry;
import org.springframework.extensions.webscripts.ScriptProcessorRegistry;
import org.springframework.extensions.webscripts.SearchPath;
import org.springframework.extensions.webscripts.ServerModel;
import org.springframework.extensions.webscripts.TemplateProcessorRegistry;

/**
 * Mock implementation of MockContainer for testing purposes.
 *
 * @author vige
 */
public class MockContainer implements Container {

  /** The template parameters. */
  private Map<String, Object> templateParameters = new HashMap<String, Object>();

  /** The search path. */
  private SearchPath searchPath = new SearchPath();

  /** The script processor registry. */
  private ScriptProcessorRegistry scriptProcessorRegistry = new ScriptProcessorRegistry();

  /** The template processor registry. */
  private TemplateProcessorRegistry templateProcessorRegistry = new MockTemplateProcessorRegistry();

  /** The format registry. */
  private FormatRegistry formatRegistry = new FormatRegistry();

  {
    Map<String, String> formats = new HashMap<String, String>();
    formats.put("json", MimetypeMap.MIMETYPE_JSON);
    formats.put("xls", MimetypeMap.MIMETYPE_EXCEL);
    formatRegistry.addFormats(null, formats);
  }

  /**
   * Get name.
   *
   * @return the result
   */
  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get description.
   *
   * @return the result
   */
  @Override
  public ServerModel getDescription() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get script processor registry.
   *
   * @return the result
   */
  @Override
  public ScriptProcessorRegistry getScriptProcessorRegistry() {
    return scriptProcessorRegistry;
  }

  /**
   * Get script parameter factory registry.
   *
   * @return the result
   */
  @Override
  public ScriptParameterFactoryRegistry getScriptParameterFactoryRegistry() {
    // TODO Auto-generated method stub
    return null;
  }

  /** Get script parameters. */
  @Override
  public Map<String, Object> getScriptParameters() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get template processor registry.
   *
   * @return the result
   */
  @Override
  public TemplateProcessorRegistry getTemplateProcessorRegistry() {
    return templateProcessorRegistry;
  }

  /** Get template parameters. */
  @Override
  public Map<String, Object> getTemplateParameters() {
    // TODO Auto-generated method stub
    return templateParameters;
  }

  /**
   * Get format registry.
   *
   * @return the result
   */
  @Override
  public FormatRegistry getFormatRegistry() {
    return formatRegistry;
  }

  /**
   * Get registry.
   *
   * @return the result
   */
  @Override
  public Registry getRegistry() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Get search path.
   *
   * @return the result
   */
  @Override
  public SearchPath getSearchPath() {
    return searchPath;
  }

  /**
   * Get config service.
   *
   * @return the result
   */
  @Override
  public ConfigService getConfigService() {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Allow callbacks.
   *
   * @return the result
   */
  @Override
  public boolean allowCallbacks() {
    // TODO Auto-generated method stub
    return false;
  }

  /** Reset. */
  @Override
  public void reset() {
    // TODO Auto-generated method stub

  }
}
