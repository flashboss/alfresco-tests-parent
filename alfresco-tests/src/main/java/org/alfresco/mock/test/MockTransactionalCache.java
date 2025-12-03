package org.alfresco.mock.test;

import java.io.Serializable;
import java.util.Collection;

import org.alfresco.repo.cache.SimpleCache;
import org.alfresco.repo.transaction.TransactionListener;
import org.springframework.beans.factory.InitializingBean;

/**
 * Mock implementation of transactional cache for testing purposes.
 * Provides stub methods for cache operations.
 *
 * @param <K> the key type
 * @param <V> the value type
 * @author lucastancapiano
 */
public class MockTransactionalCache<K extends Serializable, V extends Object>
		implements SimpleCache<K, V>, TransactionListener, InitializingBean {

	/**
	 * {@inheritDoc}
	 *
	 * @param key the key to check
	 * @return true if contains key, false otherwise
	 */
	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return the collection of keys
	 */
	@Override
	public Collection<K> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param key the key to get
	 * @return the value
	 */
	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param key the key
	 * @param value the value to put
	 */
	@Override
	public void put(K key, V value) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param key the key to remove
	 */
	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws Exception if initialization fails
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void flush() {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param readOnly whether the transaction is read-only
	 */
	@Override
	public void beforeCommit(boolean readOnly) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforeCompletion() {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterCommit() {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterRollback() {
		// TODO Auto-generated method stub
	}

}
