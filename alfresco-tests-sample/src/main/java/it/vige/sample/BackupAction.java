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
 * Class providing functionality for Alfresco testing.
 * 
 * @author vige
 */
public class BackupAction extends ActionExecuterAbstractBase {

	public static String DOCUMENT_NAME = "documentName";

	/** The file folder service. */
@Autowired
	private FileFolderService fileFolderService;

	/** The extension. */
	private String extension;

	/**
	 * Execute impl.
	 *
	 * @param action the action
	 * @param actionedUponNodeRef the actioned upon node ref
	 */
@Override
	public void executeImpl(Action action, NodeRef actionedUponNodeRef) {

	/** The document name. */
		String documentName = (String) action.getParameterValue(DOCUMENT_NAME);
		fileFolderService.create(actionedUponNodeRef, documentName + "." + extension, ContentModel.TYPE_CONTENT);

	}

	/**
	 * Add parameter definitions.
	 *
	 * @param paramList the param list
	 */
@Override
	protected void addParameterDefinitions(List<ParameterDefinition> paramList) {
		// TODO Auto-generated method stub

	}

	/**
	 * Set extension.
	 *
	 * @param extension the extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

}
