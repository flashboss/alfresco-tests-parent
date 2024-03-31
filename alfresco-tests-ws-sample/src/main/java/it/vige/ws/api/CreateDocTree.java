package it.vige.ws.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.model.FileExistsException;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

import it.vige.ws.dom.DocVigeWS;
import it.vige.ws.dom.VigeWSContentModel;

public class CreateDocTree extends DeclarativeWebScript {

	private Logger logger = Logger.getLogger(CreateDocTree.class);

	private SearchService searchService;
	private FileFolderService fileFolderService;
	private NodeService nodeService;

	private String questionsPath;
	private String questionsDocName;
	private String autoGeneratedName;
	private final String datePattern = "yyyy-MM-dd";

	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {

		Map<String, Object> model = new HashMap<>();

		Map<String, String> templateArgs = req.getServiceMatch().getTemplateVars();

		final String idPartner = templateArgs.get("idpartner");
		final String idPratica = templateArgs.get("idpratica");

		final String descConvenzione = req.getParameter("convenzione") != null ? req.getParameter("convenzione") : "";
		final String numeroPratica = req.getParameter("numeroPratica") != null ? req.getParameter("numeroPratica") : "";
		final String descPartner = req.getParameter("descrizionePartner") != null
				? req.getParameter("descrizionePartner")
				: "";
		final String cf = req.getParameter("codiceFiscaleAzienda") != null ? req.getParameter("codiceFiscaleAzienda")
				: "";
		final String ragSoc = req.getParameter("ragioneSocialeAzienda") != null
				? req.getParameter("ragioneSocialeAzienda")
				: "";
		final String dataCreazionePratica = req.getParameter("dataCreazionePratica") != null
				? req.getParameter("dataCreazionePratica")
				: "";
		DateTime dtCreazionePratica = DateTimeFormat.forPattern(datePattern).parseDateTime(dataCreazionePratica);

		// get the metadata for the pratica
		if (descConvenzione.isEmpty() || numeroPratica.isEmpty() || dataCreazionePratica.isEmpty()) {
			logger.error("Error in input parameters");
			status.setCode(Status.STATUS_BAD_REQUEST);
			status.setMessage("Error in input parameters");
			status.setRedirect(true);
			model.put("error", status);
			return model;
		}

		DocVigeWS pratica = new DocVigeWS(idPratica, dtCreazionePratica, numeroPratica, descConvenzione, descPartner,
				cf, ragSoc);

		createDestinazione(idPartner, pratica);

		model.put("pratica", pratica);
		return model;
	}

	private void createDestinazione(String idPartner, DocVigeWS pratica) throws NoSuchElementException {

		// Check if the Questions folder exists ...
		String rootQuery = "PATH:\"" + questionsPath + "\"";
		ResultSet siteRS = searchService.query(StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
				SearchService.LANGUAGE_FTS_ALFRESCO, rootQuery);

		// The Questions folder must exist
		if (siteRS.length() != 1) {
			throw new NoSuchElementException("The questions folder does not exist");
		}

		// extracts the info from the date of the practice
		String annoCreazione = String.valueOf(pratica.getDataCreazionePratica().getYear());
		String meseCreazione = (String.valueOf(pratica.getDataCreazionePratica().getMonthOfYear()));

		String meseCreazione2 = StringUtils.leftPad(meseCreazione, 2, '0');

		List<String> foldersToSearch = new ArrayList<>();
		foldersToSearch.add(idPartner);
		foldersToSearch.add(annoCreazione);
		foldersToSearch.add(meseCreazione2);
		foldersToSearch.add(pratica.getIdPratica());

		NodeRef currentParentNodeRef = siteRS.getNodeRef(0);

		// eventually create all the folders of the new path
		for (String currentChildFolder : foldersToSearch) {

			NodeRef currentFolderNodeRef = fileFolderService.searchSimple(currentParentNodeRef, currentChildFolder);

			// it the path doesn't exists, it will be created
			if (currentFolderNodeRef == null) {
				FileInfo fi = fileFolderService.create(currentParentNodeRef, currentChildFolder,
						ContentModel.TYPE_FOLDER);
				currentParentNodeRef = fi.getNodeRef();
			} else {
				currentParentNodeRef = currentFolderNodeRef;
			}
		}

		// create the Documents folder
		try {
			fileFolderService.create(currentParentNodeRef, questionsDocName, ContentModel.TYPE_FOLDER);
		} catch (FileExistsException e) {
			logger.warn("The documents folder already exixts");
		}

		// set the metadata for the pratica Folder
		Map<QName, Serializable> praticaProps = new HashMap<>();
		praticaProps.put(VigeWSContentModel.ID_PRAT, pratica.getIdPratica());
		praticaProps.put(VigeWSContentModel.ID_PARTNER_PRATICA, idPartner);
		praticaProps.put(VigeWSContentModel.DESC_PARTNER_PRATICA, pratica.getDescrizionePartner());
		praticaProps.put(VigeWSContentModel.COD_FIS_PRATICA, pratica.getCodiceFiscaleAzienda());
		praticaProps.put(VigeWSContentModel.DESC_CONVENZ_PRATICA, pratica.getConvenzione());
		praticaProps.put(VigeWSContentModel.RAG_SOC_PRATICA, pratica.getRagioneSocialeAzienda());

		praticaProps.put(VigeWSContentModel.DATA_CREAZ_PRATICA, pratica.getDataCreazionePratica().toDate());

		nodeService.addProperties(currentParentNodeRef, praticaProps);

		NodeRef prevAutoGenFolderNR = fileFolderService.searchSimple(currentParentNodeRef, autoGeneratedName);
		if (prevAutoGenFolderNR != null) {
			fileFolderService.delete(prevAutoGenFolderNR);
		}
		// create the autogenerated folder
		fileFolderService.create(currentParentNodeRef, autoGeneratedName, ContentModel.TYPE_FOLDER);

	}

	public void setQuestionsPath(String questionsPath) {
		this.questionsPath = questionsPath;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public void setQuestionsDocName(String questionsDocName) {
		this.questionsDocName = questionsDocName;
	}

	public void setAutoGeneratedName(String autoGeneratedName) {
		this.autoGeneratedName = autoGeneratedName;
	}

	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}

	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}
}
