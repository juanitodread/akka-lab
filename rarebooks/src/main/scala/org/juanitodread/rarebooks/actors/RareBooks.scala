package org.juanitodread.rarebooks.actors

import akka.actor.{ Actor, ActorLogging, ActorRef, Props }
import org.juanitodread.rarebooks.actors.protocol.RareBooks.FindBookByTitle

class RareBooks extends Actor with ActorLogging {
  private val librarian = createLibrarian()

  override def receive: Receive = {
    case findBookByTitle: FindBookByTitle =>
      log.info("Received findBookByTitle message: {}", findBookByTitle)
      librarian.forward(findBookByTitle)
  }

  protected def createLibrarian(): ActorRef = {
    context.actorOf(Librarian.props, "LibrarianActor")
  }
}

object RareBooks {
  trait State
  case object Open extends State
  case object Close extends State
  case object Report extends State

  def props: Props = Props(new RareBooks)
}
