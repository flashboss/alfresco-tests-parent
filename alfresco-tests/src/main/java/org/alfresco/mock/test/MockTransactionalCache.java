package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Collection;

import org.alfresco.repo.cache.LockingCache;
import org.alfresco.repo.transaction.TransactionListener;
import org.springframework.beans.factory.InitializingBean;

/**
 * Mock implementation of TransactionalCache for testing purposes.
 * 
 * @author vige
 */
public class MockTransactionalCache<K extends Serializable, V extends Object>
implements LockingCache<K, V>, TransactionListener, InitializingBean {	/**
	 * Contains.
	 *
	 * @param key the key
	 * @return the boolean
	 */
	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Get keys.
	 *
	 * @return the collection
	 */
	@Override
	public Collection<K> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Get.
	 *
	 * @param key the key
	 * @return the v
	 */
	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Put.
	 *
	 * @param key the key
	 * @param value the value
	 */
	@Override
	public void put(K key, V value) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Remove.
	 *
	 * @param key the key
	 */
	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Clear.
	 *
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * After properties set.
	 *
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Flush.
	 *
	 */
	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Before commit.
	 *
	 * @param readOnly the read only
	 */
	@Override
	public void beforeCommit(boolean readOnly) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Before completion.
	 *
	 */
	@Override
	public void beforeCompletion() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * After commit.
	 *
	 */
	@Override
	public void afterCommit() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * After rollback.
	 *
	 */
	@Override
	public void afterRollback() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Is value locked.
	 *
	 * @param key the key
	 * @return the boolean
	 */
	@Override
	public boolean isValueLocked(K key) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Lock value.
	 *
	 * @param key the key
	 */
	@Override
	public void lockValue(K key) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Unlock value.
	 *
	 * @param key the key
	 */
	@Override
	public void unlockValue(K key) {
		// TODO Auto-generated method stub
		
	}

}
