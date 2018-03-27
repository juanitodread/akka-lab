name := "hello-world-akka"

version := "1.0.0"

scalaVersion := "2.11.8"

lazy val root = (project in file("."))

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.10"
)
