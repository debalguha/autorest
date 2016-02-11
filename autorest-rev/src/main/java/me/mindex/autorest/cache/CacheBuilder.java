package me.mindex.autorest.cache;

import net.sf.ehcache.Cache;

public interface CacheBuilder {
	void buildCache(Cache cache) throws Exception ;
}
