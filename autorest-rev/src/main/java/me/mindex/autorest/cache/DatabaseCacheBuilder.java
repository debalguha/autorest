package me.mindex.autorest.cache;

import me.mindex.autorest.service.WordListService;
import net.sf.ehcache.Cache;

public class DatabaseCacheBuilder implements CacheBuilder{
	private WordListService service;

	@Override
	public void buildCache(Cache cache) throws Exception {
		service.populateCachePageByPage(cache);
	}

	public void setService(WordListService service) {
		this.service = service;
	}

}
