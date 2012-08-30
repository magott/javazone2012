import unfiltered.filter.Plan
import unfiltered.request.{Seg, Path, GET}
import unfiltered.response.{Html5, Ok}

class JavaZonePlan extends Plan {
  def intent = {
    case GET(Path(Seg(Nil))) => Ok ~> Html5(<h1>Hello</h1> <h2>Is it me you're looking for?</h2>)
    case GET(Path(Seg("javazone" :: Nil))) => Ok ~> Html5(<h1>Hei sveis</h1>)
    case GET(Path(Seg("javazone" :: "speakers" :: "arktekk" :: Nil))) => Ok ~> Html5(speakersTable)
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
}
