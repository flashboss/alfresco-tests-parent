package it.vige.ws.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.alfresco.mock.test.ws.AbstractWSForm;
import org.alfresco.mock.test.ws.MockWebScriptRequest;
import org.alfresco.mock.test.ws.MockWebScriptResponse;
import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.webscripts.AbstractWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;

import it.vige.ws.api.NodeListDownloadWebScript;
import it.vige.ws.utils.ActUtil;

/**
 * @author vige
 */
public class NodeListDownloadWebScriptTest extends AbstractWSForm {

	@Autowired
	private NodeListDownloadWebScript nodeListDownloadWebScript;

	private NodeRef PDL;
	private NodeRef act;

	@Before
	public void init() {
		super.init();
		// initialize repository with test nodes
		NamespaceService namespaceService = serviceRegistry.getNamespaceService();
		namespaceService.registerNamespace("crlacts", ActUtil.CRL_ACTS_MODEL);
		// NodeRef companyHome = insertFolder(spacesStore,
		// NamespaceService.APP_MODEL_PREFIX, "company_home");
		NodeRef crl = insertFolder(companyHome, "CRL");
		NodeRef gestioneActs = insertFolder(crl, "Gestione Acts");
		NodeRef legislature = insertFolder(gestioneActs, "XII");
		NodeRef anno = insertFolder(legislature, "2024");
		NodeRef mese = insertFolder(anno, "1");
		PDL = insertFolder(mese, "PDL");

		act = createAct(PDL, "1");

	}

	private NodeRef createAct(NodeRef PDL, String name) {
		Map<QName, Serializable> properties = new HashMap<QName, Serializable>(11);
		properties.put(ContentModel.PROP_NAME, name);
		properties.put(ActUtil.PROP_NUMERO_ACT_QNAME, Integer.parseInt(name));
		properties.put(ActUtil.PROP_NUMERO_ACT_QNAME, Integer.parseInt(name));
		properties.put(ActUtil.PROP_OGGETTO_ACT_QNAME, name);
		properties.put(ActUtil.PROP_LEGISLATURE_QNAME, "XII");
		properties.put(ContentModel.TYPE_BASE, ActUtil.TYPE_ACT_PDL);

		return insertDocument(PDL, name, "testbytes", properties);
	}

	private void addStateAct(NodeRef document, NodeRef PDL) {
		NodeService nodeService = serviceRegistry.getNodeService();
		String name = (String) nodeService.getProperty(document, ContentModel.PROP_NAME);
		String nameQ = name + "_target";
		Map<QName, Serializable> properties = new HashMap<QName, Serializable>(11);
		properties.put(ContentModel.PROP_NAME, nameQ);
		properties.put(ContentModel.TYPE_BASE, ActUtil.PROP_LEGISLATURE_QNAME);
		NodeRef target = insertDocument(PDL, nameQ, "testbytes_target", properties);

		nodeService.createAssociation(document, target, ActUtil.PROP_STATE_ACT_QNAME);
	}

	@Override
	protected AbstractWebScript getAbstractWebScript() {
		return nodeListDownloadWebScript;
	}

	@Test
	public void execute() throws IOException {

		addStateAct(act, PDL);
		// execute ws
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		{
			fields.put("type", "crlacts:actPdl");
			fields.put("skipCount", "0");
			fields.put("maxItems", "10");
		}

		WebScriptRequest request = new MockWebScriptRequest("xls", null, nodeListDownloadWebScript, fields,
				serviceRegistry);
		MockWebScriptResponse response = new MockWebScriptResponse();
		nodeListDownloadWebScript.execute(request, response);
		// verify
		Map<String, Object> model = response.getModel();
		byte[] ba = (byte[]) model.get("excel");
		assertNotNull(ba);
		InputStream bais = new ByteArrayInputStream(ba);
		Workbook wb = new HSSFWorkbook(bais);
		Sheet sheet = wb.getSheet("Export");
		assertNotNull(sheet);
		Row row0 = sheet.getRow(0);
		assertNotNull(row0);
		String[] overHeadings = nodeListDownloadWebScript.getOverHeadings();
		for (int i = 0; i < row0.getPhysicalNumberOfCells(); i++) {
			assertEquals(overHeadings[i], row0.getCell(i).toString());
		}
		Row row1 = sheet.getRow(1);
		assertNotNull(row1);
	}

}
