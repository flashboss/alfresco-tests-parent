package it.vige.ws.test;

import static org.apache.log4j.Logger.getLogger;
import static org.springframework.extensions.webscripts.Status.STATUS_OK;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.mock.test.ws.AbstractWSForm;
import org.alfresco.mock.test.ws.MockWebScriptRequest;
import org.alfresco.mock.test.ws.MockWebScriptResponse;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.SearchService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;

import it.vige.ws.api.CreateDocTree;
import it.vige.ws.dom.DocVigeWS;

public class CreateDocTreeTest extends AbstractWSForm {

	private final static Logger logger = getLogger(CreateDocTreeTest.class);
	private final static String ID_PARTNER = "prova";
	private final static String ID_PRATICA = "prova";
	private final static String FOLDER_WSSAMPLE = "/sites/vige-site/documentLibrary/Questions/" + ID_PARTNER
			+ "/1970/01/" + ID_PRATICA + "/Autogenerated";
	private Map<String, String> templateVars;

	private final String CONVENZIONE = "1970-01-01";
	private final String NUMERO_PRATICA = "3456";

	@Autowired
	private CreateDocTree createDocTree;

	@Override
	protected AbstractWebScript getAbstractWebScript() {
		return createDocTree;
	}

	@Before
	public void init() {
		super.init();
		templateVars = new HashMap<String, String>();
		templateVars.put("idpartner", ID_PARTNER);
		templateVars.put("idpratica", ID_PRATICA);

		// Creating initial folders and sites
		NodeRef bdm = insertFolder(sites, "vige-site");
		NodeRef bdmDL = insertFolder(bdm, "documentLibrary");
		insertFolder(bdmDL, "Questions");
	}

	@Test
	public void execute() throws ParseException, IOException {

		logger.debug("start test");
		SearchService searchService = serviceRegistry.getSearchService();
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		{
			fields.put("dataCreazionePratica", CONVENZIONE);
			fields.put("convenzione", CONVENZIONE);
			fields.put("numeroPratica", NUMERO_PRATICA);
		}
		WebScriptRequest webScriptRequest = new MockWebScriptRequest("json", templateVars, createDocTree, fields,
				serviceRegistry);
		MockWebScriptResponse webScriptResponse = new MockWebScriptResponse();
		createDocTree.execute(webScriptRequest, webScriptResponse);

		// Verify
		List<NodeRef> nodeRefs = searchService
				.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_XPATH, FOLDER_WSSAMPLE)
				.getNodeRefs();
		NodeRef result = nodeRefs.get(0);
		Assert.assertNotNull("Folder created", result);
		Map<String, Object> model = webScriptResponse.getModel();
		Assert.assertEquals("Status ok", STATUS_OK + "", model.get("status").toString());
		DocVigeWS doc = (DocVigeWS) model.get("pratica");
		Assert.assertEquals("Convenzione", CONVENZIONE, doc.getConvenzione());
		Assert.assertEquals("Id Pratica", ID_PRATICA, doc.getIdPratica());
		Assert.assertEquals("Numero Pratica", NUMERO_PRATICA, doc.getNumeroPratica());
		logger.debug("end test");
	}
}