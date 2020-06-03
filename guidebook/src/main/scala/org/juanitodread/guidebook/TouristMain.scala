package org.juanitodread.guidebook

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

import org.juanitodread.guidebook.Tourist.Start

object TouristMain {
  final case class SayStart(codes: Array[String], guidebookName: String)

  def apply(): Behavior[SayStart] = Behaviors.setup { context =>
    val tourist = context.spawn(Tourist(), "tourist")

    Behaviors.receiveMessage { message =>
      val replyTo = context.spawn(Guidebook(), message.guidebookName)
      tourist ! Start(message.codes, replyTo)
      Behaviors.same
    }
  }
}
