package org.alfresco.mock.test.activiti.type;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.impl.variable.DeserializedObject;
import org.activiti.engine.impl.variable.SerializableType;
import org.activiti.engine.impl.variable.ValueFields;

public class SerializedSerializableType extends SerializableType implements Serializable {

	@Override
	public Object getValue(ValueFields valueFields) {
		Object cachedObject = valueFields.getCachedValue();
		if (cachedObject != null) {
			return cachedObject;
		}

		byte[] bytes = null;
		if (valueFields.getByteArrayValueId() != null) {
			bytes = valueFields.getByteArrayValue().getBytes();
		}
		if (bytes != null) {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			try {
				ObjectInputStream ois = createObjectInputStream(bais);
				Object deserializedObject = ois.readObject();
				valueFields.setCachedValue(deserializedObject);

				if (valueFields instanceof VariableInstanceEntity) {
					// we need to register the deserialized object for dirty checking,
					// so that it can be serialized again if it was changed.
					Context.getCommandContext().getDbSqlSession().addDeserializedObject(new DeserializedObject(this,
							deserializedObject, bytes, (VariableInstanceEntity) valueFields));
				}

				return deserializedObject;
			} catch (Exception e) {
			} finally {
				IoUtil.closeSilently(bais);
			}
		}
		return null; // byte array is null
	}
}
