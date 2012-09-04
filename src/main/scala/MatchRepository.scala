package javazone12

import org.joda.time.LocalDateTime

class MatchRepository(matchCache: MatchCache) {

  def upcomingMatchesOnly(loginToken: String) = {
    matchCache.matchesFor(loginToken).filter(_.date.isAfter(LocalDateTime.now))
  }

  def byDateDescending(loginToken:String) = {
    matchCache.matchesFor(loginToken).sortWith( (m1,m2) => m1.date.isAfter(m2.date))
  }

  def matchesSatisfyingCriteria(loginToken:String, predicate:(AssignedMatch => Boolean)) = {
    matchCache.matchesFor(loginToken).filter(predicate)
  }

}
