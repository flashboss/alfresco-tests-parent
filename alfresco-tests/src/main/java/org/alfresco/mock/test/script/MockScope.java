package org.alfresco.mock.test.script;

import java.io.Serializable;

import org.mozilla.javascript.Scriptable;

/**
 * Mock implementation of Scope for testing purposes.
 * 
 * @author vige
 */
public class MockScope implements Scriptable, Serializable {

	@Override
	/**
	 * Get class name.
	 *
	 * @return the string
	 */
	public String getClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get.
	 *
	 * @param name the name
	 * @param start the start
	 * @return the object
	 */
	public Object get(String name, Scriptable start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Get.
	 *
	 * @param index the index
	 * @param start the start
	 * @return the object
	 */
	public Object get(int index, Scriptable start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Has.
	 *
	 * @param name the name
	 * @param start the start
	 * @return the boolean
	 */
	public boolean has(String name, Scriptable start) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Has.
	 *
	 * @param index the index
	 * @param start the start
	 * @return the boolean
	 */
	public boolean has(int index, Scriptable start) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * Put.
	 *
	 * @param name the name
	 * @param start the start
	 * @param value the value
	 */
	public void put(String name, Scriptable start, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Put.
	 *
	 * @param index the index
	 * @param start the start
	 * @param value the value
	 */
	public void put(int index, Scriptable start, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Delete.
	 *
	 * @param name the name
	 */
	public void delete(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Delete.
	 *
	 * @param index the index
	 */
	public void delete(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Get prototype.
	 *
	 * @return the scriptable
	 */
	public Scriptable getPrototype() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Set prototype.
	 *
	 * @param prototype the prototype
	 */
	public void setPrototype(Scriptable prototype) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Get parent scope.
	 *
	 * @return the scriptable
	 */
	public Scriptable getParentScope() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Set parent scope.
	 *
	 * @param parent the parent
	 */
	public void setParentScope(Scriptable parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * Get ids.
	 *
	 * @return the object[]
	 */
	public Object[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("rawtypes")
	/**
	 * Get default value.
	 *
	 * @param hint the hint
	 * @return the object
	 */
	public Object getDefaultValue(Class hint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Has instance.
	 *
	 * @param instance the instance
	 * @return the boolean
	 */
	public boolean hasInstance(Scriptable instance) {
		// TODO Auto-generated method stub
		return false;
	}

}
