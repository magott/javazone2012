import com.typesafe.startscript.StartScriptPlugin

seq(StartScriptPlugin.startScriptForClassesSettings: _*)

name:="javazone"

organization := "com.andersen-gott"

libraryDependencies ++=
  Seq(
    "net.databinder" %% "unfiltered" % "0.6.4",
    "net.databinder" %% "unfiltered-filter" % "0.6.4",
    "net.databinder" %% "unfiltered-jetty" % "0.6.4",
    "org.jsoup" % "jsoup" % "1.6.1",
    "joda-time" % "joda-time" % "2.1",
    "org.joda" % "joda-convert" % "1.2",
    "com.google.guava" % "guava" % "11.0.2"
  )