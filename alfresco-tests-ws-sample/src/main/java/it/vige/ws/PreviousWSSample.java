package it.vige.ws;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alfresco.model.ContentModel;
import org.alfresco.service.ServiceRegistry;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.repository.StoreRef;
import org.alfresco.service.cmr.search.ResultSet;
import org.alfresco.service.cmr.search.ResultSetRow;
import org.alfresco.service.cmr.search.SearchService;
import org.alfresco.service.cmr.security.AuthenticationService;
import org.alfresco.service.namespace.QName;
import org.joda.time.DateTime;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.servlet.FormData;

import net.sf.acegisecurity.providers.ProviderNotFoundException;

/**
 * WebScript for handling previous WS Sample operations.
 * This class manages the update of WS Sample folder aspects and metadata.
 *
 * @author lucastancapiano
 */
public class PreviousWSSample extends DeclarativeWebScript {

	/** The service registry. */
	private ServiceRegistry serviceRegistry;

	/** The conservation folder template path. */
	private String conservazioneFolderTemplate;

	/** The repository folder template for WS Sample. */
	private String repositoryFolderTemplateWSSample;

	/** The documents folder template for WS Sample. */
	private String documentsWSSampleFolderTemplate;

	/** The store reference for workspace. */
	private StoreRef storeRef = new StoreRef(StoreRef.PROTOCOL_WORKSPACE, "SpacesStore");

	/** The date format for parsing dates. */
	private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Executes the webscript implementation.
	 *
	 * @param req the webscript request
	 * @param status the status object for setting response codes
	 * @param cache the cache object
	 * @return a map containing the model data
	 */
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {

		// -- 1 -- VARIABLE INITIALIZATION
		Map<String, Object> model = new HashMap<String, Object>();
		NodeRef rootNode = null;
		Date dateModify = null;
		Date dateWSSampleStart = null;
		Date dateWSSampleEnd = null;
		Boolean automatico = false;
		Boolean aggiorna = false;
		List<NodeRef> folderWSSamples = new ArrayList<NodeRef>();
		try {

			// -- 2 -- AUTENTICATION
			AuthenticationService authenticationService = serviceRegistry.getAuthenticationService();
			String currentUser = authenticationService.getCurrentUserName();

			if (currentUser != null) {
				// -- 3 -- METADATA EXTRACTION FROM THE FORM
				FormData form = (FormData) req.parseContent();
				FormData.FormField[] fields = form.getFields();
				NodeService nodeService = serviceRegistry.getNodeService();
				rootNode = getConservazioneFolder();
				if (rootNode == null) {
					redirectStatus(status, "Folder 'cm:repository' in the site 'Sample Banck' doesn't exit");
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				for (FormData.FormField field : fields) {
					switch (field.getName()) {
					case "date_modified":
						dateModify = dateFormat.parse(field.getValue());
						break;
					case "date_ws_start":
						dateWSSampleStart = dateFormat.parse(field.getValue());
						break;
					case "date_ws_end":
						dateWSSampleEnd = dateFormat.parse(field.getValue());
						break;
					case "codicews":
						String codiceWSSample = field.getValue();
						folderWSSamples.add(nodeService.getChildByName(rootNode, ContentModel.ASSOC_CONTAINS, codiceWSSample));
						break;
					case "automatico":
						automatico = field.getValue().equals("on") ? true : false;
						break;
					case "aggiorna":
						aggiorna = true;
						status.setRedirect(true);
						status.setCode(Status.STATUS_MOVED_PERMANENTLY);
						status.setLocation("previouswssample?date_ws_start=" + dateFormat.format(dateWSSampleStart)
								+ "&date_ws_end=" + dateFormat.format(dateWSSampleEnd) + "&date_modified="
								+ dateFormat.format(dateModify));
						break;
					}
				}

				if (!aggiorna) {
					if (folderWSSamples.isEmpty())
						folderWSSamples = getPosizionidaConservareAsNodeRef(new DateTime(dateWSSampleStart.getTime()),
								new DateTime(dateWSSampleEnd.getTime()));

					// -- 4 -- UPDATE OF THE MODIFICATION DATE ON THE WS Sample FOLDER
					for (NodeRef folderWSSample : folderWSSamples) {
						Date dataAggiornamento = dateModify;
						if (automatico)
							dataAggiornamento = getLastDocumentDateForWSSample(folderWSSample);
						aggiornaAspettoWSSample(folderWSSample, dataAggiornamento);
					}
				}
			}
		} catch (ProviderNotFoundException ex1) {
			model.put("errore", "User not authenticated");
		} catch (Exception ex2) {
			model.put("errore", "ERROR in updating the folder");
		}

		model.put("node", rootNode);

		// -- 7 -- END WEBSCRIPT

		return model;
	}

	/**
	 * Sets redirect status with error message.
	 *
	 * @param status the status object
	 * @param message the error message
	 */
	private void redirectStatus(Status status, String message) {
		status.setCode(500);
		status.setMessage(message);
		status.setRedirect(true);
	}

	/**
	 * Gets the positions to conserve as node references.
	 *
	 * @param dateFrom the start date
	 * @param dateTo the end date
	 * @return list of node references for folders to conserve
	 */
	private List<NodeRef> getPosizionidaConservareAsNodeRef(DateTime dateFrom, DateTime dateTo) {
		ResultSet folderRs = serviceRegistry.getSearchService().query(this.storeRef,
				SearchService.LANGUAGE_FTS_ALFRESCO,
				repositoryFolderTemplateWSSample.replace("{wsSampleFrom}", fmt.format(dateFrom.toDate())).replace("{wsSampleTo}",
						fmt.format(dateTo.toDate())));
		if (folderRs.length() < 1) {
			return null;
		}
		List<NodeRef> praticheFolderList = new ArrayList<>(folderRs.length());
		for (ResultSetRow pratiche : folderRs) {
			praticheFolderList.add(pratiche.getNodeRef());
		}
		return praticheFolderList;
	}

	/**
	 * Gets the conservation folder node reference.
	 *
	 * @return the conservation folder node reference, or null if not found
	 */
	private NodeRef getConservazioneFolder() {
		ResultSet folderRs = serviceRegistry.getSearchService().query(this.storeRef,
				SearchService.LANGUAGE_FTS_ALFRESCO, conservazioneFolderTemplate);
		if (folderRs.length() < 1) {
			return null;
		}
		return folderRs.getNodeRef(0);
	}

	/**
	 * Gets the last document modification date for a WS Sample folder.
	 *
	 * @param folderWSSample the WS Sample folder node reference
	 * @return the last modification date, or null if no documents found
	 */
	private Date getLastDocumentDateForWSSample(NodeRef folderWSSample) {
		NodeService nodeService = serviceRegistry.getNodeService();
		SearchService searchService = serviceRegistry.getSearchService();
		String folderWSSampleName = (String) nodeService.getProperty(folderWSSample, ContentModel.PROP_NAME);
		ResultSet folderRs = searchService.query(this.storeRef, SearchService.LANGUAGE_FTS_ALFRESCO,
				documentsWSSampleFolderTemplate.replace("{nomeWSSampleFolder}", folderWSSampleName));
		if (folderRs.length() < 1) {
			return null;
		}
		Date date = null;
		for (ResultSetRow documento : folderRs) {
			Date dDate = (Date) nodeService.getProperty(documento.getNodeRef(), ContentModel.PROP_MODIFIED);
			if (date == null || date.compareTo(dDate) < 0)
				date = dDate;
		}
		return date;
	}

	/**
	 * Updates the WS Sample aspect on a folder.
	 *
	 * @param folderWSSample the folder node reference
	 * @param dateModify the modification date to set
	 */
	private void aggiornaAspettoWSSample(NodeRef folderWSSample, Date dateModify) {
		NodeService nodeService = serviceRegistry.getNodeService();
		if (!nodeService.hasAspect(folderWSSample, WSSampleModel.ASPECT_WSSAMPLEFOLDER)) {
			Map<QName, Serializable> aspectProperties = new HashMap<QName, Serializable>();
			aspectProperties.put(WSSampleModel.PROP_UPDATE_PROPERTY, dateModify);
			nodeService.addAspect(folderWSSample, WSSampleModel.ASPECT_WSSAMPLEFOLDER, aspectProperties);
		} else
			nodeService.setProperty(folderWSSample, WSSampleModel.PROP_UPDATE_PROPERTY, dateModify);
	}

	/**
	 * Sets the service registry.
	 *
	 * @param serviceRegistry the service registry to set
	 */
	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	/**
	 * Sets the conservation folder template.
	 *
	 * @param conservazioneFolderTemplate the template path to set
	 */
	public void setConservazioneFolderTemplate(String conservazioneFolderTemplate) {
		this.conservazioneFolderTemplate = conservazioneFolderTemplate;
	}

	/**
	 * Sets the repository folder template for WS Sample.
	 *
	 * @param repositoryFolderTemplateWSSample the template to set
	 */
	public void setRepositoryFolderTemplateWSSample(String repositoryFolderTemplateWSSample) {
		this.repositoryFolderTemplateWSSample = repositoryFolderTemplateWSSample;
	}

	/**
	 * Sets the documents folder template for WS Sample.
	 *
	 * @param documentsWSSampleFolderTemplate the template to set
	 */
	public void setDocumentsWSSampleFolderTemplate(String documentsWSSampleFolderTemplate) {
		this.documentsWSSampleFolderTemplate = documentsWSSampleFolderTemplate;
	}

}
