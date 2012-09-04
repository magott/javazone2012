package javazone12;
import org.joda.time.format.DateTimeFormat
import org.joda.time.LocalDateTime
import org.jsoup.Connection.Method
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import scala.collection.JavaConverters._
class MatchScraper {

  val dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm")

  def scrapeAssignedMatches(loginToken: String): List[AssignedMatch] = {
    val assignedMatchesResponse = Jsoup.connect("https://fiks.fotball.no/Fogisdomarklient/Uppdrag/UppdragUppdragLista.aspx")
      .cookie("ASP.NET_SessionId", loginToken).method(Method.GET).timeout(25000).followRedirects(false).execute()

    val assignedMatchesDoc = assignedMatchesResponse.parse
    val matchesElements = assignedMatchesDoc.select("div#divUppdrag")
                                            .select("table.fogisInfoTable > tbody > tr")
                                            .listIterator.asScala.drop(1)
    val upcomingAssignedMatches = matchesElements.map {
      el: Element =>
        AssignedMatch(dateTimeFormat.parseLocalDateTime(el.child(0).text),
          el.child(1).text,
          el.child(3).getElementsByTag("a").text,
          el.child(4).text,
          el.child(5).text,
          el.child(6).text,
          el.child(3).getElementsByTag("a").attr("href").split("=")(1))
    }
    upcomingAssignedMatches.toList
  }

}
