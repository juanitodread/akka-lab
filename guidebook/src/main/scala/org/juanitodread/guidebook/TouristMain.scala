package org.juanitodread.guidebook

import java.util.Locale

import akka.actor.{ ActorRef, ActorSystem, Props }
import akka.util.Timeout
import org.juanitodread.guidebook.Tourist.Start

import scala.concurrent.duration.SECONDS
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }

object TouristMain {

  def main(args: Array[String]): Unit = {
    val system: ActorSystem = ActorSystem("TouristActorSystem")

    val path = "akka.tcp://GuidebookActorSystem@127.0.0.1:8081/user/GuidebookActor"
    implicit val timeout: Timeout = Timeout(5, SECONDS)

    system.actorSelection(path).resolveOne.onComplete {
      case Success(guidebook) => {
        val touristProps: Props = Props(classOf[Tourist], guidebook)
        val tourist: ActorRef = system.actorOf(touristProps, "TouristActor")

        tourist ! Start(Locale.getISOCountries)
      }
      case Failure(ex) => println(ex)
    }
  }

}
