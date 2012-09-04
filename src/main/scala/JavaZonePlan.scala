package javazone12
import unfiltered.filter.Plan
import unfiltered.request.{Seg, Path, GET}
import unfiltered.response.{ResponseString, CharContentType, Html5, Ok}
import org.joda.time.{DateTimeZone, LocalDateTime}

class JavaZonePlan extends Plan {
  def intent = {
    case GET(Path(Seg("javazone" :: Nil))) => Ok ~> Html5(<h1>Hei sveis</h1>)
    case GET(Path(Seg("javazone" :: "speakers" :: "arktekk" :: Nil))) => Ok ~> Html5(speakersTable)
    case GET(Path(Seg(Nil))) => Ok ~> Html5(Snippets.welcomePage)

    case GET(Path(Seg("kaare" :: "reminder" :: Nil))) => CharContentType("text/calendar") ~> ResponseString(kaareCal)
  }

  def speakersTable = {
    val arktekkSpeakers = List("erlend","kaare","morten")
    <h1>Speakers</h1>
    <table>
      {
        arktekkSpeakers.map(s => <tr><td>{s}</td></tr>)
      }
    </table>
  }

  def kaareCal = {
    val start = osloToUtc(new LocalDateTime(2012,9,12,15,40))
    VCalendar(VEvent("True unfiltered love!","Kaare skravler og demonstrerer Unfiltered!",start,start.plusHours(1),"Sal 5")).feed
  }

  private def osloToUtc(dateTime: LocalDateTime) = {
    dateTime.toDateTime(DateTimeZone.forID("Europe/Oslo")).withZone(DateTimeZone.UTC).toLocalDateTime
  }
}
