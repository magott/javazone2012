package javazone12
import unfiltered.filter.Plan
import unfiltered.request.{Seg, Path, GET}
import unfiltered.response.{ResponseString, CharContentType, Html5, Ok}
import org.joda.time.{DateTimeZone, LocalDateTime}

class JavaZonePlan extends Plan {
  def intent = {
    case GET(Path(Seg(Nil))) => Ok ~> Html5(<h1>Hei sveis</h1>)
    case GET(Path(Seg("speakers" :: "arktekk" :: Nil))) => Ok ~> Html5(speakersTable)

  }

  def speakersTable = {
    val arktekkSpeakers = List("bodil","thor åge","erlend","kaare","morten")
    <h1>Speakers</h1>
    <table>
      {
        arktekkSpeakers.map(s => <tr><td>{s}</td></tr>)
      }
    </table>
  }

}
