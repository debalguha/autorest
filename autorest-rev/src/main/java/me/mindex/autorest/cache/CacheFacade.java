package me.mindex.autorest.cache;

import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import me.mindex.autorest.model.YtmWordListEntity;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Query;

import org.joda.time.DateTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheFacade implements InitializingBean {
	@Autowired
	private Cache cache;
	@Autowired
	private CacheBuilder cacheBuilder;

	private ReadWriteLock cacheLock = new ReentrantReadWriteLock(true);

	public CacheFacade() {
	}

	public CacheFacade(Cache cache) {
		this.cache = cache;
	}

	public YtmWordListEntity getAWord() throws Exception {
		cacheLock.readLock().lock();
		try {
			Date now = new DateTime().minusMinutes(21).toDate();
			Query query = cache.createQuery().addCriteria(cache.getSearchAttribute("isComplete").eq(Boolean.FALSE).or(cache.getSearchAttribute("isWorking").eq(Boolean.TRUE).and(cache.getSearchAttribute("lastDate").le(now))));
			query.maxResults(1);
			query.includeValues();
			YtmWordListEntity entity = (YtmWordListEntity) query.execute().all().iterator().next().getValue();
			cache.remove(entity.getYwlId());
			return entity;
		} finally {
			cacheLock.readLock().unlock();
		}
	}

	public void putAWord(YtmWordListEntity entity) {
		cacheLock.readLock().lock();
		try {
			cache.put(new Element(entity.getYwlId(), entity));
		} finally {
			cacheLock.readLock().unlock();
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		loadCache();
	}

	public void reloadCache() throws Exception {
		new Thread(new Runnable(){
			@Override
			public void run() {
				cacheLock.writeLock().lock();
				try {
					cache.removeAll();
					reloadCache();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					cacheLock.writeLock().unlock();
				}
			}
		}).start();
	}

	private void loadCache() throws Exception {
		cacheBuilder.buildCache(cache);
	}
}
