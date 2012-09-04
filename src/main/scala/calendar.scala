package javazone12
import org.joda.time._

case class VCalendar(vevents: VEvent*) {


  private val calendarFormatString = "yyyyMMdd'T'HHmmss'Z"

  def feed = {
    vevents.foldLeft(start)(_ + "\n" + vevent(_)) + "\n" + end
  }

  def add(events: VEvent*) = VCalendar((vevents ++ events):_*)

  private def vevent(v: VEvent) = {
    """BEGIN:VEVENT
      |DTSTART:%s
      |DTEND:%s
      |DTSTAMP:%s
      |LAST-MODIFIED:%s
      |UID:%s
      |LOCATION:%s
      |SUMMARY:%s
      |DESCRIPTION:%s
      |END:VEVENT""".stripMargin.format(
      v.start.toString(calendarFormatString),
      v.end.toString(calendarFormatString),
      LocalDateTime.now.toString(calendarFormatString),
      LocalDateTime.now.toString(calendarFormatString),
      v.title + "@andersen-gott.com",
      v.location,
      v.title,
      v.description
    )
  }

  val start =
    """BEGIN:VCALENDAR
      |VERSION:2.0
      |PRODID:-//andersen-gott.com/demo//NONSGML v1.0//EN
      |METHOD:PUBLISH
      |X-WR-CALNAME:JZ12-demo
      |X-WR-CALDESC:JZ12-demo
      |X-PUBLISHED-TTL:PT1H""".stripMargin

  val end = "END:VCALENDAR"

}

case class VEvent(title:String, description:String, start:LocalDateTime, end:LocalDateTime, location:String)

