package it.vige.ws.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptException;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.servlet.FormData;
import org.springframework.extensions.webscripts.servlet.FormData.FormField;

import it.vige.ws.bean.Signer;
import it.vige.ws.dom.VigeWSContentModel;
import it.vige.ws.service.SignService;
import it.vige.ws.utils.GenerationUtils;

/**
 * WebScript for generating and signing PDF documents.
 * This class handles the generation of PDF documents from templates
 * and optionally signs them using PAdES format.
 *
 * @author lucastancapiano
 */
public class SignPDFGeneration extends DeclarativeWebScript {

	/** The file folder service. */
	private FileFolderService fileFolderService;

	/** The node service. */
	private NodeService nodeService;

	/** The sign service. */
	private SignService signService;

	/** The generation utility. */
	private GenerationUtils generationUtil;

	/** The generate cedra flag. */
	private String generateCedra;

	/** The signer list. */
	private HashMap<String, Signer> signerList;

	/** The date pattern for parsing dates. */
	private final String datePattern = "yyyy-MM-dd HH:mm:ss";

	/** The logger instance. */
	private Logger logger = Logger.getLogger(SignPDFGeneration.class);

	/**
	 * Executes the webscript to generate and sign PDF documents.
	 *
	 * @param req the webscript request containing form data and template variables
	 * @param status the status object for setting response codes
	 * @param cache the cache object
	 * @return a map containing the model data with generated document IDs
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {

		logger.info(">>> PDF sign generation");

		Map<String, Object> model = new HashMap<>();
		FormData formData = (FormData) req.parseContent();

		if (formData == null) {
			logger.error("Bad Req... No multipart form data received");
			status.setCode(Status.STATUS_BAD_REQUEST);
			status.setMessage("Bad Req... No multipart form data received");
			status.setRedirect(true);
			return model;
		}

		FormField[] formFields = formData.getFields();
		Map<String, String> templateArgs = req.getServiceMatch().getTemplateVars();

		if (formFields.length == 0 || templateArgs.size() < 4) {
			logger.error("Mandatory parameters not all completed");
			status.setCode(Status.STATUS_BAD_REQUEST);
			status.setMessage("Mandatory parameters not all completed");
			status.setRedirect(true);
			return model;
		}

		final String idPartner = templateArgs.get("idpartner");
		final String idPratica = templateArgs.get("idpratica");
		final String annoCreazionePratica = templateArgs.get("annopratica");
		final String meseCreazionePratica = templateArgs.get("mesepratica");

		JSONObject json;

		try (final InputStream jsonInputStream = formFields[0].getInputStream()) {
			JSONParser parser = new JSONParser();
			json = (JSONObject) parser.parse(new InputStreamReader(jsonInputStream));
			logger.info("json parsed");
		} catch (IOException | ParseException e) {
			logger.error("can't parse the json", e);
			status.setCode(Status.STATUS_INTERNAL_SERVER_ERROR);
			status.setMessage("can't parse the json");
			status.setRedirect(true);
			return model;
		}

		logger.info("Parsing of the json performed");

		Set<String> templates = json.keySet();
		NodeRef destinazioneNodeRef;

		try {
			destinazioneNodeRef = generationUtil.getDestinazioneNodeRef(idPartner, idPratica, annoCreazionePratica,
					meseCreazionePratica, json);
		} catch (NoSuchElementException e) {
			logger.error("Dest folder doesn't exist");
			status.setCode(Status.STATUS_INTERNAL_SERVER_ERROR);
			status.setMessage("Dest folder doesn't exist");
			status.setRedirect(true);
			return model;
		}

		logger.info("identified / created the destination path");

		final String dataCreazionePratica = json.get("dataCreazionePratica") != null
				? (String) json.get("dataCreazionePratica")
				: "";

		List<String> jsonObjects = new ArrayList<>();
		// For each template: fillTemplate it and convert it in pdf
		for (String template : templates) {

			if (json.get(template) instanceof JSONObject) {

				/*
				 * Elimino eventuali modelli precedentemente generati per la stessa tipologia di
				 * documento
				 */
				List<ChildAssociationRef> listaFiles = nodeService.getChildAssocs(destinazioneNodeRef);
				for (ChildAssociationRef figlio : listaFiles) {
					NodeRef figlioNodeRef = figlio.getChildRef();

					if (nodeService.getProperty(figlioNodeRef, VigeWSContentModel.CODICE_DOC).equals(template)) {
						nodeService.addAspect(figlioNodeRef, ContentModel.ASPECT_TEMPORARY, null);
						fileFolderService.delete(figlioNodeRef);
						break;
					}
				}

				JSONObject contenuto = (JSONObject) json.get(template);

				String multiplo = (String) contenuto.get("istanzeMultiple");
				String genera = (String) contenuto.get("genera");

				if (!"SI".equals(genera)) {
					continue;
				}

				// get the generic metadata for the document
				Map<String, String> genericMetadata = new HashMap<>();
				final String idUser = contenuto.get("idUser") != null ? (String) contenuto.get("idUser") : "";
				final String nomeDoc = contenuto.get("nomeDocumento") != null ? (String) contenuto.get("nomeDocumento")
						: "";
				final String categoriaDoc = contenuto.get("descrizioneCategoria") != null
						? (String) contenuto.get("descrizioneCategoria")
						: "";
				final String nomeFile = nomeDoc != null ? nomeDoc + ".pdf" : "";

				genericMetadata.put("codiceTemplate", template);
				genericMetadata.put("idPartner", idPartner);
				genericMetadata.put("idPratica", idPratica);
				genericMetadata.put("idUser", idUser);
				genericMetadata.put("nomeDoc", nomeDoc);
				genericMetadata.put("categoriaDoc", categoriaDoc);
				genericMetadata.put("nomeFile", nomeFile);
				genericMetadata.put("dataCreazionePratica", dataCreazionePratica);
				genericMetadata.put("signDoc",
						contenuto.get("signDoc") != null ? (String) contenuto.get("signDoc") : "");
				genericMetadata.put("signUser",
						contenuto.get("signUser") != null ? (String) contenuto.get("signUser") : "");
				genericMetadata.put("signPassword",
						contenuto.get("signPassword") != null ? (String) contenuto.get("signPassword") : "");

				if ("SI".equals(multiplo)) {
					logger.info("Multiple module: " + template);
					JSONArray datiArray = (JSONArray) contenuto.get("dati");

					for (int i = 0; i < datiArray.size(); i++) {
						JSONObject dati = (JSONObject) datiArray.get(i);

						// check the document id
						final String idDocumento = dati.get("idDocumento") != null ? (String) dati.get("idDocumento")
								: "";
						if (idDocumento.isEmpty()) {
							logger.error("id_documento cannot be empty");
							status.setCode(Status.STATUS_INTERNAL_SERVER_ERROR);
							status.setMessage("id_documento cannot be empty");
							status.setRedirect(true);
							return model;
						}

						logger.info("doc to generate: " + idPartner + "/" + idPratica + "/" + idDocumento);

						genericMetadata.put("idDocumento", idDocumento);
						creaPratica(destinazioneNodeRef, dati, genericMetadata, i);

						jsonObjects.add("{\"id\" : \"" + idDocumento + "\"}");
					}
				} else {
					// single document
					logger.info("Single module: " + template);
					JSONObject dati = (JSONObject) contenuto.get("dati");

					// check the document id
					final String idDocumento = dati.get("idDocumento") != null ? (String) dati.get("idDocumento") : "";
					if (idDocumento.isEmpty()) {
						logger.error("id_documento can't be empty");
						status.setCode(Status.STATUS_INTERNAL_SERVER_ERROR);
						status.setMessage("id_documento can't be empty");
						status.setRedirect(true);
						return model;
					}

					genericMetadata.put("idDocumento", idDocumento);
					creaPratica(destinazioneNodeRef, dati, genericMetadata, -1);

					jsonObjects.add("{\"id\" : \"" + idDocumento + "\"}");
				}
			}
		}

		model.put("generatiList", StringUtils.join(jsonObjects.toArray(), ","));
		return model;
	}

	/**
	 * Creates a practice document from template data.
	 *
	 * @param destinazioneNodeRef the destination folder node reference
	 * @param dati the JSON data for the document
	 * @param metadata the metadata map containing document properties
	 * @param index the index for multiple documents, -1 for single documents
	 */
	private void creaPratica(NodeRef destinazioneNodeRef, JSONObject dati, Map<String, String> metadata, int index) {

		final String codiceTemplate = metadata.get("codiceTemplate");
		final NodeRef droolsFileNodeRef = generationUtil.getRegolaDrools(metadata.get("codiceTemplate"));

		// retrieve the template
		final NodeRef modelloNR = generationUtil.getTemplate(metadata.get("idPartner"), metadata.get("idPratica"),
				metadata.get("codiceTemplate"));

		logger.info("successfully retrieved model and rule");

		try (final InputStream modelloIS = fileFolderService.getReader(modelloNR).getContentInputStream();
				final InputStream droolsIS = fileFolderService.getReader(droolsFileNodeRef).getContentInputStream();
				final InputStream conversionResult = generationUtil.convertToPdf(metadata.get("nomeFile"))) {
			// fill the template

			logger.info("document compilation and conversion completed");

			Map<QName, Serializable> props = new HashMap<>();
			props.put(VigeWSContentModel.ID_DOC, metadata.get("idDocumento"));
			props.put(VigeWSContentModel.ID_PARTNER, metadata.get("idPartner"));
			props.put(VigeWSContentModel.ID_PRATICA, metadata.get("idPratica"));
			props.put(VigeWSContentModel.ID_USER, metadata.get("idUser"));

			props.put(VigeWSContentModel.DESC_DOC, metadata.get("nomeDoc"));
			props.put(VigeWSContentModel.CATEGORIA_TIPO_DOC, metadata.get("categoriaDoc"));
			props.put(VigeWSContentModel.CODICE_DOC, codiceTemplate);
			props.put(VigeWSContentModel.NOME_FILE, metadata.get("nomeFile"));

			DateTime dataCreazionePratica = DateTimeFormat.forPattern(datePattern)
					.parseDateTime(metadata.get("dataCreazionePratica"));
			props.put(VigeWSContentModel.DATA_CREAZIONE_PRATICA, dataCreazionePratica.toDate());

			// set the flag for self-generation
			props.put(VigeWSContentModel.COD_GENERATION, 1);

			// save the document
			final String docName = (index < 0 ? codiceTemplate : codiceTemplate + "_" + index) + ".pdf";

			if ("S".equals(metadata.get("signDoc")))
				generationUtil.saveDocument(destinazioneNodeRef, docName,
						signService.signPADES(conversionResult, metadata.get("signUser"), metadata.get("signPassword")),
						props);
			else
				generationUtil.saveDocument(destinazioneNodeRef, docName, conversionResult, props);

		} catch (Exception e) {
			logger.info("Document generation error: " + e.getMessage());
			throw new WebScriptException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}

		logger.info("End Crea pratica.");

	}

	/**
	 * Gets the file folder service.
	 *
	 * @return the file folder service
	 */
	public FileFolderService getFileFolderService() {
		return fileFolderService;
	}

	/**
	 * Sets the file folder service.
	 *
	 * @param fileFolderService the file folder service to set
	 */
	public void setFileFolderService(FileFolderService fileFolderService) {
		this.fileFolderService = fileFolderService;
	}

	/**
	 * Gets the node service.
	 *
	 * @return the node service
	 */
	public NodeService getNodeService() {
		return nodeService;
	}

	/**
	 * Sets the node service.
	 *
	 * @param nodeService the node service to set
	 */
	public void setNodeService(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	/**
	 * Gets the sign service.
	 *
	 * @return the sign service
	 */
	public SignService getSignService() {
		return signService;
	}

	/**
	 * Sets the sign service.
	 *
	 * @param signService the sign service to set
	 */
	public void setSignService(SignService signService) {
		this.signService = signService;
	}

	/**
	 * Gets the generation utility.
	 *
	 * @return the generation utility
	 */
	public GenerationUtils getGenerationUtil() {
		return generationUtil;
	}

	/**
	 * Sets the generation utility.
	 *
	 * @param generationUtil the generation utility to set
	 */
	public void setGenerationUtil(GenerationUtils generationUtil) {
		this.generationUtil = generationUtil;
	}

	/**
	 * Gets the generate cedra flag.
	 *
	 * @return the generate cedra flag
	 */
	public String getGenerateCeda() {
		return generateCedra;
	}

	/**
	 * Sets the generate cedra flag.
	 *
	 * @param generateCedra the generate cedra flag to set
	 */
	public void setGenerateCedra(String generateCedra) {
		this.generateCedra = generateCedra;
	}

	/**
	 * Gets the signer list.
	 *
	 * @return the signer list
	 */
	public HashMap<String, Signer> getSignerList() {
		return signerList;
	}

	/**
	 * Sets the signer list.
	 *
	 * @param signerList the signer list to set
	 */
	public void setListaFirmatari(HashMap<String, Signer> signerList) {
		this.signerList = signerList;
	}
}
