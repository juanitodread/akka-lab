package org.juanitodread.guidebook

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

import org.juanitodread.guidebook.Guidebook.Inquiry

object Tourist {
  sealed trait TouristProtocol
  final case class Start(codes: Array[String], replyTo: ActorRef[Inquiry]) extends TouristProtocol
  final case class Guidance(code: String, description: String) extends TouristProtocol

  def apply(): Behavior[TouristProtocol] = Behaviors.receive { (context, message) =>
    message match {
      case Start(codes, replyTo) =>
        codes.foreach(code => replyTo ! Inquiry(code, context.self))
        Behaviors.same
      case Guidance(code, description) =>
        context.log.info(s"$code: $description")
        Behaviors.same
    }
  }
}
