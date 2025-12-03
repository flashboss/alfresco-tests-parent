package it.vige.ws.test;

import static org.springframework.extensions.webscripts.Status.STATUS_OK;

import it.vige.ws.PreviousWSSample;
import it.vige.ws.WSSampleModel;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.alfresco.mock.test.ws.AbstractWSForm;
import org.alfresco.mock.test.ws.MockWebScriptRequest;
import org.alfresco.mock.test.ws.MockWebScriptResponse;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;

/**
 * Mock implementation of the PreviousWSSampleTest class for testing purposes. This class provides a
 * mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class PreviousWSSampleTest extends AbstractWSForm {

  private static final String FOLDER_WSSAMPLE = "WSSAMPLE-20157726";
  private static final String dataModifica = "2020-06-19";

  /** The previous ws sample. */
  @Autowired private PreviousWSSample previousWSSample;

  private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  /** The repository. */
  private NodeRef repository;

  /** Initializes the component. */
  @Before
  public void init() {
    super.init();
    NamespaceService namespaceService = serviceRegistry.getNamespaceService();
    namespaceService.registerNamespace("vigepb", WSSampleModel.PBPDV_NAMESPACE);

    // Creating initial folders and sites
    NodeRef site = insertFolder(sites, "digital-conservation");
    insertFolder(site, "documentLibrary");

    NodeRef bankSite = insertFolder(sites, "bank-site");
    NodeRef bankSiteDL = insertFolder(bankSite, "documentLibrary");
    repository = insertFolder(bankSiteDL, "repository");
    insertFolder(repository, FOLDER_WSSAMPLE);
  }

  /**
   * Gets the abstract web script.
   *
   * @return the result
   */
  @Override
  protected AbstractWebScript getAbstractWebScript() {
    return previousWSSample;
  }

  /**
   * Performs execute.
   *
   * @throws ParseException if an error occurs
   * @throws IOException if an error occurs
   */
  @Test
  public void execute() throws ParseException, IOException {

    SearchService searchService = serviceRegistry.getSearchService();
    NodeService nodeService = serviceRegistry.getNodeService();
    Map<String, Serializable> fields = new HashMap<String, Serializable>();
    {
      fields.put("date_modified", dataModifica);
      fields.put("date_ws_end", dataModifica);
      fields.put("codicews", FOLDER_WSSAMPLE);
      fields.put("date_ws_start", "1970-01-01");
    }
    WebScriptRequest webScriptRequest =
        new MockWebScriptRequest("json", null, previousWSSample, fields, serviceRegistry);
    MockWebScriptResponse webScriptResponse = new MockWebScriptResponse();
    previousWSSample.execute(webScriptRequest, webScriptResponse);

    // Verify
    List<NodeRef> nodeRefs =
        searchService
            .query(
                StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
                SearchService.LANGUAGE_XPATH,
                FOLDER_WSSAMPLE)
            .getNodeRefs();
    NodeRef result = nodeRefs.get(0);
    Set<QName> aspects = nodeService.getAspects(result);
    Assert.assertEquals("One aspect for the folder", 1, aspects.size());
    QName aspect = aspects.iterator().next();
    Assert.assertEquals(
        "Added an aspect to the WS Sample folder", WSSampleModel.ASPECT_WSSAMPLEFOLDER, aspect);
    Date dateCedra = (Date) nodeService.getProperty(result, WSSampleModel.PROP_UPDATE_PROPERTY);
    Assert.assertEquals("Added the date property", dataModifica, dateFormat.format(dateCedra));
    Map<String, Object> model = webScriptResponse.getModel();
    Assert.assertEquals("Status ok", STATUS_OK + "", model.get("status").toString());
    Assert.assertEquals("Result ok", repository, model.get("node"));
  }
}
