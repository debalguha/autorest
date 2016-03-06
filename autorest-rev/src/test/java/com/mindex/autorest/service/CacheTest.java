package com.mindex.autorest.service;

import java.io.File;

import me.mindex.autorest.cache.CacheFacade;
import me.mindex.autorest.cache.FileSystemCacheBuilder;
import me.mindex.autorest.model.YtmWordListEntity;
import me.mindex.autorest.service.WordListService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
		System.out.println(facade.getAWord());
		System.out.println(facade.getAWord());
		Assert.assertNotNull(facade.getAWord());
		Assert.assertFalse(facade.getAWord().isComplete());
	}

	private void buildCache(Cache cache) throws Exception {
		new FileSystemCacheBuilder(new File("C:\\temp\\dict.txt")).buildCache(cache);
	}
	
	@Test
	public void cacheTestWithWordListService() throws Exception {
		WordListService service = Mockito.spy(new WordListService(new CacheFacade(cache)));
		Mockito.when(service.saveAWord(Mockito.any())).thenReturn(null);
		/*Mockito.doAnswer(new Answer<YtmWordListEntity>() {
			@Override
			public YtmWordListEntity answer(InvocationOnMock invocation) throws Throwable {
				return null;
			}
		}).when(service.saveAWord(Mockito.any(YtmWordListEntity.class)));*/
		YtmWordListEntity word = service.getOneAvailableWord();
		System.out.println(service.getOneAvailableWord());
		service.wordCompleted(word.getYwlId());
		Thread.sleep(10000);
		System.out.println(service.getOneAvailableWord());
	}
}
