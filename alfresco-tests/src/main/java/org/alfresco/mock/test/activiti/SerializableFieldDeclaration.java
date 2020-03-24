package org.alfresco.mock.test.activiti;

import java.io.Serializable;

import org.activiti.engine.impl.bpmn.parser.FieldDeclaration;

public class SerializableFieldDeclaration extends FieldDeclaration implements Serializable {

	public SerializableFieldDeclaration(String name, String type, Object value) {
		super(name, type, value);
	}

}
