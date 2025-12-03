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
 * 
 * @author vige
 */
public class BackupAction extends ActionExecuterAbstractBase {

	public static String DOCUMENT_NAME = "documentName";

	@Autowired
	private FileFolderService fileFolderService;

	private String extension;

	@Override
	public void executeImpl(Action action, NodeRef actionedUponNodeRef) {
		String documentName = (String) action.getParameterValue(DOCUMENT_NAME);
		fileFolderService.create(actionedUponNodeRef, documentName + "." + extension, ContentModel.TYPE_CONTENT);

	}

	@Override
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
		// TODO Auto-generated method stub

	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

}
