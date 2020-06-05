name := "rarebooks"
version := "0.0.1"

lazy val root = (project in file("."))

scalaVersion := "2.12.8"

lazy val akkaVersion = "2.6.5"
lazy val logbackVersion = "1.2.3"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "org.scalatest" %% "scalatest" % "3.1.2" % "test",
)
