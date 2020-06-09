package org.juanitodread.rarebooks.actors

import akka.actor.{ Actor, ActorLogging, Props, Stash }
import org.juanitodread.rarebooks.actors.protocol.RareBooks.Message
import org.juanitodread.rarebooks.actors.RareBooks.{ Close, Open }

class RareBooks extends Actor with ActorLogging with Stash {
  private val librarian = context.actorOf(Librarian.props, "librarian-actor")

  override def receive: Receive = open

  private def open: Receive = {
    case message: Message =>
      log.info("Message received and forwarded: {}", message)
      librarian.forward(message)
    case Close =>
      log.info("RareBooks is closed. Wait for tomorrow")
      context.become(closed)
  }

  private def closed: Receive = {
    case Open =>
      log.info("Time to open RareBooks")
      unstashAll()
      context.become(open)
    case _ =>
      stash()
  }
}

object RareBooks {
  trait State
  case object Open extends State
  case object Close extends State
  case object Report extends State

  def props: Props = Props(new RareBooks)
}
