package javazone12;

import com.google.common.cache.{CacheBuilder, CacheLoader}
import java.util.concurrent.TimeUnit
import Scala2GuavaConversions.scalaFunction2GuavaFunction

class MatchCache(val matchscraper: MatchScraper) {

  val cache = CacheBuilder.newBuilder()
    .expireAfterWrite(5, TimeUnit.MINUTES)
    .maximumSize(100)
    .build(CacheLoader.from((loginToken: String) => matchscraper.scrapeAssignedMatches(loginToken)))

  def matchesFor(loginToken:String): List[AssignedMatch] = {
    cache.get(loginToken)
  }
}
