package it.vige.ws.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
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
import it.vige.ws.utils.AttoUtil;

public class NodeListDownloadWebScriptTest extends AbstractWSForm {

	@Autowired
	private NodeListDownloadWebScript nodeListDownloadWebScript;

	@Before
	public void init() {
		super.init();
		// initialize repository with test nodes
		NamespaceService namespaceService = serviceRegistry.getNamespaceService();
		namespaceService.registerNamespace("crlatti", AttoUtil.CRL_ATTI_MODEL);
		// NodeRef companyHome = insertFolder(spacesStore,
		// NamespaceService.APP_MODEL_PREFIX, "company_home");
		NodeRef crl = insertFolder(companyHome, "CRL");
		NodeRef gestioneAtti = insertFolder(crl, "Gestione Atti");
		NodeRef legislatura = insertFolder(gestioneAtti, "XII");
		NodeRef anno = insertFolder(legislatura, "2024");
		NodeRef mese = insertFolder(anno, "1");
		NodeRef PDL = insertFolder(mese, "PDL");

		createAtto(PDL, "1");

	}

	private void createAtto(NodeRef PDL, String name) {
		Map<QName, Serializable> properties = new HashMap<QName, Serializable>(11);
		properties.put(ContentModel.PROP_NAME, name);
		properties.put(AttoUtil.PROP_NUMERO_ATTO_QNAME, Integer.parseInt(name));
		properties.put(AttoUtil.PROP_DATA_INIZIATIVA_ATTO_QNAME, LocalDateTime.now());
		properties.put(AttoUtil.PROP_TIPO_INIZIATIVA_QNAME, "01_ATTO DI INIZIATIVA CONSILIARE");
		properties.put(AttoUtil.PROP_NUMERO_ATTO_QNAME, Integer.parseInt(name));
		properties.put(AttoUtil.PROP_OGGETTO_ATTO_QNAME, name);
		properties.put(AttoUtil.PROP_LEGISLATURA_QNAME, "XII");
		properties.put(ContentModel.TYPE_BASE, AttoUtil.TYPE_ATTO_PDL);

		NodeRef document = insertDocument(PDL, name, "testbytes", properties);

		String nameQ = name + "_child";
		properties = new HashMap<QName, Serializable>(11);
		properties.put(ContentModel.PROP_NAME, nameQ);
		properties.put(ContentModel.TYPE_BASE, AttoUtil.PROP_LEGISLATURA_QNAME);
		insertDocument(document, nameQ, "testbytes_child", properties);
		
		properties = new HashMap<QName, Serializable>(11);
		nameQ = name + "_target";
		properties.put(ContentModel.PROP_NAME, nameQ);
		properties.put(ContentModel.TYPE_BASE, AttoUtil.PROP_LEGISLATURA_QNAME);
		NodeRef target = insertDocument(PDL, nameQ, "testbytes_target", properties);

		NodeService nodeService = serviceRegistry.getNodeService();
		nodeService.createAssociation(document, target, AttoUtil.PROP_LEGISLATURA_QNAME);
	}

	@Override
	protected AbstractWebScript getAbstractWebScript() {
		return nodeListDownloadWebScript;
	}

	@Test
	public void execute() throws IOException {

		// execute ws
		Map<String, Serializable> fields = new HashMap<String, Serializable>();
		{
			fields.put("type", "crlatti:attoPdl");
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
