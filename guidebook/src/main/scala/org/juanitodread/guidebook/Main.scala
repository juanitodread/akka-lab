package org.juanitodread.guidebook

import java.util.Locale

import akka.actor.typed.ActorSystem
import org.juanitodread.guidebook.TouristMain.SayStart
import org.slf4j.{ Logger, LoggerFactory }

object Main {
  private val log: Logger = LoggerFactory.getLogger(Main.toString)

  def main(args: Array[String]): Unit = {
    log.info("Starting the actor system...")

    val touristMain: ActorSystem[SayStart] = ActorSystem(TouristMain(), "GuideSystem")

    val countriesGroup = Locale
      .getISOCountries
      .grouped(Locale.getISOCountries.length / 3)

    countriesGroup
      .zipWithIndex
      .foreach {
        case (countries, index) =>
          touristMain ! SayStart(countries, s"guidebook-$index")
      }
  }
}
