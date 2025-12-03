package it.vige.sample;

import java.util.List;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.action.executer.ActionExecuterAbstractBase;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ParameterDefinition;
import org.alfresco.service.cmr.model.FileFolderService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Action executor for creating backup copies of documents.
 * Creates a new document with the specified extension appended to the original name.
 *
 * @author lucastancapiano
 */
public class BackupAction extends ActionExecuterAbstractBase {

	/** The parameter name for the document name. */
	public static String DOCUMENT_NAME = "documentName";

	/** The file folder service. */
	@Autowired
	private FileFolderService fileFolderService;

	/** The file extension for backup files. */
	private String extension;

	/**
	 * Executes the backup action by creating a copy of the document
	 * with the configured extension.
	 *
	 * @param action the action being executed
	 * @param actionedUponNodeRef the node reference of the folder to create the backup in
	 */
	@Override
	public void executeImpl(Action action, NodeRef actionedUponNodeRef) {
		String documentName = (String) action.getParameterValue(DOCUMENT_NAME);
		fileFolderService.create(actionedUponNodeRef, documentName + "." + extension, ContentModel.TYPE_CONTENT);

	}

	/**
	 * Adds parameter definitions for the action.
	 *
	 * @param paramList the list to add parameter definitions to
	 */
	@Override
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets the file extension for backup files.
	 *
	 * @param extension the file extension to use (e.g., "bak")
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

}
