package me.mindex.autorest.service;

import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import me.mindex.autorest.cache.CacheFacade;
import me.mindex.autorest.model.YtmWordListEntity;
import me.mindex.autorest.persistence.repo.YtmWordListRepo;
import me.mindex.autorest.service.predicate.PredicateSpecFactory;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service("wordListService")
@Qualifier("wordListService")
@Transactional
public class WordListService {
	@Autowired
	private YtmWordListRepo wordListRepo;
	@Autowired
	private CacheFacade facade;

	private static final Logger LOG = LoggerFactory.getLogger(WordListService.class);

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public YtmWordListEntity saveAWord(YtmWordListEntity ytmWordList) {
		return wordListRepo.save(ytmWordList);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public Collection<YtmWordListEntity> saveWordList(Collection<YtmWordListEntity> wordList) {
		Collection<YtmWordListEntity> retList = Lists.newArrayListWithCapacity(wordList.size());
		for (YtmWordListEntity aWord : wordList)
			retList.add(saveAWord(aWord));
		return retList;
	}

	@Transactional(readOnly = true)
	public Collection<YtmWordListEntity> getWordList() {
		return wordListRepo.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public YtmWordListEntity getOneAvailableWord() throws Exception {
		return getOneAvailableWordFromCache();

	}

	public YtmWordListEntity getOneAvailableWordFromCache() throws Exception {
		YtmWordListEntity aWord = facade.getAWord();
		aWord.setWorking(true);
		aWord.setLastDate(new Date());
		facade.putAWord(aWord);
		return aWord;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public YtmWordListEntity getOneAvailableWordFromDB() {
		Page<YtmWordListEntity> availableElements = wordListRepo.findAll(PredicateSpecFactory.buildAvailableWordSpec(), new PageRequest(1, 1));
		YtmWordListEntity anElement = null;
		if (availableElements != null && availableElements.getSize() > 0) {
			anElement = availableElements.iterator().next();
			anElement.setIsWorking(true);
			anElement.setLastDate(new Date());
			saveAWord(anElement);
			return anElement;
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public YtmWordListEntity wordCompleted(Long ywId) {
		YtmWordListEntity aWord = wordListRepo.findOne(ywId);
		aWord.setComplete(true);
		aWord.setWorking(false);
		aWord.setLastDate(new Date());
		facade.putAWord(aWord);
		return saveAWord(aWord);
	}

	@Transactional(readOnly = true)
	public Collection<YtmWordListEntity> findAllWords() {
		return wordListRepo.findAll();
	}

	@Transactional(readOnly = true)
	public void populateCachePageByPage(Cache cache) {
		long total = wordListRepo.count();
		int pageSize = 10000;
		long loopCount = total % pageSize == 0 ? total / pageSize : (new Double(Math.floor(total / pageSize)).longValue() + 1);
		for (int i = 0; i < loopCount; i++) {
			cache.putAll(wordListRepo.findAll(new PageRequest(i + 1, pageSize)).getContent().stream().map(new Function<YtmWordListEntity, Element>() {
				@Override
				public Element apply(YtmWordListEntity entity) {
					return new Element(entity.getYwlId(), entity);
				}
			}).collect(Collectors.toList()));
			LOG.info("Cached " + pageSize + " records.");
		}
	}
	
	public void refreshCache() throws Exception{
		facade.reloadCache();
	}
}
