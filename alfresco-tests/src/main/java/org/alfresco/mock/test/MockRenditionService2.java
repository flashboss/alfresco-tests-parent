package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.List;

import org.alfresco.repo.rendition2.RenditionDefinitionRegistry2;
import org.alfresco.repo.rendition2.RenditionService2;
import org.alfresco.repo.rendition2.TransformDefinition;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;

public class MockRenditionService2 implements RenditionService2, Serializable {

	@Override
	public RenditionDefinitionRegistry2 getRenditionDefinitionRegistry2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void render(NodeRef sourceNodeRef, String renditionName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ChildAssociationRef> getRenditions(NodeRef sourceNodeRef) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildAssociationRef getRenditionByName(NodeRef sourceNodeRef, String renditionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void transform(NodeRef sourceNodeRef, TransformDefinition transformDefinition) {
		// TODO Auto-generated method stub
		
	}

}
