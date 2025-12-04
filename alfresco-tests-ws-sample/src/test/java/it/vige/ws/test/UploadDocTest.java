package it.vige.ws.test;

import static org.apache.log4j.Logger.getLogger;
import static org.springframework.extensions.webscripts.Status.STATUS_CREATED;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.alfresco.mock.test.ws.AbstractWSForm;
import org.alfresco.mock.test.ws.MockWebScriptRequest;
import org.alfresco.mock.test.ws.MockWebScriptResponse;
import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.QName;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;

import it.vige.ws.api.UploadDoc;
import it.vige.ws.dom.VigeWSContentModel;

/**
 * UploadDocTest implementation for testing purposes.
 *
 * @author vige
 */
public class UploadDocTest extends AbstractWSForm {

	private final static Logger logger = getLogger(UploadDocTest.class);
	private final static String ID_PARTNER = "prova";
	private final static String ID_PRATICA = "prova";
	private final static String ID_DOC = "prova";
	private final static String NOME_FILE = "unsigned.pdf";
	private final static String DESCRIZIONE = "descrizione.pdf";
	private final static String TRATTAMENTO = "3";
	private final static String DATA_SCADENZA = "2018-04-19";
	private final static String DATA_EMISSIONE = "2018-04-19";
	private final static String DATA_CREAZIONE = "2018-04-23";
	private final static String CODICE_DOC = "461";
	private final static String CATEGORIA_TIPO = "461";
	private final static String NOTE = "note";
	private final static String NUMERO_DOC = "";
	private final static String ID_USER = "105";
	private final static String FOLDER_DROPZONE = "/sites/vige-site/documentLibrary/sys/Dropzone";
	/** The template vars. */
	private Map<String, String> templateVars;

	/** The upload doc. */
	@Autowired
	private UploadDoc uploadDoc;

	/**
	 * Get abstract web script.
	 *
	 * @return the result
	 */
	@Override
	protected AbstractWebScript getAbstractWebScript() {
		return uploadDoc;
	}

	/**
	 * Init.
	 *
	 */
	@Before
	public void init() {
		super.init();
		templateVars = new HashMap<String, String>();
		templateVars.put("idpartner", ID_PARTNER);
		templateVars.put("idpratica", ID_PRATICA);
		templateVars.put("iddoc", ID_DOC);

		// Creating initial folders and sites
		NodeRef bdm = insertFolder(sites, "vige-site");
		NodeRef bdmDL = insertFolder(bdm, "documentLibrary");
		NodeRef sys = insertFolder(bdmDL, "sys");
		insertFolder(sys, "Dropzone");
	}

	/**
	 * Execute.
	 *
	 */
	@Test
	public void execute() throws ParseException, IOException {

		logger.debug("start test");
		SearchService searchService = serviceRegistry.getSearchService();
		NodeService nodeService = serviceRegistry.getNodeService();
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		{
			fields.put("datacreazione", DATA_CREAZIONE);
			fields.put("dataemissione", DATA_EMISSIONE);
			fields.put("datascadenza", DATA_SCADENZA);
			fields.put("signVerify", "n");
			fields.put("codicetipo", "45");
			fields.put("codicedoc", CODICE_DOC);
			fields.put("categoriatipo", CATEGORIA_TIPO);
			fields.put("trattamento", TRATTAMENTO);
			fields.put("iduser", ID_USER);
			fields.put("desc", DESCRIZIONE);
			fields.put("note", NOTE);
			fields.put("file", new File("src/test/resources/" + NOME_FILE));
		}
		WebScriptRequest webScriptRequest = new MockWebScriptRequest("json", templateVars, uploadDoc, fields,
				serviceRegistry);
		MockWebScriptResponse webScriptResponse = new MockWebScriptResponse();
		uploadDoc.execute(webScriptRequest, webScriptResponse);

		// Verify
		List<NodeRef> nodeRefs = searchService
				.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_XPATH,
						FOLDER_DROPZONE + "/" + "d" + ID_PARTNER + "_" + ID_PRATICA + "_" + ID_DOC)
				.getNodeRefs();
		NodeRef result = nodeRefs.get(0);
		Assert.assertNotNull("The file was created", result);
		Set<QName> aspects = nodeService.getAspects(result);
		for (QName aspect : aspects) {
			if (aspect.equals(ContentModel.ASPECT_TITLED))
				Assert.assertEquals("The signed document looks like the title", ContentModel.ASPECT_TITLED, aspect);
			else if (aspect.equals(ContentModel.ASPECT_VERSIONABLE))
				Assert.assertEquals("The signed document is versionable", ContentModel.ASPECT_VERSIONABLE, aspect);
			else if (aspect.equals(VigeWSContentModel.DOC_ASPECT))
				Assert.assertEquals("The signed document has the vigepmi aspect", VigeWSContentModel.DOC_ASPECT,
						aspect);
			else
				Assert.fail();
		}
		Map<QName, Serializable> properties = nodeService.getProperties(result);
		Assert.assertEquals("Title", NOME_FILE, properties.get(ContentModel.PROP_TITLE));
		Assert.assertEquals("Description", DESCRIZIONE, properties.get(VigeWSContentModel.DESC_DOC));
		Assert.assertEquals("Treatment", TRATTAMENTO, properties.get(VigeWSContentModel.TRATTAMENTO_DOC));
		Assert.assertEquals("Id document", ID_DOC, properties.get(VigeWSContentModel.ID_DOC));
		Assert.assertEquals("Id partner", ID_PARTNER, properties.get(VigeWSContentModel.ID_PARTNER));
		Assert.assertEquals("Document code", CODICE_DOC, properties.get(VigeWSContentModel.CODICE_DOC));
		Assert.assertEquals("Note", NOTE, properties.get(VigeWSContentModel.NOTE));
		Assert.assertEquals("Type category", CATEGORIA_TIPO, properties.get(VigeWSContentModel.CATEGORIA_TIPO_DOC));
		Assert.assertEquals("Id user", ID_USER, properties.get(VigeWSContentModel.ID_USER));
		Assert.assertEquals("Document number", NUMERO_DOC, properties.get(VigeWSContentModel.NUMERO_DOC));
		Assert.assertEquals("Id practice", ID_PRATICA, properties.get(VigeWSContentModel.ID_PRATICA));
		Assert.assertEquals("File name", NOME_FILE, properties.get(VigeWSContentModel.NOME_FILE));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Assert.assertEquals("Practice creation date", dateFormat.parse(DATA_CREAZIONE),
				properties.get(VigeWSContentModel.DATA_CREAZIONE_PRATICA));
		Assert.assertEquals("Emission date", dateFormat.parse(DATA_EMISSIONE),
				properties.get(VigeWSContentModel.DATA_EMISSIONE_DOC));
		Assert.assertEquals("Deadline date", dateFormat.parse(DATA_SCADENZA),
				properties.get(VigeWSContentModel.DATA_SCADENZA_DOC));
		Map<String, Object> model = webScriptResponse.getModel();
		Assert.assertEquals("Status ok", STATUS_CREATED + "", model.get("status").toString());

		logger.debug("end test");
	}
}