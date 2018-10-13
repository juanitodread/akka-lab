package org.juanitodread.guidebook

import java.util.Locale

import akka.actor.{ ActorRef, ActorSystem, Props }
import org.juanitodread.guidebook.Tourist.Start

object Main {

  def main(args: Array[String]): Unit = {
    println("Starting the actor system...")

    val system: ActorSystem = ActorSystem("GuideSystem")

    val guideProps: Props = Props[Guidebook]
    val guidebook: ActorRef = system.actorOf(guideProps, "guidebook")

    val touristProps: Props = Props(classOf[Tourist], guidebook)
    val tourist: ActorRef = system.actorOf(touristProps, "tourist")

    tourist ! Start(Locale.getISOCountries)
  }
}
