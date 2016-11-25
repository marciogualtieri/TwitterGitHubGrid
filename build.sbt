lazy val commonSettings = Seq(
  name := "grid"
  , version := "1.0"
  , scalaVersion := "2.11.8"
  , libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-compiler" % scalaVersion.value
    , "org.scala-lang" % "scala-reflect" % scalaVersion.value
    , "org.scalatest" %% "scalatest" % "2.2.4" % "test,it"
    , "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test"
    , "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
    , "org.slf4j" % "slf4j-api" % "1.7.20"
    , "org.slf4j" % "slf4j-log4j12" % "1.7.20"
    , "com.typesafe" % "config" % "1.3.1"
    , "net.caoticode.buhtig" %% "buhtig" % "0.3.1"
    , "org.twitter4j" % "twitter4j-core" % "4.0.5"
    , "com.github.scopt" %% "scopt" % "3.5.0")
  , logLevel in assembly := Level.Error)

lazy val root = (project in file(".")).
  configs(IntegrationTest).
  settings(commonSettings: _*).
  settings(Defaults.itSettings: _*)
