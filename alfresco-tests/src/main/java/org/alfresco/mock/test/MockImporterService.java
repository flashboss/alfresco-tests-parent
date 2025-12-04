package org.alfresco.mock.test;

import com.google.gdata.util.common.base.Charsets;
import com.google.gdata.util.common.io.CharStreams;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.importer.ACPImportPackageHandler;
import org.alfresco.service.cmr.repository.ContentService;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.cmr.view.ImportPackageHandler;
import org.alfresco.service.cmr.view.ImporterBinding;
import org.alfresco.service.cmr.view.ImporterException;
import org.alfresco.service.cmr.view.ImporterProgress;
import org.alfresco.service.cmr.view.ImporterService;
import org.alfresco.service.cmr.view.Location;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mock implementation of MockImporterService for testing purposes.
 *
 * @author vige
 */
public class MockImporterService implements ImporterService, Serializable {

  /** The node service. */
  @Autowired private NodeService nodeService;

  /** The content service. */
  @Autowired private ContentService contentService;

  /**
   * Import view.
   *
   * @param viewReader the view reader
   * @param location the location
   * @param binding the binding
   * @param progress the progress
   */
  @Override
  public void importView(
      Reader viewReader, Location location, ImporterBinding binding, ImporterProgress progress)
      throws ImporterException {
    // TODO Auto-generated method stub

  }

  /**
   * Import view.
   *
   * @param importHandler the import handler
   * @param location the location
   * @param binding the binding
   * @param progress the progress
   */
  @Override
  public void importView(
      ImportPackageHandler importHandler,
      Location location,
      ImporterBinding binding,
      ImporterProgress progress)
      throws ImporterException {
    ((ACPImportPackageHandler) importHandler).startImport();
    NodeRef nodeRef =
        nodeService
            .createNode(
                location.getNodeRef(),
                ContentModel.ASSOC_CONTAINS,
                QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, "result"),
                ContentModel.TYPE_CONTENT)
            .getChildRef();

    InputStream targetStream;
    try {
      targetStream =
          new ByteArrayInputStream(
              CharStreams.toString(importHandler.getDataStream()).getBytes(Charsets.UTF_8));
    } catch (IOException e) {
      throw new ImporterException(e.getMessage(), e);
    }
    contentService.getWriter(nodeRef, ContentModel.PROP_CONTENT, false).putContent(targetStream);
    ((ACPImportPackageHandler) importHandler).endImport();
  }
}
