package javazone12;
import scala.concurrent.ops.spawn
class AsyncCacheLoader(matchCache: MatchCache) {

  def loadCacheFor(loginToken:String) {
    spawn{
      matchCache.matchesFor(loginToken)
    }
  }

}
