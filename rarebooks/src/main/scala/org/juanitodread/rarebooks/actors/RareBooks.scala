package org.juanitodread.rarebooks.actors

import akka.actor.{ Actor, ActorLogging, Props, Stash }
import org.juanitodread.rarebooks.actors.protocol.RareBooks.Message
import org.juanitodread.rarebooks.actors.RareBooks.{ Close, Open, Report }

class RareBooks extends Actor with ActorLogging with Stash {
  private val librarian = context.actorOf(Librarian.props, "librarian-actor")
  private var todayRequests: Int = 0
  private var totalRequests: Int = 0

  override def receive: Receive = open

  private def open: Receive = {
    case message: Message =>
      log.info("Message received and forwarded: {}", message)
      librarian.forward(message)
      todayRequests += 1
    case Close =>
      log.info("RareBooks is closed. Wait for tomorrow")
      context.become(closed)
  }

  private def closed: Receive = {
    case Report =>
      totalRequests += todayRequests
      log.info("Requests processed today: {}. Total requests: {}", todayRequests, totalRequests)
      todayRequests = 0
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
