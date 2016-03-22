package com.autorest.wordlist.cache

import java.util.Date

import org.apache.commons.lang.math.RandomUtils

import scala.io.Source

/**
  * Created by pc on 3/22/2016.
  */
class FileSystemCacheBuilder(path: String) extends WordListCacheBuilder{
  private var pathToFile: String = path
  override def buildCache(): YtmCache = {
    val cache: YtmCache = new WordlistCache()
    Source.fromFile(pathToFile).getLines()
      .toStream.map((str) => new YtmWord(str, new Date(), RandomUtils.nextLong(), false, false)).toList
          .foreach((word) => cache.putWord(word))
    return cache;
  }
}
