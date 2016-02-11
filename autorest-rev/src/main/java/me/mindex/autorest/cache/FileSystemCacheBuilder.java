package me.mindex.autorest.cache;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import me.mindex.autorest.model.YtmWordListEntity;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.io.FileUtils;

public class FileSystemCacheBuilder implements CacheBuilder{
	private File dictFile;
	//private static final Logger logger = Logger.getLogger(FileSystemCacheBuilder.class.getName());
	public FileSystemCacheBuilder(File dictFile) {
		super();
		this.dictFile = dictFile;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void buildCache(final Cache cache) throws Exception {
		Random random = new Random();
		/*((List<String>) FileUtils.readLines(dictFile)).stream().parallel().forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				YtmWordListEntity ytmWordListEntity = new YtmWordListEntity(t.trim(), false, false);
				ytmWordListEntity.setYwlId(random.nextInt());
				cache.put(ytmWordListEntity.getYwlId(), ytmWordListEntity);
				System.out.println("Added to cache");
			}
		});*/
		System.out.println("Started Building Cache");
		Collection<Element> elements = ((List<String>) FileUtils.readLines(dictFile)).stream().parallel().map(new Function<String, Element>() {
			@Override
			public Element apply(String t) {
				if (t.length() > 45)
					System.out.println("Long word:: " + t);
				long id = random.nextLong();
				return new Element(id, new YtmWordListEntity(id, t.trim(), false, false, new Date()));
			}
		}).collect(Collectors.toSet());
		System.out.println("Created map for Cache");
		cache.putAll(elements);
		System.out.println("Finished Building Cache");
	}

}
