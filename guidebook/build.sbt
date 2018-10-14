name := "guidebook"
version := "1.0.0"

lazy val root = (project in file("."))

scalaVersion := "2.12.5"

val akkaVersion = "2.5.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-remote" % akkaVersion
)
