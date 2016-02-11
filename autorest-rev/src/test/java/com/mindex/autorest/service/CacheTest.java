package com.mindex.autorest.service;

import java.io.File;

import me.mindex.autorest.cache.CacheFacade;
import me.mindex.autorest.cache.FileSystemCacheBuilder;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CacheTest {
	static Cache cache;
	@Before
	public void setup() throws Exception{
	      cache = CacheManager.newInstance().getCache("wordListCache");
	      buildCache(cache);
	      System.out.println("Loading done!!");
	}
	@Test
	public void shouldBeAbleToGetARecordFromCache() throws Exception {
		CacheFacade facade = new CacheFacade(cache);
		Assert.assertNotNull(facade.getAWord());
		Assert.assertFalse(facade.getAWord().isComplete());
	}

	private void buildCache(Cache cache) throws Exception {
		new FileSystemCacheBuilder(new File("C:\\DebWork\\AutoMindexerREST\\dict.txt")).buildCache(cache);
	}
}
