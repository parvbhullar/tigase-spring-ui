package org.eredlab.g4.rif.resource.support;

import org.eredlab.g4.rif.resource.Cache;
import org.eredlab.g4.rif.resource.CacheException;
import org.eredlab.g4.rif.resource.Resource;

/**
 * CacheManager
 * 
 * @author HuangYunHui|XiongChun
 * @since 2010-2-5
 */
public class CacheManager {
	private final Cache cache;

	public CacheManager(Cache pCache) {
		this.cache = pCache;
	}

	public void put(Resource pResource) throws CacheException {
		cache.put(pResource.getUri(), pResource);
	}

	public Resource get(String pUri) throws CacheException {
		return (Resource) cache.get(pUri);
	}

}
