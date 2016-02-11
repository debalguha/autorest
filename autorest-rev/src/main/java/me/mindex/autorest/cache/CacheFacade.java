package me.mindex.autorest.cache;

import java.util.Date;

import me.mindex.autorest.model.YtmWordListEntity;
import net.sf.ehcache.Cache;
import net.sf.ehcache.search.Query;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheFacade {
	@Autowired
	private Cache cache;

	public CacheFacade() {
	}

	public CacheFacade(Cache cache) {
		this.cache = cache;
	}

	public YtmWordListEntity getAWord() throws Exception {
		Date now = new DateTime().minusMinutes(21).toDate();
		Query query = cache.createQuery().addCriteria(cache.getSearchAttribute("isComplete").eq(Boolean.FALSE).or(cache.getSearchAttribute("isWorking").eq(Boolean.TRUE).and(cache.getSearchAttribute("isWorking").le(now))));
		query.maxResults(1);
		query.includeValues();
		return (YtmWordListEntity) query.execute().all().iterator().next().getValue();
	}
}
