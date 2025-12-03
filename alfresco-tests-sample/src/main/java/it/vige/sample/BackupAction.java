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
 * Action that creates a backup copy of a document.
 * Creates a new document with the specified name and extension.
 * This action can be configured via Spring to specify the backup file extension.
 * 
 * @author vige
 */
public class BackupAction extends ActionExecuterAbstractBase {

	/**
	 * Parameter name constant for the document name.
	 * Used to retrieve the document name from action parameters.
	 */
	public static String DOCUMENT_NAME = "documentName";

	/**
	 * The file folder service used to create backup documents.
	 * Injected by Spring autowiring.
	 */
	@Autowired
	private FileFolderService fileFolderService;

	/**
	 * The file extension to append to backup documents.
	 * Configured via Spring bean properties.
	 */
	private String extension;

	/**
	 * Executes the backup action on the specified node.
	 * Creates a new content node with the document name and configured extension.
	 * 
	 * {@inheritDoc}
	 * 
	 * @param action the action instance containing parameters including the document name
	 * @param actionedUponNodeRef the node reference of the parent folder where the backup will be created
	 */
	@Override
	public void executeImpl(Action action, NodeRef actionedUponNodeRef) {
		String documentName = (String) action.getParameterValue(DOCUMENT_NAME);
		fileFolderService.create(actionedUponNodeRef, documentName + "." + extension, ContentModel.TYPE_CONTENT);

	}

	/**
	 * Adds parameter definitions for this action.
	 * Currently no parameters are defined.
	 * 
	 * {@inheritDoc}
	 * 
	 * @param paramList the list to add parameter definitions to
	 */
	@Override
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
		// TODO Auto-generated method stub

	}

	/**
	 * Sets the file extension for backup documents.
	 * 
	 * @param extension the file extension to use (e.g., "bak")
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

}
