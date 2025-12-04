package org.alfresco.mock.test.script;

import java.io.Serializable;

import org.mozilla.javascript.Scriptable;

/**
 * Mock implementation of Scope for testing purposes.
 * 
 * @author vige
 */
public class MockScope implements Scriptable, Serializable {

	/**
	 * Get class name.
	 *
	 * @return the string
	 */
@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get.
	 *
	 * @param name the name
	 * @param start the start
	 * @return the object
	 */
@Override
	public Object get(String name, Scriptable start) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get.
	 *
	 * @param index the index
	 * @param start the start
	 * @return the object
	 */
@Override
	public Object get(int index, Scriptable start) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Has.
	 *
	 * @param name the name
	 * @param start the start
	 * @return the boolean
	 */
@Override
	public boolean has(String name, Scriptable start) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Has.
	 *
	 * @param index the index
	 * @param start the start
	 * @return the boolean
	 */
@Override
	public boolean has(int index, Scriptable start) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Put.
	 *
	 * @param name the name
	 * @param start the start
	 * @param value the value
	 */
@Override
	public void put(String name, Scriptable start, Object value) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Put.
	 *
	 * @param index the index
	 * @param start the start
	 * @param value the value
	 */
@Override
	public void put(int index, Scriptable start, Object value) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Delete.
	 *
	 * @param name the name
	 */
@Override
	public void delete(String name) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Delete.
	 *
	 * @param index the index
	 */
@Override
	public void delete(int index) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Get prototype.
	 *
	 * @return the scriptable
	 */
@Override
	public Scriptable getPrototype() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Set prototype.
	 *
	 * @param prototype the prototype
	 */
@Override
	public void setPrototype(Scriptable prototype) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Get parent scope.
	 *
	 * @return the scriptable
	 */
@Override
	public Scriptable getParentScope() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Set parent scope.
	 *
	 * @param parent the parent
	 */
@Override
	public void setParentScope(Scriptable parent) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Get ids.
	 *
	 * @return the object[]
	 */
@Override
	public Object[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get default value.
	 *
	 * @param hint the hint
	 * @return the object
	 */
	@Override
@SuppressWarnings("rawtypes")
	public Object getDefaultValue(Class hint) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Has instance.
	 *
	 * @param instance the instance
	 * @return the boolean
	 */
@Override
	public boolean hasInstance(Scriptable instance) {
		// TODO Auto-generated method stub
		return false;
	}

}
