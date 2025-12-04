package it.vige.ws.api;

import it.vige.ws.bean.Signer;
import it.vige.ws.dom.VigeWSContentModel;
import it.vige.ws.service.SignService;
import it.vige.ws.templateManager.drools.DroolsConverterImpl;
import it.vige.ws.utils.GenerationUtils;
import java.io.ByteArrayOutputStream;
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
import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

/**
 * Mock implementation of the SignPDFGeneration class for testing purposes. This class provides a
 * mock implementation that allows unit and integration tests to run without requiring a full
 * Alfresco server instance.
 *
 * @author Generated
 * @version 7.4.2.1.1
 */
public class SignPDFGeneration extends DeclarativeWebScript {

  /** The file folder service. */
  private FileFolderService fileFolderService;

  /** The node service. */
  private NodeService nodeService;

  /** The sign service. */
  private SignService signService;

  /** The generation util. */
  private GenerationUtils generationUtil;

  /** The pdf conv secret. */
  private String pdfConvSecret;

  /** The generate cedra. */
  private String generateCedra;

  private HashMap<String, Signer> signerList;

  private final String datePattern = "yyyy-MM-dd HH:mm:ss";

  private Log logger = LogFactory.getLog(SignPDFGeneration.class);

  @SuppressWarnings("unchecked")
  /**
   * {@inheritDoc}
   *
   * @param req the req
   * @param status the status
   * @param cache the cache
   * @return the result
   */
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
      destinazioneNodeRef =
          generationUtil.getDestinazioneNodeRef(
              idPartner, idPratica, annoCreazionePratica, meseCreazionePratica, json);
    } catch (NoSuchElementException e) {
      logger.error("Dest folder doesn't exist");
      status.setCode(Status.STATUS_INTERNAL_SERVER_ERROR);
      status.setMessage("Dest folder doesn't exist");
      status.setRedirect(true);
      return model;
    }

    logger.info("identified / created the destination path");

    final String dataCreazionePratica =
        json.get("dataCreazionePratica") != null ? (String) json.get("dataCreazionePratica") : "";

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

          if (nodeService
              .getProperty(figlioNodeRef, VigeWSContentModel.CODICE_DOC)
              .equals(template)) {
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
        final String idUser =
            contenuto.get("idUser") != null ? (String) contenuto.get("idUser") : "";
        final String nomeDoc =
            contenuto.get("nomeDocumento") != null ? (String) contenuto.get("nomeDocumento") : "";
        final String categoriaDoc =
            contenuto.get("descrizioneCategoria") != null
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
        genericMetadata.put(
            "signDoc", contenuto.get("signDoc") != null ? (String) contenuto.get("signDoc") : "");
        genericMetadata.put(
            "signUser",
            contenuto.get("signUser") != null ? (String) contenuto.get("signUser") : "");
        genericMetadata.put(
            "signPassword",
            contenuto.get("signPassword") != null ? (String) contenuto.get("signPassword") : "");

        if ("SI".equals(multiplo)) {
          logger.info("Multiple module: " + template);
          JSONArray datiArray = (JSONArray) contenuto.get("dati");

          for (int i = 0; i < datiArray.size(); i++) {
            JSONObject dati = (JSONObject) datiArray.get(i);

            // check the document id
            final String idDocumento =
                dati.get("idDocumento") != null ? (String) dati.get("idDocumento") : "";
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
          final String idDocumento =
              dati.get("idDocumento") != null ? (String) dati.get("idDocumento") : "";
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
   * @param destinazioneNodeRef
   * @param dati
   * @param metadata
   * @param index
   */
  private void creaPratica(
      NodeRef destinazioneNodeRef, JSONObject dati, Map<String, String> metadata, int index) {

    final String codiceTemplate = metadata.get("codiceTemplate");

    final Map<String, String> parsedTemplateJson =
        generationUtil.parseJsonObject(metadata.get("codiceTemplate"), dati);
    final NodeRef droolsFileNodeRef =
        generationUtil.getRegolaDrools(metadata.get("codiceTemplate"));

    // retrieve the template
    final NodeRef modelloNR =
        generationUtil.getTemplate(
            metadata.get("idPartner"), metadata.get("idPratica"), metadata.get("codiceTemplate"));

    logger.info("successfully retrieved model and rule");

    final DroolsConverterImpl converter = new DroolsConverterImpl();

    try (final InputStream modelloIS =
            fileFolderService.getReader(modelloNR).getContentInputStream();
        final InputStream droolsIS =
            fileFolderService.getReader(droolsFileNodeRef).getContentInputStream();
        final ByteArrayOutputStream fillResultIS =
            converter.fillTemplate(
                modelloIS, droolsIS, parsedTemplateJson, metadata.get("nomeFile"));
        final InputStream conversionResult =
            generationUtil.convertToPdf(pdfConvSecret, fillResultIS, metadata.get("nomeFile"))) {
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

      DateTime dataCreazionePratica =
          DateTime.parse(
              metadata.get("dataCreazionePratica"), DateTimeFormat.forPattern(datePattern));
      props.put(VigeWSContentModel.DATA_CREAZIONE_PRATICA, dataCreazionePratica.toDate());

      // set the flag for self-generation
      props.put(VigeWSContentModel.COD_GENERATION, 1);

      // save the document
      final String docName = (index < 0 ? codiceTemplate : codiceTemplate + "_" + index) + ".pdf";

      if ("S".equals(metadata.get("signDoc")))
        generationUtil.saveDocument(
            destinazioneNodeRef,
            docName,
            signService.signPADES(
                conversionResult, metadata.get("signUser"), metadata.get("signPassword")),
            props);
      else generationUtil.saveDocument(destinazioneNodeRef, docName, conversionResult, props);

    } catch (Exception e) {
      logger.info("Document generation error: " + e.getMessage());
      throw new WebScriptException(500, e.getMessage());
    }

    logger.info("End Crea pratica.");
  }

  /**
   * Gets the file folder service.
   *
   * @return the result
   */
  public FileFolderService getFileFolderService() {
    return fileFolderService;
  }

  /**
   * Sets the file folder service.
   *
   * @param fileFolderService the fileFolderService
   */
  public void setFileFolderService(FileFolderService fileFolderService) {
    this.fileFolderService = fileFolderService;
  }

  /**
   * Gets the node service.
   *
   * @return the result
   */
  public NodeService getNodeService() {
    return nodeService;
  }

  /**
   * Sets the node service.
   *
   * @param nodeService the nodeService
   */
  public void setNodeService(NodeService nodeService) {
    this.nodeService = nodeService;
  }

  /**
   * Gets the sign service.
   *
   * @return the result
   */
  public SignService getSignService() {
    return signService;
  }

  /**
   * Sets the sign service.
   *
   * @param signService the signService
   */
  public void setSignService(SignService signService) {
    this.signService = signService;
  }

  /**
   * Gets the generation util.
   *
   * @return the result
   */
  public GenerationUtils getGenerationUtil() {
    return generationUtil;
  }

  /**
   * Sets the generation util.
   *
   * @param generationUtil the generationUtil
   */
  public void setGenerationUtil(GenerationUtils generationUtil) {
    this.generationUtil = generationUtil;
  }

  /**
   * Gets the pdf conv secret.
   *
   * @return the result
   */
  public String getPdfConvSecret() {
    return pdfConvSecret;
  }

  /**
   * Sets the pdf conv secret.
   *
   * @param pdfConvSecret the pdfConvSecret
   */
  public void setPdfConvSecret(String pdfConvSecret) {
    this.pdfConvSecret = pdfConvSecret;
  }

  /**
   * Gets the generate ceda.
   *
   * @return the result
   */
  public String getGenerateCeda() {
    return generateCedra;
  }

  /**
   * Sets the generate cedra.
   *
   * @param generateCedra the generateCedra
   */
  public void setGenerateCedra(String generateCedra) {
    this.generateCedra = generateCedra;
  }

  public HashMap<String, Signer> getSignerList() {
    return signerList;
  }

  /** Sets the lista firmatari. */
  public void setListaFirmatari(HashMap<String, Signer> signerList) {
    this.signerList = signerList;
  }
}
