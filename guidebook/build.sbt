name := "guidebook"
version := "0.1.0"

lazy val root = (project in file("."))

scalaVersion := "2.12.8"

lazy val akkaVersion = "2.6.5"
lazy val logbackVersion = "1.2.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "ch.qos.logback" % "logback-classic" % logbackVersion,
)
