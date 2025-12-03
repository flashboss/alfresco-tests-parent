package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;

import org.alfresco.repo.rendition2.RenditionDefinitionRegistry2;
import org.alfresco.repo.rendition2.RenditionService2;
import org.alfresco.repo.rendition2.TransformDefinition;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;

/**
 * Mock implementation of the MockRenditionService2 class for testing purposes.
 * This class provides a mock implementation that allows unit and integration tests
 * to run without requiring a full Alfresco server instance.
 * 
 * @author Generated
 * @version 7.4.2.1.1
 */
public class MockRenditionService2 implements RenditionService2, Serializable {

	/**


	 * {@inheritDoc}


	 */


	@Override
	public RenditionDefinitionRegistry2 getRenditionDefinitionRegistry2() {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void render(NodeRef sourceNodeRef, String renditionName) {
		// TODO Auto-generated method stub
		
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public List<ChildAssociationRef> getRenditions(NodeRef sourceNodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public ChildAssociationRef getRenditionByName(NodeRef sourceNodeRef, String renditionName) {
		// TODO Auto-generated method stub
		return null;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void transform(NodeRef sourceNodeRef, TransformDefinition transformDefinition) {
		// TODO Auto-generated method stub
		
	}

	/**


	 * {@inheritDoc}


	 */


	@Override
	public void clearRenditionContentDataInTransaction(NodeRef renditionNode) {
		// TODO Auto-generated method stub
		
	}

}
