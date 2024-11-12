val scala3Version = "3.5.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "TelegramBotPopularity",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "org.jsoup" % "jsoup" % "1.18.1",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.19",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test,
    libraryDependencies += "org.scalamock" %% "scalamock" % "6.0.0" % Test,
    libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.9.1"
  )
