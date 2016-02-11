package me.mindex.autorest.service;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import me.mindex.autorest.model.YtmWordListEntity;
import me.mindex.autorest.persistence.repo.YtmWordListRepo;
import me.mindex.autorest.service.predicate.WordListServicePredicateSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
@Transactional
public class WordListService {
	@Autowired
	private YtmWordListRepo wordListRepo;

	private Lock crawlerLock = new ReentrantLock(true);

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
	public YtmWordListEntity getOneAvailableWord() {
		crawlerLock.lock();
		try {
			Page<YtmWordListEntity> availableElements = wordListRepo.findAll(WordListServicePredicateSpec.buildAvailableWordSpec(), new PageRequest(1, 1));
			YtmWordListEntity anElement = null;
			if (availableElements != null && availableElements.getSize() > 0) {
				anElement = availableElements.iterator().next();
				anElement.setIsWorking(true);
				anElement.setLastDate(new Date());
				saveAWord(anElement);
				return anElement;
			}
			return null;
		} finally {
			crawlerLock.unlock();
		}

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public YtmWordListEntity wordCompleted(Long ywId) {
		YtmWordListEntity aWord = wordListRepo.findOne(ywId);
		aWord.setComplete(true);
		aWord.setWorking(false);
		aWord.setLastDate(new Date());
		return saveAWord(aWord);
	}
	
	@Transactional(readOnly = true)
	public Collection<YtmWordListEntity> findAllWords(){
		return wordListRepo.findAll();
	}
}
