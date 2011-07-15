package org.eredlab.g4.rif.resource.cache;

import java.util.HashMap;
import java.util.Map;

import org.eredlab.g4.rif.resource.CacheException;

/**
 * MemoryCache
 * 
 * @author HuangYunHui|XiongChun
 * @since 2009-10-13
 */
public class MemoryCache extends  AbstractCache {

	private Map map = new HashMap();
	public void put(Object key, Object pValue) throws CacheException{
		map.put(key, pValue);	
	}

	public Object get(Object key)  throws CacheException{
	   return map.get(key);
	}

	public void remove(Object key)  throws CacheException{
		map.remove(key);
	}

	public void clear() throws CacheException{
       map.clear();   		
	}

}
