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
implements LockingCache<K, V>, TransactionListener, InitializingBean {

	@Override
 /**
 * Contains.
 *
 * @param key the key
 * @return the boolean
 */
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Get keys.
 *
 * @return the collection
 */
	public Collection<K> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Get.
 *
 * @param key the key
 * @return the v
 */
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 /**
 * Put.
 *
 * @param key the key
 * @param value the value
 */
	public void put(K key, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
 /**
 * Remove.
 *
 * @param key the key
 */
	public void remove(K key) {
		// TODO Auto-generated method stub
		
	}

	@Override
 /** Clear. */
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
 /** After properties set. */
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
 /** Flush. */
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
 /**
 * Before commit.
 *
 * @param readOnly the read only
 */
	public void beforeCommit(boolean readOnly) {
		// TODO Auto-generated method stub
		
	}

	@Override
 /** Before completion. */
	public void beforeCompletion() {
		// TODO Auto-generated method stub
		
	}

	@Override
 /** After commit. */
	public void afterCommit() {
		// TODO Auto-generated method stub
		
	}

	@Override
 /** After rollback. */
	public void afterRollback() {
		// TODO Auto-generated method stub
		
	}

	@Override
 /**
 * Is value locked.
 *
 * @param key the key
 * @return the boolean
 */
	public boolean isValueLocked(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
 /**
 * Lock value.
 *
 * @param key the key
 */
	public void lockValue(K key) {
		// TODO Auto-generated method stub
		
	}

	@Override
 /**
 * Unlock value.
 *
 * @param key the key
 */
	public void unlockValue(K key) {
		// TODO Auto-generated method stub
		
	}

}
