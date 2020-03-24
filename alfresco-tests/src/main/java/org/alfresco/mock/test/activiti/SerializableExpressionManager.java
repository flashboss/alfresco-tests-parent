package org.alfresco.mock.test.activiti;

import java.io.Serializable;
import java.util.Map;

import org.activiti.engine.delegate.VariableScope;
import org.activiti.engine.impl.el.ActivitiElContext;
import org.activiti.engine.impl.el.ExpressionManager;
import org.activiti.engine.impl.javax.el.ELResolver;

public class SerializableExpressionManager extends ExpressionManager implements Serializable {

	public SerializableExpressionManager() {
		super(null);
	}

	public SerializableExpressionManager(Map<Object, Object> beans) {
		super(beans);
	}

	@Override
	protected ActivitiElContext createElContext(VariableScope variableScope) {
		ELResolver elResolver = createElResolver(variableScope);
		return new SerializableActivitiElContext(elResolver);
	}
}
