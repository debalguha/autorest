package com.autorest.wordlist.cache

import scala.collection.parallel.mutable.ParHashMap

/**
  * Created by pc on 3/22/2016.
  */
class WordlistCache extends YtmCache{
  private val wordCache: ParHashMap[Long, YtmWord] = new ParHashMap[Long, YtmWord]()
  override def getWordByKey(id: Long): YtmWord = {
    return wordCache.get(id).get
  }

  override def putWord(word: YtmWord): Unit = {
    wordCache.put(word.id, word);
  }

  override def getAWord(): YtmWord = {
    return wordCache.values.find(p => !p.complete && !p.working).iterator next()
  }
}
