package org.alfresco.mock.test.activiti;

import java.io.Serializable;

import org.activiti.engine.impl.el.ActivitiElContext;
import org.activiti.engine.impl.javax.el.ELResolver;

public class SerializableActivitiElContext extends ActivitiElContext implements Serializable {

	public SerializableActivitiElContext(ELResolver elResolver) {
		super(elResolver);
	}

}
