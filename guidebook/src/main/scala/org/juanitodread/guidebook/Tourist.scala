package org.juanitodread.guidebook

import akka.actor.{ Actor, ActorLogging, ActorRef }
import org.juanitodread.guidebook.Guidebook.Inquiry
import org.juanitodread.guidebook.Tourist.{ Guidance, Start }

class Tourist(guidebook: ActorRef) extends Actor with ActorLogging {

  override def receive: Receive = {
    case Start(codes) =>
      codes.foreach(code => guidebook ! Inquiry(code))
    case Guidance(code, description) =>
      log.info(s"$code: $description")
  }

}

object Tourist {
  case class Guidance(code: String, description: String)
  case class Start(codes: Array[String])
}
