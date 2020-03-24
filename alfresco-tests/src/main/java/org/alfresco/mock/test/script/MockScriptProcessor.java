package org.alfresco.mock.test.script;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.alfresco.repo.jscript.RhinoScriptProcessor;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeGlobal;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.NativeWith;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.continuations.Continuation;

public class MockScriptProcessor extends RhinoScriptProcessor implements Serializable {

	@Override
	protected Scriptable initScope(Context cx, boolean secure, boolean sealed) {
		Scriptable scope = super.initScope(cx, secure, sealed);
		if (secure) {

			convertConstant(NativeGlobal.class.getName(), "FTAG", "TypeError", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "isNaN", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "isFinite", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "ConversionError", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "EvalError", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "encodeURI", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "Boolean", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "Call", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "unescape", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "URIError", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "decodeURI", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "SyntaxError", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "encodeURIComponent", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "decodeURIComponent", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "RangeError", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "ReferenceError", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "InternalError", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "escape", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "JavaException", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "SyntaxError", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "parseInt", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "parseFloat", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "eval", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "uneval", null, scope);
			convertConstant(NativeGlobal.class.getName(), "FTAG", "isXMLName", null, scope);
			convertConstant("org.mozilla.javascript.NativeDate", "DATE_TAG", "Date",
					new String[] { "now", "UTC", "parse" }, scope);
			convertConstant("org.mozilla.javascript.NativeString", "STRING_TAG", "String",
					new String[] { "fromCharCode" }, scope);
			convertConstant(NativeArray.class.getName(), "ARRAY_TAG", "Array", null, scope);
			convertConstant("org.mozilla.javascript.NativeScript", "SCRIPT_TAG", "Script", null, scope);
			convertConstant(NativeWith.class.getName(), "FTAG", "With", null, scope);
			convertConstant(BaseFunction.class.getName(), "FUNCTION_TAG", "Function", null, scope);
			convertConstant("org.mozilla.javascript.NativeNumber", "NUMBER_TAG", "Number", null, scope);
			convertConstant("org.mozilla.javascript.NativeError", "ERROR_TAG", "Error", null, scope);
			convertConstant(NativeObject.class.getName(), "OBJECT_TAG", "Object", null, scope);
			convertConstant(Continuation.class.getName(), "FTAG", "Continuation", null, scope);
			convertConstant(ImporterTopLevel.class.getName(), "IMPORTER_TAG", "global", null, scope);
		}
		return scope;
	}

	private void convertConstant(String contantClassName, String constant, String propertyName, String[] subProperties,
			Scriptable scope) {
		try {
			String value = new String();
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			Class<?> contantClass = Class.forName(contantClassName);
			Field field = contantClass.getDeclaredField(constant);
			field.setAccessible(true);
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			field.set(null, value);

			Object object = scope.get(propertyName, null);
			if (object instanceof IdFunctionObject) {
				IdFunctionObject function = (IdFunctionObject) object;
				Field tag = function.getClass().getDeclaredField("tag");
				tag.setAccessible(true);
				tag.set(function, value);
				if (subProperties != null)
					for (String subProperty : subProperties) {
						Object subObject = function.get(subProperty, null);
						if (subObject instanceof IdFunctionObject) {
							IdFunctionObject subFunction = (IdFunctionObject) subObject;
							Field subTag = subFunction.getClass().getDeclaredField("tag");
							subTag.setAccessible(true);
							subTag.set(subFunction, value);
						}
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
