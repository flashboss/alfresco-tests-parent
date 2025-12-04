package it.vige.ws.api;

import it.vige.ws.dom.VigeWSContentModel;
import it.vige.ws.service.NoteReportBean;
import it.vige.ws.service.SignService;
import it.vige.ws.service.SignedDocumentReportBean;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.alfresco.model.ContentModel;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.model.FileInfo;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.ContentWriter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.version.VersionService;
import org.alfresco.service.namespace.QName;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptException;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.servlet.FormData;

/**
 * Class providing functionality for Alfresco testing.
 *
 * @author vige
 */
public class UploadDoc extends DeclarativeWebScript {
  /** The logger. */
  private Logger logger = Logger.getLogger(UploadDoc.class);

  FileFolderService fileFolderService;
  SearchService searchService;
  NodeService nodeService;
  ContentService contentService;
  VersionService versionService;
  SignService signService;

  String dropzonePath;

  boolean applicaMarca;

  /** The pades type. */
  String PADES_TYPE = "PAdES";

  /** The pades description. */
  String PADES_DESCRIPTION =
      "The sign does not respect the PAdES format as it does not take into account the entire"
          + " document";

  /**
   * Execute impl.
   *
   * @param req the req
   * @param status the status
   * @param cache the cache
   */
  @Override
  protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {

    Map<String, Object> model = new HashMap<>();

    Map<String, String> templateArgs = req.getServiceMatch().getTemplateVars();

    /** The id partner. */
    String idPartner = templateArgs.get("idpartner");

    /** The id pratica. */
    String idPratica = templateArgs.get("idpratica");

    /** The id doc. */
    String idDoc = templateArgs.get("iddoc");

    logger.info(">>> Upload doc: " + idPartner + " - " + idPratica + " - " + idDoc);

    FormData formData = (FormData) req.parseContent();

    if (formData == null) {
      logger.error("Bad Req... No multipart form data Found");
      status.setCode(Status.STATUS_BAD_REQUEST);
      status.setMessage("File to upload not found");
      status.setRedirect(true);
      return model;
    }

    FormData.FormField[] formFields = formData.getFields();

    InputStream fileToUpload = null;

    /** The file name. */
    String fileName = "";

    /** The file type. */
    String fileType = "";

    // metadata control
    String signVerify = "";

    /** The cod doc. */
    String codDoc = "";

    /** The categoria tipo. */
    String categoriaTipo = "";

    /** The trattamento. */
    String trattamento = "";

    /** The id user. */
    String idUser = "";

    /** The desc. */
    String desc = "";

    /** The numero doc. */
    String numeroDoc = "";

    /** The note. */
    String note = "";

    /** The data creazione pratica. */
    String dataCreazionePratica = "";

    /** The data emissione string. */
    String dataEmissioneString = "";

    /** The data scadenza string. */
    String dataScadenzaString = "";

    for (FormData.FormField field : formFields) {

      if (field.getIsFile()) {
        fileToUpload = field.getInputStream();
        fileName = field.getFilename();
        fileType = field.getMimetype();
      } else {
        switch (field.getName()) {
          case "codicedoc":
            codDoc = field.getValue();
            break;
          case "categoriatipo":
            categoriaTipo = field.getValue();
            break;
          case "trattamento":
            trattamento = field.getValue();
            break;
          case "iduser":
            idUser = field.getValue();
            break;
          case "desc":
            desc = field.getValue();
            break;
          case "numerodoc":
            numeroDoc = field.getValue();
            break;
          case "note":
            note = field.getValue();
            break;
          case "datacreazione":
            dataCreazionePratica = field.getValue();
            break;
          case "dataemissione":
            dataEmissioneString = field.getValue();
            break;
          case "datascadenza":
            dataScadenzaString = field.getValue();
            break;
          case "signVerify":
            signVerify = field.getValue();
            break;
          default:
        }
      }
    }

    // check mandatory fields
    if (fileToUpload == null
        || StringUtils.isBlank(dataCreazionePratica)
        || StringUtils.isBlank(idDoc)
        || StringUtils.isBlank(codDoc)
        || StringUtils.isBlank(categoriaTipo)
        || StringUtils.isBlank(trattamento)
        || StringUtils.isBlank(idUser)
        || StringUtils.isBlank(desc)) {

      logger.error("Mandatory property check failed");
      status.setCode(Status.STATUS_BAD_REQUEST);
      status.setMessage("Values have not been specified for all mandatory properties");
      status.setRedirect(true);
      return model;
    }

    if (fileToUpload != null) {

      byte[] fileToVerify;
      try {
        fileToVerify = IOUtils.toByteArray(fileToUpload);
      } catch (Exception e) {
        logger.error("Error converting stream");
        status.setCode(Status.STATUS_INTERNAL_SERVER_ERROR);
        status.setMessage("Error converting stream");
        status.setRedirect(true);
        return model;
      }

      // Check the sign of the input file if necessary ...
      // subsequently affixes a time stamp to extend its validity
      if ("s".equals(signVerify)) {
        try {
          SignedDocumentReportBean signedReportBean = signService.verifica(fileToVerify);

          // check if there is any problem
          if (!signedReportBean.isOverallVerified()) {

            StringBuilder message = new StringBuilder();

            List<NoteReportBean> noteReportBeans = signedReportBean.getNoteReportList();

            for (NoteReportBean nrb : noteReportBeans) {
              // only errors are taken into account
              // It is necessary to apply a different logo from CAdES and PAdES as in the
              // second
              // case
              // the applied sign may be incorrect for the verification service
              // but correct in general
              if (nrb.getType() == 3) {
                if (signedReportBean.getSignatureFormat().equals(this.PADES_TYPE)) {
                  if (!nrb.getDescription().equals(this.PADES_DESCRIPTION)) {
                    message.append(" [Errore:").append(nrb.getDescription()).append("]\n");
                  }
                } else {
                  message.append(" [Errore:").append(nrb.getDescription()).append("]\n");
                }
              }
            }

            // Only if I have messages do I return the error
            if (StringUtils.isNotEmpty(message.toString())) {
              logger.error("Invalid Sign");
              status.setCode(Status.STATUS_NOT_ACCEPTABLE);
              status.setMessage("Invalid Sign: " + message);
              status.setRedirect(true);
              return model;
            }
          }

          if (applicaMarca) {
            fileToVerify = signService.applicaMarcaTemporale(fileToVerify);
            fileName = fileName + ".tsd";
            fileType = "application/octet-stream";
          }
        } catch (Exception e) {
          logger.error("Error in verifying the sign", e);
          status.setCode(Status.STATUS_NOT_ACCEPTABLE);
          status.setMessage("Verification of sign not possible.");
          status.setRedirect(true);
          return model;
        }
      }

      // Check if the dropzone folder exists ...
      DateTime dataCreazione = DateTime.parse(dataCreazionePratica);

      /** The dropzone query. */
      String dropzoneQuery = "PATH:\"" + dropzonePath + "\"";
      ResultSet siteRS =
          searchService.query(
              StoreRef.STORE_REF_WORKSPACE_SPACESSTORE,
              SearchService.LANGUAGE_FTS_ALFRESCO,
              dropzoneQuery);

      logger.debug("query: " + dropzoneQuery);

      // The Dropzone folder must exist and must be unique
      if (siteRS.length() != 1) {
        logger.error("The Dropzone folder does not exist");
        status.setCode(Status.STATUS_BAD_REQUEST);
        status.setMessage("The Dropzone folder does not exist");
        status.setRedirect(true);
        return model;
      }

      NodeRef dropzoneFolderNodeRef = siteRS.getNodeRef(0);

      // convert the data
      String pattern = "yyyy-MM-dd";
      DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);

      Date dataEmissione = null;
      Date dataScadenza = null;
      try {
        if (!dataEmissioneString.isEmpty()) {
          dataEmissione = dtf.parseDateTime(dataEmissioneString).toDate();
        }

        if (!dataScadenzaString.isEmpty()) {
          dataScadenza = dtf.parseDateTime(dataScadenzaString).toDate();
        }
      } catch (RuntimeException e) {
        logger.error("Date error: " + e);
        throw new WebScriptException(500, "Date format error");
      }

      Map<QName, Serializable> props = new HashMap<>();
      props.put(VigeWSContentModel.ID_DOC, idDoc);
      props.put(VigeWSContentModel.CODICE_DOC, codDoc);
      props.put(VigeWSContentModel.CATEGORIA_TIPO_DOC, categoriaTipo);
      props.put(VigeWSContentModel.TRATTAMENTO_DOC, trattamento);
      props.put(VigeWSContentModel.ID_USER, idUser);
      props.put(VigeWSContentModel.NOME_FILE, fileName);
      props.put(VigeWSContentModel.DESC_DOC, desc);
      props.put(VigeWSContentModel.ID_PARTNER, idPartner);
      props.put(VigeWSContentModel.ID_PRATICA, idPratica);
      props.put(VigeWSContentModel.NUMERO_DOC, numeroDoc);
      props.put(VigeWSContentModel.NOTE, note);
      props.put(VigeWSContentModel.DATA_EMISSIONE_DOC, dataEmissione);
      props.put(VigeWSContentModel.DATA_SCADENZA_DOC, dataScadenza);
      props.put(VigeWSContentModel.DATA_CREAZIONE_PRATICA, dataCreazione.toDate());

      // also adds the title for convenience
      props.put(ContentModel.PROP_TITLE, fileName);

      /** The document alf name. */
      String documentAlfName = "d" + idPartner + "_" + idPratica + "_" + idDoc;

      NodeRef documentNF;
      try {

        // it checks if the document exists ... in case it is deleted and recreated
        NodeRef existingFileNodeRef =
            fileFolderService.searchSimple(dropzoneFolderNodeRef, documentAlfName);
        if (existingFileNodeRef != null) {
          logger.info(
              "There is an identical document in dropzone ... it will be replaced with the new"
                  + " one");
          fileFolderService.delete(existingFileNodeRef);
        }

        // The document is created
        logger.info("New document created");
        FileInfo nodeFI =
            fileFolderService.create(
                dropzoneFolderNodeRef, documentAlfName, ContentModel.TYPE_CONTENT);
        ContentWriter writer =
            contentService.getWriter(nodeFI.getNodeRef(), ContentModel.PROP_CONTENT, true);
        writer.setMimetype(fileType);
        writer.guessEncoding();
        InputStream isToUpload = new ByteArrayInputStream(fileToVerify);

        if (isToUpload.available() <= 0) {
          logger.error("Empty file");
          status.setCode(Status.STATUS_BAD_REQUEST);
          status.setMessage("Empty file");
          status.setRedirect(true);
          return model;
        }

        writer.putContent(isToUpload);
        documentNF = nodeFI.getNodeRef();
      } catch (Exception e) {
        logger.error("Unable to create new doc", e);
        throw new WebScriptException(500, "Unable to create node");
      }

      try {
        // Optionally add the look for versions
        if (!nodeService.hasAspect(documentNF, ContentModel.ASPECT_VERSIONABLE)) {
          nodeService.addAspect(
              documentNF, ContentModel.ASPECT_VERSIONABLE, new HashMap<QName, Serializable>());
        }

        // Eventually adds the aspect for the description
        if (!nodeService.hasAspect(documentNF, ContentModel.ASPECT_TITLED)) {
          nodeService.addAspect(
              documentNF, ContentModel.ASPECT_TITLED, new HashMap<QName, Serializable>());
        }

        // adds the aspect for the metadata
        nodeService.addAspect(documentNF, VigeWSContentModel.DOC_ASPECT, props);
      } catch (Exception e) {
        logger.error("Unable to attribute aspects", e);
        throw new WebScriptException(500, "Unable to add aspects");
      }
    }

    status.setCode(Status.STATUS_CREATED);
    return model;
  }

  /**
   * Set file folder service.
   *
   * @param fileFolderService the file folder service
   */
  public void setFileFolderService(FileFolderService fileFolderService) {
    this.fileFolderService = fileFolderService;
  }

  /**
   * Set search service.
   *
   * @param searchService the search service
   */
  public void setSearchService(SearchService searchService) {
    this.searchService = searchService;
  }

  /**
   * Set node service.
   *
   * @param nodeService the node service
   */
  public void setNodeService(NodeService nodeService) {
    this.nodeService = nodeService;
  }

  /**
   * Set content service.
   *
   * @param contentService the content service
   */
  public void setContentService(ContentService contentService) {
    this.contentService = contentService;
  }

  /**
   * Set version service.
   *
   * @param versionService the version service
   */
  public void setVersionService(VersionService versionService) {
    this.versionService = versionService;
  }

  /**
   * Set dropzone path.
   *
   * @param dropzonePath the dropzone path
   */
  public void setDropzonePath(String dropzonePath) {
    this.dropzonePath = dropzonePath;
  }

  /**
   * Set sign service.
   *
   * @param signService the sign service
   */
  public void setSignService(SignService signService) {
    this.signService = signService;
  }

  /**
   * Set applica marca.
   *
   * @param applicaMarca the applica marca
   */
  public void setApplicaMarca(boolean applicaMarca) {
    this.applicaMarca = applicaMarca;
  }
}
