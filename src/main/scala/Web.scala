package javazone12;

import unfiltered.jetty
import util.Properties

object Web extends App {

  val port = Properties.envOrElse("PORT", "1358").toInt
  println("Starting on port:" + port)
  val http = jetty.Http(port)
  http.plan(new JavaZonePlan).plan(new CalendarPlan).run()

}

