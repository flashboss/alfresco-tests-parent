package it.vige.ws.test;

import java.io.IOException;
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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tradeshift.test.remote.Remote;
import com.tradeshift.test.remote.RemoteTestRunner;

import it.vige.ws.WSSampleModel;
import it.vige.ws.PreviousWSSample;

@RunWith(RemoteTestRunner.class)
@Remote(runnerClass = SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-module-context.xml")
public class PreviousWSSampleTest extends AbstractWSForm {

	private final static String CARTELLA_WSSAMPLE = "WSSAMPLE-20157726";
	private final static String dataModifica = "2020-06-19";

	@Autowired
	private PreviousWSSample pregressoWSSample;

	private DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmm");
	private NodeRef repository;

	@Before
	public void init() {
		super.init();
		NamespaceService namespaceService = serviceRegistry.getNamespaceService();
		namespaceService.registerNamespace("mccpb", WSSampleModel.PBPDV_NAMESPACE);

		// creo le directory iniziali per i pdv, gli rdv e il repository
		NodeRef site = insertFolder(sites, "conservazione-digitale-medio-credito-centrale");
		insertFolder(site, "documentLibrary");

		NodeRef medioCreditoCentrale = insertFolder(sites, "medio-credito-centrale");
		NodeRef medioCreditoCentraleDL = insertFolder(medioCreditoCentrale, "documentLibrary");
		repository = insertFolder(medioCreditoCentraleDL, "repository");
		insertFolder(repository, CARTELLA_WSSAMPLE);
	}

	@Override
	protected DeclarativeWebScript getDeclarativeWebScript() {
		return pregressoWSSample;
	}

	@Test
	public void execute() throws ParseException, IOException {

		SearchService searchService = serviceRegistry.getSearchService();
		NodeService nodeService = serviceRegistry.getNodeService();
		Map<String, String> fields = new HashMap<String, String>();
		{
			fields.put("date_modified", dataModifica);
			fields.put("date_ws_end", dataModifica);
			fields.put("codicews", CARTELLA_WSSAMPLE);
			fields.put("date_ws_start", "1970-01-01");
		}
		WebScriptRequest webScriptRequest = new MockWebScriptRequest("json", null, pregressoWSSample, fields);
		pregressoWSSample.execute(webScriptRequest, new MockWebScriptResponse());

		// Verifico la cancellazione dei file
		List<NodeRef> nodeRefs = searchService
				.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE, SearchService.LANGUAGE_XPATH, CARTELLA_WSSAMPLE)
				.getNodeRefs();
		NodeRef result = nodeRefs.get(0);
		Set<QName> aspects = nodeService.getAspects(result);
		Assert.assertEquals("Un aspetto per la cartella", 1, aspects.size());
		QName aspect = aspects.iterator().next();
		Assert.assertEquals("Aggiunto l'aspetto per i folder WS Sample", WSSampleModel.ASPECT_WSSAMPLEFOLDER, aspect);
		Date dataCedacri = (Date) nodeService.getProperty(result, WSSampleModel.PROP_DATA_CEDACRI);
		Assert.assertEquals("Aggiunta la data cedacri", dataModifica, dateFormat.format(dataCedacri));
	}

}
