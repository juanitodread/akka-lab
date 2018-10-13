package org.juanitodread.guidebook

import java.util.{ Currency, Locale }

import akka.actor.Actor
import org.juanitodread.guidebook.Guidebook.Inquiry
import org.juanitodread.guidebook.Tourist.Guidance

class Guidebook extends Actor {

  def describe(locale: Locale): String = {
    s"""
       |In ${locale.getDisplayCountry},
       |${locale.getDisplayLanguage} is spoken and the currency is the
       |${Currency.getInstance(locale).getDisplayName}
       |""".stripMargin
  }

  override def receive: Receive = {
    case Inquiry(code) =>
      println(s"Actor :${self.path.name} responding to inquiry about $code")
      Locale.getAvailableLocales
        .filter(locale => locale.getCountry == code)
        .foreach(locale => sender ! Guidance(code, describe(locale)))
  }

}

object Guidebook {
  case class Inquiry(code: String)
}
