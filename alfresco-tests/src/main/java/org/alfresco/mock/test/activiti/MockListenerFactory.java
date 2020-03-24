package org.alfresco.mock.test.activiti;

import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.model.FieldExtension;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.bpmn.parser.FieldDeclaration;
import org.activiti.engine.impl.bpmn.parser.factory.DefaultListenerFactory;
import org.activiti.engine.impl.el.FixedValue;
import org.apache.commons.lang.StringUtils;

public class MockListenerFactory extends DefaultListenerFactory {

	@Override
	public List<FieldDeclaration> createFieldDeclarations(List<FieldExtension> fieldList) {
		List<FieldDeclaration> fieldDeclarations = new ArrayList<FieldDeclaration>();

		for (FieldExtension fieldExtension : fieldList) {
			FieldDeclaration fieldDeclaration = null;
			if (StringUtils.isNotEmpty(fieldExtension.getExpression())) {
				fieldDeclaration = new SerializableFieldDeclaration(fieldExtension.getFieldName(),
						Expression.class.getName(), expressionManager.createExpression(fieldExtension.getExpression()));
			} else {
				fieldDeclaration = new SerializableFieldDeclaration(fieldExtension.getFieldName(),
						Expression.class.getName(), new FixedValue(fieldExtension.getStringValue()));
			}

			fieldDeclarations.add(fieldDeclaration);
		}
		return fieldDeclarations;
	}

}
