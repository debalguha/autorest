package com.autorest.wordlist.cache

import java.util.Date


/**
  * Created by pc on 3/21/2016.
  */
trait YtmCache {
  def getAWord(): YtmWord
  def getWordByKey(id: Long): YtmWord
  def putWord(word: YtmWord): Unit
}

case class YtmWord(word: String, creationDate: Date, id: Long, complete: Boolean, working: Boolean){}
