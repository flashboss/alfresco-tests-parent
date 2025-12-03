package org.alfresco.mock.test.script;

import java.io.Serializable;

import org.mozilla.javascript.Scriptable;

/**
 * Mock implementation of Scriptable for testing purposes.
 * Provides a stub scope for JavaScript scripts.
 *
 * @author lucastancapiano
 */
public class MockScope implements Scriptable, Serializable {

	/**
	 * {@inheritDoc}
	 *
	 * @return the class name
	 */
	@Override
	public String getClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the property name
	 * @param start the start scriptable
	 * @return the property value
	 */
	@Override
	public Object get(String name, Scriptable start) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param index the property index
	 * @param start the start scriptable
	 * @return the property value
	 */
	@Override
	public Object get(int index, Scriptable start) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the property name
	 * @param start the start scriptable
	 * @return true if has property
	 */
	@Override
	public boolean has(String name, Scriptable start) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param index the property index
	 * @param start the start scriptable
	 * @return true if has property
	 */
	@Override
	public boolean has(int index, Scriptable start) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the property name
	 * @param start the start scriptable
	 * @param value the value to put
	 */
	@Override
	public void put(String name, Scriptable start, Object value) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param index the property index
	 * @param start the start scriptable
	 * @param value the value to put
	 */
	@Override
	public void put(int index, Scriptable start, Object value) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param name the property name to delete
	 */
	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param index the property index to delete
	 */
	@Override
	public void delete(int index) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the prototype
	 */
	@Override
	public Scriptable getPrototype() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param prototype the prototype to set
	 */
	@Override
	public void setPrototype(Scriptable prototype) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the parent scope
	 */
	@Override
	public Scriptable getParentScope() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param parent the parent scope to set
	 */
	@Override
	public void setParentScope(Scriptable parent) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the property IDs
	 */
	@Override
	public Object[] getIds() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param hint the class hint
	 * @return the default value
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public Object getDefaultValue(Class hint) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param instance the scriptable instance
	 * @return true if has instance
	 */
	@Override
	public boolean hasInstance(Scriptable instance) {
		// TODO Auto-generated method stub
		return false;
	}

}
