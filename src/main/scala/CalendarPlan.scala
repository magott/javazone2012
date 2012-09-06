package javazone12
import org.joda.time.{DateTimeZone, LocalDateTime}
import unfiltered.filter.Plan
import unfiltered.request.{GET, Seg, Path}
import unfiltered.response.{ResponseString, CharContentType, Ok}

class CalendarPlan extends Plan{

  def intent = {
    case GET(Path(Seg("kaare" :: "reminder" :: Nil))) => Ok ~> CharContentType("text/calendar") ~> ResponseString(kaareCal)
  }

  def kaareCal = {
    val start = osloToUtc(new LocalDateTime(2012,9,12,15,40))
    VCalendar(VEvent("True unfiltered love!","Kaare skravler og demonstrerer Unfiltered!",start,start.plusHours(1),"Sal 5")).feed
  }

  private def osloToUtc(dateTime: LocalDateTime) = {
    dateTime.toDateTime(DateTimeZone.forID("Europe/Oslo")).withZone(DateTimeZone.UTC).toLocalDateTime
  }

}
