package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Collection;

import org.alfresco.repo.cache.LockingCache;
import org.alfresco.repo.transaction.TransactionListener;
import org.springframework.beans.factory.InitializingBean;

/**
 * Mock implementation of MockTransactionalCache for testing purposes.
 *
 * @author vige
 */
public class MockTransactionalCache<K extends Serializable, V extends Object>
implements LockingCache<K, V>, TransactionListener, InitializingBean {

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<K> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(K key, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeCommit(boolean readOnly) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeCompletion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCommit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterRollback() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValueLocked(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void lockValue(K key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unlockValue(K key) {
		// TODO Auto-generated method stub
		
	}

}
