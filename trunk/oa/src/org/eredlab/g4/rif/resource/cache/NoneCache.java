package org.eredlab.g4.rif.resource.cache;

import org.eredlab.g4.rif.resource.CacheException;

/**
 * LRUCache
 * 
 * @author HuangYunHui|XiongChun
 * @since 2009-10-13
 */
public class NoneCache extends AbstractCache {

	public void put(Object key, Object pValue) throws CacheException {
	}

	public Object get(Object key) throws CacheException {
		return null;
	}

	public void remove(Object key) throws CacheException {
	}

	public void clear() throws CacheException {
		
	}

	public boolean exist(Object key) throws CacheException {
		return false;
	}

}
