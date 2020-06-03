package org.juanitodread.guidebook

import java.util.{ Currency, Locale }

import akka.actor.typed.{ ActorRef, Behavior }
import akka.actor.typed.scaladsl.Behaviors

import org.juanitodread.guidebook.Tourist.Guidance

object Guidebook {
  final case class Inquiry(code: String, sender: ActorRef[Guidance])

  def apply(): Behavior[Inquiry] = Behaviors.receive { (context, message) =>
    context.log.info(s"Actor: ${context.self.path} responding to inquiry about ${message.code}")
    Locale
      .getAvailableLocales
      .filter(locale => locale.getCountry == message.code)
      .foreach(locale => message.sender ! Guidance(message.code, describe(locale)))
    Behaviors.same
  }

  def describe(locale: Locale): String = {
    s"In ${locale.getDisplayCountry}, ${locale.getDisplayLanguage} is spoken and the currency is the " +
      Currency.getInstance(locale).getDisplayName
  }
}
