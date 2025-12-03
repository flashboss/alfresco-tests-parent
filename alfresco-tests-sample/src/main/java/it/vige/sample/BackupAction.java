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
* Mock implementation of the BackupAction class for testing purposes.
* This class provides a mock implementation that allows unit and integration tests
* to run without requiring a full Alfresco server instance.
*
* @author Generated
* @version 7.4.2.1.1
 */
public class BackupAction extends ActionExecuterAbstractBase {

	public static String DOCUMENT_NAME = "documentName";

/**
* The file folder service.
 */
	@Autowired
	private FileFolderService fileFolderService;

/**
* The extension.
 */
	private String extension;

/**
* {@inheritDoc}
* @param action the action
* @param actionedUponNodeRef the actionedUponNodeRef
 */
	@Override
	public void executeImpl(Action action, NodeRef actionedUponNodeRef) {
		String documentName = (String) action.getParameterValue(DOCUMENT_NAME);
		fileFolderService.create(actionedUponNodeRef, documentName + "." + extension, ContentModel.TYPE_CONTENT);

	}

/**
* Performs add parameter definitions.
* @param paramList the paramList
 */
	@Override
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
		// TODO Auto-generated method stub

	}

/**
* Sets the extension.
* @param extension the extension
 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

}
