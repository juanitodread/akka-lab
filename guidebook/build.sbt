name := "guidebook"
version := "0.1.0"

lazy val root = (project in file("."))

scalaVersion := "2.12.8"

val akkaVersion = "2.6.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
)
